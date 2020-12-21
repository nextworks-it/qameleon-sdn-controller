package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathObjectiveFunctionWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathObjectiveFunctionWrapper   {
  @JsonProperty("objective-function")
  private TapiPathComputationPathObjectiveFunction objectiveFunction = null;

  public TapiPathComputationPathObjectiveFunctionWrapper objectiveFunction(TapiPathComputationPathObjectiveFunction objectiveFunction) {
    this.objectiveFunction = objectiveFunction;
    return this;
  }

  /**
   * Get objectiveFunction
   * @return objectiveFunction
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiPathComputationPathObjectiveFunction getObjectiveFunction() {
    return objectiveFunction;
  }

  public void setObjectiveFunction(TapiPathComputationPathObjectiveFunction objectiveFunction) {
    this.objectiveFunction = objectiveFunction;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathObjectiveFunctionWrapper tapiPathComputationPathObjectiveFunctionWrapper = (TapiPathComputationPathObjectiveFunctionWrapper) o;
    return Objects.equals(this.objectiveFunction, tapiPathComputationPathObjectiveFunctionWrapper.objectiveFunction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectiveFunction);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathObjectiveFunctionWrapper {\n");
    
    sb.append("    objectiveFunction: ").append(toIndentedString(objectiveFunction)).append("\n");
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

