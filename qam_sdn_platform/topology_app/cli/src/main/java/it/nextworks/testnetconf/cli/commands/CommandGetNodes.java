package it.nextworks.testnetconf.cli.commands;

import it.nextworks.testnetconf.cli.api.TopologyAppCliCommands;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.commands.Option;
import org.apache.karaf.shell.console.AbstractAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Command(name = "getNodes", scope = "add the scope of the command, usually project name",
        description = "returns the topology")
public class CommandGetNodes extends AbstractAction {

    private static final Logger LOG = LoggerFactory.getLogger(CommandGetTopology.class);
    protected final TopologyAppCliCommands service;

    public CommandGetNodes(final TopologyAppCliCommands service) {
        this.service = service;
        LOG.info("Comando GetNodes initialized");
    }

    /**
     * Add the arguments required by the command.
     * Any number of arguments can be added using the Option annotation
     * The below argument is just an example and should be changed as per your requirements
     */
    @Option(name = "-t",
            aliases = { "--topologyName" },
            description = "topology name",
            required = true,
            multiValued = false)
    private String topology;

    @Override
    protected Object doExecute() throws Exception {
        /**
         * Invoke commannd implementation here using the service instance.
         * Implement how you want the output of the command to be displayed.
         * Below is just an example.
         */
        LOG.info("Cli command getNodes");
        final String testMessage = (String) service.getNodes(topology);
        return testMessage;
    }

}
