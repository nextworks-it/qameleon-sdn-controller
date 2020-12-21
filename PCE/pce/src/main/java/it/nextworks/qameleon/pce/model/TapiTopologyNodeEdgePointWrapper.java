package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyNodeEdgePointWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeEdgePointWrapper   {
  @JsonProperty("owned-node-edge-point")
  private TapiTopologyNodeEdgePoint ownedNodeEdgePoint = null;

  public TapiTopologyNodeEdgePointWrapper ownedNodeEdgePoint(TapiTopologyNodeEdgePoint ownedNodeEdgePoint) {
    this.ownedNodeEdgePoint = ownedNodeEdgePoint;
    return this;
  }

  /**
   * Get ownedNodeEdgePoint
   * @return ownedNodeEdgePoint
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyNodeEdgePoint getOwnedNodeEdgePoint() {
    return ownedNodeEdgePoint;
  }

  public void setOwnedNodeEdgePoint(TapiTopologyNodeEdgePoint ownedNodeEdgePoint) {
    this.ownedNodeEdgePoint = ownedNodeEdgePoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeEdgePointWrapper tapiTopologyNodeEdgePointWrapper = (TapiTopologyNodeEdgePointWrapper) o;
    return Objects.equals(this.ownedNodeEdgePoint, tapiTopologyNodeEdgePointWrapper.ownedNodeEdgePoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ownedNodeEdgePoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeEdgePointWrapper {\n");
    
    sb.append("    ownedNodeEdgePoint: ").append(toIndentedString(ownedNodeEdgePoint)).append("\n");
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

