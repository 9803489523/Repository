
package Repository;
import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import PeoplesInformation.Passport;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
/**
 * class of testing Repository
 * @author Aleksandr Nozdryuhin
 * @version 4.12
 */

public class RepositoryTest {
    /**
     * @value this field store TV channels in String array format
     */
    String[] channelsArray={"CTC","Россия 1","НТВ","5 канал","МАТЧ ТВ","1 канал","МАТЧ Премьер"};
    /**
     * @value this field convert TV channels on to List<String> (It's necessary for class DigitalTv)
     */
    List<String> channels= Arrays.stream(channelsArray).toList();
    /**
     * @value this field store FIO of contract owners
     */
    String[] fio={
            "Давыдов Алексей Святославович",
            "Летов Игорь Федорович",
            "Крикунов Денис Денисович",
            "Соболев Илья Алексеевич",
            "Елизаров Михаил Денисович",
            "Харитонов Алексей Юрьевич",
            "Данилов Юрий Максимович",
            "Горбачев Николай Артемович",
            "Новиков Николай Маркович",
            "Попов Дмитрий Олегович",
            };
    /**
     * @value this field store passport data of contract owners
     */
    Passport[] passports={
            new Passport("2014","146 900"),new Passport("201","200 359"),
            new Passport("2014","125 127"),new Passport("2014","200 045"),
            new Passport("2015","325 789"),new Passport("2015","111 007"),
            new Passport("2015","322 147"),new Passport("2015","411 782"),
            new Passport("2015","899 417"),new Passport("2015","791 457")
    };
    /**
     * @value this field store contract owners
     */
    Human[] humans={
            new Human(1,fio[1],2000,11,12,passports[1]),
            new Human(2,fio[2],2002,4,19,passports[2]),
            new Human(3,fio[3],1999,8,10,passports[3]),
            new Human(4,fio[4],2002,4,9,passports[4]),
            new Human(5,fio[5],1987,3,5,passports[5]),
            new Human(6,fio[6],2001,9,1,passports[6]),
            new Human(7,fio[7],2000,7,21,passports[7]),
            new Human(8,fio[8],1990,10,7,passports[8]),
            new Human(9,fio[9],1994,12,6,passports[9]),
            new Human(10,fio[0],1997,1,20,passports[0]),
    };
    /**
     * @value this field store digital tv contracts in array format
     */
    DigitalTV[] digitalTVContracts={
            new DigitalTV(1,2019,11,22,2020,11,22,1202120,humans[1],channels),
            new DigitalTV(2,2020,10,11,2021,3,9,1202121,humans[2],channels),
            new DigitalTV(3,2018,2,19,2020,7,25,1202122,humans[3],channels),
            new DigitalTV(4,2019,5,10,2020,6,11,1202123,humans[4],channels),
            new DigitalTV(5,2017,4,2,2018,2,8,1202124,humans[5],channels),
            new DigitalTV(6,2018,1,18,2019,10,6,1202125,humans[6],channels),
            new DigitalTV(7,2019,3,12,2020,12,7,1202126,humans[7],channels),
            new DigitalTV(8,2017,7,13,2019,1,3,1202127,humans[8],channels),
            new DigitalTV(9,2019,12,4,2020,4,18,1202128,humans[9],channels),
            new DigitalTV(10,2020,6,6,2021,8,4,1202129,humans[0],channels),
    };
    /**
     * @value this field store mobile connections contracts in array format
     */
    MobileConnection[] mobileConnectionContracts={
            new MobileConnection(11,2016,11,15,2020,11,20,5770000,humans[1],100,300,5),
            new MobileConnection(12,2014,1,14,2021,10,11,5770001,humans[2],200,500,10),
            new MobileConnection(13,2018,6,21,2019,8,1,5770002,humans[3],300,500,20),
            new MobileConnection(14,2017,8,25,2019,4,26,5770003,humans[4],500,400,10),
            new MobileConnection(15,2019,10,27,2020,2,14,5770004,humans[5],900,800,15),
            new MobileConnection(16,2019,12,24,2020,9,12,5770005,humans[6],100,1000,30),
            new MobileConnection(17,2020,4,12,2021,1,15,5770006,humans[0],800,200,10),
            new MobileConnection(18,2015,3,8,2018,3,18,5770007,humans[8],200,100,11),
            new MobileConnection(19,2019,2,6,2021,5,2,5770008,humans[7],300,300,18)
    };
    /**
     * @value this field store wired internet contracts in array format
     */
    WiredInternet[] wiredInternetContracts={
            new WiredInternet(20,2019,1,22,2020,12,20,9006770,humans[1],75),
            new WiredInternet(21,2014,2,16,2021,8,2,9006771,humans[2],100),
            new WiredInternet(22,2012,10,8,2019,4,23,9006772,humans[3],200),
            new WiredInternet(23,2018,8,27,2020,5,5,9006773,humans[4],50),
            new WiredInternet(24,2016,7,11,2021,6,1,9006774,humans[5],50),
            new WiredInternet(25,2015,6,4,2018,9,16,9006775,humans[6],100),
            new WiredInternet(26,2014,3,5,2017,7,18,9006776,humans[7],75),
            new WiredInternet(27,2010,2,23,2015,8,14,9006777,humans[8],150),
            new WiredInternet(28,2018,9,20,2021,4,3,9006778,humans[9],75)
    };
    /**
     * @value this field store all contracts in Repository format
     */
    Repository<Contract> repository= new Repository<>();
    /**
     * @value this field store digital tv contracts in Repository format
     */
    Repository<DigitalTV> digitalTVRepository=new Repository<DigitalTV>();
    /**
     * @value this field store mobile connection contracts in Repository format
     */
    Repository<MobileConnection> mobileConnectionRepository=new Repository<MobileConnection>();
    /**
     * @value this field store wired internet contracts in Repository format
     */
    Repository<WiredInternet> wiredInternetRepository=new Repository<WiredInternet>();
    /**
    * this method fill in repository of all contracts
    */
    private void fillInRepository(){
        repository.clear();
        repository.add(digitalTVContracts[0]);
        for(int i=0;i<9;i++){
            repository.add(digitalTVContracts[i+1]);
            repository.add(mobileConnectionContracts[i]);
            repository.add(wiredInternetContracts[i]);
        }
    }
    /**
     * this method fill in repository of digital tv contracts
     */
    private void fillInDigitalTV(){
        digitalTVRepository.clear();
        for (DigitalTV digitalTVContract : digitalTVContracts) {
            digitalTVRepository.add(digitalTVContract);
        }
    }
    /**
     * this method fill in repository of wired internet contracts
     */
    private void fillInWiredInternet(){
        wiredInternetRepository.clear();
        for (WiredInternet wiredInternetContract : wiredInternetContracts) {
            wiredInternetRepository.add(wiredInternetContract);
        }
    }
    /**
     * this method fill in repository of mobile connection contracts
     */
    private void fillInMobileConnection(){
        mobileConnectionRepository.clear();
        for (MobileConnection mobileConnectionContract : mobileConnectionContracts) {
            mobileConnectionRepository.add(mobileConnectionContract);
        }
    }

