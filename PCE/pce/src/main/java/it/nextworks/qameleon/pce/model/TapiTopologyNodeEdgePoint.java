package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyNodeEdgePoint
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyNodeEdgePoint extends TapiCommonAdminStatePac  {
  @JsonProperty("available-capacity")
  private TapiCommonCapacity availableCapacity = null;

  @JsonProperty("total-potential-capacity")
  private TapiCommonCapacity totalPotentialCapacity = null;

  @JsonProperty("name")
  @Valid
  private List<TapiCommonNameAndValue> name = null;

  @JsonProperty("uuid")
  private String uuid = null;

  @JsonProperty("termination-direction")
  private TapiCommonTerminationDirection terminationDirection = null;

  @JsonProperty("termination-state")
  private TapiCommonTerminationState terminationState = null;

  @JsonProperty("link-port-role")
  private TapiCommonPortRole linkPortRole = null;

  @JsonProperty("mapped-service-interface-point")
  @Valid
  private List<TapiCommonServiceInterfacePointRef> mappedServiceInterfacePoint = null;

  @JsonProperty("available-cep-layer-protocol")
  @Valid
  private List<TapiTopologyNepLayerProtocolCapability> availableCepLayerProtocol = null;

  @JsonProperty("aggregated-node-edge-point")
  @Valid
  private List<TapiTopologyNodeEdgePointRef> aggregatedNodeEdgePoint = null;

  @JsonProperty("layer-protocol-name")
  private TapiCommonLayerProtocolName layerProtocolName = null;

  @JsonProperty("link-port-direction")
  private TapiCommonPortDirection linkPortDirection = null;

  public TapiTopologyNodeEdgePoint availableCapacity(TapiCommonCapacity availableCapacity) {
    this.availableCapacity = availableCapacity;
    return this;
  }

  /**
   * Capacity available to be assigned.
   * @return availableCapacity
  **/
  @ApiModelProperty(value = "Capacity available to be assigned.")

  @Valid

  public TapiCommonCapacity getAvailableCapacity() {
    return availableCapacity;
  }

  public void setAvailableCapacity(TapiCommonCapacity availableCapacity) {
    this.availableCapacity = availableCapacity;
  }

  public TapiTopologyNodeEdgePoint totalPotentialCapacity(TapiCommonCapacity totalPotentialCapacity) {
    this.totalPotentialCapacity = totalPotentialCapacity;
    return this;
  }

  /**
   * An optimistic view of the capacity of the entity assuming that any shared capacity is available to be taken.
   * @return totalPotentialCapacity
  **/
  @ApiModelProperty(value = "An optimistic view of the capacity of the entity assuming that any shared capacity is available to be taken.")

  @Valid

  public TapiCommonCapacity getTotalPotentialCapacity() {
    return totalPotentialCapacity;
  }

  public void setTotalPotentialCapacity(TapiCommonCapacity totalPotentialCapacity) {
    this.totalPotentialCapacity = totalPotentialCapacity;
  }

  public TapiTopologyNodeEdgePoint name(List<TapiCommonNameAndValue> name) {
    this.name = name;
    return this;
  }

  public TapiTopologyNodeEdgePoint addNameItem(TapiCommonNameAndValue nameItem) {
    if (this.name == null) {
      this.name = new ArrayList<TapiCommonNameAndValue>();
    }
    this.name.add(nameItem);
    return this;
  }

  /**
   * List of names. This value is unique in some namespace but may change during the life of the entity.                  A name carries no semantics with respect to the purpose of the entity.
   * @return name
  **/
  @ApiModelProperty(value = "List of names. This value is unique in some namespace but may change during the life of the entity.                  A name carries no semantics with respect to the purpose of the entity.")

  @Valid

  public List<TapiCommonNameAndValue> getName() {
    return name;
  }

  public void setName(List<TapiCommonNameAndValue> name) {
    this.name = name;
  }

  public TapiTopologyNodeEdgePoint uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  /**
   * UUID: An identifier that is universally unique within an identifier space, where the identifier space is itself globally unique, and immutable.                  An UUID carries no semantics with respect to the purpose or state of the entity.                  UUID here uses string representation as defined in RFC 4122.  The canonical representation uses lowercase characters.                  Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}                   Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6
   * @return uuid
  **/
  @ApiModelProperty(value = "UUID: An identifier that is universally unique within an identifier space, where the identifier space is itself globally unique, and immutable.                  An UUID carries no semantics with respect to the purpose or state of the entity.                  UUID here uses string representation as defined in RFC 4122.  The canonical representation uses lowercase characters.                  Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}                   Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6")


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public TapiTopologyNodeEdgePoint terminationDirection(TapiCommonTerminationDirection terminationDirection) {
    this.terminationDirection = terminationDirection;
    return this;
  }

  /**
   * The overall directionality of the entity.                   - A BIDIRECTIONAL entity will have some SINK and/or SOURCE flows.                  - A SINK entity can only contain elements with SINK flows or CONTRA_DIRECTION_SOURCE flows.                  - A SOURCE entity can only contain SOURCE flows or CONTRA_DIRECTION_SINK flows.
   * @return terminationDirection
  **/
  @ApiModelProperty(value = "The overall directionality of the entity.                   - A BIDIRECTIONAL entity will have some SINK and/or SOURCE flows.                  - A SINK entity can only contain elements with SINK flows or CONTRA_DIRECTION_SOURCE flows.                  - A SOURCE entity can only contain SOURCE flows or CONTRA_DIRECTION_SINK flows.")

  @Valid

  public TapiCommonTerminationDirection getTerminationDirection() {
    return terminationDirection;
  }

  public void setTerminationDirection(TapiCommonTerminationDirection terminationDirection) {
    this.terminationDirection = terminationDirection;
  }

  public TapiTopologyNodeEdgePoint terminationState(TapiCommonTerminationState terminationState) {
    this.terminationState = terminationState;
    return this;
  }

  /**
   * Indicates whether the layer is terminated and if so how.
   * @return terminationState
  **/
  @ApiModelProperty(value = "Indicates whether the layer is terminated and if so how.")

  @Valid

  public TapiCommonTerminationState getTerminationState() {
    return terminationState;
  }

  public void setTerminationState(TapiCommonTerminationState terminationState) {
    this.terminationState = terminationState;
  }

  public TapiTopologyNodeEdgePoint linkPortRole(TapiCommonPortRole linkPortRole) {
    this.linkPortRole = linkPortRole;
    return this;
  }

  /**
   * Each LinkEnd of the Link has a role (e.g., symmetric, hub, spoke, leaf, root)  in the context of the Link with respect to the Link function. 
   * @return linkPortRole
  **/
  @ApiModelProperty(value = "Each LinkEnd of the Link has a role (e.g., symmetric, hub, spoke, leaf, root)  in the context of the Link with respect to the Link function. ")

  @Valid

  public TapiCommonPortRole getLinkPortRole() {
    return linkPortRole;
  }

  public void setLinkPortRole(TapiCommonPortRole linkPortRole) {
    this.linkPortRole = linkPortRole;
  }

  public TapiTopologyNodeEdgePoint mappedServiceInterfacePoint(List<TapiCommonServiceInterfacePointRef> mappedServiceInterfacePoint) {
    this.mappedServiceInterfacePoint = mappedServiceInterfacePoint;
    return this;
  }

  public TapiTopologyNodeEdgePoint addMappedServiceInterfacePointItem(TapiCommonServiceInterfacePointRef mappedServiceInterfacePointItem) {
    if (this.mappedServiceInterfacePoint == null) {
      this.mappedServiceInterfacePoint = new ArrayList<TapiCommonServiceInterfacePointRef>();
    }
    this.mappedServiceInterfacePoint.add(mappedServiceInterfacePointItem);
    return this;
  }

  /**
   * NodeEdgePoint mapped to more than ServiceInterfacePoint (slicing/virtualizing) or a ServiceInterfacePoint mapped to more than one NodeEdgePoint (load balancing/Resilience) should be considered experimental
   * @return mappedServiceInterfacePoint
  **/
  @ApiModelProperty(value = "NodeEdgePoint mapped to more than ServiceInterfacePoint (slicing/virtualizing) or a ServiceInterfacePoint mapped to more than one NodeEdgePoint (load balancing/Resilience) should be considered experimental")

  @Valid

  public List<TapiCommonServiceInterfacePointRef> getMappedServiceInterfacePoint() {
    return mappedServiceInterfacePoint;
  }

  public void setMappedServiceInterfacePoint(List<TapiCommonServiceInterfacePointRef> mappedServiceInterfacePoint) {
    this.mappedServiceInterfacePoint = mappedServiceInterfacePoint;
  }

  public TapiTopologyNodeEdgePoint availableCepLayerProtocol(List<TapiTopologyNepLayerProtocolCapability> availableCepLayerProtocol) {
    this.availableCepLayerProtocol = availableCepLayerProtocol;
    return this;
  }

  public TapiTopologyNodeEdgePoint addAvailableCepLayerProtocolItem(TapiTopologyNepLayerProtocolCapability availableCepLayerProtocolItem) {
    if (this.availableCepLayerProtocol == null) {
      this.availableCepLayerProtocol = new ArrayList<TapiTopologyNepLayerProtocolCapability>();
    }
    this.availableCepLayerProtocol.add(availableCepLayerProtocolItem);
    return this;
  }

  /**
   * none
   * @return availableCepLayerProtocol
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiTopologyNepLayerProtocolCapability> getAvailableCepLayerProtocol() {
    return availableCepLayerProtocol;
  }

  public void setAvailableCepLayerProtocol(List<TapiTopologyNepLayerProtocolCapability> availableCepLayerProtocol) {
    this.availableCepLayerProtocol = availableCepLayerProtocol;
  }

  public TapiTopologyNodeEdgePoint aggregatedNodeEdgePoint(List<TapiTopologyNodeEdgePointRef> aggregatedNodeEdgePoint) {
    this.aggregatedNodeEdgePoint = aggregatedNodeEdgePoint;
    return this;
  }

  public TapiTopologyNodeEdgePoint addAggregatedNodeEdgePointItem(TapiTopologyNodeEdgePointRef aggregatedNodeEdgePointItem) {
    if (this.aggregatedNodeEdgePoint == null) {
      this.aggregatedNodeEdgePoint = new ArrayList<TapiTopologyNodeEdgePointRef>();
    }
    this.aggregatedNodeEdgePoint.add(aggregatedNodeEdgePointItem);
    return this;
  }

  /**
   * none
   * @return aggregatedNodeEdgePoint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiTopologyNodeEdgePointRef> getAggregatedNodeEdgePoint() {
    return aggregatedNodeEdgePoint;
  }

  public void setAggregatedNodeEdgePoint(List<TapiTopologyNodeEdgePointRef> aggregatedNodeEdgePoint) {
    this.aggregatedNodeEdgePoint = aggregatedNodeEdgePoint;
  }

  public TapiTopologyNodeEdgePoint layerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
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

  public TapiTopologyNodeEdgePoint linkPortDirection(TapiCommonPortDirection linkPortDirection) {
    this.linkPortDirection = linkPortDirection;
    return this;
  }

  /**
   * The orientation of defined flow at the LinkEnd.
   * @return linkPortDirection
  **/
  @ApiModelProperty(value = "The orientation of defined flow at the LinkEnd.")

  @Valid

  public TapiCommonPortDirection getLinkPortDirection() {
    return linkPortDirection;
  }

  public void setLinkPortDirection(TapiCommonPortDirection linkPortDirection) {
    this.linkPortDirection = linkPortDirection;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyNodeEdgePoint tapiTopologyNodeEdgePoint = (TapiTopologyNodeEdgePoint) o;
    return Objects.equals(this.availableCapacity, tapiTopologyNodeEdgePoint.availableCapacity) &&
        Objects.equals(this.totalPotentialCapacity, tapiTopologyNodeEdgePoint.totalPotentialCapacity) &&
        Objects.equals(this.name, tapiTopologyNodeEdgePoint.name) &&
        Objects.equals(this.uuid, tapiTopologyNodeEdgePoint.uuid) &&
        Objects.equals(this.terminationDirection, tapiTopologyNodeEdgePoint.terminationDirection) &&
        Objects.equals(this.terminationState, tapiTopologyNodeEdgePoint.terminationState) &&
        Objects.equals(this.linkPortRole, tapiTopologyNodeEdgePoint.linkPortRole) &&
        Objects.equals(this.mappedServiceInterfacePoint, tapiTopologyNodeEdgePoint.mappedServiceInterfacePoint) &&
        Objects.equals(this.availableCepLayerProtocol, tapiTopologyNodeEdgePoint.availableCepLayerProtocol) &&
        Objects.equals(this.aggregatedNodeEdgePoint, tapiTopologyNodeEdgePoint.aggregatedNodeEdgePoint) &&
        Objects.equals(this.layerProtocolName, tapiTopologyNodeEdgePoint.layerProtocolName) &&
        Objects.equals(this.linkPortDirection, tapiTopologyNodeEdgePoint.linkPortDirection) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(availableCapacity, totalPotentialCapacity, name, uuid, terminationDirection, terminationState, linkPortRole, mappedServiceInterfacePoint, availableCepLayerProtocol, aggregatedNodeEdgePoint, layerProtocolName, linkPortDirection, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyNodeEdgePoint {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    availableCapacity: ").append(toIndentedString(availableCapacity)).append("\n");
    sb.append("    totalPotentialCapacity: ").append(toIndentedString(totalPotentialCapacity)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    terminationDirection: ").append(toIndentedString(terminationDirection)).append("\n");
    sb.append("    terminationState: ").append(toIndentedString(terminationState)).append("\n");
    sb.append("    linkPortRole: ").append(toIndentedString(linkPortRole)).append("\n");
    sb.append("    mappedServiceInterfacePoint: ").append(toIndentedString(mappedServiceInterfacePoint)).append("\n");
    sb.append("    availableCepLayerProtocol: ").append(toIndentedString(availableCepLayerProtocol)).append("\n");
    sb.append("    aggregatedNodeEdgePoint: ").append(toIndentedString(aggregatedNodeEdgePoint)).append("\n");
    sb.append("    layerProtocolName: ").append(toIndentedString(layerProtocolName)).append("\n");
    sb.append("    linkPortDirection: ").append(toIndentedString(linkPortDirection)).append("\n");
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

