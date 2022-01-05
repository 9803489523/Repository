package TestReflection;

import Contracts.Contract;
import Repository.Repository;
import WorkWithCSV.CsvWorker;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * class of testing Injector
 * @author Aleksandr Nozdryuhin
 * @version 4.12
 */
public class InjectorTest {
    /**
     * test of inject method
     */
    @Test
    public void inject() throws InjectorException {
        String[] checkingValidators=
                {"StartOfContractCheck","EndOfContractCheck","OwnerAgeChecker",
                        "CheckingOwnerPassport","IdChecker","ContractNumberChecker"
                };
        Injector injector=new Injector();
        Repository<Contract> repository=injector.inject(new Repository<Contract>());
        assertEquals("ShellSorter",repository.getSorter().getClass().getSimpleName());
        CsvWorker csvWorker=injector.inject(new CsvWorker());
        System.out.println(csvWorker.getValidators());
        CsvWorker csvWorker1=injector.inject(new CsvWorker());
        System.out.println(csvWorker1.getValidators());
        CsvWorker csvWorker2=injector.inject(new CsvWorker());
        for(int i=0;i<checkingValidators.length;i++){
            assertEquals(checkingValidators[i],csvWorker.getValidators().get(i).getClass().getSimpleName());
            assertEquals(checkingValidators[i],csvWorker1.getValidators().get(i).getClass().getSimpleName());
            assertEquals(checkingValidators[i],csvWorker2.getValidators().get(i).getClass().getSimpleName());
        }
    }
}