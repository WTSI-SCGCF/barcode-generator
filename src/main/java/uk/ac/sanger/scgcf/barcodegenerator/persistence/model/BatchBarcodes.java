package uk.ac.sanger.scgcf.barcodegenerator.persistence.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * BatchBarcodes
 */
@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

public class BatchBarcodes extends ArrayList<BatchBarcodeElement> {
    
    private static final long serialVersionUID = 945438707879813832L;
    
    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BatchBarcodes {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
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
