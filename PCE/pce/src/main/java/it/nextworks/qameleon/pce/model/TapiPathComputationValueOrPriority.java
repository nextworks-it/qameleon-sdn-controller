package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiPathComputationValueOrPriority
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationValueOrPriority   {
  @JsonProperty("priority")
  private Integer priority = null;

  @JsonProperty("value")
  private Integer value = null;

  public TapiPathComputationValueOrPriority priority(Integer priority) {
    this.priority = priority;
    return this;
  }

  /**
   * none
   * @return priority
  **/
  @ApiModelProperty(value = "none")


  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public TapiPathComputationValueOrPriority value(Integer value) {
    this.value = value;
    return this;
  }

  /**
   * none
   * @return value
  **/
  @ApiModelProperty(value = "none")


  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationValueOrPriority tapiPathComputationValueOrPriority = (TapiPathComputationValueOrPriority) o;
    return Objects.equals(this.priority, tapiPathComputationValueOrPriority.priority) &&
        Objects.equals(this.value, tapiPathComputationValueOrPriority.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(priority, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationValueOrPriority {\n");
    
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

