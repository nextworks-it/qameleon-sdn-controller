package it.nextworks.qameleon.pce.model;

import com.fasterxml.jackson.annotation.JsonValue;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets tapi.path.computation.RouteObjectiveFunction
 */
public enum TapiPathComputationRouteObjectiveFunction {
  
  MIN_WORK_ROUTE_HOP("MIN_WORK_ROUTE_HOP"),
  
  MIN_WORK_ROUTE_COST("MIN_WORK_ROUTE_COST"),
  
  MIN_WORK_ROUTE_LATENCY("MIN_WORK_ROUTE_LATENCY"),
  
  MIN_SUM_OF_WORK_AND_PROTECTION_ROUTE_HOP("MIN_SUM_OF_WORK_AND_PROTECTION_ROUTE_HOP"),
  
  MIN_SUM_OF_WORK_AND_PROTECTION_ROUTE_COST("MIN_SUM_OF_WORK_AND_PROTECTION_ROUTE_COST"),
  
  MIN_SUM_OF_WORK_AND_PROTECTION_ROUTE_LATENCY("MIN_SUM_OF_WORK_AND_PROTECTION_ROUTE_LATENCY"),
  
  LOAD_BALANCE_MAX_UNUSED_CAPACITY("LOAD_BALANCE_MAX_UNUSED_CAPACITY");

  private String value;

  TapiPathComputationRouteObjectiveFunction(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static TapiPathComputationRouteObjectiveFunction fromValue(String text) {
    for (TapiPathComputationRouteObjectiveFunction b : TapiPathComputationRouteObjectiveFunction.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}

