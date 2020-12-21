package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyResilienceTypeWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyResilienceTypeWrapper   {
  @JsonProperty("resilience-type")
  private TapiTopologyResilienceType resilienceType = null;

  public TapiTopologyResilienceTypeWrapper resilienceType(TapiTopologyResilienceType resilienceType) {
    this.resilienceType = resilienceType;
    return this;
  }

  /**
   * Get resilienceType
   * @return resilienceType
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyResilienceType getResilienceType() {
    return resilienceType;
  }

  public void setResilienceType(TapiTopologyResilienceType resilienceType) {
    this.resilienceType = resilienceType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyResilienceTypeWrapper tapiTopologyResilienceTypeWrapper = (TapiTopologyResilienceTypeWrapper) o;
    return Objects.equals(this.resilienceType, tapiTopologyResilienceTypeWrapper.resilienceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resilienceType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyResilienceTypeWrapper {\n");
    
    sb.append("    resilienceType: ").append(toIndentedString(resilienceType)).append("\n");
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

