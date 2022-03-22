package JAXB;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * class Adapter of localDate type
 * need to marshall and unmarshall localDate
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class DateAdapter extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
