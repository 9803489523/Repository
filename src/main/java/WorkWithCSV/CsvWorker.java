package WorkWithCSV;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import PeoplesInformation.Human;
import Repository.Repository;
import ReflectionResources.Autoinjectable;
import ReflectionResources.CollectionField;
import ReflectionResources.WithDefaultConstructor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


 /**
 * class of  CsvWorkerWithValidators
 * with field {@link #validators}
 * this class need to read information from CSV file and write to repository taking into account validators
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
 @WithDefaultConstructor
public class CsvWorker {
     @Autoinjectable
     @CollectionField(genericsParametersName = "IValidator")
    private List<IValidator> validators;

    /**
     * constructor with parameters
     * @param validators, list of validators
     */
    public CsvWorker(List<IValidator> validators) {
        this.validators = validators;
    }

    /**
     * getter of validators list
     * @return validators list
     */
    public List<IValidator> getValidators() {
        return validators;
    }
    /**
     * constructor without parameters
     * fill in validators with default list validators
     */
    public CsvWorker() {
        validators=new ArrayList<>();
        IValidator endOfContract= contract -> {
            if(contract.getEndContract().compareTo(contract.getStartContract())<=0)
                return new ContractChecker("Start date more or equals end of date",false,"endOfContract");
            else
                return new ContractChecker("Valid contract",true,"endOfContract");
        };
        validators.add(endOfContract);
        IValidator startOfContract= contract -> {
            if(contract.getStartContract().getYear()<2000)
                return new ContractChecker("Start date smaller than date of start providing contracts",false,"startOfContract");
            else
                return new ContractChecker("Valid contract",true,"startOfContract");
        };
        validators.add(startOfContract);
        IValidator checkingID = contract -> {
            if(contract.getId()<1)
                return new ContractChecker("Contract id smaller than zero",false,"contractId");
            else
                return new ContractChecker("Valid contract",true,"contractId");
        };
        validators.add(checkingID);
        IValidator checkingOwnerAge = contract -> {
            if(contract.getOwner().getAge()<18)
                return new ContractChecker("Owner is so young to get a contract",false,"ownerAge");
            else
                return new ContractChecker("Valid contract",true,"ownerAge");
        };
        validators.add(checkingOwnerAge);
        IValidator checkingOwnerPassport= contract -> {
            String passportRegexp1="\\d{4} \\d{6}";
            String passportRegexp2="\\d{4} \\d{3} \\d{3}";
            String passportRegexp3="\\d{2} \\d{2} \\d{6}";
            String passportRegexp4="\\d{2} \\d{2} \\d{3} \\d{3}";
            if(!
               (
               contract.getOwner().getPassport().matches(passportRegexp1)||
               contract.getOwner().getPassport().matches(passportRegexp2)||
               contract.getOwner().getPassport().matches(passportRegexp3)||
               contract.getOwner().getPassport().matches(passportRegexp4)
               )
            )

                return new ContractChecker("Not valid passport",false,"ownerPassport");
            else
                return new ContractChecker("Valid contract",true,"ownerPassport");
        };
        validators.add(checkingOwnerPassport);
        IValidator checkingContractNumber= contract -> {
            if(contract.getNumberOfContract()<1)

                return new ContractChecker("Number of contract smaller or equals zero",false,"numberOfContract");
            else
                return new ContractChecker("Valid contract",true,"numberOfContract");
        };
        validators.add(checkingContractNumber);
        IValidator checkingInternetSpeed= contract -> {
            if (contract instanceof WiredInternet) {
                if (((WiredInternet) contract).getConnectionSpeed()<=0)

                    return new ContractChecker("Internet speed smaller or equals zero", false, "wiredInternetSpeed");
            }
            return new ContractChecker("Valid contract",true,"");
        };
        validators.add(checkingInternetSpeed);
        IValidator checkingMobileConnectionParameters= contract -> {
            if (contract instanceof MobileConnection) {
                if (((MobileConnection) contract).getNumberOfSMS()<0)

                    return new ContractChecker("SMS number smaller  than zero", false, "mobileConnectionSMSNumber");
                else{
                    if(((MobileConnection) contract).getNumberOfMinutes()<=0)
                        return new ContractChecker("Minutes number smaller or equals zero", false, "mobileConnectionMinutesNumber");
                    else{
                        if(((MobileConnection) contract).getInternetTraffic()<=0)
                            return new ContractChecker("Internet traffic smaller or equals zero", false, "mobileConnectionMinutesNumber");
                    }
                }
            }
            return new ContractChecker("Valid contract",true,"");
        };
        validators.add(checkingMobileConnectionParameters);
    }

    /**
     * setter list of validators
     * @param validators, list, which we want to set
     */
    public void setValidators(List<IValidator> validators) {
        this.validators = validators;
    }

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
                    int indexNotValidValue=0;
                    Contract notValid = null;
                    try{
                        String[] parseArray;
                        counter++;
                        String[] contractInfo=line.split(";");
                        int id=Integer.parseInt(contractInfo[0]);
                        LocalDate startContract= LocalDate.parse(contractInfo[1], DateTimeFormatter.ofPattern("yyyy.M.d"));
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
                                for (int i=0;i<validators.size();i++) {
                                    if (!validators.get(i).validate(digitalTV).isStatus()) {
                                        indexNotValidValue=i;
                                        notValid=digitalTV;
                                        throw new IllegalArgumentException();
                                    }
                                }
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
                                    for (int i=0;i<validators.size();i++) {
                                        if (!validators.get(i).validate(mobileConnection).isStatus()) {
                                            indexNotValidValue=i;
                                            notValid=mobileConnection;
                                            throw new IllegalArgumentException();
                                        }
                                    }
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
                                        for (int i=0;i<validators.size();i++) {
                                            if (!validators.get(i).validate(wiredInternet).isStatus()) {
                                                indexNotValidValue=i;
                                                notValid=wiredInternet;
                                                throw new IllegalArgumentException();
                                            }
                                        }
                                        repository.add(wiredInternet);
                                    }
                            default -> throw new IOException();
                        }
                    }
                    catch (NumberFormatException|IOException| DateTimeParseException e){
                        System.out.printf("%s value not added to repository, returned with exception \n",counter);
                        e.printStackTrace();
                    }
                    catch (IllegalArgumentException e){
                        System.out.printf("%s value not added to repository, returned with exception \n",counter);
                        System.out.println(validators.get(indexNotValidValue).validate(notValid));

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
