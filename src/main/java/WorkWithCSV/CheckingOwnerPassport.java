package WorkWithCSV;

import Contracts.Contract;
import ReflectionResources.WithDefaultConstructor;

@WithDefaultConstructor
public class CheckingOwnerPassport implements IValidator{
    @Override
    public ContractChecker validate(Contract contract) {
        String passportRegexp1="\\d{4} \\d{6}";
        String passportRegexp2="\\d{4} \\d{3} \\d{3}";
        String passportRegexp3="\\d{2} \\d{2} \\d{6}";
        String passportRegexp4="\\d{2} \\d{2} \\d{3} \\d{3}";
        if(!
                (
                        contract.getOwner().getPassport().matches(passportRegexp1)||
                                contract.getOwner().getPassport().matches(passportRegexp2)||
                                contract.getOwner().getPassport().matches(passportRegexp3)||
                                contract.getOwner().getPassport().matches(passportRegexp4)
                )
        )

            return new ContractChecker("Not valid passport",false,"ownerPassport");
        else
            return new ContractChecker("Valid contract",true,"ownerPassport");
    }
}
