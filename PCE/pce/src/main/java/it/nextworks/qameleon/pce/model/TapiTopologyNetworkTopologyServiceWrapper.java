package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyNetworkTopologyServiceWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNetworkTopologyServiceWrapper   {
  @JsonProperty("nw-topology-service")
  private TapiTopologyNetworkTopologyService nwTopologyService = null;

  public TapiTopologyNetworkTopologyServiceWrapper nwTopologyService(TapiTopologyNetworkTopologyService nwTopologyService) {
    this.nwTopologyService = nwTopologyService;
    return this;
  }

  /**
   * Get nwTopologyService
   * @return nwTopologyService
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyNetworkTopologyService getNwTopologyService() {
    return nwTopologyService;
  }

  public void setNwTopologyService(TapiTopologyNetworkTopologyService nwTopologyService) {
    this.nwTopologyService = nwTopologyService;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNetworkTopologyServiceWrapper tapiTopologyNetworkTopologyServiceWrapper = (TapiTopologyNetworkTopologyServiceWrapper) o;
    return Objects.equals(this.nwTopologyService, tapiTopologyNetworkTopologyServiceWrapper.nwTopologyService);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nwTopologyService);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNetworkTopologyServiceWrapper {\n");
    
    sb.append("    nwTopologyService: ").append(toIndentedString(nwTopologyService)).append("\n");
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

