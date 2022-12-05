package it.nextworks.qameleon.sbi.netconf_driver;

import com.google.common.util.concurrent.FluentFuture;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.CommitInfo;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.FrequencyGhzType;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.Sbvt;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.SbvtBuilder;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.optical.flow.carrier.config.FrequencyInfo;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.optical.flow.carrier.config.FrequencyInfoBuilder;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.optical.flow.config.OpticalFlowCarriers;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.optical.flow.config.OpticalFlowCarriersBuilder;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.sbvt.OpticalFlows;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.sbvt.rev180423.sbvt.OpticalFlowsBuilder;
import org.opendaylight.yang.gen.v1.sim.dev.rev201215.cross.connections.top.CrossConnections;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.opendaylight.yangtools.yang.common.RpcError;
import org.opendaylight.yangtools.yang.common.RpcResultBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NllNetconfDriver extends NetconfDriver {
    private static final Logger LOG = LoggerFactory.getLogger(NllNetconfDriver.class);

    public NllNetconfDriver(MountPointService mountPointService, String nllId) {
        super(mountPointService,nllId);
    }

//Used for provisioning app
    public Sbvt getSbvtInfo(double frequencyGhz){
        frequencyGhz = Math.round(frequencyGhz);
        LOG.info("Frequency to set: "+frequencyGhz);

        BigDecimal bd = new BigDecimal(frequencyGhz);
        bd = bd.setScale(0);
        LOG.info("Rounded Frequency to set: "+bd.doubleValue());
        FrequencyInfo frequencyInfo = new FrequencyInfoBuilder().
                setBaseCentralFrequency(new FrequencyGhzType(bd)).
                //setBaseCentralFrequencyGranularity(new FrequencyGhzType(new BigDecimal(6.25))).
                setCentralFrequencySlotGranularity(new FrequencyGhzType(new BigDecimal(12.5))).
                setM((short) 1).
                setN((short) 0).
                build();


        OpticalFlowCarriers opticalFlowCarriers = new OpticalFlowCarriersBuilder().setOfcId(1L).setFrequencyInfo(frequencyInfo).build();
        List<OpticalFlowCarriers> opticalFlowCarriersList = new ArrayList<>();
        opticalFlowCarriersList.add(opticalFlowCarriers);
        OpticalFlows opticalFlows = new OpticalFlowsBuilder().setOfId(1L).setOpticalFlowCarriers(opticalFlowCarriersList).build();
        List<OpticalFlows> opticalFlowsList = new ArrayList<>();
        opticalFlowsList.add(opticalFlows);
        Sbvt sbvt = new SbvtBuilder().setSbvtId(1).setOpticalFlows(opticalFlowsList).build();
        return sbvt;
    }

    public boolean setSbvtChannel(double frequencyGhz){
        Sbvt sbvt = getSbvtInfo(frequencyGhz);

        InstanceIdentifier<Sbvt> iid = InstanceIdentifier.create(Sbvt.class);

        LOG.info("Updating remote data store of SBVT SDN Agent whose ID is "+getNodeId());

        final WriteTransaction writeTransaction = this.getDataBroker().newWriteOnlyTransaction();
        writeTransaction.merge(LogicalDatastoreType.CONFIGURATION, iid, sbvt);

        FluentFuture<? extends CommitInfo> result = writeTransaction.commit();

        try {
            result.get();
        } catch (InterruptedException | ExecutionException e) {
            LOG.error(e.getMessage());
            RpcResultBuilder<CrossConnections> rpcResultBuilder = RpcResultBuilder.failed();
            rpcResultBuilder.withError(RpcError.ErrorType.APPLICATION, "Write transaction error");
            return false;
        }
        return true;
    }

    public void getChannelPlan(){

    }
}
