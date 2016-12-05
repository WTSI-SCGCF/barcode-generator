/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.validators;

import org.springframework.http.HttpStatus;

import uk.ac.sanger.scgcf.barcodegenerator.exceptions.InvalidBarcodeParameterException;

/**
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

    public static void validatePrefix(String prefix) throws InvalidBarcodeParameterException {
        if (prefix.length() != BARCODE_PREFIX_LENGTH || !prefix.matches("^[a-zA-Z0-9]*$")) {
            throw new InvalidBarcodeParameterException(
                    HttpStatus.BAD_REQUEST.value(),
                    "prefix",
                    INVALID_BARCODE_PREFIX_ERROR_MESSAGE);
        }
    }

    public static void validateInfo(String info) throws InvalidBarcodeParameterException {
        if (info.length() != BARCODE_INFO_LENGTH || !info.matches("^[a-zA-Z0-9]*$")) {
            throw new InvalidBarcodeParameterException(
                    HttpStatus.BAD_REQUEST.value(),
                    "info",
                    INVALID_BARCODE_INFO_ERROR_MESSAGE);
        }
    }
}
