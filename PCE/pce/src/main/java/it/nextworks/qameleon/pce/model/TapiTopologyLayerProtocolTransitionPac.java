package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyLayerProtocolTransitionPac
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyLayerProtocolTransitionPac   {
  @JsonProperty("transitioned-layer-protocol-name")
  @Valid
  private List<String> transitionedLayerProtocolName = null;

  public TapiTopologyLayerProtocolTransitionPac transitionedLayerProtocolName(List<String> transitionedLayerProtocolName) {
    this.transitionedLayerProtocolName = transitionedLayerProtocolName;
    return this;
  }

  public TapiTopologyLayerProtocolTransitionPac addTransitionedLayerProtocolNameItem(String transitionedLayerProtocolNameItem) {
    if (this.transitionedLayerProtocolName == null) {
      this.transitionedLayerProtocolName = new ArrayList<String>();
    }
    this.transitionedLayerProtocolName.add(transitionedLayerProtocolNameItem);
    return this;
  }

  /**
   * Provides the ordered structure of layer protocol transitions encapsulated in the TopologicalEntity.                  The list starts with the client side as the first entry and includes all layer-protocol names (hence the smallest number is 2 as otherwise the Link is not transitional).                  The ordering relates also to the LinkPort role (which emphasizes the orientation).
   * @return transitionedLayerProtocolName
  **/
  @ApiModelProperty(value = "Provides the ordered structure of layer protocol transitions encapsulated in the TopologicalEntity.                  The list starts with the client side as the first entry and includes all layer-protocol names (hence the smallest number is 2 as otherwise the Link is not transitional).                  The ordering relates also to the LinkPort role (which emphasizes the orientation).")


  public List<String> getTransitionedLayerProtocolName() {
    return transitionedLayerProtocolName;
  }

  public void setTransitionedLayerProtocolName(List<String> transitionedLayerProtocolName) {
    this.transitionedLayerProtocolName = transitionedLayerProtocolName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyLayerProtocolTransitionPac tapiTopologyLayerProtocolTransitionPac = (TapiTopologyLayerProtocolTransitionPac) o;
    return Objects.equals(this.transitionedLayerProtocolName, tapiTopologyLayerProtocolTransitionPac.transitionedLayerProtocolName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transitionedLayerProtocolName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyLayerProtocolTransitionPac {\n");
    
    sb.append("    transitionedLayerProtocolName: ").append(toIndentedString(transitionedLayerProtocolName)).append("\n");
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

