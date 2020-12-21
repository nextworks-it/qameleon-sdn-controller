package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.common.CapacityUnit
 */
public enum TapiCommonCapacityUnit {
  
  TB("TB"),
  
  TBPS("TBPS"),
  
  GB("GB"),
  
  GBPS("GBPS"),
  
  MB("MB"),
  
  MBPS("MBPS"),
  
  KB("KB"),
  
  KBPS("KBPS"),
  
  GHZ("GHz"),
  
  MHZ("MHz");

  private String value;

  TapiCommonCapacityUnit(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiCommonCapacityUnit fromValue(String text) {
    for (TapiCommonCapacityUnit b : TapiCommonCapacityUnit.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

