package it.nextworks.qameleon.qamProvisioningApp.sbi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.common.base.CaseFormat;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.common.rev200423.Uuid;
import org.opendaylight.yang.gen.v1.urn.onf.otcc.yang.tapi.path.computation.rev200423.ComputeP2PPathInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PceRestClient {

    private final String PCE_HOSTNAME;
    private final int PCE_PORT;
    private final String PATH_COMPUTATION_END_POINT="/operations/tapi-path-computation:compute-p-2-p-path/";
    private final String GET_ERO_END_POINT="/data/tapi-common:context/tapi-path-computation:path-computation-context/path=";
    private final String DELETE_PATH_END_POINT="/operations/tapi-path-computation:delete-p-2-p-path/";
    private static final Logger LOG = LoggerFactory.getLogger(PceRestClient.class);


    public PceRestClient(String pceHostname, int pcePort){
        this.PCE_HOSTNAME = pceHostname;
        this.PCE_PORT = pcePort;

    }

public JSONObject computeLightPathRequest(ComputeP2PPathInput input, String epSrc, String epDst) throws IOException {
        String lightPathId = requestPathComputation(input, epSrc, epDst);
        if(lightPathId==null){
            LOG.warn("Unable to compute a light path from "+input.getSep().get(0).getLocalId()+" to "+input.getSep().get(1).getLocalId());
            return null;
        }
        JSONObject ero = getEroByPathId(lightPathId);
        if(ero==null) {
            LOG.warn("Unable to get the Explicit Route Object");
        }
        return ero;
    }


    private List<String> fromUuidListToStringList(List<Uuid> uuidList){
        List<String> listString = new ArrayList<>();
        for(Uuid uuid: uuidList){
            listString.add(uuid.getValue());
        }
        return listString;
    }

    private JSONObject buildPayload(JSONObject jsonObj, String src, String dst, List<Uuid> includeLinkList, List<Uuid> excludeLinkList){

        JSONArray jsonArraySep = jsonObj.getJSONArray("sep");

        JSONArray jsonArrayA = null;
        JSONArray jsonArrayB = null;
        try {
            jsonArrayA = jsonArraySep.getJSONObject(0).getJSONArray("name");

        }
        catch(JSONException e){
                LOG.warn("Not found any name under "+jsonArraySep.getJSONObject(0).toString());
        }

        try {
            jsonArrayB = jsonArraySep.getJSONObject(1).getJSONArray("name");
        }
        catch(JSONException e){
            LOG.warn("Not found any name under "+jsonArraySep.getJSONObject(1).toString());
        }

        JSONArray nameList = null;
        if(jsonArrayA!=null){
            nameList = jsonArrayA;
        }
        if(jsonArrayA==null && jsonArrayB!=null){
            nameList = jsonArrayB;
        }

        String sipZero = jsonArraySep.getJSONObject(0)
                .getJSONObject("service-interface-point").getJSONObject("service-interface-point-uuid").getString("value");

        String sipOne = jsonArraySep.getJSONObject(1)
                .getJSONObject("service-interface-point").getJSONObject("service-interface-point-uuid").getString("value");

        String srcSip = null;
        String dstSip = null;

        if(sipZero.contains(src))
            srcSip = sipZero;

        if(sipOne.contains(src))
            srcSip = sipOne;


        if(sipZero.contains(dst))
            dstSip = sipZero;

        if(sipOne.contains(dst))
            dstSip = sipOne;

        JSONObject sipJsonSrc = buildSipJson(srcSip,"OUTPUT",nameList);
        JSONObject sipJsonDst = buildSipJson(dstSip,"INPUT",nameList);



        JSONArray jsonArray = new JSONArray();
        LOG.info("SRC sip is "+sipJsonSrc);
        LOG.info("DST sip is "+sipJsonDst);
        jsonArray.put(sipJsonSrc);
        jsonArray.put(sipJsonDst);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("end-point",jsonArray);

        JSONArray jsonArrayIncludeLink = new JSONArray();
        if(includeLinkList!=null && includeLinkList.size()>0){
            jsonArrayIncludeLink = new JSONArray(fromUuidListToStringList(includeLinkList));
        }

        JSONArray jsonArrayExcludeLink = new JSONArray();
        if(excludeLinkList!=null && excludeLinkList.size()>0) {
            jsonArrayExcludeLink = new JSONArray(fromUuidListToStringList(excludeLinkList));
        }


        JSONObject jsonTopologyConstraint = new JSONObject();
        if(jsonArrayIncludeLink.length()>0) {
            jsonTopologyConstraint.put("include-link", jsonArrayIncludeLink);
        }
        if(jsonArrayExcludeLink.length()>0) {
            jsonTopologyConstraint.put("exclude-link", jsonArrayExcludeLink);
        }
        if(jsonTopologyConstraint.length()>0)
            jsonObject.put("topology-constraint",jsonTopologyConstraint);

        JSONObject jsonFather = new JSONObject();
        jsonFather.put("input",jsonObject);

        return jsonFather;
    }

    private JSONObject buildSipJson(String sipUuid, String direction, JSONArray jsonArrayNameList){
        JSONObject sipJsonObject = new JSONObject();
        sipJsonObject.put("service-interface-point-uuid", sipUuid);
        JSONObject sipJsonObjectFather = new JSONObject();
        sipJsonObjectFather.put("service-interface-point",sipJsonObject);
        sipJsonObjectFather.put("direction",direction);

        if(direction.equals("INPUT") && jsonArrayNameList!=null){
            sipJsonObjectFather.put("name",jsonArrayNameList);
        }
        return sipJsonObjectFather;
    }

    private String requestPathComputation(ComputeP2PPathInput input, String src, String dst) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonInputStrFormat = ow.writeValueAsString(input);


        jsonInputStrFormat = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN,jsonInputStrFormat);
        JSONObject jsonObject = new JSONObject(jsonInputStrFormat);

        LOG.info("Payload to send to the PCE before processing");
        LOG.info(jsonObject.toString());
        jsonObject = buildPayload(jsonObject, src, dst, input.getTopologyConstraint().getIncludeLink(), input.getTopologyConstraint().getExcludeLink());

        LOG.info("Payload to send to the PCE");
        LOG.info(jsonObject.toString());
        HttpResponse httpResponse = sendHttpRequest(PCE_HOSTNAME +":"+ PCE_PORT +PATH_COMPUTATION_END_POINT,"POST",jsonObject.toString());
        String jsonResponse = httpResponse.getPayload();
        JSONObject obj = new JSONObject(jsonResponse);
        obj =  (JSONObject)obj.getJSONObject("output").getJSONObject("service").getJSONArray("path").get(0);
        return obj.getString("path-uuid");
    }

    private JSONObject getEroByPathId(String pathId) throws IOException {
        HttpResponse httpResponse = sendHttpRequest(PCE_HOSTNAME +":"+ PCE_PORT +GET_ERO_END_POINT+pathId+"/","GET",null);;
        String jsonResponse = httpResponse.getPayload();
        JSONObject obj = new JSONObject(jsonResponse);
        return obj;
    }


    private HttpResponse sendHttpRequest(String urlString, String requestMethod, String inputJson) throws IOException {
        String completeUrl = "http://"+urlString;
        URL url = new URL(completeUrl);
        LOG.info("Sending "+requestMethod+ " request to "+completeUrl);
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
        HttpResponse httpResponse = sendHttpRequest(PCE_HOSTNAME +":"+ PCE_PORT +DELETE_PATH_END_POINT,"POST",outer_json.toString());
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
