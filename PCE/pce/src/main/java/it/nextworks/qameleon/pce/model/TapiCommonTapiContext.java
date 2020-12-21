package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonTapiContext
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonTapiContext extends TapiCommonGlobalClass  {
  @JsonProperty("service-interface-point")
  @Valid
  private List<TapiCommonServiceInterfacePoint> serviceInterfacePoint = null;

  public TapiCommonTapiContext serviceInterfacePoint(List<TapiCommonServiceInterfacePoint> serviceInterfacePoint) {
    this.serviceInterfacePoint = serviceInterfacePoint;
    return this;
  }

  public TapiCommonTapiContext addServiceInterfacePointItem(TapiCommonServiceInterfacePoint serviceInterfacePointItem) {
    if (this.serviceInterfacePoint == null) {
      this.serviceInterfacePoint = new ArrayList<TapiCommonServiceInterfacePoint>();
    }
    this.serviceInterfacePoint.add(serviceInterfacePointItem);
    return this;
  }

  /**
   * none
   * @return serviceInterfacePoint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiCommonServiceInterfacePoint> getServiceInterfacePoint() {
    return serviceInterfacePoint;
  }

  public void setServiceInterfacePoint(List<TapiCommonServiceInterfacePoint> serviceInterfacePoint) {
    this.serviceInterfacePoint = serviceInterfacePoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonTapiContext tapiCommonTapiContext = (TapiCommonTapiContext) o;
    return Objects.equals(this.serviceInterfacePoint, tapiCommonTapiContext.serviceInterfacePoint) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serviceInterfacePoint, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonTapiContext {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    serviceInterfacePoint: ").append(toIndentedString(serviceInterfacePoint)).append("\n");
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

