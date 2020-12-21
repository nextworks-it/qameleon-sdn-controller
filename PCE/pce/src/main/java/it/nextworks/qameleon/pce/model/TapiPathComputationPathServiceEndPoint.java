package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathServiceEndPoint
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathServiceEndPoint extends TapiCommonLocalClass  {
  @JsonProperty("role")
  private TapiCommonPortRole role = null;

  @JsonProperty("service-interface-point")
  private TapiCommonServiceInterfacePointRef serviceInterfacePoint = null;

  @JsonProperty("layer-protocol-name")
  private TapiCommonLayerProtocolName layerProtocolName = null;

  @JsonProperty("layer-protocol-qualifier")
  private String layerProtocolQualifier = null;

  @JsonProperty("direction")
  private TapiCommonPortDirection direction = null;

  @JsonProperty("capacity")
  private TapiCommonCapacity capacity = null;

  public TapiPathComputationPathServiceEndPoint role(TapiCommonPortRole role) {
    this.role = role;
    return this;
  }

  /**
   * Each EP of the FC has a role (e.g., working, protection, protected, symmetric, hub, spoke, leaf, root)  in the context of the FC with respect to the FC function. 
   * @return role
  **/
  @ApiModelProperty(value = "Each EP of the FC has a role (e.g., working, protection, protected, symmetric, hub, spoke, leaf, root)  in the context of the FC with respect to the FC function. ")

  @Valid

  public TapiCommonPortRole getRole() {
    return role;
  }

  public void setRole(TapiCommonPortRole role) {
    this.role = role;
  }

  public TapiPathComputationPathServiceEndPoint serviceInterfacePoint(TapiCommonServiceInterfacePointRef serviceInterfacePoint) {
    this.serviceInterfacePoint = serviceInterfacePoint;
    return this;
  }

  /**
   * none
   * @return serviceInterfacePoint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonServiceInterfacePointRef getServiceInterfacePoint() {
    return serviceInterfacePoint;
  }

  public void setServiceInterfacePoint(TapiCommonServiceInterfacePointRef serviceInterfacePoint) {
    this.serviceInterfacePoint = serviceInterfacePoint;
  }

  public TapiPathComputationPathServiceEndPoint layerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
    this.layerProtocolName = layerProtocolName;
    return this;
  }

  /**
   * none
   * @return layerProtocolName
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonLayerProtocolName getLayerProtocolName() {
    return layerProtocolName;
  }

  public void setLayerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
    this.layerProtocolName = layerProtocolName;
  }

  public TapiPathComputationPathServiceEndPoint layerProtocolQualifier(String layerProtocolQualifier) {
    this.layerProtocolQualifier = layerProtocolQualifier;
    return this;
  }

  /**
   * none
   * @return layerProtocolQualifier
  **/
  @ApiModelProperty(value = "none")


  public String getLayerProtocolQualifier() {
    return layerProtocolQualifier;
  }

  public void setLayerProtocolQualifier(String layerProtocolQualifier) {
    this.layerProtocolQualifier = layerProtocolQualifier;
  }

  public TapiPathComputationPathServiceEndPoint direction(TapiCommonPortDirection direction) {
    this.direction = direction;
    return this;
  }

  /**
   * The orientation of defined flow at the EndPoint.
   * @return direction
  **/
  @ApiModelProperty(value = "The orientation of defined flow at the EndPoint.")

  @Valid

  public TapiCommonPortDirection getDirection() {
    return direction;
  }

  public void setDirection(TapiCommonPortDirection direction) {
    this.direction = direction;
  }

  public TapiPathComputationPathServiceEndPoint capacity(TapiCommonCapacity capacity) {
    this.capacity = capacity;
    return this;
  }

  /**
   * none
   * @return capacity
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonCapacity getCapacity() {
    return capacity;
  }

  public void setCapacity(TapiCommonCapacity capacity) {
    this.capacity = capacity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathServiceEndPoint tapiPathComputationPathServiceEndPoint = (TapiPathComputationPathServiceEndPoint) o;
    return Objects.equals(this.role, tapiPathComputationPathServiceEndPoint.role) &&
        Objects.equals(this.serviceInterfacePoint, tapiPathComputationPathServiceEndPoint.serviceInterfacePoint) &&
        Objects.equals(this.layerProtocolName, tapiPathComputationPathServiceEndPoint.layerProtocolName) &&
        Objects.equals(this.layerProtocolQualifier, tapiPathComputationPathServiceEndPoint.layerProtocolQualifier) &&
        Objects.equals(this.direction, tapiPathComputationPathServiceEndPoint.direction) &&
        Objects.equals(this.capacity, tapiPathComputationPathServiceEndPoint.capacity) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(role, serviceInterfacePoint, layerProtocolName, layerProtocolQualifier, direction, capacity, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathServiceEndPoint {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    serviceInterfacePoint: ").append(toIndentedString(serviceInterfacePoint)).append("\n");
    sb.append("    layerProtocolName: ").append(toIndentedString(layerProtocolName)).append("\n");
    sb.append("    layerProtocolQualifier: ").append(toIndentedString(layerProtocolQualifier)).append("\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
    sb.append("    capacity: ").append(toIndentedString(capacity)).append("\n");
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

