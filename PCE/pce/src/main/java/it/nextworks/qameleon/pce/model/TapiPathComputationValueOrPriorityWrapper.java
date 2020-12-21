package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationValueOrPriorityWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationValueOrPriorityWrapper   {
  @JsonProperty("max-allowed-cost")
  private TapiPathComputationValueOrPriority maxAllowedCost = null;

  public TapiPathComputationValueOrPriorityWrapper maxAllowedCost(TapiPathComputationValueOrPriority maxAllowedCost) {
    this.maxAllowedCost = maxAllowedCost;
    return this;
  }

  /**
   * Get maxAllowedCost
   * @return maxAllowedCost
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiPathComputationValueOrPriority getMaxAllowedCost() {
    return maxAllowedCost;
  }

  public void setMaxAllowedCost(TapiPathComputationValueOrPriority maxAllowedCost) {
    this.maxAllowedCost = maxAllowedCost;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationValueOrPriorityWrapper tapiPathComputationValueOrPriorityWrapper = (TapiPathComputationValueOrPriorityWrapper) o;
    return Objects.equals(this.maxAllowedCost, tapiPathComputationValueOrPriorityWrapper.maxAllowedCost);
  }

  @Override
  public int hashCode() {
    return Objects.hash(maxAllowedCost);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationValueOrPriorityWrapper {\n");
    
    sb.append("    maxAllowedCost: ").append(toIndentedString(maxAllowedCost)).append("\n");
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

