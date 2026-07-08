package models;

public class User {
    // Private fields for encapsulation
    private String userId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean loggedIn;

    public User(String userId, String username, String password, String name,
                String email, String phoneNumber, String address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.loggedIn = false;
    }

    // Getters and Setters for encapsulation

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            this.loggedIn = true;
            System.out.println("Login successful! Welcome, " + this.name + "!");
            return true;
        }
        System.out.println("Login failed. Invalid username or password.");
        return false;
    }

    public boolean logout() {
        if (this.loggedIn) {
            this.loggedIn = false;
            System.out.println("Logout successful. Goodbye, " + this.name + "!");
            return true;
        }
        System.out.println("You are not logged in.");
        return false;
    }

    public void displayPersonalInfo() {
        System.out.println("\nPersonal Information ");
        System.out.println("User ID: " + userId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("\n");
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
