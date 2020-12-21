package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyTopologyContextWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyTopologyContextWrapper   {
  @JsonProperty("tapi-topology:topology-context")
  private TapiTopologyTopologyContext tapiTopologytopologyContext = null;

  public TapiTopologyTopologyContextWrapper tapiTopologytopologyContext(TapiTopologyTopologyContext tapiTopologytopologyContext) {
    this.tapiTopologytopologyContext = tapiTopologytopologyContext;
    return this;
  }

  /**
   * Get tapiTopologytopologyContext
   * @return tapiTopologytopologyContext
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiTopologyTopologyContext getTapiTopologytopologyContext() {
    return tapiTopologytopologyContext;
  }

  public void setTapiTopologytopologyContext(TapiTopologyTopologyContext tapiTopologytopologyContext) {
    this.tapiTopologytopologyContext = tapiTopologytopologyContext;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyTopologyContextWrapper tapiTopologyTopologyContextWrapper = (TapiTopologyTopologyContextWrapper) o;
    return Objects.equals(this.tapiTopologytopologyContext, tapiTopologyTopologyContextWrapper.tapiTopologytopologyContext);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tapiTopologytopologyContext);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyTopologyContextWrapper {\n");
    
    sb.append("    tapiTopologytopologyContext: ").append(toIndentedString(tapiTopologytopologyContext)).append("\n");
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

