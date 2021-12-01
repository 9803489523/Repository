package WorkWithCSV;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import Repository.Repository;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * class of  CsvWorker
 * without fields
 * this class need to read information from CSV file and write to repository
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */

public class CsvWorker{
    /**
     * method to read info from CSV file and write this to repository
     * @param csvFile, file for reading info
     * @param repository, repository to write info from file
     */
    public void fromCsvToRepository(File csvFile, Repository<Contract> repository){
        System.out.println("List of not added to repository contracts :");
        if(getFileExtension(csvFile).toLowerCase(Locale.ROOT).equals("csv")){
            try(BufferedReader reader=new BufferedReader(new FileReader(csvFile))) {
                String line;
                int counter=0;
                while ((line=reader.readLine())!=null){
                    try{
                        String[] parseArray;
                        counter++;
                        String[] contractInfo=line.split(";");
                        int id=Integer.parseInt(contractInfo[0]);
                        LocalDate startContract= LocalDate.parse(contractInfo[1],DateTimeFormatter.ofPattern("yyyy.M.d"));
                        LocalDate endContract=LocalDate.parse(contractInfo[2],DateTimeFormatter.ofPattern("yyyy.M.d"));
                        int numberOfContract=Integer.parseInt(contractInfo[3]);
                        String fio=contractInfo[4];
                        LocalDate clientBornDate=LocalDate.parse(contractInfo[5],DateTimeFormatter.ofPattern("yyyy.M.d"));
                        String passport =contractInfo[6];
                        String type=contractInfo[7].toUpperCase(Locale.ROOT);
                        switch (type) {
                            case "DTV" -> {
                                List<String> channels = Arrays.stream(contractInfo[8].split(",")).toList();
                                DigitalTV digitalTV = new DigitalTV(id,
                                        startContract.getYear(), startContract.getMonthValue(), startContract.getDayOfMonth(),
                                        endContract.getYear(), endContract.getMonthValue(), endContract.getDayOfMonth(),
                                        numberOfContract, new Human(id, fio, clientBornDate.getYear(),
                                        clientBornDate.getMonthValue(), clientBornDate.getDayOfMonth(), passport), channels);
                                repository.add(digitalTV);
                            }
                            case "MC" -> {
                                parseArray = contractInfo[8].split(" ");
                                if(parseArray.length==3)
                                {
                                    MobileConnection mobileConnection = new MobileConnection(id,
                                            startContract.getYear(), startContract.getMonthValue(), startContract.getDayOfMonth(),
                                            endContract.getYear(), endContract.getMonthValue(), endContract.getDayOfMonth(),
                                            numberOfContract, new Human(id, fio, clientBornDate.getYear(),
                                            clientBornDate.getMonthValue(), clientBornDate.getDayOfMonth(), passport),
                                            Integer.parseInt(parseArray[0]), Integer.parseInt(parseArray[1]), Integer.parseInt(parseArray[2]));
                                    repository.add(mobileConnection);
                                }
                                else
                                    throw new IOException();
                            }
                            case "WI" ->
                            {
                                int speed = Integer.parseInt(contractInfo[8]);
                                WiredInternet wiredInternet = new WiredInternet(id,
                                        startContract.getYear(), startContract.getMonthValue(), startContract.getDayOfMonth(),
                                        endContract.getYear(), endContract.getMonthValue(), endContract.getDayOfMonth(),
                                        numberOfContract, new Human(id, fio, clientBornDate.getYear(),
                                        clientBornDate.getMonthValue(), clientBornDate.getDayOfMonth(), passport), speed);
                                repository.add(wiredInternet);
                            }
                            default -> throw new IOException();
                        }
                    }
                    catch (NumberFormatException|IOException| DateTimeParseException e){
                        System.out.printf("%s value not added to repository, returned with exception \n",counter);
                        e.printStackTrace();
                    }
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
