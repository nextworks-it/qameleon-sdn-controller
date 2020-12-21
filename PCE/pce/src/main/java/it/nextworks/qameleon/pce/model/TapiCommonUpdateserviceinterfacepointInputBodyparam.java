package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonUpdateserviceinterfacepointInputBodyparam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonUpdateserviceinterfacepointInputBodyparam   {
  @JsonProperty("input")
  private TapiCommonUpdateserviceinterfacepointInput input = null;

  public TapiCommonUpdateserviceinterfacepointInputBodyparam input(TapiCommonUpdateserviceinterfacepointInput input) {
    this.input = input;
    return this;
  }

  /**
   * Get input
   * @return input
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiCommonUpdateserviceinterfacepointInput getInput() {
    return input;
  }

  public void setInput(TapiCommonUpdateserviceinterfacepointInput input) {
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
    TapiCommonUpdateserviceinterfacepointInputBodyparam tapiCommonUpdateserviceinterfacepointInputBodyparam = (TapiCommonUpdateserviceinterfacepointInputBodyparam) o;
    return Objects.equals(this.input, tapiCommonUpdateserviceinterfacepointInputBodyparam.input);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonUpdateserviceinterfacepointInputBodyparam {\n");
    
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

