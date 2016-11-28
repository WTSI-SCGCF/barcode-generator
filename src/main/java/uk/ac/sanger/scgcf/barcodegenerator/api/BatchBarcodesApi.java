package uk.ac.sanger.scgcf.barcodegenerator.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BarcodesPayload;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BatchBarcodes;

@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

@Api(value = "batch_barcodes", description = "the batch_barcodes API")
public interface BatchBarcodesApi {

    @ApiOperation(value = "Creates a batch of barcodes with the given input parameters", notes = "Batch barcode creation", response = BatchBarcodes.class, tags={ "batch_barcode", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "List of barcodes has been created", response = BatchBarcodes.class),
        @ApiResponse(code = 400, message = "Invalid parameter(s) supplied", response = BatchBarcodes.class) })
    @RequestMapping(value = "/batch_barcodes",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<BatchBarcodes> bartchCreateListOfBarcodes(@ApiParam(value = "Input parameters of the Barcode object(s) that needs to be created" ,required=true ) @RequestBody BarcodesPayload body);

}
