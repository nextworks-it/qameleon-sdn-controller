package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonCapacityValueWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonCapacityValueWrapper   {
  @JsonProperty("total-size")
  private TapiCommonCapacityValue totalSize = null;

  public TapiCommonCapacityValueWrapper totalSize(TapiCommonCapacityValue totalSize) {
    this.totalSize = totalSize;
    return this;
  }

  /**
   * Get totalSize
   * @return totalSize
  **/
  @ApiModelProperty(value = "")

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
    TapiCommonCapacityValueWrapper tapiCommonCapacityValueWrapper = (TapiCommonCapacityValueWrapper) o;
    return Objects.equals(this.totalSize, tapiCommonCapacityValueWrapper.totalSize);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalSize);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonCapacityValueWrapper {\n");
    
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

