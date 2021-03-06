package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyContextAugmentation3
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyContextAugmentation3   {
  @JsonProperty("topology-context")
  private TapiTopologyTopologyContext topologyContext = null;

  public TapiTopologyContextAugmentation3 topologyContext(TapiTopologyTopologyContext topologyContext) {
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
    TapiTopologyContextAugmentation3 tapiTopologyContextAugmentation3 = (TapiTopologyContextAugmentation3) o;
    return Objects.equals(this.topologyContext, tapiTopologyContextAugmentation3.topologyContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topologyContext);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyContextAugmentation3 {\n");
    
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

