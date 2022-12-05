package it.nextworks.qameleon.sbi.api.provisioning.impl;

import it.nextworks.common.TwoWaysChannelFreqTranslator;
import it.nextworks.qameleon.sbi.api.provisioning.LightPathProvisioning;
import it.nextworks.qameleon.sbi.netconf_driver.lumentumNetconfDriver.LumentumNetconfDriver;
import it.nextworks.qameleon.sbi.netconf_driver.lumentumNetconfDriver.LumentumUtil;
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
import java.util.ArrayList;
import java.util.List;

public class LumentumProvisioning implements LightPathProvisioning {
    private LumentumNetconfDriver lumentumNetconfDriver;
    private static final Logger LOG = LoggerFactory.getLogger(LumentumProvisioning.class);
    private final String DN_MUX_CONNECTION_PREFIX = "ne=1;chassis=1;card=1;module=1;connection=";
    private final String DN_DEMUX_CONNECTION_PREFIX = "ne=1;chassis=1;card=1;module=2;connection=";
    private final double MINIMUM_CHANNEL_WIDTH_GHZ = 37.5;
    private final double MINIMUM_SLOT_WIDTH_GHZ = 6.25;
    private final String DN_MUX_DEMUX_PREFIX = "ne=1;chassis=1;card=1;port=";
    private final int OUTPUT_PORT_MUX = 4201;
    private final int INPUT_PORT_DEMUX = 5101;
    private final int LINE_IN_OUT_ID = 3001;

    public LumentumProvisioning(MountPointService mountPointService, String lumentumId) {
        lumentumNetconfDriver = new LumentumNetconfDriver(mountPointService,lumentumId);
    }

    private List<Integer> connectionIdUsed(){
        LOG.info("Checking connection ID used so far");
        List<Integer> connectionIdList = new ArrayList<>();
        for(Connection connection:lumentumNetconfDriver.getLumentumConnections().getConnection()){
            connectionIdList.add(LumentumUtil.dnToHasMap( connection.getDn()).get("connection"));
        }
        return connectionIdList;
    }

    private Double roundToSlotFrequency(Double freq){
        double freqTmp = 191325;
        int slotCount = 1;
        while(freqTmp<freq){
            freqTmp += MINIMUM_SLOT_WIDTH_GHZ;
            slotCount++;
        }
        LOG.info("Round to slot #"+slotCount+ " whose frequency is "+freqTmp);
        return freqTmp;
    }



