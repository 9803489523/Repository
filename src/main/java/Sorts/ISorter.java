package Sorts;

import Contracts.Contract;
import Repository.Repository;
import java.util.Comparator;

/**
 * interface of various sorts
 * without fields
 * this interface needed to sort repository
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
@FunctionalInterface
public interface ISorter<T extends Contract> {
     /**
      * this method sort repository by comparator criteria
      * @param comparator is criteria to sort
      * @param repository is repository, which we want to sort
      */
     void  sort(Comparator<T> comparator,Repository<T> repository);
}
