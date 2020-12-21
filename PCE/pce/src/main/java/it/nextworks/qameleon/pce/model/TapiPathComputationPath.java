package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPath
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPath extends TapiCommonGlobalClass  {
  @JsonProperty("layer-protocol-name")
  private TapiCommonLayerProtocolName layerProtocolName = null;

  @JsonProperty("link")
  @Valid
  private List<TapiTopologyLinkRef> link = null;

  @JsonProperty("routing-constraint")
  private TapiPathComputationRoutingConstraint routingConstraint = null;

  @JsonProperty("direction")
  private TapiCommonForwardingDirection direction = null;

  public TapiPathComputationPath layerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
    this.layerProtocolName = layerProtocolName;
    return this;
  }

  /**
   * none
   * @return layerProtocolName
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonLayerProtocolName getLayerProtocolName() {
    return layerProtocolName;
  }

  public void setLayerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
    this.layerProtocolName = layerProtocolName;
  }

  public TapiPathComputationPath link(List<TapiTopologyLinkRef> link) {
    this.link = link;
    return this;
  }

  public TapiPathComputationPath addLinkItem(TapiTopologyLinkRef linkItem) {
    if (this.link == null) {
      this.link = new ArrayList<TapiTopologyLinkRef>();
    }
    this.link.add(linkItem);
    return this;
  }

  /**
   * none
   * @return link
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiTopologyLinkRef> getLink() {
    return link;
  }

  public void setLink(List<TapiTopologyLinkRef> link) {
    this.link = link;
  }

  public TapiPathComputationPath routingConstraint(TapiPathComputationRoutingConstraint routingConstraint) {
    this.routingConstraint = routingConstraint;
    return this;
  }

  /**
   * none
   * @return routingConstraint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationRoutingConstraint getRoutingConstraint() {
    return routingConstraint;
  }

  public void setRoutingConstraint(TapiPathComputationRoutingConstraint routingConstraint) {
    this.routingConstraint = routingConstraint;
  }

  public TapiPathComputationPath direction(TapiCommonForwardingDirection direction) {
    this.direction = direction;
    return this;
  }

  /**
   * none
   * @return direction
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonForwardingDirection getDirection() {
    return direction;
  }

  public void setDirection(TapiCommonForwardingDirection direction) {
    this.direction = direction;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPath tapiPathComputationPath = (TapiPathComputationPath) o;
    return Objects.equals(this.layerProtocolName, tapiPathComputationPath.layerProtocolName) &&
        Objects.equals(this.link, tapiPathComputationPath.link) &&
        Objects.equals(this.routingConstraint, tapiPathComputationPath.routingConstraint) &&
        Objects.equals(this.direction, tapiPathComputationPath.direction) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(layerProtocolName, link, routingConstraint, direction, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPath {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    layerProtocolName: ").append(toIndentedString(layerProtocolName)).append("\n");
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
    sb.append("    routingConstraint: ").append(toIndentedString(routingConstraint)).append("\n");
    sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
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

