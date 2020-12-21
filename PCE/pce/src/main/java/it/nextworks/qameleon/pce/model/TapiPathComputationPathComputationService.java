package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathComputationService
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathComputationService extends TapiCommonGlobalClass  {
  @JsonProperty("topology-constraint")
  @Valid
  private List<TapiPathComputationTopologyConstraint> topologyConstraint = null;

  @JsonProperty("path")
  @Valid
  private List<TapiPathComputationPathRef> path = null;

  @JsonProperty("objective-function")
  private TapiPathComputationPathObjectiveFunction objectiveFunction = null;

  @JsonProperty("end-point")
  @Valid
  private List<TapiPathComputationPathServiceEndPoint> endPoint = null;

  @JsonProperty("layer-protocol-name")
  private TapiCommonLayerProtocolName layerProtocolName = null;

  @JsonProperty("routing-constraint")
  private TapiPathComputationRoutingConstraint routingConstraint = null;

  @JsonProperty("optimization-constraint")
  private TapiPathComputationPathOptimizationConstraint optimizationConstraint = null;

  @JsonProperty("direction")
  private TapiCommonForwardingDirection direction = null;

  public TapiPathComputationPathComputationService topologyConstraint(List<TapiPathComputationTopologyConstraint> topologyConstraint) {
    this.topologyConstraint = topologyConstraint;
    return this;
  }

  public TapiPathComputationPathComputationService addTopologyConstraintItem(TapiPathComputationTopologyConstraint topologyConstraintItem) {
    if (this.topologyConstraint == null) {
      this.topologyConstraint = new ArrayList<TapiPathComputationTopologyConstraint>();
    }
    this.topologyConstraint.add(topologyConstraintItem);
    return this;
  }

  /**
   * none
   * @return topologyConstraint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiPathComputationTopologyConstraint> getTopologyConstraint() {
    return topologyConstraint;
  }

  public void setTopologyConstraint(List<TapiPathComputationTopologyConstraint> topologyConstraint) {
    this.topologyConstraint = topologyConstraint;
  }

  public TapiPathComputationPathComputationService path(List<TapiPathComputationPathRef> path) {
    this.path = path;
    return this;
  }

  public TapiPathComputationPathComputationService addPathItem(TapiPathComputationPathRef pathItem) {
    if (this.path == null) {
      this.path = new ArrayList<TapiPathComputationPathRef>();
    }
    this.path.add(pathItem);
    return this;
  }

  /**
   * none
   * @return path
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiPathComputationPathRef> getPath() {
    return path;
  }

  public void setPath(List<TapiPathComputationPathRef> path) {
    this.path = path;
  }

  public TapiPathComputationPathComputationService objectiveFunction(TapiPathComputationPathObjectiveFunction objectiveFunction) {
    this.objectiveFunction = objectiveFunction;
    return this;
  }

  /**
   * none
   * @return objectiveFunction
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationPathObjectiveFunction getObjectiveFunction() {
    return objectiveFunction;
  }

  public void setObjectiveFunction(TapiPathComputationPathObjectiveFunction objectiveFunction) {
    this.objectiveFunction = objectiveFunction;
  }

  public TapiPathComputationPathComputationService endPoint(List<TapiPathComputationPathServiceEndPoint> endPoint) {
    this.endPoint = endPoint;
    return this;
  }

  public TapiPathComputationPathComputationService addEndPointItem(TapiPathComputationPathServiceEndPoint endPointItem) {
    if (this.endPoint == null) {
      this.endPoint = new ArrayList<TapiPathComputationPathServiceEndPoint>();
    }
    this.endPoint.add(endPointItem);
    return this;
  }

  /**
   * none
   * @return endPoint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiPathComputationPathServiceEndPoint> getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(List<TapiPathComputationPathServiceEndPoint> endPoint) {
    this.endPoint = endPoint;
  }

  public TapiPathComputationPathComputationService layerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
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

  public TapiPathComputationPathComputationService routingConstraint(TapiPathComputationRoutingConstraint routingConstraint) {
    this.routingConstraint = routingConstraint;
    return this;
  }

  /**
   * none
   * @return routingConstraint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationRoutingConstraint getRoutingConstraint() {
    return routingConstraint;
  }

  public void setRoutingConstraint(TapiPathComputationRoutingConstraint routingConstraint) {
    this.routingConstraint = routingConstraint;
  }

  public TapiPathComputationPathComputationService optimizationConstraint(TapiPathComputationPathOptimizationConstraint optimizationConstraint) {
    this.optimizationConstraint = optimizationConstraint;
    return this;
  }

  /**
   * none
   * @return optimizationConstraint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationPathOptimizationConstraint getOptimizationConstraint() {
    return optimizationConstraint;
  }

  public void setOptimizationConstraint(TapiPathComputationPathOptimizationConstraint optimizationConstraint) {
    this.optimizationConstraint = optimizationConstraint;
  }

  public TapiPathComputationPathComputationService direction(TapiCommonForwardingDirection direction) {
    this.direction = direction;
    return this;
  }

  /**
   * none
   * @return direction
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonForwardingDirection getDirection() {
    return direction;
  }

  public void setDirection(TapiCommonForwardingDirection direction) {
    this.direction = direction;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathComputationService tapiPathComputationPathComputationService = (TapiPathComputationPathComputationService) o;
    return Objects.equals(this.topologyConstraint, tapiPathComputationPathComputationService.topologyConstraint) &&
        Objects.equals(this.path, tapiPathComputationPathComputationService.path) &&
        Objects.equals(this.objectiveFunction, tapiPathComputationPathComputationService.objectiveFunction) &&
        Objects.equals(this.endPoint, tapiPathComputationPathComputationService.endPoint) &&
        Objects.equals(this.layerProtocolName, tapiPathComputationPathComputationService.layerProtocolName) &&
        Objects.equals(this.routingConstraint, tapiPathComputationPathComputationService.routingConstraint) &&
        Objects.equals(this.optimizationConstraint, tapiPathComputationPathComputationService.optimizationConstraint) &&
        Objects.equals(this.direction, tapiPathComputationPathComputationService.direction) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topologyConstraint, path, objectiveFunction, endPoint, layerProtocolName, routingConstraint, optimizationConstraint, direction, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathComputationService {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    topologyConstraint: ").append(toIndentedString(topologyConstraint)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    objectiveFunction: ").append(toIndentedString(objectiveFunction)).append("\n");
    sb.append("    endPoint: ").append(toIndentedString(endPoint)).append("\n");
    sb.append("    layerProtocolName: ").append(toIndentedString(layerProtocolName)).append("\n");
    sb.append("    routingConstraint: ").append(toIndentedString(routingConstraint)).append("\n");
    sb.append("    optimizationConstraint: ").append(toIndentedString(optimizationConstraint)).append("\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
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

