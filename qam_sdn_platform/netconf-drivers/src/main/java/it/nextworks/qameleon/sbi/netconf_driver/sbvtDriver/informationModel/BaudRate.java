package it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum BaudRate {
    _16(16), _32(32), _56(56), _64(64), _96(96), _128(128);

    private final int value;
    private static final Logger LOG = LoggerFactory.getLogger(BaudRate.class);

    BaudRate(int value) {
        this.value = value;
    }

    public static BaudRate valueOf(int baudRateInt){
        switch(baudRateInt){}
        if(baudRateInt==_16.getValue())
            return _16;
        if(baudRateInt==_32.getValue())
            return _32;
        if(baudRateInt==_56.getValue())
            return _56;
        if(baudRateInt==_64.getValue())
            return _64;
        if(baudRateInt==_96.getValue())
            return _96;
        if(baudRateInt==_128.getValue())
            return _128;

        LOG.error(baudRateInt+ " baud rate value is not valid.");
        return null;
    }


    public int getValue() {
        return value;
    }

    //32, 56, 64, 96, 128
}



