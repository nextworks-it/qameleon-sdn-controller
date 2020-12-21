package it.nextworks.generic.topologyApp.nbi;

import com.google.common.util.concurrent.ListenableFuture;
import it.nextworks.generic.topologyApp.service.TapiDataTreeService;
import javassist.NotFoundException;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.*;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.ExecutionException;

public class TapiTopologyServiceImpl implements TapiTopologyService {
    TapiDataTreeService tapiDataTreeService;
    private static final Logger LOG = LoggerFactory.getLogger(NxwTapiTopologyServiceImpl.class);



    public TapiTopologyServiceImpl(final DataBroker dataBroker){
        tapiDataTreeService = new TapiDataTreeService(dataBroker);
    }


    @Override
    public ListenableFuture<RpcResult<GetTopologyListOutput>> getTopologyList(GetTopologyListInput input) {
         try {
            LOG.info("Received request to get all topologies.");
            GetTopologyListOutput getTopologyListOutput = new GetTopologyListOutputBuilder().setTopology(tapiDataTreeService.getAllTopologies()).build();
            return RpcResultBuilder.success(getTopologyListOutput).buildFuture();

        } catch (ExecutionException | NotFoundException e) {
            LOG.error(e.getMessage());
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        }
        RpcResultBuilder<GetTopologyListOutput> rpcResultBuilder = RpcResultBuilder.failed();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error during get all topologies.");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<GetTopologyDetailsOutput>> getTopologyDetails(GetTopologyDetailsInput input) {
        LOG.info("Received request to get a specific topology.");
        String topologyUuid = input.getTopologyIdOrName();
        try {
            GetTopologyDetailsOutput getTopologyDetailsOutput = new GetTopologyDetailsOutputBuilder().setTopology(tapiDataTreeService.getTopologyFromDataTree(topologyUuid)).build();
            return RpcResultBuilder.success(getTopologyDetailsOutput).buildFuture();
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } catch (NotFoundException e) {
            LOG.error(e.getMessage());
        }
        RpcResultBuilder<GetTopologyDetailsOutput> rpcResultBuilder = RpcResultBuilder.failed();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error during get of topology.");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<GetLinkDetailsOutput>> getLinkDetails(GetLinkDetailsInput input) {
        LOG.info("Received request to get link details.");
        String linkUuid = input.getLinkIdOrName();
        String topologyUuid = input.getTopologyIdOrName();
        try {
            GetLinkDetailsOutput getLinkDetailsOutput =
                    new GetLinkDetailsOutputBuilder().setLink(tapiDataTreeService.getLinkFromTree(topologyUuid, linkUuid)).build();
            return RpcResultBuilder.success(getLinkDetailsOutput).buildFuture();
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } catch (NotFoundException e) {
            LOG.error(e.getMessage());
        }
        RpcResultBuilder<GetLinkDetailsOutput> rpcResultBuilder = RpcResultBuilder.failed();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error during get of link.");
        return rpcResultBuilder.buildFuture();
    }


    public boolean updateTapiTopologyIntoTree(Topology topology){
        LOG.info("Received request to update topology.");
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.context.Topology topology1 =
        new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.context.TopologyBuilder(topology).build();
        try {
            tapiDataTreeService.writeTapiTopologyIntoTree(topology1);
            return true;
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
        }
        return false;
    }

    @Override
    public ListenableFuture<RpcResult<GetNodeDetailsOutput>> getNodeDetails(GetNodeDetailsInput input) {
        LOG.info("Received request to get node details.");
        String nodeUuid = input.getNodeIdOrName();
        String topologyUuid = input.getTopologyIdOrName();
        try {
            GetNodeDetailsOutput getNodeDetailsOutput =
                    new GetNodeDetailsOutputBuilder().setNode(tapiDataTreeService.getNodeFromTree(topologyUuid,nodeUuid)).build();
            return RpcResultBuilder.success(getNodeDetailsOutput).buildFuture();
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } catch (NotFoundException e) {
            LOG.error(e.getMessage());
        }
        RpcResultBuilder<GetNodeDetailsOutput> rpcResultBuilder = RpcResultBuilder.failed();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error during get of node.");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<GetNodeEdgePointDetailsOutput>> getNodeEdgePointDetails(GetNodeEdgePointDetailsInput input) {
        LOG.info("Received request to get node edge point details.");
        String nodeEdgePointUuid = input.getEpIdOrName();
        String nodeUuid = input.getNodeIdOrName();
        String topologyUuid = input.getTopologyIdOrName();
        try {
            GetNodeEdgePointDetailsOutput getNodeEdgePointDetailsOutput = new GetNodeEdgePointDetailsOutputBuilder()
                    .setNodeEdgePoint(tapiDataTreeService.getNodeEdgePointFromTree(topologyUuid, nodeUuid, nodeEdgePointUuid))
            .build();
            ;
            return RpcResultBuilder.success(getNodeEdgePointDetailsOutput).buildFuture();
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
        } catch (NotFoundException e) {
            LOG.error(e.getMessage());
        }
        RpcResultBuilder<GetNodeEdgePointDetailsOutput> rpcResultBuilder = RpcResultBuilder.failed();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Error during get of node.");
        return rpcResultBuilder.buildFuture();
    }


}
