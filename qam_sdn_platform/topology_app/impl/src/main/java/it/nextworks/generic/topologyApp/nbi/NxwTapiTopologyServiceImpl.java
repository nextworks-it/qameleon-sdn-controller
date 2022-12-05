package it.nextworks.generic.topologyApp.nbi;

import com.google.common.util.concurrent.ListenableFuture;
import it.nextworks.generic.topologyApp.Util;
import it.nextworks.generic.topologyApp.TopologyApp;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.ReadTransaction;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.*;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.get.nxw.topology.output.TopologyOutput;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.get.nxw.topology.output.TopologyOutputBuilder;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.nxw.topology.NxwLink;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.nxw.topology.NxwNode;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.tapi.topology.containers.TapiTopologyContainer;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.tapi.topology.containers.TapiTopologyContainerBuilder;
import org.opendaylight.yang.gen.v1.nxw.tapi.topology.rev200728.tapi.topology.containers.TapiTopologyContainerKey;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Host;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.IpAddress;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.Ipv4Address;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.PortNumber;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.ForwardingDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNode;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.NetconfNodeBuilder;
import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.netconf.node.credentials.credentials.LoginPasswordBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.LinkId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.link.attributes.Destination;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.link.attributes.DestinationBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.link.attributes.Source;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.link.attributes.SourceBuilder;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.*;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class NxwTapiTopologyServiceImpl implements NxwTapiTopologyService {

    private static final Logger LOG = LoggerFactory.getLogger(NxwTapiTopologyServiceImpl.class);
    private final DataBroker dataBroker;
    private final String TOPOLOGY_NETCONF = "topology-netconf";

    public NxwTapiTopologyServiceImpl(DataBroker dataBroker) {
        this.dataBroker = dataBroker;
    }


    /*Tapi topology methods*/
    private boolean addLinkTapiTopology(TopologyApp topo, NxwLink nxwLink){
        String nodeSrcId = nxwLink.getNodeSrc();
        String nodeDstId =nxwLink.getNodeDst();
        String linkId = nxwLink.getUuid().getValue();
        topo.addLink(linkId, nodeSrcId, nodeDstId, ForwardingDirection.UNIDIRECTIONAL);
        return true;
    }

    private boolean writeTapiTopologyIntoTree(TopologyApp topo){
        TapiTopologyContainer tapiTopologyContainer = new TapiTopologyContainerBuilder(topo.getTopology()).build();
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        InstanceIdentifier<TapiTopologyContainer> instanceIdentifier = InstanceIdentifier.builder(TapiTopologyContainers.class)
                .child(TapiTopologyContainer.class, new TapiTopologyContainerKey(new Uuid(topo.getTopology().getUuid()))).build();
        transaction.put(LogicalDatastoreType.CONFIGURATION,  instanceIdentifier, tapiTopologyContainer);

        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    private boolean deleteTapiTopologyFromTree(String topologyId){
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        InstanceIdentifier<TapiTopologyContainer> instanceIdentifier = InstanceIdentifier.builder(TapiTopologyContainers.class)
                .child(TapiTopologyContainer.class, new TapiTopologyContainerKey(new Uuid(topologyId)))
                .build();
        transaction.delete(LogicalDatastoreType.CONFIGURATION, instanceIdentifier);

        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            e.printStackTrace();;
            return false;
        }
        return true;
    }


    private TapiTopologyContainer readTapiTopologyFromTree(String topologyId){
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        //InstanceIdentifier<TapiTopologyContainer> instanceIdentifierTapiTopology = InstanceIdentifier.builder(TapiTopologyContainer.class).build();


        InstanceIdentifier<TapiTopologyContainer> iidtapitopology = InstanceIdentifier.builder(TapiTopologyContainers.class)
                .child(TapiTopologyContainer.class, new TapiTopologyContainerKey(new Uuid(topologyId)))
                        .build();


        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iidtapitopology).get();
            if(!data){
                LOG.warn("No tapi topology found into the tree.");
                return null;
            }
            Optional<TapiTopologyContainer> optionalTapiTopologyContainer =  transaction.read(LogicalDatastoreType.CONFIGURATION, iidtapitopology).get();
            return  optionalTapiTopologyContainer.get();

        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error while reading from the data tree.");
            e.printStackTrace();
            return null;
        } finally {
            transaction.close();
        }
    }

    /*network topology methods*/

    // It deletes a Node from the ODL topology (NetworkTopology)
    // tapiTopologyId: topology uuid in the tapiTopology, not "topology-netconf"
    // nodeId: node uuid
    private boolean deleteNodeFromNetworkTopology(String nodeId){
        InstanceIdentifier<Node> nodeIdentifier = Util.nodeIIdentifier(TOPOLOGY_NETCONF, nodeId);
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        transaction.delete(LogicalDatastoreType.CONFIGURATION, nodeIdentifier);

        try {
            LOG.debug("Deleting netconf node: {}", nodeIdentifier);
            transaction.commit().get();

        } catch (final InterruptedException | ExecutionException e) {
            LOG.error("Unable to remove node with Iid {}", nodeIdentifier, e);
            return false;
        }
        return true;
    }

    // It deletes a Link from the ODL topology (NetworkTopology)
    // tapiTopologyId: topology uuid in the tapiTopology, not "topology-netconf"
    // nodeId: node uuid
    private boolean deleteLinkFromNetworkTopology(String linkId){
        InstanceIdentifier<Link> linkIdentifier = Util.linkIIdentifier(TOPOLOGY_NETCONF, linkId);
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        transaction.delete(LogicalDatastoreType.CONFIGURATION, linkIdentifier);

        try {
            LOG.debug("Deleting netconf node: {}", linkIdentifier);
            transaction.commit().get();

        } catch (final InterruptedException | ExecutionException e) {
            LOG.error("Unable to remove node with Iid {}", linkIdentifier, e);
            return false;
        }
        return true;
    }


    // It deletes a TapiTopology from the ODL topology (NetworkTopology).
    // In this case we do not delete the entire topology, because under NetworkTopology the only one topology is the "topology-netconf"
    // that manages all the netconf devices. Deletes all the nodes and links of the NxwTapiTopology under the NetworkTopology.
    private boolean deleteTopologyFromTree(String topologyId){
        TapiTopologyContainer tapiTopologyContainer = readTapiTopologyFromTree(topologyId);
        if(tapiTopologyContainer==null){
            LOG.warn("Topology with ID "+ topologyId +" not found");
            return false;
        }

        for(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Node node: tapiTopologyContainer.getNode()){
            if(!deleteNodeFromNetworkTopology(node.getUuid().getValue())){
                return false;
            }
        }

        for(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Link link: tapiTopologyContainer.getLink()){
            if(!deleteLinkFromNetworkTopology(link.getUuid().getValue())){
                return false;
            }
        }
        return true;
    }


    // It checks if a Node with id nodeId already exists under the ODL topology (NetworkTopology) in the topology-netconf
    // returns true if already exists, false if not.
    private boolean checkIfNodeExists(String nodeId){
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        InstanceIdentifier<Node> nodeIID = Util.netconfNodeIid(nodeId);

        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, nodeIID).get();
            if(!data){

                return false;
            }
            LOG.error("Node " +  nodeId + " already exists in topology-netconf");
            return  true;

        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error while checking if the node " + nodeId + " exists in topology-netconf.");
            e.printStackTrace();
            return true;
        } finally {
            transaction.close();
        }
    }

    // It checks if a Node with id nodeId already exists under the ODL topology (NetworkTopology) in the topology-netconf
    // returns true if already exists, false if not.
    private boolean checkIfLinkExists(String linkId){
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        InstanceIdentifier<Link> linkIID = Util.netconfLinkIid(linkId);

        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, linkIID).get();
            if(!data){

                return false;
            }
            LOG.error("Link " +  linkId + " already exists in topology-netconf");
            return  true;

        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error while checking if the link " + linkId + " exists in topology-netconf.");
            e.printStackTrace();
            return true;
        } finally {
            transaction.close();
        }
    }


    // It checks if a node or a link already exists in the ODL topology (topology-netconf)
    // if false the creation will be ok; if true, a node or a link exists - stop creating
    private boolean checkIfExists(String tapiTopologyId, List<NxwNode> nodes, List<NxwLink> links){
        for(NxwNode nxwNode: nodes){
            String nodeId = nxwNode.getUuid().getValue();
            if(checkIfNodeExists(nodeId)){
                return true;
            }
        }
        for(NxwLink nxwLink: links){
            String linkId = nxwLink.getUuid().getValue();
            if(checkIfLinkExists(linkId)){
                return true;
            }
        }
        return false;
    }

    // It checks if a TapiTopology with this name already exists under tapi-topology-containers
    private boolean checkIfTapiTopologyExists(String tapiTopologyId){
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        InstanceIdentifier<TapiTopologyContainer> tapitopoiid = InstanceIdentifier.builder(TapiTopologyContainers.class)
                .child(TapiTopologyContainer.class, new TapiTopologyContainerKey(new Uuid(tapiTopologyId))).build();

        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, tapitopoiid).get();
            if(!data){
                return false;
            }
            LOG.warn("TapiTopology " +  tapiTopologyId + " already exists in tapi-topology-containers");
            return  true;

        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Error while checking if the tapitopology " + tapiTopologyId + " exists in tapi-topology-containers.");
            e.printStackTrace();
            return true;
        } finally {
            transaction.close();
        }
    }


    private boolean createNodeNetworkTopology(String topologyId, NxwNode nxwNode){
        final NodeId nodeId = new NodeId(nxwNode.getUuid().getValue());


        NetconfNode ncnode = new NetconfNodeBuilder()
                .setHost(new Host(new IpAddress(new Ipv4Address(nxwNode.getHost()))))
                .setPort(new PortNumber(Integer.valueOf(nxwNode.getPort())))
                .setTcpOnly(false).setSchemaless(false)
                .setCredentials(new LoginPasswordBuilder()
                        .setUsername(nxwNode.getUsername())
                        .setPassword(nxwNode.getPassword())
                        .build())
                .build();

        final Node updatedNode = new NodeBuilder()
                .withKey(new NodeKey(nodeId))
                .setNodeId(nodeId).addAugmentation(NetconfNode.class,ncnode).build();


        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        transaction.put(LogicalDatastoreType.CONFIGURATION,     Util.nodeIIdentifier(topologyId, nxwNode.getUuid().getValue()), updatedNode);

        try {
            transaction.commit().get();
            return true;

        } catch (final InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }


    private boolean createLinkNetworkTopology(String topologyId, NxwLink nxwLink){
        String linkidString = nxwLink.getUuid().getValue();
        final LinkId linkId = new LinkId(linkidString);
        Source source = new SourceBuilder().setSourceNode(new NodeId(nxwLink.getNodeSrc())).build();
        Destination destination = new DestinationBuilder().setDestNode(new NodeId(nxwLink.getNodeDst())).build();

        final Link link = new LinkBuilder().
                setLinkId(linkId).setSource(source).setDestination(destination).build();

        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        InstanceIdentifier <Link> instanceIdentifier = InstanceIdentifier.builder(NetworkTopology.class)
                .child(Topology.class, new TopologyKey(new TopologyId(topologyId)))
                .child(Link.class, new LinkKey(new LinkId(linkId)))
                .build();

        transaction.put(LogicalDatastoreType.CONFIGURATION,     instanceIdentifier, link);

        try {
            transaction.commit().get();
            return true;

        } catch (final InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }



    private ListenableFuture<RpcResult<CreateNxwTopologyOutput>> buildErrorMessage(String errorMessage){
        RpcResultBuilder<CreateNxwTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, errorMessage);
        LOG.error(errorMessage);
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<CreateNxwTopologyOutput>> createNxwTopology(CreateNxwTopologyInput input) {
        String topologyName = input.getNxwTopologyInput().getTopologyUuid();
        List<NxwNode> nxwNodeList = input.getNxwTopologyInput().getNxwNode();
        List<NxwLink> nxwLinkList = input.getNxwTopologyInput().getNxwLink();

        TopologyApp topologyApp = new TopologyApp(topologyName);

        LOG.info("The topology has "+nxwNodeList.size()+" nodes.");
        LOG.info("The topology has "+nxwLinkList.size()+" links.");

        // initial checks:
        // if the topologyname already exists, the creation fails
        if(checkIfTapiTopologyExists(topologyName)){
            return buildErrorMessage("Topology with name " + topologyName + " already exists");
        }
        // unique name for nodes and links, check if they already exist in the network topology - topology-netconf
        if(checkIfExists(topologyName,nxwNodeList,nxwLinkList)){
            return buildErrorMessage("Node or link already exists into network topology");
        }


        for(NxwNode nxwNode: nxwNodeList){

              boolean nodeSuccessfulCreated = createNodeNetworkTopology(TOPOLOGY_NETCONF,nxwNode);//Create a node for ODL topology

            if(!nodeSuccessfulCreated){
                return buildErrorMessage("Error creating node into network topology");
            }
            topologyApp.addNode(nxwNode.getUuid().getValue());
        }

        for(NxwLink nxwLink: nxwLinkList){
            boolean linkSuccessfulCreated = createLinkNetworkTopology(TOPOLOGY_NETCONF,nxwLink);//Create a link for ODL topology

            if(!linkSuccessfulCreated){
                return buildErrorMessage("Error creating link into network topology");
            }
            addLinkTapiTopology(topologyApp,nxwLink);
        }

        if(!writeTapiTopologyIntoTree(topologyApp)){
            return buildErrorMessage("Error creating tapi topology into tree.");
        }


        RpcResultBuilder<CreateNxwTopologyOutput> rpcResultBuilder = RpcResultBuilder.success();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Topology correctly created.");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<GetNxwTopologyOutput>> getNxwTopology(GetNxwTopologyInput input) {
        LOG.info("Get NXW Topology called");
        TapiTopologyContainer topologyContainer = readTapiTopologyFromTree(input.getTopologyId());
        if(topologyContainer==null){
            RpcResultBuilder<GetNxwTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Unable to find tapi topology into tree.");
            return rpcResultBuilder.buildFuture();
        }
        TopologyOutput topologyOutput =
                new TopologyOutputBuilder()
                        //.setBoundaryNodeEdgePoint(topologyContainer.getBoundaryNodeEdgePoint())
                        .setLink(topologyContainer.getLink())
                        .setNode(topologyContainer.getNode())
                        .setUuid(topologyContainer.getUuid())
                        .build();

        TopologyApp topologyApp = new TopologyApp(topologyContainer);

        GetNxwTopologyOutput getNxwTopologyOutput = new GetNxwTopologyOutputBuilder().setTopologyOutput(topologyOutput).build();
        RpcResultBuilder<GetNxwTopologyOutput> rpcResultBuilder = RpcResultBuilder.success();
        return rpcResultBuilder.withResult(getNxwTopologyOutput).buildFuture();
    }


    @Override
    public ListenableFuture<RpcResult<DelNxwTopologyOutput>> delNxwTopology(DelNxwTopologyInput input) {
        String topologyId = input.getTopologyId();

        LOG.info("Delete NXW Topology called");
        if(!deleteTopologyFromTree(input.getTopologyId())){ //Delete the topology from network topology tree
            RpcResultBuilder<DelNxwTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error deleting topology.");
            LOG.error("Error deleting network topology.");
            return rpcResultBuilder.buildFuture();
        }
        if(!deleteTapiTopologyFromTree(topologyId)){ //Delete the topology from tapi topology tree
            RpcResultBuilder<DelNxwTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error deleting topology.");
            LOG.error("Error deleting tapi topology.");
            return rpcResultBuilder.buildFuture();
        }
        RpcResultBuilder<DelNxwTopologyOutput> rpcResultBuilder = RpcResultBuilder.success();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Topology network correctly deleted");
        LOG.info("Topology network correctly deleted");
        return rpcResultBuilder.buildFuture();
    }

}
