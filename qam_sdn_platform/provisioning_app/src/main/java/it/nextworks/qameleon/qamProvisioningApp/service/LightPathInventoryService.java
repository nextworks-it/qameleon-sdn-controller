package it.nextworks.qameleon.qamProvisioningApp.service;

import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.ReadTransaction;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Context;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.ContextBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1Builder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.Connection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.ConnectivityService;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.context.ConnectivityContext;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.context.ConnectivityContextBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.Context1;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.context.PathComputationContext;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.computation.context.Path;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.computation.context.PathKey;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
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

    private ConnectivityContext getConnectivityContext() throws ExecutionException, InterruptedException {
        LOG.info("Going to read Connectivity Context from Data Store.");
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        Boolean data;
        Optional<ConnectivityContext> connectivityContextOptional;
        InstanceIdentifier<ConnectivityContext> iidConnectivityContext = InstanceIdentifier.create(Context.class)
                .augmentation(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1.class)
                .child(ConnectivityContext.class);

        data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iidConnectivityContext).get();
        if(!data){
            LOG.warn("No Connectivity Service found into the Data Store.");
            return new ConnectivityContextBuilder().build();
        }
        connectivityContextOptional =  transaction.read(LogicalDatastoreType.CONFIGURATION, iidConnectivityContext).get();
        ConnectivityContext connectivityContext = connectivityContextOptional.get();
        transaction.close();
        return  connectivityContext;
    }

    public static Context getContext(DataBroker dataBroker) throws ExecutionException, InterruptedException {
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        Boolean data;
        Optional<Context> context;
        InstanceIdentifier<Context> iidContext = InstanceIdentifier.create(Context.class);

        data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iidContext).get();
        if (!data) {
            LOG.warn("No context found into the data store.");
            return new ContextBuilder().build();
        }
        context = transaction.read(LogicalDatastoreType.CONFIGURATION, iidContext).get();
        return context.get();
    }

    public boolean addConnectivityService(ConnectivityService connectivityService) {
        LOG.info("Going to write Connectivity Service into Data Store.");

        try {
            List<ConnectivityService> connectivityServices = readAllConnectivityServices();
            connectivityServices.add(connectivityService);
            updateConnectivityServiceList(connectivityServices);
            return true;
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
            return false;
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    private void updateConnectivityServiceList(List<ConnectivityService> connectivityServices) throws ExecutionException, InterruptedException {
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1 context1 =
                new Context1Builder()
                        .setConnectivityContext(new ConnectivityContextBuilder(getConnectivityContext()).setConnectivityService(connectivityServices).build()).build();

        Context context = new ContextBuilder(getContext(dataBroker)).addAugmentation(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1.class, context1).build();
        InstanceIdentifier<Context> iidContext = InstanceIdentifier.create(Context.class);
        transaction.put(LogicalDatastoreType.CONFIGURATION,  iidContext, context);
        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            throw new ExecutionException(new Throwable("Error during the topology update."));
        }
    }

    private List<ConnectivityService> readAllConnectivityServices() throws ExecutionException, InterruptedException {
        LOG.info("Reading Connectivity services from data store.");
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();
        Boolean data;
        Optional<ConnectivityContext> connectivityContextOpt;
        InstanceIdentifier<ConnectivityContext> iidConnContext = InstanceIdentifier.create(Context.class)
                .augmentation(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1.class)
                .child(ConnectivityContext.class);

        data = transaction.exists(LogicalDatastoreType.CONFIGURATION, iidConnContext).get();
        if (!data) {
            LOG.warn("No connectivity context found into the Data Store.");
            return new ArrayList<>();
        }
        connectivityContextOpt = transaction.read(LogicalDatastoreType.CONFIGURATION, iidConnContext).get();
        ConnectivityContext connectivityContext = connectivityContextOpt.get();

        if(connectivityContext.getConnectivityService()==null)
            return new ArrayList<>();

        return connectivityContext.getConnectivityService();
    }

    public boolean removeConnectivityService(String connectivityServiceId) {
        LOG.info("Going to remove Connectivity Service from Data Store.");
        int index = -1;
        List<String> uuidConnectionsToDelete=new ArrayList<>();

        try {
            List<ConnectivityService> connectivityServices = readAllConnectivityServices();
            for(int i=0; i<connectivityServices.size(); i++){
                if(connectivityServices.get(i).getUuid().getValue().equals(connectivityServiceId)){
                    for(int j=0; j< connectivityServices.get(i).getConnection().size(); j++){
                        uuidConnectionsToDelete.add(connectivityServices.get(i).getConnection().get(j).getConnectionUuid().getValue());
                    }
                    index = i;
                    break;
                }
            }
            if(index==-1){
                LOG.warn("Unable to find connectivity service with UUID "+connectivityServiceId);
                return false;
            }
            connectivityServices.remove(index);
            updateConnectivityServiceList(connectivityServices);
            LOG.info("Going to remove connections related to the connectivity service.");
            for(String uuidConnectionToDelete: uuidConnectionsToDelete)
                deleteConnection(uuidConnectionToDelete);
        }
        catch (ExecutionException e) {
            LOG.error(e.getMessage());
            return false;
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean addConnectionsToConnectivityContext(List<Connection> connectionList) {
        LOG.info("Going to add connections to connectivity context into data store.");
        ConnectivityContext connectivityContextRead = null;
        try {
            connectivityContextRead = getConnectivityContext();
            ConnectivityContext connectivityContext;

            if(connectivityContextRead.getConnection()!=null && connectivityContextRead.getConnection().size()>0){
                LOG.info("Adding new connections to connectivity Context to the existing one.");
                List<Connection> newConnectionList =connectivityContextRead.getConnection();
                newConnectionList.addAll(connectionList);
                connectivityContext = new ConnectivityContextBuilder(connectivityContextRead).setConnection(newConnectionList).build();

            }
            else{
                LOG.info("Adding connections to Connectivity Context.");
                connectivityContext = new ConnectivityContextBuilder(connectivityContextRead).setConnection(connectionList).build();
            }
            final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
            org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1 context1 =
                    new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1Builder()
                            .setConnectivityContext(connectivityContext).build();
            Context context = new ContextBuilder(getContext(dataBroker))
                    .addAugmentation(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1.class, context1).build();
            InstanceIdentifier<Context> iidContext = InstanceIdentifier.create(Context.class);
            transaction.put(LogicalDatastoreType.CONFIGURATION,  iidContext, context);
            transaction.commit().get();
            return true;
        } catch (ExecutionException e) {
            LOG.error(e.getMessage());
            return false;
        } catch (InterruptedException e) {
            LOG.error(e.getMessage());
            return false;
        }
      }

    private void deleteConnection(String connectionId) throws ExecutionException, InterruptedException {
        LOG.info("Removing connection with UUID "+connectionId);
        List<Connection> connectionList = getConnectivityContext().getConnection();
        if(connectionList==null){
            LOG.warn("No connections available");
            return;
        }

        int index = -1;
        for(int i=0; i<connectionList.size(); i++){
            if(connectionList.get(i).getUuid().getValue()==connectionId){
                index = i;
                break;
            }
        }
        if(index==-1){
            LOG.warn("Unable to find connection with UUID "+connectionId);
            return;
        }
        connectionList.remove(index);

        ConnectivityContext connectivityContextRead = getConnectivityContext();
        ConnectivityContext connectivityContext = new ConnectivityContextBuilder(connectivityContextRead).setConnection(connectionList).build();
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1 context1 =
                new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1Builder()
                        .setConnectivityContext(connectivityContext).build();
        Context context = new ContextBuilder(getContext(dataBroker))
                .addAugmentation(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.Context1.class, context1).build();
        InstanceIdentifier<Context> iidContext = InstanceIdentifier.create(Context.class);
        transaction.put(LogicalDatastoreType.CONFIGURATION,  iidContext, context);
        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            throw new ExecutionException(new Throwable("Error during the connectivityContext update."));
        }

    }


}
