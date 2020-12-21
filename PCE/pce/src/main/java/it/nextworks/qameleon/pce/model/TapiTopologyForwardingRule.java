package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.topology.ForwardingRule
 */
public enum TapiTopologyForwardingRule {
  
  MAY_FORWARD_ACROSS_GROUP("MAY_FORWARD_ACROSS_GROUP"),
  
  MUST_FORWARD_ACROSS_GROUP("MUST_FORWARD_ACROSS_GROUP"),
  
  CANNOT_FORWARD_ACROSS_GROUP("CANNOT_FORWARD_ACROSS_GROUP"),
  
  NO_STATEMENT_ON_FORWARDING("NO_STATEMENT_ON_FORWARDING"),
  
  INTER_CONNECTION_CONTENTION("INTER_CONNECTION_CONTENTION");

  private String value;

  TapiTopologyForwardingRule(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiTopologyForwardingRule fromValue(String text) {
    for (TapiTopologyForwardingRule b : TapiTopologyForwardingRule.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

