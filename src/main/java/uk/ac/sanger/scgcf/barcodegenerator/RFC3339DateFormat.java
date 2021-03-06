package uk.ac.sanger.scgcf.barcodegenerator;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.databind.util.ISO8601Utils;

import java.text.FieldPosition;
import java.util.Date;

/**
 * Generated class to handle the date format serialisation.
 * 
 * @author ke4
 *
 */
public class RFC3339DateFormat extends ISO8601DateFormat {

    private static final long serialVersionUID = -6127953831318507056L;

    // Same as ISO8601DateFormat but serializing milliseconds.
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo,
            FieldPosition fieldPosition) {
        String value = ISO8601Utils.format(date, true);
        toAppendTo.append(value);
        return toAppendTo;
    }
}
