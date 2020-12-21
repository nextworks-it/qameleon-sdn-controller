package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiCommonNameAndValue
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonNameAndValue   {
  @JsonProperty("value-name")
  private String valueName = null;

  @JsonProperty("value")
  private String value = null;

  public TapiCommonNameAndValue valueName(String valueName) {
    this.valueName = valueName;
    return this;
  }

  /**
   * The name of the value. The value need not have a name.
   * @return valueName
  **/
  @ApiModelProperty(value = "The name of the value. The value need not have a name.")


  public String getValueName() {
    return valueName;
  }

  public void setValueName(String valueName) {
    this.valueName = valueName;
  }

  public TapiCommonNameAndValue value(String value) {
    this.value = value;
    return this;
  }

  /**
   * The value
   * @return value
  **/
  @ApiModelProperty(value = "The value")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonNameAndValue tapiCommonNameAndValue = (TapiCommonNameAndValue) o;
    return Objects.equals(this.valueName, tapiCommonNameAndValue.valueName) &&
        Objects.equals(this.value, tapiCommonNameAndValue.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valueName, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonNameAndValue {\n");
    
    sb.append("    valueName: ").append(toIndentedString(valueName)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

