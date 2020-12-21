package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyTopologyRef
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyTopologyRef   {
  @JsonProperty("topology-uuid")
  private String topologyUuid = null;

  public TapiTopologyTopologyRef topologyUuid(String topologyUuid) {
    this.topologyUuid = topologyUuid;
    return this;
  }

  /**
   * none
   * @return topologyUuid
  **/
  @ApiModelProperty(value = "none")


  public String getTopologyUuid() {
    return topologyUuid;
  }

  public void setTopologyUuid(String topologyUuid) {
    this.topologyUuid = topologyUuid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyTopologyRef tapiTopologyTopologyRef = (TapiTopologyTopologyRef) o;
    return Objects.equals(this.topologyUuid, tapiTopologyTopologyRef.topologyUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topologyUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyTopologyRef {\n");
    
    sb.append("    topologyUuid: ").append(toIndentedString(topologyUuid)).append("\n");
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

