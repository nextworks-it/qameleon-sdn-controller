package it.nextworks.qameleon.qamProvisioningApp.service;

import it.nextworks.common.PropertyReader;
import it.nextworks.common.TwoWaysChannelFreqTranslator;
import it.nextworks.qameleon.qamProvisioningApp.sbi.PceRestClient;
import it.nextworks.qameleon.qamProvisioningApp.sbi.TopologyAppServiceConsumer;
import it.nextworks.qameleon.sbi.api.provisioning.LightPathProvisioning;
import it.nextworks.qameleon.sbi.api.provisioning.impl.*;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.BaudRate;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.ModulationFormat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.yang.gen.v1.qameleon.common.rev200914.QNodeType;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamLink;
import org.opendaylight.yang.gen.v1.qameleon.topology.rev200904.qam.topology.QamNode;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.ForwardingDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.PortDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.global._class.Name;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.global._class.NameBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.CreateConnectivityServiceInput;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connection.ConnectionEndPoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connection.ConnectionEndPointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connection.Route;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connection.RouteBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.ConnectionBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.ConnectivityService;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.ConnectivityServiceBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.ComputeP2PPathInput;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.ComputeP2PPathInputBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.compute.p._2.p.path.input.Sep;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.compute.p._2.p.path.input.SepBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.compute.p._2.p.path.input.TopologyConstraintBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.Link;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.LinkBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.RoutingConstraintBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.computation.context.Path;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.computation.context.PathBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.path.service.end.point.ServiceInterfacePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.link.NodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class QamProvisioningService {
    private PceRestClient pceRestClient;
    private TopologyAppServiceConsumer topologyAppServiceConsumer;
    private LightPathInventoryService lightPathInventoryService;
    private final MountPointService mountPointService;
    private final int NO_PROVISIONING_CONSTRAINT = -99;
    private final Double DEFAULT_BANDWIDTH_CONSTRAINT = 50.0;

    private static final Logger LOG = LoggerFactory.getLogger(QamProvisioningService.class);
    public QamProvisioningService(DataBroker dataBroker, final MountPointService mountPointService){
        this.mountPointService = mountPointService;
        topologyAppServiceConsumer = TopologyAppServiceConsumer.getInstance();
        lightPathInventoryService = new LightPathInventoryService(dataBroker);
        initPceRestClient();
    }

    private void initPceRestClient(){
        PropertyReader propertyReader = new PropertyReader();
        boolean readSuccess = propertyReader.parsePropertiesFile();
        if(readSuccess)
            pceRestClient = new PceRestClient(propertyReader.getPceIp(),propertyReader.getPcePort());
        else
            pceRestClient = new PceRestClient(PropertyReader.getDefaultPceHost(),PropertyReader.getPcePort());
    }

    private Path jsonToPathConverter(JSONObject eroJson){
        List<Name> values = new ArrayList();
        JSONArray jsonElements = eroJson.getJSONArray("name");
        for(int i=0; i<jsonElements.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonElements.get(i);
            String value = jsonObject.getString("value");
            String valueName = jsonObject.getString("value-name");
            values.add(new NameBuilder().setValue(value).setValueName(valueName).build());
        }

        String uuid = eroJson.getString("uuid");
        List<Link> linkListObj = new ArrayList<>();
        JSONArray linkList = eroJson.getJSONArray("link");
        for(int i=0; i<linkList.length(); i++) {
            JSONObject jsonObject = (JSONObject) linkList.get(i);
            String linkUuid = jsonObject.getString("link-uuid");
            String topologyUuid = jsonObject.getString("topology-uuid");
            linkListObj.add(new LinkBuilder().setLinkUuid(new Uuid(linkUuid)).setTopologyUuid(new Uuid(topologyUuid)).build());
        }
        return new PathBuilder().setUuid(new Uuid(uuid)).setName(values).setDirection(ForwardingDirection.UNIDIRECTIONAL)
                .setLink(linkListObj).setRoutingConstraint(new RoutingConstraintBuilder().build()).build();

    }


    private String prePadZeroIfNeeded(int number){
        if (number <= 9)
             return "0"+number;

         return String.valueOf(number);
    }

    private List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.Connection> getConnections(Path lightpath){
        List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.Connection> connectionList = new ArrayList<>();

        for(Link link: lightpath.getLink()) {
            QamLink qamLink = topologyAppServiceConsumer.getQamLink(link.getLinkUuid().getValue());
            qamLink.getUuid();
            String nodeSrc = qamLink.getNodeSrc();
            String nodeDst = qamLink.getNodeDst();
            String portSrc = qamLink.getPortSrc();
            String portDst = qamLink.getPortDst();

            List<ConnectionEndPoint> cepList = new ArrayList<>();

            //TO CEP yet into topology.
            //int cepNumberSrc = getCepCount(nodeSrc, portSrc);
            //int cepNumberDst = getCepCount(nodeDst, portDst);

            int cepNumberSrc = getChannelNumberFromLightpath(lightpath);
            int cepNumberDst = getChannelNumberFromLightpath(lightpath);

            final String TOPOLOGY_NAME  = "QameleonTopology";

            ConnectionEndPoint cep1 = new ConnectionEndPointBuilder()
                    .setConnectionEndPointUuid(new Uuid("CEP"+ prePadZeroIfNeeded(cepNumberSrc)))
                    .setNodeEdgePointUuid(new Uuid(portSrc))
                    .setNodeUuid(new Uuid(nodeSrc))
                    .setTopologyUuid(new Uuid(TOPOLOGY_NAME))
                    .build();

            ConnectionEndPoint cep2 = new ConnectionEndPointBuilder()
                    .setConnectionEndPointUuid(new Uuid("CEP"+ prePadZeroIfNeeded(cepNumberDst)))
                    .setNodeEdgePointUuid(new Uuid(portDst))
                    .setNodeUuid(new Uuid(nodeDst))
                    .setTopologyUuid(new Uuid(TOPOLOGY_NAME))
                    .build();

            cepList.add(cep1);
            cepList.add(cep2);

            List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.route.ConnectionEndPoint> cepRouteList = new ArrayList<>();

            cepRouteList.add(
            new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.route.ConnectionEndPointBuilder()
                    .setTopologyUuid(cep1.getTopologyUuid())
                    .setConnectionEndPointUuid(cep1.getConnectionEndPointUuid())
                    .setNodeEdgePointUuid(cep1.getNodeEdgePointUuid())
                    .setNodeUuid(cep1.getNodeUuid())
                    .build());
            cepRouteList.add(
                    new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.route.ConnectionEndPointBuilder()
                            .setTopologyUuid(cep2.getTopologyUuid())
                            .setConnectionEndPointUuid(cep2.getConnectionEndPointUuid())
                            .setNodeEdgePointUuid(cep2.getNodeEdgePointUuid())
                            .setNodeUuid(cep2.getNodeUuid())
                            .build());

            Route route = new RouteBuilder().setConnectionEndPoint(cepRouteList).setLocalId("route-1").build();
            List<Route> routeList = new ArrayList<>();
            routeList.add(route);


            org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.Connection connection
                    = new ConnectionBuilder()
                    .setUuid(new Uuid("connection-"+nodeSrc+"-"+nodeDst+"-ch-"+getChannelNumberFromLightpath(lightpath)))
                    .setDirection(ForwardingDirection.UNIDIRECTIONAL)
                    .setRoute(routeList)
                    .setConnectionEndPoint(cepList)
                    .build();
            connectionList.add(connection);
        }
        return connectionList;
    }

    private HashMap<String, DeviceProvisioning> getDeviceProvisioningMap(Path lightpath){
        HashMap<String, DeviceProvisioning> deviceProvisioningHashMap = new HashMap<>();
        List<String> linkListUuid = new ArrayList<>();
        int channelNumber = getChannelNumberFromLightpath(lightpath);

        for(Link link: lightpath.getLink()){
            QamLink qamLink= topologyAppServiceConsumer.getQamLink(link.getLinkUuid().getValue());
            linkListUuid.add(link.getLinkUuid().getValue());


            qamLink.getNodeEdgePoint().size();

            List<NodeEdgePoint> nodeEdgePoints = qamLink.getNodeEdgePoint();
            for(int i=0; i<nodeEdgePoints.size(); i++){
                NodeEdgePoint nodeEdgePoint = qamLink.getNodeEdgePoint().get(i);
                String nodeId = nodeEdgePoint.getNodeUuid().getValue();
                String portId = nodeEdgePoint.getNodeEdgePointUuid().getValue();

                DeviceProvisioning deviceProvisioning = deviceProvisioningHashMap.get(nodeId);
                if(deviceProvisioning==null){
                    deviceProvisioning = new DeviceProvisioning(nodeId, channelNumber,getNodeType(nodeId) );
                }
                PortDirection portDirection = getPortDirection(nodeId, portId);

                if(portDirection==PortDirection.INPUT){
                    deviceProvisioning.setSrcPort(portId);
                }
                else if(portDirection==PortDirection.OUTPUT){
                    deviceProvisioning.setDstPort(portId);
                }
                else if(portDirection==PortDirection.BIDIRECTIONAL){
                    LOG.debug("Node "+nodeId+": found bidirectional port whose ID is: "+portId);
                    if(deviceProvisioning.getSrcPort()==null){
                        deviceProvisioning.setSrcPort(portId);
                        LOG.info("Setting bidirectional port whose ID is "+portId+ " as source port");
                    }
                    if(deviceProvisioning.getDstPort()==null){
                        deviceProvisioning.setDstPort(portId);
                        LOG.debug("Setting bidirectional port whose ID is "+portId+ " as destination port");
                    }
                }

                deviceProvisioningHashMap.put(nodeId,deviceProvisioning);
            }

        }
        return deviceProvisioningHashMap;
    }


    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    ///


    ////

    private ModulationFormat getModulationFormatInfoFromTapiName(CreateConnectivityServiceInput inputCreateConnServ){
        String modulationFormatStr = getConstraintValue(inputCreateConnServ,"modulation-format");
        if(modulationFormatStr==null) {
            LOG.info("No modulation format found");
            return null;
        }
        try{
            ModulationFormat modulationFormat = ModulationFormat.valueOf(modulationFormatStr);
            return modulationFormat;
        }
        catch(Exception e){
            LOG.error("Invalid value of modulation format: "+modulationFormatStr);
            e.printStackTrace();
            LOG.error(e.getMessage());
            return null;
        }
    }

    private BaudRate getBaudRateInfoFromTapiName(CreateConnectivityServiceInput inputCreateConnServ){
        String baudRateStr = getConstraintValue(inputCreateConnServ,"baud-rate");
        if(baudRateStr==null) {
            LOG.info("No baud rate found");
            return null;
        }
        try{
            BaudRate baudRate = BaudRate.valueOf(baudRateStr);
            return baudRate;
        }
        catch(Exception e){
            LOG.error("Invalid value of baud rate: "+baudRateStr);
            e.printStackTrace();
            LOG.error(e.getMessage());
            return null;
        }
    }

    private int getChannelToSetupInfoFromTapiName(CreateConnectivityServiceInput inputCreateConnServ){
        String channelStr = getConstraintValue(inputCreateConnServ,"channel-constraint");
        if(channelStr==null) {
            LOG.info("No channel to setfound");
            return NO_PROVISIONING_CONSTRAINT;
        }
        try{
            return Integer.valueOf(channelStr);
        }
        catch(Exception e){
            LOG.error("Invalid value channel to set: "+channelStr);
            e.printStackTrace();
            LOG.error(e.getMessage());
            return NO_PROVISIONING_CONSTRAINT;
        }
    }

    private int getOpticalChannelConstraintFromTapiName(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name name){
        if(name==null)
            return NO_PROVISIONING_CONSTRAINT;

            TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator();
            String valueName = name.getValueName();
            String value = name.getValue();
            if(!isNumeric(value)){
                LOG.warn("No numeric value found for provisioning constraint");
                return NO_PROVISIONING_CONSTRAINT;
            }
            if(valueName.equals("frequency-constraint")) {
                LOG.info("Found frequency constraint");
                return twoWaysChannelFreqTranslator.frequencyToChannel(Double.valueOf(value));
            }

            if(valueName.equals("wavelength-constraint")) {
                LOG.info("Found wavelength constraint");
                return twoWaysChannelFreqTranslator.wavelengthToChannel(Double.valueOf(value));
            }
            if(valueName.equals("optical-channel-constraint")) {
                LOG.info("Found optical channel constraint");
                return Integer.valueOf(value);
            }
        LOG.warn("Found no valid value name: "+valueName);
        return NO_PROVISIONING_CONSTRAINT;
    }

    private int getOpticalChannelConstraint(List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name> names, List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name> names2) {
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name name = null;
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name name2 = null;

        if (names != null && names.size() > 0) {
           name = names.get(0);
        }

        if (names2 != null && names2.size() > 0) {
            name2 = names2.get(0);
        }

        if (name != null && name2==null) {
            return getOpticalChannelConstraintFromTapiName(name);
        }
        else if (name != null && name2!=null) {
            LOG.warn("Found two provisioning constraints: taking into consideration only one. ");
            return getOpticalChannelConstraintFromTapiName(name);
        }
        else if (name == null && name2!=null) {
            return getOpticalChannelConstraintFromTapiName(name2);
        }
        else {
            LOG.info("No provisioning constraint found. Both names are null");
            return NO_PROVISIONING_CONSTRAINT;
        }
    }

    private ComputeP2PPathInput updateComputeP2PPathInput(ComputeP2PPathInput computeP2PPathInput, int channelConstraint){
        LOG.info("Updating SEP list");
        if(channelConstraint==NO_PROVISIONING_CONSTRAINT){
            LOG.info("No channel constraint found");
            return computeP2PPathInput;
        }

        List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name> nameList = new ArrayList<>();
        org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name name = new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.NameBuilder()
                .setValue(String.valueOf(channelConstraint))
                .setValueName("channel")
                .build();
        nameList.add(name);

       LOG.info("Setting channel value to "+channelConstraint);
       List<Sep> sepList = computeP2PPathInput.getSep();
        sepList.set(0,new SepBuilder(sepList.get(0)).setName(nameList).build());

       return new ComputeP2PPathInputBuilder(computeP2PPathInput).setSep(sepList)
               .setTopologyConstraint(computeP2PPathInput.getTopologyConstraint())
               .build();

    }

    private void printUuidList(List<Uuid> listUuid){
        if(listUuid==null) {
            LOG.warn("UUID List is null");
            return;
        }
        if(listUuid.size()==0) {
            LOG.warn("UUID List is empty");
        }
        for(Uuid uuid: listUuid){
            LOG.info(uuid.getValue());
        }

    }

    private String getEndPointSrc(CreateConnectivityServiceInput inputCreateConnServ) throws ExecutionException {
        PortDirection portDirectionEndPointZero = inputCreateConnServ.getEndPoint().get(0).getDirection();
        PortDirection portDirectionEndPointOne = inputCreateConnServ.getEndPoint().get(1).getDirection();

        if (portDirectionEndPointZero == PortDirection.OUTPUT) {
            return inputCreateConnServ.getEndPoint().get(0).getLocalId();
        }
        if (portDirectionEndPointOne == PortDirection.OUTPUT) {
            return inputCreateConnServ.getEndPoint().get(1).getLocalId();
        } else {
            throw new ExecutionException(new Throwable("No Port Direction OUTPUT found"));
        }
    }

    private String getEndPointDst(CreateConnectivityServiceInput inputCreateConnServ) throws ExecutionException {
        PortDirection portDirectionEndPointZero = inputCreateConnServ.getEndPoint().get(0).getDirection();
        PortDirection portDirectionEndPointOne = inputCreateConnServ.getEndPoint().get(1).getDirection();

        if (portDirectionEndPointZero == PortDirection.INPUT) {
            return inputCreateConnServ.getEndPoint().get(0).getLocalId();
        }
        if (portDirectionEndPointOne == PortDirection.INPUT) {
            return inputCreateConnServ.getEndPoint().get(1).getLocalId();
        } else {
            throw new ExecutionException(new Throwable("No Port Direction INPUT found"));
        }
    }

    private ComputeP2PPathInput buildPayloadRequestForPCE(CreateConnectivityServiceInput inputCreateConnServ, ComputeP2PPathInput inputComputePath) throws ExecutionException {
        String sipSrc;
        String sipDst;
        int channelConstraint = NO_PROVISIONING_CONSTRAINT;

        TopologyConstraintBuilder topologyConstraintBuilder = new TopologyConstraintBuilder();
        if(inputCreateConnServ.getTopologyConstraint()==null){
            LOG.info("No topology constraints found");
        }
        else{
            LOG.info("Topology constraints found.");

            List<Uuid> linksToInclude = inputCreateConnServ.getTopologyConstraint().getIncludeLink();
            LOG.info("List of UUID of link to include: ");
            printUuidList(linksToInclude);
            List<Uuid> linksToExclude = inputCreateConnServ.getTopologyConstraint().getExcludeLink();
            LOG.info("List of UUID of link to exclude: ");
            printUuidList(linksToExclude);

            topologyConstraintBuilder
                    .setIncludeLink(linksToInclude)
                    .setExcludeLink(linksToExclude);
        }


        String epSrc = getEndPointSrc(inputCreateConnServ);
        String epDst = getEndPointDst(inputCreateConnServ);
        ComputeP2PPathInput computeP2PPathInput = null;
        if(inputCreateConnServ!=null && inputComputePath==null) {
            sipSrc = epSrc +"_" + "sip01";
            sipDst = epDst + "_" + "sip01";
            channelConstraint = getOpticalChannelConstraint(inputCreateConnServ.getEndPoint().get(0).getName(),inputCreateConnServ.getEndPoint().get(1).getName());

            Sep sepSrc = new SepBuilder().setDirection(PortDirection.OUTPUT).setServiceInterfacePoint(new ServiceInterfacePointBuilder().setServiceInterfacePointUuid(new Uuid(sipSrc)).build()).build();
            Sep sepDst = new SepBuilder().setDirection(PortDirection.INPUT).setServiceInterfacePoint(new ServiceInterfacePointBuilder().setServiceInterfacePointUuid(new Uuid(sipDst)).build()).build();

            List<Sep> sepList = new ArrayList<>();
            sepList.add(sepDst);
            sepList.add(sepSrc);

            computeP2PPathInput = new ComputeP2PPathInputBuilder()
                    .setSep(sepList)
                    .setTopologyConstraint(topologyConstraintBuilder.build())
                    .build();

            return updateComputeP2PPathInput(computeP2PPathInput, channelConstraint);
        }

        if(inputCreateConnServ==null && inputComputePath!=null){
            channelConstraint = getOpticalChannelConstraint(inputComputePath.getSep().get(0).getName(),inputComputePath.getSep().get(1).getName());
            computeP2PPathInput = new ComputeP2PPathInputBuilder()
                    .setSep(inputComputePath.getSep())
                    .build();


            return updateComputeP2PPathInput(computeP2PPathInput, channelConstraint);
        }
        LOG.error("No input for lightpath provisioning provided.");
        return null;
    }


    private Double convertBandwidthConstraintStr(String bandwidthConstraint){

        try{
            Double bandwidthConstraintDouble = Double.valueOf(bandwidthConstraint);
            return bandwidthConstraintDouble;
        }
        catch(NumberFormatException e){
            LOG.warn("Cannot convert "+bandwidthConstraint+ " into double");
            return DEFAULT_BANDWIDTH_CONSTRAINT;
        }
    }


    private Double getBandwidthConstraint(CreateConnectivityServiceInput inputCreateConnServ){

        final String BANDWIDTH_CONSTRAINT_VALUE_NAME = "bandwidth-constraint";
        LOG.info("Checking for bandwidth constraint");
        if(inputCreateConnServ.getEndPoint().get(0).getName()!=null) {
            List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name> nameListEpZero =
                    inputCreateConnServ.getEndPoint().get(0).getName();
            LOG.info("Name count is "+nameListEpZero.size());
            for (org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name name : nameListEpZero) {
                LOG.info("Name value "+name.getValueName() );
                LOG.info("Value "+name.getValue() );
                if (name.getValueName().equals(BANDWIDTH_CONSTRAINT_VALUE_NAME)) {
                    LOG.info(BANDWIDTH_CONSTRAINT_VALUE_NAME + " found: its value is " + name.getValue() + "Ghz");
                    return convertBandwidthConstraintStr(name.getValue());
                }
            }
        }
        else{
            LOG.warn("No name found under endpoint zero information");
        }

        if(inputCreateConnServ.getEndPoint().get(1).getName()!=null) {
            List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name> nameListEpOne =
                    inputCreateConnServ.getEndPoint().get(1).getName();
            LOG.info("Name count is "+nameListEpOne.size());
            for (org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name name : nameListEpOne) {
                LOG.info("Name value "+name.getValueName() );
                LOG.info("Value "+name.getValue() );
                if (name.getValueName().equals(BANDWIDTH_CONSTRAINT_VALUE_NAME)) {
                    LOG.info(BANDWIDTH_CONSTRAINT_VALUE_NAME + " found: its value is " + name.getValue() + "Ghz");
                    return convertBandwidthConstraintStr(name.getValue());
                }
            }
        }
        else{
            LOG.warn("No name found under endpoint one information");
        }

        LOG.info("No "+BANDWIDTH_CONSTRAINT_VALUE_NAME+" found.");
        return DEFAULT_BANDWIDTH_CONSTRAINT;
    }



    private String getConstraintValue(CreateConnectivityServiceInput inputCreateConnServ, String constraintValueName){
        LOG.info("Checking for constraint whose value name is "+constraintValueName);
        if(inputCreateConnServ.getEndPoint().get(0).getName()!=null) {
            for (org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name name : inputCreateConnServ.getEndPoint().get(0).getName()) {
                LOG.info(name.getValueName());
                LOG.info(name.getValue());
                if (name.getValueName().equals(constraintValueName)) {
                    LOG.info(constraintValueName + " found: its value is " + name.getValue());
                    return name.getValue();
                }
            }
        }
        else{
            LOG.warn("No name found under endpoint zero information");
        }
        return null;
    }



    public String createLightPathRequest(CreateConnectivityServiceInput inputCreateConnServ, ComputeP2PPathInput inputComputePath) throws IOException, ExecutionException, InterruptedException {
        CreateConnectivityServiceInput originalPayload = inputCreateConnServ;

        for(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.create.connectivity.service.input.EndPoint ep: originalPayload.getEndPoint()){
            LOG.info(ep.getLocalId());
            if(ep.getName()==null)
                continue;
            for(org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.local._class.Name name: ep.getName()){
                LOG.info(name.getValue());
                LOG.info(name.getValueName());
            }
        }

        //one ep means configure the laser (modulation format, channel, baudrate)
        if(originalPayload.getEndPoint().size()==1){
            SbvtConfigurator sbvtConfigurator = new SbvtConfigurator();
            LOG.info("Configuring the laser");

            //Case #1: Update Modulation format
            ModulationFormat modulationFormat = getModulationFormatInfoFromTapiName(inputCreateConnServ);
            if(modulationFormat!=null){
                boolean successConfigModulationFormat = sbvtConfigurator.configureModulationFormat(modulationFormat);
                if(successConfigModulationFormat)
                    return String.valueOf(modulationFormat.hashCode());
                else
                    throw new BadRequestException("Error configuring modulation Format");
            }

            //Case #2: Update Baud rate
            BaudRate baudRate = getBaudRateInfoFromTapiName(inputCreateConnServ);
            if(baudRate!=null){

                boolean successConfigBaudRate = sbvtConfigurator.configureBaudRate(baudRate);
                if(successConfigBaudRate)
                    return String.valueOf(baudRate.hashCode());
                else
                    throw new BadRequestException("Error configuring baud rate");
            }

            //Case #3: Update frequency
            String nodeId =originalPayload.getEndPoint().get(0).getLocalId();
            LightPathProvisioning lightPathProvisioning = getLightPathProvisioningDriver(nodeId,  getNodeType(nodeId));
            int channelConstraint  = getChannelToSetupInfoFromTapiName(inputCreateConnServ);

            if(channelConstraint==NO_PROVISIONING_CONSTRAINT)
                throw new BadRequestException("Error during the configuration of the laser. Wrong value specified");

            boolean channelSetupSuccess = lightPathProvisioning.setupLightPath(channelConstraint,"NO_PORT", "NO_PORT",0);
            if(!channelSetupSuccess)
                throw new BadRequestException("Error during the configuration of the laser");
            TwoWaysChannelFreqTranslator twoWaysChannelFreqTranslator = new TwoWaysChannelFreqTranslator();
            double frequency = twoWaysChannelFreqTranslator.channelToFrequency(channelConstraint);

            boolean freqModulatorConfSuccess = sbvtConfigurator.configureFrequency(frequency);
            if(!freqModulatorConfSuccess)
                throw new BadRequestException("Error during the configuration of the modulator");

            return "";
        }

        ComputeP2PPathInput computeP2PPathInput = buildPayloadRequestForPCE(inputCreateConnServ, inputComputePath);
        if(computeP2PPathInput==null)
            return null;

        String sipSrc = null;
        if(computeP2PPathInput.getSep().get(0).getDirection()==PortDirection.OUTPUT)
            sipSrc = computeP2PPathInput.getSep().get(0).getServiceInterfacePoint().getServiceInterfacePointUuid().getValue();

        if(computeP2PPathInput.getSep().get(1).getDirection()==PortDirection.OUTPUT)
            sipSrc = computeP2PPathInput.getSep().get(1).getServiceInterfacePoint().getServiceInterfacePointUuid().getValue();

        if(sipSrc==null){
            throw new BadRequestException("Cannot find SIP src");
        }

        String sipDst = null;
        if(computeP2PPathInput.getSep().get(0).getDirection()==PortDirection.INPUT)
            sipDst = computeP2PPathInput.getSep().get(0).getServiceInterfacePoint().getServiceInterfacePointUuid().getValue();

        if(computeP2PPathInput.getSep().get(1).getDirection()==PortDirection.INPUT)
            sipDst = computeP2PPathInput.getSep().get(1).getServiceInterfacePoint().getServiceInterfacePointUuid().getValue();

        if(sipDst==null){
            throw new BadRequestException("Cannot find SIP dst");
        }


        String epSrc =sipSrc.split("_")[0];
        String epDst = sipDst.split("_")[0];


        JSONObject eroObj = pceRestClient.computeLightPathRequest(computeP2PPathInput, epSrc, epDst);


        eroObj =  (JSONObject)eroObj.getJSONObject("path");
        Path path = jsonToPathConverter(eroObj);
        int channelNumber = getChannelNumberFromLightpath(path);

        if(channelNumber==NO_PROVISIONING_CONSTRAINT){
            LOG.error("Channel number not found in the PCE response.");
            throw new BadRequestException("Channel number not found in the PCE response");
        }

        List<String> linkListUuid = new ArrayList<>();

        HashMap<String, DeviceProvisioning> deviceProvisioningHashMap = getDeviceProvisioningMap(path);

        for(Link link: path.getLink()) {
            linkListUuid.add(link.getLinkUuid().getValue());

        }

        ConnectivityServiceBuilder connectivityServiceBuilder = new ConnectivityServiceBuilder();
        connectivityServiceBuilder.setUuid(new Uuid(path.getUuid()));
        List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.service.EndPoint> endPointList = new ArrayList<>();
        endPointList
                .add(new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.service.EndPointBuilder()
                        .setLocalId(epSrc).build());
        endPointList
                .add(new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.service.EndPointBuilder()
                        .setLocalId(epDst).build());


        connectivityServiceBuilder.setEndPoint(endPointList).setName(path.getName());

        for(String key: deviceProvisioningHashMap.keySet()){

            DeviceProvisioning deviceProvisioning = deviceProvisioningHashMap.get(key);
            QNodeType qNodeType = deviceProvisioning.getQnodeType();

            LightPathProvisioning lightPathProvisioning = getLightPathProvisioningDriver(deviceProvisioning.getNodeId(), qNodeType);
            LOG.info("Request to setup node "+deviceProvisioning.getNodeId());

            Double bandwidthConstraint = getBandwidthConstraint(originalPayload);

            boolean lightPathSetupSuccess = lightPathProvisioning.setupLightPath(channelNumber,deviceProvisioning.getSrcPort(),deviceProvisioning.getDstPort(), bandwidthConstraint);
            if(!lightPathSetupSuccess)
                throw new ExecutionException(new Throwable("Error during the setup of cross-connection within node "+deviceProvisioning.getNodeId()+" between port "+deviceProvisioning.getSrcPort()+" and port "+deviceProvisioning.getDstPort()));
        }


        LOG.info("Provisioning operation successfully made. Going to update link information on topology app.");
        boolean updateLightPathSuccess = topologyAppServiceConsumer.updateLightPathInfo(linkListUuid,channelNumber);
        if(!updateLightPathSuccess) {
            LOG.error("Unable to update light path.");
            throw new BadRequestException("Unable to update light path.");
        }

        List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.Connection> connectionList = getConnections(path);
        List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.service.Connection> connectionListForCs = new ArrayList<>();

        for(int i=0; i<connectionList.size(); i++){
            org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.service.Connection connectionForCs =
            new org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.service.ConnectionBuilder().setConnectionUuid(connectionList.get(i).getUuid()).build();
            connectionListForCs.add(connectionForCs);
        }


        connectivityServiceBuilder.setConnection(connectionListForCs);
        storeLightPathInformation(path, connectivityServiceBuilder.build(), connectionList);

        return path.getUuid().getValue();
    }


    private LightPathProvisioning getLightPathProvisioningDriver(String nodeId, QNodeType qNodeType){
        switch(qNodeType){
            case Lumentum:
                return new LumentumProvisioning(mountPointService,nodeId);

            case Endpoint:
            case Dummy:
                return new DummyProvisioning(mountPointService, nodeId);

            case Nll:
                return new NllProvisioning(mountPointService, nodeId);

            case Openroadm:
                QamNode qamNode = topologyAppServiceConsumer.getQamNodeWithCredentials(nodeId);
                if(qamNode==null){
                    LOG.warn("Unable to find OpenRoadm node, using default credentials. Not guarantee is going to work");
                    return new OpenRoadmProvisioning(mountPointService, nodeId,
                            "127.0.0.1", "830", "openroadm", "openroadm");//default dummy credentials
                }
                String host = qamNode.getHost();
                String port =qamNode.getPort();
                String username =qamNode.getUsername();
                String password = qamNode.getPassword();


                return new OpenRoadmProvisioning(mountPointService, nodeId,host, port, username, password);
        }
        throw new BadRequestException("QNode type not expected.");
    }

    private PortDirection getPortDirection(String nodeId, String portId) {
       QamNode qamNode= topologyAppServiceConsumer.getQamNode(nodeId);
       for(OwnedNodeEdgePoint ownedNodeEdgePoint: qamNode.getOwnedNodeEdgePoint()){
           if(ownedNodeEdgePoint.getUuid().getValue().equals(portId))
               return ownedNodeEdgePoint.getLinkPortDirection();
       }
       return PortDirection.UNIDENTIFIEDORUNKNOWN;
    }

    private QNodeType getNodeType(String nodeId) {
        QamNode qamNode= topologyAppServiceConsumer.getQamNode(nodeId);
        return qamNode.getNodeType();
    }

    private boolean storeLightPathInformation(Path path, ConnectivityService connectivityService, List<org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.connectivity.rev200616.connectivity.context.Connection> connectionList){
        //TODO add some checks
        LOG.info("Going to write lightpath with uuid :  "+path.getUuid().getValue());
        boolean areConnectionWritten = lightPathInventoryService.addConnectionsToConnectivityContext(connectionList);
        boolean isConnectivityServiceWritten = lightPathInventoryService.addConnectivityService(connectivityService);
        return lightPathInventoryService.writeLightPathIntoTree(path);
    }

    private boolean removeLightPathFromTree(String uuid){
        LOG.info("Going to remove lightpath with uuid :  "+uuid);
        lightPathInventoryService.removeConnectivityService(uuid);
        return  lightPathInventoryService.deleteLightPathFromTree(uuid);
    }

    public void removeLightPath(String lightPathUuid) throws ExecutionException, InterruptedException, IOException {
        if(!pceRestClient.removeLightPathRequest(lightPathUuid)){
            LOG.warn("Unable to find on PCE light path with uuid "+lightPathUuid);
        }

        Path lightPath = lightPathInventoryService.getLightPathFromTree(lightPathUuid);
        if(lightPath==null){
            LOG.error("Unable to find on data tree light path with uuid "+lightPathUuid);
            throw new BadRequestException("Unable to find on data tree light path with uuid "+lightPathUuid);
        }

        List<Link> linkList = lightPath.getLink();
        if(linkList.size()==0){
            LOG.warn("Unable to find links into lightpath with Uuid  "+lightPathUuid);
            throw new BadRequestException("Unable to find link associated with light path with Uuid  "+lightPathUuid);
        }

        List<String> listLinkUuid = new ArrayList<>();

        int channelNumber = getChannelNumberFromLightpath(lightPath);

        for(Link link: linkList){
            String linkUuid = link.getLinkUuid().getValue();
            listLinkUuid.add(linkUuid);
            QamLink qamLink = topologyAppServiceConsumer.getQamLink(linkUuid);
            LOG.info("Going to remove provisioning into link with Uuid "+qamLink.getUuid().getValue()+" for lightpath with uuid  "+lightPathUuid);
            LOG.info("Changing configuration to node "+qamLink.getNodeSrc()+ " at port "+qamLink.getPortSrc());
            LOG.info("Changing configuration to node "+qamLink.getNodeDst()+ " at port "+qamLink.getNodeDst());

        }
        LOG.info("Going to update topology app.");

        HashMap<String, DeviceProvisioning> deviceProvisioningHashMap = getDeviceProvisioningMap(lightPath);
        for(String key: deviceProvisioningHashMap.keySet()){
            DeviceProvisioning deviceProvisioning = deviceProvisioningHashMap.get(key);
            int channel= Integer.valueOf(deviceProvisioning.getChannel());
            QNodeType qNodeType = deviceProvisioning.getQnodeType();
            LightPathProvisioning lightPathProvisioning = getLightPathProvisioningDriver(deviceProvisioning.getNodeId(), qNodeType);
            LOG.info("Request to remove cross-connection within node "+deviceProvisioning.getNodeId());

          //Used only by OpenROADM device.
            boolean lightPathRemovalSuccess = lightPathProvisioning.removeLightPath(channel,deviceProvisioning.getSrcPort(),deviceProvisioning.getDstPort(), DEFAULT_BANDWIDTH_CONSTRAINT);
            if(!lightPathRemovalSuccess)
                throw new ExecutionException(new Throwable("Error during the removal of cross-connection of node "+deviceProvisioning.getNodeId()+" between port "+deviceProvisioning.getSrcPort()+" and port "+deviceProvisioning.getDstPort()));
        }

        boolean removeSuccess = removeLightPathFromTree(lightPathUuid);
        if(!removeSuccess){
            throw new BadRequestException("Unable to find lightpath with channel into data store "+lightPathUuid);
        }



        boolean topologyUpdateSuccess = topologyAppServiceConsumer.removeLightPath(listLinkUuid, channelNumber);
        if(!topologyUpdateSuccess)
            throw new BadRequestException("Error during updating of topology channel availability." );
    }

    private int getChannelNumberFromLightpath(Path lightpath){
        for(Name name: lightpath.getName()){
            if(name.getValueName().equals("channel")){
                return Integer.valueOf(name.getValue());
            }
        }
        LOG.error("Unable to find channel number into the lightpath with uuid "+lightpath.getUuid());
        throw new BadRequestException("Unable to find channel number into the lightpath with UUID " +lightpath.getUuid());
    }
    private class DeviceProvisioning{

        private String nodeId;
        private String srcPort;
        private String dstPort;
        private int channel;
        private QNodeType qNodeType;

        public DeviceProvisioning(String nodeId, int channel, QNodeType qNodeType){
            this.nodeId= nodeId;
            this.channel = channel;
            this.qNodeType = qNodeType;
        }
        public String getNodeId() {
            return nodeId;
        }

        public String getSrcPort() {
            return srcPort;
        }

        public void setSrcPort(String srcPort) {
            this.srcPort = srcPort;
        }

        public String getDstPort() {
            return dstPort;
        }

        public void setDstPort(String dstPort) {
            this.dstPort = dstPort;
        }

        public int getChannel() {
            return channel;
        }

        public QNodeType getQnodeType(){return qNodeType; }

    }
}
