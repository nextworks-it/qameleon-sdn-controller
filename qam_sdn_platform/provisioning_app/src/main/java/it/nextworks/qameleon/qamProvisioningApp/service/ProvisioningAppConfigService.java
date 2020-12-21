package it.nextworks.qameleon.qamProvisioningApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//TODO the configuration must be get from file, no hardcoded. To be fixed.
public class ProvisioningAppConfigService {
        private String pceHost;
        private int pcePort;
        private String absoluteLookupTablePath;

    private static ProvisioningAppConfigService singleton;

    private static final Logger log = LoggerFactory
            .getLogger(ProvisioningAppConfigService.class);

    public static ProvisioningAppConfigService getInstance() {
        if(singleton==null) {
            singleton = new ProvisioningAppConfigService();
        }
        return singleton;

    }

    public String getPceHost() {
        return "10.30.8.74";
    }

    public int getPcePort() {
        return 1234;
    }

    public String getAbsoluteLookupTablePath() {
        return "/home/ubuntu/repo/qam_gitlab/qam_devel/qameleon_devel/sdn_agent/netconf_server_netopeer/inv_lookup_table.json";
    }
}
