package it.nextworks.qameleon.qamProvisioningApp.nbi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.util.concurrent.ListenableFuture;
import it.nextworks.qameleon.qamProvisioningApp.service.QamProvisioningService;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.*;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.compute.p._2.p.path.output.Service;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.compute.p._2.p.path.output.ServiceBuilder;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TapiPathComputationRest implements TapiPathComputationService {
    private QamProvisioningService qamProvisioningService;
    private static final Logger LOG = LoggerFactory.getLogger(TapiPathComputationRest.class);

    public TapiPathComputationRest(DataBroker dataBroker, MountPointService mountPointService){
        qamProvisioningService = new QamProvisioningService(dataBroker,mountPointService);
    }
    @Override
    public ListenableFuture<RpcResult<DeleteP2PPathOutput>> deleteP2PPath(DeleteP2PPathInput input) {
        String lightPathId = input.getPathIdOrName();
        LOG.info("Tapi path computation: received request to remove light path with uuid: "+lightPathId);
        try {
            qamProvisioningService.removeLightPath(lightPathId);
        } catch (Exception e) {
            LOG.error("Error during removal of light path with uuid "+lightPathId);
            LOG.error(e.getMessage());
            RpcResultBuilder<DeleteP2PPathOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
            return rpcResultBuilder.buildFuture();
        }
        RpcResultBuilder<DeleteP2PPathOutput> rpcResultBuilder = RpcResultBuilder.success();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<OptimizeP2PpathOutput>> optimizeP2Ppath(OptimizeP2PpathInput input) {
        RpcResultBuilder<OptimizeP2PpathOutput> rpcResultBuilder = RpcResultBuilder.failed();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Method not implemented yet.");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<ComputeP2PPathOutput>> computeP2PPath(ComputeP2PPathInput input) {
        try {

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String jsonInputStrFormant = ow.writeValueAsString(input);
            LOG.info(jsonInputStrFormant);
            String lightPathId = qamProvisioningService.createLightPathRequest(input);

            LOG.info("Light path correctly created..");
            Service service =new ServiceBuilder().setUuid(new Uuid(lightPathId)).build();
            RpcResultBuilder<ComputeP2PPathOutput> rpcResultBuilder = RpcResultBuilder.success(new ComputeP2PPathOutputBuilder().setService(service).build());
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Light path correctly created.");
            return rpcResultBuilder.buildFuture();
        } catch (Exception e) {
            LOG.error("Unable to create light path.");
            LOG.error(e.getMessage());
            RpcResultBuilder<ComputeP2PPathOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Internal server error");
            return rpcResultBuilder.buildFuture();
        }
    }
}