    /**
     * this test check methods {@link Repository#add(Contract[])},{@link Repository#getSize()} for all types of repository
     * {@link RepositoryTest#repository#digitalTVRepository#wiredInternetRepository#mobileConnectionRepository}
     */
    @Test
    public void addAndGetSize() {
        // add test and check size for all contracts repository
        repository.add(digitalTVContracts[0]);
        assertEquals(1,repository.getSize());
        repository.add(digitalTVContracts[1],mobileConnectionContracts[0],wiredInternetContracts[0]);
        assertEquals(4,repository.getSize());
        for(int i=1;i<9;i++){
            repository.add(digitalTVContracts[i+1]);
            repository.add(mobileConnectionContracts[i]);
            repository.add(wiredInternetContracts[i]);
        }
        assertEquals(28,repository.getSize());
        // add test and check size for digital tv contracts repository
        digitalTVRepository.add(digitalTVContracts[0]);
        assertEquals(1,digitalTVRepository.getSize());
        digitalTVRepository.add(digitalTVContracts[1],digitalTVContracts[2],digitalTVContracts[3]);
        assertEquals(4,digitalTVRepository.getSize());
        for(int i=4;i<digitalTVContracts.length;i++){
            digitalTVRepository.add(digitalTVContracts[i]);
        }
        assertEquals(10,digitalTVRepository.getSize());
        // add test and check size for wired internet contracts repository
        wiredInternetRepository.add(wiredInternetContracts[0]);
        assertEquals(1,wiredInternetRepository.getSize());
        wiredInternetRepository.add(wiredInternetContracts[1],wiredInternetContracts[2]);
        assertEquals(3,wiredInternetRepository.getSize());
        for(int i=3;i<wiredInternetContracts.length;i++){
            wiredInternetRepository.add(wiredInternetContracts[i]);
        }
        assertEquals(9,wiredInternetRepository.getSize());
        // add test and check size for mobile connection contracts repository
        mobileConnectionRepository.add(mobileConnectionContracts[0]);
        assertEquals(1,mobileConnectionRepository.getSize());
        mobileConnectionRepository.add(mobileConnectionContracts[1],mobileConnectionContracts[2],mobileConnectionContracts[3],mobileConnectionContracts[4]);
        assertEquals(5,mobileConnectionRepository.getSize());
        for(int i=5;i<mobileConnectionContracts.length;i++){
            mobileConnectionRepository.add(mobileConnectionContracts[i]);
        }
        assertEquals(9,mobileConnectionRepository.getSize());
    }
    /**
     * this test check methods {@link Repository#remove(int)}, {@link Repository#getSize()}  for all types of repository
     * {@link RepositoryTest#repository#digitalTVRepository#wiredInternetRepository#mobileConnectionRepository}
     */

