/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.exceptions;

/**
 * @author ke4
 *
 */
public class InvalidBarcodeParameterException extends ApiException {

    private static final long serialVersionUID = 5625857272696874121L;

    private String field;

    /**
     * @param code
     * @param msg
     */
    public InvalidBarcodeParameterException(int code, String field, String msg) {
        super(code, msg);
        this.field = field;
    }


    public String getField() {
        return field;
    }

}
