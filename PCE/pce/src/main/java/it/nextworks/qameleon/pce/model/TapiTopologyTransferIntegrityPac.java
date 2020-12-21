package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyTransferIntegrityPac
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyTransferIntegrityPac   {
  @JsonProperty("error-characteristic")
  private String errorCharacteristic = null;

  @JsonProperty("unavailable-time-characteristic")
  private String unavailableTimeCharacteristic = null;

  @JsonProperty("server-integrity-process-characteristic")
  private String serverIntegrityProcessCharacteristic = null;

  @JsonProperty("delivery-order-characteristic")
  private String deliveryOrderCharacteristic = null;

  @JsonProperty("repeat-delivery-characteristic")
  private String repeatDeliveryCharacteristic = null;

  @JsonProperty("loss-characteristic")
  private String lossCharacteristic = null;

  public TapiTopologyTransferIntegrityPac errorCharacteristic(String errorCharacteristic) {
    this.errorCharacteristic = errorCharacteristic;
    return this;
  }

  /**
   * Describes the degree to which the signal propagated can be errored.                   Applies to TDM systems as the errored signal will be propagated and not packet as errored packets will be discarded.
   * @return errorCharacteristic
  **/
  @ApiModelProperty(value = "Describes the degree to which the signal propagated can be errored.                   Applies to TDM systems as the errored signal will be propagated and not packet as errored packets will be discarded.")


  public String getErrorCharacteristic() {
    return errorCharacteristic;
  }

  public void setErrorCharacteristic(String errorCharacteristic) {
    this.errorCharacteristic = errorCharacteristic;
  }

  public TapiTopologyTransferIntegrityPac unavailableTimeCharacteristic(String unavailableTimeCharacteristic) {
    this.unavailableTimeCharacteristic = unavailableTimeCharacteristic;
    return this;
  }

  /**
   * Describes the duration for which there may be no valid signal propagated.
   * @return unavailableTimeCharacteristic
  **/
  @ApiModelProperty(value = "Describes the duration for which there may be no valid signal propagated.")


  public String getUnavailableTimeCharacteristic() {
    return unavailableTimeCharacteristic;
  }

  public void setUnavailableTimeCharacteristic(String unavailableTimeCharacteristic) {
    this.unavailableTimeCharacteristic = unavailableTimeCharacteristic;
  }

  public TapiTopologyTransferIntegrityPac serverIntegrityProcessCharacteristic(String serverIntegrityProcessCharacteristic) {
    this.serverIntegrityProcessCharacteristic = serverIntegrityProcessCharacteristic;
    return this;
  }

  /**
   * Describes the effect of any server integrity enhancement process on the characteristics of the TopologicalEntity.
   * @return serverIntegrityProcessCharacteristic
  **/
  @ApiModelProperty(value = "Describes the effect of any server integrity enhancement process on the characteristics of the TopologicalEntity.")


  public String getServerIntegrityProcessCharacteristic() {
    return serverIntegrityProcessCharacteristic;
  }

  public void setServerIntegrityProcessCharacteristic(String serverIntegrityProcessCharacteristic) {
    this.serverIntegrityProcessCharacteristic = serverIntegrityProcessCharacteristic;
  }

  public TapiTopologyTransferIntegrityPac deliveryOrderCharacteristic(String deliveryOrderCharacteristic) {
    this.deliveryOrderCharacteristic = deliveryOrderCharacteristic;
    return this;
  }

  /**
   * Describes the degree to which packets will be delivered out of sequence.                  Does not apply to TDM as the TDM protocols maintain strict order.
   * @return deliveryOrderCharacteristic
  **/
  @ApiModelProperty(value = "Describes the degree to which packets will be delivered out of sequence.                  Does not apply to TDM as the TDM protocols maintain strict order.")


  public String getDeliveryOrderCharacteristic() {
    return deliveryOrderCharacteristic;
  }

  public void setDeliveryOrderCharacteristic(String deliveryOrderCharacteristic) {
    this.deliveryOrderCharacteristic = deliveryOrderCharacteristic;
  }

  public TapiTopologyTransferIntegrityPac repeatDeliveryCharacteristic(String repeatDeliveryCharacteristic) {
    this.repeatDeliveryCharacteristic = repeatDeliveryCharacteristic;
    return this;
  }

  /**
   * Primarily applies to packet systems where a packet may be delivered more than once (in fault recovery for example).                   It can also apply to TDM where several frames may be received twice due to switching in a system with a large differential propagation delay.
   * @return repeatDeliveryCharacteristic
  **/
  @ApiModelProperty(value = "Primarily applies to packet systems where a packet may be delivered more than once (in fault recovery for example).                   It can also apply to TDM where several frames may be received twice due to switching in a system with a large differential propagation delay.")


  public String getRepeatDeliveryCharacteristic() {
    return repeatDeliveryCharacteristic;
  }

  public void setRepeatDeliveryCharacteristic(String repeatDeliveryCharacteristic) {
    this.repeatDeliveryCharacteristic = repeatDeliveryCharacteristic;
  }

  public TapiTopologyTransferIntegrityPac lossCharacteristic(String lossCharacteristic) {
    this.lossCharacteristic = lossCharacteristic;
    return this;
  }

  /**
   * Describes the acceptable characteristic of lost packets where loss may result from discard due to errors or overflow.                  Applies to packet systems and not TDM (as for TDM errored signals are propagated unless grossly errored and overflow/underflow turns into timing slips).
   * @return lossCharacteristic
  **/
  @ApiModelProperty(value = "Describes the acceptable characteristic of lost packets where loss may result from discard due to errors or overflow.                  Applies to packet systems and not TDM (as for TDM errored signals are propagated unless grossly errored and overflow/underflow turns into timing slips).")


  public String getLossCharacteristic() {
    return lossCharacteristic;
  }

  public void setLossCharacteristic(String lossCharacteristic) {
    this.lossCharacteristic = lossCharacteristic;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyTransferIntegrityPac tapiTopologyTransferIntegrityPac = (TapiTopologyTransferIntegrityPac) o;
    return Objects.equals(this.errorCharacteristic, tapiTopologyTransferIntegrityPac.errorCharacteristic) &&
        Objects.equals(this.unavailableTimeCharacteristic, tapiTopologyTransferIntegrityPac.unavailableTimeCharacteristic) &&
        Objects.equals(this.serverIntegrityProcessCharacteristic, tapiTopologyTransferIntegrityPac.serverIntegrityProcessCharacteristic) &&
        Objects.equals(this.deliveryOrderCharacteristic, tapiTopologyTransferIntegrityPac.deliveryOrderCharacteristic) &&
        Objects.equals(this.repeatDeliveryCharacteristic, tapiTopologyTransferIntegrityPac.repeatDeliveryCharacteristic) &&
        Objects.equals(this.lossCharacteristic, tapiTopologyTransferIntegrityPac.lossCharacteristic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCharacteristic, unavailableTimeCharacteristic, serverIntegrityProcessCharacteristic, deliveryOrderCharacteristic, repeatDeliveryCharacteristic, lossCharacteristic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyTransferIntegrityPac {\n");
    
    sb.append("    errorCharacteristic: ").append(toIndentedString(errorCharacteristic)).append("\n");
    sb.append("    unavailableTimeCharacteristic: ").append(toIndentedString(unavailableTimeCharacteristic)).append("\n");
    sb.append("    serverIntegrityProcessCharacteristic: ").append(toIndentedString(serverIntegrityProcessCharacteristic)).append("\n");
    sb.append("    deliveryOrderCharacteristic: ").append(toIndentedString(deliveryOrderCharacteristic)).append("\n");
    sb.append("    repeatDeliveryCharacteristic: ").append(toIndentedString(repeatDeliveryCharacteristic)).append("\n");
    sb.append("    lossCharacteristic: ").append(toIndentedString(lossCharacteristic)).append("\n");
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

