package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologySignalPropertyRule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologySignalPropertyRule   {
  @JsonProperty("number-of-signal-values")
  private Integer numberOfSignalValues = null;

  @JsonProperty("applicable-signal-value")
  @Valid
  private List<String> applicableSignalValue = null;

  @JsonProperty("signal-property-value-rule")
  private String signalPropertyValueRule = null;

  @JsonProperty("signal-property-name")
  private String signalPropertyName = null;

  public TapiTopologySignalPropertyRule numberOfSignalValues(Integer numberOfSignalValues) {
    this.numberOfSignalValues = numberOfSignalValues;
    return this;
  }

  /**
   * The number of instances of this specific property that can be supported by the group.
   * @return numberOfSignalValues
  **/
  @ApiModelProperty(value = "The number of instances of this specific property that can be supported by the group.")


  public Integer getNumberOfSignalValues() {
    return numberOfSignalValues;
  }

  public void setNumberOfSignalValues(Integer numberOfSignalValues) {
    this.numberOfSignalValues = numberOfSignalValues;
  }

  public TapiTopologySignalPropertyRule applicableSignalValue(List<String> applicableSignalValue) {
    this.applicableSignalValue = applicableSignalValue;
    return this;
  }

  public TapiTopologySignalPropertyRule addApplicableSignalValueItem(String applicableSignalValueItem) {
    if (this.applicableSignalValue == null) {
      this.applicableSignalValue = new ArrayList<String>();
    }
    this.applicableSignalValue.add(applicableSignalValueItem);
    return this;
  }

  /**
   * Specific values of the signal property to which the rule applies.
   * @return applicableSignalValue
  **/
  @ApiModelProperty(value = "Specific values of the signal property to which the rule applies.")


  public List<String> getApplicableSignalValue() {
    return applicableSignalValue;
  }

  public void setApplicableSignalValue(List<String> applicableSignalValue) {
    this.applicableSignalValue = applicableSignalValue;
  }

  public TapiTopologySignalPropertyRule signalPropertyValueRule(String signalPropertyValueRule) {
    this.signalPropertyValueRule = signalPropertyValueRule;
    return this;
  }

  /**
   * Indicates how the signal properties should be accounted for.
   * @return signalPropertyValueRule
  **/
  @ApiModelProperty(value = "Indicates how the signal properties should be accounted for.")


  public String getSignalPropertyValueRule() {
    return signalPropertyValueRule;
  }

  public void setSignalPropertyValueRule(String signalPropertyValueRule) {
    this.signalPropertyValueRule = signalPropertyValueRule;
  }

  public TapiTopologySignalPropertyRule signalPropertyName(String signalPropertyName) {
    this.signalPropertyName = signalPropertyName;
    return this;
  }

  /**
   * The name of the signal property to which the rule applies.
   * @return signalPropertyName
  **/
  @ApiModelProperty(value = "The name of the signal property to which the rule applies.")


  public String getSignalPropertyName() {
    return signalPropertyName;
  }

  public void setSignalPropertyName(String signalPropertyName) {
    this.signalPropertyName = signalPropertyName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologySignalPropertyRule tapiTopologySignalPropertyRule = (TapiTopologySignalPropertyRule) o;
    return Objects.equals(this.numberOfSignalValues, tapiTopologySignalPropertyRule.numberOfSignalValues) &&
        Objects.equals(this.applicableSignalValue, tapiTopologySignalPropertyRule.applicableSignalValue) &&
        Objects.equals(this.signalPropertyValueRule, tapiTopologySignalPropertyRule.signalPropertyValueRule) &&
        Objects.equals(this.signalPropertyName, tapiTopologySignalPropertyRule.signalPropertyName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(numberOfSignalValues, applicableSignalValue, signalPropertyValueRule, signalPropertyName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologySignalPropertyRule {\n");
    
    sb.append("    numberOfSignalValues: ").append(toIndentedString(numberOfSignalValues)).append("\n");
    sb.append("    applicableSignalValue: ").append(toIndentedString(applicableSignalValue)).append("\n");
    sb.append("    signalPropertyValueRule: ").append(toIndentedString(signalPropertyValueRule)).append("\n");
    sb.append("    signalPropertyName: ").append(toIndentedString(signalPropertyName)).append("\n");
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

