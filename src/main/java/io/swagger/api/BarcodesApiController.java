package io.swagger.api;

import io.swagger.model.Barcode;
import io.swagger.model.Error;
import io.swagger.model.SingleBarcodePayload;

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
public class BarcodesApiController implements BarcodesApi {

    public ResponseEntity<Barcode> createSingleBarcode(@ApiParam(value = "Input parameters of the Barcode object that needs to be created" ,required=true ) @RequestBody SingleBarcodePayload body) {
        // do some magic!
        return new ResponseEntity<Barcode>(HttpStatus.OK);
    }

    public ResponseEntity<Barcode> findBarcodeByPrefix(@ApiParam(value = "Prefix to filter by") @RequestParam(value = "prefix", required = false) String prefix) {
        // do some magic!
        return new ResponseEntity<Barcode>(HttpStatus.OK);
    }

    public ResponseEntity<List<Barcode>> getBarcodesInfo(@ApiParam(value = "Size of barcode array") @RequestParam(value = "size", required = false) Integer size) {
        // do some magic!
        return new ResponseEntity<List<Barcode>>(HttpStatus.OK);
    }

}
