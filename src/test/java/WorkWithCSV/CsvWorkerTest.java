package WorkWithCSV;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import PeoplesInformation.Passport;
import Repository.Repository;
import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import static org.junit.Assert.*;
/**
 * class of  CsvWorkerTest
 * this class need to test all methods of class CsvWorker
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class CsvWorkerTest {
    /**
     * repository, which store all contract
     */
    Repository<Contract> contractRepository=new Repository<Contract>();
    /**
     * repository, which store digital tv contract
     */
    Repository<DigitalTV> digitalTVRepository=new Repository<DigitalTV>();
    /**
     * repository, which store wired internet contract
     */
    Repository<WiredInternet> wiredInternetRepository=new Repository<WiredInternet>();
    /**
     * repository, which store mobile connection contract
     */
    Repository<MobileConnection> mobileConnectionRepository=new Repository<MobileConnection>();
    /**
     * first variant of channels packet
     */
    ArrayList<String> firstPacket=new ArrayList<>();
    /**
     * second variant of channels packet
     */
    ArrayList<String> secondPacket=new ArrayList<>();

    /**
     * method which fill in all repositories
     */
    public void fillIN(){
        firstPacket.add("СТС");firstPacket.add("1 канал");firstPacket.add("Россия 1");
        secondPacket.add("1 канал");secondPacket.add("НТВ");secondPacket.add("МАТЧ ТВ");
        contractRepository.add(new WiredInternet(1,2009,11,9,
                2015,10,8,2114445,
                new Human(1,"Иванов Сергей Петрович",1979,5,16,
                        new Passport("2000","125789")),40));
        contractRepository.add(new MobileConnection(2,2010,11,9,
                2017,10,8,2114446,
                new Human(2,"Петров Сергей Петрович",1979,5,16,
                        new Passport("2001","125789")),54,120,48));
        contractRepository.add(new WiredInternet(3,2015,11,9,
                2016,10,8,2114447,
                new Human(3,"Смирнов Сергей Петрович",1979,5,16,
                        new Passport("2002","125789")),40));
        contractRepository.add(new WiredInternet(5,2017,11,9,
                2021,10,8,2114410,
                new Human(5,"Храмов Сергей Петрович",1979,5,16,
                        new Passport("2005","125789")),40));
        contractRepository.add(new DigitalTV(6,2007,11,9,
                2011,10,8,2114411,
                new Human(6,"Махин Сергей Петрович",1979,5,16,
                        new Passport("2006","125789")),secondPacket));
        contractRepository.add(new MobileConnection(7,2019,11,9,
                2020,10,8,2114412,
                new Human(7,"Троцкий Сергей Петрович",1979,5,16,
                        new Passport("2008","125789")),40,45,12));
        contractRepository.add(new DigitalTV(8,2014,11,9,
                2016,10,8,2114413,
                new Human(8,"Парков Сергей Петрович",1979,5,16,
                        new Passport("2009","125789")),firstPacket));
        digitalTVRepository.add(new DigitalTV(1,2014,11,9,
                2016,10,8,2114413,
                new Human(1,"Парков Сергей Петрович",1979,5,16,
                        new Passport("2009","125789")),firstPacket));
        digitalTVRepository.add(new DigitalTV(2,2014,11,9,
                2016,10,8,2114413,
                new Human(2,"Денисов Сергей Петрович",1979,5,16,
                        new Passport("2009","125789")),firstPacket));
        digitalTVRepository.add(new DigitalTV(3,2014,11,9,
                2016,10,8,2114413,
                new Human(3,"Иванов Сергей Петрович",1979,5,16,
                        new Passport("2009","125789")),firstPacket));
        digitalTVRepository.add(new DigitalTV(5,2014,11,9,
                2016,10,8,2114413,
                new Human(5,"Ечин Сергей Петрович",1979,5,16,
                        new Passport("2009","125789")),firstPacket));
       mobileConnectionRepository.add(new MobileConnection(7,2019,11,9,
                2020,10,8,2114412,
                new Human(7,"Ильин Сергей Петрович",1979,5,16,
                        new Passport("2008","125789")),40,45,12));
        mobileConnectionRepository.add(new MobileConnection(1,2019,11,9,
                2020,10,8,2114412,
                new Human(1,"Жданов Сергей Петрович",1979,5,16,
                        new Passport("2008","125781")),20,48,15));
        mobileConnectionRepository.add(new MobileConnection(4,2019,11,9,
                2020,10,8,2114412,
                new Human(4,"Попов Сергей Петрович",1979,5,16,
                        new Passport("2008","125788")),8,54,21));
        mobileConnectionRepository.add(new MobileConnection(6,2019,11,9,
                2020,10,8,2114412,
                new Human(6,"Зотов Сергей Петрович",1979,5,16,
                        new Passport("2008","125783")),36,100,51));
        wiredInternetRepository.add(new WiredInternet(3,2015,11,9,
                2016,10,8,2114447,
                new Human(3,"Смирнов Сергей Петрович",1979,5,16,
                        new Passport("2002","125789")),45));
        wiredInternetRepository.add(new WiredInternet(5,2015,11,9,
                2016,10,8,2114447,
                new Human(5,"Смирнов Сергей Петрович",1979,5,16,
                        new Passport("2002","125789")),70));
        wiredInternetRepository.add(new WiredInternet(4,2015,11,9,
                2016,10,8,2114447,
                new Human(4,"Смирнов Сергей Петрович",1979,5,16,
                        new Passport("2002","125789")),29));
    }

    /**
     * test, which check method {fromCsvToRepository} on all contracts types
     */
    @Test
    public void fromCsvToRepository() {
        fillIN();
        CsvWorker csvWorker=new CsvWorker();
        File contracts=new File("TestForAllContract.csv");
        File digital=new File("TestForDigitalTV.csv");
        File mobile=new File("TestForMobileConnection.csv");
        File wired=new File("TestForWiredInternet.csv");
        Repository<Contract> contractRepository1=new Repository<Contract>();
        Repository<DigitalTV> digitalTVRepository1=new Repository<DigitalTV>();
        Repository<WiredInternet> wiredInternetRepository1=new Repository<WiredInternet>();
        Repository<MobileConnection> mobileConnectionRepository1=new Repository<MobileConnection>();
        csvWorker.fromCsvToRepository(contracts,contractRepository1);
        csvWorker.fromCsvToRepository(digital,digitalTVRepository1);
        csvWorker.fromCsvToRepository(wired,wiredInternetRepository1);
        csvWorker.fromCsvToRepository(mobile,mobileConnectionRepository1);
        for(int i=0;i< contractRepository.getSize();i++){
            assertEquals(contractRepository.getByIndex(i),contractRepository1.getByIndex(i));
        }
        for (int i=0;i< digitalTVRepository.getSize();i++){
            assertEquals(digitalTVRepository.getByIndex(i),digitalTVRepository1.getByIndex(i));
        }
        for(int i=0;i< wiredInternetRepository.getSize();i++){
            assertEquals(wiredInternetRepository.getByIndex(i),wiredInternetRepository1.getByIndex(i));
        }
        for(int i=0;i< mobileConnectionRepository.getSize();i++){
            assertEquals(mobileConnectionRepository.getByIndex(i),mobileConnectionRepository1.getByIndex(i));
        }
    }
}