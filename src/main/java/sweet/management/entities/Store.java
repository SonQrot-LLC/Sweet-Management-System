package sweet.management.entities;

import sweet.management.UserAuthService;
import sweet.management.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Store {
    private static final String NO_CONNECTION = "No connection";
    private static final String STORE_ID_COL = "store_id";
    private static final String OWNER_EMAIL_COL = "owner_email";
    private static final String STORE_NAME_COL = "store_name";
    private static final String BUSINESS_INFO_COL = "business_info";
    private static final String CREATED_AT_COL = "created_at";
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
            throw new RuntimeException("Something went wrong when trying to connect to database");
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
    public static void createStore(Store store, Connection conn) throws SQLException {
        String sql = "INSERT INTO stores (store_id, owner_email, store_name, business_info, created_at) VALUES (?, ?, ?, ?, ?)";
        DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, store.getStoreId()); // Include store_id in the insert
            stmt.setString(2, store.getOwnerEmail());
            stmt.setString(3, store.getStoreName());
            stmt.setString(4, store.getBusinessInfo());
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
        });
    }


    public static Store getStoreByOwnerEmail(String ownerEmail, Connection conn) throws SQLException {
        String sql = "SELECT" + " * FROM stores WHERE owner_email = ?";
        if (conn == null) {
            throw new SQLException(NO_CONNECTION);
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ownerEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Store(
                            rs.getInt(STORE_ID_COL),
                            rs.getString(OWNER_EMAIL_COL),
                            rs.getString(STORE_NAME_COL),
                            rs.getString(BUSINESS_INFO_COL),
                            rs.getTimestamp(CREATED_AT_COL)
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
            return switch (updateType) {
                case UPDATE_STORE_NAME -> updateStoreName(store, conn);
                case UPDATE_BUSINESS_INFO -> updateBusinessInfo(store, conn);
                case DELETE_STORE -> deleteStore(store.getStoreId(), conn);
                default -> false;
            };
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(DatabaseService.class.getName());
            logger.warning("Something went wrong when trying to update store");
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
            throw new SQLException(NO_CONNECTION);
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
        String sql = "SELECT " + "* FROM stores WHERE store_id = ?";
        if (conn == null) {
            throw new SQLException(NO_CONNECTION);
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, storeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Store(
                            rs.getInt(STORE_ID_COL),
                            rs.getString(OWNER_EMAIL_COL),
                            rs.getString(STORE_NAME_COL),
                            rs.getString(BUSINESS_INFO_COL),
                            rs.getTimestamp(CREATED_AT_COL)
                    );
                }
            }
        }
        return null; // Return null if no store is found with the given ID
    }

    public static List<Store> getAllStores(Connection conn) throws SQLException {
        String sql = "SELECT" + " * FROM stores";
        List<Store> stores = new ArrayList<>();
        if (conn == null) {
            throw new SQLException(NO_CONNECTION);
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                stores.add(new Store(
                        rs.getInt(STORE_ID_COL),
                        rs.getString(OWNER_EMAIL_COL),
                        rs.getString(STORE_NAME_COL),
                        rs.getString(BUSINESS_INFO_COL),
                        rs.getTimestamp(CREATED_AT_COL)
                ));
            }
        }
        return stores;
    }


}
