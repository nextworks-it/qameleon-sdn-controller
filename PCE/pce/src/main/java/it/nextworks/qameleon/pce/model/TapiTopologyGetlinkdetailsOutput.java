package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiTopologyGetlinkdetailsOutput
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyGetlinkdetailsOutput   {
  @JsonProperty("link")
  private TapiTopologyLink link = null;

  public TapiTopologyGetlinkdetailsOutput link(TapiTopologyLink link) {
    this.link = link;
    return this;
  }

  /**
   * none
   * @return link
  **/
  @ApiModelProperty(value = "none")

  @Valid

  public TapiTopologyLink getLink() {
    return link;
  }

  public void setLink(TapiTopologyLink link) {
    this.link = link;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyGetlinkdetailsOutput tapiTopologyGetlinkdetailsOutput = (TapiTopologyGetlinkdetailsOutput) o;
    return Objects.equals(this.link, tapiTopologyGetlinkdetailsOutput.link);
  }

  @Override
  public int hashCode() {
    return Objects.hash(link);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyGetlinkdetailsOutput {\n");
    
    sb.append("    link: ").append(toIndentedString(link)).append("\n");
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

