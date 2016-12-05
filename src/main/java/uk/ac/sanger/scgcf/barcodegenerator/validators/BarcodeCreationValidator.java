/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.validators;

import org.springframework.http.HttpStatus;

import uk.ac.sanger.scgcf.barcodegenerator.exceptions.InvalidBarcodeParameterException;

/**
 * This class contains the validations related to the <code>Barcode</code>.
 * @author ke4
 *
 */
public class BarcodeCreationValidator {

    public static final String INVALID_BARCODE_PREFIX_ERROR_MESSAGE =
            "The prefix of the barcode should be exactly 4 alphanumerical characters.";
    public static final String INVALID_BARCODE_INFO_ERROR_MESSAGE =
            "The info of the barcode should be exactly 3 alphanumerical characters.";

    private static final int BARCODE_PREFIX_LENGTH = 4;
    private static final int BARCODE_INFO_LENGTH = 3;

    /**
     * Validates the prefix of the <code>Barcode</code>.
     * 
     * @param prefix the prefix to validate
     * @throws InvalidBarcodeParameterException if the prefix length is not
     * equals to the given <code>BARCODE_PREFIX_LENGTH</code> or the prefix
     * contains not alphanumeric characters.
     */
    public static void validatePrefix(String prefix) throws InvalidBarcodeParameterException {
        if (prefix.length() != BARCODE_PREFIX_LENGTH || !prefix.matches("^[a-zA-Z0-9]*$")) {
            throw new InvalidBarcodeParameterException(
                    HttpStatus.BAD_REQUEST.value(),
                    "prefix",
                    INVALID_BARCODE_PREFIX_ERROR_MESSAGE);
        }
    }

    /**
     * Validates the info of the <code>Barcode</code>.
     * 
     * @param info the info to validate
     * @throws InvalidBarcodeParameterException if the info length is not
     * equals to the given <code>BARCODE_INFO_LENGTH</code> or the info
     * contains not alphanumeric characters.
     */
    public static void validateInfo(String info) throws InvalidBarcodeParameterException {
        if (info.length() != BARCODE_INFO_LENGTH || !info.matches("^[a-zA-Z0-9]*$")) {
            throw new InvalidBarcodeParameterException(
                    HttpStatus.BAD_REQUEST.value(),
                    "info",
                    INVALID_BARCODE_INFO_ERROR_MESSAGE);
        }
    }
}
