package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyRiskCharacteristicWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyRiskCharacteristicWrapper   {
  @JsonProperty("risk-diversity-characteristic")
  private TapiTopologyRiskCharacteristic riskDiversityCharacteristic = null;

  public TapiTopologyRiskCharacteristicWrapper riskDiversityCharacteristic(TapiTopologyRiskCharacteristic riskDiversityCharacteristic) {
    this.riskDiversityCharacteristic = riskDiversityCharacteristic;
    return this;
  }

  /**
   * Get riskDiversityCharacteristic
   * @return riskDiversityCharacteristic
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyRiskCharacteristic getRiskDiversityCharacteristic() {
    return riskDiversityCharacteristic;
  }

  public void setRiskDiversityCharacteristic(TapiTopologyRiskCharacteristic riskDiversityCharacteristic) {
    this.riskDiversityCharacteristic = riskDiversityCharacteristic;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyRiskCharacteristicWrapper tapiTopologyRiskCharacteristicWrapper = (TapiTopologyRiskCharacteristicWrapper) o;
    return Objects.equals(this.riskDiversityCharacteristic, tapiTopologyRiskCharacteristicWrapper.riskDiversityCharacteristic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(riskDiversityCharacteristic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyRiskCharacteristicWrapper {\n");
    
    sb.append("    riskDiversityCharacteristic: ").append(toIndentedString(riskDiversityCharacteristic)).append("\n");
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

