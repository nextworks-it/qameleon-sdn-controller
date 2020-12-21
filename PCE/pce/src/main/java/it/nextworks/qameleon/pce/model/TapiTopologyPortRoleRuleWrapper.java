package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyPortRoleRuleWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyPortRoleRuleWrapper   {
  @JsonProperty("cep-port-role")
  private TapiTopologyPortRoleRule cepPortRole = null;

  public TapiTopologyPortRoleRuleWrapper cepPortRole(TapiTopologyPortRoleRule cepPortRole) {
    this.cepPortRole = cepPortRole;
    return this;
  }

  /**
   * Get cepPortRole
   * @return cepPortRole
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyPortRoleRule getCepPortRole() {
    return cepPortRole;
  }

  public void setCepPortRole(TapiTopologyPortRoleRule cepPortRole) {
    this.cepPortRole = cepPortRole;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyPortRoleRuleWrapper tapiTopologyPortRoleRuleWrapper = (TapiTopologyPortRoleRuleWrapper) o;
    return Objects.equals(this.cepPortRole, tapiTopologyPortRoleRuleWrapper.cepPortRole);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cepPortRole);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyPortRoleRuleWrapper {\n");
    
    sb.append("    cepPortRole: ").append(toIndentedString(cepPortRole)).append("\n");
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

