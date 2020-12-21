package it.nextworks.generic.topologyApp.service;

import javassist.NotFoundException;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.ReadTransaction;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Context;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.ContextBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Context1;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Context1Builder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Link;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.context.TopologyContext;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.context.TopologyContextBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.context.Topology;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.context.TopologyKey;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class TapiDataTreeService {
    private final DataBroker dataBroker;
    private static final Logger LOG = LoggerFactory.getLogger(TapiDataTreeService.class);

    public TapiDataTreeService(DataBroker dataBroker) {
        this.dataBroker = dataBroker;
    }

    private InstanceIdentifier<TopologyContext> getInstanceIdentifierAllTopologies() {
        return InstanceIdentifier.create(Context.class)
                .augmentation(Context1.class)
                .child(TopologyContext.class);
    }

    private InstanceIdentifier<Topology> getInstanceIdentifierTopology(String topologyId) {
        return InstanceIdentifier.create(Context.class)
                .augmentation(Context1.class)
                .child(TopologyContext.class)
                .child(Topology.class, new TopologyKey(new Uuid(topologyId)));
    }


    public void writeTapiTopologyIntoTree(Topology topology) throws ExecutionException {
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        List<Topology> topologyList = new ArrayList<>();
        topologyList.add(topology);
        Context1 context1 = new Context1Builder().setTopologyContext(new TopologyContextBuilder().setTopology(topologyList).build()).build();
        Context context = new ContextBuilder().addAugmentation(Context1.class, context1).build();
        InstanceIdentifier<Context> iidContext = InstanceIdentifier.create(Context.class);
       // InstanceIdentifier<Topology> iid = getInstanceIdentifierTopology(topology.getUuid().getValue());
        transaction.put(LogicalDatastoreType.CONFIGURATION,  iidContext, context);
        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
           throw new ExecutionException(new Throwable("Error during the topology update."));
        }
    }

    public List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.topology.list.output.Topology> getAllTopologies() throws ExecutionException, InterruptedException, NotFoundException {
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        Boolean data;
        Optional<TopologyContext> opt;
        InstanceIdentifier<TopologyContext> iid = getInstanceIdentifierAllTopologies();
        data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iid).get();
            if (!data) {
                LOG.warn("Error reading topologies from the tree.");
                throw new NotFoundException("Error reading topologies from the tree.");
            }
            opt = transaction.read(LogicalDatastoreType.CONFIGURATION, iid).get();
            transaction.close();

            List<Topology> topologyList=opt.get().getTopology();
            List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.topology.list.output.Topology> topologyOutList
                    = new ArrayList<>();

            for(Topology topology: topologyList){
                org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.topology.list.output.Topology topologyOut
                        = new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.topology.list.output.TopologyBuilder()
                        .setLayerProtocolName(topology.getLayerProtocolName())
                        .setLink(topology.getLink())
                        .setName(topology.getName())
                        .setUuid(topology.getUuid())
                        .setNode(topology.getNode())
                        .build();
                topologyOutList.add(topologyOut);
            }

        return topologyOutList;
    }


public org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.topology.details.output.Topology getTopologyFromDataTree(String topologyUuid) throws ExecutionException, InterruptedException, NotFoundException {
    final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
    Boolean data;
    Optional<Topology> topologyOpt;
    InstanceIdentifier<Topology> iidTopology = getInstanceIdentifierTopology(topologyUuid);

        data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iidTopology).get();
        if(!data){
            LOG.warn("No tapi topology found into the tree.");
            throw new NotFoundException("Topology with Uuid "+topologyUuid+" not found");
        }
        topologyOpt =  transaction.read(LogicalDatastoreType.CONFIGURATION, iidTopology).get();
    Topology topology = topologyOpt.get();
        transaction.close();
    return  new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.topology.details.output.TopologyBuilder()
            .setLayerProtocolName(topology.getLayerProtocolName())
            .setLink(topology.getLink())
            .setName(topology.getName())
            .setUuid(topology.getUuid())
            .setNode(topology.getNode())
            .build();
}

public org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.node.details.output.Node getNodeFromTree(String topologyUuid, String nodeUuid) throws ExecutionException, InterruptedException, NotFoundException {
    org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.topology.details.output.Topology topology = getTopologyFromDataTree(topologyUuid);
    for(Node node : topology.getNode()){
        if(node.getUuid().getValue().equals(nodeUuid)) {
            return new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.node.details.output.NodeBuilder(node).build();

        }
    }
    throw new NotFoundException("Node with uuid "+nodeUuid+" not found within topology with uuid " +topologyUuid);
}

    public org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.link.details.output.Link getLinkFromTree(String topologyUuid, String linkUuid) throws ExecutionException, InterruptedException, NotFoundException {
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.topology.details.output.Topology topology = getTopologyFromDataTree(topologyUuid);
        for(Link link : topology.getLink()){
            if(link.getUuid().getValue().equals(linkUuid)) {
             return new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.link.details.output.LinkBuilder(link).build();
            }
        }
        throw new NotFoundException("Link with uuid "+linkUuid+" not found within topology with uuid " +topologyUuid);
    }


    public org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.node.edge.point.details.output.NodeEdgePoint getNodeEdgePointFromTree(String topologyUuid, String nodeUuid, String nepUuid) throws InterruptedException, ExecutionException, NotFoundException {
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.node.details.output.Node node = getNodeFromTree(topologyUuid, nodeUuid);
        for(OwnedNodeEdgePoint onep: node.getOwnedNodeEdgePoint()){
            if(onep.getUuid().getValue().equals(nepUuid)) {
                return new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.get.node.edge.point.details.output.NodeEdgePointBuilder(onep)
                        .build();
            }
        }
        throw new NotFoundException("Node Edge Point with uuid "+nepUuid+" not found within node "+nodeUuid +" into topology with uuid " +topologyUuid);
    }
}