    public boolean setupLightPath(int channel, String portSrc, String portDst, double bandwidthChannel){
        LOG.info("Processing request for creating cross-connection from port (src) "+portSrc+ " to port (dst) "+portDst+" using channel "+channel);
        TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator();
        Double centralFreqGhz= twoWaysChannelFreqTranslator.channelToFrequency(channel);

        Double startingFreqGhz = centralFreqGhz-bandwidthChannel/2;
        Double endingFreqGhz = centralFreqGhz+bandwidthChannel/2;

        LOG.info("Optical channel: "+channel);
        LOG.info("Starting frequency (Ghz) : "+startingFreqGhz);
        LOG.info("Central frequency (Ghz) : "+centralFreqGhz);
        LOG.info("Ending frequency (Ghz) : "+endingFreqGhz);

        startingFreqGhz = roundToSlotFrequency(startingFreqGhz);
        endingFreqGhz = roundToSlotFrequency(endingFreqGhz);

        LOG.info("Starting frequency rounded (Ghz) "+startingFreqGhz);
        LOG.info("Ending frequency rounded (Ghz) : "+endingFreqGhz);

        Double deltaFreqGhz = endingFreqGhz-startingFreqGhz;
        LOG.info("Total spectrum is (Ghz) : "+deltaFreqGhz);

        if(deltaFreqGhz<MINIMUM_CHANNEL_WIDTH_GHZ){
            LOG.error("The frequency width ("+deltaFreqGhz+" Ghz) is less than the minimum allowed ("+MINIMUM_CHANNEL_WIDTH_GHZ+" Ghz)");
            return false;
        }
        List<Integer> connectionIdUsed = connectionIdUsed();
        Integer connectionMuxId = 1;
        LOG.info("Number of connections ID used: "+connectionIdUsed.size());

        int portDstInt = -1;

        try {
            portDstInt = Integer.parseInt(portDst);
        } catch (NumberFormatException e) {
            LOG.error("Cannot parse "+portDst+ " to an integer value");
            LOG.error(e.getMessage());
            return false;
        }

        if(portDstInt==LINE_IN_OUT_ID) {
            while(connectionIdUsed.contains(connectionMuxId)) {
                LOG.warn("Connection ID "+connectionMuxId+ " already used");
                connectionMuxId ++;
            }

            connectionIdUsed.add(connectionMuxId);
            LOG.info("Connection MUX ID to assign is "+connectionMuxId);

            if (!setupCrossConnection("Conn MUX lightpath channel " + channel, Integer.parseInt(portSrc), OUTPUT_PORT_MUX, startingFreqGhz, endingFreqGhz, MaintenanceState.InService, connectionMuxId, DN_MUX_CONNECTION_PREFIX)) {
                LOG.error("Error during cross connection creation within MUX");
                return false;
            }
            LOG.info("Cross-connection within MUX successfully setup");
        }

        int portSrcInt = -1;
        try {
            portSrcInt = Integer.parseInt(portSrc);
        } catch (NumberFormatException e) {
            LOG.error("Cannot parse "+portSrc+ " to an integer value");
            LOG.error(e.getMessage());
            return false;
        }

        if(portSrcInt==LINE_IN_OUT_ID){
            Integer connectionDemuxId = 1;

            while(connectionIdUsed.contains(connectionDemuxId)) {
                LOG.warn("Connection ID "+connectionDemuxId+ " already used");
                connectionDemuxId ++;
            }

            LOG.info("Connection DEMUX ID to assign is "+connectionDemuxId);
            if(!setupCrossConnection("Conn DEMUX lightpath channel "+channel, INPUT_PORT_DEMUX, Integer.parseInt(portDst), startingFreqGhz,endingFreqGhz, MaintenanceState.InService, connectionDemuxId, DN_DEMUX_CONNECTION_PREFIX)){
                LOG.error("Error during cross connection creation within DEMUX");
                return false;
            }
            LOG.info("Cross-connection within DEMUX successfully setup");
        }

        //LOG.error("Either source or destination port must be equal to "+LINE_IN_OUT_ID);

        return true;
    }



    @Override
    public boolean removeLightPath(int channel, String portSrc, String portDst, double bandwidthChannel) {
        TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator();
        LOG.info("Going to remove cross-connection within Lumentum Device for channel "+channel);

        for(Connection connection:lumentumNetconfDriver.getLumentumConnections().getConnection()){
            double startingFreq = connection.getConfig().getStartFreq().getValue().doubleValue();
            double endingFreq =connection.getConfig().getEndFreq().getValue().doubleValue();
            double centralFreq = (startingFreq + endingFreq)/2;
            int moduleId = LumentumUtil.dnToHasMap( connection.getDn()).get("module");
            int channelTmp =twoWaysChannelFreqTranslator.frequencyToChannel(centralFreq);
            int connectionId = LumentumUtil.dnToHasMap( connection.getDn()).get("connection");

            LOG.info("starting freq "+startingFreq);
            LOG.info("ending freq "+endingFreq);
            LOG.info("central freq "+centralFreq);
            LOG.info("moduleId "+moduleId);
            LOG.info("channelTmp "+channelTmp);
            LOG.info("connectionId "+connectionId);

            if(channelTmp==channel && moduleId==1){
                LOG.info("Going to remove cross-connection within MUX");
                DistinguishedName dnConnectionMux = new DistinguishedName(DN_MUX_CONNECTION_PREFIX+connectionId);
                if(!lumentumNetconfDriver.removeCrossConnection(dnConnectionMux)){
                    LOG.error("Error removing cross connection with id "+connectionId);
                    return false;
                }
                LOG.info("Connection with ID "+connectionId+" successfully removed from MUX");
            }
            if(channelTmp==channel && moduleId==2){
                LOG.info("Going to remove cross-connection within DEMUX");
                DistinguishedName dnConnectionDemux = new DistinguishedName(DN_DEMUX_CONNECTION_PREFIX+connectionId);
                if(!lumentumNetconfDriver.removeCrossConnection(dnConnectionDemux)){
                    LOG.error("Error removing cross connection with id " + connectionId);
                   return false;
                }
                LOG.info("Connection with ID "+connectionId+" successfully removed from DEMUX");
            }
        }
        return true;
    }

