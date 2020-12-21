package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiCommonServiceInterfacePointRef
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonServiceInterfacePointRef   {
  @JsonProperty("service-interface-point-uuid")
  private String serviceInterfacePointUuid = null;

  public TapiCommonServiceInterfacePointRef serviceInterfacePointUuid(String serviceInterfacePointUuid) {
    this.serviceInterfacePointUuid = serviceInterfacePointUuid;
    return this;
  }

  /**
   * none
   * @return serviceInterfacePointUuid
  **/
  @ApiModelProperty(value = "none")


  public String getServiceInterfacePointUuid() {
    return serviceInterfacePointUuid;
  }

  public void setServiceInterfacePointUuid(String serviceInterfacePointUuid) {
    this.serviceInterfacePointUuid = serviceInterfacePointUuid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonServiceInterfacePointRef tapiCommonServiceInterfacePointRef = (TapiCommonServiceInterfacePointRef) o;
    return Objects.equals(this.serviceInterfacePointUuid, tapiCommonServiceInterfacePointRef.serviceInterfacePointUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(serviceInterfacePointUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonServiceInterfacePointRef {\n");
    
    sb.append("    serviceInterfacePointUuid: ").append(toIndentedString(serviceInterfacePointUuid)).append("\n");
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

