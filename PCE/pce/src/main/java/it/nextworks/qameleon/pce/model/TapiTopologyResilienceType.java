package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyResilienceType
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyResilienceType   {
  @JsonProperty("restoration-policy")
  private TapiTopologyRestorationPolicy restorationPolicy = null;

  @JsonProperty("protection-type")
  private TapiTopologyProtectionType protectionType = null;

  public TapiTopologyResilienceType restorationPolicy(TapiTopologyRestorationPolicy restorationPolicy) {
    this.restorationPolicy = restorationPolicy;
    return this;
  }

  /**
   * none
   * @return restorationPolicy
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiTopologyRestorationPolicy getRestorationPolicy() {
    return restorationPolicy;
  }

  public void setRestorationPolicy(TapiTopologyRestorationPolicy restorationPolicy) {
    this.restorationPolicy = restorationPolicy;
  }

  public TapiTopologyResilienceType protectionType(TapiTopologyProtectionType protectionType) {
    this.protectionType = protectionType;
    return this;
  }

  /**
   * none
   * @return protectionType
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiTopologyProtectionType getProtectionType() {
    return protectionType;
  }

  public void setProtectionType(TapiTopologyProtectionType protectionType) {
    this.protectionType = protectionType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyResilienceType tapiTopologyResilienceType = (TapiTopologyResilienceType) o;
    return Objects.equals(this.restorationPolicy, tapiTopologyResilienceType.restorationPolicy) &&
        Objects.equals(this.protectionType, tapiTopologyResilienceType.protectionType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(restorationPolicy, protectionType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyResilienceType {\n");
    
    sb.append("    restorationPolicy: ").append(toIndentedString(restorationPolicy)).append("\n");
    sb.append("    protectionType: ").append(toIndentedString(protectionType)).append("\n");
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

