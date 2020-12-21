package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyPortRoleRule
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyPortRoleRule   {
  @JsonProperty("port-role-rule")
  @Valid
  private List<String> portRoleRule = null;

  @JsonProperty("port-role")
  @Valid
  private List<String> portRole = null;

  public TapiTopologyPortRoleRule portRoleRule(List<String> portRoleRule) {
    this.portRoleRule = portRoleRule;
    return this;
  }

  public TapiTopologyPortRoleRule addPortRoleRuleItem(String portRoleRuleItem) {
    if (this.portRoleRule == null) {
      this.portRoleRule = new ArrayList<String>();
    }
    this.portRoleRule.add(portRoleRuleItem);
    return this;
  }

  /**
   * Where the rule references more than one port role or where there are rule intersections either as a result of overlay of rules or inter rule group usage indicates role matching criteria for a connection following the rules.                  For example if two port roles, 'a' and 'b', are listed and the port role rule is 'different', this means that a connection connecting points in that group must have port roles that are different for each CEP in that group.                  In the example if a connection can have n ports of role 'a' and m ports of role 'b' then a maximum of two ports can be drawn from the NEPs of the group and where there are two, one must be role 'a' and one must be role 'b'.
   * @return portRoleRule
  **/
  @ApiModelProperty(value = "Where the rule references more than one port role or where there are rule intersections either as a result of overlay of rules or inter rule group usage indicates role matching criteria for a connection following the rules.                  For example if two port roles, 'a' and 'b', are listed and the port role rule is 'different', this means that a connection connecting points in that group must have port roles that are different for each CEP in that group.                  In the example if a connection can have n ports of role 'a' and m ports of role 'b' then a maximum of two ports can be drawn from the NEPs of the group and where there are two, one must be role 'a' and one must be role 'b'.")


  public List<String> getPortRoleRule() {
    return portRoleRule;
  }

  public void setPortRoleRule(List<String> portRoleRule) {
    this.portRoleRule = portRoleRule;
  }

  public TapiTopologyPortRoleRule portRole(List<String> portRole) {
    this.portRole = portRole;
    return this;
  }

  public TapiTopologyPortRoleRule addPortRoleItem(String portRoleItem) {
    if (this.portRole == null) {
      this.portRole = new ArrayList<String>();
    }
    this.portRole.add(portRoleItem);
    return this;
  }

  /**
   * The role(s) of the port(s) considered in the rule.
   * @return portRole
  **/
  @ApiModelProperty(value = "The role(s) of the port(s) considered in the rule.")


  public List<String> getPortRole() {
    return portRole;
  }

  public void setPortRole(List<String> portRole) {
    this.portRole = portRole;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyPortRoleRule tapiTopologyPortRoleRule = (TapiTopologyPortRoleRule) o;
    return Objects.equals(this.portRoleRule, tapiTopologyPortRoleRule.portRoleRule) &&
        Objects.equals(this.portRole, tapiTopologyPortRoleRule.portRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(portRoleRule, portRole);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyPortRoleRule {\n");
    
    sb.append("    portRoleRule: ").append(toIndentedString(portRoleRule)).append("\n");
    sb.append("    portRole: ").append(toIndentedString(portRole)).append("\n");
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

