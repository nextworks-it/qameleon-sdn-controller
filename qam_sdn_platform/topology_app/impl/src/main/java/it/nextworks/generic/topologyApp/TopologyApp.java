package it.nextworks.generic.topologyApp;

import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.*;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.Topology;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.link.NodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.link.NodeEdgePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePointBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Link;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.LinkBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.NodeBuilder;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.context.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class TopologyApp {

    protected Topology topology;

    /*
    * Internal mapping between OwnedNodeEdgePoint under the node package and NodeEdgePoint under the link package. 
    * This is done to reduce the computational
    * cost for "areDirectlyConnected" method.
    * */
    private Map<OwnedNodeEdgePoint, NodeEdgePoint> nodeEdgePointMap;
    private static final Logger LOG = LoggerFactory.getLogger(TopologyApp.class);


    public TopologyApp(Topology topology){
        this.topology=topology;
    }

    
    public TopologyApp(){
        Uuid topologyUuid = getRandomUuid();
        LOG.info("Created empty topology with Uuid: "+topologyUuid);
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        topology =  topologyBuilder.setUuid(topologyUuid).setLink(linkList).setNode(nodeList).build();
        nodeEdgePointMap = new HashMap<>();
    }

    // Creates empty topology with Uuid passed as parameter
    public TopologyApp(String name){
        Uuid topologyUuid = new Uuid(name);
        LOG.info("Created empty topology with Uuid: "+topologyUuid);
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        topology =  topologyBuilder.setUuid(topologyUuid).setLink(linkList).setNode(nodeList).build();
        nodeEdgePointMap = new HashMap<>();
    }


    private Uuid getRandomUuid(){
        String uuid = UUID.randomUUID().toString();
        return new Uuid(uuid);
    }

    protected void addNodeIntoTopology(Node node){
        TopologyBuilder topologyBuilder = new TopologyBuilder(topology);
        List<Node> nodeList = topology.getNode();
        nodeList.add(node);
        topology = topologyBuilder.setNode(nodeList).build();
    }

    public void updateTopologyLinks(List<Link> linkList){
        TopologyBuilder topologyBuilder = new TopologyBuilder(topology);
        topology = topologyBuilder.setLink(linkList).build();
    }

    public OwnedNodeEdgePoint createNodeEdgePoint(PortDirection portDirection){
        OwnedNodeEdgePointBuilder nodeEdgePointBuilder = new OwnedNodeEdgePointBuilder();
        Uuid nodeEdgePointUuid = getRandomUuid();
        LOG.info("Created node edge point with Uuid: "+nodeEdgePointUuid);

        OwnedNodeEdgePoint nodeEdgePoint = nodeEdgePointBuilder.setUuid(nodeEdgePointUuid)
                .setAdministrativeState(AdministrativeState.UNLOCKED)
                .setLifecycleState(LifecycleState.INSTALLED)
                .setLinkPortDirection(portDirection).build();

        return nodeEdgePoint;
    }

    private NodeEdgePoint createNodeEdgePointForLink(Uuid nodeEdgePointUuid, Uuid nodeUuid){
       NodeEdgePointBuilder nodeEdgePointBuilder = new NodeEdgePointBuilder();
       return nodeEdgePointBuilder.setNodeEdgePointUuid(nodeEdgePointUuid)
       .setTopologyUuid(topology.getUuid())
       .setNodeUuid(nodeUuid).build();
    }

    public void addNode(List<OwnedNodeEdgePoint> nodeEdgePointList){

        NodeBuilder nodeBuilder = new NodeBuilder();
        Uuid nodeUuid = getRandomUuid();
        Node node = nodeBuilder.
                setUuid(new Uuid(nodeUuid)).
                setOwnedNodeEdgePoint(nodeEdgePointList)
                .setOperationalState(OperationalState.ENABLED)
                .build();

        for(OwnedNodeEdgePoint nodeEdgePoint: nodeEdgePointList){
            nodeEdgePointMap.put(nodeEdgePoint, createNodeEdgePointForLink(nodeEdgePoint.getUuid(), nodeUuid));
        }

        //Update the topology
        addNodeIntoTopology(node);
        LOG.info("Created node with node edge point with with Uuid: "+nodeUuid);
    }

    public Node getNode(String nodeId){
        Uuid nodeUuid = new Uuid(nodeId);
        for(Node node: topology.getNode()){
            if(nodeUuid.equals(node.getUuid()))
                return node;
        }
        //LOG.info("Node with Uuid "+nodeUuid+" not found into the topology.");
        return null;
    }

    public Node addNode(String nodeId){
        Node existingNode = getNode(nodeId);
        if(existingNode!=null)
            return existingNode;

        NodeBuilder nodeBuilder = new NodeBuilder();
        Uuid nodeUuid = new Uuid(nodeId);
        Node node = nodeBuilder.
                setUuid(new Uuid(nodeUuid))
                .setOperationalState(OperationalState.ENABLED)
                .setOwnedNodeEdgePoint(new ArrayList<>())
                .build();
        addNodeIntoTopology(node);
        LOG.info("Created node (no node edge points) with with Uuid: "+nodeUuid);
        return node;
    }


    public void removeNode(String nodeId){
        Node nodeToRemove = null;
        List<Node> nodeList = topology.getNode();
        Uuid nodeUuid = new Uuid(nodeId);
        for(Node node: topology.getNode()){
            if(node.getUuid().equals(nodeUuid)){
                nodeToRemove = node;
                break;
            }
        }
        boolean nodeRemoved = false;
        if(nodeToRemove!=null)
            nodeRemoved = nodeList.remove(nodeToRemove);

        if(!nodeRemoved){
            LOG.info("Unable to find the node with Uuid: "+nodeUuid);
            return;
        }

        //update Node list
        TopologyBuilder topologyBuilder= new TopologyBuilder(topology);
        topology = topologyBuilder.setNode(nodeList).build();

        List<Link> linkList= topology.getLink();

        List<Uuid> uuidListLinkToRemove = new ArrayList<>();
        Map<Integer,NodeEdgePoint> linkIndexNodeEdgePointMapping = new HashMap<>();

        //Update Link list: going to remove any node edge point reference of just deleted node into the topology links.
        for(int i=0; i<linkList.size(); i++){
            Link link = linkList.get(i);
            for(NodeEdgePoint nodeEdgePoint: link.getNodeEdgePoint()){
                //Case #1. In the below case a link has more than two node edge points references. In this case only the node edge point references of just deleted node are flagged.
                if(nodeEdgePoint.getNodeUuid().equals(nodeToRemove.getUuid()) && link.getNodeEdgePoint().size()>2){
                    linkIndexNodeEdgePointMapping.put(i,nodeEdgePoint);
                    break;
                }
                //Case #2. Removing a node edge point reference into a link with only two node edge points references means to have a pending link.
                // For this reason the link is deleted as well.
                if(nodeEdgePoint.getNodeUuid().equals(nodeToRemove.getUuid()) && link.getNodeEdgePoint().size()<=2){
                    uuidListLinkToRemove.add(link.getUuid());
                }

            }

        }

        //Removing the node edge point references flagged. in case #1
        for(Integer linkIndex: linkIndexNodeEdgePointMapping.keySet()){
            Uuid uuidNodeEdgePointToRemove = linkIndexNodeEdgePointMapping.get(linkIndex).getNodeEdgePointUuid();
            boolean removed= linkList.get(linkIndex).getNodeEdgePoint().remove(linkIndexNodeEdgePointMapping.get(linkIndex));
            LOG.info("Node edge point reference with Uuid "+uuidNodeEdgePointToRemove+" removed: "+removed);
        }

        updateTopologyLinks(linkList);
        //Removing the link flagged in case #2.
        if(uuidListLinkToRemove.size()>0){
            for(Uuid uuidLinkToRemove: uuidListLinkToRemove){
                removeLink(uuidLinkToRemove.getValue());
            }
        }

    }


    private void updateNodeWithinTopology(Node node){
        List<Node> nodeList = topology.getNode();
        for(int i =0; i<nodeList.size(); i++){
            if(nodeList.get(i).getUuid().equals(node.getUuid())){
                nodeList.set(i,node);
                break;
            }
        }
        topology = new TopologyBuilder(topology).setNode(nodeList).build();
    }


    private OwnedNodeEdgePoint addNodeEdgePointToNode(Node node, PortDirection direction){
        OwnedNodeEdgePoint nodeEdgePoint = createNodeEdgePoint(direction);
        List<OwnedNodeEdgePoint> nodeEdgePointList = node.getOwnedNodeEdgePoint();
        nodeEdgePointList.add(nodeEdgePoint);
        Node nodeSrcUpdated = new NodeBuilder(node).setOwnedNodeEdgePoint(nodeEdgePointList).build();
        nodeEdgePointMap.put(nodeEdgePoint, createNodeEdgePointForLink(nodeEdgePoint.getUuid(), node.getUuid()));
        updateNodeWithinTopology(nodeSrcUpdated);
        return nodeEdgePoint;
    }


    public Link getLink(String linkId){
        Uuid linkUuid = new Uuid(linkId);
        for(Link link: topology.getLink()){
            if(linkUuid.equals(link.getUuid()))
                return link;
        }
        LOG.info("Link with Uuid "+linkId+" not found into the topology.");
        return null;
    }

    public Link addLink(String linkId, String nodeSrcId, String nodeDstId, ForwardingDirection linkDirection){
        return addLink(linkId, getNode(nodeSrcId), getNode(nodeDstId), linkDirection);
    }


    public Link addLink(String linkId, Node nodeSrc, Node nodeDst, ForwardingDirection linkDirection){
        if(linkDirection==ForwardingDirection.UNIDIRECTIONAL){
            OwnedNodeEdgePoint nodeEdgePointSrc = addNodeEdgePointToNode(nodeSrc,PortDirection.OUTPUT);
            OwnedNodeEdgePoint nodeEdgePointDst = addNodeEdgePointToNode(nodeDst,PortDirection.INPUT);
            LOG.info("Created node edge point output and node edge point input for node source and node destination, respectively ");
            return addLink(linkId, nodeEdgePointSrc, nodeEdgePointDst, ForwardingDirection.UNIDIRECTIONAL);
        }

        // The nodeSrc node can reach the nodeDst and viceversa
        if(linkDirection==ForwardingDirection.BIDIRECTIONAL){
            OwnedNodeEdgePoint nodeEdgePointSrc = addNodeEdgePointToNode(nodeSrc,PortDirection.BIDIRECTIONAL);
            OwnedNodeEdgePoint nodeEdgePointDst = addNodeEdgePointToNode(nodeDst,PortDirection.BIDIRECTIONAL);
                LOG.info("Created two bidirectional owned node edge points for node "+nodeSrc.getUuid().getValue()+" (src) and "+nodeDst.getUuid().getValue()+" (dst)");
                return addLink(linkId, nodeEdgePointSrc, nodeEdgePointDst, ForwardingDirection.BIDIRECTIONAL);
        }
        LOG.info("Unable to create a link: the direction is neither unidirectional nor bidirectional");
        return null;
    }


    public Link addLink(String linkUuid, OwnedNodeEdgePoint nodeEdgePointOne, OwnedNodeEdgePoint nodeEdgePointTwo, ForwardingDirection forwardingDirection){
        LinkBuilder linkBuilder = new LinkBuilder();

        NodeEdgePointBuilder nodeEdgePointBuilder
                = new NodeEdgePointBuilder(nodeEdgePointMap.get(nodeEdgePointOne));

      NodeEdgePoint nodeEdgePoint = nodeEdgePointBuilder.build();

        NodeEdgePointBuilder nodeEdgePointBuilder2
                = new NodeEdgePointBuilder(nodeEdgePointMap.get(nodeEdgePointTwo));

       NodeEdgePoint nodeEdgePoint2 = nodeEdgePointBuilder2.build();

        List<NodeEdgePoint> nodeEdgePointList = new ArrayList<>();
        nodeEdgePointList.add(nodeEdgePoint);
        nodeEdgePointList.add(nodeEdgePoint2);

        Link link =  linkBuilder.setUuid(new Uuid(linkUuid))
                .setNodeEdgePoint(nodeEdgePointList)
                .setDirection(forwardingDirection)
                .setOperationalState(OperationalState.ENABLED)
                .setAdministrativeState(AdministrativeState.UNLOCKED).build();

        //Update the topology
        TopologyBuilder topologyBuilder = new TopologyBuilder(topology);
        List<Link> linkList = topology.getLink();
        linkList.add(link);
        topology = topologyBuilder.setLink(linkList).build();
        LOG.info("Added link with Uuid: "+linkUuid+ " between node edge point "+nodeEdgePoint.getNodeEdgePointUuid()+" and node edge point "+nodeEdgePoint2.getNodeEdgePointUuid());
        return link;
    }


    public void removeLink(String linkId){
        List<Link> linkList= topology.getLink();
        Link linkToRemove = null;
        Uuid linkUuid = new Uuid(linkId);
        for(Link link: linkList){
            if(linkUuid.equals(link.getUuid())){
                linkToRemove=link;
                break;
            }
        }
        if(linkToRemove!=null){
            linkList.remove(linkToRemove);
            updateTopologyLinks(linkList);
        }
    }

    public boolean areDirectlyConnected(Node nodeSrc, Node nodeDst){
        boolean foundNodeOne = false;
        boolean foundNodeTwo = false;
        Link linkSrcDst = null;
        Uuid linkUuid = null;
       for(Link link: topology.getLink()){
           for(NodeEdgePoint nodeEdgePoint: link.getNodeEdgePoint()){
                if(nodeEdgePoint.getNodeUuid().equals(nodeSrc.getUuid())){
                    foundNodeOne=true;
                    linkUuid = link.getUuid();
                    linkSrcDst=link;
                }
               if(nodeEdgePoint.getNodeUuid().equals(nodeDst.getUuid())){
                   foundNodeTwo=true;
               }
           }
       }
        if(foundNodeOne && foundNodeTwo){
            LOG.info("Node with Uuid: "+nodeSrc.getUuid().getValue()+
                    " is directly connected with node with Uuid "+nodeDst.getUuid().getValue()+
                    " through link with Uuid "+linkUuid.getValue());
            linkSrcDst.getDirection();
            return true;
        }

       LOG.info("Node with Uuid: "+nodeSrc.getUuid().getValue()+" and node with Uuid "+nodeDst.getUuid().getValue()+" are not directly connected.");
        return false;
    }

    public String getLinkUuid(String nodeSrcId, String nodeDstId){
        boolean foundNodeOne = false;
        boolean foundNodeTwo = false;
        String linkUuid = null;

        for(Link link: topology.getLink()){
            foundNodeOne = false;
            foundNodeTwo = false;
            for(NodeEdgePoint nodeEdgePoint: link.getNodeEdgePoint()){
                if(nodeEdgePoint.getNodeUuid().getValue().equals(nodeSrcId)){
                    foundNodeOne=true;
                }
                if(nodeEdgePoint.getNodeUuid().getValue().equals(nodeDstId)){
                    foundNodeTwo=true;
                }
                if(foundNodeOne && foundNodeTwo){
                    linkUuid = link.getUuid().getValue();
                    LOG.info("Node with Uuid: "+nodeSrcId+
                            " is directly connected with node with Uuid "+nodeDstId+
                            " through link with Uuid "+linkUuid);
                    return linkUuid;
                }
            }
        }

        LOG.info("Node with Uuid: "+nodeSrcId+" and node with Uuid "+nodeDstId+" are not directly connected.");
        return null;
    }


    public Topology getTopology() {
        return topology;
    }
}
