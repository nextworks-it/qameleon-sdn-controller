package it.nextworks.qameleon.qamProvisioningApp.sbi;

import com.google.common.util.concurrent.ListenableFuture;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.ReadTransaction;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.*;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLink;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLinkBuilder;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNode;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNodeBuilder;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class TopologyAppServiceConsumer {

    private final QameleonTopologyService qameleonTopologyService;
    private static final Logger LOG = LoggerFactory.getLogger(PceRestClient.class);
    private static TopologyAppServiceConsumer topologyAppServiceConsumerInstance = null;
    private final DataBroker dataBroker;

    public TopologyAppServiceConsumer(DataBroker dataBroker, QameleonTopologyService qameleonTopologyService){
        this.qameleonTopologyService = qameleonTopologyService;
        this.dataBroker = dataBroker;
        topologyAppServiceConsumerInstance = this;
    }

    public static TopologyAppServiceConsumer getInstance(){
        if(topologyAppServiceConsumerInstance==null)
            LOG.error("Topology app service consumer has not been started yet.");
        return topologyAppServiceConsumerInstance;
    }

   public boolean removeLightPath(List<String> linkUuidList, int channelNumber) throws ExecutionException, InterruptedException {
        return qameleonTopologyService.removeLightPath(new RemoveLightPathInputBuilder().setChannelNumber(channelNumber).setLinkUuidList(linkUuidList).build()).get().isSuccessful();
    }


    public boolean updateLightPathInfo(List<String> linkUuidList, int channelNumber) throws ExecutionException, InterruptedException {
        UpdateLinkInput updateLinkInput = new UpdateLinkInputBuilder().setChannelNumber(channelNumber).setLinkUuidList(linkUuidList).build();
        ListenableFuture<RpcResult<UpdateLinkOutput>> rpcResultListenableFuture = qameleonTopologyService.updateLink(updateLinkInput);
        return rpcResultListenableFuture.get().isSuccessful();
    }

    public QamLink getQamLink(String linkId){
        ListenableFuture<RpcResult<GetQamLinkOutput>> getQamLinkOutput = qameleonTopologyService.getQamLink(new GetQamLinkInputBuilder().setLinkId(linkId).build());
        try {
            return new QamLinkBuilder(getQamLinkOutput.get().getResult().getLinkOut()).build();

        } catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }

    public QamNode getQamNodeWithCredentials(String nodeId){
            LOG.info("Looking or Qameleon node with UUID "+nodeId);
            final ReadTransaction transaction = dataBroker.newReadOnlyTransaction();

            InstanceIdentifier<QamTopologyCont> instanceIdentifier = InstanceIdentifier.create(QamTopologyCont.class);

            Boolean data;
            try {
                data = transaction.exists(LogicalDatastoreType.CONFIGURATION, instanceIdentifier).get();
                if(!data){
                    LOG.warn("No Qam Topology found into the tree.");
                    return null;
                }
                Optional<QamTopologyCont> opt =
                        transaction.read(LogicalDatastoreType.CONFIGURATION, instanceIdentifier).get();
                QamTopologyCont qamTopologyCont = opt.get();
                if(qamTopologyCont==null){
                    LOG.warn("qamTopologyCont not available");
                    return null;
                }
                for(QamNode qamNode: qamTopologyCont.getQamNode()){
                    LOG.info("UUID Node is "+qamNode.getUuid().getValue());
                    if(qamNode.getUuid().getValue().equals(nodeId)){
                        return qamNode;
                    }
                }
            }
            catch (final InterruptedException | ExecutionException e) {
                LOG.error(e.getMessage());
            }
        return null;
        }

    public QamNode getQamNode(String nodeId){

        ListenableFuture<RpcResult<GetQamNodeOutput>> getQamNodeOutput =qameleonTopologyService.getQamNode(new GetQamNodeInputBuilder().setNodeId(nodeId).build());

        try {
            return new QamNodeBuilder(getQamNodeOutput.get().getResult().getNodeOut()).build();
        }  catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }
}
