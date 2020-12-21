package it.nextworks.qameleon.pce.PCE;

import es.usc.citius.hipster.algorithm.Algorithm;
import es.usc.citius.hipster.algorithm.Hipster;
import es.usc.citius.hipster.graph.GraphBuilder;
import es.usc.citius.hipster.graph.GraphSearchProblem;
import es.usc.citius.hipster.graph.HipsterDirectedGraph;
import es.usc.citius.hipster.model.Node;
import es.usc.citius.hipster.model.problem.SearchProblem;
import it.nextworks.qameleon.pce.model.*;
import it.nextworks.qameleon.pce.sbi.TopologyAppRestClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PceService {

    @Value("${odl.hostname}")
    private String odlHostName;

    @Value("${odl.port}")
    private int odlPort;

    @Value("${odl.username}")
    private String odlUsername;

    @Value("${odl.password}")
    private String odlPassword;

    private TopologyAppRestClient topologyAppRestClient;

    private HashMap<String, LinkInfo> linkInfoMap;
    private HashMap<String, TapiPathComputationPath> computedMapPath;

    private static final Logger LOG = LoggerFactory.getLogger(PceService.class);
    private final String TOPOLOGY_ID = "QameleonTopology";


    public PceService(){
        LOG.info("Starting Path Computation Engine (PCE)...");
        linkInfoMap = new HashMap<>();
        computedMapPath = new HashMap<>();
    }

    private void initRestClients(){
        if(topologyAppRestClient==null){
            topologyAppRestClient =  new TopologyAppRestClient(odlHostName, odlPort, odlUsername, odlPassword);
        }
    }
    private int isUsable(JSONArray jsonArray, String elementId){
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject element = jsonArray.getJSONObject(i);
            String idElementToCompare = element.getString("uuid");
            String opState = element.getString("operational-state");
            String adminState =  element.getString("administrative-state");
            if(elementId.equals(idElementToCompare)){
             if(!opState.equals("ENABLED") || !adminState.equals("UNLOCKED")){
                LOG.warn("Element in not working state");
                return -1;
            }
            return i;
            }
        }
        LOG.warn("Element not found");
        return -1;
    }


    private boolean areNodeAndPortsUsable(JSONArray jsonArrayNodes, String nodeId, String portId){
        int nodeIndex = isUsable(jsonArrayNodes,nodeId);
        JSONArray onepNodeJsonArray;
        if(nodeIndex>-1){
            onepNodeJsonArray = jsonArrayNodes.getJSONObject(nodeIndex).getJSONArray("owned-node-edge-point");
        }
        else{
            LOG.warn("Cannot find/use node with uuid "+nodeId);
            return false;
        }

        int onepIndex = isUsable(onepNodeJsonArray,portId);
        if(onepIndex>-1){
            return true;
        }

        LOG.warn("port with uuid "+portId+" cannot be found/used");
        return false;
    }


    private HipsterDirectedGraph buildGraph(JSONArray jsonArrayLinks, JSONArray jsonArrayNodes){
        GraphBuilder graphBuilder = GraphBuilder.<String,Double>create();

        for(int i=0; i<jsonArrayLinks.length(); i++){
            JSONObject jsonQamLink = (JSONObject)jsonArrayLinks.get(i);
            String linkId = jsonQamLink.getString("uuid");
            String nodeSrc = jsonQamLink.getString("node-src");
            String portSrc = jsonQamLink.getString("port-src");
            String nodeDst = jsonQamLink.getString("node-dst");
            String portDst = jsonQamLink.getString("port-dst");

            JSONArray availableChannels = jsonQamLink.getJSONArray("available-channel");

            List<Object> myList =  availableChannels.toList();
            List<Integer> availableChannelList = new ArrayList<>();

            boolean isSrcUsable = areNodeAndPortsUsable(jsonArrayNodes, nodeSrc, portSrc);
            boolean isDstUsable = areNodeAndPortsUsable(jsonArrayNodes, nodeDst, portDst);

            for (int j = 0; j < myList.size(); j++) {
                availableChannelList.add((int)myList.get(j));
            }
            boolean atLeastOneChannelUsable = availableChannelList.size()>0;

            if(isSrcUsable && isDstUsable && atLeastOneChannelUsable) {
               linkInfoMap.put(nodeSrc+"_"+nodeDst,new LinkInfo(linkId,nodeSrc,nodeDst,portSrc,portDst, availableChannelList));
                graphBuilder = graphBuilder.connect(nodeSrc).to(nodeDst).withEdge(1d);
            }
            else{
                LOG.warn("Src: nodeID "+nodeSrc+" is usable "+isSrcUsable+ "Dst: nodeID "+nodeDst+" is usable "+isDstUsable+" atLeastOneChannelUsable "+atLeastOneChannelUsable);
            }
        }
        return graphBuilder.createDirectedGraph();
    }



    private boolean isSipIdValid(String sipId){
        return sipId.contains("_") && sipId.contains("sip");
    }



    public TapiPathComputationComputeP2PPath computeLightPath(TapiPathComputationComputep2ppathInputBodyparam tapiPathComputationComputep2ppathInputBodyParam) throws BadRequestException {

        if(tapiPathComputationComputep2ppathInputBodyParam==null || tapiPathComputationComputep2ppathInputBodyParam.getInput()==null){
            LOG.warn("No input provided.");
            throw new BadRequestException("No input provided.");
        }

        List<TapiPathComputationPathServiceEndPoint> endpointList = tapiPathComputationComputep2ppathInputBodyParam.getInput().getEndPoint();
        if(endpointList==null || endpointList.size()<2){
            LOG.warn("Not enough endpoints provided for path computation. They must be exactly two.");
            throw new BadRequestException("Not enough endpoints provided for path computation. They must be exactly two.");
        }

        String srcSipUuid = tapiPathComputationComputep2ppathInputBodyParam.getInput().getEndPoint().get(0).getServiceInterfacePoint().getServiceInterfacePointUuid();
        String dstSipUuid = tapiPathComputationComputep2ppathInputBodyParam.getInput().getEndPoint().get(1).getServiceInterfacePoint().getServiceInterfacePointUuid();

        if(!isSipIdValid(srcSipUuid) || !isSipIdValid(srcSipUuid)){
            LOG.warn("Bad request. SPI format not valid. It should be something like this: node-a_sip01. The underscore and \"sip\" substring are mandatory. ");
            throw new BadRequestException("Bad request. SPI format not valid.");
        }
        String srcNode = srcSipUuid.split("_")[0];
        String dstNode = dstSipUuid.split("_")[0];

        try {
            LOG.info("Received request to compute light path from "+srcNode+" to "+dstNode);
            initRestClients();//used this fix because in the constructor the config variables are not immediately loaded.
            //TODO Topology name hardcoded. To be fixed.
            JSONObject jsonObject = topologyAppRestClient.getTopology(TOPOLOGY_ID); //SUPPOSE TO use always the same topology (for now)
            if(jsonObject==null) {
                throw new BadRequestException("No topology found.");
            }
            String topologyUuid = jsonObject.getString("topology-uuid");
            JSONArray jsonArrayNodes = jsonObject.getJSONArray("qam-node");
            JSONArray jsonArrayLinks = jsonObject.getJSONArray("qam-link");

            if(jsonArrayNodes.length()<2 || jsonArrayLinks.length()<1){
                LOG.warn("Either the number of nodes ("+jsonArrayNodes.length()+") or the number of links ("+jsonArrayLinks.length()+") is not enough for path computation");
                throw new BadRequestException("Not enough elements for path computation found.");
            }
            LOG.info("Building graph from topology.");
            HipsterDirectedGraph hipsterDirectedGraph = buildGraph(jsonArrayLinks, jsonArrayNodes);
            LOG.info("Computing lightpath from source node "+srcNode+" to destination node "+dstNode+".");
            List<Object> paths = computePaths(hipsterDirectedGraph, srcNode, dstNode);
            LOG.info("Storing Explicit Route Object (ERO) into PCE.");
            String pathId = setEro(paths,topologyUuid);

            if(pathId==null) {
                LOG.warn("Cannot set ERO. Path not found.");
                throw new BadRequestException("Cannot set ERO. Path not found.");
            }
            LOG.info("The ERO id within PCE is "+pathId);
            return getPathRefUuid(pathId,tapiPathComputationComputep2ppathInputBodyParam.getInput());

        } catch (JSONException e) {
            LOG.error(e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }

    private List<Object> computePaths(HipsterDirectedGraph hipsterDirectedGraph, String nodeSrc, String nodeDst) {
        SearchProblem p = GraphSearchProblem
                .startingFrom(nodeSrc)
                .in(hipsterDirectedGraph)
                .takeCostsFromEdges()
                .build();

        Algorithm.SearchResult searchResult = Hipster.createDijkstra(p).search(nodeDst);
        searchResult.getGoalNodes();
        List<Object> nodes= Arrays.asList(Hipster.createDijkstra(p).search(nodeDst).getGoalNodes().toArray());
        return nodes;
        }




    private TapiPathComputationComputeP2PPath getPathRefUuid(String pathId, TapiPathComputationComputep2ppathInput tapiPathComputationComputep2ppathInput){
        TapiPathComputationComputeP2PPath tapiPathComputationComputeP2PPath = new TapiPathComputationComputeP2PPath();

        TapiPathComputationComputep2ppathOutput output = new TapiPathComputationComputep2ppathOutput();
        TapiPathComputationPathComputationService service = new TapiPathComputationPathComputationService();

        TapiPathComputationPathRef tapiPathComputationPathRef = new TapiPathComputationPathRef();
        tapiPathComputationPathRef.pathUuid(pathId);

        List<TapiPathComputationPathRef> path = new ArrayList<>();
        path.add(tapiPathComputationPathRef);

        service.setPath(path);
        service.setEndPoint(tapiPathComputationComputep2ppathInput.getEndPoint());
        service.setUuid(tapiPathComputationComputep2ppathInput.getUuid());
        service.setDirection(TapiCommonForwardingDirection.UNIDIRECTIONAL);
        service.setLayerProtocolName(TapiCommonLayerProtocolName.PHOTONIC_MEDIA);
        service.setObjectiveFunction(tapiPathComputationComputep2ppathInput.getObjectiveFunction());
        service.setTopologyConstraint(new ArrayList<>());
        //service.setRoutingConstraint(tapiPathComputationComputep2ppathInput.getRoutingConstraint());

        output.setService(service);
        tapiPathComputationComputeP2PPath.setOutput(output);
        return tapiPathComputationComputeP2PPath;
    }


    public TapiPathComputationPath getEro(String pathId){
        return computedMapPath.get(pathId);
    }

    public boolean removeComputedPath(String pathId){
        if(computedMapPath.remove(pathId)==null)
            return false;
        return true;
    }

    private String setEro(List<Object> paths, String topologyUuid){
        TapiPathComputationPath tapiPathComputationPath = new TapiPathComputationPath();

        Set<Integer> channelSet  = new HashSet<Integer>();

        String srcPath = null;
        String dstPath = null;
        for(int i=0; i<paths.size(); i++) {//Path size is the number of paths found
            Node node = (Node) paths.get(i);

            if(Algorithm.recoverStatePath(node).size()-1==0){
                return null;
            }


            for(int j=0; j<Algorithm.recoverStatePath(node).size()-1; j++){

                String src = (String)Algorithm.recoverStatePath(node).get(j);
                String dst = (String)Algorithm.recoverStatePath(node).get(j+1);
                String linkId = linkInfoMap.get(src+"_"+dst).getLinkId();
                if(j==0) {
                    channelSet.addAll(linkInfoMap.get(src + "_" + dst).getAvailableChannels());
                    srcPath=src;
                }
                else {
                    channelSet.retainAll(linkInfoMap.get(src + "_" + dst).getAvailableChannels());
                    if(j==Algorithm.recoverStatePath(node).size()-2)
                        dstPath = dst;
                }
                TapiTopologyLinkRef tapiTopologyLinkRef = new TapiTopologyLinkRef();
                tapiTopologyLinkRef.setLinkUuid(linkId);
                tapiTopologyLinkRef.setTopologyUuid(topologyUuid);
                tapiPathComputationPath.addLinkItem(tapiTopologyLinkRef);
            }
        }

        if(channelSet.size()==0) {
            LOG.warn("Not able to find a common available channel.");
            return null;
        }

        Integer minimumChannel = Collections.min(channelSet);


        List<TapiCommonNameAndValue> tapiCommonNameAndValues = new ArrayList<>();
        TapiCommonNameAndValue tapiCommonNameAndValue = new TapiCommonNameAndValue();
        tapiCommonNameAndValue.setValueName("channel");
        tapiCommonNameAndValue.setValue(String.valueOf(minimumChannel));
        tapiCommonNameAndValues.add(tapiCommonNameAndValue);

        TapiCommonNameAndValue tapiCommonNameAndValueSrcPath = new TapiCommonNameAndValue();
        tapiCommonNameAndValueSrcPath.setValueName("src");
        tapiCommonNameAndValueSrcPath.setValue(srcPath);
        tapiCommonNameAndValues.add(tapiCommonNameAndValueSrcPath);

        TapiCommonNameAndValue tapiCommonNameAndValueDstPath = new TapiCommonNameAndValue();
        tapiCommonNameAndValueDstPath.setValueName("dst");
        tapiCommonNameAndValueDstPath.setValue(dstPath);
        tapiCommonNameAndValues.add(tapiCommonNameAndValueDstPath);

        tapiPathComputationPath.setName(tapiCommonNameAndValues);
        String pathId = String.valueOf(Math.abs(tapiPathComputationPath.hashCode()));
        tapiPathComputationPath.setUuid(pathId);
        //tapiPathComputationPath.setRoutingConstraint(new TapiPathComputationRoutingConstraint());
        tapiPathComputationPath.setLayerProtocolName(TapiCommonLayerProtocolName.PHOTONIC_MEDIA);
        tapiPathComputationPath.setDirection(TapiCommonForwardingDirection.UNIDIRECTIONAL);

        computedMapPath.put(pathId, tapiPathComputationPath);
        return pathId;
    }
}
