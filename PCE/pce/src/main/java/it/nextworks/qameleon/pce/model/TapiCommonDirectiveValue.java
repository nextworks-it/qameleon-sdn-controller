package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.common.DirectiveValue
 */
public enum TapiCommonDirectiveValue {
  
  MINIMIZE("MINIMIZE"),
  
  MAXIMIZE("MAXIMIZE"),
  
  ALLOW("ALLOW"),
  
  DISALLOW("DISALLOW"),
  
  DONT_CARE("DONT_CARE");

  private String value;

  TapiCommonDirectiveValue(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiCommonDirectiveValue fromValue(String text) {
    for (TapiCommonDirectiveValue b : TapiCommonDirectiveValue.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

