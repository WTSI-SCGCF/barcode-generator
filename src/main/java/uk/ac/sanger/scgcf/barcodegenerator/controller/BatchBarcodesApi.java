package uk.ac.sanger.scgcf.barcodegenerator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.ac.sanger.scgcf.barcodegenerator.exceptions.InvalidBarcodeParameterException;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BarcodesPayload;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BatchBarcodes;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Error;

@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

/**
 * API defining the bulk barcode operations.
 * 
 * @author ke4
 *
 */
@Api(value = "batch_barcodes", description = "the batch_barcodes API")
public interface BatchBarcodesApi {

    /**
     * Creates a given number of barcodes with the given input parameters (prefix, info).
     * 
     * @param body body contains the payload for barcode creation
     * @return a list of <code>ResponseEntity</code> with the created barcode and the status code
     * or error message and its status code
     * @throws InvalidBarcodeParameterException when the validation failed
     * related the parameters of the barcode
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(code = 201, value = "Creates a batch of barcodes with the given input parameters", notes = "Batch barcode creation", response = BatchBarcodes.class, tags = {
            "batch_barcode", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "List of barcodes has been created", response = BatchBarcodes.class),
            @ApiResponse(code = 400, message = "Invalid parameter(s) supplied", response = Error.class) })
    @RequestMapping(value = "/batch_barcodes", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)
    ResponseEntity<BatchBarcodes> batchCreateListOfBarcodes(
            @ApiParam(value = "Input parameters of the Barcode object(s) that needs to be created", required = true) @RequestBody BarcodesPayload body) throws InvalidBarcodeParameterException;
}
