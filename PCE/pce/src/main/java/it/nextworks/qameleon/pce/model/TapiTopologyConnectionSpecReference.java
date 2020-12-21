package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyConnectionSpecReference
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyConnectionSpecReference   {
  @JsonProperty("connection-spec")
  private String connectionSpec = null;

  @JsonProperty("connection-spec-name")
  private String connectionSpecName = null;

  public TapiTopologyConnectionSpecReference connectionSpec(String connectionSpec) {
    this.connectionSpec = connectionSpec;
    return this;
  }

  /**
   * The reference to the formal connection type spec.
   * @return connectionSpec
  **/
  @ApiModelProperty(value = "The reference to the formal connection type spec.")


  public String getConnectionSpec() {
    return connectionSpec;
  }

  public void setConnectionSpec(String connectionSpec) {
    this.connectionSpec = connectionSpec;
  }

  public TapiTopologyConnectionSpecReference connectionSpecName(String connectionSpecName) {
    this.connectionSpecName = connectionSpecName;
    return this;
  }

  /**
   * The name of the connection type spec.                  This can be used as a reference to a paper document where full formal machine interpretable specs are not supported.
   * @return connectionSpecName
  **/
  @ApiModelProperty(value = "The name of the connection type spec.                  This can be used as a reference to a paper document where full formal machine interpretable specs are not supported.")


  public String getConnectionSpecName() {
    return connectionSpecName;
  }

  public void setConnectionSpecName(String connectionSpecName) {
    this.connectionSpecName = connectionSpecName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyConnectionSpecReference tapiTopologyConnectionSpecReference = (TapiTopologyConnectionSpecReference) o;
    return Objects.equals(this.connectionSpec, tapiTopologyConnectionSpecReference.connectionSpec) &&
        Objects.equals(this.connectionSpecName, tapiTopologyConnectionSpecReference.connectionSpecName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(connectionSpec, connectionSpecName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyConnectionSpecReference {\n");
    
    sb.append("    connectionSpec: ").append(toIndentedString(connectionSpec)).append("\n");
    sb.append("    connectionSpecName: ").append(toIndentedString(connectionSpecName)).append("\n");
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

