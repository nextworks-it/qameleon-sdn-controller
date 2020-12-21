package it.nextworks.qameleon.sbi.netconf_driver;

import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.binding.api.RpcConsumerRegistry;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.finisar.wss.rev200901.Channel;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.finisar.wss.rev200901.ConfigureChannelPlanInput;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.finisar.wss.rev200901.ConfigureChannelPlanInputBuilder;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.finisar.wss.rev200901.Port;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinisarNetconfDriver extends NetconfDriver {
    private static final Logger LOG = LoggerFactory.getLogger(FinisarNetconfDriver.class);

    public FinisarNetconfDriver(MountPointService mountPointService, String finisarId) {
        super(mountPointService,finisarId);
    }

//Used for provisioning app
    public void setChannelPlan(int channel, int port){
        ConfigureChannelPlanInput configureChannelPlanInput=  new ConfigureChannelPlanInputBuilder().setChannelNumber(new Channel(channel)).setPortNumber(new Port(port)).build();
        RpcConsumerRegistry rpcConsumerRegistry = getRpcConsumerRegistry();
    }
    private boolean setChannelPlanRPC(String nodeId,LogicalDatastoreType logicalDatastoreType, int channel, int port){

        return true;
    }


    //Used for topology app
    public void getChannelPlan(){

    }
}
