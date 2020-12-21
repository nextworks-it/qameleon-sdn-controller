package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyValidationMechanismWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyValidationMechanismWrapper   {
  @JsonProperty("validation-mechanism")
  private TapiTopologyValidationMechanism validationMechanism = null;

  public TapiTopologyValidationMechanismWrapper validationMechanism(TapiTopologyValidationMechanism validationMechanism) {
    this.validationMechanism = validationMechanism;
    return this;
  }

  /**
   * Get validationMechanism
   * @return validationMechanism
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyValidationMechanism getValidationMechanism() {
    return validationMechanism;
  }

  public void setValidationMechanism(TapiTopologyValidationMechanism validationMechanism) {
    this.validationMechanism = validationMechanism;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyValidationMechanismWrapper tapiTopologyValidationMechanismWrapper = (TapiTopologyValidationMechanismWrapper) o;
    return Objects.equals(this.validationMechanism, tapiTopologyValidationMechanismWrapper.validationMechanism);
  }

  @Override
  public int hashCode() {
    return Objects.hash(validationMechanism);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyValidationMechanismWrapper {\n");
    
    sb.append("    validationMechanism: ").append(toIndentedString(validationMechanism)).append("\n");
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

