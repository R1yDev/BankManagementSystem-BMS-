import models.*;
import services.BankService;
import java.util.Scanner;

public class Test {
    private static Scanner scanner = new Scanner(System.in);
    private static BankService bankService;

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  BANKING MANAGEMENT SYSTEM - TEST    ");
        System.out.println("========================================\n");

        System.out.println("SCENARIO 1: System Initialization");
        System.out.println("---------------------------------------");
        initializeSystem();

        System.out.println("\n\nSCENARIO 2: Admin Login and Account Management");
        System.out.println("---------------------------------------");
        testAdminFunctionality();

        System.out.println("\n\nSCENARIO 3: User Registration and Login");
        System.out.println("---------------------------------------");
        testUserRegistration();

        System.out.println("\n\nSCENARIO 4: Deposit Operations");
        System.out.println("---------------------------------------");
        testDepositOperations();

        System.out.println("\n\nSCENARIO 5: Withdrawal Operations");
        System.out.println("---------------------------------------");
        testWithdrawalOperations();

        System.out.println("\n\nSCENARIO 6: User Viewing Balance and Operations");
        System.out.println("---------------------------------------");
        testUserViewFunctionality();

        System.out.println("\n\nSCENARIO 7: Modifying Personal Information");
        System.out.println("---------------------------------------");
        testModifyPersonalInfo();

        System.out.println("\n\nSCENARIO 8: Admin Deleting Operations");
        System.out.println("---------------------------------------");
        testDeleteOperations();

        System.out.println("\n\nSCENARIO 9: Inheritance Demonstration");
        System.out.println("---------------------------------------");
        testInheritance();

        System.out.println("\n\nSCENARIO 10: Has-A Relationship Demonstration");
        System.out.println("---------------------------------------");
        testHasARelationship();

        System.out.println("\n\nSCENARIO 11: Encapsulation Demonstration");
        System.out.println("---------------------------------------");
        testEncapsulation();

        System.out.println("\n\nSCENARIO 12: Interactive Mode (Keyboard Input)");
        System.out.println("---------------------------------------");
        runInteractiveMode();

        System.out.println("\n\nSCENARIO 13: Bank Statistics");
        System.out.println("---------------------------------------");
        bankService.displayBankStatistics();

        System.out.println("\n========================================");
        System.out.println("  ALL TESTS COMPLETED SUCCESSFULLY!   ");
        System.out.println("========================================");

