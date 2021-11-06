package Sorts;
import Contracts.Contract;
import Repository.Repository;
import java.util.Comparator;

/**
 * class of Shell's sort
 * without fields
 * this class needed to sort repository by various criteria by using Shell's sort
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */

public class ShellSorter<T extends Contract> implements Isorter<T>{

    /**
     * this method sorts repository by criteria, transmitted by comparator
     * @param comparator is criteria of sort
     * @param repository is repository, which we want to sort
     */
    @Override
    public void sort(Comparator<T> comparator, Repository<T> repository) {
        int gap = repository.getSize()/2;
        Contract[] contracts=repository.getContracts();
        while (gap >= 1) {
            for (int right = 0; right < repository.getSize(); right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (comparator.compare( (T)contracts[c],(T)contracts[c + gap])>0) {
                        Contract replace=contracts[c];
                        contracts[c]=contracts[c+gap];
                        contracts[c+gap]=replace;
                    }
                }
            }
            gap = gap / 2;
        }
    }
}
