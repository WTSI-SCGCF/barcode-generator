/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.controller;

import uk.ac.sanger.scgcf.barcodegenerator.persistence.dao.BarcodeRepository;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;

/**
 * @author  ke4
 */
public class BarcodeCreator {

    /**
     * 
     */
    public static Barcode create(String prefix, String info,
            BarcodeRepository barcodeRepository) {
        Barcode latestBarcode = barcodeRepository.findByPrefix(prefix);

        if (latestBarcode == null) {
            latestBarcode = new Barcode()
                    .prefix(prefix).info(info).number(1L);
        } else {
            latestBarcode.info(info)
                .increaseNumberBy(1L);
        }
        latestBarcode.setFullBarcode();

//        barcodeRepository.flush();
        latestBarcode = barcodeRepository.save(latestBarcode);

        return latestBarcode;
    }
}