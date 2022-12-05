package it.nextworks.qameleon.topologyApp.service;

import com.google.common.util.concurrent.ListenableFuture;
import it.nextworks.common.TwoWaysChannelFreqTranslator;
import it.nextworks.generic.topologyApp.nbi.NxwTapiTopologyServiceImpl;
import it.nextworks.qameleon.topology_driver.DeviceInfoReader;
import it.nextworks.qameleon.topology_driver.impl.*;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.CreateNxwTopologyInputBuilder;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.CreateNxwTopologyOutput;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.DelNxwTopologyInputBuilder;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.create.nxw.topology.input.NxwTopologyInput;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.create.nxw.topology.input.NxwTopologyInputBuilder;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.nxw.topology.NxwNode;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.nxw.topology.NxwNodeBuilder;
import org.opendaylight.yang.gen.v1.qameleon.common.rev200914.QNodeType;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.*;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnection;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLink;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLinkBuilder;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNode;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNodeBuilder;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.cont.qam.node.owned.node.edge.point.QamCepList;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.cont.qam.node.owned.node.edge.point.QamCepListBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.LayerProtocolName;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.OperationalState;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.cep.list.ConnectionEndPoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.cep.list.ConnectionEndPointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.link.NodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.link.NodeEdgePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePointBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class TopologyQameleonService {

    private static final Logger LOG = LoggerFactory.getLogger(TopologyQameleonService.class);

    private HashMap<String, DeviceInfoReader> deviceInfoReaders;
    private HashMap<String, ConnectionInfo> qameleonConnectivityMap;
    private QamTopologyInventoryService qamTopologyInventoryService;
    private final int TIMEOUT_TRY = 5000;
    private final int MAX_TENTATIVE = 3;


    public TopologyQameleonService(DataBroker dataBroker) {
        deviceInfoReaders = new HashMap<>();
        qameleonConnectivityMap =  new HashMap<>();
        qamTopologyInventoryService = new QamTopologyInventoryService(dataBroker);
    }


    public boolean createTopology(NxwTapiTopologyServiceImpl nxwTapiTopologyServiceImpl, final MountPointService mountPointService, QamTopology qamTopology){
        if(!connectToDevice(nxwTapiTopologyServiceImpl, qamTopology)){
           //TODO make rollback
            return false;
        }
        if(!getInternalConnectivity(mountPointService, qamTopology))
            return false;

        return  setChannels();
    }

    public boolean deleteTopology(NxwTapiTopologyServiceImpl nxwTapiTopologyServiceImpl, String topologyUuid){
        QamTopology qamTopology = getQamTopology();
        if(qamTopology==null || !qamTopology.getTopologyUuid().equals(topologyUuid)){
            LOG.error("Unable to delete topology with UUID "+topologyUuid+". It was not found.");
            return false;
        }
        try {
            boolean nodesDisconnectedSuccessful =  nxwTapiTopologyServiceImpl.delNxwTopology(new DelNxwTopologyInputBuilder().setTopologyId(topologyUuid).build()).get().isSuccessful();
            if(!nodesDisconnectedSuccessful){
                LOG.error("Error during deletion: unable to disconnect NETCONF devices.");
                return false;
            }
            if(!qamTopologyInventoryService.deleteQamTopologyFromTree()){
                LOG.error("Error during deletion: unable to remove topology from qameleon topology tree.");
                return false;
            }

            qameleonConnectivityMap.clear();
            deviceInfoReaders.clear();
            return true;
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
            return false;
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    private boolean connectToDevice(NxwTapiTopologyServiceImpl nxwTapiTopologyServiceImpl, QamTopology qamTopology){
        LOG.info("Going to connect nodes to devices");
        List<NxwNode> nxwNodes = new ArrayList<>();
        for(QamNode qNode: qamTopology.getQamNode()){
                NxwNode nxwNode= new NxwNodeBuilder()
                        .setHost(qNode.getHost())
                        .setPort(qNode.getPort())
                        .setUsername(qNode.getUsername())
                        .setPassword(qNode.getPassword())
                        .setAdministrativeState(qNode.getAdministrativeState())
                        .setUuid(qNode.getUuid()).build();
                nxwNodes.add(nxwNode);
        }

        NxwTopologyInput nxwTopologyInput = new NxwTopologyInputBuilder()
                //.setTopologyUuid("topology-netconf")
                .setTopologyUuid(qamTopology.getTopologyUuid())
                .setNxwNode(nxwNodes)
                .setNxwLink(new ArrayList<>()).build();
        ListenableFuture<RpcResult<CreateNxwTopologyOutput>> out = nxwTapiTopologyServiceImpl.createNxwTopology(new CreateNxwTopologyInputBuilder().setNxwTopologyInput(nxwTopologyInput).build());
        try {
            return out.get().isSuccessful();
        } catch (InterruptedException e) {
            LOG.error("InterruptedException e");
            LOG.error(e.getMessage());
            return false;
        } catch (ExecutionException e) {
            LOG.error("ExecutionException e");
            LOG.error(e.getMessage());
            return false;
        }

    }


    private boolean getInternalConnectivity(final MountPointService mountPointService, QamTopology qamTopology){

        List<QamNode> qamNodeListUpdate = new ArrayList<>();

        for(QamNode qamNode: qamTopology.getQamNode()) {
            String nodeId = qamNode.getUuid().getValue();
            LOG.info("Getting internal connectivity for device with ID "+nodeId);
            List<InternalConnection> internalConnectionsList;
            List<OwnedNodeEdgePoint> ownedNodeEdgePoints;

            DeviceInfoReader deviceInfoReader = getDeviceInfoReader(mountPointService, nodeId, qamNode);

            if(deviceInfoReader==null) {
                continue;
            }
            ownedNodeEdgePoints = deviceInfoReader.getDeviceInfo().getOwnedNodeEdgePoint();
            internalConnectionsList = deviceInfoReader.getInternalConnection();

            deviceInfoReaders.put(nodeId,deviceInfoReader);
            qamNode = new QamNodeBuilder(qamNode).
                    setOperationalState(OperationalState.ENABLED).
                    setInternalConnection(internalConnectionsList).
                    setOwnedNodeEdgePoint(ownedNodeEdgePoints).

                    build();

            qamNodeListUpdate.add(qamNode);
            qamTopology = new QamTopologyContBuilder(qamTopology).setQamNode(qamNodeListUpdate).build();
        }

        qamTopology = new QamTopologyContBuilder(qamTopology).setQamNode(qamNodeListUpdate).build();
        return qamTopologyInventoryService.writeQamTopologyIntoTree(qamTopology);
    }


    public QamLink getQamLink(String qamLinkId){
        QamTopology qamTopology = qamTopologyInventoryService.getQamTapiTopologyFromTree();
        for(int i = 0; i< qamTopology.getQamLink().size();i++)
        {
            if (qamTopology.getQamLink().get(i).getUuid().getValue().equals(qamLinkId))
                return qamTopology.getQamLink().get(i);
        }
        LOG.warn("Link with UUID "+qamLinkId+" not found");
        return null;
    }

    private int getQamLinkIndex(QamTopology qamTopology, String qamLinkId){
        int index = -1;
        for(int i = 0; i< qamTopology.getQamLink().size();i++)

            {
                if (qamTopology.getQamLink().get(i).getUuid().getValue().equals(qamLinkId))
                    return i;
            }

        return index;
    }


    private List<Integer> getUnionFromLists(List <Integer> list1, List <Integer> list2){
        Set<Integer> set = new HashSet<>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }

    private boolean setChannels(){
        LOG.info("Setting availability of channels");
        QamTopology qamTopology = qamTopologyInventoryService.getQamTapiTopologyFromTree();
        TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator
                = new TwoWaysChannelFreqTranslator();

        List<Integer> listOfAllChannels = new ArrayList<>(twoWaysChannelFreqTranslator.getChannelSet());
        Integer lastChannelNumber = listOfAllChannels.size();

        List<QamLink> qamLinkListNew = new ArrayList<>();
        String topologyId = qamTopology.getTopologyUuid();
        if(qamTopology.getQamLink()!=null) {
            for (QamLink qamLink : qamTopology.getQamLink()) {

                NodeEdgePoint nodeEdgePointSrc = new NodeEdgePointBuilder()
                        .setNodeEdgePointUuid(new Uuid(qamLink.getPortSrc()))
                        .setNodeUuid(new Uuid(qamLink.getNodeSrc())).
                                setTopologyUuid(new Uuid(topologyId)).build();

                NodeEdgePoint nodeEdgePointDst = new NodeEdgePointBuilder()
                        .setNodeEdgePointUuid(new Uuid(qamLink.getPortDst()))
                        .setNodeUuid(new Uuid(qamLink.getNodeDst())).
                                setTopologyUuid(new Uuid(topologyId)).build();

                List<NodeEdgePoint> nodeEdgePoints = new ArrayList<>();
                nodeEdgePoints.add(nodeEdgePointSrc);
                nodeEdgePoints.add(nodeEdgePointDst);

                List<LayerProtocolName> layerProtocolNames = new ArrayList<>();
                layerProtocolNames.add(LayerProtocolName.ODU);
                layerProtocolNames.add(LayerProtocolName.PHOTONICMEDIA);
                long minFreq = Math.round(twoWaysChannelFreqTranslator.channelToFrequency(1));
                long maxFreq = Math.round(twoWaysChannelFreqTranslator.channelToFrequency(lastChannelNumber));

                String nodeSrcUuid = qamLink.getNodeSrc();
                String nodeDstUuid = qamLink.getNodeDst();

                DeviceInfoReader deviceInfoReaderSrc = deviceInfoReaders.get(nodeSrcUuid);
                if(deviceInfoReaderSrc==null){
                    LOG.error("Cannot find source node with UUID "+nodeSrcUuid);
                    continue;
                }

                List<Integer> srcNodeOutputChannelOccupied = deviceInfoReaderSrc.getOutputChannelOccupied();

                DeviceInfoReader deviceInfoReaderDst = deviceInfoReaders.get(nodeDstUuid);
                if(deviceInfoReaderDst==null){
                    LOG.error("Cannot destionation find node with UUID "+nodeDstUuid);
                    continue;
                }
                List<Integer> dstNodeInputChannelOccupied = deviceInfoReaderDst.getInputChannelOccupied();

                List<Integer> occupiedChannelLink = getUnionFromLists(srcNodeOutputChannelOccupied, dstNodeInputChannelOccupied);
                List<Integer> availableChannels = new ArrayList<>(listOfAllChannels);
                availableChannels.removeAll(occupiedChannelLink);

                qamLink = new QamLinkBuilder(qamLink)
                        .setLowerFrequency(new BigInteger(String.valueOf(minFreq)))
                        .setUpperFrequency(new BigInteger(String.valueOf(maxFreq)))
                        //.setFrequencyConstraint(frequencyConstraint)
                        //.setChannelSpacing(CHANNEL_WIDTH_MHZ)
                        //.setAvailableChannel(listOfAllChannels) OLD
                        //.setOccupiedChannel(new ArrayList<>()) OLD
                        .setAvailableChannel(availableChannels)
                        .setOccupiedChannel(occupiedChannelLink)
                        .setNodeEdgePoint(nodeEdgePoints)
                        .setLayerProtocolName(layerProtocolNames)
                        .build();
                qamLinkListNew.add(qamLink);
                qameleonConnectivityMap.put(
                        qamLink.getUuid().getValue(),
                        new ConnectionInfo(
                                qamLink.getUuid().getValue(),
                                qamLink.getNodeSrc(),
                                qamLink.getNodeDst()));
            }
        }
        qamTopology = new QamTopologyContBuilder(qamTopology).setQamLink(qamLinkListNew).build();
        return qamTopologyInventoryService.writeQamTopologyIntoTree(qamTopology);
    }


     private void moveElementsBetweenLists(List<Integer> source, List<Integer> destination, List<Integer> elements){
            for(Integer item: elements){
                    source.remove(item);
                    destination.add(item);
            }
    }


    public boolean updateLinkInfoChannelRemoved(RemoveLightPathInput input){
        List<Integer> channelList = new ArrayList<Integer>();
        channelList.add(input.getChannelNumber());
        List<String> linkUuidList = input.getLinkUuidList();
        for(String linkUuid: linkUuidList){
            if(!freeChannels(linkUuid, channelList)){
                return false;
            }
        }
        return true;
    }


    public boolean freeChannels(String linkId, List<Integer> channelToFree){
        QamTopology qamTopology = qamTopologyInventoryService.getQamTapiTopologyFromTree();
        int linkIndex = getQamLinkIndex(qamTopology, linkId);
        if(linkIndex==-1)
            return false;

        QamLink qamLink = qamTopology.getQamLink().get(linkIndex);
        List<Integer> occupiedChannel = qamLink.getOccupiedChannel();
        List<Integer> availableChannel = qamLink.getAvailableChannel();

        for(int i=0; i<channelToFree.size(); i++){
            if(availableChannel.contains(channelToFree.get(i))) {
                LOG.error("Channel " + channelToFree.get(i) + " already free");
                return false;
            }
        }
        moveElementsBetweenLists(occupiedChannel, availableChannel, channelToFree);
        List<QamLink> qamListLink = qamTopology.getQamLink();
        qamListLink.set(linkIndex, qamLink);
        return  qamTopologyInventoryService.writeQamTopologyIntoTree( new  QamTopologyContBuilder(qamTopology).setQamLink(qamListLink).build());
    }



    public boolean updateLinkInfoChannelAdded(Integer channelNumber, List<String> linkListUuid){
        List<Integer> channelList = new ArrayList<Integer>();
        channelList.add(channelNumber);

        for(String linkUuid: linkListUuid){
            if(!occupyChannels(linkUuid, channelList)){
                LOG.error("Error during light path info updating for link with Uuid "+linkUuid);
                return false;
            }
            String nodeSrc = qameleonConnectivityMap.get(linkUuid).getNodeSrcId();
            String nodeDst = qameleonConnectivityMap.get(linkUuid).getNodeSrcId();
            LOG.info("Link identifier cross connection has been setup is : "+linkUuid);
            LOG.info("Node src identifier cross connection has been setup is : "+nodeSrc);
            LOG.info("Node src identifier cross connection has been setup is : "+nodeDst);

            //LOG.info("Retrieving information from source ("+nodeSrc+") and destination ("+nodeDst+") nodes where cross connections has been setup");

            
            deviceInfoReaders.get(nodeSrc).getInternalConnection(); //The internal connection (real or dummy) are not actually update yet. TODO
            deviceInfoReaders.get(nodeDst).getInternalConnection();
        }
        LOG.info("Link info correctly updated.");
        return true;
    }

    private List<QamNode> addCepIntoQamNode(QamTopology qamTopology, String nodeUuid, String onep){
        List<QamNode> qamNodeList = qamTopology.getQamNode();
        int nodeIndex = -1;
        int onepIndex = -1;
        QamCepList cepList = null;

        for(int i=0; i<qamNodeList.size(); i++){
            QamNode qamNode = qamNodeList.get(i);
            if(qamNode.getUuid().getValue().equals(nodeUuid)) {
                nodeIndex = i;
                for (int j=0; j<qamNode.getOwnedNodeEdgePoint().size(); j++) {
                    OwnedNodeEdgePoint ownedNodeEdgePoint = qamNode.getOwnedNodeEdgePoint().get(j);


                    if (ownedNodeEdgePoint.getUuid().getValue().equals(onep)) {
                        onepIndex = j;

                        OwnedNodeEdgePoint1 onep1 = ownedNodeEdgePoint.augmentation(OwnedNodeEdgePoint1.class);
                        if (onep1 == null || onep1.getQamCepList() == null || onep1.getQamCepList().getConnectionEndPoint().size() == 0) {

                            LOG.warn("No CEP found into Node with UUID "+nodeUuid+ " at ONEP "+nodeUuid+". Creating a new one.");
                            List<ConnectionEndPoint> connectionEndPoints = new ArrayList<>();
                            connectionEndPoints.add(new ConnectionEndPointBuilder().setUuid(new Uuid("CEP00")).build());
                            cepList = new QamCepListBuilder().setConnectionEndPoint(connectionEndPoints).build();

                        } else {
                            int cepCount = cepList.getConnectionEndPoint().size();
                            List<ConnectionEndPoint> connectionEndPoints = cepList.getConnectionEndPoint();
                            connectionEndPoints.add(new ConnectionEndPointBuilder().setUuid(new Uuid("CEP"+cepCount)).build());
                            cepList = new QamCepListBuilder().setConnectionEndPoint(connectionEndPoints).build();
                        }
                    }

                }
            }
        }
        if(cepList!=null){

            QamNode qamNodeTmp = qamNodeList.get(nodeIndex);
            List<OwnedNodeEdgePoint> onepListTmp = qamNodeTmp.getOwnedNodeEdgePoint();

            OwnedNodeEdgePoint1 onep1 = new OwnedNodeEdgePoint1Builder().setQamCepList(cepList).build();
            OwnedNodeEdgePoint onepTmp = new OwnedNodeEdgePointBuilder(onepListTmp.get(onepIndex))
                    .addAugmentation(OwnedNodeEdgePoint1.class,onep1).build();

            onepListTmp.set(onepIndex,onepTmp);
            QamNode qamNodeNew = new QamNodeBuilder(qamNodeTmp).setOwnedNodeEdgePoint(onepListTmp).build();
            qamNodeList.set(nodeIndex, qamNodeNew);
        }


        return qamNodeList;
    }
    private boolean occupyChannels(String linkId, List<Integer> channelToOccupy){
        QamTopology qamTopology = qamTopologyInventoryService.getQamTapiTopologyFromTree();
        List<QamNode> qamNodeList = qamTopology.getQamNode();
            int linkIndex = getQamLinkIndex(qamTopology,linkId);
            if(linkIndex==-1)
                return false;

            QamLink qamLink = qamTopology.getQamLink().get(linkIndex);
            List<Integer> occupiedChannel = qamLink.getOccupiedChannel();
            List<Integer> availableChannel = qamLink.getAvailableChannel();

            for(int i=0; i<channelToOccupy.size(); i++){
                    if(occupiedChannel.contains(channelToOccupy.get(i))) {
                        LOG.error("Channel " + channelToOccupy.get(i) + " already occupied.");
                        return false;
                    }
            }
        moveElementsBetweenLists(availableChannel, occupiedChannel, channelToOccupy);
        List<QamLink> qamListLink = qamTopology.getQamLink();

        qamListLink.set(linkIndex, qamLink);
        //qamNodeList = addCepIntoQamNode(qamTopology,  qamLink.getNodeSrc(),qamLink.getPortSrc()); //TODO. Are they necessary ?
        //qamNodeList = addCepIntoQamNode(qamTopology,  qamLink.getNodeDst(),qamLink.getPortDst());
        return  qamTopologyInventoryService.writeQamTopologyIntoTree(new  QamTopologyContBuilder(qamTopology).setQamLink(qamListLink).setQamNode(qamNodeList).build());

    }

    public QamNode getNode(String nodeId, boolean includeCredentials){
        QamTopology qamTopology = qamTopologyInventoryService.getQamTapiTopologyFromTree();
        for(QamNode qamNode: qamTopology.getQamNode()){
            if(qamNode.getUuid().getValue().equals(nodeId) && includeCredentials==false)
                return new QamNodeBuilder(qamNode).setUsername(null).setPassword(null).build();
            if(qamNode.getUuid().getValue().equals(nodeId) && includeCredentials==true)
                return qamNode;
        }
        LOG.warn("Node with ID "+nodeId+" not found");
        return null;
    }

       public QamTopology getQamTopology() {
        QamTopology qamTopology = qamTopologyInventoryService.getQamTapiTopologyFromTree();
        if(qamTopology==null){
            return null;
        }
        List<QamNode> qamNodes = qamTopology.getQamNode();
        List<QamNode> qamNodesNewList = new ArrayList<>();
        for(QamNode qamNode: qamNodes){
            //It removes the credentials when get the topology Info.
            qamNodesNewList.add(new QamNodeBuilder(qamNode).setUsername(null).setPassword(null).build());
        }

        return new QamTopologyContBuilder(qamTopology).setQamNode(qamNodesNewList).build();
    }


    private DeviceInfoReader getDeviceInfoReader(final MountPointService mountPointService, String nodeId, QamNode qamNode) {
        DeviceInfoReader deviceInfoReader;
        QNodeType qNodeType = qamNode.getNodeType();
        LOG.info("QNode Type is "+qNodeType);
        switch (qNodeType) {
            case Dummy:
                return new DummyInfoReader( nodeId, false);

            case Lumentum:
                deviceInfoReader = new LumentumInfoReader(mountPointService, nodeId);
                break;

            case Nll:
                deviceInfoReader = new NllInfoReader(mountPointService, nodeId);
                break;

            case Endpoint:
                deviceInfoReader =   new DummyInfoReader( nodeId, true);
                break;

            case Openroadm:
                deviceInfoReader =  new OpenRoadmInfoReader(mountPointService, nodeId,
                        qamNode.getHost(),
                        qamNode.getPort(),
                        qamNode.getUsername(),
                        qamNode.getPassword());
                break;
/*
            case Sbvt:
                deviceInfoReader = new SbvtInfoReader(nodeId);
                break;
*/

            default:
                LOG.warn("Qam Node "+qNodeType+" is unknown");
                return null;
        }

        int counter = 0;
        boolean getInfoSuccess = deviceInfoReader.refreshInfo();
        //If some error occurs, the info is reading again, or at least trying to
        while (!getInfoSuccess  && counter<MAX_TENTATIVE) {
            try {
                Thread.sleep(TIMEOUT_TRY);
                getInfoSuccess=deviceInfoReader.refreshInfo();
            } catch (InterruptedException e) {
                LOG.warn(e.getMessage());
                counter++;
            }
            LOG.warn("Unable to get internal connectivity from "+qNodeType+" devices. Retrying in "+TIMEOUT_TRY/1000+" seconds.");
        }
        return deviceInfoReader;

    }
}
