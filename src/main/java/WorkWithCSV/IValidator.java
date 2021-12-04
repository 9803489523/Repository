package WorkWithCSV;

import Contracts.Contract;
/**
 * interface of various validators
 * without fields
 * this interface needed to check contract on validation
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public interface IValidator {
    /**
     * method, which check contract on validation
     * @param contract, which we want check on validation
     * @return class, which describe invalid values in contract
     */
    ContractChecker validate(Contract contract);
}
