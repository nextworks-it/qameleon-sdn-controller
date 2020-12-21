package it.nextworks.qameleon.pce.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nextworks.qameleon.pce.PCE.PceService;
import io.swagger.annotations.ApiParam;
import it.nextworks.qameleon.pce.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")

@Controller
public class DataApiController implements DataApi {

    private static final Logger log = LoggerFactory.getLogger(DataApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private static final Logger LOG = LoggerFactory.getLogger(DataApiController.class);

    @Autowired
    private PceService pceService;

    @org.springframework.beans.factory.annotation.Autowired
    public DataApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> dataTapiCommoncontextDelete() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonContextWrapper> dataTapiCommoncontextGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonContextWrapper>(objectMapper.readValue("{  \"tapi-common:context\" : \"\"}", TapiCommonContextWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonContextWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonContextWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextNamePost(@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextNamevalueNameDelete(@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextNamevalueNameGet(@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextNamevalueNamePut(@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextPost(@ApiParam(value = "tapi.common.Context to be added to list"  )  @Valid @RequestBody TapiCommonContextWrapper tapiCommonContextBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextPut(@ApiParam(value = "tapi.common.Context to be added or updated"  )  @Valid @RequestBody TapiCommonContextWrapper tapiCommonContextBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextServiceInterfacePointPost(@ApiParam(value = "tapi.common.ServiceInterfacePoint to be added to list"  )  @Valid @RequestBody TapiCommonServiceInterfacePointWrapper tapiCommonServiceInterfacePointBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextServiceInterfacePointuuidAvailableCapacityGet(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextServiceInterfacePointuuidAvailableCapacityTotalSizeGet(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextServiceInterfacePointuuidDelete(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonServiceInterfacePointWrapper> dataTapiCommoncontextServiceInterfacePointuuidGet(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonServiceInterfacePointWrapper>(objectMapper.readValue("{  \"service-interface-point\" : \"\"}", TapiCommonServiceInterfacePointWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonServiceInterfacePointWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonServiceInterfacePointWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextServiceInterfacePointuuidNamePost(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextServiceInterfacePointuuidNamevalueNameDelete(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextServiceInterfacePointuuidNamevalueNameGet(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextServiceInterfacePointuuidNamevalueNamePut(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextServiceInterfacePointuuidPut(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.common.ServiceInterfacePoint to be added or updated"  )  @Valid @RequestBody TapiCommonServiceInterfacePointWrapper tapiCommonServiceInterfacePointBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextServiceInterfacePointuuidTotalPotentialCapacityGet(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextServiceInterfacePointuuidTotalPotentialCapacityTotalSizeGet(@ApiParam(value = "Id of service-interface-point",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextDelete() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationNotificationContextWrapper> dataTapiCommoncontextTapiNotificationnotificationContextGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationNotificationContextWrapper>(objectMapper.readValue("{  \"tapi-notification:notification-context\" : {    \"notif-subscription\" : [ \"\", \"\" ],    \"notification\" : [ \"\", \"\" ]  }}", TapiNotificationNotificationContextWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationNotificationContextWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationNotificationContextWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionPost(@ApiParam(value = "tapi.notification.NotificationSubscriptionService to be added to list"  )  @Valid @RequestBody TapiNotificationNotificationSubscriptionServiceWrapper tapiNotificationNotificationSubscriptionServiceBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidDelete(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationNotificationSubscriptionServiceWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationNotificationSubscriptionServiceWrapper>(objectMapper.readValue("{  \"notif-subscription\" : \"\"}", TapiNotificationNotificationSubscriptionServiceWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationNotificationSubscriptionServiceWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationNotificationSubscriptionServiceWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNamePost(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNamevalueNameDelete(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNamevalueNameGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNamevalueNamePut(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationNotificationChannelWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNotificationChannelGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationNotificationChannelWrapper>(objectMapper.readValue("{  \"notification-channel\" : \"\"}", TapiNotificationNotificationChannelWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationNotificationChannelWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationNotificationChannelWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNotificationChannelNamevalueNameGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNotificationnotificationUuidAdditionalInfovalueNameGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of notification",required=true) @PathVariable("notification-uuid") String notificationUuid,@ApiParam(value = "Id of additional-info",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationNameAndValueChangeWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNotificationnotificationUuidChangedAttributesvalueNameGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of notification",required=true) @PathVariable("notification-uuid") String notificationUuid,@ApiParam(value = "Id of changed-attributes",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationNameAndValueChangeWrapper>(objectMapper.readValue("{  \"changed-attributes\" : {    \"value-name\" : \"value-name\",    \"old-value\" : \"old-value\",    \"new-value\" : \"new-value\"  }}", TapiNotificationNameAndValueChangeWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationNameAndValueChangeWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationNameAndValueChangeWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationNotificationWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNotificationnotificationUuidGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of notification",required=true) @PathVariable("notification-uuid") String notificationUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationNotificationWrapper>(objectMapper.readValue("{  \"notification\" : \"\"}", TapiNotificationNotificationWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationNotificationWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationNotificationWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNotificationnotificationUuidNamevalueNameGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of notification",required=true) @PathVariable("notification-uuid") String notificationUuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidNotificationnotificationUuidTargetObjectNamevalueNameGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of notification",required=true) @PathVariable("notification-uuid") String notificationUuid,@ApiParam(value = "Id of target-object-name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidPut(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.notification.NotificationSubscriptionService to be added or updated"  )  @Valid @RequestBody TapiNotificationNotificationSubscriptionServiceWrapper tapiNotificationNotificationSubscriptionServiceBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidSubscriptionFilterDelete(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationSubscriptionFilterWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidSubscriptionFilterGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationSubscriptionFilterWrapper>(objectMapper.readValue("{  \"subscription-filter\" : \"\"}", TapiNotificationSubscriptionFilterWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationSubscriptionFilterWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationSubscriptionFilterWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidSubscriptionFilterNamePost(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidSubscriptionFilterNamevalueNameDelete(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidSubscriptionFilterNamevalueNameGet(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidSubscriptionFilterNamevalueNamePut(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidSubscriptionFilterPost(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.notification.SubscriptionFilter to be added to list"  )  @Valid @RequestBody TapiNotificationSubscriptionFilterWrapper tapiNotificationSubscriptionFilterBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextNotifSubscriptionuuidSubscriptionFilterPut(@ApiParam(value = "Id of notif-subscription",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.notification.SubscriptionFilter to be added or updated"  )  @Valid @RequestBody TapiNotificationSubscriptionFilterWrapper tapiNotificationSubscriptionFilterBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotificationuuidAdditionalInfovalueNameGet(@ApiParam(value = "Id of notification",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of additional-info",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationNameAndValueChangeWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotificationuuidChangedAttributesvalueNameGet(@ApiParam(value = "Id of notification",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of changed-attributes",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationNameAndValueChangeWrapper>(objectMapper.readValue("{  \"changed-attributes\" : {    \"value-name\" : \"value-name\",    \"old-value\" : \"old-value\",    \"new-value\" : \"new-value\"  }}", TapiNotificationNameAndValueChangeWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationNameAndValueChangeWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationNameAndValueChangeWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiNotificationNotificationWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotificationuuidGet(@ApiParam(value = "Id of notification",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiNotificationNotificationWrapper>(objectMapper.readValue("{  \"notification\" : \"\"}", TapiNotificationNotificationWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiNotificationNotificationWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiNotificationNotificationWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotificationuuidNamevalueNameGet(@ApiParam(value = "Id of notification",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiNotificationnotificationContextNotificationuuidTargetObjectNamevalueNameGet(@ApiParam(value = "Id of notification",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of target-object-name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextPost(@ApiParam(value = "tapi.notification.NotificationContext to be added to list"  )  @Valid @RequestBody TapiNotificationNotificationContextWrapper tapiNotificationNotificationContextBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiNotificationnotificationContextPut(@ApiParam(value = "tapi.notification.NotificationContext to be added or updated"  )  @Valid @RequestBody TapiNotificationNotificationContextWrapper tapiNotificationNotificationContextBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextDelete() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationPathComputationContextWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                LOG.info("Call me baby.");

                return new ResponseEntity<TapiPathComputationPathComputationContextWrapper>(objectMapper.readValue("{  \"tapi-path-computation:path-computation-context\" : {    \"path-comp-service\" : [ \"\", \"\" ],    \"path\" : [ \"\", \"\" ]  }}", TapiPathComputationPathComputationContextWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationPathComputationContextWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationPathComputationContextWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServicePost(@ApiParam(value = "tapi.path.computation.PathComputationService to be added to list"  )  @Valid @RequestBody TapiPathComputationPathComputationServiceWrapper tapiPathComputationPathComputationServiceBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.PathServiceEndPoint to be added to list"  )  @Valid @RequestBody TapiPathComputationPathServiceEndPointWrapper tapiPathComputationPathServiceEndPointBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdCapacityDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdCapacityGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdCapacityPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.common.Capacity to be added to list"  )  @Valid @RequestBody TapiCommonCapacityWrapper tapiCommonCapacityBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdCapacityPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.common.Capacity to be added or updated"  )  @Valid @RequestBody TapiCommonCapacityWrapper tapiCommonCapacityBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdCapacityTotalSizeDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdCapacityTotalSizeGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdCapacityTotalSizePost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.common.CapacityValue to be added to list"  )  @Valid @RequestBody TapiCommonCapacityValueWrapper tapiCommonCapacityValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdCapacityTotalSizePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.common.CapacityValue to be added or updated"  )  @Valid @RequestBody TapiCommonCapacityValueWrapper tapiCommonCapacityValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationPathServiceEndPointWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationPathServiceEndPointWrapper>(objectMapper.readValue("{  \"end-point\" : \"\"}", TapiPathComputationPathServiceEndPointWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationPathServiceEndPointWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationPathServiceEndPointWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdNamePost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdNamevalueNameDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdNamevalueNameGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdNamevalueNamePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.path.computation.PathServiceEndPoint to be added or updated"  )  @Valid @RequestBody TapiPathComputationPathServiceEndPointWrapper tapiPathComputationPathServiceEndPointBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdServiceInterfacePointDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonServiceInterfacePointRefWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdServiceInterfacePointGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonServiceInterfacePointRefWrapper>(objectMapper.readValue("{  \"service-interface-point\" : {    \"service-interface-point-uuid\" : \"service-interface-point-uuid\"  }}", TapiCommonServiceInterfacePointRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonServiceInterfacePointRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonServiceInterfacePointRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdServiceInterfacePointPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.common.ServiceInterfacePointRef to be added to list"  )  @Valid @RequestBody TapiCommonServiceInterfacePointRefWrapper tapiCommonServiceInterfacePointRefBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidEndPointlocalIdServiceInterfacePointPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of end-point",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.common.ServiceInterfacePointRef to be added or updated"  )  @Valid @RequestBody TapiCommonServiceInterfacePointRefWrapper tapiCommonServiceInterfacePointRefBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationPathComputationServiceWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationPathComputationServiceWrapper>(objectMapper.readValue("{  \"path-comp-service\" : \"\"}", TapiPathComputationPathComputationServiceWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationPathComputationServiceWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationPathComputationServiceWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidNamePost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidNamevalueNameDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidNamevalueNameGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidNamevalueNamePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidObjectiveFunctionDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationPathObjectiveFunctionWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidObjectiveFunctionGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationPathObjectiveFunctionWrapper>(objectMapper.readValue("{  \"objective-function\" : \"\"}", TapiPathComputationPathObjectiveFunctionWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationPathObjectiveFunctionWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationPathObjectiveFunctionWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidObjectiveFunctionNamePost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidObjectiveFunctionNamevalueNameDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidObjectiveFunctionNamevalueNameGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidObjectiveFunctionNamevalueNamePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidObjectiveFunctionPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.PathObjectiveFunction to be added to list"  )  @Valid @RequestBody TapiPathComputationPathObjectiveFunctionWrapper tapiPathComputationPathObjectiveFunctionBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidObjectiveFunctionPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.PathObjectiveFunction to be added or updated"  )  @Valid @RequestBody TapiPathComputationPathObjectiveFunctionWrapper tapiPathComputationPathObjectiveFunctionBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidOptimizationConstraintDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationPathOptimizationConstraintWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidOptimizationConstraintGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationPathOptimizationConstraintWrapper>(objectMapper.readValue("{  \"optimization-constraint\" : \"\"}", TapiPathComputationPathOptimizationConstraintWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationPathOptimizationConstraintWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationPathOptimizationConstraintWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidOptimizationConstraintNamePost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidOptimizationConstraintNamevalueNameDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidOptimizationConstraintNamevalueNameGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidOptimizationConstraintNamevalueNamePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidOptimizationConstraintPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.PathOptimizationConstraint to be added to list"  )  @Valid @RequestBody TapiPathComputationPathOptimizationConstraintWrapper tapiPathComputationPathOptimizationConstraintBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidOptimizationConstraintPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.PathOptimizationConstraint to be added or updated"  )  @Valid @RequestBody TapiPathComputationPathOptimizationConstraintWrapper tapiPathComputationPathOptimizationConstraintBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationPathRefWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidPathpathUuidGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of path",required=true) @PathVariable("path-uuid") String pathUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationPathRefWrapper>(objectMapper.readValue("{  \"path\" : {    \"path-uuid\" : \"path-uuid\"  }}", TapiPathComputationPathRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationPathRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationPathRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.PathComputationService to be added or updated"  )  @Valid @RequestBody TapiPathComputationPathComputationServiceWrapper tapiPathComputationPathComputationServiceBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintCostCharacteristicPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.topology.CostCharacteristic to be added to list"  )  @Valid @RequestBody TapiTopologyCostCharacteristicWrapper tapiTopologyCostCharacteristicBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintCostCharacteristiccostNameDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of cost-characteristic",required=true) @PathVariable("cost-name") String costName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyCostCharacteristicWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintCostCharacteristiccostNameGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of cost-characteristic",required=true) @PathVariable("cost-name") String costName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(objectMapper.readValue("{  \"cost-characteristic\" : {    \"cost-value\" : \"cost-value\",    \"cost-algorithm\" : \"cost-algorithm\",    \"cost-name\" : \"cost-name\"  }}", TapiTopologyCostCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintCostCharacteristiccostNamePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of cost-characteristic",required=true) @PathVariable("cost-name") String costName,@ApiParam(value = "tapi.topology.CostCharacteristic to be added or updated"  )  @Valid @RequestBody TapiTopologyCostCharacteristicWrapper tapiTopologyCostCharacteristicBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationRoutingConstraintWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationRoutingConstraintWrapper>(objectMapper.readValue("{  \"routing-constraint\" : {    \"is-exclusive\" : true,    \"max-allowed-cost\" : {      \"priority\" : 0,      \"value\" : 6    },    \"tolerable-impact\" : \"tolerable-impact\",    \"diversity-policy\" : { },    \"route-objective-function\" : { },    \"cost-characteristic\" : [ {      \"cost-value\" : \"cost-value\",      \"cost-algorithm\" : \"cost-algorithm\",      \"cost-name\" : \"cost-name\"    }, {      \"cost-value\" : \"cost-value\",      \"cost-algorithm\" : \"cost-algorithm\",      \"cost-name\" : \"cost-name\"    } ],    \"max-allowed-hops\" : {      \"priority\" : 0,      \"value\" : 6    },    \"max-allowed-delay\" : {      \"priority\" : 0,      \"value\" : 6    },    \"latency-characteristic\" : [ {      \"traffic-property-name\" : \"traffic-property-name\",      \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",      \"wander-characteristic\" : \"wander-characteristic\",      \"jitter-characteristic\" : \"jitter-characteristic\",      \"queing-latency-characteristic\" : \"queing-latency-characteristic\"    }, {      \"traffic-property-name\" : \"traffic-property-name\",      \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",      \"wander-characteristic\" : \"wander-characteristic\",      \"jitter-characteristic\" : \"jitter-characteristic\",      \"queing-latency-characteristic\" : \"queing-latency-characteristic\"    } ],    \"risk-diversity-characteristic\" : [ {      \"risk-characteristic-name\" : \"risk-characteristic-name\",      \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]    }, {      \"risk-characteristic-name\" : \"risk-characteristic-name\",      \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]    } ]  }}", TapiPathComputationRoutingConstraintWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationRoutingConstraintWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationRoutingConstraintWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintLatencyCharacteristicPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.topology.LatencyCharacteristic to be added to list"  )  @Valid @RequestBody TapiTopologyLatencyCharacteristicWrapper tapiTopologyLatencyCharacteristicBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintLatencyCharacteristictrafficPropertyNameDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of latency-characteristic",required=true) @PathVariable("traffic-property-name") String trafficPropertyName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyLatencyCharacteristicWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintLatencyCharacteristictrafficPropertyNameGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of latency-characteristic",required=true) @PathVariable("traffic-property-name") String trafficPropertyName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(objectMapper.readValue("{  \"latency-characteristic\" : {    \"traffic-property-name\" : \"traffic-property-name\",    \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",    \"wander-characteristic\" : \"wander-characteristic\",    \"jitter-characteristic\" : \"jitter-characteristic\",    \"queing-latency-characteristic\" : \"queing-latency-characteristic\"  }}", TapiTopologyLatencyCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintLatencyCharacteristictrafficPropertyNamePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of latency-characteristic",required=true) @PathVariable("traffic-property-name") String trafficPropertyName,@ApiParam(value = "tapi.topology.LatencyCharacteristic to be added or updated"  )  @Valid @RequestBody TapiTopologyLatencyCharacteristicWrapper tapiTopologyLatencyCharacteristicBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedCostDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationValueOrPriorityWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedCostGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(objectMapper.readValue("{  \"max-allowed-cost\" : {    \"priority\" : 0,    \"value\" : 6  }}", TapiPathComputationValueOrPriorityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedCostPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.ValueOrPriority to be added to list"  )  @Valid @RequestBody TapiPathComputationValueOrPriorityWrapper tapiPathComputationValueOrPriorityBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedCostPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.ValueOrPriority to be added or updated"  )  @Valid @RequestBody TapiPathComputationValueOrPriorityWrapper tapiPathComputationValueOrPriorityBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedDelayDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationValueOrPriorityWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedDelayGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(objectMapper.readValue("{  \"max-allowed-cost\" : {    \"priority\" : 0,    \"value\" : 6  }}", TapiPathComputationValueOrPriorityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedDelayPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.ValueOrPriority to be added to list"  )  @Valid @RequestBody TapiPathComputationValueOrPriorityWrapper tapiPathComputationValueOrPriorityBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedDelayPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.ValueOrPriority to be added or updated"  )  @Valid @RequestBody TapiPathComputationValueOrPriorityWrapper tapiPathComputationValueOrPriorityBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedHopsDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationValueOrPriorityWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedHopsGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(objectMapper.readValue("{  \"max-allowed-cost\" : {    \"priority\" : 0,    \"value\" : 6  }}", TapiPathComputationValueOrPriorityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedHopsPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.ValueOrPriority to be added to list"  )  @Valid @RequestBody TapiPathComputationValueOrPriorityWrapper tapiPathComputationValueOrPriorityBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintMaxAllowedHopsPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.ValueOrPriority to be added or updated"  )  @Valid @RequestBody TapiPathComputationValueOrPriorityWrapper tapiPathComputationValueOrPriorityBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.RoutingConstraint to be added to list"  )  @Valid @RequestBody TapiPathComputationRoutingConstraintWrapper tapiPathComputationRoutingConstraintBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.RoutingConstraint to be added or updated"  )  @Valid @RequestBody TapiPathComputationRoutingConstraintWrapper tapiPathComputationRoutingConstraintBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintRiskDiversityCharacteristicPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.topology.RiskCharacteristic to be added to list"  )  @Valid @RequestBody TapiTopologyRiskCharacteristicWrapper tapiTopologyRiskCharacteristicBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintRiskDiversityCharacteristicriskCharacteristicNameDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of risk-diversity-characteristic",required=true) @PathVariable("risk-characteristic-name") String riskCharacteristicName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyRiskCharacteristicWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintRiskDiversityCharacteristicriskCharacteristicNameGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of risk-diversity-characteristic",required=true) @PathVariable("risk-characteristic-name") String riskCharacteristicName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(objectMapper.readValue("{  \"risk-diversity-characteristic\" : {    \"risk-characteristic-name\" : \"risk-characteristic-name\",    \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]  }}", TapiTopologyRiskCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidRoutingConstraintRiskDiversityCharacteristicriskCharacteristicNamePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of risk-diversity-characteristic",required=true) @PathVariable("risk-characteristic-name") String riskCharacteristicName,@ApiParam(value = "tapi.topology.RiskCharacteristic to be added or updated"  )  @Valid @RequestBody TapiTopologyRiskCharacteristicWrapper tapiTopologyRiskCharacteristicBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidTopologyConstraintPost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "tapi.path.computation.TopologyConstraint to be added to list"  )  @Valid @RequestBody TapiPathComputationTopologyConstraintWrapper tapiPathComputationTopologyConstraintBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidTopologyConstraintlocalIdDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of topology-constraint",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationTopologyConstraintWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidTopologyConstraintlocalIdGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of topology-constraint",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationTopologyConstraintWrapper>(objectMapper.readValue("{  \"topology-constraint\" : \"\"}", TapiPathComputationTopologyConstraintWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationTopologyConstraintWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationTopologyConstraintWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidTopologyConstraintlocalIdNamePost(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of topology-constraint",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.common.NameAndValue to be added to list"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidTopologyConstraintlocalIdNamevalueNameDelete(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of topology-constraint",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidTopologyConstraintlocalIdNamevalueNameGet(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of topology-constraint",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidTopologyConstraintlocalIdNamevalueNamePut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of topology-constraint",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName,@ApiParam(value = "tapi.common.NameAndValue to be added or updated"  )  @Valid @RequestBody TapiCommonNameAndValueWrapper tapiCommonNameAndValueBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPathCompServiceuuidTopologyConstraintlocalIdPut(@ApiParam(value = "Id of path-comp-service",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of topology-constraint",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "tapi.path.computation.TopologyConstraint to be added or updated"  )  @Valid @RequestBody TapiPathComputationTopologyConstraintWrapper tapiPathComputationTopologyConstraintBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }




    public ResponseEntity<TapiPathComputationPathWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid) {

        log.info("Received request to get ERO of path with Uuid "+uuid);
        TapiPathComputationPath tapiPathComputationPath = pceService.getEro(uuid);

        String accept = request.getHeader("Accept");
        if (accept != null && (accept.contains("application/json") || accept.contains("application/yang-data+json"))) {
            try {
                if(tapiPathComputationPath==null){
                    log.warn("The path with Uuid "+uuid+" was not found.");
                    return new ResponseEntity<TapiPathComputationPathWrapper>(HttpStatus.NOT_FOUND);
                }
                TapiPathComputationPathWrapper tapiPathComputationPathWrapper = new TapiPathComputationPathWrapper();
                tapiPathComputationPathWrapper.setPath(tapiPathComputationPath);

                String jsonString = objectMapper.writeValueAsString(tapiPathComputationPathWrapper);
                return new ResponseEntity<TapiPathComputationPathWrapper>(objectMapper.readValue(jsonString, TapiPathComputationPathWrapper.class), HttpStatus.OK);
            } catch (IOException e) {
                log.error(e.getMessage());
                log.error("Couldn't serialize response for content type application/json");
                return new ResponseEntity<TapiPathComputationPathWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationPathWrapper>(HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<TapiTopologyLinkRefWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidLinktopologyUuidlinkUuidGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("topology-uuid") String topologyUuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyLinkRefWrapper>(objectMapper.readValue("{  \"link\" : \"\"}", TapiTopologyLinkRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyLinkRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyLinkRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidNamevalueNameGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyCostCharacteristicWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidRoutingConstraintCostCharacteristiccostNameGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of cost-characteristic",required=true) @PathVariable("cost-name") String costName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(objectMapper.readValue("{  \"cost-characteristic\" : {    \"cost-value\" : \"cost-value\",    \"cost-algorithm\" : \"cost-algorithm\",    \"cost-name\" : \"cost-name\"  }}", TapiTopologyCostCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationRoutingConstraintWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidRoutingConstraintGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationRoutingConstraintWrapper>(objectMapper.readValue("{  \"routing-constraint\" : {    \"is-exclusive\" : true,    \"max-allowed-cost\" : {      \"priority\" : 0,      \"value\" : 6    },    \"tolerable-impact\" : \"tolerable-impact\",    \"diversity-policy\" : { },    \"route-objective-function\" : { },    \"cost-characteristic\" : [ {      \"cost-value\" : \"cost-value\",      \"cost-algorithm\" : \"cost-algorithm\",      \"cost-name\" : \"cost-name\"    }, {      \"cost-value\" : \"cost-value\",      \"cost-algorithm\" : \"cost-algorithm\",      \"cost-name\" : \"cost-name\"    } ],    \"max-allowed-hops\" : {      \"priority\" : 0,      \"value\" : 6    },    \"max-allowed-delay\" : {      \"priority\" : 0,      \"value\" : 6    },    \"latency-characteristic\" : [ {      \"traffic-property-name\" : \"traffic-property-name\",      \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",      \"wander-characteristic\" : \"wander-characteristic\",      \"jitter-characteristic\" : \"jitter-characteristic\",      \"queing-latency-characteristic\" : \"queing-latency-characteristic\"    }, {      \"traffic-property-name\" : \"traffic-property-name\",      \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",      \"wander-characteristic\" : \"wander-characteristic\",      \"jitter-characteristic\" : \"jitter-characteristic\",      \"queing-latency-characteristic\" : \"queing-latency-characteristic\"    } ],    \"risk-diversity-characteristic\" : [ {      \"risk-characteristic-name\" : \"risk-characteristic-name\",      \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]    }, {      \"risk-characteristic-name\" : \"risk-characteristic-name\",      \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]    } ]  }}", TapiPathComputationRoutingConstraintWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationRoutingConstraintWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationRoutingConstraintWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyLatencyCharacteristicWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidRoutingConstraintLatencyCharacteristictrafficPropertyNameGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of latency-characteristic",required=true) @PathVariable("traffic-property-name") String trafficPropertyName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(objectMapper.readValue("{  \"latency-characteristic\" : {    \"traffic-property-name\" : \"traffic-property-name\",    \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",    \"wander-characteristic\" : \"wander-characteristic\",    \"jitter-characteristic\" : \"jitter-characteristic\",    \"queing-latency-characteristic\" : \"queing-latency-characteristic\"  }}", TapiTopologyLatencyCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationValueOrPriorityWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidRoutingConstraintMaxAllowedCostGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(objectMapper.readValue("{  \"max-allowed-cost\" : {    \"priority\" : 0,    \"value\" : 6  }}", TapiPathComputationValueOrPriorityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationValueOrPriorityWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidRoutingConstraintMaxAllowedDelayGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(objectMapper.readValue("{  \"max-allowed-cost\" : {    \"priority\" : 0,    \"value\" : 6  }}", TapiPathComputationValueOrPriorityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiPathComputationValueOrPriorityWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidRoutingConstraintMaxAllowedHopsGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(objectMapper.readValue("{  \"max-allowed-cost\" : {    \"priority\" : 0,    \"value\" : 6  }}", TapiPathComputationValueOrPriorityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiPathComputationValueOrPriorityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyRiskCharacteristicWrapper> dataTapiCommoncontextTapiPathComputationpathComputationContextPathuuidRoutingConstraintRiskDiversityCharacteristicriskCharacteristicNameGet(@ApiParam(value = "Id of path",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of risk-diversity-characteristic",required=true) @PathVariable("risk-characteristic-name") String riskCharacteristicName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(objectMapper.readValue("{  \"risk-diversity-characteristic\" : {    \"risk-characteristic-name\" : \"risk-characteristic-name\",    \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]  }}", TapiTopologyRiskCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPost(@ApiParam(value = "tapi.path.computation.PathComputationContext to be added to list"  )  @Valid @RequestBody TapiPathComputationPathComputationContextWrapper tapiPathComputationPathComputationContextBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiPathComputationpathComputationContextPut(@ApiParam(value = "tapi.path.computation.PathComputationContext to be added or updated"  )  @Valid @RequestBody TapiPathComputationPathComputationContextWrapper tapiPathComputationPathComputationContextBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiTopologytopologyContextDelete() {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyTopologyContextWrapper> dataTapiCommoncontextTapiTopologytopologyContextGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyTopologyContextWrapper>(objectMapper.readValue("{  \"tapi-topology:topology-context\" : {    \"nw-topology-service\" : \"\",    \"topology\" : [ \"\", \"\" ]  }}", TapiTopologyTopologyContextWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyTopologyContextWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyTopologyContextWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNetworkTopologyServiceWrapper> dataTapiCommoncontextTapiTopologytopologyContextNwTopologyServiceGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNetworkTopologyServiceWrapper>(objectMapper.readValue("{  \"nw-topology-service\" : \"\"}", TapiTopologyNetworkTopologyServiceWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNetworkTopologyServiceWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNetworkTopologyServiceWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextNwTopologyServiceNamevalueNameGet(@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyTopologyRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextNwTopologyServiceTopologytopologyUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("topology-uuid") String topologyUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyTopologyRefWrapper>(objectMapper.readValue("{  \"topology\" : {    \"topology-uuid\" : \"topology-uuid\"  }}", TapiTopologyTopologyRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyTopologyRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyTopologyRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiTopologytopologyContextPost(@ApiParam(value = "tapi.topology.TopologyContext to be added to list"  )  @Valid @RequestBody TapiTopologyTopologyContextWrapper tapiTopologyTopologyContextBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> dataTapiCommoncontextTapiTopologytopologyContextPut(@ApiParam(value = "tapi.topology.TopologyContext to be added or updated"  )  @Valid @RequestBody TapiTopologyTopologyContextWrapper tapiTopologyTopologyContextBodyParam) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeEdgePointRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidBoundaryNodeEdgePointtopologyUuidnodeUuidnodeEdgePointUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of boundary-node-edge-point",required=true) @PathVariable("topology-uuid") String topologyUuid,@ApiParam(value = "Id of boundary-node-edge-point",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of boundary-node-edge-point",required=true) @PathVariable("node-edge-point-uuid") String nodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(objectMapper.readValue("{  \"boundary-node-edge-point\" : \"\"}", TapiTopologyNodeEdgePointRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyTopologyWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyTopologyWrapper>(objectMapper.readValue("{  \"topology\" : \"\"}", TapiTopologyTopologyWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyTopologyWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyTopologyWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidAvailableCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidAvailableCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyCostCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidCostCharacteristiccostNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid,@ApiParam(value = "Id of cost-characteristic",required=true) @PathVariable("cost-name") String costName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(objectMapper.readValue("{  \"cost-characteristic\" : {    \"cost-value\" : \"cost-value\",    \"cost-algorithm\" : \"cost-algorithm\",    \"cost-name\" : \"cost-name\"  }}", TapiTopologyCostCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyLinkWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyLinkWrapper>(objectMapper.readValue("{  \"link\" : \"\"}", TapiTopologyLinkWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyLinkWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyLinkWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyLatencyCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidLatencyCharacteristictrafficPropertyNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid,@ApiParam(value = "Id of latency-characteristic",required=true) @PathVariable("traffic-property-name") String trafficPropertyName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(objectMapper.readValue("{  \"latency-characteristic\" : {    \"traffic-property-name\" : \"traffic-property-name\",    \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",    \"wander-characteristic\" : \"wander-characteristic\",    \"jitter-characteristic\" : \"jitter-characteristic\",    \"queing-latency-characteristic\" : \"queing-latency-characteristic\"  }}", TapiTopologyLatencyCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidNamevalueNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeEdgePointRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidNodeEdgePointtopologyUuidnodeUuidnodeEdgePointUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid,@ApiParam(value = "Id of node-edge-point",required=true) @PathVariable("topology-uuid") String topologyUuid,@ApiParam(value = "Id of node-edge-point",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-edge-point",required=true) @PathVariable("node-edge-point-uuid") String nodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(objectMapper.readValue("{  \"boundary-node-edge-point\" : \"\"}", TapiTopologyNodeEdgePointRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyResilienceTypeWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidResilienceTypeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyResilienceTypeWrapper>(objectMapper.readValue("{  \"resilience-type\" : {    \"restoration-policy\" : { },    \"protection-type\" : { }  }}", TapiTopologyResilienceTypeWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyResilienceTypeWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyResilienceTypeWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyRiskCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidRiskCharacteristicriskCharacteristicNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid,@ApiParam(value = "Id of risk-characteristic",required=true) @PathVariable("risk-characteristic-name") String riskCharacteristicName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(objectMapper.readValue("{  \"risk-diversity-characteristic\" : {    \"risk-characteristic-name\" : \"risk-characteristic-name\",    \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]  }}", TapiTopologyRiskCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidTotalPotentialCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidTotalPotentialCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyValidationMechanismWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidLinklinkUuidValidationMechanismvalidationMechanismGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of link",required=true) @PathVariable("link-uuid") String linkUuid,@ApiParam(value = "Id of validation-mechanism",required=true) @PathVariable("validation-mechanism") String validationMechanism) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyValidationMechanismWrapper>(objectMapper.readValue("{  \"validation-mechanism\" : {    \"layer-protocol-adjacency-validated\" : \"layer-protocol-adjacency-validated\",    \"validation-mechanism\" : \"validation-mechanism\",    \"validation-robustness\" : \"validation-robustness\"  }}", TapiTopologyValidationMechanismWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyValidationMechanismWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyValidationMechanismWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNamevalueNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeEdgePointRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidAggregatedNodeEdgePointtopologyUuidaggregatedNodeEdgePointNodeUuidnodeEdgePointUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of aggregated-node-edge-point",required=true) @PathVariable("topology-uuid") String topologyUuid,@ApiParam(value = "Id of aggregated-node-edge-point",required=true) @PathVariable("aggregated-node-edge-point-node-uuid") String aggregatedNodeEdgePointNodeUuid,@ApiParam(value = "Id of aggregated-node-edge-point",required=true) @PathVariable("node-edge-point-uuid") String nodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(objectMapper.readValue("{  \"boundary-node-edge-point\" : \"\"}", TapiTopologyNodeEdgePointRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidAvailableCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidAvailableCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyCostCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidCostCharacteristiccostNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of cost-characteristic",required=true) @PathVariable("cost-name") String costName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(objectMapper.readValue("{  \"cost-characteristic\" : {    \"cost-value\" : \"cost-value\",    \"cost-algorithm\" : \"cost-algorithm\",    \"cost-name\" : \"cost-name\"  }}", TapiTopologyCostCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyTopologyRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidEncapTopologyGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyTopologyRefWrapper>(objectMapper.readValue("{  \"topology\" : {    \"topology-uuid\" : \"topology-uuid\"  }}", TapiTopologyTopologyRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyTopologyRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyTopologyRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeWrapper>(objectMapper.readValue("{  \"node\" : \"\"}", TapiTopologyNodeWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyLatencyCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidLatencyCharacteristictrafficPropertyNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of latency-characteristic",required=true) @PathVariable("traffic-property-name") String trafficPropertyName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(objectMapper.readValue("{  \"latency-characteristic\" : {    \"traffic-property-name\" : \"traffic-property-name\",    \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",    \"wander-characteristic\" : \"wander-characteristic\",    \"jitter-characteristic\" : \"jitter-characteristic\",    \"queing-latency-characteristic\" : \"queing-latency-characteristic\"  }}", TapiTopologyLatencyCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNamevalueNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidAvailableCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidAvailableCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeRuleGroupRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidComposedRuleGrouptopologyUuidcomposedRuleGroupNodeUuidcomposedRuleGroupNodeRuleGroupUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of composed-rule-group",required=true) @PathVariable("topology-uuid") String topologyUuid,@ApiParam(value = "Id of composed-rule-group",required=true) @PathVariable("composed-rule-group-node-uuid") String composedRuleGroupNodeUuid,@ApiParam(value = "Id of composed-rule-group",required=true) @PathVariable("composed-rule-group-node-rule-group-uuid") String composedRuleGroupNodeRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeRuleGroupRefWrapper>(objectMapper.readValue("{  \"composed-rule-group\" : \"\"}", TapiTopologyNodeRuleGroupRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeRuleGroupRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeRuleGroupRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyCostCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidCostCharacteristiccostNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of cost-characteristic",required=true) @PathVariable("cost-name") String costName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(objectMapper.readValue("{  \"cost-characteristic\" : {    \"cost-value\" : \"cost-value\",    \"cost-algorithm\" : \"cost-algorithm\",    \"cost-name\" : \"cost-name\"  }}", TapiTopologyCostCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeRuleGroupWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeRuleGroupWrapper>(objectMapper.readValue("{  \"node-rule-group\" : \"\"}", TapiTopologyNodeRuleGroupWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeRuleGroupWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeRuleGroupWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeRuleGroupRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidAssociatedNodeRuleGrouptopologyUuidassociatedNodeRuleGroupNodeUuidassociatedNodeRuleGroupNodeRuleGroupUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of associated-node-rule-group",required=true) @PathVariable("topology-uuid") String topologyUuid,@ApiParam(value = "Id of associated-node-rule-group",required=true) @PathVariable("associated-node-rule-group-node-uuid") String associatedNodeRuleGroupNodeUuid,@ApiParam(value = "Id of associated-node-rule-group",required=true) @PathVariable("associated-node-rule-group-node-rule-group-uuid") String associatedNodeRuleGroupNodeRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeRuleGroupRefWrapper>(objectMapper.readValue("{  \"composed-rule-group\" : \"\"}", TapiTopologyNodeRuleGroupRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeRuleGroupRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeRuleGroupRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidAvailableCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidAvailableCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyCostCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidCostCharacteristiccostNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of cost-characteristic",required=true) @PathVariable("cost-name") String costName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(objectMapper.readValue("{  \"cost-characteristic\" : {    \"cost-value\" : \"cost-value\",    \"cost-algorithm\" : \"cost-algorithm\",    \"cost-name\" : \"cost-name\"  }}", TapiTopologyCostCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyCostCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyInterRuleGroupWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyInterRuleGroupWrapper>(objectMapper.readValue("{  \"inter-rule-group\" : \"\"}", TapiTopologyInterRuleGroupWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyInterRuleGroupWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyInterRuleGroupWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyLatencyCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidLatencyCharacteristictrafficPropertyNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of latency-characteristic",required=true) @PathVariable("traffic-property-name") String trafficPropertyName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(objectMapper.readValue("{  \"latency-characteristic\" : {    \"traffic-property-name\" : \"traffic-property-name\",    \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",    \"wander-characteristic\" : \"wander-characteristic\",    \"jitter-characteristic\" : \"jitter-characteristic\",    \"queing-latency-characteristic\" : \"queing-latency-characteristic\"  }}", TapiTopologyLatencyCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidNamevalueNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyRiskCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidRiskCharacteristicriskCharacteristicNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of risk-characteristic",required=true) @PathVariable("risk-characteristic-name") String riskCharacteristicName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(objectMapper.readValue("{  \"risk-diversity-characteristic\" : {    \"risk-characteristic-name\" : \"risk-characteristic-name\",    \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]  }}", TapiTopologyRiskCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyPortRoleRuleWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidRulelocalIdCepPortRoleGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyPortRoleRuleWrapper>(objectMapper.readValue("{  \"cep-port-role\" : {    \"port-role-rule\" : [ \"port-role-rule\", \"port-role-rule\" ],    \"port-role\" : [ \"port-role\", \"port-role\" ]  }}", TapiTopologyPortRoleRuleWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyPortRoleRuleWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyPortRoleRuleWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyConnectionSpecReferenceWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidRulelocalIdConnectionSpecReferenceGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyConnectionSpecReferenceWrapper>(objectMapper.readValue("{  \"connection-spec-reference\" : {    \"connection-spec-name\" : \"connection-spec-name\",    \"connection-spec\" : \"connection-spec\"  }}", TapiTopologyConnectionSpecReferenceWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyConnectionSpecReferenceWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyConnectionSpecReferenceWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyRuleWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidRulelocalIdGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyRuleWrapper>(objectMapper.readValue("{  \"rule\" : \"\"}", TapiTopologyRuleWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyRuleWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyRuleWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidRulelocalIdNamevalueNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologySignalPropertyRuleWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidRulelocalIdSignalPropertyGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologySignalPropertyRuleWrapper>(objectMapper.readValue("{  \"signal-property\" : {    \"number-of-signal-values\" : 0,    \"applicable-signal-value\" : [ \"applicable-signal-value\", \"applicable-signal-value\" ],    \"signal-property-value-rule\" : \"signal-property-value-rule\",    \"signal-property-name\" : \"signal-property-name\"  }}", TapiTopologySignalPropertyRuleWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologySignalPropertyRuleWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologySignalPropertyRuleWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidTotalPotentialCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidInterRuleGroupinterRuleGroupUuidTotalPotentialCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of inter-rule-group",required=true) @PathVariable("inter-rule-group-uuid") String interRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyLatencyCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidLatencyCharacteristictrafficPropertyNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of latency-characteristic",required=true) @PathVariable("traffic-property-name") String trafficPropertyName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(objectMapper.readValue("{  \"latency-characteristic\" : {    \"traffic-property-name\" : \"traffic-property-name\",    \"fixed-latency-characteristic\" : \"fixed-latency-characteristic\",    \"wander-characteristic\" : \"wander-characteristic\",    \"jitter-characteristic\" : \"jitter-characteristic\",    \"queing-latency-characteristic\" : \"queing-latency-characteristic\"  }}", TapiTopologyLatencyCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyLatencyCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidNamevalueNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeEdgePointRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidNodeEdgePointtopologyUuidnodeEdgePointNodeUuidnodeEdgePointUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of node-edge-point",required=true) @PathVariable("topology-uuid") String topologyUuid,@ApiParam(value = "Id of node-edge-point",required=true) @PathVariable("node-edge-point-node-uuid") String nodeEdgePointNodeUuid,@ApiParam(value = "Id of node-edge-point",required=true) @PathVariable("node-edge-point-uuid") String nodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(objectMapper.readValue("{  \"boundary-node-edge-point\" : \"\"}", TapiTopologyNodeEdgePointRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyRiskCharacteristicWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidRiskCharacteristicriskCharacteristicNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of risk-characteristic",required=true) @PathVariable("risk-characteristic-name") String riskCharacteristicName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(objectMapper.readValue("{  \"risk-diversity-characteristic\" : {    \"risk-characteristic-name\" : \"risk-characteristic-name\",    \"risk-identifier-list\" : [ \"risk-identifier-list\", \"risk-identifier-list\" ]  }}", TapiTopologyRiskCharacteristicWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyRiskCharacteristicWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyPortRoleRuleWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidRulelocalIdCepPortRoleGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyPortRoleRuleWrapper>(objectMapper.readValue("{  \"cep-port-role\" : {    \"port-role-rule\" : [ \"port-role-rule\", \"port-role-rule\" ],    \"port-role\" : [ \"port-role\", \"port-role\" ]  }}", TapiTopologyPortRoleRuleWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyPortRoleRuleWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyPortRoleRuleWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyConnectionSpecReferenceWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidRulelocalIdConnectionSpecReferenceGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyConnectionSpecReferenceWrapper>(objectMapper.readValue("{  \"connection-spec-reference\" : {    \"connection-spec-name\" : \"connection-spec-name\",    \"connection-spec\" : \"connection-spec\"  }}", TapiTopologyConnectionSpecReferenceWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyConnectionSpecReferenceWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyConnectionSpecReferenceWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyRuleWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidRulelocalIdGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyRuleWrapper>(objectMapper.readValue("{  \"rule\" : \"\"}", TapiTopologyRuleWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyRuleWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyRuleWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidRulelocalIdNamevalueNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologySignalPropertyRuleWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidRulelocalIdSignalPropertyGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid,@ApiParam(value = "Id of rule",required=true) @PathVariable("local-id") String localId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologySignalPropertyRuleWrapper>(objectMapper.readValue("{  \"signal-property\" : {    \"number-of-signal-values\" : 0,    \"applicable-signal-value\" : [ \"applicable-signal-value\", \"applicable-signal-value\" ],    \"signal-property-value-rule\" : \"signal-property-value-rule\",    \"signal-property-name\" : \"signal-property-name\"  }}", TapiTopologySignalPropertyRuleWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologySignalPropertyRuleWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologySignalPropertyRuleWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidTotalPotentialCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidNodeRuleGroupnodeRuleGroupUuidTotalPotentialCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of node-rule-group",required=true) @PathVariable("node-rule-group-uuid") String nodeRuleGroupUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeEdgePointRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidAggregatedNodeEdgePointtopologyUuidaggregatedNodeEdgePointNodeUuidnodeEdgePointUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid,@ApiParam(value = "Id of aggregated-node-edge-point",required=true) @PathVariable("topology-uuid") String topologyUuid,@ApiParam(value = "Id of aggregated-node-edge-point",required=true) @PathVariable("aggregated-node-edge-point-node-uuid") String aggregatedNodeEdgePointNodeUuid,@ApiParam(value = "Id of aggregated-node-edge-point",required=true) @PathVariable("node-edge-point-uuid") String nodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(objectMapper.readValue("{  \"boundary-node-edge-point\" : \"\"}", TapiTopologyNodeEdgePointRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeEdgePointRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidAvailableCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidAvailableCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNepLayerProtocolCapabilityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidAvailableCepLayerProtocollayerProtocolQualifierGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid,@ApiParam(value = "Id of available-cep-layer-protocol",required=true) @PathVariable("layer-protocol-qualifier") String layerProtocolQualifier) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNepLayerProtocolCapabilityWrapper>(objectMapper.readValue("{  \"available-cep-layer-protocol\" : {    \"number-of-cep-instances\" : 0,    \"layer-protocol-qualifier\" : \"layer-protocol-qualifier\"  }}", TapiTopologyNepLayerProtocolCapabilityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNepLayerProtocolCapabilityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNepLayerProtocolCapabilityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiTopologyNodeEdgePointWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiTopologyNodeEdgePointWrapper>(objectMapper.readValue("{  \"owned-node-edge-point\" : \"\"}", TapiTopologyNodeEdgePointWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiTopologyNodeEdgePointWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiTopologyNodeEdgePointWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonServiceInterfacePointRefWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidMappedServiceInterfacePointserviceInterfacePointUuidGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid,@ApiParam(value = "Id of mapped-service-interface-point",required=true) @PathVariable("service-interface-point-uuid") String serviceInterfacePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonServiceInterfacePointRefWrapper>(objectMapper.readValue("{  \"service-interface-point\" : {    \"service-interface-point-uuid\" : \"service-interface-point-uuid\"  }}", TapiCommonServiceInterfacePointRefWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonServiceInterfacePointRefWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonServiceInterfacePointRefWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonNameAndValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidNamevalueNameGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid,@ApiParam(value = "Id of name",required=true) @PathVariable("value-name") String valueName) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(objectMapper.readValue("{  \"name\" : {    \"value-name\" : \"value-name\",    \"value\" : \"value\"  }}", TapiCommonNameAndValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonNameAndValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidTotalPotentialCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidOwnedNodeEdgePointownedNodeEdgePointUuidTotalPotentialCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid,@ApiParam(value = "Id of owned-node-edge-point",required=true) @PathVariable("owned-node-edge-point-uuid") String ownedNodeEdgePointUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidTotalPotentialCapacityGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityWrapper>(objectMapper.readValue("{  \"available-capacity\" : {    \"total-size\" : {      \"unit\" : { },      \"value\" : 0    }  }}", TapiCommonCapacityWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TapiCommonCapacityValueWrapper> dataTapiCommoncontextTapiTopologytopologyContextTopologyuuidNodenodeUuidTotalPotentialCapacityTotalSizeGet(@ApiParam(value = "Id of topology",required=true) @PathVariable("uuid") String uuid,@ApiParam(value = "Id of node",required=true) @PathVariable("node-uuid") String nodeUuid) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(objectMapper.readValue("{  \"total-size\" : {    \"unit\" : { },    \"value\" : 0  }}", TapiCommonCapacityValueWrapper.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<TapiCommonCapacityValueWrapper>(HttpStatus.NOT_IMPLEMENTED);
    }

}