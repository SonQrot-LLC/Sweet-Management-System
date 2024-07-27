package sweet.management.entities;

import sweet.management.UserAuthService;
import sweet.management.services.DatabaseService;

import java.sql.*;

public class Store {
    private int storeId;
    private final String ownerEmail;
    private String storeName;
    private String businessInfo;
    private Timestamp createdAt;
    public static final int UPDATE_STORE_NAME = 1;
    public static final int UPDATE_BUSINESS_INFO = 2;
    public static final int DELETE_STORE = 3;

    // Constructors
    public Store(int storeId, String ownerEmail, String storeName, String businessInfo, Timestamp createdAt) {
        this.storeId = storeId;
        this.ownerEmail = ownerEmail;
        this.storeName = storeName;
        this.businessInfo = businessInfo;
        this.createdAt = createdAt;
    }

//    public Store(String ownerEmail, String storeName, String businessInfo) {
//        this.ownerEmail = ownerEmail;
//        this.storeName = storeName;
//        this.businessInfo = businessInfo;
//    }

    // Getters and Setters
    public int getStoreId() {
        return storeId;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }

//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }

    // Additional Methods
    public static void createStore(Store store, Connection conn) throws SQLException {
        String sql = "INSERT INTO stores (owner_email, store_name, business_info, created_at) VALUES (?, ?, ?, ?)";
        DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, store.getOwnerEmail());
            stmt.setString(2, store.getStoreName());
            stmt.setString(3, store.getBusinessInfo());
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        });
    }

    public static Store getStoreByOwnerEmail(String ownerEmail, Connection conn) throws SQLException {
        String sql = "SELECT * "+" FROM stores WHERE owner_email = ?";
        if (conn == null)
            throw new SQLException("No connection");

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ownerEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Store(
                            rs.getInt("store_id"),
                            rs.getString("owner_email"),
                            rs.getString("store_name"),
                            rs.getString("business_info"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        }
        return null;
    }

    public static boolean updateStoreName(Store store, Connection conn) throws SQLException {
        String sql = "UPDATE stores SET store_name = ? WHERE owner_email = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, store.getStoreName());
            stmt.setString(2, store.getOwnerEmail());
        });
    }

    public static boolean updateBusinessInfo(Store store, Connection conn) throws SQLException {
        String sql = "UPDATE stores SET business_info = ? WHERE owner_email = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, store.getBusinessInfo());
            stmt.setString(2, store.getOwnerEmail());
        });
    }

    public static boolean updateStore(Store store, Connection conn, int updateType, UserAuthService userAuthService) {
        try {
            if (conn == null || store == null || userAuthService == null || (!userAuthService.getLoggedInUser().isAdmin() && !store.getOwnerEmail().equals(userAuthService.getLoggedInUser().getEmail()) )) {
                throw new SQLException("No connection or unauthorized user");
            }
            switch (updateType) {
                case UPDATE_STORE_NAME:
                    return updateStoreName(store, conn);
                case UPDATE_BUSINESS_INFO:
                    return updateBusinessInfo(store, conn);
                case DELETE_STORE:
                    return deleteStore(store.getStoreId(), conn);
                default:
                    return false;
            }
        } catch (SQLException e) {
            System.out.println("Error updating store: " + e.getMessage());
            return false;
        }
    }

    public static boolean deleteStore(int storeId, Connection conn) throws SQLException {
        String sql = "DELETE FROM stores WHERE store_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> stmt.setInt(1, storeId));
    }
}
