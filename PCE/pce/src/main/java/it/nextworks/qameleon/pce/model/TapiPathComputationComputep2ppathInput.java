package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationComputep2ppathInput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationComputep2ppathInput   {
  @JsonProperty("topology-constraint")
  private TapiPathComputationTopologyConstraint topologyConstraint = null;

  @JsonProperty("end-point")
  @Valid
  private List<TapiPathComputationPathServiceEndPoint> endPoint = null;

  @JsonProperty("objective-function")
  private TapiPathComputationPathObjectiveFunction objectiveFunction = null;

  @JsonProperty("name")
  @Valid
  private List<TapiCommonNameAndValue> name = null;

  @JsonProperty("routing-constraint")
  private TapiPathComputationRoutingConstraint routingConstraint = null;

  @JsonProperty("uuid")
  private String uuid = null;

  public TapiPathComputationComputep2ppathInput topologyConstraint(TapiPathComputationTopologyConstraint topologyConstraint) {
    this.topologyConstraint = topologyConstraint;
    return this;
  }

  /**
   * none
   * @return topologyConstraint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationTopologyConstraint getTopologyConstraint() {
    return topologyConstraint;
  }

  public void setTopologyConstraint(TapiPathComputationTopologyConstraint topologyConstraint) {
    this.topologyConstraint = topologyConstraint;
  }

  public TapiPathComputationComputep2ppathInput endPoint(List<TapiPathComputationPathServiceEndPoint> endPoint) {
    this.endPoint = endPoint;
    return this;
  }

  public TapiPathComputationComputep2ppathInput addEndPointItem(TapiPathComputationPathServiceEndPoint endPointItem) {
    if (this.endPoint == null) {
      this.endPoint = new ArrayList<TapiPathComputationPathServiceEndPoint>();
    }
    this.endPoint.add(endPointItem);
    return this;
  }

  /**
   * none
   * @return endPoint
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiPathComputationPathServiceEndPoint> getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(List<TapiPathComputationPathServiceEndPoint> endPoint) {
    this.endPoint = endPoint;
  }

  public TapiPathComputationComputep2ppathInput objectiveFunction(TapiPathComputationPathObjectiveFunction objectiveFunction) {
    this.objectiveFunction = objectiveFunction;
    return this;
  }

  /**
   * none
   * @return objectiveFunction
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationPathObjectiveFunction getObjectiveFunction() {
    return objectiveFunction;
  }

  public void setObjectiveFunction(TapiPathComputationPathObjectiveFunction objectiveFunction) {
    this.objectiveFunction = objectiveFunction;
  }

  public TapiPathComputationComputep2ppathInput name(List<TapiCommonNameAndValue> name) {
    this.name = name;
    return this;
  }

  public TapiPathComputationComputep2ppathInput addNameItem(TapiCommonNameAndValue nameItem) {
    if (this.name == null) {
      this.name = new ArrayList<TapiCommonNameAndValue>();
    }
    this.name.add(nameItem);
    return this;
  }

  /**
   * List of names. This value is unique in some namespace but may change during the life of the entity.                      A name carries no semantics with respect to the purpose of the entity.
   * @return name
  **/
  @ApiModelProperty(value = "List of names. This value is unique in some namespace but may change during the life of the entity.                      A name carries no semantics with respect to the purpose of the entity.")

  @Valid

  public List<TapiCommonNameAndValue> getName() {
    return name;
  }

  public void setName(List<TapiCommonNameAndValue> name) {
    this.name = name;
  }

  public TapiPathComputationComputep2ppathInput routingConstraint(TapiPathComputationRoutingConstraint routingConstraint) {
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

  public TapiPathComputationComputep2ppathInput uuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  /**
   * UUID: An identifier that is universally unique within an identifier space, where the identifier space is itself globally unique, and immutable.                      An UUID carries no semantics with respect to the purpose or state of the entity.                      UUID here uses string representation as defined in RFC 4122.  The canonical representation uses lowercase characters.                      Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}                       Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6
   * @return uuid
  **/
  @ApiModelProperty(value = "UUID: An identifier that is universally unique within an identifier space, where the identifier space is itself globally unique, and immutable.                      An UUID carries no semantics with respect to the purpose or state of the entity.                      UUID here uses string representation as defined in RFC 4122.  The canonical representation uses lowercase characters.                      Pattern: [0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-' + '[0-9a-fA-F]{4}-[0-9a-fA-F]{12}                       Example of a UUID in string representation: f81d4fae-7dec-11d0-a765-00a0c91e6bf6")


  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationComputep2ppathInput tapiPathComputationComputep2ppathInput = (TapiPathComputationComputep2ppathInput) o;
    return Objects.equals(this.topologyConstraint, tapiPathComputationComputep2ppathInput.topologyConstraint) &&
        Objects.equals(this.endPoint, tapiPathComputationComputep2ppathInput.endPoint) &&
        Objects.equals(this.objectiveFunction, tapiPathComputationComputep2ppathInput.objectiveFunction) &&
        Objects.equals(this.name, tapiPathComputationComputep2ppathInput.name) &&
        Objects.equals(this.routingConstraint, tapiPathComputationComputep2ppathInput.routingConstraint) &&
        Objects.equals(this.uuid, tapiPathComputationComputep2ppathInput.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(topologyConstraint, endPoint, objectiveFunction, name, routingConstraint, uuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationComputep2ppathInput {\n");
    
    sb.append("    topologyConstraint: ").append(toIndentedString(topologyConstraint)).append("\n");
    sb.append("    endPoint: ").append(toIndentedString(endPoint)).append("\n");
    sb.append("    objectiveFunction: ").append(toIndentedString(objectiveFunction)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    routingConstraint: ").append(toIndentedString(routingConstraint)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
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

