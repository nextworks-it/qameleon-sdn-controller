package it.nextworks.qameleon.sbi.netconf_driver;

import com.google.common.util.concurrent.FluentFuture;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.CommitInfo;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.connections.InternalConnection;
import org.opendaylight.yang.gen.v1.sim.dev.rev201215.CrossConnectionsTop;
import org.opendaylight.yang.gen.v1.sim.dev.rev201215.CrossConnectionsTopBuilder;
import org.opendaylight.yang.gen.v1.sim.dev.rev201215.cross.connections.top.CrossConnections;
import org.opendaylight.yang.gen.v1.sim.dev.rev201215.cross.connections.top.CrossConnectionsBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.AdministrativeState;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.OperationalState;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.PortDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.edge.point.MappedServiceInterfacePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.edge.point.MappedServiceInterfacePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.NodeBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class DummyNetconfDriver extends NetconfDriver{
    private Node dummyNetconfDevice;
    private List<InternalConnection> internalConnectionList;
    private static final Logger LOG = LoggerFactory.getLogger(DummyNetconfDriver.class);

    public DummyNetconfDriver(MountPointService mountPointService, String nodeId){
        super(mountPointService, nodeId);
        List<OwnedNodeEdgePoint> onepList= new ArrayList<>();
        internalConnectionList = new ArrayList<>();
        for(int i=0; i<3; i++){
            OwnedNodeEdgePoint onepInput = new OwnedNodeEdgePointBuilder().setUuid(new Uuid("IN0"+String.valueOf(i))).setLinkPortDirection(PortDirection.INPUT).build();
            OwnedNodeEdgePoint onepOutput =new OwnedNodeEdgePointBuilder().setUuid(new Uuid("OUT0"+String.valueOf(i))).setLinkPortDirection(PortDirection.INPUT).build();
            onepList.add(onepInput);
            onepList.add(onepOutput);
        }
        dummyNetconfDevice = new NodeBuilder().setUuid(new Uuid(nodeId))
                .setOwnedNodeEdgePoint(onepList)
                .setOperationalState(OperationalState.ENABLED)
                .setAdministrativeState(AdministrativeState.UNLOCKED)
                .build();
    }

    public DummyNetconfDriver(String nodeId, int inputPortCount, int outputPortCount, boolean addServiceInterfacePoint){
        super(null,nodeId);
        List<OwnedNodeEdgePoint> onepList= new ArrayList<>();
        internalConnectionList = new ArrayList<>();
        onepList.addAll(addNodeEdgePointSameDirection(nodeId, inputPortCount, PortDirection.INPUT, "IN", addServiceInterfacePoint));
        onepList.addAll(addNodeEdgePointSameDirection(nodeId, outputPortCount, PortDirection.OUTPUT, "OUT", addServiceInterfacePoint));
            dummyNetconfDevice = new NodeBuilder().setUuid(new Uuid(nodeId))
                .setOwnedNodeEdgePoint(onepList)
                .setOperationalState(OperationalState.ENABLED)
                .setAdministrativeState(AdministrativeState.UNLOCKED)
                .build();
    }

    public Node getDeviceInfo(){
        return dummyNetconfDevice;
    }

    public List<InternalConnection> getInternalConnectionList() {
        return internalConnectionList;
    }

    public CrossConnectionsTop getCrossConnections() {
        Optional<CrossConnectionsTop> crossConnectionsTop = (Optional<CrossConnectionsTop>)readFromDataStore(CrossConnectionsTop.class, LogicalDatastoreType.OPERATIONAL);
        if(!crossConnectionsTop.isPresent() || crossConnectionsTop.get()==null) {
            LOG.warn("No cross-connection found.");
            return null;
        }
        return crossConnectionsTop.get();
    }


    public boolean createCrossConnection(int channel, Double wavelengthNm, String srcPort, String dstPort) {
        LOG.info("Creating cross-connection on channel "+channel+" source port "+srcPort+" and destination port "+dstPort);

        DataBroker dataBrokerDevice = getDataBroker();
        if(dataBrokerDevice==null){
            LOG.error("Data broker not available");
            return false;
        }
        CrossConnectionsTopBuilder crossConnectionsTopBuilder = new CrossConnectionsTopBuilder();
        CrossConnectionsTop crossConnectionsTop = getCrossConnections();

        if(crossConnectionsTop==null)
            crossConnectionsTopBuilder.setDeviceId(getNodeId());

        else
            crossConnectionsTopBuilder.setDeviceId(getNodeId()).setCrossConnections(crossConnectionsTop.getCrossConnections());


        List<CrossConnections> crossConnectionList = crossConnectionsTopBuilder.getCrossConnections();

        if(crossConnectionList==null) {
            crossConnectionList = new ArrayList<>();
        }


        BigDecimal bigDecimal = new BigDecimal(wavelengthNm);
        bigDecimal = bigDecimal.round(new MathContext(3));

        LOG.info("bigDecimal {}",bigDecimal);
        crossConnectionList.add(new CrossConnectionsBuilder()
                .setOpticalChannel(channel)
                .setCentralWavelength(bigDecimal)
                .setSrcPort(srcPort)
                .setDstPort(dstPort)
                .build());

        crossConnectionsTop = crossConnectionsTopBuilder.setCrossConnections(crossConnectionList).build();
        return writeConfigRemoteDs(dataBrokerDevice, crossConnectionsTop);
    }



    private boolean writeConfigRemoteDs(DataBroker dataBrokerDevice, CrossConnectionsTop crossConnectionsTop){

        InstanceIdentifier<CrossConnectionsTop> iid = InstanceIdentifier.create(CrossConnectionsTop.class);

        LOG.info("Going to write into remote config datastore");
        final WriteTransaction writeTransaction = dataBrokerDevice.newWriteOnlyTransaction();
        writeTransaction.merge(LogicalDatastoreType.CONFIGURATION, iid, crossConnectionsTop);

        // commit:
        FluentFuture<? extends CommitInfo> result = writeTransaction.commit();

        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            RpcResultBuilder<CrossConnections> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Write transaction error");
            return false;
        }
        return true;
    }

    public boolean removeCrossConnection(int channel, String srcPort, String dstPort) {
        LOG.info("Going to remove cross-connection on channel "+channel+" source port "+srcPort+" and destination port "+dstPort);

        DataBroker dataBrokerDevice = getDataBroker();
        if(dataBrokerDevice==null){
            LOG.error("Data broker not available.");
            return false;
        }
        CrossConnectionsTopBuilder crossConnectionsTopBuilder = new CrossConnectionsTopBuilder();
        CrossConnectionsTop crossConnectionsTop = getCrossConnections();

        if(crossConnectionsTop==null) {
            LOG.error("No cross-connection found into node "+getNodeId()+". Nothing to remove.");
            return false;
        }
        int elementIndex = -1;
        List<CrossConnections> crossConnections = crossConnectionsTop.getCrossConnections();
        LOG.info("Current number of cross-connection into node "+getNodeId()+" is "+crossConnections.size());
        for(int i =0; i<crossConnections.size(); i++){
            CrossConnections crossConnection =crossConnections.get(i);
            if((crossConnection.getSrcPort()==null || crossConnection.getDstPort()==null) && crossConnection.getOpticalChannel().intValue()==channel)
            {
                LOG.info("Removing cross-connection from EP");
                elementIndex = i;
                break;
            }
            if(crossConnection.getOpticalChannel().intValue()==channel &&
                    crossConnection.getSrcPort().equals(srcPort) &&
                    crossConnection.getDstPort().equals(dstPort)){
                elementIndex = i;
                break;
            }
        }
        if(elementIndex==-1){
            LOG.error("No cross-connection between source port "+srcPort+" and "+dstPort+" destination port found into node "+getNodeId()+".");
            return false;
        }
        crossConnections.remove(elementIndex);
        CrossConnectionsTop connectionsTop = crossConnectionsTopBuilder.setDeviceId(getNodeId()).setCrossConnections(crossConnections).build();
        LOG.info("Cross-connection between source port "+srcPort+" and "+dstPort+" destination port found into node "+getNodeId()+" is going to be removed.");
        return writeConfigRemoteDs(dataBrokerDevice, connectionsTop);
    }

    public boolean removeAllCrossConnection(){
        internalConnectionList.clear();
        return true;
    }

    private List<OwnedNodeEdgePoint> addNodeEdgePointSameDirection(String nodeId, int countNodeEdgePointToAdd, PortDirection portDirection, String startingPortId, boolean addServiceInterfacePoint){
        List <OwnedNodeEdgePoint> ownedNodeEdgePoints = new ArrayList<>();


        for(int i=0; i<countNodeEdgePointToAdd; i++){
            String leftPaddedCounter = String.valueOf(LumentumUtil.leftPadZero(i+1));
            String portId = startingPortId+leftPaddedCounter;


            List<MappedServiceInterfacePoint> mappedServiceInterfacePointList = new ArrayList<>();
            if(addServiceInterfacePoint) {
                MappedServiceInterfacePoint mappedServiceInterfacePoint =
                        new MappedServiceInterfacePointBuilder().setServiceInterfacePointUuid(new Uuid(nodeId+"_SIP" + leftPaddedCounter)).build();

                mappedServiceInterfacePointList.add(mappedServiceInterfacePoint);
            }

            OwnedNodeEdgePoint ownedNodeEdgePoint = new OwnedNodeEdgePointBuilder()
                    .setUuid(new Uuid(portId))
                    .setLinkPortDirection(portDirection)
                    .setAdministrativeState(AdministrativeState.UNLOCKED)
                    .setOperationalState(OperationalState.ENABLED)
                    .setMappedServiceInterfacePoint(mappedServiceInterfacePointList)
                    .build();
            ownedNodeEdgePoints.add(ownedNodeEdgePoint);
        }
        return ownedNodeEdgePoints;
    }


}
