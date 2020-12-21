package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonTerminationPac
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonTerminationPac   {
  @JsonProperty("termination-direction")
  private TapiCommonTerminationDirection terminationDirection = null;

  @JsonProperty("termination-state")
  private TapiCommonTerminationState terminationState = null;

  public TapiCommonTerminationPac terminationDirection(TapiCommonTerminationDirection terminationDirection) {
    this.terminationDirection = terminationDirection;
    return this;
  }

  /**
   * The overall directionality of the entity.                   - A BIDIRECTIONAL entity will have some SINK and/or SOURCE flows.                  - A SINK entity can only contain elements with SINK flows or CONTRA_DIRECTION_SOURCE flows.                  - A SOURCE entity can only contain SOURCE flows or CONTRA_DIRECTION_SINK flows.
   * @return terminationDirection
  **/
  @ApiModelProperty(value = "The overall directionality of the entity.                   - A BIDIRECTIONAL entity will have some SINK and/or SOURCE flows.                  - A SINK entity can only contain elements with SINK flows or CONTRA_DIRECTION_SOURCE flows.                  - A SOURCE entity can only contain SOURCE flows or CONTRA_DIRECTION_SINK flows.")

  @Valid

  public TapiCommonTerminationDirection getTerminationDirection() {
    return terminationDirection;
  }

  public void setTerminationDirection(TapiCommonTerminationDirection terminationDirection) {
    this.terminationDirection = terminationDirection;
  }

  public TapiCommonTerminationPac terminationState(TapiCommonTerminationState terminationState) {
    this.terminationState = terminationState;
    return this;
  }

  /**
   * Indicates whether the layer is terminated and if so how.
   * @return terminationState
  **/
  @ApiModelProperty(value = "Indicates whether the layer is terminated and if so how.")

  @Valid

  public TapiCommonTerminationState getTerminationState() {
    return terminationState;
  }

  public void setTerminationState(TapiCommonTerminationState terminationState) {
    this.terminationState = terminationState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonTerminationPac tapiCommonTerminationPac = (TapiCommonTerminationPac) o;
    return Objects.equals(this.terminationDirection, tapiCommonTerminationPac.terminationDirection) &&
        Objects.equals(this.terminationState, tapiCommonTerminationPac.terminationState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(terminationDirection, terminationState);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonTerminationPac {\n");
    
    sb.append("    terminationDirection: ").append(toIndentedString(terminationDirection)).append("\n");
    sb.append("    terminationState: ").append(toIndentedString(terminationState)).append("\n");
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

