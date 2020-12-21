package it.nextworks.qameleon.topologyApp.nbi;

import com.google.common.util.concurrent.ListenableFuture;
import it.nextworks.generic.topologyApp.nbi.NxwTapiTopologyServiceImpl;
import it.nextworks.generic.topologyApp.nbi.TapiTopologyServiceImpl;
import it.nextworks.qameleon.topologyApp.service.TopologyQameleonService;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.*;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.get.qam.link.output.LinkOutBuilder;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.get.qam.node.output.NodeOutBuilder;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.get.qam.topology.output.QamTopologyOut;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.get.qam.topology.output.QamTopologyOutBuilder;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLink;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNode;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Link;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.LinkBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.NodeBuilder;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class QameleonTopologyAppRest implements QameleonTopologyService {

    private TopologyQameleonService topologyQameleonService;
    private TapiTopologyServiceImpl tapiTopologyService;
    private NxwTapiTopologyServiceImpl nxwTapiTopologyServiceImpl;
    private MountPointService mountPointService;
    private final DataBroker dataBroker;

    private static final Logger LOG = LoggerFactory.getLogger(QameleonTopologyAppRest.class);

    public QameleonTopologyAppRest(DataBroker dataBroker, TapiTopologyServiceImpl tapiTopologyService, NxwTapiTopologyServiceImpl nxwTapiTopologyServiceImpl, MountPointService mountPointService){
        this.tapiTopologyService = tapiTopologyService;
        this.nxwTapiTopologyServiceImpl = nxwTapiTopologyServiceImpl;
        this.mountPointService = mountPointService;
        this.dataBroker = dataBroker;
        topologyQameleonService = new TopologyQameleonService(this.dataBroker);
    }


    @Override
    public ListenableFuture<RpcResult<CreateQamTopologyOutput>> createQamTopology(CreateQamTopologyInput input) {
        LOG.info("Received request to create QAM topology.");
        if(!topologyQameleonService.createTopology(nxwTapiTopologyServiceImpl, mountPointService, input.getTopology())){
            RpcResultBuilder<CreateQamTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error during devices connection.");
            return rpcResultBuilder.buildFuture();
        }

        boolean treeUpdated = tapiTopologyService.updateTapiTopologyIntoTree(qamTopologyToTapiTopology(topologyQameleonService.getQamTopology()));
        if(!treeUpdated){
            RpcResultBuilder<CreateQamTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Unable to update the data tree..");
            return rpcResultBuilder.buildFuture();
        }

        RpcResultBuilder<CreateQamTopologyOutput> rpcResultBuilder = RpcResultBuilder.success();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<GetQamLinkOutput>> getQamLink(GetQamLinkInput input) {
        if(topologyQameleonService==null){
            RpcResultBuilder<GetQamLinkOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error getting link. There is no topology.");
            return rpcResultBuilder.buildFuture();
        }

        String linkId = input.getLinkId();
        QamLink qamLink = topologyQameleonService.getQamLink(linkId);
        if(qamLink==null){
            RpcResultBuilder<GetQamLinkOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Link not found.");
            return rpcResultBuilder.buildFuture();
        }
        GetQamLinkOutput getQamLinkOutput = new GetQamLinkOutputBuilder()
                .setLinkOut
                        (new LinkOutBuilder(qamLink).build())
                .build();
        RpcResultBuilder<GetQamLinkOutput> rpcResultBuilder = RpcResultBuilder.success(getQamLinkOutput);
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();

    }

    @Override
    public ListenableFuture<RpcResult<GetQamNodeOutput>> getQamNode(GetQamNodeInput input) {
        if(topologyQameleonService==null){
            RpcResultBuilder<GetQamNodeOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error getting node. There is no topology.");
            return rpcResultBuilder.buildFuture();
        }

        String nodeId = input.getNodeId();
        QamNode qamNode = topologyQameleonService.getNode(nodeId);
        if(qamNode==null){
            new GetQamNodeOutputBuilder().setNodeOut(new NodeOutBuilder().build());
            RpcResultBuilder<GetQamNodeOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Node not found");
            return rpcResultBuilder.buildFuture();
        }
        GetQamNodeOutput getQamNodeOutput = new GetQamNodeOutputBuilder()
                .setNodeOut
                        (new NodeOutBuilder(qamNode).build())
                .build();
        RpcResultBuilder<GetQamNodeOutput> rpcResultBuilder = RpcResultBuilder.success(getQamNodeOutput);
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<DeleteTopologyOutput>> deleteTopology(DeleteTopologyInput input) {
        if(topologyQameleonService==null){
            RpcResultBuilder<DeleteTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error deleting topology. topology not found");
            return rpcResultBuilder.buildFuture();
        }
        boolean topologyDeletedSuccess= topologyQameleonService.deleteTopology(nxwTapiTopologyServiceImpl, input.getTopologyUuid());
        if(!topologyDeletedSuccess){
            RpcResultBuilder<DeleteTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error deleting topology.");
            return rpcResultBuilder.buildFuture();
        }
        RpcResultBuilder<DeleteTopologyOutput> rpcResultBuilder = RpcResultBuilder.success();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<UpdateLinkOutput>> updateLink(UpdateLinkInput input) {
        if(topologyQameleonService==null){
            RpcResultBuilder<UpdateLinkOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error updating link info. There is no topology.");
            return rpcResultBuilder.buildFuture();
        }
        if(!topologyQameleonService.updateLinkInfoChannelAdded(input.getChannelNumber(),input.getLinkUuidList())){
            RpcResultBuilder<UpdateLinkOutput> rpcResultBuilder = RpcResultBuilder.failed();
            return rpcResultBuilder.buildFuture();
        }
        RpcResultBuilder<UpdateLinkOutput> rpcResultBuilder = RpcResultBuilder.success();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<RemoveLightPathOutput>> removeLightPath(RemoveLightPathInput input) {
        if(topologyQameleonService==null){
            RpcResultBuilder<RemoveLightPathOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error updating light path. There is no topology.");
            return rpcResultBuilder.buildFuture();
        }
        if(!topologyQameleonService.updateLinkInfoChannelRemoved(input)){
            RpcResultBuilder<RemoveLightPathOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error during link updating");
            return rpcResultBuilder.buildFuture();
        }
        LOG.info("Light path correctly removed.");
        RpcResultBuilder<RemoveLightPathOutput> rpcResultBuilder = RpcResultBuilder.success();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();
    }


    @Override
    public ListenableFuture<RpcResult<GetQamTopologyOutput>> getQamTopology(GetQamTopologyInput input) {
        if(topologyQameleonService==null){
            RpcResultBuilder<GetQamTopologyOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error updating light path. There is no topology.");
            return rpcResultBuilder.buildFuture();
        }

        QamTopology qamTopology = topologyQameleonService.getQamTopology();
        QamTopologyOut qamTopologyOut;
        if(qamTopology==null){
             qamTopologyOut = new QamTopologyOutBuilder().build();
        }else {
             qamTopologyOut = new QamTopologyOutBuilder()
                    .setQamLink(qamTopology.getQamLink())
                    .setQamNode(qamTopology.getQamNode())
                    .setTopologyUuid(qamTopology.getTopologyUuid())
                    .build();
        }
        GetQamTopologyOutput getQamTopologyOutput = new GetQamTopologyOutputBuilder().setQamTopologyOut(qamTopologyOut).build();
        RpcResultBuilder<GetQamTopologyOutput> rpcResultBuilder = RpcResultBuilder.success(getQamTopologyOutput);
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();
    }

    
    private org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.context.Topology qamTopologyToTapiTopology(QamTopology qamTopology){
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.context.TopologyBuilder topologyBuilder =
                new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.context.TopologyBuilder();
        List<Link> linkList = new ArrayList<>();
        for(QamLink qamLink:qamTopology.getQamLink()){

            Link link = new LinkBuilder()
                    .setAdministrativeState(qamLink.getAdministrativeState())
                    .setAvailableCapacity(qamLink.getAvailableCapacity())
                    .setDirection(qamLink.getDirection())
                    .setName(qamLink.getName())
                    .setNodeEdgePoint(qamLink.getNodeEdgePoint())
                    .setOperationalState(qamLink.getOperationalState())
                    .setUuid(qamLink.getUuid()).build();
            linkList.add(link);
        }
        List<Node> nodesList = new ArrayList<>();
        for(QamNode qamNode:qamTopology.getQamNode()){

            Node node = new NodeBuilder()
                    .setOperationalState(qamNode.getOperationalState())
                    .setOwnedNodeEdgePoint(qamNode.getOwnedNodeEdgePoint())
                    .setUuid(qamNode.getUuid())
                    .setAdministrativeState(qamNode.getAdministrativeState())
                    .setLayerProtocolName(qamNode.getLayerProtocolName())
                    .setName(qamNode.getName())
                    .build();
            nodesList.add(node);
        }


        return topologyBuilder
                .setLink(linkList)
                .setNode(nodesList)
                .setUuid(new Uuid(qamTopology.getTopologyUuid())).build();

    }


}
