package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyNodeRuleGroupRef
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeRuleGroupRef extends TapiTopologyNodeRef  {
  @JsonProperty("node-rule-group-uuid")
  private String nodeRuleGroupUuid = null;

  public TapiTopologyNodeRuleGroupRef nodeRuleGroupUuid(String nodeRuleGroupUuid) {
    this.nodeRuleGroupUuid = nodeRuleGroupUuid;
    return this;
  }

  /**
   * none
   * @return nodeRuleGroupUuid
  **/
  @ApiModelProperty(value = "none")


  public String getNodeRuleGroupUuid() {
    return nodeRuleGroupUuid;
  }

  public void setNodeRuleGroupUuid(String nodeRuleGroupUuid) {
    this.nodeRuleGroupUuid = nodeRuleGroupUuid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeRuleGroupRef tapiTopologyNodeRuleGroupRef = (TapiTopologyNodeRuleGroupRef) o;
    return Objects.equals(this.nodeRuleGroupUuid, tapiTopologyNodeRuleGroupRef.nodeRuleGroupUuid) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeRuleGroupUuid, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeRuleGroupRef {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    nodeRuleGroupUuid: ").append(toIndentedString(nodeRuleGroupUuid)).append("\n");
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

