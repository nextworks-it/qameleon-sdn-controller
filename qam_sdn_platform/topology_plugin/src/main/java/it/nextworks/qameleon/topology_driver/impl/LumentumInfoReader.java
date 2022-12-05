package it.nextworks.qameleon.topology_driver.impl;

import it.nextworks.common.TwoWaysChannelFreqTranslator;
import it.nextworks.qameleon.sbi.netconf_driver.lumentumNetconfDriver.LumentumNetconfDriver;
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
import java.util.*;

import static it.nextworks.qameleon.sbi.netconf_driver.lumentumNetconfDriver.LumentumUtil.dnToHasMap;


public class LumentumInfoReader implements DeviceInfoReader {
    private final String deviceId;
    private HashMap<String, PhysicalPort> lumentumPorts;
    private HashMap<String, Connection> lumentumConnections;
    private LumentumNetconfDriver lumentumNetconfDriver;
    private Set<Integer> outputChannelOccupied;
    private Set<Integer> inputChannelOccupied;

    private static final Logger LOG = LoggerFactory.getLogger(LumentumInfoReader.class);
    private final int ERROR = -1;

    public LumentumInfoReader(MountPointService mountPointService, String deviceId) {
        this.deviceId = deviceId;
        lumentumPorts = new HashMap<>();
        lumentumConnections = new HashMap<>();
        lumentumNetconfDriver =new LumentumNetconfDriver(mountPointService, deviceId);
        outputChannelOccupied = new HashSet<>();
        inputChannelOccupied = new HashSet<>();
    }

    @Override
    public List<Integer> getOutputChannelOccupied() {
        return new ArrayList<>(outputChannelOccupied);
    }

    @Override
    public List<Integer> getInputChannelOccupied() {
        return new ArrayList<>(inputChannelOccupied);
    }


    private void occupyFirstNChannels(int channelCount){
        if(channelCount<=0)
        {
            LOG.error("Cannot set negative number as channel count");
            return;
        }
        for(int i=0; i<channelCount; i++){
            outputChannelOccupied.add(i+1);
            inputChannelOccupied.add(i+1);
        }
    }

    @Override
    public Node getDeviceInfo() {
        if(!getLumentumPortsInfo()) {
            LOG.warn("Unable to get Lumentum device info.");
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

        PortDirection portDirection=PortDirection.UNIDENTIFIEDORUNKNOWN;
        if(portId.contains("52") || portId.contains("42"))
            portDirection = PortDirection.OUTPUT;

        else if(portId.contains("51") || portId.contains("41"))
            portDirection = PortDirection.INPUT;

        else if(portId.contains("3001"))
            portDirection = PortDirection.BIDIRECTIONAL;

        else
            LOG.warn("Not able to identify direction of port with identifier "+portId);

       //Due To a Api generation problem by ODL, a piece of  YANG model of Lumentum port is commented.
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
        final int MAXIMUM_WIDTH_FOR_TRANSLATION = 200;
        TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator();

        double widthChannel = endingFreq.doubleValue()-startingFreq.doubleValue();
        //The finisar channel width are around 50 Ghz. Using 60 ghz to give some tollerance
        if(widthChannel>=MAXIMUM_WIDTH_FOR_TRANSLATION){
            LOG.error("The width channel is greater than "+MAXIMUM_WIDTH_FOR_TRANSLATION+" Ghz. Cannot translate into a channel");
            return ERROR;
        }

        double centralFrequency = (startingFreq.doubleValue() + endingFreq.doubleValue())/2;
        int channel =  twoWaysChannelFreqTranslator.frequencyToChannel(centralFrequency);
        LOG.info("Central frequency "+centralFrequency);
        LOG.info("Channel "+channel);
        return channel;
    }

    private boolean isMuxInput(Integer portReference){
        String portReferenceStr = String.valueOf(portReference);
        return portReferenceStr.contains("41");
    }

    private boolean isDemuxOutput(Integer portReference){
        String portReferenceStr = String.valueOf(portReference);
        return portReferenceStr.contains("52");
    }

    @Override
    public List<InternalConnection> getInternalConnection() {
        List<InternalConnection> internalConnectionList = new ArrayList<>();
        if(!getLumentumConnectionInfo()){
            LOG.error("Unable to retrieve Lumentum connection information.");
            return new ArrayList<>();
        }
        for(String connectionId: lumentumConnections.keySet()){
            Connection connection = lumentumConnections.get(connectionId);
            Integer inputPort =dnToHasMap(connection.getConfig().getInputPortReference()).get("port");
            Integer outputPort =dnToHasMap(connection.getConfig().getOutputPortReference()).get("port");
            DwdmFrequencyRangeGhz startFreq = connection.getConfig().getStartFreq();
            DwdmFrequencyRangeGhz endFreq =connection.getConfig().getEndFreq();

            Integer channel = frequencyToChannelConverter(startFreq.getValue(), endFreq.getValue());
            if(isMuxInput(inputPort)){
                inputChannelOccupied.add(channel);
                LOG.info("channel occupied in MUX input port is "+channel);
            }

            if(isDemuxOutput(outputPort)){
                outputChannelOccupied.add(channel);
                LOG.info("channel occupied in DEMUX output port is "+channel);
            }

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
        //PhysicalPorts physicalPorts2 = lumentumNetconfDriver.getPhysicalPorts();
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

    private boolean getLumentumConnectionInfo() {
        Connections connections = null;
        final int WAIT_TIME_MS = 3000;
        final int MAX_ATTEMPTS = 5;
        int counter = 1;
        while(counter<=MAX_ATTEMPTS) {
            LOG.info("Attempt #"+counter);
            try {
                connections = lumentumNetconfDriver.getLumentumConnections();
                break;
            } catch (IllegalArgumentException e) {
                //When a cross connection is setup on Lumentum ROADM and then it is immediately read,
                // a not allowed value (according to the YANG model) of the optical power (-200db around) is read.
                // Then, an IllegalArgumentException is thrown. So, in case of the exception
                //a waiting time and a maximum number of attempts fixes the aforementioned problem.
                LOG.error(e.getMessage());
                LOG.info("Waiting "+WAIT_TIME_MS/1000+" seconds before reading again the Lumentum ROADM connections information");
                try {
                    Thread.sleep(WAIT_TIME_MS);
                } catch (InterruptedException interruptedException) {
                    LOG.error(e.getMessage());
                }
            }
            counter ++;
        }

        if(connections==null)
            return false;

        List<Connection> connectionList = connections.getConnection();
        if(connectionList!=null && connectionList.size()>0){
            for(Connection connection: connectionList){
                Integer connectionId = dnToHasMap(connection.getDn()).get("connection");
                lumentumConnections.put(String.valueOf(connectionId), connection);
                LOG.debug("DN: "+connection.getDn().getValue());
                LOG.debug("Input port reference: "+connection.getConfig().getInputPortReference().getValue());
                LOG.debug("Output port reference: "+connection.getConfig().getOutputPortReference().getValue());
                LOG.debug("Start freq: "+connection.getConfig().getStartFreq().getValue().toString());
                LOG.debug("End freq: "+connection.getConfig().getEndFreq().getValue().toString());
            }
        }
        else{
            LOG.warn("No connections found into lumentum device");
        }
        return true;
    }
}
