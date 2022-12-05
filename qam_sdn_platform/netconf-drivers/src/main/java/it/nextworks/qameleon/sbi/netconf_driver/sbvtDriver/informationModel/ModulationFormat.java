package it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum ModulationFormat {
    QPSK("QPSK"),
    _16QAM("16QAM"),

    _32QAM("32QAM"),
    _64QAM("64QAM"),
    PCS_16QAM("PCS-16QAM"),
    PCS_64QAM("PCS-64QAM");

    private final String modulationType;
    private static final Logger LOG = LoggerFactory.getLogger(ModulationFormat.class);

    ModulationFormat(final String modulationType) {
        this.modulationType = modulationType;
    }

    @Override
    public final String toString() {
        return modulationType;
    }
}