        scanner.close();
    }

    /**
     * Scenario 1: Initialize the banking system
     */
    private static void initializeSystem() {
        // Create an admin user (demonstrates inheritance)
        Admin admin = new Admin(
                "ADM001",             // Admin ID
                "USR001",                    // User ID
                "admin",                     // Username
                "admin123",                  // Password
                "Rayen taleb",                // Name
                "admin@bank.com",            // Email
                "70100200",                  // Phone
                "haouaria",          // Address
                "IT Department",             // Department
                5                            // Access Level
        );


        // Initialize bank service with admin
        bankService = new BankService(admin);
        System.out.println("Banking system initialized successfully!");
        System.out.println("Admin: " + admin.getName());
        System.out.println("Admin Department: " + admin.getDepartment());
        System.out.println("Access Level: " + admin.getAccessLevel());
    }

    private static void testAdminFunctionality() {
        // Admin login
        System.out.println("Attempting admin login...");
        bankService.adminLogin("admin", "admin123");

        // Create users
        User user1 = new User("USR001", "rayenRT", "pass123", "Rayen Taleb",
                "rayen123taleb@email.com", "27443309", "el haouaria, nabeul");
        User user2 = new User("USR002", "ahmedS", "pass456", "Ahmed slimi",
                "ahmed456slimi@email.com", "90445884", "maknessi, sidibouzid");
        User user3 = new User("USR003", "salahGn", "pass789", "Salah Gneunne",
                "salah_gn@email.com", "99808909", "sidiailouan, mahdia");

        // Register users
        bankService.registerUser(user1);
        bankService.registerUser(user2);
        bankService.registerUser(user3);

        // Create accounts (demonstrates has-a relationship)
        Account account1 = new Account("ACC001", 6000.00, "Savings", user1);
        Account account2 = new Account("ACC002", 5500.00, "Checking", user2);
        Account account3 = new Account("ACC003", 5000.00, "Savings", user3);

        // Add accounts
        bankService.addAccount(account1);
        bankService.addAccount(account2);
        bankService.addAccount(account3);

        System.out.println("\nAccounts created by admin:");

        // Admin logout
        bankService.adminLogout();
    }

    private static void testUserRegistration() {
        // User login test
        System.out.println("Testing user login...");
        User user = bankService.userLogin("rayenRT", "pass123");

        if (user != null) {
            System.out.println("User logged in successfully: " + user.getName());
        }

        // Test invalid login
        System.out.println("\nTesting invalid login...");
        bankService.userLogin("rayenRT", "wrongpass");
    }

    private static void testDepositOperations() {
        // Admin login
        bankService.adminLogin("admin", "admin123");

        // Perform deposits
        bankService.deposit("ACC001", 500.00, "Salary deposit");
        bankService.deposit("ACC001", 600.00, "Bonus");
        bankService.deposit("ACC002", 1000.00, "Initial deposit");
        bankService.deposit("ACC003", 2500.00, "Investment returns");

        // View balances
        System.out.println("\nBalances after deposits:");
        System.out.println("ACC001 Balance: DT" + bankService.viewBalance("ACC001"));
        System.out.println("ACC002 Balance: DT" + bankService.viewBalance("ACC002"));
        System.out.println("ACC003 Balance: DT" + bankService.viewBalance("ACC003"));

        bankService.listAllOperations();
        bankService.adminLogout();
    }

    private static void testWithdrawalOperations() {
        // Admin login
        bankService.adminLogin("admin", "admin123");

        // Perform withdrawals
        bankService.withdraw("ACC001", 300.00, "Shopping");
        bankService.withdraw("ACC002", 500.00, "Bill payment");
        bankService.withdraw("ACC003", 1000.00, "Vacation");

        // Test insufficient funds
        System.out.println("\nTesting insufficient funds...");
        bankService.withdraw("ACC001", 10000.00, "Large withdrawal");

        // View balances after withdrawals
        System.out.println("\nBalances after withdrawals:");
        System.out.println("ACC001 Balance: DT" + bankService.viewBalance("ACC001"));
        System.out.println("ACC002 Balance: DT" + bankService.viewBalance("ACC002"));
        System.out.println("ACC003 Balance: DT" + bankService.viewBalance("ACC003"));

        bankService.listAllOperations();
        bankService.adminLogout();
    }

    private static void testUserViewFunctionality() {
        User user = bankService.userLogin("ahmedS", "pass456");

        if (user != null) {
            // View personal information
            System.out.println("Viewing personal information:");
            bankService.viewPersonalInformation(user);

            // View balance
            System.out.println("Viewing account balance:");
            double balance = bankService.viewUserBalance("ACC002", user);
            System.out.println("Current Balance: DT" + balance);

            // List operations
            System.out.println("Listing account operations:");
            bankService.listUserOperations("ACC002", user);
        }

        user.logout();
    }

    private static void testModifyPersonalInfo() {
        User user = bankService.userLogin("salahGn", "pass789");

        if (user != null) {
            System.out.println("Original information:");
            bankService.viewPersonalInformation(user);

            // Modify personal information
            System.out.println("\nModifying personal information...");
            bankService.modifyPersonalInformation(user,
                    "Salah Gneunner",
                    "salah.Gneunner1998@gamail.com",
                    "98787989",
                    "mahdia centre ville");

            System.out.println("\nUpdated information:");
            bankService.viewPersonalInformation(user);
        }

        user.logout();
    }

    private static void testDeleteOperations() {
        bankService.adminLogin("admin", "admin123");

        System.out.println("Operations before deletion:");
        bankService.listAllOperations();

        // Get the first operation ID (simulated)
        Account account = bankService.getAccount("ACC001");
        if (account != null && !account.getOperations().isEmpty()) {
            String firstOpId = account.getOperations().get(0).getOperationId();

            System.out.println("\nDeleting operation: " + firstOpId);
            bankService.deleteOperation(firstOpId);

            System.out.println("\nOperations after deletion:");
            bankService.listAllOperations();
        }

        bankService.adminLogout();
    }



    private static void testInheritance() {
        System.out.println("Demonstrating Inheritance (Admin extends User):");
        System.out.println("--------------------------------------------------");

        Admin admin = new Admin("ADM002", "USR004", "superadmin", "pass123",
                "Alice Johnson", "alice@bank.com", "555-0200",
                "200 Bank Blvd", "Security", 10);

        // Admin can use User methods (inheritance)
        System.out.println("Admin name (from User class): " + admin.getName());
        System.out.println("Admin email (from User class): " + admin.getEmail());

        // Admin can use its own methods
        System.out.println("Admin ID (Admin specific): " + admin.getAdminId());
        System.out.println("Department (Admin specific): " + admin.getDepartment());
        System.out.println("Access Level (Admin specific): " + admin.getAccessLevel());

        // Admin can login (overridden method)
        System.out.println("\nTesting admin login (overridden method):");
        admin.login("superadmin", "pass123");
        admin.logout();

        System.out.println("\nInheritance verified: Admin has all User properties plus its own!");
    }

    private static void testHasARelationship() {
        System.out.println("Demonstrating Has-A Relationship:");
        System.out.println("----------------------------------");

        User user = new User("USR005", "lina", "pass123", "lina milad",
                "lina_Milad@email.com", "27456898", "kelibia, nabeul");

        Account account = new Account("ACC010", 3000.00, "Savings", user);

        // Account HAS-A User
        System.out.println("Account HAS-A User:");
        System.out.println("  Account Number: " + account.getAccountNumber());
        System.out.println("  Account Holder: " + account.getAccountHolder().getName());
        System.out.println("  Holder Email: " + account.getAccountHolder().getEmail());

        // Account HAS-A list of Operations (array of objects)
        System.out.println("\nAccount HAS-A List of Operations:");
        System.out.println("  Operations count before: " + account.getOperations().size());

        // Add operations to demonstrate array of objects
        Operation op1 = new Operation("OP001", "DEPOSIT", 500.00, "ACC010",
                "Test deposit", 3500.00);
        Operation op2 = new Operation("OP002", "WITHDRAW", 200.00, "ACC010",
                "Test withdraw", 3300.00);

        account.addOperation(op1);
        account.addOperation(op2);

        System.out.println("  Operations count after: " + account.getOperations().size());
        System.out.println("\nHas-A relationship verified!");
    }

    private static void testEncapsulation() {
        System.out.println("Demonstrating Encapsulation:");
        System.out.println("----------------------------");

        User user = new User("USR006", "sara", "pass123", "sarra ajili",
                "sara.2007@email.com", "53556889", "kelibia, nabeul");

        System.out.println("All fields are private, accessed only through getters/setters:");

        // Accessing private fields through getters
        System.out.println("  Name (via getter): " + user.getName());
        System.out.println("  Email (via getter): " + user.getEmail());

        // Modifying private fields through setters
        System.out.println("\nModifying name via setter:");
        user.setName("Sala alAjili.");
        System.out.println("  New Name (via getter): " + user.getName());

        // Attempting to access password (should only be available through getter)
        System.out.println("\n  Password (via getter, not direct access): " + user.getPassword());

        System.out.println("\nEncapsulation verified: All fields are private and controlled!");
    }

    private static void runInteractiveMode() {
        System.out.println("Starting Interactive Mode...");
        System.out.println("============================\n");

        boolean running = true;

        while (running) {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit Interactive Mode");
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        adminMenu();
                        break;
                    case 2:
                        userMenu();
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting interactive mode...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }


    private static void adminMenu() {
        System.out.print("\nEnter admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        if (bankService.adminLogin(username, password)) {
            boolean adminRunning = true;

            while (adminRunning) {
                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("1. Add Account");
                System.out.println("2. Delete Account");
                System.out.println("3. View All Operations");
                System.out.println("4. View Account Balance");
                System.out.println("5. Deposit");
                System.out.println("6. Withdraw");
                System.out.println("7. Delete Operation");
                System.out.println("8. View Bank Statistics");
                System.out.println("9. Logout");
                System.out.print("Choose an option: ");

                try {
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            addAccountInteractive();
                            break;
                        case 2:
                            deleteAccountInteractive();
                            break;
                        case 3:
                            bankService.listAllOperations();
                            break;
                        case 4:
                            viewBalanceInteractive();
                            break;
                        case 5:
                            depositInteractive();
                            break;
                        case 6:
                            withdrawInteractive();
                            break;
                        case 7:
                            deleteOperationInteractive();
                            break;
                        case 8:
                            bankService.displayBankStatistics();
                            break;
                        case 9:
                            bankService.adminLogout();
                            adminRunning = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }
    }

    private static void userMenu() {
        System.out.print("\nEnter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = bankService.userLogin(username, password);

        if (user != null) {
            boolean userRunning = true;

            while (userRunning) {
                System.out.println("\n===== USER MENU =====");
                System.out.println("1. View Personal Information");
                System.out.println("2. Modify Personal Information");
                System.out.println("3. View Account Balance");
                System.out.println("4. List Operations");
                System.out.println("5. Logout");
                System.out.print("Choose an option: ");

                try {
                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            bankService.viewPersonalInformation(user);
                            break;
                        case 2:
                            modifyInfoInteractive(user);
                            break;
                        case 3:
                            viewUserBalanceInteractive(user);
                            break;
                        case 4:
                            listUserOperationsInteractive(user);
                            break;
                        case 5:
                            user.logout();
                            userRunning = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }
    }

    private static void addAccountInteractive() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double balance = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter account type (Savings/Checking): ");
        String type = scanner.nextLine();
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();

        // Find user
        User user = bankService.getUser(userId);

        // If user doesn't exist, create a new user
        if (user == null) {
            user = new User(userId, "user" + userId, "pass123",
                    "User " + userId, "user" + userId + "@email.com",
                    "+216-" + userId, "Address " + userId);
            bankService.registerUser(user);
            System.out.println("New user created: " + user.getName());
        }

        Account account = new Account(accNum, balance, type, user);
        bankService.addAccount(account);
    }

    private static void deleteAccountInteractive() {
        System.out.print("Enter account number to delete: ");
        String accNum = scanner.nextLine();
        bankService.deleteAccount(accNum);
    }

    private static void viewBalanceInteractive() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        double balance = bankService.viewBalance(accNum);
        if (balance >= 0) {
            System.out.println("Balance: DT" + String.format("%.2f", balance));
        }
    }

    private static void depositInteractive() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        bankService.deposit(accNum, amount, desc);
    }

    private static void withdrawInteractive() {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter description: ");
        String desc = scanner.nextLine();
        bankService.withdraw(accNum, amount, desc);
    }

    private static void deleteOperationInteractive() {
        System.out.print("Enter operation ID to delete: ");
        String opId = scanner.nextLine();
        bankService.deleteOperation(opId);
    }

    private static void modifyInfoInteractive(User user) {
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();
        bankService.modifyPersonalInformation(user, name, email, phone, address);
    }

    private static void viewUserBalanceInteractive(User user) {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        double balance = bankService.viewUserBalance(accNum, user);
        if (balance >= 0) {
            System.out.println("Balance: DT" + String.format("%.2f", balance));
        }
    }

    private static void listUserOperationsInteractive(User user) {
        System.out.print("Enter account number: ");
        String accNum = scanner.nextLine();
        bankService.listUserOperations(accNum, user);
    }
}
