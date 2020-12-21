package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathObjectiveFunction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathObjectiveFunction extends TapiCommonLocalClass  {
  @JsonProperty("link-utilization")
  private TapiCommonDirectiveValue linkUtilization = null;

  @JsonProperty("bandwidth-optimization")
  private TapiCommonDirectiveValue bandwidthOptimization = null;

  @JsonProperty("cost-optimization")
  private TapiCommonDirectiveValue costOptimization = null;

  @JsonProperty("resource-sharing")
  private TapiCommonDirectiveValue resourceSharing = null;

  @JsonProperty("concurrent-paths")
  private TapiCommonDirectiveValue concurrentPaths = null;

  public TapiPathComputationPathObjectiveFunction linkUtilization(TapiCommonDirectiveValue linkUtilization) {
    this.linkUtilization = linkUtilization;
    return this;
  }

  /**
   * none
   * @return linkUtilization
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonDirectiveValue getLinkUtilization() {
    return linkUtilization;
  }

  public void setLinkUtilization(TapiCommonDirectiveValue linkUtilization) {
    this.linkUtilization = linkUtilization;
  }

  public TapiPathComputationPathObjectiveFunction bandwidthOptimization(TapiCommonDirectiveValue bandwidthOptimization) {
    this.bandwidthOptimization = bandwidthOptimization;
    return this;
  }

  /**
   * none
   * @return bandwidthOptimization
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonDirectiveValue getBandwidthOptimization() {
    return bandwidthOptimization;
  }

  public void setBandwidthOptimization(TapiCommonDirectiveValue bandwidthOptimization) {
    this.bandwidthOptimization = bandwidthOptimization;
  }

  public TapiPathComputationPathObjectiveFunction costOptimization(TapiCommonDirectiveValue costOptimization) {
    this.costOptimization = costOptimization;
    return this;
  }

  /**
   * none
   * @return costOptimization
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonDirectiveValue getCostOptimization() {
    return costOptimization;
  }

  public void setCostOptimization(TapiCommonDirectiveValue costOptimization) {
    this.costOptimization = costOptimization;
  }

  public TapiPathComputationPathObjectiveFunction resourceSharing(TapiCommonDirectiveValue resourceSharing) {
    this.resourceSharing = resourceSharing;
    return this;
  }

  /**
   * none
   * @return resourceSharing
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonDirectiveValue getResourceSharing() {
    return resourceSharing;
  }

  public void setResourceSharing(TapiCommonDirectiveValue resourceSharing) {
    this.resourceSharing = resourceSharing;
  }

  public TapiPathComputationPathObjectiveFunction concurrentPaths(TapiCommonDirectiveValue concurrentPaths) {
    this.concurrentPaths = concurrentPaths;
    return this;
  }

  /**
   * none
   * @return concurrentPaths
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiCommonDirectiveValue getConcurrentPaths() {
    return concurrentPaths;
  }

  public void setConcurrentPaths(TapiCommonDirectiveValue concurrentPaths) {
    this.concurrentPaths = concurrentPaths;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathObjectiveFunction tapiPathComputationPathObjectiveFunction = (TapiPathComputationPathObjectiveFunction) o;
    return Objects.equals(this.linkUtilization, tapiPathComputationPathObjectiveFunction.linkUtilization) &&
        Objects.equals(this.bandwidthOptimization, tapiPathComputationPathObjectiveFunction.bandwidthOptimization) &&
        Objects.equals(this.costOptimization, tapiPathComputationPathObjectiveFunction.costOptimization) &&
        Objects.equals(this.resourceSharing, tapiPathComputationPathObjectiveFunction.resourceSharing) &&
        Objects.equals(this.concurrentPaths, tapiPathComputationPathObjectiveFunction.concurrentPaths) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(linkUtilization, bandwidthOptimization, costOptimization, resourceSharing, concurrentPaths, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathObjectiveFunction {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    linkUtilization: ").append(toIndentedString(linkUtilization)).append("\n");
    sb.append("    bandwidthOptimization: ").append(toIndentedString(bandwidthOptimization)).append("\n");
    sb.append("    costOptimization: ").append(toIndentedString(costOptimization)).append("\n");
    sb.append("    resourceSharing: ").append(toIndentedString(resourceSharing)).append("\n");
    sb.append("    concurrentPaths: ").append(toIndentedString(concurrentPaths)).append("\n");
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

