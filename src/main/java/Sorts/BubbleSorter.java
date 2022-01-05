package Sorts;

import Contracts.Contract;
import Repository.Repository;
import TestReflection.WithDefaultConstructor;

import java.util.Comparator;

/**
 * class of bubble sort
 * without fields
 * this class needed to sort repository by various criteria by using bubble sort
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
@WithDefaultConstructor
public class BubbleSorter<T extends Contract> implements ISorter<T>{

    /**
     * this method sorts repository by criteria, transmitted by comparator
     * @param comparator is criteria of sort
     * @param repository is repository, which we want to sort
     */
    @Override
    public void sort(Comparator<T> comparator, Repository<T> repository) {
        boolean flag=true;
        while(flag){
            flag=false;
            for(int i=0;i<repository.getSize()-1;i++){
                if(comparator.compare(repository.getByIndex(i),(T)repository.getByIndex(i+1))>0) {
                    T replace = repository.getByIndex(i);
                    repository.setByIndex(i, repository.getByIndex(i+1));
                    repository.setByIndex(i+1,replace);
                    flag=true;
                }
            }
        }

    }
}
