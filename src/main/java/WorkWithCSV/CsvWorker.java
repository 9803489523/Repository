package WorkWithCSV;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import PeoplesInformation.Passport;
import Repository.Repository;
import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * class of  CsvWorker
 * without fields
 * this class need to read information from CSV file and wrote to repository
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */

public class CsvWorker{
    /**
     * method to read info from CSV file and write this to repository
     * @param csvFile, file for reading info
     * @param repository, repository to write info from file
     * @param <T>, repositories generic
     */
    public<T extends Contract> void fromCsvToRepository(File csvFile, Repository<T> repository){
        if(getFileExtension(csvFile).toLowerCase(Locale.ROOT).equals("csv")){
            try(BufferedReader reader=new BufferedReader(new FileReader(csvFile))) {
                String line;
                while ((line=reader.readLine())!=null){
                    try{
                        String[] contractInfo=line.split(";");
                        int id=Integer.parseInt(contractInfo[0]);
                        String[] parseArray=contractInfo[1].split("\\.");
                        LocalDate startContract=LocalDate.of(Integer.parseInt(parseArray[0]),
                                                             Integer.parseInt(parseArray[1]),
                                                             Integer.parseInt(parseArray[2]));
                        parseArray=contractInfo[2].split("\\.");
                        LocalDate endContract=LocalDate.of(Integer.parseInt(parseArray[0]),
                                                           Integer.parseInt(parseArray[1]),
                                                           Integer.parseInt(parseArray[2]));
                        int numberOfContract=Integer.parseInt(contractInfo[3]);
                        String fio=contractInfo[4];
                        parseArray=contractInfo[5].split("\\.");
                        LocalDate clientBornDate=LocalDate.of(Integer.parseInt(parseArray[0]),
                                                              Integer.parseInt(parseArray[1]),
                                                              Integer.parseInt(parseArray[2]));
                        parseArray=contractInfo[6].split(" ");
                        Passport passport =new Passport(parseArray[0],parseArray[1]);
                        String type=contractInfo[7].toUpperCase(Locale.ROOT);
                        switch (type) {
                            case "DTV" -> {
                                parseArray = contractInfo[8].split(",");
                                List<String> channels = Arrays.stream(parseArray).toList();
                                DigitalTV digitalTV = new DigitalTV(id,
                                        startContract.getYear(), startContract.getMonthValue(), startContract.getDayOfMonth(),
                                        endContract.getYear(), endContract.getMonthValue(), endContract.getDayOfMonth(),
                                        numberOfContract, new Human(id, fio, clientBornDate.getYear(),
                                        clientBornDate.getMonthValue(), clientBornDate.getDayOfMonth(), passport), channels);
                                repository.add((T) digitalTV);
                            }
                            case "MC" -> {
                                parseArray = contractInfo[8].split(" ");
                                MobileConnection mobileConnection = new MobileConnection(id,
                                        startContract.getYear(), startContract.getMonthValue(), startContract.getDayOfMonth(),
                                        endContract.getYear(), endContract.getMonthValue(), endContract.getDayOfMonth(),
                                        numberOfContract, new Human(id, fio, clientBornDate.getYear(),
                                        clientBornDate.getMonthValue(), clientBornDate.getDayOfMonth(), passport),
                                        Integer.parseInt(parseArray[0]), Integer.parseInt(parseArray[1]), Integer.parseInt(parseArray[2]));
                                repository.add((T) mobileConnection);
                            }
                            case "WI" -> {
                                int speed = Integer.parseInt(contractInfo[8]);
                                WiredInternet wiredInternet = new WiredInternet(id,
                                        startContract.getYear(), startContract.getMonthValue(), startContract.getDayOfMonth(),
                                        endContract.getYear(), endContract.getMonthValue(), endContract.getDayOfMonth(),
                                        numberOfContract, new Human(id, fio, clientBornDate.getYear(),
                                        clientBornDate.getMonthValue(), clientBornDate.getDayOfMonth(), passport), speed);
                                repository.add((T) wiredInternet);
                            }
                        }
                    }
                    catch (Exception e){}
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * get file extension
     * @param file, in which we want to get extension
     * @return extension of file
     */
    private String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
}
