package it.nextworks.qameleon.topology_driver.impl;

import it.nextworks.qameleon.sbi.netconf_driver.LumentumNetconfDriver;
import it.nextworks.qameleon.topology_driver.DeviceInfoReader;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.Connections;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.connections.Connection;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.PhysicalPorts;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.physical.ports.PhysicalPort;

import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.DwdmFrequencyRangeGhz;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static it.nextworks.qameleon.sbi.netconf_driver.LumentumUtil.dnToHasMap;


public class LumentumInfoReader implements DeviceInfoReader {
    private final String deviceId;
    HashMap<String, PhysicalPort> lumentumPorts;
    HashMap<String, Connection> lumentumConnections;
    LumentumNetconfDriver lumentumNetconfDriver;

    private static final Logger LOG = LoggerFactory.getLogger(LumentumInfoReader.class);

    public LumentumInfoReader(MountPointService mountPointService, String deviceId) {
        this.deviceId = deviceId;
        lumentumPorts = new HashMap<>();
        lumentumConnections = new HashMap<>();
        lumentumNetconfDriver =new LumentumNetconfDriver(mountPointService, deviceId);
    }



    @Override
    public Node getDeviceInfo() {
        if(!getLumentumPortsInfo()) {
            LOG.warn("Unable to get lumentum device info.");
            return new NodeBuilder().setUuid(new Uuid(deviceId))
                    .setOwnedNodeEdgePoint(new ArrayList<>())
                    .setOperationalState(OperationalState.DISABLED).build();
        }
        List<OwnedNodeEdgePoint> onepList = new ArrayList<>();
        for(String portId: lumentumPorts.keySet()){
            onepList.add( getPortInfo(portId));
        }
        return new NodeBuilder().setUuid(new Uuid(deviceId))
                .setOwnedNodeEdgePoint(onepList)
                .setAdministrativeState(AdministrativeState.UNLOCKED)
                .setOperationalState(OperationalState.ENABLED).build();
    }

    @Override
    public OwnedNodeEdgePoint getPortInfo(String portId) {
        PhysicalPort physicalPort = lumentumPorts.get(portId);
        if(physicalPort==null)
            return null;

        PortDirection portDirection=PortDirection.INPUT;
        if(portId.contains("52"))
            portDirection = PortDirection.OUTPUT;

       //TODO due To a Api generation problem by ODL, a piece of  YANG model of lumentum port is commented.
        // This make the generation working but maybe unable to retrieve the port status.
        //So, for now the port operation and admin status is considered enabled and unlocked.
        return new OwnedNodeEdgePointBuilder()
                .setUuid(new Uuid(portId))
                .setOperationalState(OperationalState.ENABLED)
                .setAdministrativeState(AdministrativeState.UNLOCKED)
                .setLinkPortDirection(portDirection)
                .build();

    }

    private Integer frequencyToChannelConverter(BigDecimal startingFreq, BigDecimal endingFreq){
        return -1;
    }

    @Override
    public List<InternalConnection> getInternalConnection() {
        List<InternalConnection> internalConnectionList = new ArrayList<>();
        if(!getLumentumConnectionInfo()){
            LOG.error("Unable to retrieve lumentum connection information.");
            return new ArrayList<>();
        }
        for(String connectionId: lumentumConnections.keySet()){
            Connection connection = lumentumConnections.get(connectionId);

            Integer inputPort =dnToHasMap(connection.getConfig().getInputPortReference()).get("connection");
            Integer outputPort =dnToHasMap(connection.getConfig().getOutputPortReference()).get("connection");
            DwdmFrequencyRangeGhz startFreq = connection.getConfig().getStartFreq();
            DwdmFrequencyRangeGhz endFreq =connection.getConfig().getEndFreq();
            Integer channel = frequencyToChannelConverter(startFreq.getValue(), endFreq.getValue());

            InternalConnection internalConnection=
                    new InternalConnectionBuilder()
                            .setSrcPort(String.valueOf(inputPort))
                            .setDstPort(String.valueOf(outputPort))
                            .build();

            internalConnectionList.add(internalConnection);
        }
        return internalConnectionList;
    }

    @Override
    public boolean refreshInfo() {
        return (getLumentumPortsInfo() && getLumentumConnectionInfo());
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    private boolean getLumentumPortsInfo(){
        LOG.info("Getting all Lumentum (dummy) port info");
        PhysicalPorts physicalPorts = lumentumNetconfDriver.getPhysicalPortsMock();//TODO mock for now. To use real status eventually
        if(physicalPorts==null) {
            LOG.error("Unable to retrieve information about the physical port on Lumentum device.");
            return false;
        }
        for(PhysicalPort physicalPort: physicalPorts.getPhysicalPort()){
            String portId = String.valueOf(dnToHasMap(physicalPort.getDn()).get("port"));
            lumentumPorts.put(portId, physicalPort);
        }

        return true;
    }

    private boolean getLumentumConnectionInfo(){
        Connections connections = lumentumNetconfDriver.getLumentumConnections();
        if(connections==null)
            return false;

        List<Connection> connectionList = connections.getConnection();
        if(connectionList!=null && connectionList.size()>0){
            for(Connection connection: connectionList){
                Integer connectionId = dnToHasMap(connection.getDn()).get("connection");
                lumentumConnections.put(String.valueOf(connectionId), connection);
            }
        }
        else{
            LOG.info("No connections found into lumentum device");
        }
        return true;
    }
}
