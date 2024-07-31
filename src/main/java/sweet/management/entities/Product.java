package sweet.management.entities;

import sweet.management.services.DatabaseService;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Product {
    private int productId;
    private String productName;
    private String description;
    private double price;
    private int stock;
    private final Timestamp createdAt;
    private final int storeId;
    private String expiryDate; // Changed to String
    public static final int UPDATE_NAME = 1;
    public static final int UPDATE_DESCRIPTION = 2;
    public static final int UPDATE_PRICE = 3;
    public static final int UPDATE_STOCK = 4;
    public static final int UPDATE_EXPIRY_DATE = 5;
    public static final int DELETE_PRODUCT = 6;

    // Constructor
    public Product(int productId, String productName, String description, double price, int stock, Timestamp createdAt, int storeId, String expiryDate) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.storeId = storeId;
        this.expiryDate = expiryDate;
        this.createdAt = createdAt;
    }


    public Product(String productName, String description, String price, String stock, int storeId, String expiryDate) {
        try {
            this.productId = nextId(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.productName = productName;
        this.description = description;
        this.price = Double.parseDouble(price);
        this.stock = Integer.parseInt(stock);
        this.storeId = storeId;
        this.expiryDate = expiryDate;
        this.createdAt = null;
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

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Additional Methods
    public boolean isAvailable() {
        return this.stock > 0;
    }

    // Static Methods for Database Operations
    public static boolean createProduct(Product product, Connection conn) throws SQLException {
        String sql = "INSERT INTO products (product_name, description, price, stock, created_at, store_id, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setTimestamp(5, product.getCreatedAt());
            stmt.setInt(6, product.getStoreId());
            stmt.setString(7, product.getExpiryDate()); // Changed to String
        });
    }

    public static Product getProductById(int productId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
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
                            rs.getInt("store_id"),
                            rs.getString("expiry_date") // Changed to String
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
            case UPDATE_EXPIRY_DATE:
                sql = "UPDATE products SET expiry_date = ? WHERE product_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, product.getExpiryDate()); // Changed to String
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
    public static boolean resetIdCounter(Connection conn) throws SQLException {
        String sql = "ALTER TABLE products AUTO_INCREMENT = 1;";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {});
    }

    public static boolean setId(int id, Product product, Connection conn) throws SQLException {
        String sql = "UPDATE products SET product_id = ? WHERE product_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, id);
            stmt.setInt(2, product.getProductId());
        });
    }


    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT AUTO_INCREMENT\n" +
                "FROM information_schema.TABLES\n" +
                "WHERE TABLE_SCHEMA = ?" +
                "  AND TABLE_NAME = 'products';";
        if (conn == null) {
            throw new SQLException("No connection");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "sweetmanagementsystem");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
//                    System.out.println(rs.getInt(1));
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }





        public static List<Product> getProductsExpiringInLessThan120Days(Connection conn) throws SQLException {
            String sql = "SELECT * FROM products WHERE expiry_date <= ?";
            List<Product> products = new ArrayList<>();

            if (conn == null) {
                throw new SQLException("No connection");
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                LocalDate currentDate = LocalDate.now();
                LocalDate expiryThresholdDate = currentDate.plusDays(120);
                stmt.setDate(1, Date.valueOf(expiryThresholdDate));
                System.out.println(Date.valueOf(expiryThresholdDate));
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Product product = new Product(
                                rs.getInt("product_id"),
                                rs.getString("product_name"),
                                rs.getString("description"),
                                rs.getDouble("price"),
                                rs.getInt("stock"),
                                rs.getTimestamp("created_at"),
                                rs.getInt("store_id"),
                                rs.getString("expiry_date") // Assuming expiry_date is stored as a String
                        );
                        System.out.println(rs.getString("product_name"));
                        products.add(product);
                    }
                }
            }
            return products;
        }
    }

