package uk.ac.sanger.scgcf.barcodegenerator.api;

import io.swagger.annotations.*;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BarcodesPayload;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BatchBarcodes;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
