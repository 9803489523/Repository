package ReflectionResources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * annotation to mark collections fields
 * with field to identify collection generic type
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionField {
    String genericsParametersName();
}
