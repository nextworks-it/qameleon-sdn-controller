package it.nextworks.qameleon.sbi.api.provisioning;

public interface LightPathProvisioning {
    public boolean setupLightPath(int channel, String portSrc, String portDst, double bandwidthChannel);
    public boolean removeLightPath(int channel, String portSrc, String portDst, double bandwidthChannel);
    public boolean removeAllLightPaths();
}
