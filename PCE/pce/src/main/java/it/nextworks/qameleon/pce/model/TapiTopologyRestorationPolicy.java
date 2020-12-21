package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.topology.RestorationPolicy
 */
public enum TapiTopologyRestorationPolicy {
  
  PER_DOMAIN_RESTORATION("PER_DOMAIN_RESTORATION"),
  
  END_TO_END_RESTORATION("END_TO_END_RESTORATION"),
  
  NA("NA");

  private String value;

  TapiTopologyRestorationPolicy(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiTopologyRestorationPolicy fromValue(String text) {
    for (TapiTopologyRestorationPolicy b : TapiTopologyRestorationPolicy.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

