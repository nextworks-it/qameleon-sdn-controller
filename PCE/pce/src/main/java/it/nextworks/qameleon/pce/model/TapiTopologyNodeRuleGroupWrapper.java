package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyNodeRuleGroupWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeRuleGroupWrapper   {
  @JsonProperty("node-rule-group")
  private TapiTopologyNodeRuleGroup nodeRuleGroup = null;

  public TapiTopologyNodeRuleGroupWrapper nodeRuleGroup(TapiTopologyNodeRuleGroup nodeRuleGroup) {
    this.nodeRuleGroup = nodeRuleGroup;
    return this;
  }

  /**
   * Get nodeRuleGroup
   * @return nodeRuleGroup
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyNodeRuleGroup getNodeRuleGroup() {
    return nodeRuleGroup;
  }

  public void setNodeRuleGroup(TapiTopologyNodeRuleGroup nodeRuleGroup) {
    this.nodeRuleGroup = nodeRuleGroup;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeRuleGroupWrapper tapiTopologyNodeRuleGroupWrapper = (TapiTopologyNodeRuleGroupWrapper) o;
    return Objects.equals(this.nodeRuleGroup, tapiTopologyNodeRuleGroupWrapper.nodeRuleGroup);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeRuleGroup);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeRuleGroupWrapper {\n");
    
    sb.append("    nodeRuleGroup: ").append(toIndentedString(nodeRuleGroup)).append("\n");
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

