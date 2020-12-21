package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathServiceEndPointWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathServiceEndPointWrapper   {
  @JsonProperty("end-point")
  private TapiPathComputationPathServiceEndPoint endPoint = null;

  public TapiPathComputationPathServiceEndPointWrapper endPoint(TapiPathComputationPathServiceEndPoint endPoint) {
    this.endPoint = endPoint;
    return this;
  }

  /**
   * Get endPoint
   * @return endPoint
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiPathComputationPathServiceEndPoint getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(TapiPathComputationPathServiceEndPoint endPoint) {
    this.endPoint = endPoint;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathServiceEndPointWrapper tapiPathComputationPathServiceEndPointWrapper = (TapiPathComputationPathServiceEndPointWrapper) o;
    return Objects.equals(this.endPoint, tapiPathComputationPathServiceEndPointWrapper.endPoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(endPoint);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathServiceEndPointWrapper {\n");
    
    sb.append("    endPoint: ").append(toIndentedString(endPoint)).append("\n");
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

