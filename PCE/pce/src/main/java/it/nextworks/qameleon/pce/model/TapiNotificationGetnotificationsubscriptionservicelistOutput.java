package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationGetnotificationsubscriptionservicelistOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationGetnotificationsubscriptionservicelistOutput   {
  @JsonProperty("subscription-service")
  @Valid
  private List<TapiNotificationNotificationSubscriptionService> subscriptionService = null;

  public TapiNotificationGetnotificationsubscriptionservicelistOutput subscriptionService(List<TapiNotificationNotificationSubscriptionService> subscriptionService) {
    this.subscriptionService = subscriptionService;
    return this;
  }

  public TapiNotificationGetnotificationsubscriptionservicelistOutput addSubscriptionServiceItem(TapiNotificationNotificationSubscriptionService subscriptionServiceItem) {
    if (this.subscriptionService == null) {
      this.subscriptionService = new ArrayList<TapiNotificationNotificationSubscriptionService>();
    }
    this.subscriptionService.add(subscriptionServiceItem);
    return this;
  }

  /**
   * none
   * @return subscriptionService
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiNotificationNotificationSubscriptionService> getSubscriptionService() {
    return subscriptionService;
  }

  public void setSubscriptionService(List<TapiNotificationNotificationSubscriptionService> subscriptionService) {
    this.subscriptionService = subscriptionService;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationGetnotificationsubscriptionservicelistOutput tapiNotificationGetnotificationsubscriptionservicelistOutput = (TapiNotificationGetnotificationsubscriptionservicelistOutput) o;
    return Objects.equals(this.subscriptionService, tapiNotificationGetnotificationsubscriptionservicelistOutput.subscriptionService);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionService);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationGetnotificationsubscriptionservicelistOutput {\n");
    
    sb.append("    subscriptionService: ").append(toIndentedString(subscriptionService)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

