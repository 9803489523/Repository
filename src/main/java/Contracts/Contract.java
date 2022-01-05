package Contracts;

import PeoplesInformation.Human;
import java.time.LocalDate;
import java.util.Objects;
/**
 * abstract class of contract
 * with fields <b>id</b>,<b>startContract</b>,<b>endContract</b>,<b>numberOfContract</b>,<b>owner</b>
 * this class store info about all contracts
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */

public abstract class  Contract {
    /**
     * field of id contract
     */
    private int id;
    /**
     * contract start date field
     */
    private LocalDate startContract;
    /**
     * contract end date field
     */
    private LocalDate endContract;
    /**
     * number of contract field
     */
    private long numberOfContract;
    /**
     * field of contract owner
     */
    private Human owner;

    public Contract(int id, int startYear,int startMonth, int startDay,
                    int endYear, int endMonth,int endDay, long numberOfContract, Human owner)
    {
        this.id = id;
        this.startContract = LocalDate.of(startYear,startMonth,startDay);
        this.endContract = LocalDate.of(endYear,endMonth,endDay);
        this.numberOfContract = numberOfContract;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getStartContract() {
        return startContract;
    }

    public void setStartContract(LocalDate startContract) {
        this.startContract = startContract;
    }

    public LocalDate getEndContract() {
        return endContract;
    }

    public void setEndContract(LocalDate endContract) {
        this.endContract = endContract;
    }

    public long getNumberOfContract() {
        return numberOfContract;
    }

    public void setNumberOfContract(long numberOfContract) {
        this.numberOfContract = numberOfContract;
    }

    public Human getOwner() {
        return owner;
    }

    public void setOwner(Human owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id == contract.id && numberOfContract == contract.numberOfContract
                && startContract.equals(contract.startContract)
                && endContract.equals(contract.endContract) && owner.equals(contract.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startContract, endContract, numberOfContract, owner);
    }

    @Override
    public String toString() {
        return String.format("""
                        {id: %s;
                        дата начала контракта: %s
                        дата завершение контракта: %s
                        номер контракта: %s
                        Владелец: %s""",id,startContract,endContract,
                numberOfContract,owner);
    }
}
