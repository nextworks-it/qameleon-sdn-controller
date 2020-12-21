package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationContextAugmentation2
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationContextAugmentation2   {
  @JsonProperty("path-computation-context")
  private TapiPathComputationPathComputationContext pathComputationContext = null;

  public TapiPathComputationContextAugmentation2 pathComputationContext(TapiPathComputationPathComputationContext pathComputationContext) {
    this.pathComputationContext = pathComputationContext;
    return this;
  }

  /**
   * Augments the base TAPI Context with PathComputationService information
   * @return pathComputationContext
  **/
  @ApiModelProperty(value = "Augments the base TAPI Context with PathComputationService information")

  @Valid

  public TapiPathComputationPathComputationContext getPathComputationContext() {
    return pathComputationContext;
  }

  public void setPathComputationContext(TapiPathComputationPathComputationContext pathComputationContext) {
    this.pathComputationContext = pathComputationContext;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationContextAugmentation2 tapiPathComputationContextAugmentation2 = (TapiPathComputationContextAugmentation2) o;
    return Objects.equals(this.pathComputationContext, tapiPathComputationContextAugmentation2.pathComputationContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pathComputationContext);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationContextAugmentation2 {\n");
    
    sb.append("    pathComputationContext: ").append(toIndentedString(pathComputationContext)).append("\n");
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

