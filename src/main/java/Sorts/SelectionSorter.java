package Sorts;

import Contracts.Contract;
import Repository.Repository;
import java.util.Comparator;

/**
 * class of selection sort
 * without fields
 * this class needed to sort repository by various criteria by using selection sort
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class SelectionSorter<T extends Contract> implements Isorter<T>{

    /**
     * this method sorts repository by criteria, transmitted by comparator
     * @param comparator is criteria of sort
     * @param repository is repository, which we want to sort
     */
    @Override
    public void sort(Comparator<T> comparator, Repository<T> repository) {
        Contract[] contracts= repository.getContracts();
        for(int i=0;i<repository.getSize();i++){
            int minIndex=i;
            for(int j=i;j<repository.getSize();j++){
                if(comparator.compare((T)contracts[minIndex],(T)contracts[j])>0)
                    minIndex=j;
            }
            Contract replace=contracts[i];
            contracts[i]=contracts[minIndex];
            contracts[minIndex]=replace;

        }
    }
}
