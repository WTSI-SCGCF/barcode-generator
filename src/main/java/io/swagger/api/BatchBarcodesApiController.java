package io.swagger.api;

import io.swagger.model.BarcodesPayload;
import io.swagger.model.BatchBarcodes;
import io.swagger.model.Error;

import io.swagger.annotations.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

@Controller
public class BatchBarcodesApiController implements BatchBarcodesApi {

    public ResponseEntity<BatchBarcodes> bartchCreateListOfBarcodes(@ApiParam(value = "Input parameters of the Barcode object(s) that needs to be created" ,required=true ) @RequestBody BarcodesPayload body) {
        // do some magic!
        return new ResponseEntity<BatchBarcodes>(HttpStatus.OK);
    }

}
