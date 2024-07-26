package sweet.management.entities;

import java.sql.*;
import java.util.Objects;

public class User {
    private String email;
    private String password;
    private String role;
    private String city;
    private Timestamp createdAt;

    // Constructor
    public User(String email, String password, String role, String city, Timestamp createdAt) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.city = city;
        this.createdAt = createdAt;
    }

    public User(String email, String password, String role, String city) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.city = city;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Additional Methods
    public boolean isAdmin() {
        return Objects.equals(this.role, "admin");
    }

    public boolean isStoreOwner() {
        return Objects.equals(this.role, "store_owner");
    }

    public boolean isRawMaterialSupplier() {
        return Objects.equals(this.role, "raw_material_supplier");
    }

    public boolean isBeneficiaryUser() {
        return Objects.equals(this.role, "beneficiary_user");
    }

    // Override toString method for easier debugging
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", city='" + city + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public static void createUser(User user, Connection conn) throws SQLException {
        String sql = "INSERT INTO Users (email, password, role, city, created_at) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = conn;
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getCity());
            stmt.setTimestamp(5, user.getCreatedAt());
            stmt.executeUpdate();
        }
    }

    public static User getUserByEmail(String email, Connection conn) throws SQLException {
        String sql = "SELECT *"+" FROM Users WHERE email = ?";
        User user = null;
        if (conn == null)
            throw new SQLException("No connection");
        try (
                Connection connection = conn;
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("city"),
                            rs.getTimestamp("created_at")
                    );
                }
            }

        }
        return user;
    }

    public static void updateUser(User user,  Connection conn) throws SQLException {
        if (conn == null)
            throw new SQLException("No connection");

        String sql = "UPDATE Users SET password = ?, role = ?, city = ?, created_at = ? WHERE email = ?";

        try (Connection connection = conn;
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getRole());
            stmt.setString(3, user.getCity());
            stmt.setTimestamp(4, user.getCreatedAt());
            stmt.setString(5, user.getEmail());
            stmt.executeUpdate();
        }
    }

    public static void deleteUser(String email, Connection conn) throws SQLException {
        String sql = "DELETE FROM Users WHERE email = ?";

        try (Connection connection = conn;
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        }
    }



}


