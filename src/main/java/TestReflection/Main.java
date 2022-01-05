package TestReflection;

import Contracts.Contract;
import Contracts.WiredInternet;
import Repository.Repository;
import Sorts.BubbleSorter;
import Sorts.SelectionSorter;
import WorkWithCSV.CsvWorker;
import WorkWithCSV.EndOfContractCheck;
import WorkWithCSV.IValidator;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InjectorException {
        Injector injector=new Injector();
        Repository<Contract> repository=injector.inject(new Repository<Contract>());
        System.out.println(repository.getSorter().getClass().getSimpleName());
        CsvWorker csvWorker=injector.inject(new CsvWorker());
        System.out.println(csvWorker.getValidators());
        CsvWorker csvWorker1=injector.inject(new CsvWorker());
        System.out.println(csvWorker1.getValidators());
        CsvWorker csvWorker2=injector.inject(new CsvWorker());
        System.out.println(csvWorker2.getValidators());










    }
    private static List<Class<?>> findAllClassesUsingReflectionsLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        return new ArrayList<>(reflections.getSubTypesOf(Object.class));
    }
}
