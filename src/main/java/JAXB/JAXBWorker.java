package JAXB;

import Contracts.Contract;
import Repository.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * class of JAXB worker
 * need to marshall and unmarshall repository
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class JAXBWorker<T extends Contract> {

    /**
     * marshall repository to xml file
     * @param repository, that we want to marshall
     * @param filename, name of file, where we marshall repository
     */
    public void write(Repository<T> repository,String filename) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Repository.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String path="./"+filename+".xml";
        mar.marshal(repository, new File(path));
    }

    /**
     * unmarshalling repository from xml file
     * @param fileName, name of xml file
     * @return contract repository
     */
    public Repository<T> read(String fileName) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Repository.class);
        String path="./"+fileName+".xml";
        Unmarshaller unmarshaller= context.createUnmarshaller();
        return (Repository<T>) context.createUnmarshaller()
                .unmarshal(new FileReader(path));
    }
}
