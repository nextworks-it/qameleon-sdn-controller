package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonCapacity
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonCapacity   {
  @JsonProperty("total-size")
  private TapiCommonCapacityValue totalSize = null;

  public TapiCommonCapacity totalSize(TapiCommonCapacityValue totalSize) {
    this.totalSize = totalSize;
    return this;
  }

  /**
   * Total capacity of the entity. In case of bandwidthProfile, this is expected to same as the committedInformationRate.
   * @return totalSize
  **/
  @ApiModelProperty(value = "Total capacity of the entity. In case of bandwidthProfile, this is expected to same as the committedInformationRate.")

  @Valid

  public TapiCommonCapacityValue getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(TapiCommonCapacityValue totalSize) {
    this.totalSize = totalSize;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonCapacity tapiCommonCapacity = (TapiCommonCapacity) o;
    return Objects.equals(this.totalSize, tapiCommonCapacity.totalSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonCapacity {\n");
    
    sb.append("    totalSize: ").append(toIndentedString(totalSize)).append("\n");
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

