package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationRoutingConstraint
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationRoutingConstraint   {
  @JsonProperty("is-exclusive")
  private Boolean isExclusive = true;

  @JsonProperty("max-allowed-cost")
  private TapiPathComputationValueOrPriority maxAllowedCost = null;

  @JsonProperty("tolerable-impact")
  private String tolerableImpact = null;

  @JsonProperty("diversity-policy")
  private TapiPathComputationDiversityPolicy diversityPolicy = null;

  @JsonProperty("route-objective-function")
  private TapiPathComputationRouteObjectiveFunction routeObjectiveFunction = null;

  @JsonProperty("cost-characteristic")
  @Valid
  private List<TapiTopologyCostCharacteristic> costCharacteristic = null;

  @JsonProperty("max-allowed-hops")
  private TapiPathComputationValueOrPriority maxAllowedHops = null;

  @JsonProperty("max-allowed-delay")
  private TapiPathComputationValueOrPriority maxAllowedDelay = null;

  @JsonProperty("latency-characteristic")
  @Valid
  private List<TapiTopologyLatencyCharacteristic> latencyCharacteristic = null;

  @JsonProperty("risk-diversity-characteristic")
  @Valid
  private List<TapiTopologyRiskCharacteristic> riskDiversityCharacteristic = null;

  public TapiPathComputationRoutingConstraint isExclusive(Boolean isExclusive) {
    this.isExclusive = isExclusive;
    return this;
  }

  /**
   * To distinguish if the resources are to be exclusive to the service
   * @return isExclusive
  **/
  @ApiModelProperty(value = "To distinguish if the resources are to be exclusive to the service")


  public Boolean isIsExclusive() {
    return isExclusive;
  }

  public void setIsExclusive(Boolean isExclusive) {
    this.isExclusive = isExclusive;
  }

  public TapiPathComputationRoutingConstraint maxAllowedCost(TapiPathComputationValueOrPriority maxAllowedCost) {
    this.maxAllowedCost = maxAllowedCost;
    return this;
  }

  /**
   * none
   * @return maxAllowedCost
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationValueOrPriority getMaxAllowedCost() {
    return maxAllowedCost;
  }

  public void setMaxAllowedCost(TapiPathComputationValueOrPriority maxAllowedCost) {
    this.maxAllowedCost = maxAllowedCost;
  }

  public TapiPathComputationRoutingConstraint tolerableImpact(String tolerableImpact) {
    this.tolerableImpact = tolerableImpact;
    return this;
  }

  /**
   * Grades of maximum tolerable disruption to traffic.
   * @return tolerableImpact
  **/
  @ApiModelProperty(value = "Grades of maximum tolerable disruption to traffic.")


  public String getTolerableImpact() {
    return tolerableImpact;
  }

  public void setTolerableImpact(String tolerableImpact) {
    this.tolerableImpact = tolerableImpact;
  }

  public TapiPathComputationRoutingConstraint diversityPolicy(TapiPathComputationDiversityPolicy diversityPolicy) {
    this.diversityPolicy = diversityPolicy;
    return this;
  }

  /**
   * none
   * @return diversityPolicy
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationDiversityPolicy getDiversityPolicy() {
    return diversityPolicy;
  }

  public void setDiversityPolicy(TapiPathComputationDiversityPolicy diversityPolicy) {
    this.diversityPolicy = diversityPolicy;
  }

  public TapiPathComputationRoutingConstraint routeObjectiveFunction(TapiPathComputationRouteObjectiveFunction routeObjectiveFunction) {
    this.routeObjectiveFunction = routeObjectiveFunction;
    return this;
  }

  /**
   * none
   * @return routeObjectiveFunction
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationRouteObjectiveFunction getRouteObjectiveFunction() {
    return routeObjectiveFunction;
  }

  public void setRouteObjectiveFunction(TapiPathComputationRouteObjectiveFunction routeObjectiveFunction) {
    this.routeObjectiveFunction = routeObjectiveFunction;
  }

  public TapiPathComputationRoutingConstraint costCharacteristic(List<TapiTopologyCostCharacteristic> costCharacteristic) {
    this.costCharacteristic = costCharacteristic;
    return this;
  }

  public TapiPathComputationRoutingConstraint addCostCharacteristicItem(TapiTopologyCostCharacteristic costCharacteristicItem) {
    if (this.costCharacteristic == null) {
      this.costCharacteristic = new ArrayList<TapiTopologyCostCharacteristic>();
    }
    this.costCharacteristic.add(costCharacteristicItem);
    return this;
  }

  /**
   * The list of costs where each cost relates to some aspect of the TopologicalEntity.
   * @return costCharacteristic
  **/
  @ApiModelProperty(value = "The list of costs where each cost relates to some aspect of the TopologicalEntity.")

  @Valid

  public List<TapiTopologyCostCharacteristic> getCostCharacteristic() {
    return costCharacteristic;
  }

  public void setCostCharacteristic(List<TapiTopologyCostCharacteristic> costCharacteristic) {
    this.costCharacteristic = costCharacteristic;
  }

  public TapiPathComputationRoutingConstraint maxAllowedHops(TapiPathComputationValueOrPriority maxAllowedHops) {
    this.maxAllowedHops = maxAllowedHops;
    return this;
  }

  /**
   * none
   * @return maxAllowedHops
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiPathComputationValueOrPriority getMaxAllowedHops() {
    return maxAllowedHops;
  }

  public void setMaxAllowedHops(TapiPathComputationValueOrPriority maxAllowedHops) {
    this.maxAllowedHops = maxAllowedHops;
  }

  public TapiPathComputationRoutingConstraint maxAllowedDelay(TapiPathComputationValueOrPriority maxAllowedDelay) {
    this.maxAllowedDelay = maxAllowedDelay;
    return this;
  }

  /**
   * Delay unit is microseconds.
   * @return maxAllowedDelay
  **/
  @ApiModelProperty(value = "Delay unit is microseconds.")

  @Valid

  public TapiPathComputationValueOrPriority getMaxAllowedDelay() {
    return maxAllowedDelay;
  }

  public void setMaxAllowedDelay(TapiPathComputationValueOrPriority maxAllowedDelay) {
    this.maxAllowedDelay = maxAllowedDelay;
  }

  public TapiPathComputationRoutingConstraint latencyCharacteristic(List<TapiTopologyLatencyCharacteristic> latencyCharacteristic) {
    this.latencyCharacteristic = latencyCharacteristic;
    return this;
  }

  public TapiPathComputationRoutingConstraint addLatencyCharacteristicItem(TapiTopologyLatencyCharacteristic latencyCharacteristicItem) {
    if (this.latencyCharacteristic == null) {
      this.latencyCharacteristic = new ArrayList<TapiTopologyLatencyCharacteristic>();
    }
    this.latencyCharacteristic.add(latencyCharacteristicItem);
    return this;
  }

  /**
   * The effect on the latency of a queuing process. This only has significant effect for packet based systems and has a complex characteristic.
   * @return latencyCharacteristic
  **/
  @ApiModelProperty(value = "The effect on the latency of a queuing process. This only has significant effect for packet based systems and has a complex characteristic.")

  @Valid

  public List<TapiTopologyLatencyCharacteristic> getLatencyCharacteristic() {
    return latencyCharacteristic;
  }

  public void setLatencyCharacteristic(List<TapiTopologyLatencyCharacteristic> latencyCharacteristic) {
    this.latencyCharacteristic = latencyCharacteristic;
  }

  public TapiPathComputationRoutingConstraint riskDiversityCharacteristic(List<TapiTopologyRiskCharacteristic> riskDiversityCharacteristic) {
    this.riskDiversityCharacteristic = riskDiversityCharacteristic;
    return this;
  }

  public TapiPathComputationRoutingConstraint addRiskDiversityCharacteristicItem(TapiTopologyRiskCharacteristic riskDiversityCharacteristicItem) {
    if (this.riskDiversityCharacteristic == null) {
      this.riskDiversityCharacteristic = new ArrayList<TapiTopologyRiskCharacteristic>();
    }
    this.riskDiversityCharacteristic.add(riskDiversityCharacteristicItem);
    return this;
  }

  /**
   * none
   * @return riskDiversityCharacteristic
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiTopologyRiskCharacteristic> getRiskDiversityCharacteristic() {
    return riskDiversityCharacteristic;
  }

  public void setRiskDiversityCharacteristic(List<TapiTopologyRiskCharacteristic> riskDiversityCharacteristic) {
    this.riskDiversityCharacteristic = riskDiversityCharacteristic;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationRoutingConstraint tapiPathComputationRoutingConstraint = (TapiPathComputationRoutingConstraint) o;
    return Objects.equals(this.isExclusive, tapiPathComputationRoutingConstraint.isExclusive) &&
        Objects.equals(this.maxAllowedCost, tapiPathComputationRoutingConstraint.maxAllowedCost) &&
        Objects.equals(this.tolerableImpact, tapiPathComputationRoutingConstraint.tolerableImpact) &&
        Objects.equals(this.diversityPolicy, tapiPathComputationRoutingConstraint.diversityPolicy) &&
        Objects.equals(this.routeObjectiveFunction, tapiPathComputationRoutingConstraint.routeObjectiveFunction) &&
        Objects.equals(this.costCharacteristic, tapiPathComputationRoutingConstraint.costCharacteristic) &&
        Objects.equals(this.maxAllowedHops, tapiPathComputationRoutingConstraint.maxAllowedHops) &&
        Objects.equals(this.maxAllowedDelay, tapiPathComputationRoutingConstraint.maxAllowedDelay) &&
        Objects.equals(this.latencyCharacteristic, tapiPathComputationRoutingConstraint.latencyCharacteristic) &&
        Objects.equals(this.riskDiversityCharacteristic, tapiPathComputationRoutingConstraint.riskDiversityCharacteristic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isExclusive, maxAllowedCost, tolerableImpact, diversityPolicy, routeObjectiveFunction, costCharacteristic, maxAllowedHops, maxAllowedDelay, latencyCharacteristic, riskDiversityCharacteristic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationRoutingConstraint {\n");
    
    sb.append("    isExclusive: ").append(toIndentedString(isExclusive)).append("\n");
    sb.append("    maxAllowedCost: ").append(toIndentedString(maxAllowedCost)).append("\n");
    sb.append("    tolerableImpact: ").append(toIndentedString(tolerableImpact)).append("\n");
    sb.append("    diversityPolicy: ").append(toIndentedString(diversityPolicy)).append("\n");
    sb.append("    routeObjectiveFunction: ").append(toIndentedString(routeObjectiveFunction)).append("\n");
    sb.append("    costCharacteristic: ").append(toIndentedString(costCharacteristic)).append("\n");
    sb.append("    maxAllowedHops: ").append(toIndentedString(maxAllowedHops)).append("\n");
    sb.append("    maxAllowedDelay: ").append(toIndentedString(maxAllowedDelay)).append("\n");
    sb.append("    latencyCharacteristic: ").append(toIndentedString(latencyCharacteristic)).append("\n");
    sb.append("    riskDiversityCharacteristic: ").append(toIndentedString(riskDiversityCharacteristic)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

