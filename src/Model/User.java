package Model;

public class User {
    private String username;
    private String password;
    private String contactInfo;
    private boolean isDonor;

    public User(String username, String password, String contactInfo, boolean isDonor) {
        this.username = username;
        this.password = password;
        this.contactInfo = contactInfo;
        this.isDonor = isDonor;
    }

    // Getters and Setters
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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public boolean isDonor() {
        return isDonor;
    }

    public void setDonor(boolean donor) {
        isDonor = donor;
    }
}
