package it.nextworks.qameleon.sbi.netconf_driver.wssNetconfDriver;

import com.google.common.util.concurrent.FluentFuture;
import it.nextworks.qameleon.sbi.netconf_driver.DummyNetconfDriver;
import it.nextworks.qameleon.sbi.netconf_driver.NetconfDriver;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.CommitInfo;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.wss.rev180508.FrequencyGhzType;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.wss.rev180508.Wss;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.wss.rev180508.WssBuilder;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.wss.rev180508.wss.config.WssPorts;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.wss.rev180508.wss.config.WssPortsBuilder;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.wss.rev180508.wss.port.config.PortFrequencyInfo;
import org.opendaylight.yang.gen.v1.http.org.nextworks.qameleon.wss.rev180508.wss.port.config.PortFrequencyInfoBuilder;
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

public class WssNetconfDriver extends NetconfDriver {
    private double MIN_FREQ_WIDTH_GHZ = 12.5;
    private final Short N_DEFAULT = 0;
    private final FrequencyGhzType CENTRAL_FREQ_SLOT_GRAN = new FrequencyGhzType(new BigDecimal(MIN_FREQ_WIDTH_GHZ));
    private final FrequencyGhzType BASE_CENTRAL_FREQ_GRAN = new FrequencyGhzType(new BigDecimal(MIN_FREQ_WIDTH_GHZ));

    private final Long WSS_ID =1l;

    public WssNetconfDriver(MountPointService mountPointService, String NODE_ID) {
        super(mountPointService, NODE_ID);
    }


 private static final Logger LOG = LoggerFactory.getLogger(DummyNetconfDriver.class);



    public boolean configureWss(double centralFrequency, double frequencyWidth){
        Wss wss = getWss(centralFrequency, frequencyWidth);
        return writeConfigRemoteDs(wss);
    }

    private Wss getWss(double centralFrequency, double frequencyWidth){
        String mString = String.valueOf(frequencyWidth/MIN_FREQ_WIDTH_GHZ); //E.g. 50.0/12.5 = 4.0 ==> 4
        LOG.info(mString);

        PortFrequencyInfo portFrequencyInfo = new PortFrequencyInfoBuilder()
                .setBaseCentralFrequency(new FrequencyGhzType(new BigDecimal(centralFrequency)))
                .setN(N_DEFAULT)
                .setM(Short.valueOf(mString))
                .setBaseCentralFrequencyGranularity(BASE_CENTRAL_FREQ_GRAN)
                .setCentralFrequencySlotGranularity(CENTRAL_FREQ_SLOT_GRAN)
                .build();


        WssPorts wssPorts = new WssPortsBuilder().setPortFrequencyInfo(portFrequencyInfo).build();
        List<WssPorts> wssPortsList = new ArrayList<>();
        wssPortsList.add(wssPorts);
        return new WssBuilder().setWssId(WSS_ID).setWssPorts(wssPortsList).build();
    }


    private boolean writeConfigRemoteDs(Wss wss){
        DataBroker dataBroker = this.getDataBroker();
        InstanceIdentifier<Wss> iid = InstanceIdentifier.create(Wss.class);

        LOG.info("Updating remote data store of WSS SDN Agent whose ID is "+getNodeId());
        final WriteTransaction writeTransaction = dataBroker.newWriteOnlyTransaction();
        writeTransaction.merge(LogicalDatastoreType.CONFIGURATION, iid, wss);

        // commit:
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

}
