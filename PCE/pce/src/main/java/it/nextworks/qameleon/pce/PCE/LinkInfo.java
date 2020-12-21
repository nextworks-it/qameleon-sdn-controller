package it.nextworks.qameleon.pce.PCE;

import java.util.List;

public class LinkInfo {


    private String linkId;
    private String nodeSrc;
    private String nodeDst;
    private String portSrc;
    private String portDst;
    private List<Integer> availableChannels;

    public LinkInfo(String linkId, String nodeSrc, String nodeDst, String portSrc, String portDst, List<Integer> availableChannles){
        this.linkId = linkId;
        this.nodeSrc = nodeSrc;
        this.nodeDst = nodeDst;
        this.portSrc = portSrc;
        this.portDst = portDst;
        this.availableChannels = availableChannles;
    }

    public String getNodeSrc() {
        return nodeSrc;
    }

    public String getNodeDst() {
        return nodeDst;
    }

    public String getPortSrc() {
        return portSrc;
    }

    public String getPortDst() {
        return portDst;
    }

    public List<Integer> getAvailableChannels() {
        return availableChannels;
    }

    public String getLinkId() {
        return linkId;
    }

}
