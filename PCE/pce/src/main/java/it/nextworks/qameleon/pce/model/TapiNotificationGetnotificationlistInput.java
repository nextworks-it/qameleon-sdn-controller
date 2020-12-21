package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationGetnotificationlistInput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationGetnotificationlistInput   {
  @JsonProperty("time-range")
  private TapiCommonTimeRange timeRange = null;

  @JsonProperty("subscription-id")
  private String subscriptionId = null;

  public TapiNotificationGetnotificationlistInput timeRange(TapiCommonTimeRange timeRange) {
    this.timeRange = timeRange;
    return this;
  }

  /**
   * none
   * @return timeRange
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonTimeRange getTimeRange() {
    return timeRange;
  }

  public void setTimeRange(TapiCommonTimeRange timeRange) {
    this.timeRange = timeRange;
  }

  public TapiNotificationGetnotificationlistInput subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

  /**
   * UUID of the associated Notification Subscription Service: An identifier that is universally unique within an identifier space, where the identifier space is itself globally unique, and immutable.                      An UUID carries no semantics with respect to the purpose or state of the entity.                      UUID here uses string representation as defined in RFC 4122.  The canonical representation uses lowercase characters.                      Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}                       Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6
   * @return subscriptionId
  **/
  @ApiModelProperty(value = "UUID of the associated Notification Subscription Service: An identifier that is universally unique within an identifier space, where the identifier space is itself globally unique, and immutable.                      An UUID carries no semantics with respect to the purpose or state of the entity.                      UUID here uses string representation as defined in RFC 4122.  The canonical representation uses lowercase characters.                      Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}                       Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6")


  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationGetnotificationlistInput tapiNotificationGetnotificationlistInput = (TapiNotificationGetnotificationlistInput) o;
    return Objects.equals(this.timeRange, tapiNotificationGetnotificationlistInput.timeRange) &&
        Objects.equals(this.subscriptionId, tapiNotificationGetnotificationlistInput.subscriptionId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timeRange, subscriptionId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationGetnotificationlistInput {\n");
    
    sb.append("    timeRange: ").append(toIndentedString(timeRange)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
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

