package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathComputationServiceWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathComputationServiceWrapper   {
  @JsonProperty("path-comp-service")
  private TapiPathComputationPathComputationService pathCompService = null;

  public TapiPathComputationPathComputationServiceWrapper pathCompService(TapiPathComputationPathComputationService pathCompService) {
    this.pathCompService = pathCompService;
    return this;
  }

  /**
   * Get pathCompService
   * @return pathCompService
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiPathComputationPathComputationService getPathCompService() {
    return pathCompService;
  }

  public void setPathCompService(TapiPathComputationPathComputationService pathCompService) {
    this.pathCompService = pathCompService;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathComputationServiceWrapper tapiPathComputationPathComputationServiceWrapper = (TapiPathComputationPathComputationServiceWrapper) o;
    return Objects.equals(this.pathCompService, tapiPathComputationPathComputationServiceWrapper.pathCompService);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pathCompService);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathComputationServiceWrapper {\n");
    
    sb.append("    pathCompService: ").append(toIndentedString(pathCompService)).append("\n");
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

