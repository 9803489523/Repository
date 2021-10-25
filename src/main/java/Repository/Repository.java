package Repository;

import Contracts.Contract;

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
    private T[] contracts;
    /**
     * size of generic array field
     */
    private int size;

    public Repository() {
        size=0;
        contracts= (T[]) new Contract[10];
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
            return contracts[index];
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
}
