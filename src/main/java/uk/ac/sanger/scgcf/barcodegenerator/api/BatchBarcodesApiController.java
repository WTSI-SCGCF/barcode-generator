package uk.ac.sanger.scgcf.barcodegenerator.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BarcodesPayload;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BatchBarcodes;


@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

@Controller
public class BatchBarcodesApiController implements BatchBarcodesApi {

    public ResponseEntity<BatchBarcodes> bartchCreateListOfBarcodes(@ApiParam(value = "Input parameters of the Barcode object(s) that needs to be created" ,required=true ) @RequestBody BarcodesPayload body) {
        // do some magic!
        return new ResponseEntity<BatchBarcodes>(HttpStatus.OK);
    }

}
