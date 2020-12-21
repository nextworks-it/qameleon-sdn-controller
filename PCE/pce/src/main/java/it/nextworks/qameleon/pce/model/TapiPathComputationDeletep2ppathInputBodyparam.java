package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationDeletep2ppathInputBodyparam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationDeletep2ppathInputBodyparam   {
  @JsonProperty("input")
  private TapiPathComputationDeletep2ppathInput input = null;

  public TapiPathComputationDeletep2ppathInputBodyparam input(TapiPathComputationDeletep2ppathInput input) {
    this.input = input;
    return this;
  }

  /**
   * Get input
   * @return input
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiPathComputationDeletep2ppathInput getInput() {
    return input;
  }

  public void setInput(TapiPathComputationDeletep2ppathInput input) {
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
    TapiPathComputationDeletep2ppathInputBodyparam tapiPathComputationDeletep2ppathInputBodyparam = (TapiPathComputationDeletep2ppathInputBodyparam) o;
    return Objects.equals(this.input, tapiPathComputationDeletep2ppathInputBodyparam.input);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationDeletep2ppathInputBodyparam {\n");
    
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

