package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyGettopologydetailsOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyGettopologydetailsOutput   {
  @JsonProperty("topology")
  private TapiTopologyTopology topology = null;

  public TapiTopologyGettopologydetailsOutput topology(TapiTopologyTopology topology) {
    this.topology = topology;
    return this;
  }

  /**
   * none
   * @return topology
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiTopologyTopology getTopology() {
    return topology;
  }

  public void setTopology(TapiTopologyTopology topology) {
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
    TapiTopologyGettopologydetailsOutput tapiTopologyGettopologydetailsOutput = (TapiTopologyGettopologydetailsOutput) o;
    return Objects.equals(this.topology, tapiTopologyGettopologydetailsOutput.topology);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topology);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyGettopologydetailsOutput {\n");
    
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

