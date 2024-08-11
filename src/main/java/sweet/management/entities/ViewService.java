package sweet.management.entities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ViewService {

    // Retrieve best-selling products
    public List<Product> getBestSellingProducts(Connection conn) throws SQLException {
        if (conn == null) {
            throw new SQLException("Database connection is null");
        }

        List<Product> bestSellingProducts = new ArrayList<>();
        String query = "SELECT *" + " FROM bestsellingproducts";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");
                int storeId = rs.getInt("store_id");
                int totalQuantitySold = rs.getInt("total_quantity_sold");
                bestSellingProducts.add(new Product(productId, productName, storeId, totalQuantitySold));
            }
        }
        return bestSellingProducts;
    }

    // Retrieve store profits
    public List<StoreProfit> getStoreProfits(Connection conn) throws SQLException {
        if (conn == null) {
            throw new SQLException("Database connection is null");
        }

        List<StoreProfit> storeProfits = new ArrayList<>();
        String query = "SELECT *" + " FROM storeprofits";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int storeId = rs.getInt("store_id");
                String storeName = rs.getString("store_name");
                double totalProfit = rs.getDouble("total_profit");
                storeProfits.add(new StoreProfit(storeId, storeName, totalProfit));
            }
        }
        return storeProfits;
    }

    // Retrieve users by city
    public List<UserByCity> getUsersByCity(Connection conn) throws SQLException {
        if (conn == null) {
            throw new SQLException("Database connection is null");
        }

        List<UserByCity> usersByCity = new ArrayList<>();
        String query = "SELECT *" + " FROM usersbycity";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String cityName = rs.getString("city");
                int userCount = rs.getInt("user_count");
                usersByCity.add(new UserByCity(cityName, userCount));
            }
        }
        return usersByCity;
    }

    public class Product {
        private final int productId;
        private final String productName;
        private final int storeId;
        private final int totalQuantitySold;

        // Constructor
        public Product(int productId, String productName, int storeId, int totalQuantitySold) {
            this.productId = productId;
            this.productName = productName;
            this.storeId = storeId;
            this.totalQuantitySold = totalQuantitySold;
        }
        // Getters
        public int getProductId() {
            return productId;
        }
        public String getProductName() {
            return productName;
        }
        public int getStoreId() {
            return storeId;
        }
        public int getTotalQuantitySold() {
            return totalQuantitySold;
        }
    }

    public class StoreProfit {
        private final int storeId;
        private final String storeName;
        private final double totalProfit;

        // Constructor
        public StoreProfit(int storeId, String storeName, double totalProfit) {
            this.storeId = storeId;
            this.storeName = storeName;
            this.totalProfit = totalProfit;
        }

        // Getters
        public int getStoreId() {
            return storeId;
        }
        public String getStoreName() {
            return storeName;
        }
        public double getTotalProfit() {
            return totalProfit;
        }
    }

    public class UserByCity {
        private final String cityName;
        private final int userCount;

        // Constructor
        public UserByCity(String cityName, int userCount) {
            this.cityName = cityName;
            this.userCount = userCount;
        }

        // Getters
        public String getCityName() {
            return cityName;
        }
        public int getUserCount() {
            return userCount;
        }
    }
}