    @Override
    public boolean removeAllLightPaths() {
        LOG.info("Pretending to have been removed all lightPaths into Lumentum device.");
        return true;
    }


    private boolean setupCrossConnection(String configName, int inputPort, int outputPort, double startFreq, double endFreq, MaintenanceState maintenanceState, Integer connectionId, final String dnPrefix){
        if(startFreq>=endFreq){
            LOG.error("Starting frequency cannot be either equal or higher than ending frequency");
            return false;
        }

        BigDecimal startFreqGhz = new BigDecimal(startFreq);
        BigDecimal endFreqGhz = new BigDecimal(endFreq);
        DwdmFrequencyRangeGhz dwdmFrequencyRangeGhzStart = new DwdmFrequencyRangeGhz(startFreqGhz.setScale(2));
        DwdmFrequencyRangeGhz dwdmFrequencyRangeGhzEnd = new DwdmFrequencyRangeGhz(endFreqGhz.setScale(2));
        Config config = new ConfigBuilder().setCustomName(configName)
                .setMaintenanceState(maintenanceState)
                .setInputPortReference(getDnMux(inputPort))
                .setOutputPortReference(getDnMux(outputPort))
                .setStartFreq(dwdmFrequencyRangeGhzStart)
                .setEndFreq(dwdmFrequencyRangeGhzEnd)
                .setBlocked(false)

                .build();

        String distinguishName = dnPrefix +connectionId;
        Connection lumentumCrossConnection = new ConnectionBuilder()
                .setConfig(config)
                .setDn(new DistinguishedName(distinguishName))
                .build();

        LOG.info("Going to setup cross-connection within Lumentum device. Cross-connection main info below");
        LOG.info("Distinguish Name (DN)  "+distinguishName);
        LOG.info("Starting and ending frequency (Ghz): "+startFreqGhz+" and "+endFreqGhz);
        LOG.info("Source and destination ports identifier: "+inputPort+ " and "+outputPort);
        return lumentumNetconfDriver.setupLumentumCrossConnection(lumentumCrossConnection);
    }

    public boolean configureCcConnectionDemux(int connectionId, int ouputPort){
        ConfigureCcConnectionInput configureCcConnection = new ConfigureCcConnectionInputBuilder()
                .setDn(new DistinguishedName(DN_DEMUX_CONNECTION_PREFIX +connectionId))
                .setInputPortReference(new DistinguishedName("ne=1;chassis=1;card=1;port=5102"))
                .setOutputPortReference(new DistinguishedName("ne=1;chassis=1;card=1;port=52"+ LumentumUtil.leftPadZero(ouputPort)))
                .setCustomInputId(new IdentityCode(("DEMUX CC Connection "+connectionId))).build();
                return lumentumNetconfDriver.ConfigureCcConnectionRpc(configureCcConnection);
    }

    public boolean configureCcConnectionMux(int connectionId, int inputPort){
        ConfigureCcConnectionInput configureCcConnection = new ConfigureCcConnectionInputBuilder()
                .setDn(new DistinguishedName(DN_MUX_CONNECTION_PREFIX +connectionId))
                .setInputPortReference(new DistinguishedName("ne=1;chassis=1;card=1;port=41"+ LumentumUtil.leftPadZero(inputPort)))
                .setOutputPortReference(new DistinguishedName("ne=1;chassis=1;card=1;port=4202"))
                .setCustomInputId(new IdentityCode(("MUX CC Connection "+connectionId))).build();
                return lumentumNetconfDriver.ConfigureCcConnectionRpc(configureCcConnection);
    }

    private DistinguishedName getDnMuxInput(int inputPort){
        return new DistinguishedName(DN_MUX_DEMUX_PREFIX+ LumentumUtil.MUX_IN_PORT_PREFIX+ LumentumUtil.leftPadZero(inputPort));

    }

    private DistinguishedName getDnDemuxOutput(int outputNumber){
        return new DistinguishedName(DN_MUX_DEMUX_PREFIX+ LumentumUtil.DEMUX_OUT_PORT_PREFIX+ LumentumUtil.leftPadZero(outputNumber));
        }
    private DistinguishedName getDnMux(int portNumber){
        String dnBuilt = DN_MUX_DEMUX_PREFIX+ portNumber;
        return new DistinguishedName(dnBuilt);
    }

}
