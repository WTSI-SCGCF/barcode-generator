package uk.ac.sanger.scgcf.barcodegenerator.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiParam;
import uk.ac.sanger.scgcf.barcodegenerator.exceptions.InvalidBarcodeParameterException;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.dao.BarcodeRepository;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.SingleBarcodePayload;
import uk.ac.sanger.scgcf.barcodegenerator.validators.BarcodeCreationValidator;

@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

@Controller
public class BarcodesApiController implements BarcodesApi {

    private final BarcodeRepository barcodeRepository;

    @Autowired
    public BarcodesApiController(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<Barcode> createSingleBarcode(
            @ApiParam(value = "Input parameters of the Barcode object that needs to be created", required = true) @RequestBody SingleBarcodePayload barcode) throws InvalidBarcodeParameterException {
        final String prefix = barcode.getPrefix().toUpperCase();
        final String info = barcode.getInfo();

        BarcodeCreationValidator.validatePrefix(prefix);
        BarcodeCreationValidator.validateInfo(info);

        Barcode latestBarcode = 
            BarcodeCreator.create(prefix, info, barcodeRepository);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .buildAndExpand(prefix).toUri();
        return ResponseEntity.created(location).body(latestBarcode);
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<List<Barcode>> getBarcodesInfo(
            @ApiParam(value = "Size of barcode array") @RequestParam(value = "size", required = false) Integer size) {
        List<Barcode> barcodes = this.barcodeRepository.findAll();

        barcodes.forEach(barcode -> {
            String fullBarcode = barcode.getFullBarcode();
            String info = fullBarcode.substring(fullBarcode.indexOf('-') + 1, fullBarcode.length() - 9);
            barcode.setInfo(info);
        });
        return ResponseEntity.ok(barcodes);
    }
}
