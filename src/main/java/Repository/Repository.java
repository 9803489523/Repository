package Repository;

import Contracts.Contract;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * class of  Repository
 * with fields <b>contracts</b>, <b>size</b>
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class Repository<T extends Contract>{
    /**
     generic array field to store, add and delete contracts
     */
    private Contract[] contracts;
    /**
     * size of generic array field
     */
    private int size;

    /**
     * setter of contracts
     * @param contracts array
     */
    public void setContracts(T[] contracts) {
        this.contracts = contracts;
    }

    /**
     * setter of size
     * @param size of repository
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * getter of array contracts
     * @return contracts array
     */
    public Contract[] getContracts() {
        return  contracts;
    }

    /**
     * constructor
     */
    public Repository() {
        size=0;
        contracts= new Contract[10];
    }

    /**
     * method for check emptiness
     * @return boolean result for check emptiness
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * method to console print all contracts in repository
     */
    public void print(){
        for(int i=0;i<size;i++){
            System.out.println(contracts[i]);
        }
    }

    /**
     * private method, which add contract ro repository
     * @param contract for add to repository
     */
    private void add(T contract){
        if(isEmpty()) {
            contracts[0]=contract;
        }
        else{
            if(size== contracts.length){
                T[] copyContracts= (T[]) new Contract[contracts.length*2];
                System.arraycopy(contracts,0,copyContracts,0,contracts.length);
                copyContracts[contracts.length]=contract;
                contracts=copyContracts;
            }
            else {
                contracts[size]=contract;
            }
        }
        size++;
    }

    /**
     * private method, which add contract ro repository
     * @param contracts for add to repository
     */
    @SafeVarargs
    public final void add(T... contracts){
        for (Contract contract:contracts){
            add((T) contract);
        }
    }

    /**
     * method for get contract by id
     * @param id of contract
     * @return contract, if id>=size or id<0, then return null
     */
    public T get(int id){
        int index=-1;
        for (int i=0;i<size;i++) {
            if (contracts[i].getId() == id) {
                index = i;
                break;
            }
        }
        if(index==-1)
            return null;
        else
            return (T)contracts[index];
    }



    /**
     * method, which delete all items in repository
     */
    public void clear(){
        contracts= (T[]) new Contract[10];
        size=0;
    }

    /**
     * method for remove item by index
     * @param id of contract
     * @return boolean result of removing item (if {@link Repository#contracts}
     * include contract with param id, then remove  will execute and return true,
     * else remove will not  execute and return false)
     */
    public boolean remove(int id){
        int index=-1;
        for(int i=0;i<size;i++){
            if(contracts[i].getId()==id) {
                index = i;
                break;
            }
        }
        if(index==-1)
            return false;
        else {
            Contract[] copyContracts=new Contract[size-1];
            System.arraycopy(contracts,0,copyContracts,0,index);
            System.arraycopy(contracts,index+1,copyContracts,index,size-index-1);
            contracts= (T[]) copyContracts;
            size--;
            return true;
        }
    }

    /**
     * method to get size of repository
     * @return size of repository
     */
    public int getSize() {
        return size;
    }

    /**
     * method to search value in repository by various criteria
     * @param predicate, criteria for search
     * @return searches list of result
     */
    public List<T> search(Predicate<T> predicate){
        List<T> result=new ArrayList<>();
        for(int i=0;i<size;i++){
            if(predicate.test((T)contracts[i]))
                result.add((T)contracts[i]);
        }
        return result;
    }
}
