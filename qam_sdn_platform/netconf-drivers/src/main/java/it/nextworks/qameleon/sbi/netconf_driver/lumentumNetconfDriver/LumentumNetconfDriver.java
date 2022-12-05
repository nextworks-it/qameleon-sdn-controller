package it.nextworks.qameleon.sbi.netconf_driver.lumentumNetconfDriver;

import it.nextworks.qameleon.sbi.netconf_driver.NetconfDriver;
import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.binding.api.RpcConsumerRegistry;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.*;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.Connections;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.ConnectionsBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.connections.Connection;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.optical.rev170725.PortOpticalInput;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.ManagedPort;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.PortExtension;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.PhysicalPorts;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.PhysicalPortsBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.physical.ports.PhysicalPort;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.physical.ports.PhysicalPortBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.DistinguishedName;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.OpticalPowerRelative;
import org.opendaylight.yangtools.yang.common.RpcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class LumentumNetconfDriver extends NetconfDriver {
    private static final Logger LOG = LoggerFactory.getLogger(LumentumNetconfDriver.class);

    public LumentumNetconfDriver(MountPointService mountPointService, String lumentumId) {
        super(mountPointService, lumentumId);
    }


    public PhysicalPorts getPhysicalPorts(){
        Optional<PhysicalPorts> physicalPorts = (Optional<PhysicalPorts>)readFromDataStore(PhysicalPorts.class, LogicalDatastoreType.OPERATIONAL);
        if(physicalPorts==null) {
            LOG.error("Cannot retrieve status of Lumentum ports");
            return null;
        }
        return physicalPorts.get();
    }


    public PhysicalPorts getPhysicalPortsMock() {
        LOG.info("Getting physical Ports information");
       List<PhysicalPort> physicalPortList = new ArrayList<PhysicalPort>();
        List<Class<? extends PortExtension>> portExtention = new ArrayList<>();
        portExtention.add(ManagedPort.class);
        portExtention.add(PortOpticalInput.class);

        for(int i = 0; i<20; i++) {

            PhysicalPort physicalPortMuxIn = new PhysicalPortBuilder()
                            .setPortExtension(portExtention)
                            .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=41"+ LumentumUtil.leftPadZero(i)))
                             .build();

            PhysicalPort physicalPortDemuxOut = new PhysicalPortBuilder()
                    .setPortExtension(portExtention)
                    .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=52"+LumentumUtil.leftPadZero(i)))
                    .build();

           // LOG.info("Adding mock physical port: "+"ne=1;chassis=1;card=1;port=41"+LumentumUtil.leftPadZero(i));
           // LOG.info("Adding mock physical port: "+"ne=1;chassis=1;card=1;port=52"+LumentumUtil.leftPadZero(i));
            physicalPortList.add(physicalPortMuxIn);
            physicalPortList.add(physicalPortDemuxOut);

        }
        PhysicalPort physicalPortDemuxIn = new PhysicalPortBuilder()
                .setPortExtension(portExtention)
                .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=51"+LumentumUtil.leftPadZero(1)))
                .build();

        PhysicalPort physicalPortMuxOut = new PhysicalPortBuilder()
                .setPortExtension(portExtention)
                .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=42"+LumentumUtil.leftPadZero(1)))
                .build();

        PhysicalPort lineInOutPort = new PhysicalPortBuilder()
                .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=3001"))
                .build();

        physicalPortList.add(physicalPortDemuxIn);
        physicalPortList.add(physicalPortMuxOut);
        physicalPortList.add(lineInOutPort);

        return new PhysicalPortsBuilder().setPhysicalPort(physicalPortList).build();

    }


    public boolean removeCrossConnection(DistinguishedName distinguishedName){
        LOG.info("Removing Lumentum cross-connection");
        RpcConsumerRegistry rpcConsumerRegistry = getRpcConsumerRegistry();
        LumentumOteConnectionService lumentumOteConnectionService = rpcConsumerRegistry.getRpcService(LumentumOteConnectionService.class);
        DeleteConnectionInput deleteConnectionInput = new DeleteConnectionInputBuilder ().setDn(distinguishedName).build();
        try {
            RpcResult<DeleteConnectionOutput> rpcResult = lumentumOteConnectionService.deleteConnection(deleteConnectionInput).get();
            return rpcResult.isSuccessful();

        } catch (ExecutionException | InterruptedException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean ConfigureCcConnectionRpc(ConfigureCcConnectionInput configureCcConnectionInput){
        RpcConsumerRegistry rpcConsumerRegistry = getRpcConsumerRegistry();
        LumentumOteConnectionService lumentumOteConnectionService = rpcConsumerRegistry.getRpcService(LumentumOteConnectionService.class);
        try {
            RpcResult<ConfigureCcConnectionOutput> rpcResult = lumentumOteConnectionService.configureCcConnection(configureCcConnectionInput).get();
            return rpcResult.isSuccessful();

        } catch (ExecutionException | InterruptedException e) {
            LOG.error(e.getMessage());
            return false;
        }
    }

    public boolean setupLumentumCrossConnection(Connection connection){
        LOG.info("Setting up cross-connection within Lumentum whose Node ID is "+getNodeId());
        RpcConsumerRegistry rpcConsumerRegistry = getRpcConsumerRegistry();

        LumentumOteConnectionService lumentumOteConnectionService = rpcConsumerRegistry.getRpcService(LumentumOteConnectionService.class);
        AddConnectionInput addConnectionInput = new AddConnectionInputBuilder()
                .setCustomName(connection.getConfig().getCustomName())
                .setDn(connection.getDn())
                .setStartFreq(connection.getConfig().getStartFreq())
                .setEndFreq(connection.getConfig().getEndFreq())
                .setInputPortReference(connection.getConfig().getInputPortReference())
                .setOutputPortReference(connection.getConfig().getOutputPortReference())
                .setMaintenanceState(connection.getConfig().getMaintenanceState())
                .setAttenuation(new OpticalPowerRelative(new BigDecimal(5)))
                .setBlocked(false)
                .build();

        try {
            RpcResult<AddConnectionOutput> rpcResult = lumentumOteConnectionService.addConnection(addConnectionInput).get();
            boolean successRpc = rpcResult.isSuccessful();
            LOG.info("Success RPC is "+successRpc);
            return successRpc;

        } catch (ExecutionException | InterruptedException e) {
           LOG.error("Error setting up cross-connection within Lumentum whose Node ID is "+getNodeId());
           LOG.error(e.getMessage());
           return false;
           }

    }
    public Connections getLumentumConnections(){
        Optional<Connections> connections = (Optional<Connections>)readFromDataStore(Connections.class, LogicalDatastoreType.OPERATIONAL);
        if(connections==null) {
            LOG.warn("Lumentum device whose Node ID is "+getNodeId()+" cannot be reached. Please check if it is up and running.");
            return null;
        }
        if(!connections.isPresent() || connections.get().getConnection()==null){
            LOG.warn("No connections were found into Lumentum whose Node ID is "+getNodeId());
            return new ConnectionsBuilder().setConnection(new ArrayList<>()).build();
        }
        return connections.get();
    }

    }

