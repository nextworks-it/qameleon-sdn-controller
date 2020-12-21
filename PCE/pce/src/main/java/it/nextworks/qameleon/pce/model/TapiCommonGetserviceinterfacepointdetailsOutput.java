package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonGetserviceinterfacepointdetailsOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonGetserviceinterfacepointdetailsOutput   {
  @JsonProperty("sip")
  private TapiCommonServiceInterfacePoint sip = null;

  public TapiCommonGetserviceinterfacepointdetailsOutput sip(TapiCommonServiceInterfacePoint sip) {
    this.sip = sip;
    return this;
  }

  /**
   * none
   * @return sip
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonServiceInterfacePoint getSip() {
    return sip;
  }

  public void setSip(TapiCommonServiceInterfacePoint sip) {
    this.sip = sip;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonGetserviceinterfacepointdetailsOutput tapiCommonGetserviceinterfacepointdetailsOutput = (TapiCommonGetserviceinterfacepointdetailsOutput) o;
    return Objects.equals(this.sip, tapiCommonGetserviceinterfacepointdetailsOutput.sip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sip);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonGetserviceinterfacepointdetailsOutput {\n");
    
    sb.append("    sip: ").append(toIndentedString(sip)).append("\n");
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

