package WorkWithCSV;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import Repository.Repository;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CsvWorkerWithValidatorTest {

    /**
     * repository, which store all contract
     */
    Repository<Contract> contractRepository=new Repository<>();
    /**
     * first variant of channels packet
     */
    ArrayList<String> firstPacket=new ArrayList<>();
    /**
     * second variant of channels packet
     */
    ArrayList<String> secondPacket=new ArrayList<>();

    /**
     * method which fill in all contract repository
     */
    public void fillIN(){
        firstPacket.add("СТС");firstPacket.add("1 канал");firstPacket.add("Россия 1");
        secondPacket.add("1 канал");secondPacket.add("НТВ");secondPacket.add("МАТЧ ТВ");
        contractRepository.add(new WiredInternet(1,2009,11,9,
                2015,10,8,2114445,
                new Human(1,"Иванов Сергей Петрович",1979,5,16,
                        "2000 125789"),40));
        contractRepository.add(new MobileConnection(2,2010,11,9,
                2017,10,8,2114446,
                new Human(2,"Петров Сергей Петрович",1979,5,16,
                        "2001 125789"),54,120,48));
        contractRepository.add(new WiredInternet(3,2015,11,9,
                2016,10,8,2114447,
                new Human(3,"Смирнов Сергей Петрович",1979,5,16,
                        "2002 125789"),40));
        contractRepository.add(new DigitalTV(6,2007,11,9,
                2011,10,8,2114411,
                new Human(6,"Махин Сергей Петрович",1979,5,16,
                        "2006 125789"),secondPacket));
        contractRepository.add(new MobileConnection(7,2019,11,9,
                2020,10,8,2114412,
                new Human(7,"Троцкий Сергей Петрович",1979,5,16,
                        "2008 125789"),40,45,12));
        contractRepository.add(new DigitalTV(1,2014,11,9,
                2016,10,8,2114413,
                new Human(1,"Парков Сергей Петрович",1979,5,16,
                        "2009 125789"),firstPacket));
        contractRepository.add(new DigitalTV(2,2014,11,9,
                2016,10,8,2114413,
                new Human(2,"Денисов Сергей Петрович",1979,5,16,
                        "2009 125789"),firstPacket));
        contractRepository.add(new DigitalTV(3,2014,11,9,
                2016,10,8,2114413,
                new Human(3,"Иванов Сергей Петрович",1979,5,16,
                        "20 09 125 789"),firstPacket));
        contractRepository.add(new DigitalTV(5,2014,11,9,
                2016,10,8,2114413,
                new Human(5,"Ечин Сергей Петрович",1979,5,16,
                        "20 09 125 789"),firstPacket));
        contractRepository.add(new MobileConnection(7,2019,11,9,
                2020,10,8,2114412,
                new Human(7,"Ильин Сергей Петрович",1979,5,16,
                        "2008 125789"),40,45,12));
        contractRepository.add(new MobileConnection(1,2019,11,9,
                2020,10,8,2114412,
                new Human(1,"Жданов Сергей Петрович",1979,5,16,
                        "2008 125781"),20,48,15));
    }

    /**
     * test, which check method {fromCsvToRepository} on all contracts types
     */
    @Test
    public void fromCsvToRepository() {
        fillIN();
        CsvWorkerWithValidator csvWorker=new CsvWorkerWithValidator();
        File contracts=new File("TestForCsvWorkerWithValidator.csv");
        Repository<Contract> contractRepository1=new Repository<>();
        csvWorker.fromCsvToRepository(contracts,contractRepository1);
        for(int i=0;i< contractRepository.getSize();i++){
            assertEquals(contractRepository.getByIndex(i),contractRepository1.getByIndex(i));
        }
    }
}