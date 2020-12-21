package it.nextworks.qameleon.sbi.api.provisioning.impl;

import it.nextworks.qameleon.sbi.api.provisioning.LightPathProvisioning;

//TODO to be implemented during the real-device integration phase
public class FinisarProvisioning implements LightPathProvisioning {
    @Override
    public boolean setupLightPath(int channel, String portSrc, String portDst) {

        return true;
    }

    @Override
    public boolean removeLightPath(int channel, String portSrc, String portDst) {

        return true;
    }

    @Override
    public boolean removeAllLightPaths() {

        return true;
    }
}
