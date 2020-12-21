package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationNotificationWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationNotificationWrapper   {
  @JsonProperty("notification")
  private TapiNotificationNotification notification = null;

  public TapiNotificationNotificationWrapper notification(TapiNotificationNotification notification) {
    this.notification = notification;
    return this;
  }

  /**
   * Get notification
   * @return notification
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiNotificationNotification getNotification() {
    return notification;
  }

  public void setNotification(TapiNotificationNotification notification) {
    this.notification = notification;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationNotificationWrapper tapiNotificationNotificationWrapper = (TapiNotificationNotificationWrapper) o;
    return Objects.equals(this.notification, tapiNotificationNotificationWrapper.notification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationNotificationWrapper {\n");
    
    sb.append("    notification: ").append(toIndentedString(notification)).append("\n");
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

