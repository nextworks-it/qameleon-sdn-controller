package it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver;

import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.BaudRate;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.ModulationFormat;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.SbvtConfiguration;
import it.nextworks.qameleon.sbi.netconf_driver.sbvtDriver.informationModel.SbvtConfigurationRead;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;

public class SbvtRestClient {
    private final String URL = "http://mockoon:3000/tx";
    private final String PROXY = "127.0.0.1";
    private final String API_KEY = "apiKey";
    private static final Logger LOG = LoggerFactory.getLogger(SbvtRestClient.class);

    private final boolean USE_PROXY = false;
    private final boolean SEND_INSECURE = true;

    public SbvtRestClient(){
        if(!SEND_INSECURE)
            setTrustStoreProperties();
    }
    private void printStuff(String toPrint){
        System.out.println(toPrint);
        LOG.info(toPrint);
    }

    private void setTrustStoreProperties(){
        System.setProperty("javax.net.ssl.trustStore", "/cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword","ubuntu");
    }

    public boolean configureSbvt(SbvtConfiguration sbvtConfiguration){

        String boolString= String.valueOf(sbvtConfiguration.isLaserEnabled());
        String payload = "{\"modulation_format\": \""+sbvtConfiguration.getModulationType().toString()+"\", \"baudrate\": "+sbvtConfiguration.getBaudRate().getValue()+
                ", \"frequency\": "+Integer.valueOf((int) sbvtConfiguration.getFrequency())+", \"laser_enable\": "+boolString+"}";

        printStuff(payload);
        if(SEND_INSECURE)
            return sendHttpRequestInsecure("POST",payload)!=null;
        else
            return sendHttpRequest("POST",payload)!=null;

    }


    private ModulationFormat getModulationFormat(String modulationFormat){
        switch(modulationFormat){
            case "QPSK":
                return ModulationFormat.QPSK;
            case "16QAM":
                return ModulationFormat._16QAM;
            case "32QAM":
                return ModulationFormat._32QAM;
            case "64QAM":
                return ModulationFormat._64QAM;
            case "PCS-16QAM":
                return ModulationFormat.PCS_16QAM;
            case "PCS-64QAM":
                return ModulationFormat.PCS_64QAM;
            default:
                return null;
        }
    }

    private BaudRate getBaudRate(int baudRate){
        switch(baudRate){
            case 16:
                return BaudRate._16;
            case 32:
                return BaudRate._32;
            case 56:
                return BaudRate._56;
            case 64:
                return BaudRate._64;
            case 96:
                return BaudRate._96;
            case 128:
                return BaudRate._128;
            default:
                return null;
        }
    }

    public SbvtConfigurationRead getSbvtConfiguration(){
        String payloadResponse;
        if(SEND_INSECURE)
            payloadResponse =  sendHttpRequestInsecure("GET",null);
        else
            payloadResponse =  sendHttpRequest("GET",null);

        printStuff("Raw payload received");
        printStuff(payloadResponse);
        try {
            JSONObject obj = new JSONObject(payloadResponse);
            ModulationFormat modulationFormat = getModulationFormat(obj.getString("modulation_format"));
            BaudRate baudRate = getBaudRate(obj.getInt("baudrate"));
            int frequency = obj.getInt("frequency");
            boolean isLaserEnabled = obj.getBoolean("laser_enable");
            Long lastUpdate= obj.getLong("last_update");
            Double monitEdfaInput = obj.getDouble("monit_edfa_input_power");
            Double monitEdfaOutput = obj.getDouble("monit_edfa_output_power");
            Double monitFrequency = obj.getDouble("monit_frequency");
            Double monitOsnr = obj.getDouble("monit_osnr_01");
            SbvtConfigurationRead sbvtConfigurationRead =
                    new SbvtConfigurationRead(modulationFormat, baudRate,
                            frequency, isLaserEnabled, monitEdfaInput,
                            monitEdfaOutput, monitFrequency, monitOsnr, lastUpdate);
            return sbvtConfigurationRead;
        }
        catch(JSONException e){
            e.printStackTrace();
            printStuff(e.getMessage());
            return new SbvtConfigurationRead();
        }
    }

    private String sendHttpRequestInsecure(String reqMethod, String payload){
        HttpURLConnection httpURLconnection;
        java.net.URL url;
        int responseCode = 0;
        String responseMessage;
        String payloadResponse="";
        InetSocketAddress proxyInet = new InetSocketAddress(PROXY,3128);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyInet);



