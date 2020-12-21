package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiPathComputationPathRef
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathRef   {
  @JsonProperty("path-uuid")
  private String pathUuid = null;

  public TapiPathComputationPathRef pathUuid(String pathUuid) {
    this.pathUuid = pathUuid;
    return this;
  }

  /**
   * none
   * @return pathUuid
  **/
  @ApiModelProperty(value = "none")


  public String getPathUuid() {
    return pathUuid;
  }

  public void setPathUuid(String pathUuid) {
    this.pathUuid = pathUuid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathRef tapiPathComputationPathRef = (TapiPathComputationPathRef) o;
    return Objects.equals(this.pathUuid, tapiPathComputationPathRef.pathUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pathUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathRef {\n");
    
    sb.append("    pathUuid: ").append(toIndentedString(pathUuid)).append("\n");
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

