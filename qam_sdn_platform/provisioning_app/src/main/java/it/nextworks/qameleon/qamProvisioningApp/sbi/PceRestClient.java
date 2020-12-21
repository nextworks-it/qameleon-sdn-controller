package it.nextworks.qameleon.qamProvisioningApp.sbi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.CaseFormat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.ComputeP2PPathInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;

public class PceRestClient {

    private final String DUMMY_PCE_HOSTNAME;
    private final int DUMMY_PCE_PORT;
    private final String PATH_COMPUTATION_END_POINT="/operations/tapi-path-computation:compute-p-2-p-path/";
    private final String GET_ERO_END_POINT="/data/tapi-common:context/tapi-path-computation:path-computation-context/path=";
    private final String DELETE_PATH_END_POINT="/operations/tapi-path-computation:delete-p-2-p-path/";
    private static final Logger LOG = LoggerFactory.getLogger(PceRestClient.class);


    public PceRestClient(String dummyPceHostname, int dummyPcePort){
        this.DUMMY_PCE_HOSTNAME = dummyPceHostname;
        this.DUMMY_PCE_PORT = dummyPcePort;

    }

public JSONObject computeLightPathRequest(ComputeP2PPathInput input) throws IOException {
        String lightPathId = requestPathComputation(input);
        if(lightPathId==null){
            LOG.warn("Unable to compute a light path from "+input.getSipSrcUuid()+" to "+input.getSipDstUuid());
            return null;
        }
        JSONObject ero = getEroByPathId(lightPathId);
        if(ero==null) {
            LOG.warn("Unable to get the Explicit Route Object");
        }
        return ero;
    }


    private JSONObject buildMinimalPayload(JSONObject jsonObj){
        jsonObj.remove("topology-constraint");
        jsonObj.remove("objective-function");
        jsonObj.remove("routing-constraint");
        JSONObject sipJsonSrc = buildSipJson(jsonObj.getString("sip-src-uuid"));
        JSONObject sipJsonDst = buildSipJson(jsonObj.getString("sip-dst-uuid"));
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(sipJsonSrc);
        jsonArray.put(sipJsonDst);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("end-point",jsonArray);

        JSONObject jsonFather = new JSONObject();
        jsonFather.put("input",jsonObject);
        return jsonFather;
    }

    private JSONObject buildSipJson(String sipUuid){
        JSONObject sipJsonObject = new JSONObject();
        sipJsonObject.put("service-interface-point-uuid", sipUuid);
        JSONObject sipJsonObjectFather = new JSONObject();
        sipJsonObjectFather.put("service-interface-point",sipJsonObject);
        return sipJsonObjectFather;
    }

    private String requestPathComputation(ComputeP2PPathInput input) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonInputStrFormat = ow.writeValueAsString(input);
        jsonInputStrFormat = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN,jsonInputStrFormat);
        JSONObject jsonObj2 = new JSONObject(jsonInputStrFormat);
        jsonObj2 = buildMinimalPayload(jsonObj2);
        HttpResponse httpResponse = sendHttpRequest(DUMMY_PCE_HOSTNAME+":"+DUMMY_PCE_PORT+PATH_COMPUTATION_END_POINT,"POST",jsonObj2.toString());
        String jsonResponse = httpResponse.getPayload();
        JSONObject obj = new JSONObject(jsonResponse);
        obj =  (JSONObject)obj.getJSONObject("output").getJSONObject("service").getJSONArray("path").get(0);
        return obj.getString("path-uuid");
    }

    private JSONObject getEroByPathId(String pathId) throws IOException {
        HttpResponse httpResponse = sendHttpRequest(DUMMY_PCE_HOSTNAME+":"+DUMMY_PCE_PORT+GET_ERO_END_POINT+pathId+"/","GET",null);;
        String jsonResponse = httpResponse.getPayload();
        JSONObject obj = new JSONObject(jsonResponse);
        return obj;
    }


    private HttpResponse sendHttpRequest(String urlString, String requestMethod, String inputJson) throws IOException {
            URL url = new URL("http://"+urlString);
            HttpURLConnection con = null;
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(requestMethod);
            con.setRequestProperty("Accept", "application/yang-data+json");
            con.setRequestProperty("Content-Type", "application/yang-data+json");

            if(inputJson!=null) {
                con.setDoOutput(true);
                try (OutputStream os = con.getOutputStream()) {
                    byte[] input = inputJson.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }
            int status = con.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            LOG.debug("Http response");
            LOG.debug("Status code: "+status);
            LOG.debug("Content: "+content);
            con.disconnect();
            return new HttpResponse(content.toString(), status);

    }
    public boolean removeLightPathRequest(String lightPathId) throws IOException {
        JSONObject delJson = new JSONObject();
        delJson.put("uuid", lightPathId);
        JSONObject outer_json = new JSONObject();
        outer_json.put("input", delJson);
        HttpResponse httpResponse = sendHttpRequest(DUMMY_PCE_HOSTNAME+":"+DUMMY_PCE_PORT+DELETE_PATH_END_POINT,"POST",outer_json.toString());
        LOG.debug("Status code is "+httpResponse.getResponseCode());
        LOG.debug("{}",httpResponse.getResponseCode()==200);
        return httpResponse.getResponseCode()==200;
    }

    private class HttpResponse{
        private String payload;
        private int responseCode;
        public HttpResponse(String payload, int responseCode){
            this.payload = payload;
            this.responseCode = responseCode;
        }


        public String getPayload() {
            return payload;
        }

        public int getResponseCode() {
            return responseCode;
        }
    }
}
