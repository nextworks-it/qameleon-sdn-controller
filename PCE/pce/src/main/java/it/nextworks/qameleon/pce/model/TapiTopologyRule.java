package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyRule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyRule extends TapiCommonLocalClass  {
  @JsonProperty("complex-rule")
  @Valid
  private List<String> complexRule = null;

  @JsonProperty("rule-type")
  private TapiTopologyRuleType ruleType = null;

  @JsonProperty("signal-property")
  private TapiTopologySignalPropertyRule signalProperty = null;

  @JsonProperty("connection-spec-reference")
  @Valid
  private List<TapiTopologyConnectionSpecReference> connectionSpecReference = null;

  @JsonProperty("cep-port-role")
  @Valid
  private List<TapiTopologyPortRoleRule> cepPortRole = null;

  @JsonProperty("cep-direction")
  @Valid
  private List<TapiCommonPortDirection> cepDirection = null;

  @JsonProperty("override-priority")
  private Integer overridePriority = null;

  @JsonProperty("layer-protocol-qualifier")
  @Valid
  private List<String> layerProtocolQualifier = null;

  @JsonProperty("forwarding-rule")
  private TapiTopologyForwardingRule forwardingRule = null;

  public TapiTopologyRule complexRule(List<String> complexRule) {
    this.complexRule = complexRule;
    return this;
  }

  public TapiTopologyRule addComplexRuleItem(String complexRuleItem) {
    if (this.complexRule == null) {
      this.complexRule = new ArrayList<String>();
    }
    this.complexRule.add(complexRuleItem);
    return this;
  }

  /**
   * Allows for more complex rules where the basic rule system is not sufficient.
   * @return complexRule
  **/
  @ApiModelProperty(value = "Allows for more complex rules where the basic rule system is not sufficient.")


  public List<String> getComplexRule() {
    return complexRule;
  }

  public void setComplexRule(List<String> complexRule) {
    this.complexRule = complexRule;
  }

  public TapiTopologyRule ruleType(TapiTopologyRuleType ruleType) {
    this.ruleType = ruleType;
    return this;
  }

  /**
   * The focus of the rule.
   * @return ruleType
  **/
  @ApiModelProperty(value = "The focus of the rule.")

  @Valid

  public TapiTopologyRuleType getRuleType() {
    return ruleType;
  }

  public void setRuleType(TapiTopologyRuleType ruleType) {
    this.ruleType = ruleType;
  }

  public TapiTopologyRule signalProperty(TapiTopologySignalPropertyRule signalProperty) {
    this.signalProperty = signalProperty;
    return this;
  }

  /**
   * The rule only applies to signals with the properties listed.                   If the attribute is not present then the rule applies to all signals.
   * @return signalProperty
  **/
  @ApiModelProperty(value = "The rule only applies to signals with the properties listed.                   If the attribute is not present then the rule applies to all signals.")

  @Valid

  public TapiTopologySignalPropertyRule getSignalProperty() {
    return signalProperty;
  }

  public void setSignalProperty(TapiTopologySignalPropertyRule signalProperty) {
    this.signalProperty = signalProperty;
  }

  public TapiTopologyRule connectionSpecReference(List<TapiTopologyConnectionSpecReference> connectionSpecReference) {
    this.connectionSpecReference = connectionSpecReference;
    return this;
  }

  public TapiTopologyRule addConnectionSpecReferenceItem(TapiTopologyConnectionSpecReference connectionSpecReferenceItem) {
    if (this.connectionSpecReference == null) {
      this.connectionSpecReference = new ArrayList<TapiTopologyConnectionSpecReference>();
    }
    this.connectionSpecReference.add(connectionSpecReferenceItem);
    return this;
  }

  /**
   * Identifies the type of connection that the rule applies to.                   If the attribute is not present then the rule applies to all types of connection supported by the device.
   * @return connectionSpecReference
  **/
  @ApiModelProperty(value = "Identifies the type of connection that the rule applies to.                   If the attribute is not present then the rule applies to all types of connection supported by the device.")

  @Valid

  public List<TapiTopologyConnectionSpecReference> getConnectionSpecReference() {
    return connectionSpecReference;
  }

  public void setConnectionSpecReference(List<TapiTopologyConnectionSpecReference> connectionSpecReference) {
    this.connectionSpecReference = connectionSpecReference;
  }

  public TapiTopologyRule cepPortRole(List<TapiTopologyPortRoleRule> cepPortRole) {
    this.cepPortRole = cepPortRole;
    return this;
  }

  public TapiTopologyRule addCepPortRoleItem(TapiTopologyPortRoleRule cepPortRoleItem) {
    if (this.cepPortRole == null) {
      this.cepPortRole = new ArrayList<TapiTopologyPortRoleRule>();
    }
    this.cepPortRole.add(cepPortRoleItem);
    return this;
  }

  /**
   * Indicates the port role to which the rule applies.                   The port role is interpreted in the context of the connection type which is identified by the connection spec.                   The port role is not meaningful in the absence of a connection spec reference.                  If a node rule group carries a port role, that role applies also to the associated inter rule where the combination of the roles in the node rule groups at the ends of the inter group rule define the connection orientation.                  For example a root-and-leaf connection may be used in a node where a node rule group collects one set of NEPs has the port role 'root' and another node rule group collects another set of NEPs has the port role 'leaf' where these are joined by an inter rule group. This combination specifies an allowed orientation of the root-and-leaf connection.                  No port role statement means all port roles are allowed.
   * @return cepPortRole
  **/
  @ApiModelProperty(value = "Indicates the port role to which the rule applies.                   The port role is interpreted in the context of the connection type which is identified by the connection spec.                   The port role is not meaningful in the absence of a connection spec reference.                  If a node rule group carries a port role, that role applies also to the associated inter rule where the combination of the roles in the node rule groups at the ends of the inter group rule define the connection orientation.                  For example a root-and-leaf connection may be used in a node where a node rule group collects one set of NEPs has the port role 'root' and another node rule group collects another set of NEPs has the port role 'leaf' where these are joined by an inter rule group. This combination specifies an allowed orientation of the root-and-leaf connection.                  No port role statement means all port roles are allowed.")

  @Valid

  public List<TapiTopologyPortRoleRule> getCepPortRole() {
    return cepPortRole;
  }

  public void setCepPortRole(List<TapiTopologyPortRoleRule> cepPortRole) {
    this.cepPortRole = cepPortRole;
  }

  public TapiTopologyRule cepDirection(List<TapiCommonPortDirection> cepDirection) {
    this.cepDirection = cepDirection;
    return this;
  }

  public TapiTopologyRule addCepDirectionItem(TapiCommonPortDirection cepDirectionItem) {
    if (this.cepDirection == null) {
      this.cepDirection = new ArrayList<TapiCommonPortDirection>();
    }
    this.cepDirection.add(cepDirectionItem);
    return this;
  }

  /**
   * cep direction is a list of port directions that the rule applies to.                  No entry means all cep directions.
   * @return cepDirection
  **/
  @ApiModelProperty(value = "cep direction is a list of port directions that the rule applies to.                  No entry means all cep directions.")

  @Valid

  public List<TapiCommonPortDirection> getCepDirection() {
    return cepDirection;
  }

  public void setCepDirection(List<TapiCommonPortDirection> cepDirection) {
    this.cepDirection = cepDirection;
  }

  public TapiTopologyRule overridePriority(Integer overridePriority) {
    this.overridePriority = overridePriority;
    return this;
  }

  /**
   * The overridePriority allows for one rule in a rule group to override another.                  Priority n rules override priority n+1 rules.                  Rules of the same priority override as follows (n overrides n+1):                  1 - MustNot                  2 - Must                  3 - May                  4 - Null                  Within a rule the flexibility rules (signal, port role...) override as follows (n overriedes n+1):                  1 - Any                  2 - Same                  3 - Different                  Where there are two or more 'Same' rules, they will form an intersection where all must be met.                  
   * @return overridePriority
  **/
  @ApiModelProperty(value = "The overridePriority allows for one rule in a rule group to override another.                  Priority n rules override priority n+1 rules.                  Rules of the same priority override as follows (n overrides n+1):                  1 - MustNot                  2 - Must                  3 - May                  4 - Null                  Within a rule the flexibility rules (signal, port role...) override as follows (n overriedes n+1):                  1 - Any                  2 - Same                  3 - Different                  Where there are two or more 'Same' rules, they will form an intersection where all must be met.                  ")


  public Integer getOverridePriority() {
    return overridePriority;
  }

  public void setOverridePriority(Integer overridePriority) {
    this.overridePriority = overridePriority;
  }

  public TapiTopologyRule layerProtocolQualifier(List<String> layerProtocolQualifier) {
    this.layerProtocolQualifier = layerProtocolQualifier;
    return this;
  }

  public TapiTopologyRule addLayerProtocolQualifierItem(String layerProtocolQualifierItem) {
    if (this.layerProtocolQualifier == null) {
      this.layerProtocolQualifier = new ArrayList<String>();
    }
    this.layerProtocolQualifier.add(layerProtocolQualifierItem);
    return this;
  }

  /**
   * Qualifies a rule for a particular layerProtocol identifying the qualifiers that the rule apples to.                  If the attribute is not present then the rule applies to all relevant qualifiers of the layer protocol of the parent entity.
   * @return layerProtocolQualifier
  **/
  @ApiModelProperty(value = "Qualifies a rule for a particular layerProtocol identifying the qualifiers that the rule apples to.                  If the attribute is not present then the rule applies to all relevant qualifiers of the layer protocol of the parent entity.")


  public List<String> getLayerProtocolQualifier() {
    return layerProtocolQualifier;
  }

  public void setLayerProtocolQualifier(List<String> layerProtocolQualifier) {
    this.layerProtocolQualifier = layerProtocolQualifier;
  }

  public TapiTopologyRule forwardingRule(TapiTopologyForwardingRule forwardingRule) {
    this.forwardingRule = forwardingRule;
    return this;
  }

  /**
   * Rule that restricts the creation/deletion of a Connection between points in the node rule group or related by the inter rule group between node rule groups.
   * @return forwardingRule
  **/
  @ApiModelProperty(value = "Rule that restricts the creation/deletion of a Connection between points in the node rule group or related by the inter rule group between node rule groups.")

  @Valid

  public TapiTopologyForwardingRule getForwardingRule() {
    return forwardingRule;
  }

  public void setForwardingRule(TapiTopologyForwardingRule forwardingRule) {
    this.forwardingRule = forwardingRule;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyRule tapiTopologyRule = (TapiTopologyRule) o;
    return Objects.equals(this.complexRule, tapiTopologyRule.complexRule) &&
        Objects.equals(this.ruleType, tapiTopologyRule.ruleType) &&
        Objects.equals(this.signalProperty, tapiTopologyRule.signalProperty) &&
        Objects.equals(this.connectionSpecReference, tapiTopologyRule.connectionSpecReference) &&
        Objects.equals(this.cepPortRole, tapiTopologyRule.cepPortRole) &&
        Objects.equals(this.cepDirection, tapiTopologyRule.cepDirection) &&
        Objects.equals(this.overridePriority, tapiTopologyRule.overridePriority) &&
        Objects.equals(this.layerProtocolQualifier, tapiTopologyRule.layerProtocolQualifier) &&
        Objects.equals(this.forwardingRule, tapiTopologyRule.forwardingRule) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(complexRule, ruleType, signalProperty, connectionSpecReference, cepPortRole, cepDirection, overridePriority, layerProtocolQualifier, forwardingRule, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyRule {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    complexRule: ").append(toIndentedString(complexRule)).append("\n");
    sb.append("    ruleType: ").append(toIndentedString(ruleType)).append("\n");
    sb.append("    signalProperty: ").append(toIndentedString(signalProperty)).append("\n");
    sb.append("    connectionSpecReference: ").append(toIndentedString(connectionSpecReference)).append("\n");
    sb.append("    cepPortRole: ").append(toIndentedString(cepPortRole)).append("\n");
    sb.append("    cepDirection: ").append(toIndentedString(cepDirection)).append("\n");
    sb.append("    overridePriority: ").append(toIndentedString(overridePriority)).append("\n");
    sb.append("    layerProtocolQualifier: ").append(toIndentedString(layerProtocolQualifier)).append("\n");
    sb.append("    forwardingRule: ").append(toIndentedString(forwardingRule)).append("\n");
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

