package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyInterRuleGroup
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyInterRuleGroup extends TapiCommonCapacityPac  {
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

  @JsonProperty("associated-node-rule-group")
  @Valid
  private List<TapiTopologyNodeRuleGroupRef> associatedNodeRuleGroup = null;

  @JsonProperty("rule")
  @Valid
  private List<TapiTopologyRule> rule = null;

  public TapiTopologyInterRuleGroup name(List<TapiCommonNameAndValue> name) {
    this.name = name;
    return this;
  }

  public TapiTopologyInterRuleGroup addNameItem(TapiCommonNameAndValue nameItem) {
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

  public TapiTopologyInterRuleGroup uuid(String uuid) {
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

  public TapiTopologyInterRuleGroup riskCharacteristic(List<TapiTopologyRiskCharacteristic> riskCharacteristic) {
    this.riskCharacteristic = riskCharacteristic;
    return this;
  }

  public TapiTopologyInterRuleGroup addRiskCharacteristicItem(TapiTopologyRiskCharacteristic riskCharacteristicItem) {
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

  public TapiTopologyInterRuleGroup costCharacteristic(List<TapiTopologyCostCharacteristic> costCharacteristic) {
    this.costCharacteristic = costCharacteristic;
    return this;
  }

  public TapiTopologyInterRuleGroup addCostCharacteristicItem(TapiTopologyCostCharacteristic costCharacteristicItem) {
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

  public TapiTopologyInterRuleGroup latencyCharacteristic(List<TapiTopologyLatencyCharacteristic> latencyCharacteristic) {
    this.latencyCharacteristic = latencyCharacteristic;
    return this;
  }

  public TapiTopologyInterRuleGroup addLatencyCharacteristicItem(TapiTopologyLatencyCharacteristic latencyCharacteristicItem) {
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

  public TapiTopologyInterRuleGroup associatedNodeRuleGroup(List<TapiTopologyNodeRuleGroupRef> associatedNodeRuleGroup) {
    this.associatedNodeRuleGroup = associatedNodeRuleGroup;
    return this;
  }

  public TapiTopologyInterRuleGroup addAssociatedNodeRuleGroupItem(TapiTopologyNodeRuleGroupRef associatedNodeRuleGroupItem) {
    if (this.associatedNodeRuleGroup == null) {
      this.associatedNodeRuleGroup = new ArrayList<TapiTopologyNodeRuleGroupRef>();
    }
    this.associatedNodeRuleGroup.add(associatedNodeRuleGroupItem);
    return this;
  }

  /**
   * The NodeRuleGroups that the InterRuleGroup constrains interconnection between.                  The CEPs of the NEPs of a referenced NodeRuleGroup can interconnect to the CEPs of the NEPs of another referenced NodeRuleGroup constrained by the rules of the InterRuleGroup.
   * @return associatedNodeRuleGroup
  **/
  @ApiModelProperty(value = "The NodeRuleGroups that the InterRuleGroup constrains interconnection between.                  The CEPs of the NEPs of a referenced NodeRuleGroup can interconnect to the CEPs of the NEPs of another referenced NodeRuleGroup constrained by the rules of the InterRuleGroup.")

  @Valid

  public List<TapiTopologyNodeRuleGroupRef> getAssociatedNodeRuleGroup() {
    return associatedNodeRuleGroup;
  }

  public void setAssociatedNodeRuleGroup(List<TapiTopologyNodeRuleGroupRef> associatedNodeRuleGroup) {
    this.associatedNodeRuleGroup = associatedNodeRuleGroup;
  }

  public TapiTopologyInterRuleGroup rule(List<TapiTopologyRule> rule) {
    this.rule = rule;
    return this;
  }

  public TapiTopologyInterRuleGroup addRuleItem(TapiTopologyRule ruleItem) {
    if (this.rule == null) {
      this.rule = new ArrayList<TapiTopologyRule>();
    }
    this.rule.add(ruleItem);
    return this;
  }

  /**
   * The list of rules of the InterRuleGroup.
   * @return rule
  **/
  @ApiModelProperty(value = "The list of rules of the InterRuleGroup.")

  @Valid

  public List<TapiTopologyRule> getRule() {
    return rule;
  }

  public void setRule(List<TapiTopologyRule> rule) {
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
    TapiTopologyInterRuleGroup tapiTopologyInterRuleGroup = (TapiTopologyInterRuleGroup) o;
    return Objects.equals(this.name, tapiTopologyInterRuleGroup.name) &&
        Objects.equals(this.uuid, tapiTopologyInterRuleGroup.uuid) &&
        Objects.equals(this.riskCharacteristic, tapiTopologyInterRuleGroup.riskCharacteristic) &&
        Objects.equals(this.costCharacteristic, tapiTopologyInterRuleGroup.costCharacteristic) &&
        Objects.equals(this.latencyCharacteristic, tapiTopologyInterRuleGroup.latencyCharacteristic) &&
        Objects.equals(this.associatedNodeRuleGroup, tapiTopologyInterRuleGroup.associatedNodeRuleGroup) &&
        Objects.equals(this.rule, tapiTopologyInterRuleGroup.rule) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, uuid, riskCharacteristic, costCharacteristic, latencyCharacteristic, associatedNodeRuleGroup, rule, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyInterRuleGroup {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    riskCharacteristic: ").append(toIndentedString(riskCharacteristic)).append("\n");
    sb.append("    costCharacteristic: ").append(toIndentedString(costCharacteristic)).append("\n");
    sb.append("    latencyCharacteristic: ").append(toIndentedString(latencyCharacteristic)).append("\n");
    sb.append("    associatedNodeRuleGroup: ").append(toIndentedString(associatedNodeRuleGroup)).append("\n");
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

