package it.nextworks.qameleon.qamProvisioningApp.sbi;

import com.google.common.util.concurrent.ListenableFuture;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.*;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLink;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLinkBuilder;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNode;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNodeBuilder;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TopologyAppServiceConsumer {

    private final QameleonTopologyService qameleonTopologyService;
    private static final Logger LOG = LoggerFactory.getLogger(PceRestClient.class);
    private static TopologyAppServiceConsumer topologyAppServiceConsumerInstance = null;


    public TopologyAppServiceConsumer(QameleonTopologyService qameleonTopologyService){
        this.qameleonTopologyService = qameleonTopologyService;
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

    public QamNode getQamNode(String nodeId){

        ListenableFuture<RpcResult<GetQamNodeOutput>> getQamNodeOutput =qameleonTopologyService.getQamNode(new GetQamNodeInputBuilder().setNodeId(nodeId).build());
        try {
            return new QamNodeBuilder( getQamNodeOutput.get().getResult().getNodeOut()).build();
        }  catch (Exception e) {
            LOG.error(e.getMessage());
            return null;
        }
    }
}
