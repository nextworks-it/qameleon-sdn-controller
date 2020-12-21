package it.nextworks.qameleon.qamProvisioningApp.service;

import it.nextworks.qameleon.qamProvisioningApp.sbi.PceRestClient;
import it.nextworks.qameleon.qamProvisioningApp.sbi.TopologyAppServiceConsumer;
import it.nextworks.qameleon.sbi.api.provisioning.LightPathProvisioning;
import it.nextworks.qameleon.sbi.api.provisioning.impl.DummyProvisioning;
import it.nextworks.qameleon.sbi.api.provisioning.impl.FinisarProvisioning;
import it.nextworks.qameleon.sbi.api.provisioning.impl.LumentumProvisioning;
import org.json.JSONArray;
import org.json.JSONObject;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.qameleon.common.rev200914.QNodeType;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLink;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNode;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.ForwardingDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.PortDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.global._class.Name;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.global._class.NameBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.ComputeP2PPathInput;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.Link;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.LinkBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.RoutingConstraintBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.computation.context.Path;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.computation.context.PathBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.link.NodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QamProvisioningService {
    private PceRestClient pceRestClient;
    private TopologyAppServiceConsumer topologyAppServiceConsumer;
    private LightPathInventoryService lightPathInventoryService;
    private final MountPointService mountPointService;

    private final String PCE_HOST = ProvisioningAppConfigService.getInstance().getPceHost();
    private final int PCE_PORT = ProvisioningAppConfigService.getInstance().getPcePort();
    private final String LOOKUP_TABLE_PATH = ProvisioningAppConfigService.getInstance().getAbsoluteLookupTablePath();

    private static final Logger LOG = LoggerFactory.getLogger(QamProvisioningService.class);
    public QamProvisioningService(DataBroker dataBroker, final MountPointService mountPointService){
        this.mountPointService = mountPointService;
        pceRestClient = new PceRestClient(PCE_HOST,PCE_PORT);
        topologyAppServiceConsumer = TopologyAppServiceConsumer.getInstance();
        lightPathInventoryService = new LightPathInventoryService(dataBroker);
    }


    private Path jsonToPathConverter(JSONObject eroJson){
        List<Name> values = new ArrayList();
        JSONArray jsonElements = eroJson.getJSONArray("name");
        for(int i=0; i<jsonElements.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonElements.get(i);
            String value = jsonObject.getString("value");
            String valueName = jsonObject.getString("value-name");
            values.add(new NameBuilder().setValue(value).setValueName(valueName).build());
        }

        String uuid = eroJson.getString("uuid");
        List<Link> linkListObj = new ArrayList<>();
        JSONArray linkList = eroJson.getJSONArray("link");
        for(int i=0; i<linkList.length(); i++) {
            JSONObject jsonObject = (JSONObject) linkList.get(i);
            String linkUuid = jsonObject.getString("link-uuid");
            String topologyUuid = jsonObject.getString("topology-uuid");
            linkListObj.add(new LinkBuilder().setLinkUuid(new Uuid(linkUuid)).setTopologyUuid(new Uuid(topologyUuid)).build());
        }
        return new PathBuilder().setUuid(new Uuid(uuid)).setName(values).setDirection(ForwardingDirection.UNIDIRECTIONAL)
                .setLink(linkListObj).setRoutingConstraint(new RoutingConstraintBuilder().build()).build();

    }



    private HashMap<String, DeviceProvisioning> getDeviceProvisioningMap(Path lightpath){
        HashMap<String, DeviceProvisioning> deviceProvisioningHashMap = new HashMap<>();
        List<String> linkListUuid = new ArrayList<>();
        int channelNumber = getChannelNumberFromLightpath(lightpath);

        for(Link link: lightpath.getLink()){
            QamLink qamLink= topologyAppServiceConsumer.getQamLink(link.getLinkUuid().getValue());
            linkListUuid.add(link.getLinkUuid().getValue());

            for(NodeEdgePoint nodeEdgePoint: qamLink.getNodeEdgePoint()){
                String nodeId = nodeEdgePoint.getNodeUuid().getValue();
                String portId = nodeEdgePoint.getNodeEdgePointUuid().getValue();

                DeviceProvisioning deviceProvisioning = deviceProvisioningHashMap.get(nodeId);
                if(deviceProvisioning==null){
                    deviceProvisioning = new DeviceProvisioning(nodeId, channelNumber,getNodeType(nodeId) );
                }
                PortDirection portDirection = getPortDirection(nodeId, portId);

                if(portDirection==PortDirection.INPUT){
                    deviceProvisioning.setSrcPort(portId);
                }
                else if(portDirection==PortDirection.OUTPUT){
                    deviceProvisioning.setDstPort(portId);
                }
                deviceProvisioningHashMap.put(nodeId,deviceProvisioning);
            }
        }
        return deviceProvisioningHashMap;
    }

    public String createLightPathRequest(ComputeP2PPathInput input) throws IOException, ExecutionException, InterruptedException {
        LOG.info("Received request to create lightpath.");
        JSONObject eroObj = pceRestClient.computeLightPathRequest(input);


        eroObj =  (JSONObject)eroObj.getJSONObject("path");
        Path path = jsonToPathConverter(eroObj);
        int channelNumber = getChannelNumberFromLightpath(path);

        if(channelNumber==-1){
            LOG.error("Channel number not found in the PCE response.");
            throw new BadRequestException("Channel number not found in the PCE response");
        }

        List<String> linkListUuid = new ArrayList<>();


        HashMap<String, DeviceProvisioning> deviceProvisioningHashMap = getDeviceProvisioningMap(path);
        for(Link link: path.getLink()) {
            linkListUuid.add(link.getLinkUuid().getValue());
        }
        for(String key: deviceProvisioningHashMap.keySet()){
            DeviceProvisioning deviceProvisioning = deviceProvisioningHashMap.get(key);
            QNodeType qNodeType = deviceProvisioning.getQnodeType();
            LightPathProvisioning lightPathProvisioning = getLightPathProvisioningDriver(deviceProvisioning.getNodeId(), qNodeType);
            LOG.info("Request to create cross-connection within node "+deviceProvisioning.getNodeId());

            boolean lightPathSetupSuccess = lightPathProvisioning.setupLightPath(channelNumber,deviceProvisioning.getSrcPort(),deviceProvisioning.getDstPort());
            if(!lightPathSetupSuccess)
                throw new ExecutionException(new Throwable("Error during the setup of cross-connection of node "+deviceProvisioning.getNodeId()+" between port "+deviceProvisioning.getSrcPort()+" and port "+deviceProvisioning.getDstPort()));
        }

        LOG.info("Provisioning operation successfully made. Going to update link information on topology app.");
        boolean updateLightPathSuccess = topologyAppServiceConsumer.updateLightPathInfo(linkListUuid,channelNumber);
        if(!updateLightPathSuccess) {
            LOG.error("Unable to update light path.");
            throw new BadRequestException("Unable to update light path.");
        }

        storeLightPathIntoTree(path);
        return path.getUuid().getValue();
    }


    private LightPathProvisioning getLightPathProvisioningDriver(String nodeId, QNodeType qNodeType){
        switch(qNodeType){
            case Lumentum:
                return new LumentumProvisioning(mountPointService,nodeId, LOOKUP_TABLE_PATH);
            case Endpoint:
            case Dummy:
                return new DummyProvisioning(mountPointService, nodeId, LOOKUP_TABLE_PATH);

            case Finisar:
                return new FinisarProvisioning();

        }
        throw new BadRequestException("QNode type not expected.");
    }

    private PortDirection getPortDirection(String nodeId, String portId) {
       QamNode qamNode= topologyAppServiceConsumer.getQamNode(nodeId);
       for(OwnedNodeEdgePoint ownedNodeEdgePoint: qamNode.getOwnedNodeEdgePoint()){
           if(ownedNodeEdgePoint.getUuid().getValue().equals(portId))
               return ownedNodeEdgePoint.getLinkPortDirection();
       }
       return PortDirection.UNIDENTIFIEDORUNKNOWN;
    }

    private QNodeType getNodeType(String nodeId) {
        QamNode qamNode= topologyAppServiceConsumer.getQamNode(nodeId);
        return qamNode.getNodeType();
    }

    private boolean storeLightPathIntoTree(Path path){
        //TODO add some checks
        return lightPathInventoryService.writeLightPathIntoTree(path);
    }

    private boolean removeLightPathFromTree(String uuid){
        //TODO add some checks
        LOG.info("Removed lightpath with uuid :  "+uuid);
        return  lightPathInventoryService.deleteLightPathFromTree(uuid);
    }

    public void removeLightPath(String lightPathUuid) throws ExecutionException, InterruptedException, IOException {
        if(!pceRestClient.removeLightPathRequest(lightPathUuid)){
            LOG.warn("Unable to find on PCE light path with uuid "+lightPathUuid);
        }

        Path lightPath = lightPathInventoryService.getLightPathFromTree(lightPathUuid);
        if(lightPath==null){
            LOG.error("Unable to find on data tree light path with uuid "+lightPathUuid);
            throw new BadRequestException("Unable to find on data tree light path with uuid "+lightPathUuid);
        }

        List<Link> linkList = lightPath.getLink();
        if(linkList.size()==0){
            LOG.warn("Unable to find links into lightpath with Uuid  "+lightPathUuid);
            throw new BadRequestException("Unable to find link associated with light path with Uuid  "+lightPathUuid);
        }

        List<String> listLinkUuid = new ArrayList<>();

        int channelNumber = getChannelNumberFromLightpath(lightPath);

        for(Link link: linkList){
            String linkUuid = link.getLinkUuid().getValue();
            listLinkUuid.add(linkUuid);
            QamLink qamLink = topologyAppServiceConsumer.getQamLink(linkUuid);
            LOG.info("Going to remove provisioning into link with Uuid "+qamLink.getUuid().getValue()+" for lightpath with uuid  "+lightPathUuid);
            LOG.info("Changing configuration to node "+qamLink.getNodeSrc()+ " at port "+qamLink.getPortSrc());
            LOG.info("Changing configuration to node "+qamLink.getNodeDst()+ " at port "+qamLink.getNodeDst());

        }
        LOG.info("Going to update topology app.");

        HashMap<String, DeviceProvisioning> deviceProvisioningHashMap = getDeviceProvisioningMap(lightPath);
        for(String key: deviceProvisioningHashMap.keySet()){
            DeviceProvisioning deviceProvisioning = deviceProvisioningHashMap.get(key);
            int channel= Integer.valueOf(deviceProvisioning.getChannel());
            QNodeType qNodeType = deviceProvisioning.getQnodeType();
            LightPathProvisioning lightPathProvisioning = getLightPathProvisioningDriver(deviceProvisioning.getNodeId(), qNodeType);
            LOG.info("Request to remove cross-connection within node "+deviceProvisioning.getNodeId());

            boolean lightPathRemovalSuccess = lightPathProvisioning.removeLightPath(channel,deviceProvisioning.getSrcPort(),deviceProvisioning.getDstPort());
            if(!lightPathRemovalSuccess)
                throw new ExecutionException(new Throwable("Error during the removal of cross-connection of node "+deviceProvisioning.getNodeId()+" between port "+deviceProvisioning.getSrcPort()+" and port "+deviceProvisioning.getDstPort()));
        }

        boolean removeSuccess = removeLightPathFromTree(lightPathUuid);
        if(!removeSuccess){
            throw new BadRequestException("Unable to find lightpath with channel into data store "+lightPathUuid);
        }



        boolean topologyUpdateSuccess = topologyAppServiceConsumer.removeLightPath(listLinkUuid, channelNumber);
        if(!topologyUpdateSuccess)
            throw new BadRequestException("Error during updating of topology channel availability." );
    }

    private int getChannelNumberFromLightpath(Path lightpath){
        for(Name name: lightpath.getName()){
            if(name.getValueName().equals("channel")){
                return Integer.valueOf(name.getValue());
            }
        }
        LOG.error("Unable to find channel number into the lightpath with uuid "+lightpath.getUuid());
        throw new BadRequestException("Unable to find channel number into the lightpath with UUID " +lightpath.getUuid());
    }
    private class DeviceProvisioning{

        private String nodeId;
        private String srcPort;
        private String dstPort;
        private int channel;
        private QNodeType qNodeType;

        public DeviceProvisioning(String nodeId, int channel, QNodeType qNodeType){
            this.nodeId= nodeId;
            this.channel = channel;
            this.qNodeType = qNodeType;
        }
        public String getNodeId() {
            return nodeId;
        }

        public String getSrcPort() {
            return srcPort;
        }

        public void setSrcPort(String srcPort) {
            this.srcPort = srcPort;
        }

        public String getDstPort() {
            return dstPort;
        }

        public void setDstPort(String dstPort) {
            this.dstPort = dstPort;
        }

        public int getChannel() {
            return channel;
        }

        public QNodeType getQnodeType(){return qNodeType; }

    }
}
