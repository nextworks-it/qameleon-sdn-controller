package it.nextworks.qameleon.sbi.netconf_driver.openRoadmDriver;

import it.nextworks.qameleon.sbi.netconf_driver.NetconfDriver;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.binding.api.WriteTransaction;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev171215.interfaces.grp.Interface;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev171215.interfaces.grp.InterfaceBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev171215.org.openroadm.device.container.OrgOpenroadmDevice;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev171215.org.openroadm.device.container.OrgOpenroadmDeviceBuilder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev171215.org.openroadm.device.container.org.openroadm.device.RoadmConnections;
import org.opendaylight.yang.gen.v1.http.org.openroadm.equipment.states.types.rev171215.AdminStates;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.*;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class OpenRoadmNetconfDriver extends NetconfDriver {
    private static final Logger LOG = LoggerFactory.getLogger(OpenRoadmNetconfDriver.class);
    private OrgOpenroadmDevice orgOpenroadmDevice;
    private final LogicalDatastoreType logicalDatastoreType = LogicalDatastoreType.CONFIGURATION;
    private OpenRoadmClient openRoadmClient;

    public OpenRoadmNetconfDriver(MountPointService mountPointService, String NODE_ID, String deviceHost, String devicePort, String username, String pwd) {
        super(mountPointService, NODE_ID);
        //Is supposed to have the plugin in the same machine of the SDN Controller
        openRoadmClient =
                new OpenRoadmClient("127.0.0.1",
                        "8080",
                        deviceHost,
                        devicePort,
                        username,
                        pwd);

        openRoadmClient.init();
    }



    public boolean discardChangesOpenRoadmDevice(){
        return openRoadmClient.discardChanges();
    }

    private boolean writeOpenRoadmDeviceRoot(OrgOpenroadmDevice orgOpenRoadmDeviceToWrite, LogicalDatastoreType logicalDatastoreType){
        DataBroker db = getDataBroker();
        if(db==null){
            LOG.error("Cannot read from datastore. Data broker is null.");
            return false;
        }

        final WriteTransaction writeTnx = db.newWriteOnlyTransaction();

        InstanceIdentifier<OrgOpenroadmDevice> iid = InstanceIdentifier.create(OrgOpenroadmDevice.class);
        try {
            LOG.info("Information going to write to OpenROADM device datastore: ");
            LOG.info(orgOpenRoadmDeviceToWrite.toString());

            writeTnx.merge(logicalDatastoreType, iid, orgOpenRoadmDeviceToWrite);
            writeTnx.commit().get();
        }
            catch (InterruptedException | ExecutionException e) {
                LOG.error(" Error while writing into datastore of OpenROADM device");
                LOG.error(e.getMessage());
                return false;
            }

          return true;
    }

    public OrgOpenroadmDevice getOpenRoadmDevice(){
        Optional<OrgOpenroadmDevice> orgOpenroadmDeviceOpt = (Optional<OrgOpenroadmDevice>)readFromDataStore(OrgOpenroadmDevice.class, logicalDatastoreType);
        if(orgOpenroadmDeviceOpt==null){
            LOG.warn("No OpenROADM device information found.");
            return null;
        }
        if(!orgOpenroadmDeviceOpt.isPresent() || orgOpenroadmDeviceOpt.get()==null){
            LOG.warn("No OpenROADM device information found.");
            return new OrgOpenroadmDeviceBuilder().build();
        }
        orgOpenroadmDevice = orgOpenroadmDeviceOpt.get();
        return orgOpenroadmDevice;
    }

    public OrgOpenroadmDevice getOpenRoadmDeviceOperational(){
        Optional<OrgOpenroadmDevice> orgOpenroadmDeviceOpt = (Optional<OrgOpenroadmDevice>)readFromDataStore(OrgOpenroadmDevice.class, LogicalDatastoreType.OPERATIONAL);
        if(orgOpenroadmDeviceOpt==null){
            LOG.warn("No OpenROADM device information found.");
            return null;
        }
        if(!orgOpenroadmDeviceOpt.isPresent() || orgOpenroadmDeviceOpt.get()==null){
            LOG.warn("No OpenROADM device information found.");
            return new OrgOpenroadmDeviceBuilder().build();
        }
        orgOpenroadmDevice = orgOpenroadmDeviceOpt.get();
        return orgOpenroadmDevice;
    }

    private OpenRoadmInterfaceType getOpenRoadmInterfaceType(final Class<? extends InterfaceType> interfaceType){
        if(interfaceType.getName()==OpenROADMOpticalMultiplex.class.getName())
            return OpenRoadmInterfaceType.OMS;

        if(interfaceType.getName()==OpticalTransport.class.getName())
            return OpenRoadmInterfaceType.OTS;

        if(interfaceType.getName()== MediaChannelTrailTerminationPoint.class.getName())
            return OpenRoadmInterfaceType.MCTTP;

        if(interfaceType.getName()== NetworkMediaChannelConnectionTerminationPoint.class.getName())
            return OpenRoadmInterfaceType.NMCCTP;

        return null;
    }


    private int executeBashCommand(String bashCommand){
        Runtime runtime = Runtime.getRuntime();
        Process proc = null;
        try {
            proc = runtime.exec(bashCommand);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));


        try {
            LOG.info("Output command is ");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                LOG.info(s);
            }
            proc.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
       return proc.exitValue();

    }

    public boolean createInterfaces(String degreeName,
                                      String supportingCircuitPackNameRx,
                                      String supportingCircuitPackNameTx,
                                      String supportingPortRx,
                                      String supportingPortTx,
                                      final Class<? extends InterfaceType> interfaceType,
                                      double minFreq,
                                      double maxFreq){

        LOG.info("\nInformation about interface creation: interface into degree "+degreeName+ ". RX support circuit pack is "+supportingCircuitPackNameRx+" "+"TX supporting circuit pack is "+supportingCircuitPackNameTx);
        LOG.info("RX supporting port is "+supportingPortRx+ " TX supporting port is "+supportingPortTx);
        LOG.info("Min frequency and max frequency are "+minFreq+" Ghz and "+maxFreq+" Ghz, respectively\n");

        InterfaceBuilder interfaceBuilderTx =
                new InterfaceBuilder()
                        .setType(interfaceType)
                        .setAdministrativeState(AdminStates.InService)
                        .setSupportingCircuitPackName(supportingCircuitPackNameTx)
                        .setSupportingPort(supportingPortTx);

        InterfaceBuilder interfaceBuilderRx =
                new InterfaceBuilder()
                        .setType(interfaceType)
                        .setAdministrativeState(AdminStates.InService)
                        .setSupportingCircuitPackName(supportingCircuitPackNameRx)
                        .setSupportingPort(supportingPortRx);
        String prefix;
        String suffixRx;
        String suffixTx;

        OpenRoadmInterfaceType openRoadmInterfaceType = getOpenRoadmInterfaceType(interfaceType);
        Path path;
        path = Paths.get(System.getProperty("user.dir"));
        LOG.debug("Directory with properties = " + path);
        Path basepath = path;
        if (path.getFileName().toString().equals("assembly"))
            basepath = path.getParent().getParent().getParent();
        switch(openRoadmInterfaceType){
            case OTS:
                LOG.info("Building OTS interface request...");
                prefix ="OTS";
                suffixRx = "RX";
                suffixTx = "TX";
                break;

            case OMS:
                LOG.info("Building OSM interface request...");
                prefix ="OMS";
                suffixRx = "RX";
                suffixTx = "TX";
                interfaceBuilderTx.setSupportingInterface("OTS-"+degreeName+"-TX");
                interfaceBuilderRx.setSupportingInterface("OTS-"+degreeName+"-RX");
                break;

            default:
                LOG.warn("OMS, OTS MC-TTP and NMC-TTP interfaces are supported." +openRoadmInterfaceType.name()+ " is NOT supported.");
                return false;

        }

        interfaceBuilderTx.setName(prefix+"-"+degreeName+"-"+suffixTx);
        interfaceBuilderRx.setName(prefix+"-"+degreeName+"-"+suffixRx);
        List<Interface> interfaces = new ArrayList<>();
        interfaces.add(interfaceBuilderTx.build());
        interfaces.add(interfaceBuilderRx.build());

        OrgOpenroadmDevice orgOpenroadmDevice= new OrgOpenroadmDeviceBuilder().setInterface(interfaces).build();
        return writeOpenRoadmDeviceRoot(orgOpenroadmDevice,logicalDatastoreType);
    }


    public boolean createRoadmConnection(String degSrc, String degDst, double startFreq, double endFreq, double freqWidth){
        return openRoadmClient.createExpressLink(degSrc, degDst, startFreq, endFreq, freqWidth);
    }

    public boolean removeRoadmConnection(String degSrc, String degDst, double startFreq, double endFreq, double freqWidth) {
        return openRoadmClient.removeExpressLink(degSrc, degDst, startFreq, endFreq, freqWidth);
    }
    private boolean areInterfacesExisting(String interfaceRx, String interfaceTx){
        boolean interfaceTxFound = false;
        boolean interfaceRxFound = false;
        for(Interface openRoadmInterface: getInterfaces()){
            String interfaceNameTmp = openRoadmInterface.getName();

            if(interfaceRx.equals(interfaceNameTmp)) {
                LOG.info("Interface with name "+interfaceRx+ " found.");
                interfaceRxFound = true;
            }
            if(interfaceTx.equals(interfaceNameTmp)) {
                LOG.info("Interface with name "+interfaceRx+ " found.");
                interfaceTxFound = true;
            }
        }
        return interfaceRxFound && interfaceTxFound;
    }

    public List<Interface> getInterfaces(){
        if(orgOpenroadmDevice==null){
            //orgOpenroadmDevice = getOpenRoadmDevice();
            orgOpenroadmDevice = getOpenRoadmDeviceOperational();
        }
        List<Interface>  interfaces = orgOpenroadmDevice.getInterface();
        if(interfaces==null || interfaces.size()==0){
            LOG.warn("No interfaces found within OpenROADM device.");
            return new ArrayList<>();
        }
        return interfaces;
    }



    public List<RoadmConnections> getRoadmsConnection(){
        if(orgOpenroadmDevice==null){
            orgOpenroadmDevice = getOpenRoadmDevice();
        }
        List<RoadmConnections> roadmConnections = orgOpenroadmDevice.getRoadmConnections();
        if(roadmConnections==null){
            return new ArrayList<>();
        }
        return roadmConnections;
    }


    public List<RoadmConnectionInfo> getRoadmConnectionInfoList(){
        List<RoadmConnectionInfo> roadmConnectionInfoArrayList = new ArrayList<>();
        String payload = openRoadmClient.getRoadmConnections();
        List<String> connectionsName =  OpenRoadmResponseParser.getConnectionNames(payload);

        LOG.info("Connections name");
        LOG.info(connectionsName.toString());
        for(String connectionName: connectionsName){
            String payloadResponse = openRoadmClient.getRoadmConnection(connectionName);
            HashMap<String, String> srcDst= OpenRoadmResponseParser.getSrcOpenRoadmConnection(payloadResponse);
            String nmcCptSrcInterface = srcDst.get(OpenRoadmResponseParser.SRC_KEY);
            String nmcCptDstInterface = srcDst.get(OpenRoadmResponseParser.DST_KEY);

            String responseDst = openRoadmClient.getRoadmInterface(nmcCptDstInterface);
            String supportingPortDst = OpenRoadmResponseParser.getSupportingPortNmcCtpInterface(responseDst);


            String responseSrc = openRoadmClient.getRoadmInterface(nmcCptSrcInterface);
            String supportingPortSrc = OpenRoadmResponseParser.getSupportingPortNmcCtpInterface(responseSrc);

            LOG.info(nmcCptSrcInterface +" "+supportingPortSrc);
            LOG.info(nmcCptDstInterface +" "+supportingPortDst);
            RoadmConnectionInfo roadmConnectionInfo = new RoadmConnectionInfo(connectionName, nmcCptSrcInterface, supportingPortSrc, nmcCptDstInterface, supportingPortDst);
            roadmConnectionInfoArrayList.add(roadmConnectionInfo);
        }
        return roadmConnectionInfoArrayList;
    }


}
