package uk.ac.sanger.scgcf.barcodegenerator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.ac.sanger.scgcf.barcodegenerator.exceptions.InvalidBarcodeParameterException;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Error;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.SingleBarcodePayload;

@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

@Api(value = "barcodes", description = "the barcodes API")
public interface BarcodesApi {

    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(code = 201, value = "Creates a barcode",
            notes = "Creates a single barcode with the given input parameters (prefix, info)",
            response = Barcode.class, tags = {"barcode"})
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Barcode has been created", response = Barcode.class),
            @ApiResponse(code = 400, message = "Invalid parameter(s) supplied", response = Error.class) })
    @RequestMapping(value = "/barcodes", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)
    ResponseEntity<Barcode> createSingleBarcode(
            @ApiParam(value = "Input parameters of the Barcode object that needs to be created", required = true) @RequestBody SingleBarcodePayload body) throws InvalidBarcodeParameterException;

    @ApiOperation(value = "Gets all barcode type's latest information", notes = "Gets `Barcode` objects. Optional query param of **size** determines size of returned array ", response = Barcode.class, responseContainer = "List", tags = {
            "barcode"})
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successful response", response = Barcode.class),
            @ApiResponse(code = 400, message = "Invalid parameter(s) supplied", response = Error.class)})
    @RequestMapping(value = "/barcodes", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<List<Barcode>> getBarcodesInfo(
            @ApiParam(value = "Size of barcode array") @RequestParam(value = "size", required = false) Integer size);

}
