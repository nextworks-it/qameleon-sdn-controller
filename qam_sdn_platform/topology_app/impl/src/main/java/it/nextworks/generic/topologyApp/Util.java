package it.nextworks.generic.topologyApp;

import org.opendaylight.yang.gen.v1.urn.opendaylight.netconf.node.topology.rev150114.network.topology.topology.topology.types.TopologyNetconf;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.LinkId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NetworkTopology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.TopologyId;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.Topology;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.TopologyKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Link;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.LinkKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.Node;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.network.topology.topology.NodeKey;
import org.opendaylight.yang.gen.v1.urn.tbd.params.xml.ns.yang.network.topology.rev131021.NodeId;
import org.opendaylight.yangtools.yang.binding.InstanceIdentifier;

public class Util {


    private Util() {
        throw new IllegalStateException("Instantiating utility class.");
    }

    //    public static final InstanceIdentifier<Node> NETCONF_DEVICE_NODE_IID =
//            InstanceIdentifier
//                    .create(NetworkTopology.class)
//                    .child(Topology.class,
//                            new TopologyKey(new TopologyId(TopologyNetconf.QNAME.getLocalName())))
//                    .child(Node.class,
//                            new NodeKey(new NodeId("new-netconf-device")));

    public static final InstanceIdentifier<Topology> NETCONF_TOPOLOGY_IID =
            InstanceIdentifier.builder(NetworkTopology.class)
                    .child(Topology.class, new TopologyKey(new TopologyId(TopologyNetconf.QNAME.getLocalName())))
                    .build();

    public static InstanceIdentifier<Node> netconfNodeIid(final String nodeId) {
        return NETCONF_TOPOLOGY_IID.child(Node.class, new NodeKey(new NodeId(nodeId)));
    }

    public static InstanceIdentifier<Link> netconfLinkIid(final String linkId) {
        return NETCONF_TOPOLOGY_IID.child(Link.class, new LinkKey(new LinkId(linkId)));
    }

   
    public static InstanceIdentifier<Node> nodeIIdentifier(final String topologyId, final String nodeId) {
        return InstanceIdentifier.builder(NetworkTopology.class)
                .child(Topology.class, new TopologyKey(new TopologyId(topologyId)))
                .child(Node.class, new NodeKey(new NodeId(nodeId)))
                .build();
    }


    public static InstanceIdentifier<Link> linkIIdentifier(final String topologyId, final String linkId) {
        return InstanceIdentifier.builder(NetworkTopology.class)
                .child(Topology.class, new TopologyKey(new TopologyId(topologyId)))
                .child(Link.class, new LinkKey(new LinkId(linkId)))
                .build();
    }


    public static InstanceIdentifier<Topology> topologyIIdentifier(final String topologyName) {
        return InstanceIdentifier.builder(NetworkTopology.class)
                .child(Topology.class, new TopologyKey(new TopologyId(topologyName)))
                .build();
    }
}
