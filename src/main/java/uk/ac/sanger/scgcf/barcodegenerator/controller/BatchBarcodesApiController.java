package uk.ac.sanger.scgcf.barcodegenerator.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiParam;
import uk.ac.sanger.scgcf.barcodegenerator.exceptions.InvalidBarcodeParameterException;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.dao.BarcodeRepository;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BarcodesPayload;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.BatchBarcodes;
import uk.ac.sanger.scgcf.barcodegenerator.validators.BarcodeCreationValidator;

@javax.annotation.Generated(value = "class uk.ac.sanger.scgcf.barcodegenerator.codegen.languages.SpringCodegen", date = "2016-11-25T08:23:20.639Z")

@Controller
public class BatchBarcodesApiController implements BatchBarcodesApi {

    private final BarcodeRepository barcodeRepository;

    @Autowired
    public BatchBarcodesApiController(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }

    /**
     * {@inheritDoc}
     */
    public ResponseEntity<BatchBarcodes> batchCreateListOfBarcodes(
            @ApiParam(value = "Input parameters of the Barcode object(s) that needs to be created",
                        required = true)
            @RequestBody BarcodesPayload barcodesParam) throws InvalidBarcodeParameterException {

        int numberOfBarcodes = barcodesParam.getNumberOfBarcodes();
        String prefix = barcodesParam.getPrefix();
        String info = barcodesParam.getInfo();

        BarcodeCreationValidator.validatePrefix(prefix);
        BarcodeCreationValidator.validateInfo(info);

        BatchBarcodes barcodes = new BatchBarcodes();

        for (int i = 0; i < numberOfBarcodes; i++) {
            Barcode newBarcode = BarcodeCreator.create(prefix, info, barcodeRepository);
            barcodes.add(new Barcode()
                    .id(newBarcode.getId())
                    .prefix(newBarcode.getPrefix())
                    .info(newBarcode.getInfo())
                    .number(newBarcode.getNumber())
                    .fullBarcode(newBarcode.getFullBarcode())
                    );
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(prefix).toUri();
        return ResponseEntity.created(location).body(barcodes);
    }
}
