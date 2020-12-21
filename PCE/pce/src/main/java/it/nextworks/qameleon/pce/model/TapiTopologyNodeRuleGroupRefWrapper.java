package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyNodeRuleGroupRefWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeRuleGroupRefWrapper   {
  @JsonProperty("composed-rule-group")
  private TapiTopologyNodeRuleGroupRef composedRuleGroup = null;

  public TapiTopologyNodeRuleGroupRefWrapper composedRuleGroup(TapiTopologyNodeRuleGroupRef composedRuleGroup) {
    this.composedRuleGroup = composedRuleGroup;
    return this;
  }

  /**
   * Get composedRuleGroup
   * @return composedRuleGroup
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyNodeRuleGroupRef getComposedRuleGroup() {
    return composedRuleGroup;
  }

  public void setComposedRuleGroup(TapiTopologyNodeRuleGroupRef composedRuleGroup) {
    this.composedRuleGroup = composedRuleGroup;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeRuleGroupRefWrapper tapiTopologyNodeRuleGroupRefWrapper = (TapiTopologyNodeRuleGroupRefWrapper) o;
    return Objects.equals(this.composedRuleGroup, tapiTopologyNodeRuleGroupRefWrapper.composedRuleGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(composedRuleGroup);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeRuleGroupRefWrapper {\n");
    
    sb.append("    composedRuleGroup: ").append(toIndentedString(composedRuleGroup)).append("\n");
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

