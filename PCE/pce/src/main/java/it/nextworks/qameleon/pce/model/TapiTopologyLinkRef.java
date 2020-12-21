package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

/**
 * TapiTopologyLinkRef
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiTopologyLinkRef extends TapiTopologyTopologyRef  {
  @JsonProperty("link-uuid")
  private String linkUuid = null;

  public TapiTopologyLinkRef linkUuid(String linkUuid) {
    this.linkUuid = linkUuid;
    return this;
  }

  /**
   * none
   * @return linkUuid
  **/
  @ApiModelProperty(value = "none")


  public String getLinkUuid() {
    return linkUuid;
  }

  public void setLinkUuid(String linkUuid) {
    this.linkUuid = linkUuid;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiTopologyLinkRef tapiTopologyLinkRef = (TapiTopologyLinkRef) o;
    return Objects.equals(this.linkUuid, tapiTopologyLinkRef.linkUuid) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(linkUuid, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiTopologyLinkRef {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    linkUuid: ").append(toIndentedString(linkUuid)).append("\n");
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

