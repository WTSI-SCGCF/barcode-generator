/**
 * 
 */
package uk.ac.sanger.scgcf.barcodegenerator.controller;

import java.util.List;

import uk.ac.sanger.scgcf.barcodegenerator.persistence.model.Barcode;

/**
 * @author ke4
 *
 */
public class BarcodesApiControllerTestData {

    private static final String SEPARATOR_CHARACTER = "-";

    public static Barcode buildBarcode(Long id, String prefix, String info, Long number) {
        if (number == null) number = 1L;
        String paddedNumber = String.format("%08d", number);
        StringBuilder builder = new StringBuilder(prefix);
        String fullBarcode = builder.append(SEPARATOR_CHARACTER)
                .append(info)
                .append(SEPARATOR_CHARACTER)
                .append(paddedNumber).toString();

        return new Barcode()
                .id(id)
                .prefix(prefix)
                .info(info)
                .number(number)
                .fullBarcode(fullBarcode);
    }

    public static String buildResponseJson(List<Barcode> barcodes) {
        StringBuilder builder = new StringBuilder();
        if (barcodes.size() > 1) {
            builder.append("[");
        }

        barcodes.forEach(barcode -> {
            builder.append("{\"id\":");
            builder.append(barcode.getId());
            builder.append(",\"prefix\":\"");
            builder.append(barcode.getPrefix());
            builder.append("\",\"info\":\"");
            builder.append(barcode.getInfo());
            builder.append("\",\"number\":");
            builder.append(barcode.getNumber());
            builder.append(",\"fullBarcode\":\"");
            builder.append(barcode.getFullBarcode());
            builder.append("\"},");
        });
        builder.deleteCharAt(builder.length() - 1);

        if (barcodes.size() > 1) {
            builder.append("]");
        }

        return builder.toString();
    }
}
