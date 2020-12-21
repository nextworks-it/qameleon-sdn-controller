package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyNodeWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeWrapper   {
  @JsonProperty("node")
  private TapiTopologyNode node = null;

  public TapiTopologyNodeWrapper node(TapiTopologyNode node) {
    this.node = node;
    return this;
  }

  /**
   * Get node
   * @return node
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyNode getNode() {
    return node;
  }

  public void setNode(TapiTopologyNode node) {
    this.node = node;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeWrapper tapiTopologyNodeWrapper = (TapiTopologyNodeWrapper) o;
    return Objects.equals(this.node, tapiTopologyNodeWrapper.node);
  }

  @Override
  public int hashCode() {
    return Objects.hash(node);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeWrapper {\n");
    
    sb.append("    node: ").append(toIndentedString(node)).append("\n");
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

