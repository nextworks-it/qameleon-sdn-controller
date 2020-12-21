package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationSubscriptionFilter
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationSubscriptionFilter extends TapiCommonLocalClass  {
  @JsonProperty("requested-notification-types")
  @Valid
  private List<String> requestedNotificationTypes = null;

  @JsonProperty("requested-object-identifier")
  @Valid
  private List<String> requestedObjectIdentifier = null;

  @JsonProperty("requested-layer-protocols")
  @Valid
  private List<TapiCommonLayerProtocolName> requestedLayerProtocols = null;

  @JsonProperty("include-content")
  private Boolean includeContent = false;

  @JsonProperty("requested-object-types")
  @Valid
  private List<String> requestedObjectTypes = null;

  public TapiNotificationSubscriptionFilter requestedNotificationTypes(List<String> requestedNotificationTypes) {
    this.requestedNotificationTypes = requestedNotificationTypes;
    return this;
  }

  public TapiNotificationSubscriptionFilter addRequestedNotificationTypesItem(String requestedNotificationTypesItem) {
    if (this.requestedNotificationTypes == null) {
      this.requestedNotificationTypes = new ArrayList<String>();
    }
    this.requestedNotificationTypes.add(requestedNotificationTypesItem);
    return this;
  }

  /**
   * none
   * @return requestedNotificationTypes
  **/
  @ApiModelProperty(value = "none")


  public List<String> getRequestedNotificationTypes() {
    return requestedNotificationTypes;
  }

  public void setRequestedNotificationTypes(List<String> requestedNotificationTypes) {
    this.requestedNotificationTypes = requestedNotificationTypes;
  }

  public TapiNotificationSubscriptionFilter requestedObjectIdentifier(List<String> requestedObjectIdentifier) {
    this.requestedObjectIdentifier = requestedObjectIdentifier;
    return this;
  }

  public TapiNotificationSubscriptionFilter addRequestedObjectIdentifierItem(String requestedObjectIdentifierItem) {
    if (this.requestedObjectIdentifier == null) {
      this.requestedObjectIdentifier = new ArrayList<String>();
    }
    this.requestedObjectIdentifier.add(requestedObjectIdentifierItem);
    return this;
  }

  /**
   * none
   * @return requestedObjectIdentifier
  **/
  @ApiModelProperty(value = "none")


  public List<String> getRequestedObjectIdentifier() {
    return requestedObjectIdentifier;
  }

  public void setRequestedObjectIdentifier(List<String> requestedObjectIdentifier) {
    this.requestedObjectIdentifier = requestedObjectIdentifier;
  }

  public TapiNotificationSubscriptionFilter requestedLayerProtocols(List<TapiCommonLayerProtocolName> requestedLayerProtocols) {
    this.requestedLayerProtocols = requestedLayerProtocols;
    return this;
  }

  public TapiNotificationSubscriptionFilter addRequestedLayerProtocolsItem(TapiCommonLayerProtocolName requestedLayerProtocolsItem) {
    if (this.requestedLayerProtocols == null) {
      this.requestedLayerProtocols = new ArrayList<TapiCommonLayerProtocolName>();
    }
    this.requestedLayerProtocols.add(requestedLayerProtocolsItem);
    return this;
  }

  /**
   * none
   * @return requestedLayerProtocols
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiCommonLayerProtocolName> getRequestedLayerProtocols() {
    return requestedLayerProtocols;
  }

  public void setRequestedLayerProtocols(List<TapiCommonLayerProtocolName> requestedLayerProtocols) {
    this.requestedLayerProtocols = requestedLayerProtocols;
  }

  public TapiNotificationSubscriptionFilter includeContent(Boolean includeContent) {
    this.includeContent = includeContent;
    return this;
  }

  /**
   * Indicates whether the published Notification includes content or just the Notification Id (which enables retrieval of the notification at the later stage)
   * @return includeContent
  **/
  @ApiModelProperty(value = "Indicates whether the published Notification includes content or just the Notification Id (which enables retrieval of the notification at the later stage)")


  public Boolean isIncludeContent() {
    return includeContent;
  }

  public void setIncludeContent(Boolean includeContent) {
    this.includeContent = includeContent;
  }

  public TapiNotificationSubscriptionFilter requestedObjectTypes(List<String> requestedObjectTypes) {
    this.requestedObjectTypes = requestedObjectTypes;
    return this;
  }

  public TapiNotificationSubscriptionFilter addRequestedObjectTypesItem(String requestedObjectTypesItem) {
    if (this.requestedObjectTypes == null) {
      this.requestedObjectTypes = new ArrayList<String>();
    }
    this.requestedObjectTypes.add(requestedObjectTypesItem);
    return this;
  }

  /**
   * none
   * @return requestedObjectTypes
  **/
  @ApiModelProperty(value = "none")


  public List<String> getRequestedObjectTypes() {
    return requestedObjectTypes;
  }

  public void setRequestedObjectTypes(List<String> requestedObjectTypes) {
    this.requestedObjectTypes = requestedObjectTypes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationSubscriptionFilter tapiNotificationSubscriptionFilter = (TapiNotificationSubscriptionFilter) o;
    return Objects.equals(this.requestedNotificationTypes, tapiNotificationSubscriptionFilter.requestedNotificationTypes) &&
        Objects.equals(this.requestedObjectIdentifier, tapiNotificationSubscriptionFilter.requestedObjectIdentifier) &&
        Objects.equals(this.requestedLayerProtocols, tapiNotificationSubscriptionFilter.requestedLayerProtocols) &&
        Objects.equals(this.includeContent, tapiNotificationSubscriptionFilter.includeContent) &&
        Objects.equals(this.requestedObjectTypes, tapiNotificationSubscriptionFilter.requestedObjectTypes) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestedNotificationTypes, requestedObjectIdentifier, requestedLayerProtocols, includeContent, requestedObjectTypes, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationSubscriptionFilter {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    requestedNotificationTypes: ").append(toIndentedString(requestedNotificationTypes)).append("\n");
    sb.append("    requestedObjectIdentifier: ").append(toIndentedString(requestedObjectIdentifier)).append("\n");
    sb.append("    requestedLayerProtocols: ").append(toIndentedString(requestedLayerProtocols)).append("\n");
    sb.append("    includeContent: ").append(toIndentedString(includeContent)).append("\n");
    sb.append("    requestedObjectTypes: ").append(toIndentedString(requestedObjectTypes)).append("\n");
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

