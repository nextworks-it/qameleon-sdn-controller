package it.nextworks.qameleon.topology_driver.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.SbvtRestClient;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.SbvtConfigurationRead;
import it.nextworks.qameleon.topology_driver.DeviceInfoReader;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnection;
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

public class SbvtInfoReader implements DeviceInfoReader {
    private SbvtRestClient sbvtRestClient;

    private final String deviceId;

    private static final Logger LOG = LoggerFactory.getLogger(SbvtRestClient.class);

    private SbvtConfigurationRead sbvtCurrentConfig;
    public SbvtInfoReader(String deviceId){
        this.deviceId = deviceId;
        sbvtRestClient = new SbvtRestClient();
    }

    @Override
    public Node getDeviceInfo() {
        refreshInfo();

        List<OwnedNodeEdgePoint> onepList = new ArrayList<>();
        OwnedNodeEdgePoint onepOut1 = new OwnedNodeEdgePointBuilder()
                .setUuid(new Uuid("OUT01"))
                .setOperationalState(OperationalState.ENABLED)
                .setAdministrativeState(AdministrativeState.UNLOCKED)
                .setLinkPortDirection(PortDirection.OUTPUT)
                .build();

        OwnedNodeEdgePoint onepOut2 = new OwnedNodeEdgePointBuilder()
                .setUuid(new Uuid("OUT02"))
                .setOperationalState(OperationalState.ENABLED)
                .setAdministrativeState(AdministrativeState.UNLOCKED)
                .setLinkPortDirection(PortDirection.OUTPUT)
                .build();

        onepList.add(onepOut1);
        onepList.add(onepOut2);

        return new NodeBuilder().setUuid(new Uuid(deviceId))
                .setOwnedNodeEdgePoint(onepList)
                .setAdministrativeState(AdministrativeState.UNLOCKED)
                .setOperationalState(OperationalState.ENABLED).build();
    }

    @Override
    public OwnedNodeEdgePoint getPortInfo(String portId) {
        return new OwnedNodeEdgePointBuilder().build();
    }

    @Override
    public List<InternalConnection> getInternalConnection() {
        return  new ArrayList<>();
    }

    @Override
    public boolean refreshInfo() {
        this.sbvtCurrentConfig = sbvtRestClient.getSbvtConfiguration();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            String json = ow.writeValueAsString(this.sbvtCurrentConfig);
            LOG.info("Raw Information about SBVT device");
            LOG.info(json);
            return true;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getDeviceId() {
        return this.deviceId;
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
