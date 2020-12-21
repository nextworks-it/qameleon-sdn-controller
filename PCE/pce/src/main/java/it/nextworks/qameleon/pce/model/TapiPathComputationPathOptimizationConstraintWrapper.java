package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathOptimizationConstraintWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathOptimizationConstraintWrapper   {
  @JsonProperty("optimization-constraint")
  private TapiPathComputationPathOptimizationConstraint optimizationConstraint = null;

  public TapiPathComputationPathOptimizationConstraintWrapper optimizationConstraint(TapiPathComputationPathOptimizationConstraint optimizationConstraint) {
    this.optimizationConstraint = optimizationConstraint;
    return this;
  }

  /**
   * Get optimizationConstraint
   * @return optimizationConstraint
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiPathComputationPathOptimizationConstraint getOptimizationConstraint() {
    return optimizationConstraint;
  }

  public void setOptimizationConstraint(TapiPathComputationPathOptimizationConstraint optimizationConstraint) {
    this.optimizationConstraint = optimizationConstraint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathOptimizationConstraintWrapper tapiPathComputationPathOptimizationConstraintWrapper = (TapiPathComputationPathOptimizationConstraintWrapper) o;
    return Objects.equals(this.optimizationConstraint, tapiPathComputationPathOptimizationConstraintWrapper.optimizationConstraint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(optimizationConstraint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathOptimizationConstraintWrapper {\n");
    
    sb.append("    optimizationConstraint: ").append(toIndentedString(optimizationConstraint)).append("\n");
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

