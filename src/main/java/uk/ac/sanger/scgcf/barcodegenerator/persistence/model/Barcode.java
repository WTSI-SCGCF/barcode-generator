package uk.ac.sanger.scgcf.barcodegenerator.persistence.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Barcode
 */
@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

/**
 * Entity class for the barcode.
 * @author ke4
 *
 */
@Entity
@Table(name="barcodes")
public class Barcode {

    private static final String SEPARATOR_CHARACTER = "-";

    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("prefix")
    private String prefix;

    @JsonProperty("info")
    private String info;

    @JsonProperty("number")
    private Long number;

    @JsonProperty("fullBarcode")
    private String fullBarcode;

    public Barcode id(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * 
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
     * 
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
     * 
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
     * 
     * @return number
     **/
    @ApiModelProperty(value = "")
    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Barcode increaseNumberBy(Long numberOfBarcodes) {
        this.number += numberOfBarcodes;
        return this;
    }

    public Barcode fullBarcode(String fullBarcode) {
        this.fullBarcode = fullBarcode;
        return this;
    }

    /**
     * Get fullBarcode
     * 
     * @return fullBarcode
     **/
    @ApiModelProperty(value = "")
    public String getFullBarcode() {
        return fullBarcode;
    }

    /**
     * Set the full barcode using the <code>prefix</code> and <code>info</code> property.
     * It pads the numeric part with `0` to 8 characters length.
     */
    public void setFullBarcode() {
        String paddedNumber = String.format("%08d", number);
        StringBuilder builder = new StringBuilder(prefix);
        this.fullBarcode = builder.append(SEPARATOR_CHARACTER).append(info).append(SEPARATOR_CHARACTER)
                .append(paddedNumber).toString();
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
        return Objects.equals(this.id, barcode.id) && Objects.equals(this.prefix, barcode.prefix)
                && Objects.equals(this.info, barcode.info) && Objects.equals(this.number, barcode.number)
                && Objects.equals(this.fullBarcode, barcode.fullBarcode);
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
