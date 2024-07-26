package sweet.management.entities;

import java.sql.*;

public class UserProfile {

    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    // Define constants for each update operation
    public static final int UPDATE_FIRST_NAME = 1;
    public static final int UPDATE_LAST_NAME = 2;
    public static final int UPDATE_PHONE = 3;
    public static final int UPDATE_ADDRESS = 4;

    // Constructor
    public UserProfile(String email, String firstName, String lastName, String phone, String address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public UserProfile(String email) {
        this.email = email;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Override toString method for easier debugging
    @Override
    public String toString() {
        return "UserProfile{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


    // Function to create a new user profile
    public static void createUserProfile(UserProfile userProfile, Connection conn) throws SQLException {
        String sql = "INSERT INTO UserProfiles (email, first_name, last_name, phone, address) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userProfile.getEmail());
            stmt.setString(2, userProfile.getFirstName());
            stmt.setString(3, userProfile.getLastName());
            stmt.setString(4, userProfile.getPhone());
            stmt.setString(5, userProfile.getAddress());
            stmt.executeUpdate();
        }
    }

    // Function to update first name
    public static boolean updateFirstName(UserProfile userProfile, Connection conn) throws SQLException {
        String sql = "UPDATE UserProfiles SET first_name = ? WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userProfile.getFirstName());
            stmt.setString(2, userProfile.getEmail());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was affected
        }
    }

    public static boolean updateLastName(UserProfile userProfile, Connection conn) throws SQLException {
        String sql = "UPDATE UserProfiles SET last_name = ? WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userProfile.getLastName());
            stmt.setString(2, userProfile.getEmail());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was affected
        }
    }

    public static boolean updatePhone(UserProfile userProfile, Connection conn) throws SQLException {
        String sql = "UPDATE UserProfiles SET phone = ? WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userProfile.getPhone());
            stmt.setString(2, userProfile.getEmail());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was affected
        }
    }
    public static boolean updateAddress(UserProfile userProfile, Connection conn) throws SQLException {
        String sql = "UPDATE UserProfiles SET address = ? WHERE email = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userProfile.getAddress());
            stmt.setString(2, userProfile.getEmail());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Return true if at least one row was affected
        }
    }

    // Main function to call the appropriate update function based on the parameter
    public static boolean updateUserProfile(UserProfile userProfile, Connection conn, int updateType){
        try {
        switch (updateType) {
            case UPDATE_FIRST_NAME:
                return updateFirstName(userProfile, conn);
            case UPDATE_LAST_NAME:
                return updateLastName(userProfile, conn);
            case UPDATE_PHONE:
                return updatePhone(userProfile, conn);
            case UPDATE_ADDRESS:
                return updateAddress(userProfile, conn);
            default:
                return false;
        }
            }
        catch (Exception e) {
            e.printStackTrace();
        return false;}
    }

}
