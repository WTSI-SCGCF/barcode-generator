package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Barcode
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

public class Barcode   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("prefix")
  private String prefix = null;

  @JsonProperty("info")
  private String info = null;

  @JsonProperty("number")
  private Long number = null;

  @JsonProperty("fullBarcode")
  private String fullBarcode = null;

  public Barcode id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Barcode prefix(String prefix) {
    this.prefix = prefix;
    return this;
  }

   /**
   * Get prefix
   * @return prefix
  **/
  @ApiModelProperty(required = true, value = "")
  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public Barcode info(String info) {
    this.info = info;
    return this;
  }

   /**
   * Get info
   * @return info
  **/
  @ApiModelProperty(required = true, value = "")
  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Barcode number(Long number) {
    this.number = number;
    return this;
  }

   /**
   * Get number
   * @return number
  **/
  @ApiModelProperty(value = "")
  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public Barcode fullBarcode(String fullBarcode) {
    this.fullBarcode = fullBarcode;
    return this;
  }

   /**
   * Get fullBarcode
   * @return fullBarcode
  **/
  @ApiModelProperty(value = "")
  public String getFullBarcode() {
    return fullBarcode;
  }

  public void setFullBarcode(String fullBarcode) {
    this.fullBarcode = fullBarcode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Barcode barcode = (Barcode) o;
    return Objects.equals(this.id, barcode.id) &&
        Objects.equals(this.prefix, barcode.prefix) &&
        Objects.equals(this.info, barcode.info) &&
        Objects.equals(this.number, barcode.number) &&
        Objects.equals(this.fullBarcode, barcode.fullBarcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, prefix, info, number, fullBarcode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Barcode {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    prefix: ").append(toIndentedString(prefix)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    fullBarcode: ").append(toIndentedString(fullBarcode)).append("\n");
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

