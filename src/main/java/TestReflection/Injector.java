package TestReflection;


import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * class Injector
 * this class need to automatic injection values into fields
 * with field pool - pool of classes, which may inject into field
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
@Configuration(packages = {"Sorts", "Contracts", "WorkWithCSV"})
public class Injector {
    /**
     * field of classes pool, which may inject into field
     */
    private List<Object> pool;

    /**
     * method, which inject to field automatic value
     * @param object, object we want to inject value
     * @param <T>, object generic
     * @return object with injection field
     */
    public <T> T inject(T object) throws InjectorException {
        try {

            fillInPool();
            Class<?> clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Collection<Object> truePool = new ArrayList<>();
            for (Field field : fields) {
                Autoinjectable annotation = field.getAnnotation(Autoinjectable.class);
                CollectionField complex = field.getAnnotation(CollectionField.class);
                if (annotation != null) {
                    if (complex == null) {
                        for (Object poolObj : pool) {
                            if (Arrays.equals(poolObj.getClass().getInterfaces(), clazz.getInterfaces()) || poolObj.getClass().getSuperclass().equals(clazz.getSuperclass())) {
                                truePool.add(poolObj);
                            }
                        }
                        if (truePool.size() == 1) {
                            field.setAccessible(true);
                            field.set(object, truePool.toArray()[0]);
                            field.setAccessible(false);
                        } else {
                            if (truePool.size() < 1)
                                throw new InjectorException();
                            else {
                                for (Object tp : truePool) {
                                    if (tp.getClass().getSimpleName().equals(annotation.defaultField())) {
                                        field.setAccessible(true);
                                        field.set(object, tp);
                                        field.setAccessible(false);
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        for (Object poolObj : pool) {
                            if (poolObj.getClass().getSuperclass().getSimpleName().equals(complex.genericsParametersName()) ||
                                    checkingInterfaces(poolObj, complex.genericsParametersName()) ||
                                    poolObj.getClass().getSimpleName().equals(complex.genericsParametersName())
                            ) {
                                truePool.add(poolObj);
                            }
                        }
                        field.setAccessible(true);
                        Class<?> classForNewInstance = field.getDeclaringClass();
                        field.set(object, truePool);
                        field.setAccessible(false);
                    }

                }
                //truePool.clear();
            }
            return object;
        }
        catch (InvocationTargetException| NoSuchMethodException| InstantiationException| IllegalAccessException| InjectorException e){
            throw new InjectorException();
        }
    }

    /**
     * method to fill in pool of classes, which we may inject to field
     */
    private void fillInPool() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> injector=Injector.class;
        pool=new ArrayList<>();
        List<Object> classes=new ArrayList<>();
        Configuration configuration=injector.getAnnotation(Configuration.class);
        for(String s:configuration.packages()){
            for(Class<?> cl:findAllClassesUsingReflectionsLibrary(s))
                if(cl.getAnnotation(FunctionalInterface.class)==null&& cl.getAnnotation(WithDefaultConstructor.class)!=null)
                {
                    classes.add(cl.getDeclaredConstructor().newInstance());
                }
        }
        pool.addAll(classes);
    }

    /**
     * method to finding all classes in package
     * @param packageName, name of package
     * @return set of all classes in package
     */
    private Set<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }

    /**
     * checking whether interface of class o1 name namedObj
     * @param o1, class
     * @param namedObj, name
     * @return result of checking
     */
    private boolean checkingInterfaces(Object o1,String namedObj){
        for(Class<?> c: o1.getClass().getInterfaces()){
            if(c.getSimpleName().equals(namedObj))
                return true;
        }
        return false;
    }
}
