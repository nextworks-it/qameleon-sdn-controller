package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationOptimizep2ppathInputBodyparam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationOptimizep2ppathInputBodyparam   {
  @JsonProperty("input")
  private TapiPathComputationOptimizep2ppathInput input = null;

  public TapiPathComputationOptimizep2ppathInputBodyparam input(TapiPathComputationOptimizep2ppathInput input) {
    this.input = input;
    return this;
  }

  /**
   * Get input
   * @return input
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiPathComputationOptimizep2ppathInput getInput() {
    return input;
  }

  public void setInput(TapiPathComputationOptimizep2ppathInput input) {
    this.input = input;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationOptimizep2ppathInputBodyparam tapiPathComputationOptimizep2ppathInputBodyparam = (TapiPathComputationOptimizep2ppathInputBodyparam) o;
    return Objects.equals(this.input, tapiPathComputationOptimizep2ppathInputBodyparam.input);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationOptimizep2ppathInputBodyparam {\n");
    
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
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

