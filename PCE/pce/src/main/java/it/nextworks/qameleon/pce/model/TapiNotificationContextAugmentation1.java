package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationContextAugmentation1
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationContextAugmentation1   {
  @JsonProperty("notification-context")
  private TapiNotificationNotificationContext notificationContext = null;

  public TapiNotificationContextAugmentation1 notificationContext(TapiNotificationNotificationContext notificationContext) {
    this.notificationContext = notificationContext;
    return this;
  }

  /**
   * Augments the base TAPI Context with NotificationService information
   * @return notificationContext
  **/
  @ApiModelProperty(value = "Augments the base TAPI Context with NotificationService information")

  @Valid

  public TapiNotificationNotificationContext getNotificationContext() {
    return notificationContext;
  }

  public void setNotificationContext(TapiNotificationNotificationContext notificationContext) {
    this.notificationContext = notificationContext;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationContextAugmentation1 tapiNotificationContextAugmentation1 = (TapiNotificationContextAugmentation1) o;
    return Objects.equals(this.notificationContext, tapiNotificationContextAugmentation1.notificationContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationContext);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationContextAugmentation1 {\n");
    
    sb.append("    notificationContext: ").append(toIndentedString(notificationContext)).append("\n");
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

