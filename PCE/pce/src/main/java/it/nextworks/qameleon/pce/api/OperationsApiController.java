package it.nextworks.qameleon.pce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nextworks.qameleon.pce.PCE.BadRequestException;
import it.nextworks.qameleon.pce.PCE.PceService;
import io.swagger.annotations.ApiParam;
import it.nextworks.qameleon.pce.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")

@Controller
public class OperationsApiController implements OperationsApi {

    private static final Logger log = LoggerFactory.getLogger(OperationsApiController.class);
    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    private PceService pceService;

    @org.springframework.beans.factory.annotation.Autowired
    public OperationsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<TapiCommonGetServiceInterfacePointDetails> operationsTapiCommongetServiceInterfacePointDetailsPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiCommonGetserviceinterfacepointdetailsInputBodyparam tapiCommonGetserviceinterfacepointdetailsInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonGetServiceInterfacePointDetails>(objectMapper.readValue("{  \"output\" : {    \"sip\" : \"\"  }}", TapiCommonGetServiceInterfacePointDetails.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonGetServiceInterfacePointDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonGetServiceInterfacePointDetails>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonGetServiceInterfacePointList> operationsTapiCommongetServiceInterfacePointListPost() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonGetServiceInterfacePointList>(objectMapper.readValue("{  \"output\" : {    \"sip\" : [ \"\", \"\" ]  }}", TapiCommonGetServiceInterfacePointList.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonGetServiceInterfacePointList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonGetServiceInterfacePointList>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> operationsTapiCommonupdateServiceInterfacePointPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiCommonUpdateserviceinterfacepointInputBodyparam tapiCommonUpdateserviceinterfacepointInputBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationCreateNotificationSubscriptionService> operationsTapiNotificationcreateNotificationSubscriptionServicePost(@ApiParam(value = ""  )  @Valid @RequestBody TapiNotificationCreatenotificationsubscriptionserviceInputBodyparam tapiNotificationCreatenotificationsubscriptionserviceInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationCreateNotificationSubscriptionService>(objectMapper.readValue("{  \"output\" : {    \"subscription-service\" : \"\"  }}", TapiNotificationCreateNotificationSubscriptionService.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationCreateNotificationSubscriptionService>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationCreateNotificationSubscriptionService>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> operationsTapiNotificationdeleteNotificationSubscriptionServicePost(@ApiParam(value = ""  )  @Valid @RequestBody TapiNotificationDeletenotificationsubscriptionserviceInputBodyparam tapiNotificationDeletenotificationsubscriptionserviceInputBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationGetNotificationList> operationsTapiNotificationgetNotificationListPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiNotificationGetnotificationlistInputBodyparam tapiNotificationGetnotificationlistInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationGetNotificationList>(objectMapper.readValue("{  \"output\" : {    \"notification\" : [ \"\", \"\" ]  }}", TapiNotificationGetNotificationList.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationGetNotificationList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationGetNotificationList>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationGetNotificationSubscriptionServiceDetails> operationsTapiNotificationgetNotificationSubscriptionServiceDetailsPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiNotificationGetnotificationsubscriptionservicedetailsInputBodyparam tapiNotificationGetnotificationsubscriptionservicedetailsInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationGetNotificationSubscriptionServiceDetails>(objectMapper.readValue("{  \"output\" : {    \"subscription-service\" : \"\"  }}", TapiNotificationGetNotificationSubscriptionServiceDetails.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationGetNotificationSubscriptionServiceDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationGetNotificationSubscriptionServiceDetails>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationGetNotificationSubscriptionServiceList> operationsTapiNotificationgetNotificationSubscriptionServiceListPost() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationGetNotificationSubscriptionServiceList>(objectMapper.readValue("{  \"output\" : {    \"subscription-service\" : [ \"\", \"\" ]  }}", TapiNotificationGetNotificationSubscriptionServiceList.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationGetNotificationSubscriptionServiceList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationGetNotificationSubscriptionServiceList>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationGetSupportedNotificationTypes> operationsTapiNotificationgetSupportedNotificationTypesPost() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationGetSupportedNotificationTypes>(objectMapper.readValue("{  \"output\" : {    \"supported-object-types\" : [ \"supported-object-types\", \"supported-object-types\" ],    \"supported-notification-types\" : [ \"supported-notification-types\", \"supported-notification-types\" ]  }}", TapiNotificationGetSupportedNotificationTypes.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationGetSupportedNotificationTypes>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationGetSupportedNotificationTypes>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationUpdateNotificationSubscriptionService> operationsTapiNotificationupdateNotificationSubscriptionServicePost(@ApiParam(value = ""  )  @Valid @RequestBody TapiNotificationUpdatenotificationsubscriptionserviceInputBodyparam tapiNotificationUpdatenotificationsubscriptionserviceInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationUpdateNotificationSubscriptionService>(objectMapper.readValue("{  \"output\" : {    \"subscription-service\" : \"\"  }}", TapiNotificationUpdateNotificationSubscriptionService.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationUpdateNotificationSubscriptionService>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationUpdateNotificationSubscriptionService>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationComputeP2PPath> operationsTapiPathComputationcomputeP2PPathPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiPathComputationComputep2ppathInputBodyparam tapiPathComputationComputep2ppathInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && (accept.contains("application/json") || accept.contains("application/yang-data+json"))) {
            try {
                TapiPathComputationComputeP2PPath tapiPathComputationComputeP2PPath = pceService.computeLightPath(tapiPathComputationComputep2ppathInputBodyParam);
                String jsonObject = objectMapper.writeValueAsString(tapiPathComputationComputeP2PPath);
                return new ResponseEntity<TapiPathComputationComputeP2PPath>(objectMapper.readValue(jsonObject, TapiPathComputationComputeP2PPath.class), HttpStatus.OK);
            }
            catch(BadRequestException e){
                log.error(e.getMessage());
                return new ResponseEntity<TapiPathComputationComputeP2PPath>(HttpStatus.BAD_REQUEST);
            }
            catch (IOException e) {
                log.error(e.getMessage());
                log.error("Couldn't serialize response for content type application/json");
                return new ResponseEntity<TapiPathComputationComputeP2PPath>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationComputeP2PPath>(HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<TapiPathComputationDeleteP2PPath> operationsTapiPathComputationdeleteP2PPathPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiPathComputationDeletep2ppathInputBodyparam tapiPathComputationDeletep2ppathInputBodyParam) {
        log.info("Received requrest to remove lightpath.");
        String accept = request.getHeader("Accept");
        String pathUuid = tapiPathComputationDeletep2ppathInputBodyParam.getInput().getUuid();
        if (accept != null && (accept.contains("application/json") || accept.contains("application/yang-data+json"))) {
           if(pceService.removeComputedPath(pathUuid)){
              TapiPathComputationDeleteP2PPath tapiPathComputationDeleteP2PPath= new TapiPathComputationDeleteP2PPath();
               log.info("Lightpath correctly deleted");
                return new ResponseEntity<TapiPathComputationDeleteP2PPath>(new TapiPathComputationDeleteP2PPath(), HttpStatus.OK);
           }
         else{
               log.info("Lightpath with uuid "+pathUuid+" not found");
           return new ResponseEntity<TapiPathComputationDeleteP2PPath>(new TapiPathComputationDeleteP2PPath(), HttpStatus.NOT_FOUND);
     }

        }
        return new ResponseEntity<TapiPathComputationDeleteP2PPath>(HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<TapiPathComputationOptimizeP2PPath> operationsTapiPathComputationoptimizeP2PPathPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiPathComputationOptimizep2ppathInputBodyparam tapiPathComputationOptimizep2ppathInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationOptimizeP2PPath>(objectMapper.readValue("{  \"output\" : {    \"service\" : \"\"  }}", TapiPathComputationOptimizeP2PPath.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationOptimizeP2PPath>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationOptimizeP2PPath>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyGetLinkDetails> operationsTapiTopologygetLinkDetailsPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiTopologyGetlinkdetailsInputBodyparam tapiTopologyGetlinkdetailsInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyGetLinkDetails>(objectMapper.readValue("{  \"output\" : {    \"link\" : \"\"  }}", TapiTopologyGetLinkDetails.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyGetLinkDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyGetLinkDetails>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyGetNodeDetails> operationsTapiTopologygetNodeDetailsPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiTopologyGetnodedetailsInputBodyparam tapiTopologyGetnodedetailsInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyGetNodeDetails>(objectMapper.readValue("{  \"output\" : {    \"node\" : \"\"  }}", TapiTopologyGetNodeDetails.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyGetNodeDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyGetNodeDetails>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyGetNodeEdgePointDetails> operationsTapiTopologygetNodeEdgePointDetailsPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiTopologyGetnodeedgepointdetailsInputBodyparam tapiTopologyGetnodeedgepointdetailsInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyGetNodeEdgePointDetails>(objectMapper.readValue("{  \"output\" : {    \"node-edge-point\" : \"\"  }}", TapiTopologyGetNodeEdgePointDetails.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyGetNodeEdgePointDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyGetNodeEdgePointDetails>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyGetTopologyDetails> operationsTapiTopologygetTopologyDetailsPost(@ApiParam(value = ""  )  @Valid @RequestBody TapiTopologyGettopologydetailsInputBodyparam tapiTopologyGettopologydetailsInputBodyParam) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyGetTopologyDetails>(objectMapper.readValue("{  \"output\" : {    \"topology\" : \"\"  }}", TapiTopologyGetTopologyDetails.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyGetTopologyDetails>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyGetTopologyDetails>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyGetTopologyList> operationsTapiTopologygetTopologyListPost() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyGetTopologyList>(objectMapper.readValue("{  \"output\" : {    \"topology\" : [ \"\", \"\" ]  }}", TapiTopologyGetTopologyList.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyGetTopologyList>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyGetTopologyList>(HttpStatus.NOT_IMPLEMENTED);
    }

}
