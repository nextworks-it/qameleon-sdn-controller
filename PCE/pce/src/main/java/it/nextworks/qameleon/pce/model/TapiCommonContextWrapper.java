package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonContextWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonContextWrapper   {
  @JsonProperty("tapi-common:context")
  private TapiCommonContext tapiCommoncontext = null;

  public TapiCommonContextWrapper tapiCommoncontext(TapiCommonContext tapiCommoncontext) {
    this.tapiCommoncontext = tapiCommoncontext;
    return this;
  }

  /**
   * Get tapiCommoncontext
   * @return tapiCommoncontext
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiCommonContext getTapiCommoncontext() {
    return tapiCommoncontext;
  }

  public void setTapiCommoncontext(TapiCommonContext tapiCommoncontext) {
    this.tapiCommoncontext = tapiCommoncontext;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonContextWrapper tapiCommonContextWrapper = (TapiCommonContextWrapper) o;
    return Objects.equals(this.tapiCommoncontext, tapiCommonContextWrapper.tapiCommoncontext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tapiCommoncontext);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonContextWrapper {\n");
    
    sb.append("    tapiCommoncontext: ").append(toIndentedString(tapiCommoncontext)).append("\n");
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

