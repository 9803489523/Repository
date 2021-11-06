package Sorts;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import PeoplesInformation.Passport;
import Repository.Repository;
import org.junit.Test;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * class of testing all sorts (BubbleSorter, SelectionSorter, ShellSorter)
 * @author Aleksandr Nozdryuhin
 * @version 4.12
 */
public class SortsTest {
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
     * method, which fill in repository of all contracts
     */
    private void fillInRepository(){
        repository.clear();
        repository.add(mobileConnectionContracts);
        repository.add(digitalTVContracts);
        repository.add(wiredInternetContracts);
    }

    /**
     * method, which fill in repository of digital TV contracts
     */
    private void fillInDigitalTVRepository(){
        digitalTVRepository.clear();
        digitalTVRepository.add(digitalTVContracts);
    }

    /**
     * method, which fill in repository of wired internet contracts
     */
    private void fillInWiredInternetRepository(){
        wiredInternetRepository.clear();
        wiredInternetRepository.add(wiredInternetContracts);
    }

    /**
     * method, which fill in repository of mobile connection contracts
     */
    private void fillInMobileConnectionRepository(){
        mobileConnectionRepository.clear();
        mobileConnectionRepository.add(mobileConnectionContracts);
    }
    /**
     * this test check method 'sort' in classes BubbleSorter, SelectionSorter, ShellSorter
     * {@link BubbleSorter#sort(Comparator, Repository)}
     * {@link ShellSorter#sort(Comparator, Repository)}
     * {@link SelectionSorter#sort(Comparator, Repository)}
     */
    @Test
    public void sort() {
        BubbleSorter<Contract> contractBubbleSorter=new BubbleSorter<>();
        BubbleSorter<DigitalTV> digitalTVBubbleSorter=new BubbleSorter<>();
        BubbleSorter<MobileConnection> mobileConnectionBubbleSorter=new BubbleSorter<>();
        BubbleSorter<WiredInternet> wiredInternetBubbleSorter=new BubbleSorter<>();
        ShellSorter<Contract> contractShellSorter=new ShellSorter<>();
        ShellSorter<DigitalTV> digitalTVShellSorter=new ShellSorter<>();
        ShellSorter<MobileConnection> mobileConnectionShellSorter=new ShellSorter<>();
        ShellSorter<WiredInternet> wiredInternetShellSorter=new ShellSorter<>();
        SelectionSorter<Contract> contractSelectionSorter=new SelectionSorter<>();
        SelectionSorter<DigitalTV> digitalTVSelectionSorter=new SelectionSorter<>();
        SelectionSorter<MobileConnection> mobileConnectionSelectionSorter=new SelectionSorter<>();
        SelectionSorter<WiredInternet> wiredInternetSelectionSorter=new SelectionSorter<>();
        //test of all contract sort
        //for bubble sort
        fillInRepository();
        contractBubbleSorter.sort(new Comparator<Contract>() {
            @Override
            public int compare(Contract o1, Contract o2) {
                return Integer.compare(o1.getId(),o2.getId());
            }
        },repository);
        for(int i=0;i<repository.getSize();i++){
            assertEquals(i+1,repository.getContracts()[i].getId());
        }
        //for Shell's sort
        fillInRepository();
        contractShellSorter.sort(new Comparator<Contract>() {
            @Override
            public int compare(Contract o1, Contract o2) {
                return Integer.compare(o1.getId(),o2.getId());
            }
        },repository);
        for(int i=0;i<repository.getSize();i++){
            assertEquals(i+1,repository.getContracts()[i].getId());
        }
        //for selection sort
        fillInRepository();
        contractSelectionSorter.sort(new Comparator<Contract>() {
            @Override
            public int compare(Contract o1, Contract o2) {
                return Integer.compare(o1.getId(),o2.getId());
            }
        },repository);
        for(int i=0;i<repository.getSize();i++){
            assertEquals(i+1,repository.getContracts()[i].getId());
        }
        //test of digital TV contract sort
        //for bubble sort
        fillInDigitalTVRepository();
        digitalTVBubbleSorter.sort(new Comparator<DigitalTV>() {
            @Override
            public int compare(DigitalTV o1, DigitalTV o2) {
                return Integer.compare(o2.getOwner().getAge(),o1.getOwner().getAge());
            }
        }, digitalTVRepository);
        assertEquals(34,digitalTVRepository.getContracts()[0].getOwner().getAge());
        assertEquals(31,digitalTVRepository.getContracts()[1].getOwner().getAge());
        assertEquals(26,digitalTVRepository.getContracts()[2].getOwner().getAge());
        assertEquals(24,digitalTVRepository.getContracts()[3].getOwner().getAge());
        assertEquals(22,digitalTVRepository.getContracts()[4].getOwner().getAge());
        assertEquals(21,digitalTVRepository.getContracts()[5].getOwner().getAge());
        assertEquals(20,digitalTVRepository.getContracts()[6].getOwner().getAge());
        assertEquals(20,digitalTVRepository.getContracts()[7].getOwner().getAge());
        assertEquals(19,digitalTVRepository.getContracts()[8].getOwner().getAge());
        assertEquals(19,digitalTVRepository.getContracts()[9].getOwner().getAge());
        //for Shell's sort
        fillInDigitalTVRepository();
        digitalTVShellSorter.sort(new Comparator<DigitalTV>() {
            @Override
            public int compare(DigitalTV o1, DigitalTV o2) {
                return Integer.compare(o2.getOwner().getAge(),o1.getOwner().getAge());
            }
        }, digitalTVRepository);
        assertEquals(34,digitalTVRepository.getContracts()[0].getOwner().getAge());
        assertEquals(31,digitalTVRepository.getContracts()[1].getOwner().getAge());
        assertEquals(26,digitalTVRepository.getContracts()[2].getOwner().getAge());
        assertEquals(24,digitalTVRepository.getContracts()[3].getOwner().getAge());
        assertEquals(22,digitalTVRepository.getContracts()[4].getOwner().getAge());
        assertEquals(21,digitalTVRepository.getContracts()[5].getOwner().getAge());
        assertEquals(20,digitalTVRepository.getContracts()[6].getOwner().getAge());
        assertEquals(20,digitalTVRepository.getContracts()[7].getOwner().getAge());
        assertEquals(19,digitalTVRepository.getContracts()[8].getOwner().getAge());
        assertEquals(19,digitalTVRepository.getContracts()[9].getOwner().getAge());
        //for selection sort
        fillInDigitalTVRepository();
        digitalTVSelectionSorter.sort(new Comparator<DigitalTV>() {
            @Override
            public int compare(DigitalTV o1, DigitalTV o2) {
                return Integer.compare(o2.getOwner().getAge(),o1.getOwner().getAge());
            }
        }, digitalTVRepository);
        assertEquals(34,digitalTVRepository.getContracts()[0].getOwner().getAge());
        assertEquals(31,digitalTVRepository.getContracts()[1].getOwner().getAge());
        assertEquals(26,digitalTVRepository.getContracts()[2].getOwner().getAge());
        assertEquals(24,digitalTVRepository.getContracts()[3].getOwner().getAge());
        assertEquals(22,digitalTVRepository.getContracts()[4].getOwner().getAge());
        assertEquals(21,digitalTVRepository.getContracts()[5].getOwner().getAge());
        assertEquals(20,digitalTVRepository.getContracts()[6].getOwner().getAge());
        assertEquals(20,digitalTVRepository.getContracts()[7].getOwner().getAge());
        assertEquals(19,digitalTVRepository.getContracts()[8].getOwner().getAge());
        assertEquals(19,digitalTVRepository.getContracts()[9].getOwner().getAge());
        //test of mobile connection contract sort
        //for bubble sort
        fillInMobileConnectionRepository();
        mobileConnectionBubbleSorter.sort(new Comparator<MobileConnection>() {
            @Override
            public int compare(MobileConnection o1, MobileConnection o2) {
                if(o1.getNumberOfMinutes()==o2.getNumberOfMinutes()) {
                    if (o1.getNumberOfSMS()==o2.getNumberOfSMS())
                        return Integer.compare(o1.getInternetTraffic(),o2.getInternetTraffic());
                    else
                        return Integer.compare(o1.getNumberOfSMS(),o2.getNumberOfSMS());
                }
                else
                    return Integer.compare(o1.getNumberOfMinutes(),o2.getNumberOfMinutes());
            }
        }, mobileConnectionRepository);
        assertEquals(mobileConnectionContracts[0],mobileConnectionRepository.getContracts()[0]);
        assertEquals(mobileConnectionContracts[5],mobileConnectionRepository.getContracts()[1]);
        assertEquals(mobileConnectionContracts[7],mobileConnectionRepository.getContracts()[2]);
        assertEquals(mobileConnectionContracts[1],mobileConnectionRepository.getContracts()[3]);
        assertEquals(mobileConnectionContracts[8],mobileConnectionRepository.getContracts()[4]);
        assertEquals(mobileConnectionContracts[2],mobileConnectionRepository.getContracts()[5]);
        assertEquals(mobileConnectionContracts[3],mobileConnectionRepository.getContracts()[6]);
        assertEquals(mobileConnectionContracts[6],mobileConnectionRepository.getContracts()[7]);
        assertEquals(mobileConnectionContracts[4],mobileConnectionRepository.getContracts()[8]);
        //for Shell's sort
        fillInMobileConnectionRepository();
        mobileConnectionShellSorter.sort(new Comparator<MobileConnection>() {
            @Override
            public int compare(MobileConnection o1, MobileConnection o2) {
                if(o1.getNumberOfMinutes()==o2.getNumberOfMinutes()) {
                    if (o1.getNumberOfSMS()==o2.getNumberOfSMS())
                        return Integer.compare(o1.getInternetTraffic(),o2.getInternetTraffic());
                    else
                        return Integer.compare(o1.getNumberOfSMS(),o2.getNumberOfSMS());
                }
                else
                    return Integer.compare(o1.getNumberOfMinutes(),o2.getNumberOfMinutes());
            }
        }, mobileConnectionRepository);
        assertEquals(mobileConnectionContracts[0],mobileConnectionRepository.getContracts()[0]);
        assertEquals(mobileConnectionContracts[5],mobileConnectionRepository.getContracts()[1]);
        assertEquals(mobileConnectionContracts[7],mobileConnectionRepository.getContracts()[2]);
        assertEquals(mobileConnectionContracts[1],mobileConnectionRepository.getContracts()[3]);
        assertEquals(mobileConnectionContracts[8],mobileConnectionRepository.getContracts()[4]);
        assertEquals(mobileConnectionContracts[2],mobileConnectionRepository.getContracts()[5]);
        assertEquals(mobileConnectionContracts[3],mobileConnectionRepository.getContracts()[6]);
        assertEquals(mobileConnectionContracts[6],mobileConnectionRepository.getContracts()[7]);
        assertEquals(mobileConnectionContracts[4],mobileConnectionRepository.getContracts()[8]);
        //for selection sort
        fillInMobileConnectionRepository();
        mobileConnectionSelectionSorter.sort(new Comparator<MobileConnection>() {
            @Override
            public int compare(MobileConnection o1, MobileConnection o2) {
                if(o1.getNumberOfMinutes()==o2.getNumberOfMinutes()) {
                    if (o1.getNumberOfSMS()==o2.getNumberOfSMS())
                        return Integer.compare(o1.getInternetTraffic(),o2.getInternetTraffic());
                    else
                        return Integer.compare(o1.getNumberOfSMS(),o2.getNumberOfSMS());
                }
                else
                    return Integer.compare(o1.getNumberOfMinutes(),o2.getNumberOfMinutes());
            }
        }, mobileConnectionRepository);
        assertEquals(mobileConnectionContracts[0],mobileConnectionRepository.getContracts()[0]);
        assertEquals(mobileConnectionContracts[5],mobileConnectionRepository.getContracts()[1]);
        assertEquals(mobileConnectionContracts[7],mobileConnectionRepository.getContracts()[2]);
        assertEquals(mobileConnectionContracts[1],mobileConnectionRepository.getContracts()[3]);
        assertEquals(mobileConnectionContracts[8],mobileConnectionRepository.getContracts()[4]);
        assertEquals(mobileConnectionContracts[2],mobileConnectionRepository.getContracts()[5]);
        assertEquals(mobileConnectionContracts[3],mobileConnectionRepository.getContracts()[6]);
        assertEquals(mobileConnectionContracts[6],mobileConnectionRepository.getContracts()[7]);
        assertEquals(mobileConnectionContracts[4],mobileConnectionRepository.getContracts()[8]);
        //test of wired internet contract sort
        //for bubble sort
        fillInWiredInternetRepository();
        wiredInternetBubbleSorter.sort(new Comparator<WiredInternet>() {
            @Override
            public int compare(WiredInternet o1, WiredInternet o2) {
                return Integer.compare(o2.getConnectionSpeed(),o1.getConnectionSpeed());
            }
        }, wiredInternetRepository);
        assertEquals(22,wiredInternetRepository.getContracts()[0].getId());
        assertEquals(24,wiredInternetRepository.getContracts()[1].getId());
        assertEquals(27,wiredInternetRepository.getContracts()[2].getId());
        assertEquals(21,wiredInternetRepository.getContracts()[3].getId());
        assertEquals(20,wiredInternetRepository.getContracts()[4].getId());
        assertEquals(23,wiredInternetRepository.getContracts()[5].getId());
        assertEquals(26,wiredInternetRepository.getContracts()[6].getId());
        assertEquals(25,wiredInternetRepository.getContracts()[7].getId());
        assertEquals(28,wiredInternetRepository.getContracts()[8].getId());
        //for Shell's sort
        fillInWiredInternetRepository();
        wiredInternetShellSorter.sort(new Comparator<WiredInternet>() {
            @Override
            public int compare(WiredInternet o1, WiredInternet o2) {
                return Integer.compare(o2.getConnectionSpeed(),o1.getConnectionSpeed());
            }
        }, wiredInternetRepository);
        assertEquals(22,wiredInternetRepository.getContracts()[0].getId());
        assertEquals(24,wiredInternetRepository.getContracts()[1].getId());
        assertEquals(27,wiredInternetRepository.getContracts()[2].getId());
        assertEquals(21,wiredInternetRepository.getContracts()[3].getId());
        assertEquals(20,wiredInternetRepository.getContracts()[4].getId());
        assertEquals(23,wiredInternetRepository.getContracts()[5].getId());
        assertEquals(26,wiredInternetRepository.getContracts()[6].getId());
        assertEquals(25,wiredInternetRepository.getContracts()[7].getId());
        assertEquals(28,wiredInternetRepository.getContracts()[8].getId());
        //for selection sort
        fillInWiredInternetRepository();
        wiredInternetShellSorter.sort(new Comparator<WiredInternet>() {
            @Override
            public int compare(WiredInternet o1, WiredInternet o2) {
                return Integer.compare(o2.getConnectionSpeed(),o1.getConnectionSpeed());
            }
        }, wiredInternetRepository);
        assertEquals(22,wiredInternetRepository.getContracts()[0].getId());
        assertEquals(24,wiredInternetRepository.getContracts()[1].getId());
        assertEquals(27,wiredInternetRepository.getContracts()[2].getId());
        assertEquals(21,wiredInternetRepository.getContracts()[3].getId());
        assertEquals(20,wiredInternetRepository.getContracts()[4].getId());
        assertEquals(23,wiredInternetRepository.getContracts()[5].getId());
        assertEquals(26,wiredInternetRepository.getContracts()[6].getId());
        assertEquals(25,wiredInternetRepository.getContracts()[7].getId());
        assertEquals(28,wiredInternetRepository.getContracts()[8].getId());
    }
}