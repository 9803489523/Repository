package Repository;

import Contracts.Contract;
import Contracts.DigitalTV;
import Contracts.MobileConnection;
import Contracts.WiredInternet;
import Sorts.BubbleSorter;
import Sorts.ISorter;
import ReflectionResources.Autoinjectable;
import ReflectionResources.WithDefaultConstructor;

import javax.xml.bind.annotation.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * class of  Repository
 * with fields <b>contracts</b>, <b>size</b>
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
@WithDefaultConstructor
@XmlRootElement(name="repository")
@XmlType(propOrder = {"contracts","size"})
@XmlSeeAlso({WiredInternet.class, DigitalTV.class, MobileConnection.class})
@XmlAccessorType (XmlAccessType.FIELD)
public class Repository<T extends Contract>{
    /**
     *generic array field to store, add and delete contracts
     */
    @XmlElement(name="contract")
    private Contract[] contracts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository<?> that = (Repository<?>) o;
        return size == that.size && Arrays.equals(contracts, that.contracts) && Objects.equals(sorter, that.sorter);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, sorter);
        result = 31 * result + Arrays.hashCode(contracts);
        return result;
    }

    /**
     * size of generic array field
     */
    @XmlElement
    private int size;

    /**
     * repositories sorter
     */
    @XmlTransient
    @Autoinjectable(defaultField = "ShellSorter")
    private ISorter<T> sorter=new BubbleSorter<>();

    /**
     * setter of size
     * @param size of repository
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * getter of field sorter
     */
    public ISorter<T> getSorter() {
        return sorter;
    }

    /**
     * setter of field sorter
     */
    public void setSorter(ISorter<T> sorter) {
        this.sorter = sorter;
    }

    /**
     * constructor of class Repository
     */
    public Repository() {
        size=0;
        contracts= new Contract[10];
    }

    /**
     * constructor with parameters
     * @param sorter, sorter of repository
     */
    public Repository(ISorter<T> sorter) {
        size=0;
        contracts= new Contract[10];
        this.sorter = sorter;
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
        contracts=  new Contract[10];
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
     * method to get contract by index from array
     * @param index of value in array
     * @return contracts[index]
     */
    public T getByIndex(int index){
        return (T)contracts[index];
    }

    /**
     * method, which set value in contracts array by index
     * @param index - position, which we want to set value in contracts array
     * @param value - value, which we want to set in contracts array
     */
    public void setByIndex(int index, T value){
        contracts[index]=value;
    }
    /**
     * method to search value in repository by various criteria
     * @param predicate, criteria for search
     * @return searches list of result
     */
    public Repository<T> search(Predicate<T> predicate){
        Repository<T> result=new Repository<T>();
        for(int i=0;i<size;i++){
            if(predicate.test((T)contracts[i]))
                result.add((T)contracts[i]);
        }
        return result;
    }

    /**
     * method, which sorts repository by comparator
     * @param comparator is criteria to sort repository
     */
    public void sort(Comparator<T> comparator){
        sorter.sort(comparator,this);
    }

    /**
     * getter of contract array
     * @return contract array
     */
    public Contract[] getContracts() {
        return contracts;
    }

    /**
     * setter of contract array
     */
    public void setContracts(Contract[] contracts) {
        this.contracts = contracts;
    }
}
