/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;

/**
 * Data repository for <code>Barcode</code> entities.
 * 
 * @author ke4
 * 
 */
@Repository
public interface BarcodeRepository extends JpaRepository<Barcode, Long> {

    /**
     * Finds barcode with a given prefix.
     * 
     * @param prefix the prefix of the barcode to find
     * @return the found <code>Barcode</code> or null,
     * if there is no <code>Barcode</code> exists with the given prefix.
     */
    Barcode findByPrefix(String prefix);
}
