package it.nextworks.qameleon.sbi.netconf_driver.openRoadmDriver;
import org.json.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OpenRoadmResponseParser {

    public static final String DST_KEY = "dst-if";
    public static final String SRC_KEY = "src-if";

    public static String getWidthFrequencyNmcCtpInterface(String inputString){
        JSONObject rootObj = new JSONObject(inputString);
        JSONObject nmcCtp = rootObj.getJSONObject("nmc-ctp");
        return nmcCtp.getString("width");
    }

    public static String getFrequencyNmcCtpInterface(String inputString){
        JSONObject rootObj = new JSONObject(inputString);
        JSONObject nmcCtp = rootObj.getJSONObject("nmc-ctp");
        return nmcCtp.getString("frequency");
    }


    public static String getSupportingPortNmcCtpInterface(String inputString){
        JSONObject rootObj = new JSONObject(inputString);
        return rootObj.getString("supporting-port");
    }

    public static HashMap getSrcOpenRoadmConnection(String inputString){

        HashMap<String,String> srcDstHashMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject(inputString);

        String dstIf = jsonObject.getJSONObject("destination").getString(DST_KEY);
        String srcIf = jsonObject.getJSONObject("source").getString(SRC_KEY);
        srcDstHashMap.put(DST_KEY, dstIf);
        srcDstHashMap.put(SRC_KEY, srcIf);
        return srcDstHashMap;
    }

    public static List<String> getConnectionNames(String inputJsonString){
        List<String> connectionNames = new ArrayList<>();

        JSONArray jsonArray = new JSONArray(inputJsonString);
        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            for(Object key: jsonObject.keySet()){
                connectionNames.add((String)key);
            }

        }

        return connectionNames;
    }

}
