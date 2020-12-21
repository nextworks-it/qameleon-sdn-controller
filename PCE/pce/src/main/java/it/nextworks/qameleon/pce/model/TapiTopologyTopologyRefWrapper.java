package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyTopologyRefWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyTopologyRefWrapper   {
  @JsonProperty("topology")
  private TapiTopologyTopologyRef topology = null;

  public TapiTopologyTopologyRefWrapper topology(TapiTopologyTopologyRef topology) {
    this.topology = topology;
    return this;
  }

  /**
   * Get topology
   * @return topology
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyTopologyRef getTopology() {
    return topology;
  }

  public void setTopology(TapiTopologyTopologyRef topology) {
    this.topology = topology;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyTopologyRefWrapper tapiTopologyTopologyRefWrapper = (TapiTopologyTopologyRefWrapper) o;
    return Objects.equals(this.topology, tapiTopologyTopologyRefWrapper.topology);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topology);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyTopologyRefWrapper {\n");
    
    sb.append("    topology: ").append(toIndentedString(topology)).append("\n");
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

