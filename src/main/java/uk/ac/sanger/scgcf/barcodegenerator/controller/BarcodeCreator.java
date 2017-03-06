/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.controller;

import uk.ac.sanger.scgcf.barcodegenerator.persistence.dao.BarcodeRepository;
import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;

/**
 * This class is responsible for creating a <code>Barcode</code>.
 * It will check first if there is a barcode exists with the given prefix.
 * If yes, then it will just update its <code>number</code> field by one. If not,
 * then it will insert a new row to the database and
 * start the <code>number</code> field with the value of 1.
 * It will also set/update the <code>fullBarcode</code> field using the
 * following pattern: <code><prefix>-<info>-<number>,
 * for example: SCGC-ABC-00000001
 * 
 * @author  ke4
 */
public class BarcodeCreator {

    /**
     * This method is creating a <code>Barcode</code>
     * with a given parameters (prefix, info).
     * 
     * @param prefix prefix of the barcode to create
     * @param info info of the barcode to create
     * @param barcodeRepository repository of the barcodes
     * @return a created or updated <code>Barcode</code> object
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

        latestBarcode = barcodeRepository.save(latestBarcode);

        return latestBarcode;
    }
}