package services;

import models.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGraphicInterface {
    private static BankService bankService;
    private static User currentUser = null;
    private static boolean isAdmin = false;

    // Main UI components
    private static JFrame mainFrame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    // Colors and Styles
    private static final Color PRIMARY_COLOR = new Color(66, 139, 202);
    private static final Color SECONDARY_COLOR = new Color(70, 130, 180);
    private static final Color BACKGROUND_COLOR = new Color(245, 247, 250);
    private static final Color WHITE = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 24);
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 14);

    /**
     * Main entry point
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            initializeSystem();
            createAndShowGUI();
        });
    }

    /**
     * Initialize banking system
     */

    private static void initializeSystem() {
        Admin admin = new Admin("ADM001", "USR001", "admin", "admin123",
                "System Administrator", "admin@bank.com", "555-0100",
                "Bank HQ", "IT", 10);

        bankService = new BankService(admin);

        // Add sample users
        User user1 = new User("USR001", "rayenRT", "pass123", "Rayen Taleb",
                "rayen123taleb@email.com", "27443309", "el haouaria, nabeul");
        User user2 = new User("USR002", "ahmedS", "pass456", "Ahmed slimi",
                "ahmed456slimi@email.com", "90445884", "maknessi, sidibouzid");
        bankService.registerUser(user1);
        bankService.registerUser(user2);

        // Add sample accounts
        Account account1 = new Account("ACC001", 6000.00, "Savings", user1);
        Account account2 = new Account("ACC002", 5500.00, "Checking", user2);

        bankService.addAccount(account1);
        bankService.addAccount(account2);

        System.out.println("✓ System initialized with GUI!");
    }

    /**
     * Create and show the main GUI
     */
    private static void createAndShowGUI() {
        mainFrame = new JFrame("Banking Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Add all panels
        mainPanel.add(createWelcomePanel(), "WELCOME");
        mainPanel.add(createAdminLoginPanel(), "ADMIN_LOGIN");
        mainPanel.add(createUserLoginPanel(), "USER_LOGIN");
        mainPanel.add(createAdminPanel(), "ADMIN");
        mainPanel.add(createUserPanel(), "USER");

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    // ==================== WELCOME PANEL ====================

    private static JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // Title
        JLabel titleLabel = new JLabel("Banking Management System", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        buttonsPanel.setBackground(BACKGROUND_COLOR);
        buttonsPanel.setMaximumSize(new Dimension(300, 200));

        JButton adminBtn = createStyledButton("Admin Login", PRIMARY_COLOR);
        adminBtn.addActionListener(e -> showPanel("ADMIN_LOGIN"));

        JButton userBtn = createStyledButton("User Login", SECONDARY_COLOR);
        userBtn.addActionListener(e -> showPanel("USER_LOGIN"));

        JButton exitBtn = createStyledButton("Exit", new Color(220, 20, 60));
        exitBtn.addActionListener(e -> System.exit(0));

        buttonsPanel.add(adminBtn);
        buttonsPanel.add(userBtn);
        buttonsPanel.add(exitBtn);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(BACKGROUND_COLOR);

        JPanel topPanel = new JPanel(new GridLayout(2, 1, 10, 20));
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.add(titleLabel);


        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.SOUTH);

        return panel;
    }

    // ==================== ADMIN LOGIN PANEL ====================

    private static JPanel createAdminLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel title = new JLabel("Admin Login", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY_COLOR);
        panel.add(title, gbc);

        // Username
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(HEADER_FONT);
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        usernameField.setFont(NORMAL_FONT);
        usernameField.setText("");//admin
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(HEADER_FONT);
        panel.add(passLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(NORMAL_FONT);
        passwordField.setText("");//admin123
        panel.add(passwordField, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(BACKGROUND_COLOR);

        JButton loginBtn = createStyledButton("Login", PRIMARY_COLOR);
        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (bankService.adminLogin(username, password)) {
                isAdmin = true;
                showPanel("ADMIN");
                showMessage("Success", "Admin login successful!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMessage("Login Failed", "Invalid credentials!\nHint: admin / admin123", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backBtn = createStyledButton("Back", SECONDARY_COLOR);
        backBtn.addActionListener(e -> showPanel("WELCOME"));

        btnPanel.add(loginBtn);
        btnPanel.add(backBtn);
        panel.add(btnPanel, gbc);

        return panel;
    }

    // ==================== USER LOGIN PANEL ====================

    private static JPanel createUserLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel title = new JLabel("User Login", SwingConstants.CENTER);
        title.setFont(TITLE_FONT);
        title.setForeground(PRIMARY_COLOR);
        panel.add(title, gbc);

        // Username
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(HEADER_FONT);
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(15);
        usernameField.setFont(NORMAL_FONT);
        usernameField.setText("");
        panel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(HEADER_FONT);
        panel.add(passLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(NORMAL_FONT);
        passwordField.setText("");
        panel.add(passwordField, gbc);

        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(BACKGROUND_COLOR);

        JButton loginBtn = createStyledButton("Login", PRIMARY_COLOR);
        loginBtn.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            currentUser = bankService.userLogin(username, password);

            if (currentUser != null) {
                isAdmin = false;
                showPanel("USER");
                showMessage("Success", "Welcome, " + currentUser.getName() + "!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMessage("Login Failed", "Invalid credentials!\nTry: Rayen / Rayen123 or Ahmed / Ahmed123", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton backBtn = createStyledButton("Back", SECONDARY_COLOR);
        backBtn.addActionListener(e -> showPanel("WELCOME"));

        btnPanel.add(loginBtn);
        btnPanel.add(backBtn);
        panel.add(btnPanel, gbc);

        return panel;
    }

    // ==================== ADMIN PANEL ====================

    private static JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);

        // Buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        buttonsPanel.setBackground(BACKGROUND_COLOR);

        buttonsPanel.add(createAdminButton("View All Operations", e -> showAllOperations()));
        buttonsPanel.add(createAdminButton("Add Account", e -> addAccount()));
        buttonsPanel.add(createAdminButton("Delete Account", e -> deleteAccount()));
        buttonsPanel.add(createAdminButton("Deposit Money", e -> depositMoney()));
        buttonsPanel.add(createAdminButton("Withdraw Money", e -> withdrawMoney()));
        buttonsPanel.add(createAdminButton("View Balance", e -> viewBalance()));
        buttonsPanel.add(createAdminButton("Bank Statistics", e -> showStatistics()));
        buttonsPanel.add(createAdminButton("Delete Operation", e -> deleteOperation()));

        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logoutPanel.setBackground(BACKGROUND_COLOR);

        JButton logoutBtn = createAdminButton("Logout", e -> {
            bankService.adminLogout();
            isAdmin = false;
            showPanel("WELCOME");
        });
        logoutBtn.setBackground(new Color(220, 20, 60));
        logoutPanel.add(logoutBtn);

        // Add components to the main panel
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);
        panel.add(logoutPanel, BorderLayout.SOUTH); // Place the centered logout button at the bottom

        return panel;
    }

    // ==================== USER PANEL ====================

    private static JPanel createUserPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title with user name
        JLabel titleLabel = new JLabel("User Dashboard - " + (currentUser != null ? currentUser.getName() : "Guest"),
                SwingConstants.CENTER);
        titleLabel.setFont(TITLE_FONT);
        titleLabel.setForeground(PRIMARY_COLOR);

        // Buttons
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1, 10, 10));
        buttonsPanel.setBackground(BACKGROUND_COLOR);

        buttonsPanel.add(createUserButton("View My Information", e -> viewMyInfo()));
        buttonsPanel.add(createUserButton("Update My Information", e -> updateMyInfo()));
        buttonsPanel.add(createUserButton("View Account Balance", e -> viewMyBalance()));
        buttonsPanel.add(createUserButton("View Account Operations", e -> viewMyOperations()));

        JButton logoutBtn = createUserButton("Logout", e -> {
            if (currentUser != null) currentUser.logout();
            currentUser = null;
            showPanel("WELCOME");
        });
        logoutBtn.setBackground(new Color(220, 20, 60));
        buttonsPanel.add(logoutBtn);

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.CENTER);

        return panel;
    }

    // ==================== HELPER METHODS ====================

    private static JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(WHITE);
        button.setFont(HEADER_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(250, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private static JButton createAdminButton(String text, ActionListener listener) {
        JButton button = createStyledButton(text, PRIMARY_COLOR);
        button.addActionListener(listener);
        return button;
    }

    private static JButton createUserButton(String text, ActionListener listener) {
        JButton button = createStyledButton(text, SECONDARY_COLOR);
        button.addActionListener(listener);
        return button;
    }

    private static void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    private static void showMessage(String title, String message, int messageType) {
        JOptionPane.showMessageDialog(mainFrame, message, title, messageType);
    }

    // ==================== ADMIN OPERATIONS ====================

    private static void showAllAccounts() {
    }

    private static void showAllOperations() {
        String message = "Operations functionality:\n\n" +
                "• Deposits track money added to accounts\n" +
                "• Withdrawals track money removed\n" +
                "• Each operation has timestamp and description\n\n" +
                "All operations are recorded in the system\n" +
                "and can be viewed by admin.";

        showMessage("Operations", message, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void addAccount() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField accNumField = new JTextField();
        JTextField balanceField = new JTextField("1000");
        String[] types = {"Savings", "Checking"};
        JComboBox<String> typeCombo = new JComboBox<>(types);
        JTextField userIdField = new JTextField();

        panel.add(new JLabel("Account Number:"));
        panel.add(accNumField);
        panel.add(new JLabel("Initial Balance:"));
        panel.add(balanceField);
        panel.add(new JLabel("Account Type:"));
        panel.add(typeCombo);
        panel.add(new JLabel("User ID:"));
        panel.add(userIdField);

        int result = JOptionPane.showConfirmDialog(mainFrame, panel,
                "Add New Account", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String accNum = accNumField.getText().trim();
                double balance = Double.parseDouble(balanceField.getText());
                String type = (String) typeCombo.getSelectedItem();
                String userId = userIdField.getText().trim();

                User user = bankService.getUser(userId);
                if (user == null) {
                    showMessage("User Not Found", "Creating new user...", JOptionPane.WARNING_MESSAGE);
                    String username = JOptionPane.showInputDialog(mainFrame, "Username:");
                    String password = JOptionPane.showInputDialog(mainFrame, "Password:");
                    String name = JOptionPane.showInputDialog(mainFrame, "Full Name:");
                    user = new User(userId, username, password, name,
                            "user@email.com", "555-0000", "Default Address");
                    bankService.registerUser(user);
                }

                Account account = new Account(accNum, balance, type, user);
                if (bankService.addAccount(account)) {
                    showMessage("Success", "Account created successfully!", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException e) {
                showMessage("Error", "Please enter a valid balance amount.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void deleteAccount() {
        String accNum = JOptionPane.showInputDialog(mainFrame, "Enter Account Number to delete:");

        if (accNum != null && !accNum.trim().isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(mainFrame,
                    "Are you sure you want to delete account " + accNum + "?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                if (bankService.deleteAccount(accNum)) {
                    showMessage("Success", "Account deleted successfully!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    showMessage("Error", "Account not found.", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static void depositMoney() {
        String accNum = JOptionPane.showInputDialog(mainFrame, "Enter Account Number:");
        if (accNum == null || accNum.trim().isEmpty()) return;

        try {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(mainFrame,
                    "Enter amount to deposit:"));
            String desc = JOptionPane.showInputDialog(mainFrame, "Enter description:");
            if (desc == null || desc.trim().isEmpty()) desc = "Deposit";

            if (bankService.deposit(accNum, amount, desc)) {
                showMessage("Success", "Deposit successful!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMessage("Error", "Deposit failed. Account not found.", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            showMessage("Error", "Please enter a valid amount.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void withdrawMoney() {
        String accNum = JOptionPane.showInputDialog(mainFrame, "Enter Account Number:");
        if (accNum == null || accNum.trim().isEmpty()) return;

        try {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(mainFrame,
                    "Enter amount to withdraw:"));
            String desc = JOptionPane.showInputDialog(mainFrame, "Enter description:");
            if (desc == null || desc.trim().isEmpty()) desc = "Withdrawal";

            if (bankService.withdraw(accNum, amount, desc)) {
                showMessage("Success", "Withdrawal successful!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMessage("Error", "Withdrawal failed. Insufficient funds or account not found.", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            showMessage("Error", "Please enter a valid amount.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void viewBalance() {
        String accNum = JOptionPane.showInputDialog(mainFrame, "Enter Account Number:");
        if (accNum == null || accNum.trim().isEmpty()) return;

        double balance = bankService.viewBalance(accNum);
        if (balance >= 0) {
            showMessage("Account Balance",
                    "Account: " + accNum + "\nBalance: DT" + String.format("%.2f", balance),
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("Error", "Account not found.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void showStatistics() {
        String stats = "BANK STATISTICS\n\n" +
                "Total Accounts: " + bankService.getTotalAccounts() + "\n" +
                "Total Operations: " + bankService.getTotalOperations() + "\n\n";
        showMessage("Statistics", stats, JOptionPane.INFORMATION_MESSAGE);
    }

    private static void deleteOperation() {
        showMessage("Delete Operation",
                "Enter Operation ID to delete:\n\n" +
                "Note: Operation IDs are displayed as OP followed by timestamp\n" +
                "Example: OP1234567890123",
                JOptionPane.INFORMATION_MESSAGE);

        String opId = JOptionPane.showInputDialog(mainFrame, "Operation ID:");

        if (opId != null && !opId.trim().isEmpty()) {
            if (bankService.deleteOperation(opId)) {
                showMessage("Success", "Operation deleted successfully!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMessage("Error", "Operation not found.", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // ==================== USER OPERATIONS ====================

    private static void viewMyInfo() {
        if (currentUser != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("<html><h2 style='color: #4287CA;'>MY INFORMATION</h2><br><br>");
            sb.append("<b>Name:</b> ").append(currentUser.getName()).append("<br>");
            sb.append("<b>Email:</b> ").append(currentUser.getEmail()).append("<br>");
            sb.append("<b>Phone:</b> ").append(currentUser.getPhoneNumber()).append("<br>");
            sb.append("<b>Address:</b> ").append(currentUser.getAddress());
            sb.append("</html>");

            JOptionPane.showMessageDialog(mainFrame, sb.toString(),
                    "My Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void updateMyInfo() {
        if (currentUser == null) return;

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

        JTextField nameField = new JTextField(currentUser.getName());
        JTextField emailField = new JTextField(currentUser.getEmail());
        JTextField phoneField = new JTextField(currentUser.getPhoneNumber());
        JTextField addressField = new JTextField(currentUser.getAddress());

        panel.add(new JLabel("Full Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        int result = JOptionPane.showConfirmDialog(mainFrame, panel,
                "Update Information", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            currentUser.setName(nameField.getText());
            currentUser.setEmail(emailField.getText());
            currentUser.setPhoneNumber(phoneField.getText());
            currentUser.setAddress(addressField.getText());
            showMessage("Success", "Information updated successfully!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void viewMyBalance() {
        String accNum = JOptionPane.showInputDialog(mainFrame, "Enter your Account Number:");
        if (accNum == null || accNum.trim().isEmpty()) return;

        double balance = bankService.viewUserBalance(accNum, currentUser);
        if (balance >= 0) {
            showMessage("Your Balance",
                    "Account: " + accNum + "\nBalance: DT" + String.format("%.2f", balance),
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            showMessage("Error", "Account not found or access denied.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void viewMyOperations() {
        String accNum = JOptionPane.showInputDialog(mainFrame, "Enter your Account Number:");
        if (accNum == null || accNum.trim().isEmpty()) return;

        String message = "Operations for account: " + accNum + "\n\n" +
                "This shows all deposits and withdrawals\n" +
                "for your account in chronological order.\n\n" +
                "Features:\n" +
                "  • View transaction history\n" +
                "  • Track deposits and withdrawals\n" +
                "  • See timestamps and descriptions";

        showMessage("Account Operations", message, JOptionPane.INFORMATION_MESSAGE);
    }

}
