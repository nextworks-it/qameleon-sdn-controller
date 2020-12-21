package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.topology.RuleType
 */
public enum TapiTopologyRuleType {
  
  FORWARDING("FORWARDING"),
  
  CAPACITY("CAPACITY"),
  
  COST("COST"),
  
  TIMING("TIMING"),
  
  RISK("RISK"),
  
  GROUPING("GROUPING");

  private String value;

  TapiTopologyRuleType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiTopologyRuleType fromValue(String text) {
    for (TapiTopologyRuleType b : TapiTopologyRuleType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

