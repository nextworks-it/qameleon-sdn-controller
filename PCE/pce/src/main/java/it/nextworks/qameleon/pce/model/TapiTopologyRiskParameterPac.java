package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyRiskParameterPac
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyRiskParameterPac   {
  @JsonProperty("risk-characteristic")
  @Valid
  private List<TapiTopologyRiskCharacteristic> riskCharacteristic = null;

  public TapiTopologyRiskParameterPac riskCharacteristic(List<TapiTopologyRiskCharacteristic> riskCharacteristic) {
    this.riskCharacteristic = riskCharacteristic;
    return this;
  }

  public TapiTopologyRiskParameterPac addRiskCharacteristicItem(TapiTopologyRiskCharacteristic riskCharacteristicItem) {
    if (this.riskCharacteristic == null) {
      this.riskCharacteristic = new ArrayList<TapiTopologyRiskCharacteristic>();
    }
    this.riskCharacteristic.add(riskCharacteristicItem);
    return this;
  }

  /**
   * A list of risk characteristics for consideration in an analysis of shared risk. Each element of the list represents a specific risk consideration.
   * @return riskCharacteristic
  **/
  @ApiModelProperty(value = "A list of risk characteristics for consideration in an analysis of shared risk. Each element of the list represents a specific risk consideration.")

  @Valid

  public List<TapiTopologyRiskCharacteristic> getRiskCharacteristic() {
    return riskCharacteristic;
  }

  public void setRiskCharacteristic(List<TapiTopologyRiskCharacteristic> riskCharacteristic) {
    this.riskCharacteristic = riskCharacteristic;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyRiskParameterPac tapiTopologyRiskParameterPac = (TapiTopologyRiskParameterPac) o;
    return Objects.equals(this.riskCharacteristic, tapiTopologyRiskParameterPac.riskCharacteristic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(riskCharacteristic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyRiskParameterPac {\n");
    
    sb.append("    riskCharacteristic: ").append(toIndentedString(riskCharacteristic)).append("\n");
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

