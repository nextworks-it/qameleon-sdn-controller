package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonContext
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonContext extends TapiCommonTapiContext  {
  @JsonProperty("notification-context")
  private TapiNotificationNotificationContext notificationContext = null;

  @JsonProperty("path-computation-context")
  private TapiPathComputationPathComputationContext pathComputationContext = null;

  @JsonProperty("topology-context")
  private TapiTopologyTopologyContext topologyContext = null;

  public TapiCommonContext notificationContext(TapiNotificationNotificationContext notificationContext) {
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

  public TapiCommonContext pathComputationContext(TapiPathComputationPathComputationContext pathComputationContext) {
    this.pathComputationContext = pathComputationContext;
    return this;
  }

  /**
   * Augments the base TAPI Context with PathComputationService information
   * @return pathComputationContext
  **/
  @ApiModelProperty(value = "Augments the base TAPI Context with PathComputationService information")

  @Valid

  public TapiPathComputationPathComputationContext getPathComputationContext() {
    return pathComputationContext;
  }

  public void setPathComputationContext(TapiPathComputationPathComputationContext pathComputationContext) {
    this.pathComputationContext = pathComputationContext;
  }

  public TapiCommonContext topologyContext(TapiTopologyTopologyContext topologyContext) {
    this.topologyContext = topologyContext;
    return this;
  }

  /**
   * Augments the base TAPI Context with TopologyService information
   * @return topologyContext
  **/
  @ApiModelProperty(value = "Augments the base TAPI Context with TopologyService information")

  @Valid

  public TapiTopologyTopologyContext getTopologyContext() {
    return topologyContext;
  }

  public void setTopologyContext(TapiTopologyTopologyContext topologyContext) {
    this.topologyContext = topologyContext;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonContext tapiCommonContext = (TapiCommonContext) o;
    return Objects.equals(this.notificationContext, tapiCommonContext.notificationContext) &&
        Objects.equals(this.pathComputationContext, tapiCommonContext.pathComputationContext) &&
        Objects.equals(this.topologyContext, tapiCommonContext.topologyContext) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notificationContext, pathComputationContext, topologyContext, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonContext {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    notificationContext: ").append(toIndentedString(notificationContext)).append("\n");
    sb.append("    pathComputationContext: ").append(toIndentedString(pathComputationContext)).append("\n");
    sb.append("    topologyContext: ").append(toIndentedString(topologyContext)).append("\n");
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

