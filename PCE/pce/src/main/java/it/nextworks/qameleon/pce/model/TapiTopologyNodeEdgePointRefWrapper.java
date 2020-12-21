package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyNodeEdgePointRefWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeEdgePointRefWrapper   {
  @JsonProperty("boundary-node-edge-point")
  private TapiTopologyNodeEdgePointRef boundaryNodeEdgePoint = null;

  public TapiTopologyNodeEdgePointRefWrapper boundaryNodeEdgePoint(TapiTopologyNodeEdgePointRef boundaryNodeEdgePoint) {
    this.boundaryNodeEdgePoint = boundaryNodeEdgePoint;
    return this;
  }

  /**
   * Get boundaryNodeEdgePoint
   * @return boundaryNodeEdgePoint
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyNodeEdgePointRef getBoundaryNodeEdgePoint() {
    return boundaryNodeEdgePoint;
  }

  public void setBoundaryNodeEdgePoint(TapiTopologyNodeEdgePointRef boundaryNodeEdgePoint) {
    this.boundaryNodeEdgePoint = boundaryNodeEdgePoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeEdgePointRefWrapper tapiTopologyNodeEdgePointRefWrapper = (TapiTopologyNodeEdgePointRefWrapper) o;
    return Objects.equals(this.boundaryNodeEdgePoint, tapiTopologyNodeEdgePointRefWrapper.boundaryNodeEdgePoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boundaryNodeEdgePoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeEdgePointRefWrapper {\n");
    
    sb.append("    boundaryNodeEdgePoint: ").append(toIndentedString(boundaryNodeEdgePoint)).append("\n");
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

