package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.common.AdministrativeState
 */
public enum TapiCommonAdministrativeState {
  
  LOCKED("LOCKED"),
  
  UNLOCKED("UNLOCKED");

  private String value;

  TapiCommonAdministrativeState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiCommonAdministrativeState fromValue(String text) {
    for (TapiCommonAdministrativeState b : TapiCommonAdministrativeState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

