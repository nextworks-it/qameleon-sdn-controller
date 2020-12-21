package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.common.TerminationState
 */
public enum TapiCommonTerminationState {
  
  LP_CAN_NEVER_TERMINATE("LP_CAN_NEVER_TERMINATE"),
  
  LT_NOT_TERMINATED("LT_NOT_TERMINATED"),
  
  TERMINATED_SERVER_TO_CLIENT_FLOW("TERMINATED_SERVER_TO_CLIENT_FLOW"),
  
  TERMINATED_CLIENT_TO_SERVER_FLOW("TERMINATED_CLIENT_TO_SERVER_FLOW"),
  
  TERMINATED_BIDIRECTIONAL("TERMINATED_BIDIRECTIONAL"),
  
  LT_PERMENANTLY_TERMINATED("LT_PERMENANTLY_TERMINATED"),
  
  TERMINATION_STATE_UNKNOWN("TERMINATION_STATE_UNKNOWN");

  private String value;

  TapiCommonTerminationState(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiCommonTerminationState fromValue(String text) {
    for (TapiCommonTerminationState b : TapiCommonTerminationState.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

