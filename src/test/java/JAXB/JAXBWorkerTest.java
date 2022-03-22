package JAXB;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import Repository.Repository;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing JaxbWorker class
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class JAXBWorkerTest {
    /**
     * @value this field store TV channels in String array format
     */
    static String[] channelsArray={"CTC","Россия 1","НТВ","5 канал","МАТЧ ТВ","1 канал","МАТЧ Премьер"};
    /**
     * @value this field convert TV channels on to List<String> (It's necessary for class DigitalTv)
     */
    static List<String> channels= Arrays.stream(channelsArray).toList();
    /**
     * @value this field store FIO of contract owners
     */
    static  String[] fio={
            "Давыдов Алексей Святославович",
            "Летов Игорь Федорович",
            "Крикунов Денис Денисович",
            "Соболев Илья Алексеевич",
            "Елизаров Михаил Юрьевич",
            "Харитонов Алексей Денисович",
            "Данилов Юрий Максимович",
            "Горбачев Николай Артемович",
            "Новиков Николай Маркович",
            "Попов Дмитрий Олегович",
    };
    /**
     * @value this field store passport data of contract owners
     */
    static String[] passports={"2014 146 900","2011 200359","2014 125 127","2014 200045","2015 325 789","2015 111007",
            "2015 322 147", "2015 411782","2015 899 417","2015 791457"};

    /**
     * @value this field store contract owners
     */
    static Human[] humans={
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
    static DigitalTV[] digitalTVContracts={
            new DigitalTV(6,2019,11,22,2020,11,22,1202120,humans[1],channels),
            new DigitalTV(5,2020,10,11,2021,3,9,1202121,humans[2],channels),
            new DigitalTV(3,2018,2,19,2020,7,25,1202122,humans[3],channels),
            new DigitalTV(9,2019,5,10,2020,6,11,1202123,humans[4],channels),
            new DigitalTV(2,2017,4,2,2018,2,8,1202124,humans[5],channels),
            new DigitalTV(1,2018,1,18,2019,10,6,1202125,humans[6],channels),
            new DigitalTV(7,2019,3,12,2020,12,7,1202126,humans[7],channels),
            new DigitalTV(8,2017,7,13,2019,1,3,1202127,humans[8],channels),
            new DigitalTV(4,2019,12,4,2020,4,18,1202128,humans[9],channels),
            new DigitalTV(10,2020,6,6,2021,8,4,1202129,humans[0],channels),
    };
    /**
     * @value this field store mobile connections contracts in array format
     */
    static MobileConnection[] mobileConnectionContracts={
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
    static WiredInternet[] wiredInternetContracts={
            new WiredInternet(25,2019,1,22,2020,12,20,9006770,humans[1],75),
            new WiredInternet(21,2014,2,16,2021,8,2,9006771,humans[2],125),
            new WiredInternet(22,2012,10,8,2019,4,23,9006772,humans[3],200),
            new WiredInternet(28,2018,8,27,2020,5,5,9006773,humans[4],50),
            new WiredInternet(24,2016,7,11,2021,6,1,9006774,humans[5],175),
            new WiredInternet(20,2015,6,4,2018,9,16,9006775,humans[6],100),
            new WiredInternet(26,2014,3,5,2017,7,18,9006776,humans[7],80),
            new WiredInternet(27,2010,2,23,2015,8,14,9006777,humans[8],150),
            new WiredInternet(23,2018,9,20,2021,4,3,9006778,humans[9],90)
    };
    /**
     * @value this field store all contracts in Repository format
     */
    static Repository<Contract> repository= new Repository<>();
    /**
     * @value this field store digital tv contracts in Repository format
     */
    static Repository<DigitalTV> digitalTVRepository=new Repository<DigitalTV>();
    /**
     * @value this field store mobile connection contracts in Repository format
     */
    static Repository<MobileConnection> mobileConnectionRepository=new Repository<MobileConnection>();
    /**
     * @value this field store wired internet contracts in Repository format
     */
    static Repository<WiredInternet> wiredInternetRepository=new Repository<WiredInternet>();
    /**
     * this method fill in repository of all contracts
     */
    private static  void fillInRepository(){
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
    private  static void fillInDigitalTV(){
        digitalTVRepository.clear();
        for (DigitalTV digitalTVContract : digitalTVContracts) {
            digitalTVRepository.add(digitalTVContract);
        }
    }
    /**
     * this method fill in repository of wired internet contracts
     */
    private static  void fillInWiredInternet(){
        wiredInternetRepository.clear();
        for (WiredInternet wiredInternetContract : wiredInternetContracts) {
            wiredInternetRepository.add(wiredInternetContract);
        }
    }
    /**
     * this method fill in repository of mobile connection contracts
     */
    private static  void fillInMobileConnection(){
        mobileConnectionRepository.clear();
        for (MobileConnection mobileConnectionContract : mobileConnectionContracts) {
            mobileConnectionRepository.add(mobileConnectionContract);
        }
    }

    /**
     * method to compare two Repository
     */
    public <T extends Contract>void checkingTwoRepo(Repository<T> first, Repository<T> second){
        assertEquals(first.getSize(),second.getSize());
        for(int i=0;i<first.getSize();i++){
            assertEquals(first.get(i),second.get(i));
        }
    }

    /**
     * testing 2 methods: write and read
     */
    @Test
    public void writeAndRead() throws JAXBException, IOException {
        JAXBWorker<Contract> jaxbWorker=new JAXBWorker<>();
        JAXBWorker<DigitalTV> digitalTVJAXBWorker=new JAXBWorker<>();
        JAXBWorker<MobileConnection> mobileConnectionJAXBWorker=new JAXBWorker<>();
        JAXBWorker<WiredInternet> wiredInternetJAXBWorker=new JAXBWorker<>();
        fillInRepository();
        fillInDigitalTV();
        fillInMobileConnection();
        fillInWiredInternet();
        jaxbWorker.write(repository,"contract");
        digitalTVJAXBWorker.write(digitalTVRepository,"digitalTV");
        mobileConnectionJAXBWorker.write(mobileConnectionRepository,"mobile");
        wiredInternetJAXBWorker.write(wiredInternetRepository,"wired");
        Repository<Contract> repoContract=jaxbWorker.read("contract");
        Repository<DigitalTV> repoDigital=digitalTVJAXBWorker.read("digitalTV");
        Repository<MobileConnection> repoMobile=mobileConnectionJAXBWorker.read("mobile");
        Repository<WiredInternet> repoWired=wiredInternetJAXBWorker.read("wired");
        checkingTwoRepo(repoContract,repository);
        checkingTwoRepo(digitalTVRepository,repoDigital);
        checkingTwoRepo(mobileConnectionRepository,repoMobile);
        checkingTwoRepo(wiredInternetRepository,repoWired);
    }


}