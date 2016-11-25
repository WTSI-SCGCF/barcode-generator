package uk.ac.sanger.scgcf.barcodegenerator.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiParam;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.dao.BarcodeRepository;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.SingleBarcodePayload;


@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

@Controller
public class BarcodesApiController implements BarcodesApi {

    private final BarcodeRepository barcodeRepository;

    @Autowired
    public BarcodesApiController(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }

    public ResponseEntity<Barcode> createSingleBarcode(@ApiParam(value = "Input parameters of the Barcode object that needs to be created" ,required=true ) @RequestBody SingleBarcodePayload barcode) {
        String prefix = barcode.getPrefix();
        Barcode latestBarcode = this.barcodeRepository.findByPrefix(prefix);
        if (latestBarcode == null) {
            latestBarcode = new Barcode()
                    .prefix(prefix)
                    .info(barcode.getInfo())
                    .number(1L);
        } else {
            latestBarcode
                .info(barcode.getInfo())
                .increaseNumberBy(1L);
        }
        latestBarcode.setFullBarcode();

        this.barcodeRepository.save(latestBarcode);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(latestBarcode.getPrefix()).toUri();
        return ResponseEntity.created(location).body(latestBarcode);
    }

    public ResponseEntity<Barcode> findBarcodeByPrefix(@ApiParam(value = "Prefix to filter by") @RequestParam(value = "prefix", required = false) String prefix) {
        // do some magic!
        return new ResponseEntity<Barcode>(HttpStatus.OK);
    }

    public ResponseEntity<List<Barcode>> getBarcodesInfo(@ApiParam(value = "Size of barcode array") @RequestParam(value = "size", required = false) Integer size) {
        // do some magic!
        List<Barcode> barcodes = this.barcodeRepository.findAll();

        barcodes.forEach( barcode -> {
            String fullBarcode = barcode.getFullBarcode();
            String info = fullBarcode.substring(fullBarcode.indexOf('-') + 1,
                            fullBarcode.length() - 9);
            barcode.setInfo(info);
        });
        return ResponseEntity.ok(barcodes);
    }

}
