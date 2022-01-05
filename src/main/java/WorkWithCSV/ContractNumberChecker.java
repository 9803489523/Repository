package WorkWithCSV;

import Contracts.Contract;
import ReflectionResources.WithDefaultConstructor;

@WithDefaultConstructor
public class ContractNumberChecker implements IValidator{

    @Override
    public ContractChecker validate(Contract contract) {
        if(contract.getNumberOfContract()<1)
            return new ContractChecker("Number of contract smaller or equals zero",false,"numberOfContract");
        else
            return new ContractChecker("Valid contract",true,"numberOfContract");
    }
}
