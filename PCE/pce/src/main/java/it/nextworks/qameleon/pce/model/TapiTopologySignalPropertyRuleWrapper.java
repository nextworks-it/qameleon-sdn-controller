package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologySignalPropertyRuleWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologySignalPropertyRuleWrapper   {
  @JsonProperty("signal-property")
  private TapiTopologySignalPropertyRule signalProperty = null;

  public TapiTopologySignalPropertyRuleWrapper signalProperty(TapiTopologySignalPropertyRule signalProperty) {
    this.signalProperty = signalProperty;
    return this;
  }

  /**
   * Get signalProperty
   * @return signalProperty
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologySignalPropertyRule getSignalProperty() {
    return signalProperty;
  }

  public void setSignalProperty(TapiTopologySignalPropertyRule signalProperty) {
    this.signalProperty = signalProperty;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologySignalPropertyRuleWrapper tapiTopologySignalPropertyRuleWrapper = (TapiTopologySignalPropertyRuleWrapper) o;
    return Objects.equals(this.signalProperty, tapiTopologySignalPropertyRuleWrapper.signalProperty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(signalProperty);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologySignalPropertyRuleWrapper {\n");
    
    sb.append("    signalProperty: ").append(toIndentedString(signalProperty)).append("\n");
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

