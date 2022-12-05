package it.nextworks.qameleon.sbi.api.provisioning.impl;

import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.SbvtRestClient;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.BaudRate;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.ModulationFormat;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.SbvtConfiguration;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.SbvtConfigurationRead;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SbvtConfigurator{
    private SbvtRestClient sbvtRestClient;
    private final ModulationFormat DEFAULT_MODULATION_TYPE = ModulationFormat.QPSK;
    private final BaudRate DEFAULT_BAUD_RATE = BaudRate._32;
    private final float DEFAULT_EDFA_OUTPUT_POWER = 0;

    private ModulationFormat modulationType;
    private BaudRate baudRate;
    private float edfaOutputPower;

    private static final Logger LOG = LoggerFactory.getLogger(SbvtConfigurator.class);

    public SbvtConfigurator(){
        sbvtRestClient = new SbvtRestClient();
        modulationType = DEFAULT_MODULATION_TYPE;
        baudRate = DEFAULT_BAUD_RATE;
        edfaOutputPower = DEFAULT_EDFA_OUTPUT_POWER;
    }




    public boolean configureModulationFormat(ModulationFormat modulationFormat){
        SbvtConfigurationRead currentConfiguration = sbvtRestClient.getSbvtConfiguration();
        BaudRate currentBaudRate = currentConfiguration.getBaudRate();
        double currentFrequency = Double.valueOf(currentConfiguration.getFrequency());
        SbvtConfiguration sbvtConfiguration = new SbvtConfiguration(modulationFormat, currentBaudRate, Integer.valueOf((int) currentFrequency), true, 0.0);
        return sbvtRestClient.configureSbvt(sbvtConfiguration);
    }

    public boolean configureBaudRate(BaudRate baudRate){
        SbvtConfigurationRead currentConfiguration = sbvtRestClient.getSbvtConfiguration();
        ModulationFormat currentModulationFormat = currentConfiguration.getModulationType();
        double currentFrequency = Double.valueOf(currentConfiguration.getFrequency());
        SbvtConfiguration sbvtConfiguration = new SbvtConfiguration(currentModulationFormat, baudRate, Integer.valueOf((int) currentFrequency), true, 0.0);
        return sbvtRestClient.configureSbvt(sbvtConfiguration);
    }

    public boolean configureFrequency(double frequency){
        SbvtConfigurationRead currentConfiguration = sbvtRestClient.getSbvtConfiguration();
        ModulationFormat currentModulationFormat = currentConfiguration.getModulationType();
        SbvtConfiguration sbvtConfiguration = new SbvtConfiguration(currentModulationFormat, baudRate, Integer.valueOf((int) frequency), true, 0.0);
        return sbvtRestClient.configureSbvt(sbvtConfiguration);
    }

    public boolean setLaserStatus(boolean laserStatus){
        SbvtConfigurationRead currentConfiguration = sbvtRestClient.getSbvtConfiguration();
        ModulationFormat currentModulationFormat = currentConfiguration.getModulationType();
        double frequency = currentConfiguration.getFrequency();
        SbvtConfiguration sbvtConfiguration = new SbvtConfiguration(currentModulationFormat, baudRate, (int)frequency, laserStatus, 0.0);
        return sbvtRestClient.configureSbvt(sbvtConfiguration);
    }



}
