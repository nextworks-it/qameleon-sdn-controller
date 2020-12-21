package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiNotificationNameAndValueChange
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationNameAndValueChange   {
  @JsonProperty("value-name")
  private String valueName = null;

  @JsonProperty("old-value")
  private String oldValue = null;

  @JsonProperty("new-value")
  private String newValue = null;

  public TapiNotificationNameAndValueChange valueName(String valueName) {
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

  public TapiNotificationNameAndValueChange oldValue(String oldValue) {
    this.oldValue = oldValue;
    return this;
  }

  /**
   * The value
   * @return oldValue
  **/
  @ApiModelProperty(value = "The value")


  public String getOldValue() {
    return oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }

  public TapiNotificationNameAndValueChange newValue(String newValue) {
    this.newValue = newValue;
    return this;
  }

  /**
   * The value
   * @return newValue
  **/
  @ApiModelProperty(value = "The value")


  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationNameAndValueChange tapiNotificationNameAndValueChange = (TapiNotificationNameAndValueChange) o;
    return Objects.equals(this.valueName, tapiNotificationNameAndValueChange.valueName) &&
        Objects.equals(this.oldValue, tapiNotificationNameAndValueChange.oldValue) &&
        Objects.equals(this.newValue, tapiNotificationNameAndValueChange.newValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(valueName, oldValue, newValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationNameAndValueChange {\n");
    
    sb.append("    valueName: ").append(toIndentedString(valueName)).append("\n");
    sb.append("    oldValue: ").append(toIndentedString(oldValue)).append("\n");
    sb.append("    newValue: ").append(toIndentedString(newValue)).append("\n");
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

