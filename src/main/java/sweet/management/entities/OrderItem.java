package sweet.management.entities;

import sweet.management.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItem {
    private int orderItemId;
    private final int orderId;
    private final int productId;
    private int quantity;
    private double price;

    public static final int UPDATE_QUANTITY = 1;
    public static final int UPDATE_PRICE = 2;

    // Constructor
    public OrderItem(int orderItemId, int orderId, int productId, int quantity, double price) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderItem(int orderId, int productId, int quantity, double price) {
        try (Connection conn = DatabaseService.getConnection(true)) {
            this.orderItemId = nextId(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }


    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Static Methods for Database Operations
    public static boolean createOrderItem(OrderItem orderItem, Connection conn) throws SQLException {
        String sql = "INSERT INTO orderitems (order_item_id, order_id, product_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, orderItem.getOrderItemId()); // Set the ID
            stmt.setInt(2, orderItem.getOrderId());
            stmt.setInt(3, orderItem.getProductId());
            stmt.setInt(4, orderItem.getQuantity());
            stmt.setDouble(5, orderItem.getPrice());
        });
    }

    public static OrderItem getOrderItemById(int orderItemId, Connection conn) throws SQLException {
        String sql = "SELECT *" + " FROM orderitems WHERE order_item_id = ?";
        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderItemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderItem(
                            rs.getInt("order_item_id"),
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    );
                }
            }
        }
        return null;
    }

    public static boolean updateOrderItem(OrderItem orderItem, Connection conn, int updateType) throws SQLException {
        String sql = null;
        switch (updateType) {
            case UPDATE_QUANTITY:
                sql = "UPDATE orderitems SET quantity = ? WHERE order_item_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setInt(1, orderItem.getQuantity());
                    stmt.setInt(2, orderItem.getOrderItemId());
                });
            case UPDATE_PRICE:
                sql = "UPDATE orderitems SET price = ? WHERE order_item_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setDouble(1, orderItem.getPrice());
                    stmt.setInt(2, orderItem.getOrderItemId());
                });
            default:
                return false;
        }
    }

    public static boolean deleteOrderItem(int orderItemId, Connection conn) throws SQLException {
        String sql = "DELETE FROM orderitems WHERE order_item_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, orderItemId);
        });
    }

    public static List<OrderItem> getOrderItemsByOrder(int orderId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM orderitems WHERE order_id = ?";
        List<OrderItem> orderItems = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem orderItem = new OrderItem(
                            rs.getInt("order_item_id"),
                            rs.getInt("order_id"),
                            rs.getInt("product_id"),
                            rs.getInt("quantity"),
                            rs.getDouble("price")
                    );
                    orderItems.add(orderItem);
                }
            }
        }
        return orderItems;
    }
    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(order_item_id), 0) + 1 AS next_id FROM orderitems";
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

}