    @Test
    public void removeAndGetSize() {
        // remove test and check size for all contracts repository
        fillInRepository();
        assertTrue(repository.remove(1));
        assertEquals(27,repository.getSize());
        assertTrue(repository.remove(28));
        assertEquals(26,repository.getSize());
        assertFalse(repository.remove(100));
        assertEquals(26,repository.getSize());
        for(int i=2;i<10;i++){
            assertTrue(repository.remove(i));
        }
        assertEquals(18,repository.getSize());
        // remove test and check size for digital tv contracts repository
        fillInDigitalTV();
        assertTrue(digitalTVRepository.remove(5));
        assertEquals(9,digitalTVRepository.getSize());
        assertTrue(digitalTVRepository.remove(4));
        assertEquals(8,digitalTVRepository.getSize());
        assertFalse(digitalTVRepository.remove(100));
        assertFalse(digitalTVRepository.remove(4));
        assertEquals(8,digitalTVRepository.getSize());
        assertTrue(digitalTVRepository.remove(9));
        assertEquals(7,digitalTVRepository.getSize());
        // remove test and check size for wired internet contracts repository
        fillInWiredInternet();
        assertTrue(wiredInternetRepository.remove(20));
        assertEquals(8,wiredInternetRepository.getSize());
        assertTrue(wiredInternetRepository.remove(25));
        assertEquals(7,wiredInternetRepository.getSize());
        assertTrue(wiredInternetRepository.remove(28));
        assertFalse(wiredInternetRepository.remove(20));
        assertEquals(6,wiredInternetRepository.getSize());
        assertFalse(wiredInternetRepository.remove(9));
        assertEquals(6,wiredInternetRepository.getSize());
        // remove test and check size for mobile connection contracts repository
        fillInMobileConnection();
        assertTrue(mobileConnectionRepository.remove(11));
        assertEquals(8,mobileConnectionRepository.getSize());
        assertTrue(mobileConnectionRepository.remove(19));
        assertEquals(7,mobileConnectionRepository.getSize());
        assertTrue(mobileConnectionRepository.remove(15));
        assertFalse(mobileConnectionRepository.remove(20));
        assertEquals(6,mobileConnectionRepository.getSize());
        assertFalse(mobileConnectionRepository.remove(154));
        assertEquals(6,mobileConnectionRepository.getSize());
    }
    /**
     * this test check methods {@link Repository#isEmpty()}, {@link Repository#clear()}, {@link Repository#getSize()} for all types of repository
     * {@link RepositoryTest#repository#digitalTVRepository#wiredInternetRepository#mobileConnectionRepository}
     */
    @Test
    public void isEmptyAndClearAndGetSize() {
        // test for method isEmpty, Clear and check size for all contracts repository
        fillInRepository();
        assertFalse(repository.isEmpty());
        repository.clear();
        assertTrue(repository.isEmpty());
        assertEquals(0,repository.getSize());
        // test for method isEmpty, Clear and check size for digital tv contracts repository
        fillInDigitalTV();
        assertFalse(digitalTVRepository.isEmpty());
        digitalTVRepository.clear();
        assertTrue(digitalTVRepository.isEmpty());
        assertEquals(0,digitalTVRepository.getSize());
        // test for method isEmpty, Clear and check size for wired internet contracts repository
        fillInWiredInternet();
        assertFalse(wiredInternetRepository.isEmpty());
        wiredInternetRepository.clear();
        assertTrue(wiredInternetRepository.isEmpty());
        assertEquals(0,wiredInternetRepository.getSize());
        // test for method isEmpty, Clear and check size for mobile connection contracts repository
        fillInMobileConnection();
        assertFalse(mobileConnectionRepository.isEmpty());
        mobileConnectionRepository.clear();
        assertTrue(mobileConnectionRepository.isEmpty());
        assertEquals(0,mobileConnectionRepository.getSize());
    }
    /**
     * this test check method {@link Repository#get(int)} for all types of repository
     * {@link RepositoryTest#repository#digitalTVRepository#wiredInternetRepository#mobileConnectionRepository}
     */
    @Test
    public void get() {
        // get test for all contracts repository
        fillInRepository();
        assertEquals(digitalTVContracts[0],repository.get(1));
        assertEquals(wiredInternetContracts[8],repository.get(28));
        assertEquals(digitalTVContracts[1],repository.get(2));
        assertEquals(mobileConnectionContracts[0],repository.get(11));
        assertEquals(wiredInternetContracts[7],repository.get(27));
        assertNull(repository.get(40));
        // get test for digital tv contracts repository
        fillInDigitalTV();
        for(int i=0;i<digitalTVContracts.length;i++){
            assertEquals(digitalTVContracts[i],digitalTVRepository.get(i+1));
        }
        assertNull(digitalTVRepository.get(-5));
        // get test for mobile connection contracts repository
        fillInMobileConnection();
        for(int i=0;i<mobileConnectionContracts.length;i++){
            assertEquals(mobileConnectionContracts[i],mobileConnectionRepository.get(i+11));
        }
        assertNull(mobileConnectionRepository.get(30));
        // get test for mobile wired internet repository
        fillInWiredInternet();
        for (int i=0;i<wiredInternetContracts.length;i++){
            assertEquals(wiredInternetContracts[i],wiredInternetRepository.get(i+20));
        }
        assertNull(wiredInternetRepository.get(29));
    }
}