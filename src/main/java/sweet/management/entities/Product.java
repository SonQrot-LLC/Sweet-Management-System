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
    private double discount; // New field for discount
    private final Timestamp createdAt;
    private final int storeId;
    private String expiryDate;

    public static final int UPDATE_NAME = 1;
    public static final int UPDATE_DESCRIPTION = 2;
    public static final int UPDATE_PRICE = 3;
    public static final int UPDATE_STOCK = 4;
    public static final int UPDATE_EXPIRY_DATE = 5;
    public static final int UPDATE_DISCOUNT = 6; // New constant for updating discount

    // Constructor
    public Product(int productId, String productName, String description, double price, int stock, double discount, Timestamp createdAt, int storeId, String expiryDate) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.discount = discount; // Initialize discount
        this.storeId = storeId;
        this.expiryDate = expiryDate;
        this.createdAt = createdAt;
    }

    public Product(String productName, String description, String price, String stock, String discount, int storeId, String expiryDate) {
        try {
            this.productId = nextId(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.productName = productName;
        this.description = description;
        this.price = Double.parseDouble(price);
        this.stock = Integer.parseInt(stock);
        this.discount = Double.parseDouble(discount); // Initialize discount
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
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
        String sql = "INSERT INTO products (product_name, description, price, stock, discount, created_at, store_id, expiry_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setDouble(5, product.getDiscount()); // Set discount
            stmt.setTimestamp(6, product.getCreatedAt());
            stmt.setInt(7, product.getStoreId());
            stmt.setString(8, product.getExpiryDate());
        });
    }

    public static Product getProductById(int productId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        if (conn == null) {
            throw new SQLException();
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
                            rs.getDouble("discount"), // Retrieve discount
                            rs.getTimestamp("created_at"),
                            rs.getInt("store_id"),
                            rs.getString("expiry_date")
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
                    stmt.setString(1, product.getExpiryDate());
                    stmt.setInt(2, product.getProductId());
                });
            case UPDATE_DISCOUNT: // New case for updating discount
                sql = "UPDATE products SET discount = ? WHERE product_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setDouble(1, product.getDiscount());
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

    public  boolean setId(int id,Connection conn) throws SQLException {
        int oldId = this.productId;
        this.productId = id;
        String sql = "UPDATE products SET product_id = ? WHERE product_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, id);
            stmt.setInt(2, oldId);
        });
    }

    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_NAME = 'products';";
        if (conn == null) {
            throw new SQLException("No connection");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "sweetmanagementsystem");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public static List<Product> getProductsExpiringInLessThan120Days(String email, Connection conn) throws SQLException {
        String sql = "SELECT p.* FROM products p " +
                "JOIN stores s ON p.store_id = s.store_id " +
                "WHERE s.owner_email = ? AND p.expiry_date <= ?";
        List<Product> products = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            LocalDate currentDate = LocalDate.now();
            LocalDate expiryThresholdDate = currentDate.plusDays(120);

            stmt.setString(1, email);
            stmt.setDate(2, Date.valueOf(expiryThresholdDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getDouble("discount"), // Retrieve discount
                            rs.getTimestamp("created_at"),
                            rs.getInt("store_id"),
                            rs.getString("expiry_date")
                    );
                    products.add(product);
                }
            }
        }
        return products;
    }




    public static List<Product> getProductsByUserEmail(String email, Connection conn) throws SQLException {
        String sql = "SELECT p.* FROM products p " +
                "JOIN stores s ON p.store_id = s.store_id " +
                "WHERE s.owner_email = ?";
        List<Product> products = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getDouble("discount"),
                            rs.getTimestamp("created_at"),
                            rs.getInt("store_id"),
                            rs.getString("expiry_date")
                    );
                    products.add(product);
                }
            }
        }
        return products;
    }
    public static List<Product> getAllProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        if (conn == null) {
            throw new SQLException("No connection");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            returnProductsList(stmt, products);
        }
        return products;
    }


    public static List<Product> getProductsByStoreId(int storeId, Connection conn) throws SQLException {
        String sql = "SELECT * FROM products WHERE store_id = ?";
        List<Product> products = new ArrayList<>();
        if (conn == null) {
            throw new SQLException("No connection");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, storeId); // Set the store ID in the SQL query
            returnProductsList(stmt, products);
        }
        return products;
    }

    private static void returnProductsList(PreparedStatement stmt, List<Product> products) throws SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock"),
                        rs.getDouble("discount"),
                        rs.getTimestamp("created_at"),
                        rs.getInt("store_id"),
                        rs.getString("expiry_date")
                );
                products.add(product);
            }
        }
    }

    public static List<Product> searchProducts(String searchTerm, Connection conn) throws SQLException {
        String sql = "SELECT * FROM products WHERE " +
                "LOWER(product_name) LIKE ? OR LOWER(description) LIKE ?";
        List<Product> products = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        // Prepare the search term for the SQL query
        String formattedSearchTerm = "%" + searchTerm.toLowerCase() + "%";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, formattedSearchTerm);
            stmt.setString(2, formattedSearchTerm);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("stock"),
                            rs.getDouble("discount"),
                            rs.getTimestamp("created_at"),
                            rs.getInt("store_id"),
                            rs.getString("expiry_date")
                    );
                    products.add(product);
                }
            }
        }
        return products;
    }


}


