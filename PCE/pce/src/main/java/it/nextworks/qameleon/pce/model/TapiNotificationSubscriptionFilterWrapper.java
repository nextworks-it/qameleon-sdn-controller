package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationSubscriptionFilterWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationSubscriptionFilterWrapper   {
  @JsonProperty("subscription-filter")
  private TapiNotificationSubscriptionFilter subscriptionFilter = null;

  public TapiNotificationSubscriptionFilterWrapper subscriptionFilter(TapiNotificationSubscriptionFilter subscriptionFilter) {
    this.subscriptionFilter = subscriptionFilter;
    return this;
  }

  /**
   * Get subscriptionFilter
   * @return subscriptionFilter
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiNotificationSubscriptionFilter getSubscriptionFilter() {
    return subscriptionFilter;
  }

  public void setSubscriptionFilter(TapiNotificationSubscriptionFilter subscriptionFilter) {
    this.subscriptionFilter = subscriptionFilter;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationSubscriptionFilterWrapper tapiNotificationSubscriptionFilterWrapper = (TapiNotificationSubscriptionFilterWrapper) o;
    return Objects.equals(this.subscriptionFilter, tapiNotificationSubscriptionFilterWrapper.subscriptionFilter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionFilter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationSubscriptionFilterWrapper {\n");
    
    sb.append("    subscriptionFilter: ").append(toIndentedString(subscriptionFilter)).append("\n");
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

