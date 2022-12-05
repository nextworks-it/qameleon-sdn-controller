package it.nextworks.qameleon.pce.sbi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class OdlHttpClient {

    private String hostname;
    private int port;
    private String username;
    private String password;

    private static final Logger LOG = LoggerFactory.getLogger(OdlHttpClient.class);

    public OdlHttpClient(String hostname, int port, String username, String password){
        this.hostname = hostname;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    protected String sendHttpRequest(String urlString, String requestMethod){
        try {
            LOG.info("Performing request to  "+urlString);
            URL url = new URL("http://"+urlString);
            HttpURLConnection con = null;
            String userPass = this.username + ":" + this.password;
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userPass.getBytes()));

            con = (HttpURLConnection) url.openConnection();


            con.setRequestProperty ("Authorization", basicAuth);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod(requestMethod);

            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            LOG.debug("Status code: "+status);
            LOG.debug("Content is "+content);
            con.disconnect();
            return content.toString();

        } catch (IOException e) {
            LOG.error("IOException e");
            LOG.error(e.getMessage());
        return null;
        }

    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

}
