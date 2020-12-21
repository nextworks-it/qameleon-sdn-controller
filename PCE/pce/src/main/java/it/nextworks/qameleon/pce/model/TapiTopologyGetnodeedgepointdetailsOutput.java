package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyGetnodeedgepointdetailsOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyGetnodeedgepointdetailsOutput   {
  @JsonProperty("node-edge-point")
  private TapiTopologyNodeEdgePoint nodeEdgePoint = null;

  public TapiTopologyGetnodeedgepointdetailsOutput nodeEdgePoint(TapiTopologyNodeEdgePoint nodeEdgePoint) {
    this.nodeEdgePoint = nodeEdgePoint;
    return this;
  }

  /**
   * none
   * @return nodeEdgePoint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiTopologyNodeEdgePoint getNodeEdgePoint() {
    return nodeEdgePoint;
  }

  public void setNodeEdgePoint(TapiTopologyNodeEdgePoint nodeEdgePoint) {
    this.nodeEdgePoint = nodeEdgePoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyGetnodeedgepointdetailsOutput tapiTopologyGetnodeedgepointdetailsOutput = (TapiTopologyGetnodeedgepointdetailsOutput) o;
    return Objects.equals(this.nodeEdgePoint, tapiTopologyGetnodeedgepointdetailsOutput.nodeEdgePoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeEdgePoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyGetnodeedgepointdetailsOutput {\n");
    
    sb.append("    nodeEdgePoint: ").append(toIndentedString(nodeEdgePoint)).append("\n");
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

