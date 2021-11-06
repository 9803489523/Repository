package Sorts;

import Contracts.Contract;
import Repository.Repository;
import java.util.Comparator;

/**
 * class of bubble sort
 * without fields
 * this class needed to sort repository by various criteria by using bubble sort
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class BubbleSorter<T extends Contract> implements Isorter<T>{

    /**
     * this method sorts repository by criteria, transmitted by comparator
     * @param comparator is criteria of sort
     * @param repository is repository, which we want to sort
     */
    @Override
    public void sort(Comparator<T> comparator, Repository<T> repository) {
        boolean flag=true;
        Contract[] contracts= repository.getContracts();
        while(flag){
            flag=false;
            for(int i=0;i<repository.getSize()-1;i++){
                if(comparator.compare((T)contracts[i],(T)contracts[i+1])>0) {
                    Contract replace = contracts[i];
                    contracts[i]=contracts[i+1];
                    contracts[i+1]=replace;
                    flag=true;
                }
            }
        }

    }
}
