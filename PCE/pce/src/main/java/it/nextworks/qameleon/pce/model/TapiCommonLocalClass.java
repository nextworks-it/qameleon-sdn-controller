package it.nextworks.qameleon.pce.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;

/**
 * TapiCommonLocalClass
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-09-23T14:44:33.242Z")




public class TapiCommonLocalClass   {
  @JsonProperty("name")
  @Valid
  private List<TapiCommonNameAndValue> name = null;

  @JsonProperty("local-id")
  private String localId = null;

  public TapiCommonLocalClass name(List<TapiCommonNameAndValue> name) {
    this.name = name;
    return this;
  }

  public TapiCommonLocalClass addNameItem(TapiCommonNameAndValue nameItem) {
    if (this.name == null) {
      this.name = new ArrayList<TapiCommonNameAndValue>();
    }
    this.name.add(nameItem);
    return this;
  }

  /**
   * List of names. This value is unique in some namespace but may change during the life of the entity.                  A name carries no semantics with respect to the purpose of the entity.
   * @return name
  **/
  @ApiModelProperty(value = "List of names. This value is unique in some namespace but may change during the life of the entity.                  A name carries no semantics with respect to the purpose of the entity.")

  @Valid

  public List<TapiCommonNameAndValue> getName() {
    return name;
  }

  public void setName(List<TapiCommonNameAndValue> name) {
    this.name = name;
  }

  public TapiCommonLocalClass localId(String localId) {
    this.localId = localId;
    return this;
  }

  /**
   * An identifier that is unique in the context of the GlobalClass from which it is inseparable.
   * @return localId
  **/
  @ApiModelProperty(value = "An identifier that is unique in the context of the GlobalClass from which it is inseparable.")


  public String getLocalId() {
    return localId;
  }

  public void setLocalId(String localId) {
    this.localId = localId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TapiCommonLocalClass tapiCommonLocalClass = (TapiCommonLocalClass) o;
    return Objects.equals(this.name, tapiCommonLocalClass.name) &&
        Objects.equals(this.localId, tapiCommonLocalClass.localId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, localId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TapiCommonLocalClass {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    localId: ").append(toIndentedString(localId)).append("\n");
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

