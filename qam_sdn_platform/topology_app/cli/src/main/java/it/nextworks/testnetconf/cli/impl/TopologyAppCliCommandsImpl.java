/*
 * Copyright © 2018 2020 and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package it.nextworks.testnetconf.cli.impl;

import it.nextworks.testnetconf.cli.api.TopologyAppCliCommands;
import org.opendaylight.mdsal.binding.api.DataBroker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopologyAppCliCommandsImpl implements TopologyAppCliCommands {

    private static final Logger LOG = LoggerFactory.getLogger(TopologyAppCliCommandsImpl.class);
    private final DataBroker dataBroker;

    public TopologyAppCliCommandsImpl(final DataBroker db) {
        this.dataBroker = db;
        LOG.info("CliCommandImpl initialized");
    }

    @Override
    public Object testCommand(Object testArgument) {
        return "This is a test implementation of test-command";
    }

    @Override
    public Object getTopology(String topologyName) {
        return null;
    }

    @Override
    public Object getNodes(String topologyName) {
        return null;
    }

}
