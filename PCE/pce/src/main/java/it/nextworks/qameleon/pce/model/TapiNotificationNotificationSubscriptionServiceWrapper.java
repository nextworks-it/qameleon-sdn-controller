package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationNotificationSubscriptionServiceWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationNotificationSubscriptionServiceWrapper   {
  @JsonProperty("notif-subscription")
  private TapiNotificationNotificationSubscriptionService notifSubscription = null;

  public TapiNotificationNotificationSubscriptionServiceWrapper notifSubscription(TapiNotificationNotificationSubscriptionService notifSubscription) {
    this.notifSubscription = notifSubscription;
    return this;
  }

  /**
   * Get notifSubscription
   * @return notifSubscription
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiNotificationNotificationSubscriptionService getNotifSubscription() {
    return notifSubscription;
  }

  public void setNotifSubscription(TapiNotificationNotificationSubscriptionService notifSubscription) {
    this.notifSubscription = notifSubscription;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationNotificationSubscriptionServiceWrapper tapiNotificationNotificationSubscriptionServiceWrapper = (TapiNotificationNotificationSubscriptionServiceWrapper) o;
    return Objects.equals(this.notifSubscription, tapiNotificationNotificationSubscriptionServiceWrapper.notifSubscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notifSubscription);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationNotificationSubscriptionServiceWrapper {\n");
    
    sb.append("    notifSubscription: ").append(toIndentedString(notifSubscription)).append("\n");
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

