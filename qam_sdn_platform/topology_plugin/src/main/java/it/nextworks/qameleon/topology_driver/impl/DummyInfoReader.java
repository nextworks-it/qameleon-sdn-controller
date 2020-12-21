package it.nextworks.qameleon.topology_driver.impl;

import it.nextworks.qameleon.sbi.netconf_driver.DummyNetconfDriver;
import it.nextworks.qameleon.sbi.netconf_driver.LumentumUtil;
import it.nextworks.qameleon.topology_driver.DeviceInfoReader;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.AdministrativeState;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.OperationalState;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.PortDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.edge.point.MappedServiceInterfacePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.edge.point.MappedServiceInterfacePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.NodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class DummyInfoReader implements DeviceInfoReader {
    private final String fakeDeviceId;
    private DummyNetconfDriver dummyNetconfDriver;

    public DummyInfoReader(String nodeId, boolean addSip){
        this.fakeDeviceId = nodeId;
        dummyNetconfDriver =  new DummyNetconfDriver(nodeId,3,3,addSip);
    }
    public DummyInfoReader(String nodeId, int inPortsCount, int outPortCount, boolean addSip){
        this.fakeDeviceId = nodeId;
        dummyNetconfDriver =  new DummyNetconfDriver(nodeId,inPortsCount,outPortCount,addSip);
    }

    @Override
    public Node getDeviceInfo() {
        return dummyNetconfDriver.getDeviceInfo();
    }

    @Override
    public OwnedNodeEdgePoint getPortInfo(String portId) {
        for(OwnedNodeEdgePoint ownedNodeEdgePoint: dummyNetconfDriver.getDeviceInfo().getOwnedNodeEdgePoint()){
            if(ownedNodeEdgePoint.getUuid().getValue().equals(portId)){
                return ownedNodeEdgePoint;
            }
        }
        return null;
    }

    @Override
    public List<InternalConnection> getInternalConnection() {
        return dummyNetconfDriver.getInternalConnectionList();
    }

    @Override
    public boolean refreshInfo() {
        return true;
    }

    @Override
    public String getDeviceId() {
        return fakeDeviceId;
    }


}
