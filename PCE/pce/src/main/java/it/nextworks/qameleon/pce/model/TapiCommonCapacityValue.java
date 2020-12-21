package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonCapacityValue
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonCapacityValue   {
  @JsonProperty("value")
  private Integer value = null;

  @JsonProperty("unit")
  private TapiCommonCapacityUnit unit = null;

  public TapiCommonCapacityValue value(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * none
   * @return value
  **/
  @ApiModelProperty(value = "none")


  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public TapiCommonCapacityValue unit(TapiCommonCapacityUnit unit) {
    this.unit = unit;
    return this;
  }

  /**
   * none
   * @return unit
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonCapacityUnit getUnit() {
    return unit;
  }

  public void setUnit(TapiCommonCapacityUnit unit) {
    this.unit = unit;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonCapacityValue tapiCommonCapacityValue = (TapiCommonCapacityValue) o;
    return Objects.equals(this.value, tapiCommonCapacityValue.value) &&
        Objects.equals(this.unit, tapiCommonCapacityValue.unit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, unit);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonCapacityValue {\n");
    
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
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

