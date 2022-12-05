package it.nextworks.qameleon.sbi.api.provisioning.impl;

import it.nextworks.common.TwoWaysChannelFreqTranslator;
import it.nextworks.qameleon.sbi.api.provisioning.LightPathProvisioning;

import it.nextworks.qameleon.sbi.netconf_driver.openRoadmDriver.OpenRoadmNetconfDriver;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev171215.interfaces.grp.Interface;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OpenRoadmProvisioning implements LightPathProvisioning {

    private String deviceId;
    private final OpenRoadmNetconfDriver openRoadmDriver;
    private static final Logger LOG = LoggerFactory.getLogger(OpenRoadmProvisioning.class);
    final String SRC_SUFFIX = "-AMPTX-OUT";
    final String DST_SUFFIX = "-AMPRX-IN";

    public OpenRoadmProvisioning(MountPointService mountPointService, String nodeId,
                                 String deviceHost,
                                 String devicePort,
                                 String username,
                                 String pwd) {
        openRoadmDriver = new OpenRoadmNetconfDriver(mountPointService, nodeId, deviceHost, devicePort, username, pwd);
    }

    public boolean isAvailableInterface(String degreeName,
                                        String supportingCircuitPackName,
                                        String supportingPort,
                                        final Class<? extends InterfaceType> value){

        String logText = "Interface into degree "+degreeName+ " within supporting pack name "+supportingCircuitPackName+ "with supporting port "+supportingPort;
        List<Interface> interfacesList = openRoadmDriver.getInterfaces();
        if(interfacesList==null || interfacesList.size()==0){
            return false;
        }
        for(Interface interfaceRoadm: interfacesList){
            if(degreeName.equals(interfaceRoadm.getName()) &&
                    supportingCircuitPackName.equals(interfaceRoadm.getSupportingInterface()) &&
                    supportingPort.equals(interfaceRoadm.getSupportingPort()) &&
                    value.getName().equals(interfaceRoadm.getType().getName())) {
                LOG.warn(logText+ " found");
                return true;
            }
        }
                LOG.warn(logText+ " NOT found");
                return false;
    }



    private boolean setupInterface(final Class<? extends InterfaceType> interfaceType,
                                   String degName,
                                   String supportingCircuitPackNameRx,
                                   String supportingCircuitPackNameTx,
                                   String supportPortRx,
                                   String supportPortTx,
                                   double minFreq,
                                   double maxFreq){
        String interfaceName = interfaceType.getClass().getName();

        boolean interfaceCreatedSuccess = openRoadmDriver.createInterfaces(degName,
                supportingCircuitPackNameRx,
                supportingCircuitPackNameTx,
                supportPortRx, supportPortTx,
                interfaceType,
                minFreq, maxFreq);

        if(interfaceCreatedSuccess)
            LOG.info(interfaceName+" interface on "+degName+" successfully created");

        else
            LOG.error("Error creating "+interfaceName+" interface on "+degName);

        return interfaceCreatedSuccess;
    }


    private double[] getStartAndEndFreq(int channel, double bandwidthChannel){
        TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator();
        Double centralFreqGhz= twoWaysChannelFreqTranslator.channelToFrequency(channel);

        Double startFreqTransl = centralFreqGhz-bandwidthChannel/2;
        Double endFreqTransl = centralFreqGhz+bandwidthChannel/2;

        double minFreq = startFreqTransl;
        double maxFreq = endFreqTransl;

        minFreq = (double)Math.round(minFreq * 0.1d) / 100d; //Round to the third decimal
        maxFreq = (double)Math.round(maxFreq * 0.1d) / 100d; //Round to the third decimal
        double minMaxFreqArray[];
        minMaxFreqArray = new double[2];
        minMaxFreqArray[0]=minFreq;
        minMaxFreqArray[1]=maxFreq;
        return minMaxFreqArray;

    }
    @Override
    public boolean setupLightPath(int channel, String portSrc, String portDst, double bandwidthChannel) {
        LOG.debug("Port SRC port DST");
        LOG.debug(portSrc);
        LOG.debug(portDst);
        String degSrc = portSrc.split(DST_SUFFIX)[0];
        String degDst = portDst.split(SRC_SUFFIX)[0];
        String degOneSupportingCircuitPackNameRx = degSrc+"-AMPRX";
        String degOneSupportingCircuitPackNameTx = degSrc+"-AMPTX";
        String degOneSupportPortRx = degSrc+DST_SUFFIX;
        String degOneSupportPortTx = degSrc+SRC_SUFFIX;

        String degThreeSupportingCircuitPackNameRx = degDst+"-AMPRX";
        String degThreeSupportingCircuitPackNameTx = degDst+"-AMPTX";
        String degThreeSupportPortRx = degDst+DST_SUFFIX;
        String degThreeSupportPortTx = degDst+SRC_SUFFIX;


        LOG.debug("degSrc "+degSrc);
        LOG.debug("degDst "+degDst);
        LOG.debug("degOneSupportingCircuitPackNameRx "+degOneSupportingCircuitPackNameRx);
        LOG.debug("degOneSupportingCircuitPackNameTx "+degOneSupportingCircuitPackNameTx);
        LOG.debug("degOneSupportPortRx "+degOneSupportPortRx);
        LOG.debug("degOneSupportPortTx "+degOneSupportPortTx);
        LOG.debug("degThreeSupportingCircuitPackNameRx "+degThreeSupportingCircuitPackNameRx);
        LOG.debug("degThreeSupportingCircuitPackNameTx "+degThreeSupportingCircuitPackNameTx);
        LOG.debug("degThreeSupportPortRx "+degThreeSupportPortRx);
        LOG.debug("degThreeSupportPortTx "+degThreeSupportPortTx);


        double minMaxFreqArray[] = getStartAndEndFreq(channel, bandwidthChannel);
        double minFreq = minMaxFreqArray[0];
        double maxFreq = minMaxFreqArray[1];

        LOG.debug("minFreq maxFreq");
        LOG.debug(String.valueOf(minFreq));
        LOG.debug(String.valueOf(maxFreq));
        return openRoadmDriver.createRoadmConnection(degSrc, degDst,minFreq, maxFreq, bandwidthChannel);
    }

    private boolean matchInterface(Interface openRoadmInterface, Class<? extends InterfaceType> type, String degreeName, String direction){
        LOG.info("Checking "+openRoadmInterface.toString()+ " "+degreeName+ " "+direction);
        if(openRoadmInterface.getType()!=type)
            return false;
        if( !openRoadmInterface.getSupportingCircuitPackName().contains(degreeName))
            return false;
        if(!openRoadmInterface.getSupportingCircuitPackName().contains(direction))
            return false;
        LOG.info("Interface found");
        return true;
    }

    @Override
    public boolean removeLightPath(int channel, String portSrc, String portDst, double bandwidthChannel) {
        LOG.debug("Port SRC port DST");
        LOG.debug(portSrc);
        LOG.debug(portDst);

        String degSrc = portSrc.split(DST_SUFFIX)[0];
        String degDst = portDst.split(SRC_SUFFIX)[0];

        LOG.debug("degSrc "+degSrc);
        LOG.debug("degDst "+degDst);


        double minMaxFreqArray[] = getStartAndEndFreq(channel,bandwidthChannel);
        double minFreq = minMaxFreqArray[0];
        double maxFreq = minMaxFreqArray[1];
        LOG.debug("minFreq maxFreq");
        LOG.debug(String.valueOf(minFreq));
        LOG.debug(String.valueOf(maxFreq));

        return openRoadmDriver.removeRoadmConnection(degSrc, degDst,minFreq, maxFreq, bandwidthChannel );
    }

    @Override
    public boolean removeAllLightPaths() {
        return false;
    }
}
