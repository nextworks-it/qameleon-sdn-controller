package it.nextworks.qameleon.pce.sbi;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopologyAppRestClient extends OdlHttpClient{

    private static final Logger LOG = LoggerFactory.getLogger(TopologyAppRestClient.class);

    public TopologyAppRestClient(String hostname, int port, String username, String password) {
        super(hostname, port, username, password);
    }

        public JSONObject getTopology(String topologyId) throws JSONException {
            String message;
            JSONObject topologyIdJson = new JSONObject();
            topologyIdJson.put("qameleon-topology:topology-id", topologyId);

            JSONObject topologyInput= new JSONObject();
            topologyInput.put("qameleon-topology:input",topologyIdJson);

            message = topologyInput.toString();

            final String FULL_URL = getHostname()+":"+getPort()+"/restconf/operations/qameleon-topology:get-qam-topology";
            String rawTopology = sendHttpRequest(FULL_URL,"POST",message);
            if(rawTopology==null){
                LOG.warn("Not able to receive a response. Check the topology app status.");
                return null;
            }

            JSONObject jsonObject = new JSONObject(rawTopology);
            if(!jsonObject.getJSONObject("output").has("qam-topology-out")){
                LOG.warn("No topology found.");
                return null;
            }
            return jsonObject.getJSONObject("output").getJSONObject("qam-topology-out");
    }

}
