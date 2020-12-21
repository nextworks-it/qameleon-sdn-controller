package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyNodeRef
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeRef extends TapiTopologyTopologyRef  {
  @JsonProperty("node-uuid")
  private String nodeUuid = null;

  public TapiTopologyNodeRef nodeUuid(String nodeUuid) {
    this.nodeUuid = nodeUuid;
    return this;
  }

  /**
   * none
   * @return nodeUuid
  **/
  @ApiModelProperty(value = "none")


  public String getNodeUuid() {
    return nodeUuid;
  }

  public void setNodeUuid(String nodeUuid) {
    this.nodeUuid = nodeUuid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeRef tapiTopologyNodeRef = (TapiTopologyNodeRef) o;
    return Objects.equals(this.nodeUuid, tapiTopologyNodeRef.nodeUuid) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeUuid, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeRef {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    nodeUuid: ").append(toIndentedString(nodeUuid)).append("\n");
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

