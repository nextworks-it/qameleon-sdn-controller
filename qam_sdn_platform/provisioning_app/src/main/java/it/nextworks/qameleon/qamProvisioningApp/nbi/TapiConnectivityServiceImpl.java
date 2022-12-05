package it.nextworks.qameleon.qamProvisioningApp.nbi;


import com.google.common.util.concurrent.ListenableFuture;
import it.nextworks.qameleon.qamProvisioningApp.service.QamProvisioningService;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.*;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.service.EndPoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.service.EndPointBuilder;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TapiConnectivityServiceImpl implements TapiConnectivityService {
    private QamProvisioningService qamProvisioningService;
    private static final Logger LOG = LoggerFactory.getLogger(TapiPathComputationRest.class);

    public TapiConnectivityServiceImpl(DataBroker dataBroker, MountPointService mountPointService) {
        qamProvisioningService = new QamProvisioningService(dataBroker, mountPointService);
    }

    @Override
    public ListenableFuture<RpcResult<UpdateConnectivityServiceOutput>> updateConnectivityService(UpdateConnectivityServiceInput input) {
        return null;
    }

    @Override
    public ListenableFuture<RpcResult<GetConnectionDetailsOutput>> getConnectionDetails(GetConnectionDetailsInput input) {
        return null;
    }

    @Override
    public ListenableFuture<RpcResult<GetConnectivityServiceDetailsOutput>> getConnectivityServiceDetails(GetConnectivityServiceDetailsInput input) {
        return null;
    }

    @Override
    public ListenableFuture<RpcResult<DeleteConnectivityServiceOutput>> deleteConnectivityService(DeleteConnectivityServiceInput input) {
        String lightPathId = input.getServiceIdOrName();
        LOG.info("Tapi path computation: received request to remove light path with uuid: "+lightPathId);
        try {
            qamProvisioningService.removeLightPath(lightPathId);
        } catch (Exception e) {
            LOG.error("Error during removal of light path with uuid "+lightPathId);
            LOG.error(e.getMessage());
            RpcResultBuilder<DeleteConnectivityServiceOutput> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
            return rpcResultBuilder.buildFuture();
        }
        RpcResultBuilder<DeleteConnectivityServiceOutput> rpcResultBuilder = RpcResultBuilder.success();
        rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "OK");
        return rpcResultBuilder.buildFuture();
    }

    @Override
    public ListenableFuture<RpcResult<GetConnectivityServiceListOutput>> getConnectivityServiceList(GetConnectivityServiceListInput input) {
        return null;
    }

    @Override
    public ListenableFuture<RpcResult<GetConnectionEndPointDetailsOutput>> getConnectionEndPointDetails(GetConnectionEndPointDetailsInput input) {
        return null;
    }

    @Override
    public ListenableFuture<RpcResult<CreateConnectivityServiceOutput>> createConnectivityService(CreateConnectivityServiceInput input) {
        String lightPathId = null;
        try {
            lightPathId = qamProvisioningService.createLightPathRequest(input, null);
            LOG.info("Light path correctly created..");
            List<EndPoint> epList = new ArrayList();

            epList.add(new EndPointBuilder().setLocalId(input.getEndPoint().get(0).getLocalId()).build());
            String message = "";
            if(input.getEndPoint().size()>1) {
                epList.add(new EndPointBuilder().setLocalId(input.getEndPoint().get(1).getLocalId()).build());
                message="Light path correctly created.";
            }
            else {
                epList.add(new EndPointBuilder().setLocalId(input.getEndPoint().get(0).getLocalId()).build());
                message="Configuration successfully managed";
            }

            org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.create.connectivity.service.output.Service service =
                    new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.create.connectivity.service.output.ServiceBuilder()
                            .setUuid(new Uuid(lightPathId))
                            .setEndPoint(epList)
                            .build();
            RpcResultBuilder<CreateConnectivityServiceOutput> rpcResultBuilder =
                    RpcResultBuilder.
                            success(new CreateConnectivityServiceOutputBuilder().setService(service).build());
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, message);
            return rpcResultBuilder.buildFuture();
        } catch (IOException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
        LOG.error("Error during lightpath creation or configuration management");
        RpcResultBuilder<CreateConnectivityServiceOutput> rpcResultBuilderError = RpcResultBuilder.failed();
        rpcResultBuilderError.withError(RpcError.ErrorType.APPLICATION, "Internal server error");
        return rpcResultBuilderError.buildFuture();

        }
}
