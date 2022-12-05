package it.nextworks.qameleon.topologyApp.service;

import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.ReadTransaction;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.QamTopology;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.QamTopologyCont;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.QamTopologyContBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class QamTopologyInventoryService {
    private final DataBroker dataBroker;
    private static final Logger LOG = LoggerFactory.getLogger(QamTopologyInventoryService.class);


    public QamTopologyInventoryService(final DataBroker dataBroker) {
        this.dataBroker = dataBroker;
    }

    private InstanceIdentifier<QamTopologyCont> getInstanceIdentifierTapiTopology(){

        InstanceIdentifier<QamTopologyCont> instanceIdentifier =
                InstanceIdentifier.create(QamTopologyCont.class);
        return instanceIdentifier;
    }


    public boolean writeQamTopologyIntoTree(QamTopology qamTopology){
        //LOG.info("Writing Qam topology into data tree...");
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();

        String topologyUuid = qamTopology.getTopologyUuid();
        InstanceIdentifier<QamTopologyCont> instanceIdentifier = getInstanceIdentifierTapiTopology();
        QamTopologyCont qamTopologyCont = new QamTopologyContBuilder()
                .setTopologyUuid(topologyUuid)
                .setQamLink(qamTopology.getQamLink())
                .setQamNode(qamTopology.getQamNode())
                .build();

        transaction.put(LogicalDatastoreType.CONFIGURATION,  instanceIdentifier, qamTopologyCont);

        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            return false;
        }
        return true;
    }


    public QamTopology getQamTapiTopologyFromTree(){
        //LOG.info("Reading Qam topology from data tree...");
        final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();

        InstanceIdentifier<QamTopologyCont> instanceIdentifier = getInstanceIdentifierTapiTopology();

        Boolean data;
        try {
            data = transaction.exists(LogicalDatastoreType.CONFIGURATION, instanceIdentifier).get();
            if(!data){
                LOG.warn("No Qam Topology found into the tree.");
                return null;
            }
            Optional<QamTopologyCont> opt =
                    transaction.read(LogicalDatastoreType.CONFIGURATION, instanceIdentifier).get();
            return  opt.get();
        } catch (final InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            return null;
        }
    }


    public boolean deleteQamTopologyFromTree(){
        LOG.info("Deleting Qam topology from data tree...");
        InstanceIdentifier<QamTopologyCont> instanceIdentifier = getInstanceIdentifierTapiTopology();
        final WriteTransaction transaction = dataBroker.newWriteOnlyTransaction();

        transaction.delete(LogicalDatastoreType.CONFIGURATION, instanceIdentifier);

        try {
            transaction.commit().get();
        } catch (final InterruptedException | ExecutionException e) {
            e.printStackTrace();;
            return false;
        }
        return true;
    }
}
