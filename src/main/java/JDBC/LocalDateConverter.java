package JDBC;

import javax.persistence.AttributeConverter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Class to convert SQL date to LocalDate and conversely
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public  Date convertToDatabaseColumn(LocalDate localDate) {
        return Optional.ofNullable(localDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return Optional.ofNullable(date)
                .map(Date::toLocalDate)
                .orElse(null);
    }
}
