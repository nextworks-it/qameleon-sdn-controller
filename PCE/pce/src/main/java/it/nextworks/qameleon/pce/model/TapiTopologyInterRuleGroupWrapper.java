package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyInterRuleGroupWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyInterRuleGroupWrapper   {
  @JsonProperty("inter-rule-group")
  private TapiTopologyInterRuleGroup interRuleGroup = null;

  public TapiTopologyInterRuleGroupWrapper interRuleGroup(TapiTopologyInterRuleGroup interRuleGroup) {
    this.interRuleGroup = interRuleGroup;
    return this;
  }

  /**
   * Get interRuleGroup
   * @return interRuleGroup
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyInterRuleGroup getInterRuleGroup() {
    return interRuleGroup;
  }

  public void setInterRuleGroup(TapiTopologyInterRuleGroup interRuleGroup) {
    this.interRuleGroup = interRuleGroup;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyInterRuleGroupWrapper tapiTopologyInterRuleGroupWrapper = (TapiTopologyInterRuleGroupWrapper) o;
    return Objects.equals(this.interRuleGroup, tapiTopologyInterRuleGroupWrapper.interRuleGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(interRuleGroup);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyInterRuleGroupWrapper {\n");
    
    sb.append("    interRuleGroup: ").append(toIndentedString(interRuleGroup)).append("\n");
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

