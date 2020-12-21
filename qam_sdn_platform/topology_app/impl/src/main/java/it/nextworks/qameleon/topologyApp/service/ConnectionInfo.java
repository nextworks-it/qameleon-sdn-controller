package it.nextworks.qameleon.topologyApp.service;

public class ConnectionInfo {

    private String linkId;
    private String nodeSrcId;
    private String nodeDstId;

    public ConnectionInfo(String linkId, String nodeSrcId, String nodeDstId){
        this.linkId = linkId;
        this.nodeSrcId = nodeSrcId;
        this.nodeDstId = nodeDstId;
    }

    public String getLinkId() {
        return linkId;
    }

    public String getNodeSrcId() {
        return nodeSrcId;
    }

    public String getNodeDstId() {
        return nodeDstId;
    }

}
