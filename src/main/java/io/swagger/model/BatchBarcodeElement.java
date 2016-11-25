package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * BatchBarcodeElement
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

public class BatchBarcodeElement   {
  @JsonProperty("href")
  private String href = null;

  @JsonProperty("fullBarcode")
  private String fullBarcode = null;

  public BatchBarcodeElement href(String href) {
    this.href = href;
    return this;
  }

   /**
   * Get href
   * @return href
  **/
  @ApiModelProperty(required = true, value = "")
  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public BatchBarcodeElement fullBarcode(String fullBarcode) {
    this.fullBarcode = fullBarcode;
    return this;
  }

   /**
   * Get fullBarcode
   * @return fullBarcode
  **/
  @ApiModelProperty(required = true, value = "")
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
    BatchBarcodeElement batchBarcodeElement = (BatchBarcodeElement) o;
    return Objects.equals(this.href, batchBarcodeElement.href) &&
        Objects.equals(this.fullBarcode, batchBarcodeElement.fullBarcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(href, fullBarcode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BatchBarcodeElement {\n");
    
    sb.append("    href: ").append(toIndentedString(href)).append("\n");
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

