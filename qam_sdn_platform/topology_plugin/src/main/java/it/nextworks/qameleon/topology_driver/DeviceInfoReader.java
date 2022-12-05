package it.nextworks.qameleon.topology_driver;

import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Node;

import java.util.List;

public interface DeviceInfoReader {
    //It converts the specific device info, into Tapi node TAPI topology info where possible
    public Node getDeviceInfo();
    //It converts the specific port info, into Tapi Node edge point TAPI topology info where possible
    public OwnedNodeEdgePoint getPortInfo(String portId);
    //It converts the specific running internal connection, into a list of connection (src and dst)
    public List<InternalConnection> getInternalConnection();

    public boolean refreshInfo();

    public String getDeviceId();

    public List<Integer> getOutputChannelOccupied();

    public List<Integer> getInputChannelOccupied();
}
