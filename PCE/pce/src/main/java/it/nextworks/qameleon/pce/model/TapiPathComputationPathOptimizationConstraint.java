package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathOptimizationConstraint
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathOptimizationConstraint extends TapiCommonLocalClass  {
  @JsonProperty("traffic-interruption")
  private TapiCommonDirectiveValue trafficInterruption = null;

  public TapiPathComputationPathOptimizationConstraint trafficInterruption(TapiCommonDirectiveValue trafficInterruption) {
    this.trafficInterruption = trafficInterruption;
    return this;
  }

  /**
   * none
   * @return trafficInterruption
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonDirectiveValue getTrafficInterruption() {
    return trafficInterruption;
  }

  public void setTrafficInterruption(TapiCommonDirectiveValue trafficInterruption) {
    this.trafficInterruption = trafficInterruption;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathOptimizationConstraint tapiPathComputationPathOptimizationConstraint = (TapiPathComputationPathOptimizationConstraint) o;
    return Objects.equals(this.trafficInterruption, tapiPathComputationPathOptimizationConstraint.trafficInterruption) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trafficInterruption, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathOptimizationConstraint {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    trafficInterruption: ").append(toIndentedString(trafficInterruption)).append("\n");
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

