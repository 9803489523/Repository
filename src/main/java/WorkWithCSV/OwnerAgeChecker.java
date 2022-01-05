package WorkWithCSV;

import Contracts.Contract;
import ReflectionResources.WithDefaultConstructor;

@WithDefaultConstructor
public class OwnerAgeChecker implements IValidator{
    @Override
    public ContractChecker validate(Contract contract) {
        if(contract.getOwner().getAge()<18)
            return new ContractChecker("Owner is so young to get a contract",false,"ownerAge");
        else
            return new ContractChecker("Valid contract",true,"ownerAge");
    }
}
