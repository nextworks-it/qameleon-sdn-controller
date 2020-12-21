package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationNotification
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationNotification extends TapiCommonGlobalClass  {
  @JsonProperty("target-object-type")
  private String targetObjectType = null;

  @JsonProperty("additional-text")
  private String additionalText = null;

  @JsonProperty("event-time-stamp")
  private String eventTimeStamp = null;

  @JsonProperty("additional-info")
  @Valid
  private List<TapiCommonNameAndValue> additionalInfo = null;

  @JsonProperty("sequence-number")
  private Integer sequenceNumber = null;

  @JsonProperty("target-object-identifier")
  private String targetObjectIdentifier = null;

  @JsonProperty("notification-type")
  private String notificationType = null;

  @JsonProperty("target-object-name")
  @Valid
  private List<TapiCommonNameAndValue> targetObjectName = null;

  @JsonProperty("layer-protocol-name")
  private TapiCommonLayerProtocolName layerProtocolName = null;

  @JsonProperty("source-indicator")
  private TapiNotificationSourceIndicator sourceIndicator = null;

  @JsonProperty("layer-protocol-qualifier")
  private String layerProtocolQualifier = null;

  @JsonProperty("changed-attributes")
  @Valid
  private List<TapiNotificationNameAndValueChange> changedAttributes = null;

  public TapiNotificationNotification targetObjectType(String targetObjectType) {
    this.targetObjectType = targetObjectType;
    return this;
  }

  /**
   * none
   * @return targetObjectType
  **/
  @ApiModelProperty(value = "none")


  public String getTargetObjectType() {
    return targetObjectType;
  }

  public void setTargetObjectType(String targetObjectType) {
    this.targetObjectType = targetObjectType;
  }

  public TapiNotificationNotification additionalText(String additionalText) {
    this.additionalText = additionalText;
    return this;
  }

  /**
   * none
   * @return additionalText
  **/
  @ApiModelProperty(value = "none")


  public String getAdditionalText() {
    return additionalText;
  }

  public void setAdditionalText(String additionalText) {
    this.additionalText = additionalText;
  }

  public TapiNotificationNotification eventTimeStamp(String eventTimeStamp) {
    this.eventTimeStamp = eventTimeStamp;
    return this;
  }

  /**
   * none
   * @return eventTimeStamp
  **/
  @ApiModelProperty(value = "none")


  public String getEventTimeStamp() {
    return eventTimeStamp;
  }

  public void setEventTimeStamp(String eventTimeStamp) {
    this.eventTimeStamp = eventTimeStamp;
  }

  public TapiNotificationNotification additionalInfo(List<TapiCommonNameAndValue> additionalInfo) {
    this.additionalInfo = additionalInfo;
    return this;
  }

  public TapiNotificationNotification addAdditionalInfoItem(TapiCommonNameAndValue additionalInfoItem) {
    if (this.additionalInfo == null) {
      this.additionalInfo = new ArrayList<TapiCommonNameAndValue>();
    }
    this.additionalInfo.add(additionalInfoItem);
    return this;
  }

  /**
   * none
   * @return additionalInfo
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiCommonNameAndValue> getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(List<TapiCommonNameAndValue> additionalInfo) {
    this.additionalInfo = additionalInfo;
  }

  public TapiNotificationNotification sequenceNumber(Integer sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
    return this;
  }

  /**
   * A monotonous increasing sequence number associated with the notification.                  The exact semantics of how this sequence number is assigned (per channel or subscription or source or system) is left undefined.
   * @return sequenceNumber
  **/
  @ApiModelProperty(value = "A monotonous increasing sequence number associated with the notification.                  The exact semantics of how this sequence number is assigned (per channel or subscription or source or system) is left undefined.")


  public Integer getSequenceNumber() {
    return sequenceNumber;
  }

  public void setSequenceNumber(Integer sequenceNumber) {
    this.sequenceNumber = sequenceNumber;
  }

  public TapiNotificationNotification targetObjectIdentifier(String targetObjectIdentifier) {
    this.targetObjectIdentifier = targetObjectIdentifier;
    return this;
  }

  /**
   * none
   * @return targetObjectIdentifier
  **/
  @ApiModelProperty(value = "none")


  public String getTargetObjectIdentifier() {
    return targetObjectIdentifier;
  }

  public void setTargetObjectIdentifier(String targetObjectIdentifier) {
    this.targetObjectIdentifier = targetObjectIdentifier;
  }

  public TapiNotificationNotification notificationType(String notificationType) {
    this.notificationType = notificationType;
    return this;
  }

  /**
   * none
   * @return notificationType
  **/
  @ApiModelProperty(value = "none")


  public String getNotificationType() {
    return notificationType;
  }

  public void setNotificationType(String notificationType) {
    this.notificationType = notificationType;
  }

  public TapiNotificationNotification targetObjectName(List<TapiCommonNameAndValue> targetObjectName) {
    this.targetObjectName = targetObjectName;
    return this;
  }

  public TapiNotificationNotification addTargetObjectNameItem(TapiCommonNameAndValue targetObjectNameItem) {
    if (this.targetObjectName == null) {
      this.targetObjectName = new ArrayList<TapiCommonNameAndValue>();
    }
    this.targetObjectName.add(targetObjectNameItem);
    return this;
  }

  /**
   * none
   * @return targetObjectName
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiCommonNameAndValue> getTargetObjectName() {
    return targetObjectName;
  }

  public void setTargetObjectName(List<TapiCommonNameAndValue> targetObjectName) {
    this.targetObjectName = targetObjectName;
  }

  public TapiNotificationNotification layerProtocolName(TapiCommonLayerProtocolName layerProtocolName) {
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

  public TapiNotificationNotification sourceIndicator(TapiNotificationSourceIndicator sourceIndicator) {
    this.sourceIndicator = sourceIndicator;
    return this;
  }

  /**
   * none
   * @return sourceIndicator
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiNotificationSourceIndicator getSourceIndicator() {
    return sourceIndicator;
  }

  public void setSourceIndicator(TapiNotificationSourceIndicator sourceIndicator) {
    this.sourceIndicator = sourceIndicator;
  }

  public TapiNotificationNotification layerProtocolQualifier(String layerProtocolQualifier) {
    this.layerProtocolQualifier = layerProtocolQualifier;
    return this;
  }

  /**
   * none
   * @return layerProtocolQualifier
  **/
  @ApiModelProperty(value = "none")


  public String getLayerProtocolQualifier() {
    return layerProtocolQualifier;
  }

  public void setLayerProtocolQualifier(String layerProtocolQualifier) {
    this.layerProtocolQualifier = layerProtocolQualifier;
  }

  public TapiNotificationNotification changedAttributes(List<TapiNotificationNameAndValueChange> changedAttributes) {
    this.changedAttributes = changedAttributes;
    return this;
  }

  public TapiNotificationNotification addChangedAttributesItem(TapiNotificationNameAndValueChange changedAttributesItem) {
    if (this.changedAttributes == null) {
      this.changedAttributes = new ArrayList<TapiNotificationNameAndValueChange>();
    }
    this.changedAttributes.add(changedAttributesItem);
    return this;
  }

  /**
   * none
   * @return changedAttributes
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiNotificationNameAndValueChange> getChangedAttributes() {
    return changedAttributes;
  }

  public void setChangedAttributes(List<TapiNotificationNameAndValueChange> changedAttributes) {
    this.changedAttributes = changedAttributes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationNotification tapiNotificationNotification = (TapiNotificationNotification) o;
    return Objects.equals(this.targetObjectType, tapiNotificationNotification.targetObjectType) &&
        Objects.equals(this.additionalText, tapiNotificationNotification.additionalText) &&
        Objects.equals(this.eventTimeStamp, tapiNotificationNotification.eventTimeStamp) &&
        Objects.equals(this.additionalInfo, tapiNotificationNotification.additionalInfo) &&
        Objects.equals(this.sequenceNumber, tapiNotificationNotification.sequenceNumber) &&
        Objects.equals(this.targetObjectIdentifier, tapiNotificationNotification.targetObjectIdentifier) &&
        Objects.equals(this.notificationType, tapiNotificationNotification.notificationType) &&
        Objects.equals(this.targetObjectName, tapiNotificationNotification.targetObjectName) &&
        Objects.equals(this.layerProtocolName, tapiNotificationNotification.layerProtocolName) &&
        Objects.equals(this.sourceIndicator, tapiNotificationNotification.sourceIndicator) &&
        Objects.equals(this.layerProtocolQualifier, tapiNotificationNotification.layerProtocolQualifier) &&
        Objects.equals(this.changedAttributes, tapiNotificationNotification.changedAttributes) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(targetObjectType, additionalText, eventTimeStamp, additionalInfo, sequenceNumber, targetObjectIdentifier, notificationType, targetObjectName, layerProtocolName, sourceIndicator, layerProtocolQualifier, changedAttributes, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationNotification {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    targetObjectType: ").append(toIndentedString(targetObjectType)).append("\n");
    sb.append("    additionalText: ").append(toIndentedString(additionalText)).append("\n");
    sb.append("    eventTimeStamp: ").append(toIndentedString(eventTimeStamp)).append("\n");
    sb.append("    additionalInfo: ").append(toIndentedString(additionalInfo)).append("\n");
    sb.append("    sequenceNumber: ").append(toIndentedString(sequenceNumber)).append("\n");
    sb.append("    targetObjectIdentifier: ").append(toIndentedString(targetObjectIdentifier)).append("\n");
    sb.append("    notificationType: ").append(toIndentedString(notificationType)).append("\n");
    sb.append("    targetObjectName: ").append(toIndentedString(targetObjectName)).append("\n");
    sb.append("    layerProtocolName: ").append(toIndentedString(layerProtocolName)).append("\n");
    sb.append("    sourceIndicator: ").append(toIndentedString(sourceIndicator)).append("\n");
    sb.append("    layerProtocolQualifier: ").append(toIndentedString(layerProtocolQualifier)).append("\n");
    sb.append("    changedAttributes: ").append(toIndentedString(changedAttributes)).append("\n");
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

