package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyLatencyCharacteristicWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyLatencyCharacteristicWrapper   {
  @JsonProperty("latency-characteristic")
  private TapiTopologyLatencyCharacteristic latencyCharacteristic = null;

  public TapiTopologyLatencyCharacteristicWrapper latencyCharacteristic(TapiTopologyLatencyCharacteristic latencyCharacteristic) {
    this.latencyCharacteristic = latencyCharacteristic;
    return this;
  }

  /**
   * Get latencyCharacteristic
   * @return latencyCharacteristic
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyLatencyCharacteristic getLatencyCharacteristic() {
    return latencyCharacteristic;
  }

  public void setLatencyCharacteristic(TapiTopologyLatencyCharacteristic latencyCharacteristic) {
    this.latencyCharacteristic = latencyCharacteristic;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyLatencyCharacteristicWrapper tapiTopologyLatencyCharacteristicWrapper = (TapiTopologyLatencyCharacteristicWrapper) o;
    return Objects.equals(this.latencyCharacteristic, tapiTopologyLatencyCharacteristicWrapper.latencyCharacteristic);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latencyCharacteristic);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyLatencyCharacteristicWrapper {\n");
    
    sb.append("    latencyCharacteristic: ").append(toIndentedString(latencyCharacteristic)).append("\n");
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

