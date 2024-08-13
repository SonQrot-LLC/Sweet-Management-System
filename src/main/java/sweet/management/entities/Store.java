package sweet.management.entities;

import sweet.management.UserAuthService;
import sweet.management.services.DatabaseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final int storeId;
    private final String ownerEmail;
    private String storeName;
    private String businessInfo;
    private final Timestamp createdAt;
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

    public Store(String ownerEmail, String storeName, String businessInfo) {
        try {
            this.storeId = nextId(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            throw new RuntimeException("Error generating next store ID", e);
        }
        this.ownerEmail = ownerEmail;
        this.storeName = storeName;
        this.businessInfo = businessInfo;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // Additional Methods
    public static boolean createStore(Store store, Connection conn) throws SQLException {
        String sql = "INSERT INTO stores (store_id, owner_email, store_name, business_info, created_at) VALUES (?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, store.getStoreId()); // Include store_id in the insert
            stmt.setString(2, store.getOwnerEmail());
            stmt.setString(3, store.getStoreName());
            stmt.setString(4, store.getBusinessInfo());
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
        });
    }


    public static Store getStoreByOwnerEmail(String ownerEmail, Connection conn) throws SQLException {
        String sql = "SELECT * FROM stores WHERE owner_email = ?";
        if (conn == null) {
            throw new SQLException("No connection");
        }

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
            if (conn == null || store == null || userAuthService == null || (!userAuthService.getLoggedInUser().isAdmin() && !store.getOwnerEmail().equals(userAuthService.getLoggedInUser().getEmail()))) {
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



    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(store_id) AS max_id FROM stores";
        if (conn == null) {
            throw new SQLException("No connection");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("max_id") + 1;
                }
            }
        }
        return 1;
    }
    public static Store getStoreById(int storeId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM stores WHERE store_id = ?";
        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, storeId);
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
        return null; // Return null if no store is found with the given ID
    }

    public static List<Store> getAllStores(Connection conn) throws SQLException {
        String sql = "SELECT * FROM stores";
        List<Store> stores = new ArrayList<>();
        if (conn == null) {
            throw new SQLException("No connection");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                stores.add(new Store(
                        rs.getInt("store_id"),
                        rs.getString("owner_email"),
                        rs.getString("store_name"),
                        rs.getString("business_info"),
                        rs.getTimestamp("created_at")
                ));
            }
        }
        return stores;
    }


}
