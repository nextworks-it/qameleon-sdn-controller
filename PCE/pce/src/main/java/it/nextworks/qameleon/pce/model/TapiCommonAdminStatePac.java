package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonAdminStatePac
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonAdminStatePac   {
  @JsonProperty("operational-state")
  private TapiCommonOperationalState operationalState = null;

  @JsonProperty("lifecycle-state")
  private TapiCommonLifecycleState lifecycleState = null;

  @JsonProperty("administrative-state")
  private TapiCommonAdministrativeState administrativeState = null;

  public TapiCommonAdminStatePac operationalState(TapiCommonOperationalState operationalState) {
    this.operationalState = operationalState;
    return this;
  }

  /**
   * The operational state gives the information about the real capability of a resource to provide or not provide service.
   * @return operationalState
  **/
  @ApiModelProperty(value = "The operational state gives the information about the real capability of a resource to provide or not provide service.")

  @Valid

  public TapiCommonOperationalState getOperationalState() {
    return operationalState;
  }

  public void setOperationalState(TapiCommonOperationalState operationalState) {
    this.operationalState = operationalState;
  }

  public TapiCommonAdminStatePac lifecycleState(TapiCommonLifecycleState lifecycleState) {
    this.lifecycleState = lifecycleState;
    return this;
  }

  /**
   * Used to track the planned deployment, allocation to clients and withdrawal of resources.
   * @return lifecycleState
  **/
  @ApiModelProperty(value = "Used to track the planned deployment, allocation to clients and withdrawal of resources.")

  @Valid

  public TapiCommonLifecycleState getLifecycleState() {
    return lifecycleState;
  }

  public void setLifecycleState(TapiCommonLifecycleState lifecycleState) {
    this.lifecycleState = lifecycleState;
  }

  public TapiCommonAdminStatePac administrativeState(TapiCommonAdministrativeState administrativeState) {
    this.administrativeState = administrativeState;
    return this;
  }

  /**
   * The administration of managed objects operates independently of the operability and usage of managed objects and is described by the administrative state attribute. The administrative state is used by the operator to make a resource available for service, or to remove a resource from service.
   * @return administrativeState
  **/
  @ApiModelProperty(value = "The administration of managed objects operates independently of the operability and usage of managed objects and is described by the administrative state attribute. The administrative state is used by the operator to make a resource available for service, or to remove a resource from service.")

  @Valid

  public TapiCommonAdministrativeState getAdministrativeState() {
    return administrativeState;
  }

  public void setAdministrativeState(TapiCommonAdministrativeState administrativeState) {
    this.administrativeState = administrativeState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonAdminStatePac tapiCommonAdminStatePac = (TapiCommonAdminStatePac) o;
    return Objects.equals(this.operationalState, tapiCommonAdminStatePac.operationalState) &&
        Objects.equals(this.lifecycleState, tapiCommonAdminStatePac.lifecycleState) &&
        Objects.equals(this.administrativeState, tapiCommonAdminStatePac.administrativeState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationalState, lifecycleState, administrativeState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonAdminStatePac {\n");
    
    sb.append("    operationalState: ").append(toIndentedString(operationalState)).append("\n");
    sb.append("    lifecycleState: ").append(toIndentedString(lifecycleState)).append("\n");
    sb.append("    administrativeState: ").append(toIndentedString(administrativeState)).append("\n");
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

