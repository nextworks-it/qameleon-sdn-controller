package it.nextworks.qameleon.sbi.api.provisioning.impl;

import it.nextworks.common.TwoWaysChannelFreqTranslator;
import it.nextworks.qameleon.sbi.api.provisioning.LightPathProvisioning;
import it.nextworks.qameleon.sbi.netconf_driver.DummyNetconfDriver;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyProvisioning implements LightPathProvisioning {
    private static final Logger LOG = LoggerFactory.getLogger(DummyProvisioning.class);
    private DummyNetconfDriver dummyNetconfDriver;
    private String lookupTableFilename;

    public DummyProvisioning(MountPointService mountPointService, String nodeId, String lookupTableFilename){
        dummyNetconfDriver = new DummyNetconfDriver(mountPointService, nodeId);
        this.lookupTableFilename = lookupTableFilename;
    }

    @Override
    public boolean setupLightPath(int channel, String portSrc, String portDst) {
        if(!isValid(channel, portSrc, portDst)) {
            LOG.info("Your lightpath setup request is not valid. Please check the fields.");
            return false;
        }
        TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator(lookupTableFilename);
        Double frequencyMhz = twoWaysChannelFreqTranslator.channelToFrequency(channel);
        Double wavelengthNm = twoWaysChannelFreqTranslator.getWavelengthNanometer(frequencyMhz);
        LOG.debug("Channel "+channel+" corresponds to frequency "+frequencyMhz+" Ghz");
        LOG.debug("Channel "+channel+" corresponds to "+wavelengthNm+ " nm");
        return dummyNetconfDriver.createCrossConnection(channel,wavelengthNm, portSrc,portDst);
    }

    @Override
    public boolean removeLightPath(int channel, String portSrc, String portDst) {
        if(!isValid(channel, portSrc, portDst)) {
            LOG.info("Your setup lightpath request is not valid. Please check the fields.");
            return false;
        }
        return dummyNetconfDriver.removeCrossConnection(channel,portSrc,portDst);
    }

    @Override
    public boolean removeAllLightPaths() {
        return dummyNetconfDriver.removeAllCrossConnection();
    }

    private boolean isValid(int channel, String portSrc, String portDst){
        if(channel <0)
            return false;
        if(portSrc==null && portDst==null)
            return false;
        return true;
    }

}
