package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyRuleWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyRuleWrapper   {
  @JsonProperty("rule")
  private TapiTopologyRule rule = null;

  public TapiTopologyRuleWrapper rule(TapiTopologyRule rule) {
    this.rule = rule;
    return this;
  }

  /**
   * Get rule
   * @return rule
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyRule getRule() {
    return rule;
  }

  public void setRule(TapiTopologyRule rule) {
    this.rule = rule;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyRuleWrapper tapiTopologyRuleWrapper = (TapiTopologyRuleWrapper) o;
    return Objects.equals(this.rule, tapiTopologyRuleWrapper.rule);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rule);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyRuleWrapper {\n");
    
    sb.append("    rule: ").append(toIndentedString(rule)).append("\n");
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

