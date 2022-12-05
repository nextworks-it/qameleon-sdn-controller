package it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel;

public class SbvtConfigurationRead extends SbvtConfiguration{
    private Double monitEdfaInputPower;
    private Double monitFrequency;

    private Double monitOsnr;
    private Long lastUpdate;
    public SbvtConfigurationRead(ModulationFormat modulationFormat, BaudRate baudRate, float frequency, boolean laserEnabled, Double edfaOutputPower) {
        super(modulationFormat, baudRate, frequency, laserEnabled, edfaOutputPower);
    }

    public SbvtConfigurationRead(){
        super();

    }
    public SbvtConfigurationRead(ModulationFormat modulationFormat, BaudRate baudRate,
                                 float frequency, boolean laserEnabled,
                                 Double monitEdfaInputPower,
                                 Double monitEdfaOutputPower,
                                Double monitFrequency,
                                Double monitOsnr,
                                 Long lastUpdate
    ) {
        super(modulationFormat, baudRate, frequency, laserEnabled, monitEdfaOutputPower);
        this.monitEdfaInputPower = monitEdfaInputPower;
        this.monitFrequency = monitFrequency;
        this.monitOsnr = monitOsnr;
        this.lastUpdate = lastUpdate;
    }

    public Double getMonitEdfaInputPower() {
        return monitEdfaInputPower;
    }

    public Double getMonitFrequency() {
        return monitFrequency;
    }

    public Double getMonitOsnr() {
        return monitOsnr;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }



}
