package it.nextworks.qameleon.sbi.api.provisioning.impl;

import it.nextworks.common.TwoWaysChannelFreqTranslator;
import it.nextworks.qameleon.sbi.api.provisioning.LightPathProvisioning;
import it.nextworks.qameleon.sbi.netconf_driver.NllNetconfDriver;
import org.opendaylight.mdsal.binding.api.MountPointService;

public class NllProvisioning implements LightPathProvisioning {
    private NllNetconfDriver nllNetconfDriver;

    public NllProvisioning(MountPointService mountPointService, String nllId){
        nllNetconfDriver = new NllNetconfDriver(mountPointService, nllId);
    }
    @Override
    public boolean setupLightPath(int channel, String portSrc, String portDst, double bandwidthChannel) {
        TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator();
        Double centralFreqGhz= twoWaysChannelFreqTranslator.channelToFrequency(channel);
        return nllNetconfDriver.setSbvtChannel(centralFreqGhz);
    }

    @Override
    public boolean removeLightPath(int channel, String portSrc, String portDst, double bandwidthChannel) {

        return true;
    }

    @Override
    public boolean removeAllLightPaths() {

        return true;
    }
}
