package WorkWithCSV;

/**
 * class of  ContractChecker
 * with fields {@link #message},{@link #status},{@link #fieldName}
 * this class need to read information from CSV file and write to repository
 * @author Aleksandr Nozdryuhin
 * @version 4.0.0
 */
public class ContractChecker {
    /**
     * validators message
     */
    private String message;
    /**
     * status of check, True, if contract is valid value, False, if contract no valid
     */
    private boolean status;
    /**
     * fields name, which is not valid
     */
    private String fieldName;

    public ContractChecker(String message, boolean status, String fieldName) {
        this.message = message;
        this.status = status;
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return String.format("%sError in field %s,\nError message: %s%s","\u001B[31m",fieldName,message,"\u001B[0m");
    }
}
