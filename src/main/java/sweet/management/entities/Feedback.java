package sweet.management.entities;

import sweet.management.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Feedback {
    public static final String QUERY_BY_EMAIL = "email";
    public static final String QUERY_BY_STORE = "store";
    public static final String QUERY_BY_PRODUCT = "product";

    private final int feedbackId;
    private final String userEmail;
    private final int productId;
    private final int rating;
    private final String comment;
    private final Timestamp createdAt;


    public Feedback(int feedbackId, String userEmail, int productId, int rating, String comment, Timestamp createdAt) {
        this.feedbackId = feedbackId;
        this.userEmail = userEmail;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Feedback(String userEmail, int productId, int rating, String comment,Connection connection) throws SQLException {
        this.feedbackId = nextId(connection);
        this.userEmail = userEmail;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getProductId() {
        return productId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public static boolean createFeedback(Feedback feedback, Connection conn) throws SQLException {
        String sql = "INSERT INTO feedback (feedback_id, user_email, product_id, rating, comment, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, feedback.getFeedbackId());
            stmt.setString(2, feedback.getUserEmail());
            stmt.setInt(3, feedback.getProductId());
            stmt.setInt(4, feedback.getRating());
            stmt.setString(5, feedback.getComment());
            stmt.setTimestamp(6, feedback.getCreatedAt());
        });
    }

    public static boolean deleteFeedback(int feedbackId, Connection conn) throws SQLException {
        String sql = "DELETE FROM feedback WHERE feedback_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> stmt.setInt(1, feedbackId));
    }

    public static List<Feedback> getFeedback(String queryType, String queryValue, Connection conn) throws SQLException {
        String sql;
        if (!queryType.equals(QUERY_BY_EMAIL)) {
            if (queryType.equals(QUERY_BY_STORE)) {
                sql = "SELECT f.* FROM feedback f JOIN products p ON f.product_id = p.product_id WHERE p.store_id = ?";
            } else if (queryType.equals(QUERY_BY_PRODUCT)) {
                sql = "SELECT * FROM feedback WHERE product_id = ?";
            } else {
                throw new IllegalArgumentException("Invalid query type: " + queryType);
            }
        } else {
            sql = "SELECT * FROM feedback WHERE user_email = ?";
        }

        List<Feedback> feedbackList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, queryValue);
            feedbackGetResults(feedbackList, stmt);
        }
        return feedbackList;
    }

    private static void feedbackGetResults(List<Feedback> feedbackList, PreparedStatement stmt) throws SQLException {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Feedback feedback = new Feedback(
                        rs.getInt("feedback_id"),
                        rs.getString("user_email"),
                        rs.getInt("product_id"),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_at")
                );
                feedbackList.add(feedback);
            }
        }
    }

    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(feedback_id), 0) + 1 AS next_id FROM feedback";
        if (conn == null) throw new SQLException("No connection");
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("next_id");
                }
            }
        }
        return 1; // Default to 1 if something goes wrong
    }

    public static List<Feedback> getAllFeedbacks(Connection conn) throws SQLException {
        String sql = "SELECT * " + " FROM feedback";
        List<Feedback> feedbackList = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            feedbackGetResults(feedbackList, stmt);
        }
        return feedbackList;
    }

}
