package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiNotificationCreatenotificationsubscriptionserviceInputBodyparam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiNotificationCreatenotificationsubscriptionserviceInputBodyparam   {
  @JsonProperty("input")
  private TapiNotificationCreatenotificationsubscriptionserviceInput input = null;

  public TapiNotificationCreatenotificationsubscriptionserviceInputBodyparam input(TapiNotificationCreatenotificationsubscriptionserviceInput input) {
    this.input = input;
    return this;
  }

  /**
   * Get input
   * @return input
  **/
  @ApiModelProperty(value = "")

  @Valid

  public TapiNotificationCreatenotificationsubscriptionserviceInput getInput() {
    return input;
  }

  public void setInput(TapiNotificationCreatenotificationsubscriptionserviceInput input) {
    this.input = input;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiNotificationCreatenotificationsubscriptionserviceInputBodyparam tapiNotificationCreatenotificationsubscriptionserviceInputBodyparam = (TapiNotificationCreatenotificationsubscriptionserviceInputBodyparam) o;
    return Objects.equals(this.input, tapiNotificationCreatenotificationsubscriptionserviceInputBodyparam.input);
  }

  @Override
  public int hashCode() {
    return Objects.hash(input);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiNotificationCreatenotificationsubscriptionserviceInputBodyparam {\n");
    
    sb.append("    input: ").append(toIndentedString(input)).append("\n");
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

