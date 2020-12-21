package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyCostCharacteristicWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyCostCharacteristicWrapper   {
  @JsonProperty("cost-characteristic")
  private TapiTopologyCostCharacteristic costCharacteristic = null;

  public TapiTopologyCostCharacteristicWrapper costCharacteristic(TapiTopologyCostCharacteristic costCharacteristic) {
    this.costCharacteristic = costCharacteristic;
    return this;
  }

  /**
   * Get costCharacteristic
   * @return costCharacteristic
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyCostCharacteristic getCostCharacteristic() {
    return costCharacteristic;
  }

  public void setCostCharacteristic(TapiTopologyCostCharacteristic costCharacteristic) {
    this.costCharacteristic = costCharacteristic;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyCostCharacteristicWrapper tapiTopologyCostCharacteristicWrapper = (TapiTopologyCostCharacteristicWrapper) o;
    return Objects.equals(this.costCharacteristic, tapiTopologyCostCharacteristicWrapper.costCharacteristic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(costCharacteristic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyCostCharacteristicWrapper {\n");
    
    sb.append("    costCharacteristic: ").append(toIndentedString(costCharacteristic)).append("\n");
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

