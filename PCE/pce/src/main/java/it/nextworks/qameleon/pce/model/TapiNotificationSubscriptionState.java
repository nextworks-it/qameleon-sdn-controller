package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.notification.SubscriptionState
 */
public enum TapiNotificationSubscriptionState {
  
  SUSPENDED("SUSPENDED"),
  
  ACTIVE("ACTIVE");

  private String value;

  TapiNotificationSubscriptionState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiNotificationSubscriptionState fromValue(String text) {
    for (TapiNotificationSubscriptionState b : TapiNotificationSubscriptionState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

