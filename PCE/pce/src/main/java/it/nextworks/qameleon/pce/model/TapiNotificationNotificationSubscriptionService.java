package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationNotificationSubscriptionService
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationNotificationSubscriptionService extends TapiCommonGlobalClass  {
  @JsonProperty("notification")
  @Valid
  private List<TapiNotificationNotification> notification = null;

  @JsonProperty("notification-channel")
  private TapiNotificationNotificationChannel notificationChannel = null;

  @JsonProperty("subscription-state")
  private TapiNotificationSubscriptionState subscriptionState = null;

  @JsonProperty("supported-object-types")
  @Valid
  private List<String> supportedObjectTypes = null;

  @JsonProperty("supported-notification-types")
  @Valid
  private List<String> supportedNotificationTypes = null;

  @JsonProperty("subscription-filter")
  private TapiNotificationSubscriptionFilter subscriptionFilter = null;

  public TapiNotificationNotificationSubscriptionService notification(List<TapiNotificationNotification> notification) {
    this.notification = notification;
    return this;
  }

  public TapiNotificationNotificationSubscriptionService addNotificationItem(TapiNotificationNotification notificationItem) {
    if (this.notification == null) {
      this.notification = new ArrayList<TapiNotificationNotification>();
    }
    this.notification.add(notificationItem);
    return this;
  }

  /**
   * none
   * @return notification
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiNotificationNotification> getNotification() {
    return notification;
  }

  public void setNotification(List<TapiNotificationNotification> notification) {
    this.notification = notification;
  }

  public TapiNotificationNotificationSubscriptionService notificationChannel(TapiNotificationNotificationChannel notificationChannel) {
    this.notificationChannel = notificationChannel;
    return this;
  }

  /**
   * none
   * @return notificationChannel
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiNotificationNotificationChannel getNotificationChannel() {
    return notificationChannel;
  }

  public void setNotificationChannel(TapiNotificationNotificationChannel notificationChannel) {
    this.notificationChannel = notificationChannel;
  }

  public TapiNotificationNotificationSubscriptionService subscriptionState(TapiNotificationSubscriptionState subscriptionState) {
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

  public TapiNotificationNotificationSubscriptionService supportedObjectTypes(List<String> supportedObjectTypes) {
    this.supportedObjectTypes = supportedObjectTypes;
    return this;
  }

  public TapiNotificationNotificationSubscriptionService addSupportedObjectTypesItem(String supportedObjectTypesItem) {
    if (this.supportedObjectTypes == null) {
      this.supportedObjectTypes = new ArrayList<String>();
    }
    this.supportedObjectTypes.add(supportedObjectTypesItem);
    return this;
  }

  /**
   * none
   * @return supportedObjectTypes
  **/
  @ApiModelProperty(value = "none")


  public List<String> getSupportedObjectTypes() {
    return supportedObjectTypes;
  }

  public void setSupportedObjectTypes(List<String> supportedObjectTypes) {
    this.supportedObjectTypes = supportedObjectTypes;
  }

  public TapiNotificationNotificationSubscriptionService supportedNotificationTypes(List<String> supportedNotificationTypes) {
    this.supportedNotificationTypes = supportedNotificationTypes;
    return this;
  }

  public TapiNotificationNotificationSubscriptionService addSupportedNotificationTypesItem(String supportedNotificationTypesItem) {
    if (this.supportedNotificationTypes == null) {
      this.supportedNotificationTypes = new ArrayList<String>();
    }
    this.supportedNotificationTypes.add(supportedNotificationTypesItem);
    return this;
  }

  /**
   * none
   * @return supportedNotificationTypes
  **/
  @ApiModelProperty(value = "none")


  public List<String> getSupportedNotificationTypes() {
    return supportedNotificationTypes;
  }

  public void setSupportedNotificationTypes(List<String> supportedNotificationTypes) {
    this.supportedNotificationTypes = supportedNotificationTypes;
  }

  public TapiNotificationNotificationSubscriptionService subscriptionFilter(TapiNotificationSubscriptionFilter subscriptionFilter) {
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
    TapiNotificationNotificationSubscriptionService tapiNotificationNotificationSubscriptionService = (TapiNotificationNotificationSubscriptionService) o;
    return Objects.equals(this.notification, tapiNotificationNotificationSubscriptionService.notification) &&
        Objects.equals(this.notificationChannel, tapiNotificationNotificationSubscriptionService.notificationChannel) &&
        Objects.equals(this.subscriptionState, tapiNotificationNotificationSubscriptionService.subscriptionState) &&
        Objects.equals(this.supportedObjectTypes, tapiNotificationNotificationSubscriptionService.supportedObjectTypes) &&
        Objects.equals(this.supportedNotificationTypes, tapiNotificationNotificationSubscriptionService.supportedNotificationTypes) &&
        Objects.equals(this.subscriptionFilter, tapiNotificationNotificationSubscriptionService.subscriptionFilter) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(notification, notificationChannel, subscriptionState, supportedObjectTypes, supportedNotificationTypes, subscriptionFilter, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationNotificationSubscriptionService {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    notification: ").append(toIndentedString(notification)).append("\n");
    sb.append("    notificationChannel: ").append(toIndentedString(notificationChannel)).append("\n");
    sb.append("    subscriptionState: ").append(toIndentedString(subscriptionState)).append("\n");
    sb.append("    supportedObjectTypes: ").append(toIndentedString(supportedObjectTypes)).append("\n");
    sb.append("    supportedNotificationTypes: ").append(toIndentedString(supportedNotificationTypes)).append("\n");
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

