package it.nextworks.qameleon.topology_driver.impl;

import it.nextworks.qameleon.topology_driver.DeviceInfoReader;
import it.nextworks.qameleon.sbi.netconf_driver.NllNetconfDriver;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;

import java.util.ArrayList;
import java.util.List;

public class NllInfoReader implements DeviceInfoReader {
    private final String deviceId;
    private NllNetconfDriver nllNetconfDriver;
    private DummyInfoReader dummyInfoReader;

    public NllInfoReader(MountPointService mountPointService, String deviceId) {
        this.deviceId = deviceId;
        nllNetconfDriver =new NllNetconfDriver(mountPointService,deviceId);
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

    @Override
    public List<Integer> getOutputChannelOccupied() {
        return new ArrayList<>();
    }

    @Override
    public List<Integer> getInputChannelOccupied() {
        return new ArrayList<>();
    }
}
