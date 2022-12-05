package it.nextworks.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOG = LoggerFactory.getLogger(PropertyReader.class);
    private static String frequencyChannelMapFilePath;
    private static String pceIp;
    private static String pcePort;
    private static final String HOST_PCE_DEFAULT = "172.10.12.3";
    private static final int PCE_PORT_DEFAULT = 1234;

    public static String getDefaultPceHost(){
        return HOST_PCE_DEFAULT;
    }
    public static int getDefaultPcePort(){
        return PCE_PORT_DEFAULT;
    }

    public static boolean parsePropertiesFile(){
        // When running as a single application the CWD is <path_to_parent_application>/karaf/target/assembly
        Path path;
        path = Paths.get(System.getProperty("user.dir"));
        LOG.info("Directory with properties = " + path);
        Path basepath = path;
        if (path.getFileName().toString().equals("assembly"))
            basepath = path.getParent().getParent().getParent();

        try (InputStream input = new FileInputStream(basepath+"/controller.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                LOG.error("Unable to find controller.properties");
                return false;
            }
            prop.load(input);

            frequencyChannelMapFilePath = prop.getProperty("path.frequency_channel_map");
            LOG.info("path.dictionary property is equal to "+frequencyChannelMapFilePath);

            pceIp = prop.getProperty("pce.ip",HOST_PCE_DEFAULT);
            LOG.info("pce.ip property is equal to "+pceIp);

            pcePort = prop.getProperty("pce.port",String.valueOf(PCE_PORT_DEFAULT));
            LOG.info("pce.port property is equal to "+pcePort);
            return true;

        } catch (Exception ex) {
            LOG.error("Unable to load properties");
            LOG.error(ex.getMessage());
            return false;
        }
    }
    public static String getFrequencyChannelMapFilePath() {
        return frequencyChannelMapFilePath;
    }
    public static String getPceIp() {
        return pceIp;
    }

    public static int getPcePort() {
        try{
            return Integer.parseInt(pcePort);
        } catch(NumberFormatException ex){
            LOG.warn("Error parsing pce.port. Using default value 1234");
            LOG.warn(ex.getMessage());
            return PCE_PORT_DEFAULT;
        }
    }

}
