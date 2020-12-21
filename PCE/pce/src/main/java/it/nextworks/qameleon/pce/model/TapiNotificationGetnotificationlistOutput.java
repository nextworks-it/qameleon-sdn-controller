package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationGetnotificationlistOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationGetnotificationlistOutput   {
  @JsonProperty("notification")
  @Valid
  private List<TapiNotificationNotification> notification = null;

  public TapiNotificationGetnotificationlistOutput notification(List<TapiNotificationNotification> notification) {
    this.notification = notification;
    return this;
  }

  public TapiNotificationGetnotificationlistOutput addNotificationItem(TapiNotificationNotification notificationItem) {
    if (this.notification == null) {
      this.notification = new ArrayList<TapiNotificationNotification>();
    }
    this.notification.add(notificationItem);
    return this;
  }

  /**
   * none
   * @return notification
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiNotificationNotification> getNotification() {
    return notification;
  }

  public void setNotification(List<TapiNotificationNotification> notification) {
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
    TapiNotificationGetnotificationlistOutput tapiNotificationGetnotificationlistOutput = (TapiNotificationGetnotificationlistOutput) o;
    return Objects.equals(this.notification, tapiNotificationGetnotificationlistOutput.notification);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notification);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationGetnotificationlistOutput {\n");
    
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

