package models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    // Private fields for encapsulation
    private String accountNumber;
    private double balance;
    private String accountType;
    private User accountHolder;
    private List<Operation> operations;
    private String accountStatus;
    private java.time.LocalDateTime createdAt;

    public Account(String accountNumber, double balance, String accountType, User accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
        this.operations = new ArrayList<>();
        this.accountStatus = "Active";
        this.createdAt = java.time.LocalDateTime.now();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public User getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(User accountHolder) {
        this.accountHolder = accountHolder;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addOperation(Operation operation) {
        this.operations.add(operation);
    }

    public boolean removeOperation(String operationId) {
        return this.operations.removeIf(op -> op.getOperationId().equals(operationId));
    }

    public void listOperations() {
        System.out.println("\nOperations for Account: " + accountNumber);
        if (operations.isEmpty()) {
            System.out.println("No operations found.");
        } else {
            for (int i = 0; i < operations.size(); i++) {
                System.out.println((i + 1) + ". " + operations.get(i));
            }
        }
    }

    public void displayAccountInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: DT" + String.format("%.2f", balance));
        System.out.println("Status: " + accountStatus);
        System.out.println("Created At: " + createdAt);
        System.out.println("Account Holder: " + accountHolder.getName());
        System.out.println("============================\n");
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", balance=$" + String.format("%.2f", balance) +
                ", accountType='" + accountType + '\'' +
                ", accountHolder=" + accountHolder.getName() +
                ", status='" + accountStatus + '\'' +
                ", operationsCount=" + operations.size() +
                '}';
    }
}
