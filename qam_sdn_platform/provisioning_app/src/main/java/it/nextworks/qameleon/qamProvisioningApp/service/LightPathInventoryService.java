package it.nextworks.qameleon.qamProvisioningApp.service;

import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.ReadTransaction;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Context;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.Context1;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.context.PathComputationContext;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.computation.context.Path;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.computation.context.PathKey;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class LightPathInventoryService {
    private final DataBroker dataBroker;
    private static final Logger LOG = LoggerFactory.getLogger(LightPathInventoryService.class);
    public LightPathInventoryService(final DataBroker dataBroker) {
        this.dataBroker = dataBroker;
    }

    private InstanceIdentifier<Path> getInstanceIdentifierPath(String lightPathUuid){

        InstanceIdentifier<Path> instanceIdentifier =
                InstanceIdentifier.create(Context.class)
                        .augmentation(Context1.class)
                        .child(PathComputationContext.class)
                        .child(Path.class,new PathKey(new Uuid(lightPathUuid)));
        return instanceIdentifier;
    }


    public boolean writeLightPathIntoTree(Path lightPath){
       final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();

        String lightPathUuid = lightPath.getUuid().getValue();
        InstanceIdentifier<Path> instanceIdentifier = getInstanceIdentifierPath(lightPathUuid);
        LOG.debug("instance identifier: "+ instanceIdentifier);
        transaction.put(LogicalDatastoreType.CONFIGURATION,  instanceIdentifier, lightPath);

        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            return false;
        }
            return true;
    }


    public Path getLightPathFromTree(String lightPathUuid){
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();

        InstanceIdentifier<Path> instanceIdentifier = getInstanceIdentifierPath(lightPathUuid);
        LOG.debug("instance identifier: "+ instanceIdentifier);
        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, instanceIdentifier).get();
            if(!data){
                LOG.warn("No lightpath with uuid "+lightPathUuid+" found into the tree.");
                return null;
            }
            Optional<Path> opt =
                    transaction.read(LogicalDatastoreType.CONFIGURATION, instanceIdentifier).get();
            return  opt.get();
        } catch (final InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            return null;
        }
    }


    public boolean deleteLightPathFromTree(String lightPathUuid){
        getInstanceIdentifierPath(lightPathUuid);
        InstanceIdentifier<Path> instanceIdentifier = getInstanceIdentifierPath(lightPathUuid);
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();

        transaction.delete(LogicalDatastoreType.CONFIGURATION, instanceIdentifier);

        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            return false;
        }
        return true;
    }
}
