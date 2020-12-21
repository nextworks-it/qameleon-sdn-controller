package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyNepLayerProtocolCapability
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNepLayerProtocolCapability   {
  @JsonProperty("layer-protocol-qualifier")
  private String layerProtocolQualifier = null;

  @JsonProperty("number-of-cep-instances")
  private Integer numberOfCepInstances = null;

  public TapiTopologyNepLayerProtocolCapability layerProtocolQualifier(String layerProtocolQualifier) {
    this.layerProtocolQualifier = layerProtocolQualifier;
    return this;
  }

  /**
   * none
   * @return layerProtocolQualifier
  **/
  @ApiModelProperty(value = "none")


  public String getLayerProtocolQualifier() {
    return layerProtocolQualifier;
  }

  public void setLayerProtocolQualifier(String layerProtocolQualifier) {
    this.layerProtocolQualifier = layerProtocolQualifier;
  }

  public TapiTopologyNepLayerProtocolCapability numberOfCepInstances(Integer numberOfCepInstances) {
    this.numberOfCepInstances = numberOfCepInstances;
    return this;
  }

  /**
   * none
   * @return numberOfCepInstances
  **/
  @ApiModelProperty(value = "none")


  public Integer getNumberOfCepInstances() {
    return numberOfCepInstances;
  }

  public void setNumberOfCepInstances(Integer numberOfCepInstances) {
    this.numberOfCepInstances = numberOfCepInstances;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNepLayerProtocolCapability tapiTopologyNepLayerProtocolCapability = (TapiTopologyNepLayerProtocolCapability) o;
    return Objects.equals(this.layerProtocolQualifier, tapiTopologyNepLayerProtocolCapability.layerProtocolQualifier) &&
        Objects.equals(this.numberOfCepInstances, tapiTopologyNepLayerProtocolCapability.numberOfCepInstances);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layerProtocolQualifier, numberOfCepInstances);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNepLayerProtocolCapability {\n");
    
    sb.append("    layerProtocolQualifier: ").append(toIndentedString(layerProtocolQualifier)).append("\n");
    sb.append("    numberOfCepInstances: ").append(toIndentedString(numberOfCepInstances)).append("\n");
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

