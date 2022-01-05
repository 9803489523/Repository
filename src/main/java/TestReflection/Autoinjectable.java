package TestReflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * annotation to mark field with automatic injection
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autoinjectable {
    String defaultField() default "";
}
