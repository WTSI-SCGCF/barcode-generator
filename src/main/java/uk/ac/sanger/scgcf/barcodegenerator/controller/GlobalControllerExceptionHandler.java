/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import uk.ac.sanger.scgcf.barcodegenerator.exceptions.InvalidBarcodeParameterException;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Error;

/**
 * Global exception handler for handling barcode creation related exceptions.
 * 
 * @author ke4
 *
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    /**
     * Handles <code>InvalidBarcodeParameterException</code> exception.
     * Maps it with <code>BAD_REQUEST</code> HTTP status and returns an
     * <code>Error</code> response entity.
     * 
     * @param ex the exception has been thrown
     * @return an <code>Error</code> response entity with
     * <code>BAD_REQUEST</code> HTTP status code.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidBarcodeParameterException.class)
    public ResponseEntity<Error> handleBarcodeInvalidExceptions(
            InvalidBarcodeParameterException ex) {
        Error error = new Error();
        error.code(ex.getCode())
            .fields(ex.getField())
            .message(ex.getMessage());

        return ResponseEntity.badRequest().body(error);
    }
}
