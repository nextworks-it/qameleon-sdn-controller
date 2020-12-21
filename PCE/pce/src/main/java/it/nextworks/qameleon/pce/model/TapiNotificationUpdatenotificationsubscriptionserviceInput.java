package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationUpdatenotificationsubscriptionserviceInput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationUpdatenotificationsubscriptionserviceInput   {
  @JsonProperty("subscription-state")
  private TapiNotificationSubscriptionState subscriptionState = null;

  @JsonProperty("name")
  @Valid
  private List<TapiCommonNameAndValue> name = null;

  @JsonProperty("uuid")
  private String uuid = null;

  @JsonProperty("subscription-filter")
  private TapiNotificationSubscriptionFilter subscriptionFilter = null;

  public TapiNotificationUpdatenotificationsubscriptionserviceInput subscriptionState(TapiNotificationSubscriptionState subscriptionState) {
    this.subscriptionState = subscriptionState;
    return this;
  }

  /**
   * none
   * @return subscriptionState
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiNotificationSubscriptionState getSubscriptionState() {
    return subscriptionState;
  }

  public void setSubscriptionState(TapiNotificationSubscriptionState subscriptionState) {
    this.subscriptionState = subscriptionState;
  }

  public TapiNotificationUpdatenotificationsubscriptionserviceInput name(List<TapiCommonNameAndValue> name) {
    this.name = name;
    return this;
  }

  public TapiNotificationUpdatenotificationsubscriptionserviceInput addNameItem(TapiCommonNameAndValue nameItem) {
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

  public TapiNotificationUpdatenotificationsubscriptionserviceInput uuid(String uuid) {
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

  public TapiNotificationUpdatenotificationsubscriptionserviceInput subscriptionFilter(TapiNotificationSubscriptionFilter subscriptionFilter) {
    this.subscriptionFilter = subscriptionFilter;
    return this;
  }

  /**
   * none
   * @return subscriptionFilter
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiNotificationSubscriptionFilter getSubscriptionFilter() {
    return subscriptionFilter;
  }

  public void setSubscriptionFilter(TapiNotificationSubscriptionFilter subscriptionFilter) {
    this.subscriptionFilter = subscriptionFilter;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationUpdatenotificationsubscriptionserviceInput tapiNotificationUpdatenotificationsubscriptionserviceInput = (TapiNotificationUpdatenotificationsubscriptionserviceInput) o;
    return Objects.equals(this.subscriptionState, tapiNotificationUpdatenotificationsubscriptionserviceInput.subscriptionState) &&
        Objects.equals(this.name, tapiNotificationUpdatenotificationsubscriptionserviceInput.name) &&
        Objects.equals(this.uuid, tapiNotificationUpdatenotificationsubscriptionserviceInput.uuid) &&
        Objects.equals(this.subscriptionFilter, tapiNotificationUpdatenotificationsubscriptionserviceInput.subscriptionFilter);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscriptionState, name, uuid, subscriptionFilter);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationUpdatenotificationsubscriptionserviceInput {\n");
    
    sb.append("    subscriptionState: ").append(toIndentedString(subscriptionState)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    subscriptionFilter: ").append(toIndentedString(subscriptionFilter)).append("\n");
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

