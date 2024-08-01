package sweet.management.entities;

import sweet.management.services.DatabaseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private final String userEmail;
    private final int storeId;
    private String orderStatus;
    private double totalAmount;
    private final Timestamp createdAt;

    public static final int UPDATE_STATUS = 1;
    public static final int UPDATE_TOTAL_AMOUNT = 2;
    public static final int DELETE_ORDER = 3;

    // Constructors
    public Order(int orderId, String userEmail, int storeId, String orderStatus, double totalAmount, Timestamp createdAt) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.storeId = storeId;
        this.orderStatus = orderStatus;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
    }

    public Order(String userEmail, int storeId, String orderStatus) {
        try {
            this.orderId = nextId(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.userEmail = userEmail;
        this.storeId = storeId;
        this.orderStatus = orderStatus;
        this.totalAmount = 0.0;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }


    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // Static Methods for Database Operations
    public static boolean createOrder(Order order, Connection conn) throws SQLException {
        String sql = "INSERT INTO orders (order_id, user_email, store_id, order_status, total_amount, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, order.getOrderId()); // Include order_id
            stmt.setString(2, order.getUserEmail());
            stmt.setInt(3, order.getStoreId());
            stmt.setString(4, order.getOrderStatus());
            stmt.setDouble(5, order.getTotalAmount());
            stmt.setTimestamp(6, order.getCreatedAt());
        });
    }


    public static Order getOrderById(int orderId, Connection conn) throws SQLException {
        String sql = "SELECT *" +" FROM orders WHERE order_id = ?";
        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getInt("order_id"),
                            rs.getString("user_email"),
                            rs.getInt("store_id"),
                            rs.getString("order_status"),
                            rs.getDouble("total_amount"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        }
        return null;
    }

    public static boolean updateOrder(Order order, Connection conn, int updateType) throws SQLException {
        String sql = null;
        switch (updateType) {
            case DELETE_ORDER:
                sql = "DELETE FROM orders WHERE order_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setInt(1, order.getOrderId());
                });

            case UPDATE_STATUS:
                sql = "UPDATE orders SET order_status = ? WHERE order_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, order.getOrderStatus());
                    stmt.setInt(2, order.getOrderId());
                });
            case UPDATE_TOTAL_AMOUNT:
                sql = "UPDATE orders SET total_amount = ? WHERE order_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setDouble(1, order.getTotalAmount());
                    stmt.setInt(2, order.getOrderId());
                });

            default:
                return false;
        }
    }

    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(order_id), 0) + 1 AS next_id FROM orders";
        if (conn == null) {
            throw new SQLException("No connection");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("next_id");
                }
            }
        }
        return 1; // Default to 1 if something goes wrong
    }


    public static List<Order> getOrdersByStoreId(int storeId, Connection conn) throws SQLException {
        String sql = "SELECT *" +" FROM orders WHERE store_id = ?";
        List<Order> orders = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, storeId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getInt("order_id"),
                            rs.getString("user_email"),
                            rs.getInt("store_id"),
                            rs.getString("order_status"),
                            rs.getDouble("total_amount"),
                            rs.getTimestamp("created_at")
                    );
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    public static List<Order> getOrdersByUserEmail(String userEmail, Connection conn) throws SQLException {
        String sql = "SELECT *" +" FROM orders WHERE user_email = ?";
        List<Order> orders = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userEmail);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order(
                            rs.getInt("order_id"),
                            rs.getString("user_email"),
                            rs.getInt("store_id"),
                            rs.getString("order_status"),
                            rs.getDouble("total_amount"),
                            rs.getTimestamp("created_at")
                    );
                    orders.add(order);
                }
            }
        }
        return orders;
    }
}
