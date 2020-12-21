package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonServiceInterfacePoint
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonServiceInterfacePoint extends TapiCommonAdminStatePac  {
  @JsonProperty("available-capacity")
  private TapiCommonCapacity availableCapacity = null;

  @JsonProperty("total-potential-capacity")
  private TapiCommonCapacity totalPotentialCapacity = null;

  @JsonProperty("name")
  @Valid
  private List<TapiCommonNameAndValue> name = null;

  @JsonProperty("uuid")
  private String uuid = null;

  @JsonProperty("supported-layer-protocol-qualifier")
  @Valid
  private List<String> supportedLayerProtocolQualifier = null;

  @JsonProperty("layer-protocol-name")
  private TapiCommonLayerProtocolName layerProtocolName = null;

  @JsonProperty("direction")
  private TapiCommonPortDirection direction = null;

  public TapiCommonServiceInterfacePoint availableCapacity(TapiCommonCapacity availableCapacity) {
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

  public TapiCommonServiceInterfacePoint totalPotentialCapacity(TapiCommonCapacity totalPotentialCapacity) {
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

  public TapiCommonServiceInterfacePoint name(List<TapiCommonNameAndValue> name) {
    this.name = name;
    return this;
  }

  public TapiCommonServiceInterfacePoint addNameItem(TapiCommonNameAndValue nameItem) {
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

  public TapiCommonServiceInterfacePoint uuid(String uuid) {
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

  public TapiCommonServiceInterfacePoint supportedLayerProtocolQualifier(List<String> supportedLayerProtocolQualifier) {
    this.supportedLayerProtocolQualifier = supportedLayerProtocolQualifier;
    return this;
  }

  public TapiCommonServiceInterfacePoint addSupportedLayerProtocolQualifierItem(String supportedLayerProtocolQualifierItem) {
    if (this.supportedLayerProtocolQualifier == null) {
      this.supportedLayerProtocolQualifier = new ArrayList<String>();
    }
    this.supportedLayerProtocolQualifier.add(supportedLayerProtocolQualifierItem);
    return this;
  }

  /**
   * The supported sub-layer(s) or rate(s) of Layer Protocol.
   * @return supportedLayerProtocolQualifier
  **/
  @ApiModelProperty(value = "The supported sub-layer(s) or rate(s) of Layer Protocol.")


  public List<String> getSupportedLayerProtocolQualifier() {
    return supportedLayerProtocolQualifier;
  }

  public void setSupportedLayerProtocolQualifier(List<String> supportedLayerProtocolQualifier) {
    this.supportedLayerProtocolQualifier = supportedLayerProtocolQualifier;
  }

  public TapiCommonServiceInterfacePoint layerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
    this.layerProtocolName = layerProtocolName;
    return this;
  }

  /**
   * Usage of layerProtocolName [>1]  in the ServiceInterfacePoint should be considered experimental
   * @return layerProtocolName
  **/
  @ApiModelProperty(value = "Usage of layerProtocolName [>1]  in the ServiceInterfacePoint should be considered experimental")

  @Valid

  public TapiCommonLayerProtocolName getLayerProtocolName() {
    return layerProtocolName;
  }

  public void setLayerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
    this.layerProtocolName = layerProtocolName;
  }

  public TapiCommonServiceInterfacePoint direction(TapiCommonPortDirection direction) {
    this.direction = direction;
    return this;
  }

  /**
   * If direction attribute is missing the SIP instance is to be intended as 'BIDIRECTIONAL'
   * @return direction
  **/
  @ApiModelProperty(value = "If direction attribute is missing the SIP instance is to be intended as 'BIDIRECTIONAL'")

  @Valid

  public TapiCommonPortDirection getDirection() {
    return direction;
  }

  public void setDirection(TapiCommonPortDirection direction) {
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
    TapiCommonServiceInterfacePoint tapiCommonServiceInterfacePoint = (TapiCommonServiceInterfacePoint) o;
    return Objects.equals(this.availableCapacity, tapiCommonServiceInterfacePoint.availableCapacity) &&
        Objects.equals(this.totalPotentialCapacity, tapiCommonServiceInterfacePoint.totalPotentialCapacity) &&
        Objects.equals(this.name, tapiCommonServiceInterfacePoint.name) &&
        Objects.equals(this.uuid, tapiCommonServiceInterfacePoint.uuid) &&
        Objects.equals(this.supportedLayerProtocolQualifier, tapiCommonServiceInterfacePoint.supportedLayerProtocolQualifier) &&
        Objects.equals(this.layerProtocolName, tapiCommonServiceInterfacePoint.layerProtocolName) &&
        Objects.equals(this.direction, tapiCommonServiceInterfacePoint.direction) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(availableCapacity, totalPotentialCapacity, name, uuid, supportedLayerProtocolQualifier, layerProtocolName, direction, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonServiceInterfacePoint {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    availableCapacity: ").append(toIndentedString(availableCapacity)).append("\n");
    sb.append("    totalPotentialCapacity: ").append(toIndentedString(totalPotentialCapacity)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    supportedLayerProtocolQualifier: ").append(toIndentedString(supportedLayerProtocolQualifier)).append("\n");
    sb.append("    layerProtocolName: ").append(toIndentedString(layerProtocolName)).append("\n");
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

