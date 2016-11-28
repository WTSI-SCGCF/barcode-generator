/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;

/**
 * @author ke4
 *        
 */
public interface BarcodeRepository extends JpaRepository<Barcode, Long> {
    
    Barcode findByPrefix(String prefix);
}
