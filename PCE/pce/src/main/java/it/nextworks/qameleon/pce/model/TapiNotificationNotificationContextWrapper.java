package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationNotificationContextWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationNotificationContextWrapper   {
  @JsonProperty("tapi-notification:notification-context")
  private TapiNotificationNotificationContext tapiNotificationnotificationContext = null;

  public TapiNotificationNotificationContextWrapper tapiNotificationnotificationContext(TapiNotificationNotificationContext tapiNotificationnotificationContext) {
    this.tapiNotificationnotificationContext = tapiNotificationnotificationContext;
    return this;
  }

  /**
   * Get tapiNotificationnotificationContext
   * @return tapiNotificationnotificationContext
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiNotificationNotificationContext getTapiNotificationnotificationContext() {
    return tapiNotificationnotificationContext;
  }

  public void setTapiNotificationnotificationContext(TapiNotificationNotificationContext tapiNotificationnotificationContext) {
    this.tapiNotificationnotificationContext = tapiNotificationnotificationContext;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationNotificationContextWrapper tapiNotificationNotificationContextWrapper = (TapiNotificationNotificationContextWrapper) o;
    return Objects.equals(this.tapiNotificationnotificationContext, tapiNotificationNotificationContextWrapper.tapiNotificationnotificationContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tapiNotificationnotificationContext);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationNotificationContextWrapper {\n");
    
    sb.append("    tapiNotificationnotificationContext: ").append(toIndentedString(tapiNotificationnotificationContext)).append("\n");
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

