package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationNameAndValueChangeWrapper
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationNameAndValueChangeWrapper   {
  @JsonProperty("changed-attributes")
  private TapiNotificationNameAndValueChange changedAttributes = null;

  public TapiNotificationNameAndValueChangeWrapper changedAttributes(TapiNotificationNameAndValueChange changedAttributes) {
    this.changedAttributes = changedAttributes;
    return this;
  }

  /**
   * Get changedAttributes
   * @return changedAttributes
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiNotificationNameAndValueChange getChangedAttributes() {
    return changedAttributes;
  }

  public void setChangedAttributes(TapiNotificationNameAndValueChange changedAttributes) {
    this.changedAttributes = changedAttributes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationNameAndValueChangeWrapper tapiNotificationNameAndValueChangeWrapper = (TapiNotificationNameAndValueChangeWrapper) o;
    return Objects.equals(this.changedAttributes, tapiNotificationNameAndValueChangeWrapper.changedAttributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(changedAttributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationNameAndValueChangeWrapper {\n");
    
    sb.append("    changedAttributes: ").append(toIndentedString(changedAttributes)).append("\n");
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

