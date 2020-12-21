package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.common.LifecycleState
 */
public enum TapiCommonLifecycleState {
  
  PLANNED("PLANNED"),
  
  POTENTIAL_AVAILABLE("POTENTIAL_AVAILABLE"),
  
  POTENTIAL_BUSY("POTENTIAL_BUSY"),
  
  INSTALLED("INSTALLED"),
  
  PENDING_REMOVAL("PENDING_REMOVAL");

  private String value;

  TapiCommonLifecycleState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiCommonLifecycleState fromValue(String text) {
    for (TapiCommonLifecycleState b : TapiCommonLifecycleState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

