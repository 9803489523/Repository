package WorkWithCSV;

import Contracts.Contract;
import ReflectionResources.WithDefaultConstructor;

@WithDefaultConstructor
public class StartOfContractCheck implements IValidator{
    @Override
    public ContractChecker validate(Contract contract) {
        if(contract.getStartContract().getYear()<2000)
            return new ContractChecker("Start date smaller than date of start providing contracts",false,"startOfContract");
        else
            return new ContractChecker("Valid contract",true,"startOfContract");
    }
}
