package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiPathComputationPathComputationContext
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiPathComputationPathComputationContext   {
  @JsonProperty("path-comp-service")
  @Valid
  private List<TapiPathComputationPathComputationService> pathCompService = null;

  @JsonProperty("path")
  @Valid
  private List<TapiPathComputationPath> path = null;

  public TapiPathComputationPathComputationContext pathCompService(List<TapiPathComputationPathComputationService> pathCompService) {
    this.pathCompService = pathCompService;
    return this;
  }

  public TapiPathComputationPathComputationContext addPathCompServiceItem(TapiPathComputationPathComputationService pathCompServiceItem) {
    if (this.pathCompService == null) {
      this.pathCompService = new ArrayList<TapiPathComputationPathComputationService>();
    }
    this.pathCompService.add(pathCompServiceItem);
    return this;
  }

  /**
   * none
   * @return pathCompService
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiPathComputationPathComputationService> getPathCompService() {
    return pathCompService;
  }

  public void setPathCompService(List<TapiPathComputationPathComputationService> pathCompService) {
    this.pathCompService = pathCompService;
  }

  public TapiPathComputationPathComputationContext path(List<TapiPathComputationPath> path) {
    this.path = path;
    return this;
  }

  public TapiPathComputationPathComputationContext addPathItem(TapiPathComputationPath pathItem) {
    if (this.path == null) {
      this.path = new ArrayList<TapiPathComputationPath>();
    }
    this.path.add(pathItem);
    return this;
  }

  /**
   * none
   * @return path
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public List<TapiPathComputationPath> getPath() {
    return path;
  }

  public void setPath(List<TapiPathComputationPath> path) {
    this.path = path;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiPathComputationPathComputationContext tapiPathComputationPathComputationContext = (TapiPathComputationPathComputationContext) o;
    return Objects.equals(this.pathCompService, tapiPathComputationPathComputationContext.pathCompService) &&
        Objects.equals(this.path, tapiPathComputationPathComputationContext.path);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pathCompService, path);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiPathComputationPathComputationContext {\n");
    
    sb.append("    pathCompService: ").append(toIndentedString(pathCompService)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
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

