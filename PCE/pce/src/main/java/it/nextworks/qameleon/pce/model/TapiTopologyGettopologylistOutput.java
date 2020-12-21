package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyGettopologylistOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyGettopologylistOutput   {
  @JsonProperty("topology")
  @Valid
  private List<TapiTopologyTopology> topology = null;

  public TapiTopologyGettopologylistOutput topology(List<TapiTopologyTopology> topology) {
    this.topology = topology;
    return this;
  }

  public TapiTopologyGettopologylistOutput addTopologyItem(TapiTopologyTopology topologyItem) {
    if (this.topology == null) {
      this.topology = new ArrayList<TapiTopologyTopology>();
    }
    this.topology.add(topologyItem);
    return this;
  }

  /**
   * none
   * @return topology
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiTopologyTopology> getTopology() {
    return topology;
  }

  public void setTopology(List<TapiTopologyTopology> topology) {
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
    TapiTopologyGettopologylistOutput tapiTopologyGettopologylistOutput = (TapiTopologyGettopologylistOutput) o;
    return Objects.equals(this.topology, tapiTopologyGettopologylistOutput.topology);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topology);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyGettopologylistOutput {\n");
    
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

