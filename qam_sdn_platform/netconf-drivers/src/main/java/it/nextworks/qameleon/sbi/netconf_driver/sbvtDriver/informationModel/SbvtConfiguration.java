package it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel;

public class SbvtConfiguration {


    private ModulationFormat modulationFormat;
    private BaudRate baudRate;
    private float frequency;
    private boolean laserEnabled;
    private Double edfaOutputPower;


    public SbvtConfiguration(){}
    public SbvtConfiguration(ModulationFormat modulationFormat, BaudRate baudRate, float frequency, boolean laserEnabled, Double edfaOutputPower) {
        this.modulationFormat = modulationFormat;
        this.baudRate = baudRate;
        this.frequency = frequency;
        this.laserEnabled = laserEnabled;
        this.edfaOutputPower = edfaOutputPower;
    }

    public ModulationFormat getModulationType() {
        return modulationFormat;
    }

    public BaudRate getBaudRate() {
        return baudRate;
    }

    public float getFrequency() {
        return frequency;
    }

    public boolean isLaserEnabled() {
        return laserEnabled;
    }

    public Double getEdfaOutputPower() {
        return edfaOutputPower;
    }

}