package it.nextworks.qameleon.sbi.netconf_driver.openRoadmDriver;

public class RoadmConnectionInfo {
    private String connectionName;
    private String sourceNmcInterfaceName;
    private String sourceNmcSupportingPort;

    private String destinationNmcInterfaceName;
    private String destinationNmcSupportingPort;


    public RoadmConnectionInfo(String connectionName, String sourceNmcInterfaceName, String sourceNmcSupportingInterface, String destinationNmcInterfaceName, String destinationNmcSupportingInterface) {
        this.connectionName = connectionName;
        this.sourceNmcInterfaceName = sourceNmcInterfaceName;
        this.sourceNmcSupportingPort = sourceNmcSupportingInterface;
        this.destinationNmcInterfaceName = destinationNmcInterfaceName;
        this.destinationNmcSupportingPort = destinationNmcSupportingInterface;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public String getSourceNmcInterfaceName() {
        return sourceNmcInterfaceName;
    }

    public String getSourceNmcSupportingPort() {
        return sourceNmcSupportingPort;
    }

    public String getDestinationNmcInterfaceName() {
        return destinationNmcInterfaceName;
    }

    public String getDestinationNmcSupportingPort() {
        return destinationNmcSupportingPort;
    }
}
