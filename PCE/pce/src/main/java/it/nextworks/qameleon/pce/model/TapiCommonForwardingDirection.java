package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.common.ForwardingDirection
 */
public enum TapiCommonForwardingDirection {
  
  BIDIRECTIONAL("BIDIRECTIONAL"),
  
  UNIDIRECTIONAL("UNIDIRECTIONAL"),
  
  UNDEFINED_OR_UNKNOWN("UNDEFINED_OR_UNKNOWN");

  private String value;

  TapiCommonForwardingDirection(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiCommonForwardingDirection fromValue(String text) {
    for (TapiCommonForwardingDirection b : TapiCommonForwardingDirection.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

