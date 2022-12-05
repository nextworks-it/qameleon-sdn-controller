package it.nextworks.qameleon.pce.PCE;

import java.util.List;

public class LinkInfo {


    private String linkId;
    private String nodeSrc;
    private String nodeDst;
    private String portSrc;
    private String portDst;
    private List<Integer> availableChannels;
    private double weight;

    public LinkInfo(String linkId, String nodeSrc, String nodeDst, String portSrc, String portDst, List<Integer> availableChannles, double weight){
        this.linkId = linkId;
        this.nodeSrc = nodeSrc;
        this.nodeDst = nodeDst;
        this.portSrc = portSrc;
        this.portDst = portDst;
        this.availableChannels = availableChannles;
        this.weight = weight;
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


    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
