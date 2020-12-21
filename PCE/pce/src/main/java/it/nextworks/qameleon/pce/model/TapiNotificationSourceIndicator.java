package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.notification.SourceIndicator
 */
public enum TapiNotificationSourceIndicator {
  
  RESOURCE_OPERATION("RESOURCE_OPERATION"),
  
  MANAGEMENT_OPERATION("MANAGEMENT_OPERATION"),
  
  UNKNOWN("UNKNOWN");

  private String value;

  TapiNotificationSourceIndicator(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiNotificationSourceIndicator fromValue(String text) {
    for (TapiNotificationSourceIndicator b : TapiNotificationSourceIndicator.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

