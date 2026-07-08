package models;

public class Admin extends User {
    private String adminId;
    private String department;
    private int accessLevel;

    public Admin(String adminId, String userId, String username, String password,
                 String name, String email, String phoneNumber, String address,
                 String department, int accessLevel) {
        super(userId, username, password, name, email, phoneNumber, address);
        this.adminId = adminId;
        this.department = department;
        this.accessLevel = accessLevel;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    @Override
    public boolean login(String username, String password) {
        if (super.login(username, password) && accessLevel > 0) {
            System.out.println("Admin access granted. Access Level: " + accessLevel);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", department='" + department + '\'' +
                ", accessLevel=" + accessLevel +
                ", " + super.toString() +
                '}';
    }
}