        try {
            url = new URL(URL);
            if(USE_PROXY)
                httpURLconnection = (HttpURLConnection) url.openConnection(proxy);
            else
                httpURLconnection = (HttpURLConnection) url.openConnection();
            //LOG.info("Sending "+reqMethod+" request to "+ url);
            printStuff("Sending "+reqMethod+" request to "+ url);
            httpURLconnection.setRequestMethod(reqMethod);
            httpURLconnection.setRequestProperty("Content-Type", "application/json");
            httpURLconnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpURLconnection.setRequestProperty("Accept", "*");
            httpURLconnection.setRequestProperty("Connection","keep-alive");
            httpURLconnection.setRequestProperty("apikey", API_KEY);



            httpURLconnection.setDoOutput(true);
            //printStuff(httpsURLconnection.toString());
            //printStuff(httpsURLconnection.getContentEncoding());

            if(payload!=null) {
                try (OutputStream os = httpURLconnection.getOutputStream()) {
                    byte[] input = payload.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            responseCode = httpURLconnection.getResponseCode();
            //LOG.info("ResponseCode: "+responseCode);
            printStuff("ResponseCode: "+responseCode);
            responseMessage = httpURLconnection.getResponseMessage();
            //LOG.info("Response Message: "+responseMessage);
            printStuff("Response Message: "+responseMessage);
            BufferedReader br = null;
            if (httpURLconnection.getResponseCode() < 300) {
                br = new BufferedReader(new InputStreamReader(httpURLconnection.getInputStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    payloadResponse+=strCurrentLine;
                }
            }
            else {
                if (httpURLconnection.getErrorStream() != null) {
                    br = new BufferedReader(new InputStreamReader(httpURLconnection.getErrorStream()));
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

        LOG.info("Response code "+responseCode);
        if(responseCode >= 300)
            return null;

        //  LOG.info("Response body message: ");
        //  LOG.info(payloadResponse);

        printStuff("Response body message: ");
        printStuff(payloadResponse);
        return payloadResponse;
    }


    private String sendHttpRequest(String reqMethod, String payload){
        HttpsURLConnection httpsURLconnection;
        java.net.URL url;
        int responseCode = 0;
        String responseMessage;
        String payloadResponse="";
        InetSocketAddress proxyInet = new InetSocketAddress(PROXY,3128);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyInet);



        try {
            url = new URL(URL);
            if(USE_PROXY)
                httpsURLconnection = (HttpsURLConnection) url.openConnection(proxy);
            else
                httpsURLconnection = (HttpsURLConnection) url.openConnection();
            //LOG.info("Sending "+reqMethod+" request to "+ url);
            printStuff("Sending "+reqMethod+" request to "+ url);
            httpsURLconnection.setRequestMethod(reqMethod);
            httpsURLconnection.setRequestProperty("Content-Type", "application/json");
            httpsURLconnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpsURLconnection.setRequestProperty("Accept", "*");
            httpsURLconnection.setRequestProperty("Connection","keep-alive");
            httpsURLconnection.setRequestProperty("apikey", API_KEY);



            httpsURLconnection.setDoOutput(true);
            //printStuff(httpsURLconnection.toString());
            //printStuff(httpsURLconnection.getContentEncoding());

            if(payload!=null) {
                try (OutputStream os = httpsURLconnection.getOutputStream()) {
                    byte[] input = payload.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }

            responseCode = httpsURLconnection.getResponseCode();
            //LOG.info("ResponseCode: "+responseCode);
            printStuff("ResponseCode: "+responseCode);
            responseMessage = httpsURLconnection.getResponseMessage();
            //LOG.info("Response Message: "+responseMessage);
            printStuff("Response Message: "+responseMessage);
            BufferedReader br = null;
            if (httpsURLconnection.getResponseCode() < 300) {
                br = new BufferedReader(new InputStreamReader(httpsURLconnection.getInputStream()));
                String strCurrentLine;
                while ((strCurrentLine = br.readLine()) != null) {
                    payloadResponse+=strCurrentLine;
                }
            }
            else {
                if (httpsURLconnection.getErrorStream() != null) {
                    br = new BufferedReader(new InputStreamReader(httpsURLconnection.getErrorStream()));
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

        LOG.info("Response code "+responseCode);
        if(responseCode >= 300)
            return null;

      //  LOG.info("Response body message: ");
      //  LOG.info(payloadResponse);

        printStuff("Response body message: ");
        printStuff(payloadResponse);
        return payloadResponse;
    }

}
