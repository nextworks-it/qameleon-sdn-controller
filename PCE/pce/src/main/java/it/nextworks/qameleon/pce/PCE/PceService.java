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

    @Value("${topologyName}")
    private String topologyName;

    private final int ERROR = -1;
    private TopologyAppRestClient topologyAppRestClient;

    private HashMap<String, LinkInfo> linkInfoMap;
    private HashMap<String, TapiPathComputationPath> computedMapPath;

    private static final Logger LOG = LoggerFactory.getLogger(PceService.class);



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
                return ERROR;
            }
            return i;
            }
        }
        LOG.warn("Element not found");
        return ERROR;
    }


    private boolean areNodeAndPortsUsable(JSONArray jsonArrayNodes, String nodeId, String portId){
        int nodeIndex = isUsable(jsonArrayNodes,nodeId);
        JSONArray onepNodeJsonArray;
        if(nodeIndex>ERROR){
            onepNodeJsonArray = jsonArrayNodes.getJSONObject(nodeIndex).getJSONArray("owned-node-edge-point");
        }
        else{
            LOG.warn("Cannot find/use node with uuid "+nodeId);
            return false;
        }

        int onepIndex = isUsable(onepNodeJsonArray,portId);
        if(onepIndex>ERROR){
            return true;
        }

        LOG.warn("port with uuid "+portId+" cannot be found/used");
        return false;
    }


    private HipsterDirectedGraph buildGraph(JSONArray jsonArrayLinks, JSONArray jsonArrayNodes, List<String> linksToInclude, List<String> linksToExclude){
        GraphBuilder graphBuilder = GraphBuilder.<String,Double>create();

        linkInfoMap.clear();
        for(int i=0; i<jsonArrayLinks.length(); i++){
            double linkWeight =2d;
            JSONObject jsonQamLink = (JSONObject)jsonArrayLinks.get(i);
            String linkId = jsonQamLink.getString("uuid");
            if(linksToExclude!=null && linksToExclude.size()>0 && linksToExclude.contains(linkId)){
                LOG.info("Link with UUID "+linkId+" must be excluded from the path");
                continue;
            }
            if(linksToInclude!=null && linksToInclude.size()>0 && linksToInclude.contains(linkId)){
                LOG.info("Link with UUID "+linkId+" must be included in the path");
                linkWeight = 1d;
            }
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
                String keyMap = nodeSrc+"_"+nodeDst;
                if(linkInfoMap.get(keyMap)==null){
                   linkInfoMap.put(keyMap,new LinkInfo(linkId,nodeSrc,nodeDst,portSrc,portDst, availableChannelList,linkWeight));
                   LOG.info("Adding link "+linkId+ " from src "+nodeSrc+" to destination "+nodeDst+" with weight "+linkWeight);
                   graphBuilder = graphBuilder.connect(nodeSrc).to(nodeDst).withEdge(linkWeight);
                }else{
                    LinkInfo linkInfo =linkInfoMap.get(keyMap);
                    double weightExistingLink = linkInfo.getWeight();
                    if(linkWeight<weightExistingLink){
                        linkInfoMap.put(keyMap,new LinkInfo(linkId,nodeSrc,nodeDst,portSrc,portDst, availableChannelList,linkWeight));
                        LOG.info("Replacing link "+linkId+ " from src "+nodeSrc+" to destination "+nodeDst+" with weight "+linkWeight);
                    }
                }
            }
            else{
                LOG.warn("Src: nodeID "+nodeSrc+" is usable "+isSrcUsable+ ". Dst: nodeID "+nodeDst+" is usable "+isDstUsable+" atLeastOneChannelUsable "+atLeastOneChannelUsable);
            }
        }
        return graphBuilder.createDirectedGraph();
    }



    private boolean isSipIdValid(String sipId){
        return sipId.contains("_") && sipId.contains("sip");
    }


    private String getSrcNodeUuid(TapiPathComputationPathServiceEndPoint ep1, TapiPathComputationPathServiceEndPoint ep2){
        //TapiCommonPortDirection sourceDirection = TapiCommonPortDirection.INPUT;
        TapiCommonPortDirection sourceDirection = TapiCommonPortDirection.OUTPUT;

        if(ep1.getDirection()!=null && ep1.getDirection().equals(sourceDirection))
            return ep1.getServiceInterfacePoint().getServiceInterfacePointUuid().split("_")[0];

        if(ep2.getDirection()!=null && ep2.getDirection().equals(sourceDirection))
            return ep2.getServiceInterfacePoint().getServiceInterfacePointUuid().split("_")[0];

        String defaultEpSrc = ep1.getServiceInterfacePoint().getServiceInterfacePointUuid().split("_")[0];
        LOG.warn("Not able to find end point with INPUT as direction port. Considering "+defaultEpSrc+ " as default src");
        return defaultEpSrc;
    }

    private String getDstNodeUuid(TapiPathComputationPathServiceEndPoint ep1, TapiPathComputationPathServiceEndPoint ep2){
        //TapiCommonPortDirection dstDirection = TapiCommonPortDirection.OUTPUT;
        TapiCommonPortDirection dstDirection = TapiCommonPortDirection.INPUT;
        if(ep1.getDirection()!=null && ep1.getDirection().equals(dstDirection))
            return ep1.getServiceInterfacePoint().getServiceInterfacePointUuid().split("_")[0];

        if(ep2.getDirection()!=null && ep2.getDirection().equals(dstDirection))
            return ep2.getServiceInterfacePoint().getServiceInterfacePointUuid().split("_")[0];

        String defaultEpDst = ep2.getServiceInterfacePoint().getServiceInterfacePointUuid().split("_")[0];
        LOG.warn("Not able to find end point with OUTPUT as direction port. Considering "+defaultEpDst+ " as default src");
        return defaultEpDst;
    }

    private int getOpticalChannelConstraint(List<TapiCommonNameAndValue> nameList){
        int channelConstraint = -1;
        LOG.info("{}",nameList==null);
        if(nameList==null || nameList.size()==0){
            LOG.info("No constraints on either channel or frequency or wavelength found.");
            return channelConstraint;
        }

         if(nameList.get(0).getValueName().equals("channel")){
                channelConstraint = Integer.valueOf(nameList.get(0).getValue());
                LOG.info("Found constraint on channel "+channelConstraint +"the path computation request ");
            }

        else{
            LOG.info("No constraints on either channel or frequency or wavelength found.");
        }
        return channelConstraint;
    }


    private boolean areListElementsInAnotherList(List<String> listElements, List<String> otherList){
        for(String elementToCheck: listElements){
            if(!otherList.contains(elementToCheck)) {
                LOG.warn(elementToCheck + " not available in the list");
                return false;
            }
        }
        return true;
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

        TapiPathComputationPathServiceEndPoint ep1 =tapiPathComputationComputep2ppathInputBodyParam.getInput().getEndPoint().get(0);
        TapiPathComputationPathServiceEndPoint ep2 =tapiPathComputationComputep2ppathInputBodyParam.getInput().getEndPoint().get(1);
        String srcSipUuid = ep1.getServiceInterfacePoint().getServiceInterfacePointUuid();
        String dstSipUuid = ep2.getServiceInterfacePoint().getServiceInterfacePointUuid();


        //tapiPathComputationComputep2ppathInputBodyParam.getInput().getEndPoint().get(1).getName()
        if(!isSipIdValid(srcSipUuid) || !isSipIdValid(dstSipUuid)){
            LOG.warn("Bad request. SPI format not valid. It should be something like this: node-a_sip01. The underscore and \"sip\" substring are mandatory. ");
            throw new BadRequestException("Bad request. SPI format not valid.");
        }
        LOG.info("Source SIP is "+srcSipUuid);
        LOG.info("Destination SIP is "+dstSipUuid);

        String srcNode = getSrcNodeUuid(ep1, ep2);
        String dstNode = getDstNodeUuid(ep1, ep2);

        try {
            LOG.info("Received request to compute light path from "+srcNode+" to "+dstNode);
            initRestClients();//used this fix because in the constructor the config variables are not immediately loaded.
            JSONObject jsonObject = topologyAppRestClient.getTopology(topologyName);
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
            List<String> includeLink = new ArrayList<>();
            List<String> excludeLink = new ArrayList<>();
            if(tapiPathComputationComputep2ppathInputBodyParam.getInput().getTopologyConstraint()!=null) {
                LOG.info("Topology constraints found.");
                includeLink = tapiPathComputationComputep2ppathInputBodyParam.getInput().getTopologyConstraint().getIncludeLink();
                excludeLink = tapiPathComputationComputep2ppathInputBodyParam.getInput().getTopologyConstraint().getExcludeLink();
            }

            List<String> linkUuidList = getLinkUuidList(jsonArrayLinks);
            if(includeLink!=null && includeLink.size()>0){
                if(!areListElementsInAnotherList(includeLink, linkUuidList))
                    throw new BadRequestException("Include link list contains links UUID not available into topology links ");
            }

            if(excludeLink!=null && excludeLink.size()>0){
                if(!areListElementsInAnotherList(excludeLink, linkUuidList))
                    throw new BadRequestException("Exclude link list contains links UUID not available into topology links ");
            }


            HipsterDirectedGraph hipsterDirectedGraph = buildGraph(jsonArrayLinks, jsonArrayNodes,includeLink, excludeLink);
            LOG.info("Computing lightpath from source node "+srcNode+" to destination node "+dstNode+".");
            List<Object> paths = computePaths(hipsterDirectedGraph, srcNode, dstNode);
            LOG.info("Storing Explicit Route Object (ERO) into PCE.");


            int channelConstraint = -1;
            int channelConstraintSrc = getOpticalChannelConstraint(ep1.getName());
            int channelConstraintDst = getOpticalChannelConstraint(ep2.getName());

            if(channelConstraintSrc == channelConstraintDst || channelConstraintSrc != -1){
                channelConstraint = channelConstraintSrc;
            }
            if(channelConstraintSrc == -1 || channelConstraintDst != -1){
                channelConstraint = channelConstraintDst;
            }

            String pathId = setEro(paths,topologyUuid, channelConstraint);

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

    private List<String> getLinkUuidList(JSONArray jsonArrayLinks) {
        List<String> linkUuid = new ArrayList<>();
        for(int i=0; i<jsonArrayLinks.length(); i++) {
            JSONObject jsonQamLink = (JSONObject) jsonArrayLinks.get(i);
            String linkUuidStr = jsonQamLink.getString("uuid");
            linkUuid.add(linkUuidStr);
        }
        return linkUuid;
    }

    private List<Object> computePaths(HipsterDirectedGraph hipsterDirectedGraph, String nodeSrc, String nodeDst) {
        SearchProblem p = GraphSearchProblem
                .startingFrom(nodeSrc)
                .in(hipsterDirectedGraph)
                .takeCostsFromEdges()
                .build();

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


    private String setEro(List<Object> paths, String topologyUuid, int channelConstraint){
        TapiPathComputationPath tapiPathComputationPath = new TapiPathComputationPath();

        Set<Integer> channelSet  = new HashSet<>();

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

        Integer channelSelected = Collections.min(channelSet); //By default the minimum channel is selected

        //if a channel constraint is specified, is selected that one
        if(channelConstraint!=-1 && channelSet.contains(channelConstraint)){
            channelSelected = channelConstraint;
        }

        if(channelConstraint!=-1 && !channelSet.contains(channelConstraint)){
            LOG.warn("The path computation request cannot be satisfied: channel "+channelConstraint+" not available");
            return null;
        }

        List<TapiCommonNameAndValue> tapiCommonNameAndValues = new ArrayList<>();
        TapiCommonNameAndValue tapiCommonNameAndValue = new TapiCommonNameAndValue();
        tapiCommonNameAndValue.setValueName("channel");
        tapiCommonNameAndValue.setValue(String.valueOf(channelSelected));
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
