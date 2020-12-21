package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationCreatenotificationsubscriptionserviceOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationCreatenotificationsubscriptionserviceOutput   {
  @JsonProperty("subscription-service")
  private TapiNotificationNotificationSubscriptionService subscriptionService = null;

  public TapiNotificationCreatenotificationsubscriptionserviceOutput subscriptionService(TapiNotificationNotificationSubscriptionService subscriptionService) {
    this.subscriptionService = subscriptionService;
    return this;
  }

  /**
   * none
   * @return subscriptionService
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiNotificationNotificationSubscriptionService getSubscriptionService() {
    return subscriptionService;
  }

  public void setSubscriptionService(TapiNotificationNotificationSubscriptionService subscriptionService) {
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
    TapiNotificationCreatenotificationsubscriptionserviceOutput tapiNotificationCreatenotificationsubscriptionserviceOutput = (TapiNotificationCreatenotificationsubscriptionserviceOutput) o;
    return Objects.equals(this.subscriptionService, tapiNotificationCreatenotificationsubscriptionserviceOutput.subscriptionService);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionService);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationCreatenotificationsubscriptionserviceOutput {\n");
    
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
