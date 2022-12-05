package it.nextworks.qameleon.topology_driver.impl;

import it.nextworks.qameleon.sbi.netconf_driver.openRoadmDriver.OpenRoadmNetconfDriver;
import it.nextworks.qameleon.sbi.netconf_driver.openRoadmDriver.RoadmConnectionInfo;
import it.nextworks.qameleon.topology_driver.DeviceInfoReader;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev171215.ExternalLink;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev171215.org.openroadm.device.container.OrgOpenroadmDevice;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnection;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnectionBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.AdministrativeState;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.OperationalState;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.PortDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OpenRoadmInfoReader implements DeviceInfoReader {
        private String deviceId;
        private OpenRoadmNetconfDriver openroadmDriver;
        private OrgOpenroadmDevice orgOpenroadmDevice;
        private static final Logger LOG = LoggerFactory.getLogger(OpenRoadmInfoReader.class);

    public OpenRoadmInfoReader(MountPointService mountPointService,
                               String deviceId,
                               String deviceHost,
                               String devicePort,
                               String username,
                               String pwd) {
        this.deviceId = deviceId;
        openroadmDriver =new OpenRoadmNetconfDriver(mountPointService, deviceId,
                deviceHost,
                devicePort,
                username,
                pwd);
        openroadmDriver.discardChangesOpenRoadmDevice();
    }

    @Override
    public Node getDeviceInfo() {
        NodeBuilder nodeBuilder = new NodeBuilder()
                .setUuid(new Uuid(deviceId))
                .setOperationalState(OperationalState.ENABLED)
                .setAdministrativeState(AdministrativeState.UNLOCKED);

        if(orgOpenroadmDevice==null){
            LOG.warn("Unable to get information from OpenROADM Device");
            return nodeBuilder.build();
        }

        List<OwnedNodeEdgePoint> onepList = new ArrayList<>();

        if(orgOpenroadmDevice.getExternalLink()==null || orgOpenroadmDevice.getExternalLink().size()==0) {
            LOG.info("No external connections found in openROAM device");
            return nodeBuilder.build();
        }
        LOG.info("External connections found in openROAM device");
        for(ExternalLink externalLink: orgOpenroadmDevice.getExternalLink()){

            String extLinkSrc = externalLink.getSource().getPortName();
            String extLinkDst = externalLink.getDestination().getPortName();

            OwnedNodeEdgePoint ownedNodeEdgePointSrc =
                    new OwnedNodeEdgePointBuilder().setUuid(new Uuid(extLinkSrc))
                            .setOperationalState(OperationalState.ENABLED)
                            .setAdministrativeState(AdministrativeState.UNLOCKED)
                    .setLinkPortDirection(PortDirection.OUTPUT).build();

            onepList.add(ownedNodeEdgePointSrc);

            OwnedNodeEdgePoint ownedNodeEdgePointDst =
                    new OwnedNodeEdgePointBuilder().setUuid(new Uuid(extLinkDst))
                            .setOperationalState(OperationalState.ENABLED)
                            .setAdministrativeState(AdministrativeState.UNLOCKED)
                            .setLinkPortDirection(PortDirection.INPUT).build();
            onepList.add(ownedNodeEdgePointDst);
        }

        return nodeBuilder.setOwnedNodeEdgePoint(onepList)
                .setOperationalState(OperationalState.ENABLED).build();
    }

    @Override
    public OwnedNodeEdgePoint getPortInfo(String portId) {
        return new OwnedNodeEdgePointBuilder().build();
    }

    @Override
        public List<InternalConnection> getInternalConnection() {

            LOG.info("Getting internal connectivity of OpenROADM device which id is "+deviceId);
            List<RoadmConnectionInfo> roadmConnectionInfoList = openroadmDriver.getRoadmConnectionInfoList();
            List<InternalConnection> internalConnectionList = new ArrayList<>();
            if(roadmConnectionInfoList==null || roadmConnectionInfoList.size()==0) {
                LOG.info("No OpenRoadm Connection found into OpenRoadm device");
                return new ArrayList<>();
            }

            for(RoadmConnectionInfo roadmConnectionInfo: roadmConnectionInfoList){
                String sourceNmcSupportingPort = roadmConnectionInfo.getSourceNmcSupportingPort();
                String destinationNmcSupportingPort = roadmConnectionInfo.getDestinationNmcSupportingPort();
                internalConnectionList.add(new InternalConnectionBuilder().setSrcPort(sourceNmcSupportingPort).setDstPort(destinationNmcSupportingPort).build());
            }
            return internalConnectionList;
        }

    @Override
    public boolean refreshInfo() {
        orgOpenroadmDevice = openroadmDriver.getOpenRoadmDevice();
        return orgOpenroadmDevice!=null;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public List<Integer> getOutputChannelOccupied() {
        return new ArrayList<>();
    }

    @Override
    public List<Integer> getInputChannelOccupied() {
        return new ArrayList<>();
    }
}
