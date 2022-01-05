package WorkWithCSV;

import Contracts.Contract;
import ReflectionResources.WithDefaultConstructor;

@WithDefaultConstructor
public class EndOfContractCheck implements IValidator{
    @Override
    public ContractChecker validate(Contract contract) {
        if(contract.getEndContract().compareTo(contract.getStartContract())<=0)
        return new ContractChecker("Start date more or equals end of date",false,"endOfContract");
            else
        return new ContractChecker("Valid contract",true,"endOfContract");
    }
}
