package ReflectionResources;


/**
 * class Injector Exception
 * without fields
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class InjectorException extends Exception{

    public InjectorException() {
    }

    public InjectorException(String message) {
        super(message);
    }

    public InjectorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InjectorException(IllegalArgumentException cause) {
        super(cause);
    }
    public InjectorException(IllegalAccessException cause) {
        super(cause);
    }

    protected InjectorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
