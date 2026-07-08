package services;

import models.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BankService {
    // HashMaps for storing accounts and operations (as per requirements)
    private HashMap<String, Account> accounts;
    private HashMap<String, Operation> operations;  // Operation ID -> Operation
    private HashMap<String, User> users;  // User ID -> User
    private Admin admin; //for admin only can acc

    public BankService(Admin admin) {
        this.accounts = new HashMap<>();
        this.operations = new HashMap<>();
        this.users = new HashMap<>();
        this.admin = admin;
    }
    // **************************************ADMIN METHODS***************************************
    public boolean adminLogin(String username, String password) {
        return admin.login(username, password);
    }

    public boolean adminLogout() {
        return admin.logout();
    }

    public boolean addAccount(Account account) {
        if (accounts.containsKey(account.getAccountNumber())) {
            System.out.println("Account already exists: " + account.getAccountNumber());
            return false;
        }
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account added successfully: " + account.getAccountNumber());
        return true;
    }

    public boolean deleteAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            Account account = accounts.remove(accountNumber);
            System.out.println("Account deleted successfully: " + accountNumber);
            return true;
        }
        System.out.println("Account not found: " + accountNumber);
        return false;
    }

    public boolean addOperation(Operation operation) {
        if (operations.containsKey(operation.getOperationId())) {
            System.out.println("Operation already exists: " + operation.getOperationId());
            return false;
        }
        operations.put(operation.getOperationId(), operation);
        System.out.println("Operation added successfully: " + operation.getOperationId());

        // Also add the operation to the corresponding account
        Account account = accounts.get(operation.getAccountNumber());
        if (account != null) {
            account.addOperation(operation);
        }
        return true;
    }

    public boolean deleteOperation(String operationId) {
        if (operations.containsKey(operationId)) {
            Operation operation = operations.remove(operationId);

            // Also remove from the corresponding account
            Account account = accounts.get(operation.getAccountNumber());
            if (account != null) {
                account.removeOperation(operationId);
            }

            System.out.println("Operation deleted successfully: " + operationId);
            return true;
        }
        System.out.println("Operation not found: " + operationId);
        return false;
    }

    public double viewBalance(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            return account.getBalance();
        }
        System.out.println("Account not found: " + accountNumber);
        return -1;
    }

    public void listAllOperations() {
        System.out.println("\n All Operations ");
        if (operations.isEmpty()) {
            System.out.println("No operations found.");
        } else {
            int count = 1;
            for (Map.Entry<String, Operation> entry : operations.entrySet()) {
                System.out.println(count + ". " + entry.getValue());
                count++;
            }
        }
        System.out.println("\n");
    }

    // **************************************USER METHODS***************************************

    public boolean registerUser(User user) {
        if (users.containsKey(user.getUserId())) {
            System.out.println("User already exists: " + user.getUserId());
            return false;
        }
        users.put(user.getUserId(), user);
        System.out.println("User registered successfully: " + user.getName());
        return true;
    }

    /**
     * User: Login
     */
    public User userLogin(String username, String password) {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                user.login(username, password);
                return user;
            }
        }
        System.out.println("Login failed. Invalid username or password.");
        return null;
    }

    public void viewPersonalInformation(User user) {
        user.displayPersonalInfo();
    }

    public void modifyPersonalInformation(User user, String name, String email,
                                          String phoneNumber, String address) {
        user.setName(name);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        System.out.println("Personal information updated successfully!");
    }

    public double viewUserBalance(String accountNumber, User user) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.getAccountHolder().getUserId().equals(user.getUserId())) {
            return account.getBalance();
        }
        System.out.println("Account not found or access denied.");
        return -1;
    }

    /**
     * User: List operations for their account
     */
    public void listUserOperations(String accountNumber, User user) {
        Account account = accounts.get(accountNumber);
        if (account != null && account.getAccountHolder().getUserId().equals(user.getUserId())) {
            account.listOperations();
        } else {
            System.out.println("Account not found or access denied.");
        }
    }

    // ==================== DEPOSIT & WITHDRAW METHODS ====================

    /**
     * Deposit money into an account
     */
    public boolean deposit(String accountNumber, double amount, String description) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);

            String operationId = "OP" + System.currentTimeMillis();
            Operation operation = new Operation(operationId, "DEPOSIT", amount,
                    accountNumber, description, newBalance);

            addOperation(operation);
            System.out.println("Deposit successful! New balance: DT" + String.format("%.2f", newBalance));
            return true;
        }
        System.out.println("Account not found: " + accountNumber);
        return false;
    }

    /**
     * Withdraw money from an account
     */
    public boolean withdraw(String accountNumber, double amount, String description) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            if (account.getBalance() >= amount) {
                double newBalance = account.getBalance() - amount;
                account.setBalance(newBalance);

                String operationId = "OP" + System.currentTimeMillis();
                Operation operation = new Operation(operationId, "WITHDRAW", amount,
                        accountNumber, description, newBalance);

                addOperation(operation);
                System.out.println("Withdrawal successful! New balance: DT" + String.format("%.2f", newBalance));
                return true;
            } else {
                System.out.println("Insufficient funds. Current balance: DT" +
                        String.format("%.2f", account.getBalance()));
                return false;
            }
        }
        System.out.println("Account not found: " + accountNumber);
        return false;
    }

    // ==================== UTILITY METHODS ====================

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public int getTotalAccounts() {
        return accounts.size();
    }

    public int getTotalOperations() {
        return operations.size();
    }

    public void displayBankStatistics() {
        System.out.println("\nBank Statistics");
        System.out.println("Total Accounts: " + getTotalAccounts());
        System.out.println("Total Operations: " + getTotalOperations());

        double totalBalance = 0;
        for (Account account : accounts.values()) {
            totalBalance += account.getBalance();
        }
        System.out.println("Total Bank Balance: DT" + String.format("%.2f", totalBalance));
        System.out.println("========================\n");
    }
}
