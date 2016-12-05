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
 * @author ke4
 *
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

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
