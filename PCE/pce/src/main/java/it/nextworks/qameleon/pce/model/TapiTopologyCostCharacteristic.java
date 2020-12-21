package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyCostCharacteristic
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyCostCharacteristic   {
  @JsonProperty("cost-value")
  private String costValue = null;

  @JsonProperty("cost-algorithm")
  private String costAlgorithm = null;

  @JsonProperty("cost-name")
  private String costName = null;

  public TapiTopologyCostCharacteristic costValue(String costValue) {
    this.costValue = costValue;
    return this;
  }

  /**
   * The specific cost.
   * @return costValue
  **/
  @ApiModelProperty(value = "The specific cost.")


  public String getCostValue() {
    return costValue;
  }

  public void setCostValue(String costValue) {
    this.costValue = costValue;
  }

  public TapiTopologyCostCharacteristic costAlgorithm(String costAlgorithm) {
    this.costAlgorithm = costAlgorithm;
    return this;
  }

  /**
   * The cost may vary based upon some properties of the TopologicalEntity. The rules for the variation are conveyed by the costAlgorithm.
   * @return costAlgorithm
  **/
  @ApiModelProperty(value = "The cost may vary based upon some properties of the TopologicalEntity. The rules for the variation are conveyed by the costAlgorithm.")


  public String getCostAlgorithm() {
    return costAlgorithm;
  }

  public void setCostAlgorithm(String costAlgorithm) {
    this.costAlgorithm = costAlgorithm;
  }

  public TapiTopologyCostCharacteristic costName(String costName) {
    this.costName = costName;
    return this;
  }

  /**
   * The cost characteristic will related to some aspect of the TopologicalEntity (e.g. $ cost, routing weight). This aspect will be conveyed by the costName.
   * @return costName
  **/
  @ApiModelProperty(value = "The cost characteristic will related to some aspect of the TopologicalEntity (e.g. $ cost, routing weight). This aspect will be conveyed by the costName.")


  public String getCostName() {
    return costName;
  }

  public void setCostName(String costName) {
    this.costName = costName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyCostCharacteristic tapiTopologyCostCharacteristic = (TapiTopologyCostCharacteristic) o;
    return Objects.equals(this.costValue, tapiTopologyCostCharacteristic.costValue) &&
        Objects.equals(this.costAlgorithm, tapiTopologyCostCharacteristic.costAlgorithm) &&
        Objects.equals(this.costName, tapiTopologyCostCharacteristic.costName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(costValue, costAlgorithm, costName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyCostCharacteristic {\n");
    
    sb.append("    costValue: ").append(toIndentedString(costValue)).append("\n");
    sb.append("    costAlgorithm: ").append(toIndentedString(costAlgorithm)).append("\n");
    sb.append("    costName: ").append(toIndentedString(costName)).append("\n");
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

