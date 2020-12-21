package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyConnectionSpecReferenceWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyConnectionSpecReferenceWrapper   {
  @JsonProperty("connection-spec-reference")
  private TapiTopologyConnectionSpecReference connectionSpecReference = null;

  public TapiTopologyConnectionSpecReferenceWrapper connectionSpecReference(TapiTopologyConnectionSpecReference connectionSpecReference) {
    this.connectionSpecReference = connectionSpecReference;
    return this;
  }

  /**
   * Get connectionSpecReference
   * @return connectionSpecReference
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyConnectionSpecReference getConnectionSpecReference() {
    return connectionSpecReference;
  }

  public void setConnectionSpecReference(TapiTopologyConnectionSpecReference connectionSpecReference) {
    this.connectionSpecReference = connectionSpecReference;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyConnectionSpecReferenceWrapper tapiTopologyConnectionSpecReferenceWrapper = (TapiTopologyConnectionSpecReferenceWrapper) o;
    return Objects.equals(this.connectionSpecReference, tapiTopologyConnectionSpecReferenceWrapper.connectionSpecReference);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectionSpecReference);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyConnectionSpecReferenceWrapper {\n");
    
    sb.append("    connectionSpecReference: ").append(toIndentedString(connectionSpecReference)).append("\n");
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

