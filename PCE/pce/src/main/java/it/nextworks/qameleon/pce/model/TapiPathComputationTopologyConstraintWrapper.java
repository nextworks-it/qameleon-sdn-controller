package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationTopologyConstraintWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationTopologyConstraintWrapper   {
  @JsonProperty("topology-constraint")
  private TapiPathComputationTopologyConstraint topologyConstraint = null;

  public TapiPathComputationTopologyConstraintWrapper topologyConstraint(TapiPathComputationTopologyConstraint topologyConstraint) {
    this.topologyConstraint = topologyConstraint;
    return this;
  }

  /**
   * Get topologyConstraint
   * @return topologyConstraint
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiPathComputationTopologyConstraint getTopologyConstraint() {
    return topologyConstraint;
  }

  public void setTopologyConstraint(TapiPathComputationTopologyConstraint topologyConstraint) {
    this.topologyConstraint = topologyConstraint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationTopologyConstraintWrapper tapiPathComputationTopologyConstraintWrapper = (TapiPathComputationTopologyConstraintWrapper) o;
    return Objects.equals(this.topologyConstraint, tapiPathComputationTopologyConstraintWrapper.topologyConstraint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topologyConstraint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationTopologyConstraintWrapper {\n");
    
    sb.append("    topologyConstraint: ").append(toIndentedString(topologyConstraint)).append("\n");
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

