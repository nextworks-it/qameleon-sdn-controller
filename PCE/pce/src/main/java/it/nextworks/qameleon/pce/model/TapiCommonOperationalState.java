package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.common.OperationalState
 */
public enum TapiCommonOperationalState {
  
  DISABLED("DISABLED"),
  
  ENABLED("ENABLED");

  private String value;

  TapiCommonOperationalState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiCommonOperationalState fromValue(String text) {
    for (TapiCommonOperationalState b : TapiCommonOperationalState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

