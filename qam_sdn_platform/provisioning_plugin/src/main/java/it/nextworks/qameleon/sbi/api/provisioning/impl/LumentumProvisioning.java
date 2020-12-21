package it.nextworks.qameleon.sbi.api.provisioning.impl;

import it.nextworks.common.TwoWaysChannelFreqTranslator;
import it.nextworks.qameleon.sbi.netconf_driver.LumentumNetconfDriver;
import it.nextworks.qameleon.sbi.api.provisioning.LightPathProvisioning;
import it.nextworks.qameleon.sbi.netconf_driver.LumentumUtil;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.ConfigureCcConnectionInput;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.ConfigureCcConnectionInputBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.connections.Connection;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.connections.ConnectionBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.top.Config;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.top.ConfigBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.DistinguishedName;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.DwdmFrequencyRangeGhz;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.IdentityCode;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.MaintenanceState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;

public class LumentumProvisioning implements LightPathProvisioning {
    LumentumNetconfDriver lumentumNetconfDriver;
    private static final Logger LOG = LoggerFactory.getLogger(LumentumProvisioning.class);
    private final String DN_MUX_CONNECTION_PREFIX = "ne=1;card=1;chassis=1;module=1;connection=";
    private final String DN_DEMUX_CONNECTION_PREFIX = "ne=1;card=1;chassis=1;module=2;connection=";
    private final double MINIMUM_CHANNEL_WIDTH_GHZ = 37.5;
    private final double MINIMUM_SLOT_WIDTH_GHZ = 6.25;
    private final String DN_MUX_DEMUX_PREFIX = "ne=1;chassis=1;card=1;port=";
    private final String FILENAME_LOOKUP_TABLE;

    public LumentumProvisioning(MountPointService mountPointService, String lumentumId, String filenameLookupTable) {
        FILENAME_LOOKUP_TABLE=filenameLookupTable;
        lumentumNetconfDriver = new LumentumNetconfDriver(mountPointService,lumentumId);
    }

    @Override//TODO to be tested
    public boolean setupLightPath(int channel, String portSrc, String portDst) {
        TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator(FILENAME_LOOKUP_TABLE);
        Double centralFreq= twoWaysChannelFreqTranslator.channelToFrequency(channel);
        Double centralFreq2 = twoWaysChannelFreqTranslator.channelToFrequency(channel);
        Double deltaFreq = centralFreq2-centralFreq;
        Double startingFreq = centralFreq-deltaFreq/2;
        //TODO convert the channel, src and dst port into the lumentum corresponding one.
        String connectionId = portSrc+ portDst+ channel;
        boolean crossConnectionSetupSuccess = createConnection(String.valueOf(portSrc), Integer.valueOf(portSrc), Integer.valueOf(portDst), startingFreq,2, MaintenanceState.InService, connectionId);
        LOG.info("Pretending to have been setup a cross connection into Lumentum device.");
        return crossConnectionSetupSuccess;
    }

    @Override
    public boolean removeLightPath(int channel, String portSrc, String portDst) {
        LOG.info("Pretending to have been removed cross connection(s) into Lumentum device.");
        return true;
    }

    @Override
    public boolean removeAllLightPaths() {
        LOG.info("Pretending to have been removed all lightPaths into Lumentum device.");
        return true;
    }


    //Used by provisioning
    private boolean createConnection(String configName, int inputPort, int outputPort, double startFreq, int slotCount, MaintenanceState maintenanceState, String connectionId){
        if(slotCount<0 || slotCount>762){//It is 762 and not  768 because 37.5 is the minimum with already allocated.
            LOG.error("Slot count cannot be lower than 0 or greater than 762");
            return false;
        }
        double endFreq = startFreq+MINIMUM_CHANNEL_WIDTH_GHZ+slotCount*MINIMUM_SLOT_WIDTH_GHZ;
        //TODO missing the amplifier info. To be added ??
        BigDecimal startFreqGhz = new BigDecimal(startFreq);
        BigDecimal endFreqGhz = new BigDecimal(endFreq);
        DwdmFrequencyRangeGhz dwdmFrequencyRangeGhzStart = new DwdmFrequencyRangeGhz(startFreqGhz);
        DwdmFrequencyRangeGhz dwdmFrequencyRangeGhzEnd = new DwdmFrequencyRangeGhz(endFreqGhz);
        Config config = new ConfigBuilder().setCustomName(configName)
                .setMaintenanceState(maintenanceState)
                .setInputPortReference(getDnMuxInput(inputPort))
                .setOutputPortReference(getDnDemuxOutput(outputPort))
                .setStartFreq(dwdmFrequencyRangeGhzStart)
                .setEndFreq(dwdmFrequencyRangeGhzEnd)
                .build();

        Connection connection = new ConnectionBuilder()
                .setConfig(config)
                .setDn(new DistinguishedName(DN_MUX_CONNECTION_PREFIX +connectionId)).build();

        String nodeSrcId = LumentumUtil.MUX_IN_PORT_PREFIX + LumentumUtil.leftPadZero(inputPort);
        String nodeDstId = LumentumUtil.DEMUX_OUT_PORT_PREFIX + LumentumUtil.leftPadZero(outputPort);
        String linkId = nodeSrcId+"-"+nodeDstId+"-"+startFreq+"-"+endFreq;
        //TODO the connection object must be sent using the corresponding RPC to the Lumentum device using NETCONF
        //Once send the RPC, perform a NETCONF get request to connectivity in order to see if the connection has been setup. TODO
        return true;

    }

    private boolean configureCcConnectionDemux(int connectionId, int ouputPort){
        ConfigureCcConnectionInput configureCcConnection = new ConfigureCcConnectionInputBuilder()
                .setDn(new DistinguishedName(DN_DEMUX_CONNECTION_PREFIX +connectionId))
                .setInputPortReference(new DistinguishedName("ne=1;chassis=1;card=1;port=5102"))
                .setOutputPortReference(new DistinguishedName("ne=1;chassis=1;card=1;port=52"+ LumentumUtil.leftPadZero(ouputPort)))
                .setCustomInputId(new IdentityCode(("CC DeMux"))).build();
        return false;
    }

    private boolean configureCcConnectionMux(int connectionId, int inputPort){
        ConfigureCcConnectionInput configureCcConnection = new ConfigureCcConnectionInputBuilder()
                .setDn(new DistinguishedName(DN_MUX_CONNECTION_PREFIX +connectionId))
                .setInputPortReference(new DistinguishedName("ne=1;chassis=1;card=1;port=41"+ LumentumUtil.leftPadZero(inputPort)))
                .setOutputPortReference(new DistinguishedName("ne=1;chassis=1;card=1;port=4202"))
                .setCustomInputId(new IdentityCode(("CC Mux"))).build();
        return false;
    }

    private DistinguishedName getDnMuxInput(int inputPort){
        DistinguishedName distinguishedName = new DistinguishedName(DN_MUX_DEMUX_PREFIX+ LumentumUtil.MUX_IN_PORT_PREFIX+ LumentumUtil.leftPadZero(inputPort));
        return distinguishedName;
    }

    private DistinguishedName getDnDemuxOutput(int outputNumber){
        DistinguishedName distinguishedName = new DistinguishedName(DN_MUX_DEMUX_PREFIX+ LumentumUtil.DEMUX_OUT_PORT_PREFIX+ LumentumUtil.leftPadZero(outputNumber));
        return distinguishedName;

    }

}
