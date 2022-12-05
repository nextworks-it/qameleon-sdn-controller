package it.nextworks.qameleon.sbi.netconf_driver;

import org.opendaylight.mdsal.binding.api.*;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.network.topology.topology.topology.types.TopologyNetconf;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.NodeKey;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class NetconfDriver {

    private static final Logger LOG = LoggerFactory.getLogger(NetconfDriver.class);
    private final MountPointService mountPointService;



    private final String NODE_ID;

    private  final InstanceIdentifier<Topology> NETCONF_TOPOLOGY_IID =
            InstanceIdentifier.builder(NetworkTopology.class).child(Topology.class, new TopologyKey(new TopologyId(TopologyNetconf.QNAME.getLocalName()))).build();

    public NetconfDriver(MountPointService mountPointService, String NODE_ID) {
        this.mountPointService = mountPointService;
        this.NODE_ID = NODE_ID;
    }

    private InstanceIdentifier<Node> netconfNodeIid(final String nodeId) {
        LOG.info("Getting node instance identifier.");
        return NETCONF_TOPOLOGY_IID.child(Node.class, new NodeKey(new NodeId(nodeId)));
    }


    protected RpcConsumerRegistry getRpcConsumerRegistry(){
        InstanceIdentifier<Node> nodeInput = netconfNodeIid(NODE_ID);
        final Optional<MountPoint> mpOptional = mountPointService.getMountPoint(nodeInput);
        final MountPoint mountPoint = mpOptional.get();
        return mountPoint.getService(RpcConsumerRegistry.class).get();
    }


    protected DataBroker getDataBroker(){
        LOG.info("Getting data broker for node "+NODE_ID);
        InstanceIdentifier<Node> nodeInput = netconfNodeIid(NODE_ID);
        if(nodeInput==null){
            LOG.info("Instance identifier is null.");
            return null;
        }
        //LOG.info("Getting mount point");
        //LOG.info("mountPointService==null {}",mountPointService==null);
        final Optional<MountPoint> mpOptional = mountPointService.getMountPoint(nodeInput);

        if(mpOptional==null || !mpOptional.isPresent()){
            LOG.warn("Unable to locate mount point: either not mounted yet or not configured. ");
            return null;
        }

        final MountPoint mountPoint = mpOptional.get();
        // Get the DataBroker for the mounted node
        if(mountPoint==null){
            LOG.warn("Mount point not available.");
            return null;
        }
        final DataBroker dataBrokerDevice = mountPoint.getService(DataBroker.class).get();

        LOG.info("Data broker correctly get for node  "+NODE_ID);
        return dataBrokerDevice;
    }



    public Optional<?> readFromDataStore( Class className, LogicalDatastoreType logicalDatastoreType){
        DataBroker db = getDataBroker();
        if(db==null){
            LOG.error("Cannot read from datastore. Data broker is null.");
            return null;
        }

        final ReadTransaction readTrx = db.newReadOnlyTransaction();

        InstanceIdentifier<?> iid = InstanceIdentifier.create(className);

        Optional<?> data;
        try {
            LOG.info("Trying reading from "+logicalDatastoreType.toString()+ " data store");
            data = readTrx.read(logicalDatastoreType, iid).get();
            if(data==null || !data.isPresent())
                return Optional.empty();

        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error while reading from data store");
            LOG.error(e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        } finally {
            readTrx.close();
        }

        LOG.info("Content read: " + data.get());
        return data;
    }

    public String getNodeId() {
        return NODE_ID;
    }


}
