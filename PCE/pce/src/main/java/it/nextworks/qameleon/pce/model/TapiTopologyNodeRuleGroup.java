package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyNodeRuleGroup
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeRuleGroup extends TapiCommonCapacityPac  {
  @JsonProperty("name")
  @Valid
  private List<TapiCommonNameAndValue> name = null;

  @JsonProperty("uuid")
  private String uuid = null;

  @JsonProperty("risk-characteristic")
  @Valid
  private List<TapiTopologyRiskCharacteristic> riskCharacteristic = null;

  @JsonProperty("cost-characteristic")
  @Valid
  private List<TapiTopologyCostCharacteristic> costCharacteristic = null;

  @JsonProperty("latency-characteristic")
  @Valid
  private List<TapiTopologyLatencyCharacteristic> latencyCharacteristic = null;

  @JsonProperty("inter-rule-group")
  @Valid
  private List<TapiTopologyInterRuleGroup> interRuleGroup = null;

  @JsonProperty("rule")
  @Valid
  private List<TapiTopologyRule> rule = null;

  @JsonProperty("composed-rule-group")
  @Valid
  private List<TapiTopologyNodeRuleGroupRef> composedRuleGroup = null;

  @JsonProperty("node-edge-point")
  @Valid
  private List<TapiTopologyNodeEdgePointRef> nodeEdgePoint = null;

  public TapiTopologyNodeRuleGroup name(List<TapiCommonNameAndValue> name) {
    this.name = name;
    return this;
  }

  public TapiTopologyNodeRuleGroup addNameItem(TapiCommonNameAndValue nameItem) {
    if (this.name == null) {
      this.name = new ArrayList<TapiCommonNameAndValue>();
    }
    this.name.add(nameItem);
    return this;
  }

  /**
   * List of names. This value is unique in some namespace but may change during the life of the entity.                  A name carries no semantics with respect to the purpose of the entity.
   * @return name
  **/
  @ApiModelProperty(value = "List of names. This value is unique in some namespace but may change during the life of the entity.                  A name carries no semantics with respect to the purpose of the entity.")

  @Valid

  public List<TapiCommonNameAndValue> getName() {
    return name;
  }

  public void setName(List<TapiCommonNameAndValue> name) {
    this.name = name;
  }

  public TapiTopologyNodeRuleGroup uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  /**
   * UUID: An identifier that is universally unique within an identifier space, where the identifier space is itself globally unique, and immutable.                  An UUID carries no semantics with respect to the purpose or state of the entity.                  UUID here uses string representation as defined in RFC 4122.  The canonical representation uses lowercase characters.                  Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}                   Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6
   * @return uuid
  **/
  @ApiModelProperty(value = "UUID: An identifier that is universally unique within an identifier space, where the identifier space is itself globally unique, and immutable.                  An UUID carries no semantics with respect to the purpose or state of the entity.                  UUID here uses string representation as defined in RFC 4122.  The canonical representation uses lowercase characters.                  Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}                   Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6")


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public TapiTopologyNodeRuleGroup riskCharacteristic(List<TapiTopologyRiskCharacteristic> riskCharacteristic) {
    this.riskCharacteristic = riskCharacteristic;
    return this;
  }

  public TapiTopologyNodeRuleGroup addRiskCharacteristicItem(TapiTopologyRiskCharacteristic riskCharacteristicItem) {
    if (this.riskCharacteristic == null) {
      this.riskCharacteristic = new ArrayList<TapiTopologyRiskCharacteristic>();
    }
    this.riskCharacteristic.add(riskCharacteristicItem);
    return this;
  }

  /**
   * A list of risk characteristics for consideration in an analysis of shared risk. Each element of the list represents a specific risk consideration.
   * @return riskCharacteristic
  **/
  @ApiModelProperty(value = "A list of risk characteristics for consideration in an analysis of shared risk. Each element of the list represents a specific risk consideration.")

  @Valid

  public List<TapiTopologyRiskCharacteristic> getRiskCharacteristic() {
    return riskCharacteristic;
  }

  public void setRiskCharacteristic(List<TapiTopologyRiskCharacteristic> riskCharacteristic) {
    this.riskCharacteristic = riskCharacteristic;
  }

  public TapiTopologyNodeRuleGroup costCharacteristic(List<TapiTopologyCostCharacteristic> costCharacteristic) {
    this.costCharacteristic = costCharacteristic;
    return this;
  }

  public TapiTopologyNodeRuleGroup addCostCharacteristicItem(TapiTopologyCostCharacteristic costCharacteristicItem) {
    if (this.costCharacteristic == null) {
      this.costCharacteristic = new ArrayList<TapiTopologyCostCharacteristic>();
    }
    this.costCharacteristic.add(costCharacteristicItem);
    return this;
  }

  /**
   * The list of costs where each cost relates to some aspect of the TopologicalEntity.
   * @return costCharacteristic
  **/
  @ApiModelProperty(value = "The list of costs where each cost relates to some aspect of the TopologicalEntity.")

  @Valid

  public List<TapiTopologyCostCharacteristic> getCostCharacteristic() {
    return costCharacteristic;
  }

  public void setCostCharacteristic(List<TapiTopologyCostCharacteristic> costCharacteristic) {
    this.costCharacteristic = costCharacteristic;
  }

  public TapiTopologyNodeRuleGroup latencyCharacteristic(List<TapiTopologyLatencyCharacteristic> latencyCharacteristic) {
    this.latencyCharacteristic = latencyCharacteristic;
    return this;
  }

  public TapiTopologyNodeRuleGroup addLatencyCharacteristicItem(TapiTopologyLatencyCharacteristic latencyCharacteristicItem) {
    if (this.latencyCharacteristic == null) {
      this.latencyCharacteristic = new ArrayList<TapiTopologyLatencyCharacteristic>();
    }
    this.latencyCharacteristic.add(latencyCharacteristicItem);
    return this;
  }

  /**
   * The effect on the latency of a queuing process. This only has significant effect for packet based systems and has a complex characteristic.
   * @return latencyCharacteristic
  **/
  @ApiModelProperty(value = "The effect on the latency of a queuing process. This only has significant effect for packet based systems and has a complex characteristic.")

  @Valid

  public List<TapiTopologyLatencyCharacteristic> getLatencyCharacteristic() {
    return latencyCharacteristic;
  }

  public void setLatencyCharacteristic(List<TapiTopologyLatencyCharacteristic> latencyCharacteristic) {
    this.latencyCharacteristic = latencyCharacteristic;
  }

  public TapiTopologyNodeRuleGroup interRuleGroup(List<TapiTopologyInterRuleGroup> interRuleGroup) {
    this.interRuleGroup = interRuleGroup;
    return this;
  }

  public TapiTopologyNodeRuleGroup addInterRuleGroupItem(TapiTopologyInterRuleGroup interRuleGroupItem) {
    if (this.interRuleGroup == null) {
      this.interRuleGroup = new ArrayList<TapiTopologyInterRuleGroup>();
    }
    this.interRuleGroup.add(interRuleGroupItem);
    return this;
  }

  /**
   * Nested NodeRuleGroups may have InterRuleGroups. The Superior NodeRuleGroup contains the nested NodeRuleGroups and their associated InterRuleGroups.                  This is equivalent to the Node-Topology hierarchy.
   * @return interRuleGroup
  **/
  @ApiModelProperty(value = "Nested NodeRuleGroups may have InterRuleGroups. The Superior NodeRuleGroup contains the nested NodeRuleGroups and their associated InterRuleGroups.                  This is equivalent to the Node-Topology hierarchy.")

  @Valid

  public List<TapiTopologyInterRuleGroup> getInterRuleGroup() {
    return interRuleGroup;
  }

  public void setInterRuleGroup(List<TapiTopologyInterRuleGroup> interRuleGroup) {
    this.interRuleGroup = interRuleGroup;
  }

  public TapiTopologyNodeRuleGroup rule(List<TapiTopologyRule> rule) {
    this.rule = rule;
    return this;
  }

  public TapiTopologyNodeRuleGroup addRuleItem(TapiTopologyRule ruleItem) {
    if (this.rule == null) {
      this.rule = new ArrayList<TapiTopologyRule>();
    }
    this.rule.add(ruleItem);
    return this;
  }

  /**
   * The list of rules of the NodeRuleGroup.
   * @return rule
  **/
  @ApiModelProperty(value = "The list of rules of the NodeRuleGroup.")

  @Valid

  public List<TapiTopologyRule> getRule() {
    return rule;
  }

  public void setRule(List<TapiTopologyRule> rule) {
    this.rule = rule;
  }

  public TapiTopologyNodeRuleGroup composedRuleGroup(List<TapiTopologyNodeRuleGroupRef> composedRuleGroup) {
    this.composedRuleGroup = composedRuleGroup;
    return this;
  }

  public TapiTopologyNodeRuleGroup addComposedRuleGroupItem(TapiTopologyNodeRuleGroupRef composedRuleGroupItem) {
    if (this.composedRuleGroup == null) {
      this.composedRuleGroup = new ArrayList<TapiTopologyNodeRuleGroupRef>();
    }
    this.composedRuleGroup.add(composedRuleGroupItem);
    return this;
  }

  /**
   * NodeRuleGroups may be nested such that finer grained rules may be applied.                  A nested rule group should have a subset of the NEPs of the superior rule group.
   * @return composedRuleGroup
  **/
  @ApiModelProperty(value = "NodeRuleGroups may be nested such that finer grained rules may be applied.                  A nested rule group should have a subset of the NEPs of the superior rule group.")

  @Valid

  public List<TapiTopologyNodeRuleGroupRef> getComposedRuleGroup() {
    return composedRuleGroup;
  }

  public void setComposedRuleGroup(List<TapiTopologyNodeRuleGroupRef> composedRuleGroup) {
    this.composedRuleGroup = composedRuleGroup;
  }

  public TapiTopologyNodeRuleGroup nodeEdgePoint(List<TapiTopologyNodeEdgePointRef> nodeEdgePoint) {
    this.nodeEdgePoint = nodeEdgePoint;
    return this;
  }

  public TapiTopologyNodeRuleGroup addNodeEdgePointItem(TapiTopologyNodeEdgePointRef nodeEdgePointItem) {
    if (this.nodeEdgePoint == null) {
      this.nodeEdgePoint = new ArrayList<TapiTopologyNodeEdgePointRef>();
    }
    this.nodeEdgePoint.add(nodeEdgePointItem);
    return this;
  }

  /**
   * NEPs and their client CEPs that the rules apply to.
   * @return nodeEdgePoint
  **/
  @ApiModelProperty(value = "NEPs and their client CEPs that the rules apply to.")

  @Valid

  public List<TapiTopologyNodeEdgePointRef> getNodeEdgePoint() {
    return nodeEdgePoint;
  }

  public void setNodeEdgePoint(List<TapiTopologyNodeEdgePointRef> nodeEdgePoint) {
    this.nodeEdgePoint = nodeEdgePoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeRuleGroup tapiTopologyNodeRuleGroup = (TapiTopologyNodeRuleGroup) o;
    return Objects.equals(this.name, tapiTopologyNodeRuleGroup.name) &&
        Objects.equals(this.uuid, tapiTopologyNodeRuleGroup.uuid) &&
        Objects.equals(this.riskCharacteristic, tapiTopologyNodeRuleGroup.riskCharacteristic) &&
        Objects.equals(this.costCharacteristic, tapiTopologyNodeRuleGroup.costCharacteristic) &&
        Objects.equals(this.latencyCharacteristic, tapiTopologyNodeRuleGroup.latencyCharacteristic) &&
        Objects.equals(this.interRuleGroup, tapiTopologyNodeRuleGroup.interRuleGroup) &&
        Objects.equals(this.rule, tapiTopologyNodeRuleGroup.rule) &&
        Objects.equals(this.composedRuleGroup, tapiTopologyNodeRuleGroup.composedRuleGroup) &&
        Objects.equals(this.nodeEdgePoint, tapiTopologyNodeRuleGroup.nodeEdgePoint) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, uuid, riskCharacteristic, costCharacteristic, latencyCharacteristic, interRuleGroup, rule, composedRuleGroup, nodeEdgePoint, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeRuleGroup {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    riskCharacteristic: ").append(toIndentedString(riskCharacteristic)).append("\n");
    sb.append("    costCharacteristic: ").append(toIndentedString(costCharacteristic)).append("\n");
    sb.append("    latencyCharacteristic: ").append(toIndentedString(latencyCharacteristic)).append("\n");
    sb.append("    interRuleGroup: ").append(toIndentedString(interRuleGroup)).append("\n");
    sb.append("    rule: ").append(toIndentedString(rule)).append("\n");
    sb.append("    composedRuleGroup: ").append(toIndentedString(composedRuleGroup)).append("\n");
    sb.append("    nodeEdgePoint: ").append(toIndentedString(nodeEdgePoint)).append("\n");
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

