package sweet.management.entities;

import sweet.management.UserAuthService;
import sweet.management.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final String email;
    private String password;
    private String role;
    private String city;
    private Timestamp createdAt;
    public static final int UPDATE_PASSWORD = 1;
    public static final int UPDATE_CITY = 2;
    public static final int UPDATE_ROLE = 3;
    public static final int DELETE_ACCOUNT = 4;

    // Constructor
    public User(String email, String password, String role, String city, Timestamp createdAt) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.city = city;
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

    // Additional Methods
    public boolean isAdmin() {
        return Objects.equals(this.role, "admin");
    }

    public boolean isBeneficiaryUser() {
        return Objects.equals(this.role, "beneficiary_user");

    }
    public boolean isStoreOwner() {
        return Objects.equals(this.role, "store_owner");
    }

    public boolean isRawMaterialSupplier(){
        return Objects.equals(this.role, "raw_material_supplier");
    }

    public static void createUser(User user, Connection conn) throws SQLException {
        String sql = "INSERT INTO users (email, password, role, city) VALUES (?, ?, ?, ?)";
        DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getCity());
        });
    }

    public static User getUserByEmail(String email, Connection conn) throws SQLException {
        String sql = "SELECT" + " * FROM users WHERE email = ?";
        if (conn == null)
            throw new SQLException("No connection");

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getString("city"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        }
        return null;
    }

    public static boolean updatePassword(User user, Connection conn) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getEmail());
        });
    }

    public static boolean updateRole(User user, Connection conn, UserAuthService userAuthService) throws SQLException {
//        if (!userAuthService.getLoggedInUser().isAdmin()) return false;

        String sql = "UPDATE users SET role = ? WHERE email = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, user.getRole());
            stmt.setString(2, user.getEmail());
        });
    }

    public static boolean updateCity(User user, Connection conn) throws SQLException {
        String sql = "UPDATE users SET city = ? WHERE email = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, user.getCity());
            stmt.setString(2, user.getEmail());
        });
    }

    public static boolean updateUser(User user, Connection conn, int updateType, UserAuthService userAuthService) {
        try {
            if (conn == null || user == null || userAuthService == null || (!userAuthService.getLoggedInUser().isAdmin() && !user.getEmail().equals(userAuthService.getLoggedInUser().getEmail()) )) {
                throw new SQLException("No connection or unauthorized user");
            }switch (updateType) {
                case UPDATE_PASSWORD:
                    return updatePassword(user, conn);
                case UPDATE_CITY:
                    return updateCity(user, conn);
                case UPDATE_ROLE:
                    return updateRole(user, conn, userAuthService);
                case DELETE_ACCOUNT:
                    return deleteUser(user.getEmail(), conn);
                default:
                    return false;
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteUser(String email, Connection conn) throws SQLException {
        String sql = "DELETE FROM users WHERE email = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> stmt.setString(1, email));
    }

    public static List<User> getUsersByFlag(int flag, Connection conn) throws SQLException {
        String sql;
        if (flag == 1) {
            sql = "SELECT * FROM users WHERE role = 'store_owner' OR role = 'raw_material_supplier' OR role = 'beneficiary_user'";
        } else if (flag == 2) {
            sql = "SELECT * FROM users WHERE role = 'beneficiary_user'";
        } else {
            throw new IllegalArgumentException("Invalid flag value. Use 1 for store owners and suppliers, or 2 for beneficiary users.");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("city"),
                        rs.getTimestamp("created_at")
                );
                users.add(user);
            }
            return users;
        }
    }

}
