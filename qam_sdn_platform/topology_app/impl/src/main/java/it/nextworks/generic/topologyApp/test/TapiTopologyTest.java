package it.nextworks.generic.topologyApp.test;

import it.nextworks.generic.topologyApp.TopologyApp;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.ForwardingDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.PortDirection;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.topology.Node;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.topology.rev200423.node.OwnedNodeEdgePoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TapiTopologyTest {
    private static final Logger LOG = LoggerFactory.getLogger(TapiTopologyTest.class);
    public TapiTopologyTest(){}

    public void test(){
         TopologyApp topologyApp = new TopologyApp("aaa");

        //nodeOne is a node with one bidirectional node edge point
        OwnedNodeEdgePoint nodeEdgePointOne = topologyApp.createNodeEdgePoint(PortDirection.BIDIRECTIONAL);
        List<OwnedNodeEdgePoint> nodeEdgePointList = new ArrayList<OwnedNodeEdgePoint>();
        nodeEdgePointList.add(nodeEdgePointOne);
        topologyApp.addNode(nodeEdgePointList);

        //nodeTwo is a node with one bidirectional node edge point
        OwnedNodeEdgePoint nodeEdgePointTwo = topologyApp.createNodeEdgePoint(PortDirection.BIDIRECTIONAL);
        List<OwnedNodeEdgePoint> nodeEdgePointList2 = new ArrayList<OwnedNodeEdgePoint>();
        nodeEdgePointList2.add(nodeEdgePointTwo);
        topologyApp.addNode(nodeEdgePointList2);

        Node nodeOne = topologyApp.getTopology().getNode().get(0);
        Node nodeTwo = topologyApp.getTopology().getNode().get(1);
        topologyApp.areDirectlyConnected(nodeOne, nodeTwo);

        topologyApp.addLink("aa",nodeEdgePointOne,nodeEdgePointTwo, ForwardingDirection.BIDIRECTIONAL);
        //tapiTopology.printTopologyInfo();
        topologyApp.areDirectlyConnected(nodeOne, nodeTwo);
        LOG.info("Going to remove a node");
        topologyApp.removeNode(nodeOne.getUuid().getValue());

    }

    public void test2(){
        TopologyApp topologyApp = new TopologyApp("aaa");
        OwnedNodeEdgePoint nodeEdgePointOne = topologyApp.createNodeEdgePoint(PortDirection.OUTPUT);
        List<OwnedNodeEdgePoint> nodeEdgePointList = new ArrayList<OwnedNodeEdgePoint>();
        nodeEdgePointList.add(nodeEdgePointOne);
        topologyApp.addNode(nodeEdgePointList);

        //nodeTwo is a node with one bidirectional node edge point
        OwnedNodeEdgePoint nodeEdgePointTwo = topologyApp.createNodeEdgePoint(PortDirection.INPUT);
        List<OwnedNodeEdgePoint> nodeEdgePointList2 = new ArrayList<OwnedNodeEdgePoint>();
        nodeEdgePointList2.add(nodeEdgePointTwo);
        topologyApp.addNode(nodeEdgePointList2);

        Node nodeOne = topologyApp.getTopology().getNode().get(0);
        Node nodeTwo = topologyApp.getTopology().getNode().get(1);
        topologyApp.areDirectlyConnected(nodeOne, nodeTwo);

        topologyApp.addLink("linkUuid",nodeEdgePointOne,nodeEdgePointTwo, ForwardingDirection.UNIDIRECTIONAL);
        //tapiTopology.printTopologyInfo();
        topologyApp.areDirectlyConnected(nodeOne, nodeTwo);
        LOG.info("Going to remove a node");
        topologyApp.removeNode(nodeOne.getUuid().getValue());
    }



    public void test3(){
        TopologyApp topologyApp = new TopologyApp("topologyId");
        Node a = topologyApp.addNode("a");
        Node b = topologyApp.addNode("b");
        Node c = topologyApp.addNode("c");
        Node d = topologyApp.addNode("d");
        Node e = topologyApp.addNode("e");
        Node f = topologyApp.addNode("f");

        topologyApp.addLink("ab-link-uni",a,b,ForwardingDirection.UNIDIRECTIONAL);

        topologyApp.addLink("cd-link-ubid",c,d,ForwardingDirection.BIDIRECTIONAL);

        topologyApp.addLink("ef-link-bid",e,f,ForwardingDirection.BIDIRECTIONAL);

    }
    public void test4(){
        TopologyApp topologyApp = new TopologyApp("abc");
        Node a = topologyApp.addNode("a");
        Node b = topologyApp.addNode("b");
        Node c = topologyApp.addNode("c");
        Node d = topologyApp.addNode("d");

        topologyApp.addLink("ab-link-uni",a,b,ForwardingDirection.UNIDIRECTIONAL);
        topologyApp.addLink("ac-link-bid",a,c,ForwardingDirection.BIDIRECTIONAL);
        topologyApp.addLink("bc-link-uni",b,c,ForwardingDirection.UNIDIRECTIONAL);
        topologyApp.addLink("ad-link-bid",a,d,ForwardingDirection.BIDIRECTIONAL);
        topologyApp.addLink("db-link-inu",d,b,ForwardingDirection.UNIDIRECTIONAL);
    }

}
