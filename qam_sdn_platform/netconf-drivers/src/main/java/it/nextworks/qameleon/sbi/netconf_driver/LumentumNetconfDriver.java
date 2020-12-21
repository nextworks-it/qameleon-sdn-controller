package it.nextworks.qameleon.sbi.netconf_driver;

import org.opendaylight.mdsal.binding.api.MountPointService;
import org.opendaylight.mdsal.common.api.LogicalDatastoreType;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.attenuated.slot.entities.slots.Slot;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.Connections;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.ConnectionsBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.entities.connections.Connection;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.connection.rev170213.connection.top.Config;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.optical.rev170725.PortOpticalInput;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.ManagedPort;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.PortExtension;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.PhysicalPorts;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.PhysicalPortsBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.physical.ports.PhysicalPort;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.port.rev170621.physical.port.entities.physical.ports.PhysicalPortBuilder;
import org.opendaylight.yang.gen.v1.http.www.lumentum.com.lumentum.ote.types.rev170621.DistinguishedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LumentumNetconfDriver extends NetconfDriver {
    private static final Logger LOG = LoggerFactory.getLogger(LumentumNetconfDriver.class);

    public LumentumNetconfDriver(MountPointService mountPointService, String lumentumId) {
        super(mountPointService, lumentumId);
    }

    //TODO fix when integration comes in place
    public PhysicalPorts getPhysicalPorts(){//Does not work. TO FIXX
        Optional<PhysicalPorts> physicalPorts = (Optional<PhysicalPorts>)readFromDataStore(PhysicalPorts.class, LogicalDatastoreType.OPERATIONAL);
        if(physicalPorts==null) {
            LOG.error("Cannot retrieve status of Lumentum ports");
            return null;
        }
        return physicalPorts.get();
    }


    public PhysicalPorts getPhysicalPortsMock() {

       List<PhysicalPort> physicalPortList = new ArrayList<PhysicalPort>();
        List<Class<? extends PortExtension>> list = new ArrayList<>();
        list.add(ManagedPort.class);
        list.add(PortOpticalInput.class);

        for(int i = 0; i<20; i++) {

            PhysicalPort physicalPortMuxIn = new PhysicalPortBuilder()
                            .setPortExtension(list)
                            .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=41"+LumentumUtil.leftPadZero(i)))
                             .build();

            PhysicalPort physicalPortDemuxOut = new PhysicalPortBuilder()
                    .setPortExtension(list)
                    .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=52"+LumentumUtil.leftPadZero(i)))
                    .build();

           // LOG.info("Adding mock physical port: "+"ne=1;chassis=1;card=1;port=41"+LumentumUtil.leftPadZero(i));
           // LOG.info("Adding mock physical port: "+"ne=1;chassis=1;card=1;port=52"+LumentumUtil.leftPadZero(i));
            physicalPortList.add(physicalPortMuxIn);
            physicalPortList.add(physicalPortDemuxOut);

        }
        PhysicalPort physicalPortDemuxIn = new PhysicalPortBuilder()
                .setPortExtension(list)
                .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=51"+LumentumUtil.leftPadZero(1)))
                .build();

        PhysicalPort physicalPortMuxOut = new PhysicalPortBuilder()
                .setPortExtension(list)
                .setDn(new DistinguishedName("ne=1;chassis=1;card=1;port=42"+LumentumUtil.leftPadZero(1)))
                .build();

        physicalPortList.add(physicalPortDemuxIn);
        physicalPortList.add(physicalPortMuxOut);

        return new PhysicalPortsBuilder().setPhysicalPort(physicalPortList).build();

    }


    public Connections getLumentumConnections(){
        Optional<Connections> connections = (Optional<Connections>)readFromDataStore(Connections.class, LogicalDatastoreType.OPERATIONAL);
        if(connections==null) {
            LOG.warn("Lumentum device cannot be reached. Please check if it is up and running.");
            return null;
        }
        if(!connections.isPresent() || connections.get().getConnection()==null){
            LOG.warn("No connections were found.");
            return new ConnectionsBuilder().setConnection(new ArrayList<>()).build();
        }

        for(Connection connection: connections.get().getConnection()){
            Config config = connection.getConfig();
            LOG.info("DN:" +connection.getDn().getValue());
            LOG.info("Slots");
            if(config.getSlots()==null) {
                LOG.warn("There are no slot available");
                continue;
            }
            for(Slot slot: config.getSlots().getSlot()){
                LOG.info("Slot key:" +slot.key().toString());

            }
        }

        return connections.get();
    }

    }

