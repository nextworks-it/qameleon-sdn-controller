package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyGetnodedetailsOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyGetnodedetailsOutput   {
  @JsonProperty("node")
  private TapiTopologyNode node = null;

  public TapiTopologyGetnodedetailsOutput node(TapiTopologyNode node) {
    this.node = node;
    return this;
  }

  /**
   * none
   * @return node
  **/
  @ApiModelProperty(value = "none")

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
    TapiTopologyGetnodedetailsOutput tapiTopologyGetnodedetailsOutput = (TapiTopologyGetnodedetailsOutput) o;
    return Objects.equals(this.node, tapiTopologyGetnodedetailsOutput.node);
  }

  @Override
  public int hashCode() {
    return Objects.hash(node);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyGetnodedetailsOutput {\n");
    
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

