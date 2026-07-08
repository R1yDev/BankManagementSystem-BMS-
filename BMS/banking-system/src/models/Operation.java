package models;

public class Operation {
    private String operationId;
    private String operationType;  // "DEPOSIT" or "WITHDRAW"
    private double amount;
    private String accountNumber;
    private String description;
    private java.time.LocalDateTime operationDate;
    private double balanceAfter;

    public Operation(String operationId, String operationType, double amount,
                     String accountNumber, String description, double balanceAfter) {
        this.operationId = operationId;
        this.operationType = operationType;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.description = description;
        this.balanceAfter = balanceAfter;
        this.operationDate = java.time.LocalDateTime.now();
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public java.time.LocalDateTime getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(java.time.LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public boolean isDeposit() {
        return "DEPOSIT".equalsIgnoreCase(operationType);
    }

    public boolean isWithdrawal() {
        return "WITHDRAW".equalsIgnoreCase(operationType);
    }

    public void displayOperation() {
        System.out.println("Operation ID: " + operationId);
        System.out.println("Type: " + operationType);
        System.out.println("Amount: DT" + String.format("%.2f", amount));
        System.out.println("Account: " + accountNumber);
        System.out.println("Description: " + description);
        System.out.println("Balance After: DT" + String.format("%.2f", balanceAfter));
        System.out.println("Date: " + operationDate);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id='" + operationId + '\'' +
                ", type=" + operationType +
                ", amount=$" + String.format("%.2f", amount) +
                ", account='" + accountNumber + '\'' +
                ", date=" + operationDate.toLocalDate() +
                ", balanceAfter=$" + String.format("%.2f", balanceAfter) +
                '}';
    }
}
