package WorkWithCSV;

import Contracts.Contract;
import ReflectionResources.WithDefaultConstructor;

@WithDefaultConstructor
public class IdChecker implements IValidator{
    @Override
    public ContractChecker validate(Contract contract) {
        if(contract.getId()<1)
            return new ContractChecker("Contract id smaller than zero",false,"contractId");
        else
            return new ContractChecker("Valid contract",true,"contractId");
    }
}
