package it.nextworks.qameleon.topology_driver.impl;

import it.nextworks.qameleon.topology_driver.DeviceInfoReader;
import it.nextworks.qameleon.sbi.netconf_driver.FinisarNetconfDriver;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;

import java.util.List;

public class FinisarInfoReader implements DeviceInfoReader {
    private final String deviceId;
    private FinisarNetconfDriver finisarNetconfDriver;
    private DummyInfoReader dummyInfoReader;

    public FinisarInfoReader(MountPointService mountPointService, String deviceId) {
        this.deviceId = deviceId;
        finisarNetconfDriver =new FinisarNetconfDriver(mountPointService,deviceId);
        dummyInfoReader= new DummyInfoReader(deviceId, 1,20,false);
    }
    @Override
    public Node getDeviceInfo() {
        return dummyInfoReader.getDeviceInfo();
    }

    @Override
    public OwnedNodeEdgePoint getPortInfo(String portId) {
        return dummyInfoReader.getPortInfo(portId);
    }

    @Override
    public List<InternalConnection> getInternalConnection() {
        return dummyInfoReader.getInternalConnection();
    }

    @Override
    public boolean refreshInfo() {
        return true;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }
}
