package it.nextworks.qameleon.sbi.netconf_driver.openRoadmDriver;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class OpenRoadmClient {
    private static final Logger LOG = LoggerFactory.getLogger(OpenRoadmClient.class);

    private String netconfserverhost;
    private String netconfserverport;
    private String devicehost;
    private String deviceport;
    private String deviceusername;
    private String devicepassword;

    private final String EXPRESS_LINK_EP = "/open-roadm/express-link";
    private final String CONFIG_SERVER_EP = "/open-roadm/config";
    private final String GET_ROADM_CONN_EP = "/open-roadm/connection";
    private final String ROADM_INTERFACE_EP = "/open-roadm/interface";
    private final String ROADM_DISCARD_CHANGES_EP = "/open-roadm/discard-changes";


    public OpenRoadmClient(String netconfHost, String netconfPort, String deviceHost, String devicePort, String username, String password) {
        this.netconfserverhost = netconfHost;
        this.netconfserverport = netconfPort;

        this.devicehost = deviceHost;
        this.deviceport = devicePort;
        this.deviceusername = username;
        this.devicepassword = password;
    }


    private String sendHttpRequest(String endpoint, String reqMethod, String payload){
        HttpURLConnection insecureConnection;
        URL url;
        int responseCode = 0;
        String responseMessage;
        String payloadResponse="";
        try {
            String baseUrlString = "http://"+netconfserverhost+":"+netconfserverport+endpoint;

            url = new URL(baseUrlString);
            insecureConnection = (HttpURLConnection) url.openConnection();
            LOG.info("Sending "+reqMethod+" request to "+ url);
            insecureConnection.setRequestMethod(reqMethod);
            insecureConnection.setRequestProperty("Content-Type", "application/json");


            insecureConnection.setDoOutput(true);
            if(payload!=null) {
                try (OutputStream os = insecureConnection.getOutputStream()) {
                    byte[] input = payload.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            responseCode = insecureConnection.getResponseCode();
            LOG.info("ResponseCode: "+responseCode);
            responseMessage = insecureConnection.getResponseMessage();
            LOG.info("Response Message: "+responseMessage);

            BufferedReader br = null;
            if (insecureConnection.getResponseCode() < 300) {
                br = new BufferedReader(new InputStreamReader(insecureConnection.getInputStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    payloadResponse+=strCurrentLine;
                }
            }
            else {
                if (insecureConnection.getErrorStream() != null) {
                    br = new BufferedReader(new InputStreamReader(insecureConnection.getErrorStream()));
                    String strCurrentLine;
                    while ((strCurrentLine = br.readLine()) != null) {
                        payloadResponse+=strCurrentLine;
                    }
                }
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(responseCode >= 300)
            return null;

        LOG.info("Response body message: ");
        LOG.info(payloadResponse);

        return payloadResponse;
    }

    public boolean init(){
        String payload ="{\"ip\": \""+devicehost+"\", \"port\": \""+deviceport+"\", \"password\": \""+devicepassword+"\", \"username\": \""+deviceusername+"\"}";
        LOG.info("Initing OpenRoadmClient with the following info: ");
        String responseMsg = sendHttpRequest(CONFIG_SERVER_EP, "POST", payload);
        return responseMsg!=null;
    }

    public boolean createExpressLink(String degSrc, String degDst, double startFreq, double endFreq, double freqWidth){
        String payload ="{\"degSrc\":\""+degSrc+"\",\"degDst\":\""+degDst+"\",\"startFreq\":"+startFreq+",\"endFreq\":"+endFreq+",\"freqWidth\":"+freqWidth+"}";
        String responseMsg = sendHttpRequest(EXPRESS_LINK_EP, "POST", payload);
        return responseMsg!=null;
    }

    public boolean removeExpressLink(String degSrc, String degDst, double startFreq, double endFreq, double freqWidth){
        String payload ="{\"degSrc\":\""+degSrc+"\",\"degDst\":\""+degDst+"\",\"startFreq\":"+startFreq+",\"endFreq\":"+endFreq+",\"freqWidth\":"+freqWidth+"}";
        String responseMsg = sendHttpRequest(EXPRESS_LINK_EP, "DELETE", payload);
        return responseMsg!=null;
    }

    public boolean discardChanges(){
        String responseMsg = sendHttpRequest(ROADM_DISCARD_CHANGES_EP, "POST", null);
        return responseMsg!=null;
    }

    public String getRoadmInterface(String openRoadmInterface){
        String responseMsg = sendHttpRequest(ROADM_INTERFACE_EP +"/"+openRoadmInterface, "GET", null);
        return responseMsg;
    }

    public String getRoadmConnection(String openRoadmConnection){
        String responseMsg = sendHttpRequest(GET_ROADM_CONN_EP +"/"+openRoadmConnection, "GET", null);
        return responseMsg;
    }

    public String getRoadmConnections(){
        String responseMsg = sendHttpRequest(GET_ROADM_CONN_EP, "GET", null);
        return responseMsg;
    }


}
