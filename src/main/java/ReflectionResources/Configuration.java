package ReflectionResources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * annotation to mark class-Injector
 * with field to mark packages, where we gonna search necessary classes
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Configuration {
    String[] packages() default {"sorts"};
}
