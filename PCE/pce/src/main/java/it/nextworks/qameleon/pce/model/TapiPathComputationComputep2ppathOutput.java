package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationComputep2ppathOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationComputep2ppathOutput   {
  @JsonProperty("service")
  private TapiPathComputationPathComputationService service = null;

  public TapiPathComputationComputep2ppathOutput service(TapiPathComputationPathComputationService service) {
    this.service = service;
    return this;
  }

  /**
   * none
   * @return service
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationPathComputationService getService() {
    return service;
  }

  public void setService(TapiPathComputationPathComputationService service) {
    this.service = service;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationComputep2ppathOutput tapiPathComputationComputep2ppathOutput = (TapiPathComputationComputep2ppathOutput) o;
    return Objects.equals(this.service, tapiPathComputationComputep2ppathOutput.service);
  }

  @Override
  public int hashCode() {
    return Objects.hash(service);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationComputep2ppathOutput {\n");
    
    sb.append("    service: ").append(toIndentedString(service)).append("\n");
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

