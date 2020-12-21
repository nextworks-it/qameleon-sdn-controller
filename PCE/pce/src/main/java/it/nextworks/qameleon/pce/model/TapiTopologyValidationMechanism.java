package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyValidationMechanism
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyValidationMechanism   {
  @JsonProperty("layer-protocol-adjacency-validated")
  private String layerProtocolAdjacencyValidated = null;

  @JsonProperty("validation-mechanism")
  private String validationMechanism = null;

  @JsonProperty("validation-robustness")
  private String validationRobustness = null;

  public TapiTopologyValidationMechanism layerProtocolAdjacencyValidated(String layerProtocolAdjacencyValidated) {
    this.layerProtocolAdjacencyValidated = layerProtocolAdjacencyValidated;
    return this;
  }

  /**
   * State of validatiion
   * @return layerProtocolAdjacencyValidated
  **/
  @ApiModelProperty(value = "State of validatiion")


  public String getLayerProtocolAdjacencyValidated() {
    return layerProtocolAdjacencyValidated;
  }

  public void setLayerProtocolAdjacencyValidated(String layerProtocolAdjacencyValidated) {
    this.layerProtocolAdjacencyValidated = layerProtocolAdjacencyValidated;
  }

  public TapiTopologyValidationMechanism validationMechanism(String validationMechanism) {
    this.validationMechanism = validationMechanism;
    return this;
  }

  /**
   * Name of mechanism used to validate adjacency
   * @return validationMechanism
  **/
  @ApiModelProperty(value = "Name of mechanism used to validate adjacency")


  public String getValidationMechanism() {
    return validationMechanism;
  }

  public void setValidationMechanism(String validationMechanism) {
    this.validationMechanism = validationMechanism;
  }

  public TapiTopologyValidationMechanism validationRobustness(String validationRobustness) {
    this.validationRobustness = validationRobustness;
    return this;
  }

  /**
   * Quality of validation (i.e. how likely is the stated validation to be invalid)
   * @return validationRobustness
  **/
  @ApiModelProperty(value = "Quality of validation (i.e. how likely is the stated validation to be invalid)")


  public String getValidationRobustness() {
    return validationRobustness;
  }

  public void setValidationRobustness(String validationRobustness) {
    this.validationRobustness = validationRobustness;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyValidationMechanism tapiTopologyValidationMechanism = (TapiTopologyValidationMechanism) o;
    return Objects.equals(this.layerProtocolAdjacencyValidated, tapiTopologyValidationMechanism.layerProtocolAdjacencyValidated) &&
        Objects.equals(this.validationMechanism, tapiTopologyValidationMechanism.validationMechanism) &&
        Objects.equals(this.validationRobustness, tapiTopologyValidationMechanism.validationRobustness);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layerProtocolAdjacencyValidated, validationMechanism, validationRobustness);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyValidationMechanism {\n");
    
    sb.append("    layerProtocolAdjacencyValidated: ").append(toIndentedString(layerProtocolAdjacencyValidated)).append("\n");
    sb.append("    validationMechanism: ").append(toIndentedString(validationMechanism)).append("\n");
    sb.append("    validationRobustness: ").append(toIndentedString(validationRobustness)).append("\n");
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

