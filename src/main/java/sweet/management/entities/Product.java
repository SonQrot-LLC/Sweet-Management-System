package sweet.management.entities;

import sweet.management.services.DatabaseService;
import java.sql.*;

public class Product {
    private final int productId;
    private String productName;
    private String description;
    private double price;
    private int stock;
    private final Timestamp createdAt;
    private final int storeId;
    public static final int UPDATE_NAME = 1;
    public static final int UPDATE_DESCRIPTION = 2;
    public static final int UPDATE_PRICE = 3;
    public static final int UPDATE_STOCK = 4;
    public static final int DELETE_PRODUCT = 5;

    // Constructor
    public Product(int productId, String productName, String description, double price, int stock, Timestamp createdAt, int storeId) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.createdAt = createdAt;
        this.storeId = storeId;
    }

    public Product(String productName, String description, double price, int stock, int storeId) {
        this.productId = -1;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.storeId = storeId;
        createdAt = null;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getStoreId() {
        return storeId;
    }

    // Additional Methods
    public boolean isAvailable() {
        return this.stock > 0;
    }

    // Static Methods for Database Operations
    public static boolean createProduct(Product product, Connection conn) throws SQLException {
        String sql = "INSERT INTO products (product_name, description, price, stock, created_at, store_id) VALUES (?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setTimestamp(5, product.getCreatedAt());
            stmt.setInt(6, product.getStoreId());
        });
    }

    public static Product getProductById(int productId, Connection conn) throws SQLException {
        String sql = "SELECT *" +" FROM products WHERE product_id = ?";
        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getTimestamp("created_at"),
                            rs.getInt("store_id")
                    );
                }
            }
        }
        return null;
    }

    public static boolean updateProduct(Product product, Connection conn, int updateType) throws SQLException {
        String sql = null;
        switch (updateType) {
            case UPDATE_NAME:
                sql = "UPDATE products SET product_name = ? WHERE product_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, product.getProductName());
                    stmt.setInt(2, product.getProductId());
                });
            case UPDATE_DESCRIPTION:
                sql = "UPDATE products SET description = ? WHERE product_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, product.getDescription());
                    stmt.setInt(2, product.getProductId());
                });
            case UPDATE_PRICE:
                sql = "UPDATE products SET price = ? WHERE product_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setDouble(1, product.getPrice());
                    stmt.setInt(2, product.getProductId());
                });
            case UPDATE_STOCK:
                sql = "UPDATE products SET stock = ? WHERE product_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setInt(1, product.getStock());
                    stmt.setInt(2, product.getProductId());
                });
            default:
                return false;
        }
    }

    public static boolean deleteProduct(int productId, Connection conn) throws SQLException {
        String sql = "DELETE FROM products WHERE product_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, productId);
        });
    }
}
