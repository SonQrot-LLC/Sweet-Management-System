package sweet.management.entities;

import sweet.management.services.DatabaseService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Feedback {
    private int feedbackId;
    private String userEmail;
    private int productId;
    private int rating;
    private String comment;
    private final Timestamp createdAt;

    // Constructor
    public Feedback(int feedbackId, String userEmail, int productId, int rating, String comment, Timestamp createdAt) {
        this.feedbackId = feedbackId;
        this.userEmail = userEmail;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public Feedback(String userEmail, int productId, int rating, String comment) {
        try {
            this.feedbackId = nextId(DatabaseService.getConnection(true));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.userEmail = userEmail;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // Static Methods for Database Operations
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

    public static Feedback getFeedbackById(int feedbackId, Connection conn) throws SQLException {
        String sql = "SELECT *" + " FROM feedback WHERE feedback_id = ?";
        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedbackId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Feedback(
                            rs.getInt("feedback_id"),
                            rs.getString("user_email"),
                            rs.getInt("product_id"),
                            rs.getInt("rating"),
                            rs.getString("comment"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        }
        return null;
    }

    public static boolean updateFeedback(Feedback feedback, Connection conn, int updateType) throws SQLException {
        String sql = null;
        switch (updateType) {
            case 1: // Update user_email
                sql = "UPDATE feedback SET user_email = ? WHERE feedback_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, feedback.getUserEmail());
                    stmt.setInt(2, feedback.getFeedbackId());
                });
            case 2: // Update product_id
                sql = "UPDATE feedback SET product_id = ? WHERE feedback_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setInt(1, feedback.getProductId());
                    stmt.setInt(2, feedback.getFeedbackId());
                });
            case 3: // Update rating
                sql = "UPDATE feedback SET rating = ? WHERE feedback_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setInt(1, feedback.getRating());
                    stmt.setInt(2, feedback.getFeedbackId());
                });
            case 4: // Update comment
                sql = "UPDATE feedback SET comment = ? WHERE feedback_id = ?";
                return DatabaseService.executeUpdate(sql, conn, stmt -> {
                    stmt.setString(1, feedback.getComment());
                    stmt.setInt(2, feedback.getFeedbackId());
                });
            default:
                return false;
        }
    }

    public static boolean deleteFeedback(int feedbackId, Connection conn) throws SQLException {
        String sql = "DELETE FROM feedback WHERE feedback_id = ?";
        return DatabaseService.executeUpdate(sql, conn, stmt -> {
            stmt.setInt(1, feedbackId);
        });
    }

    public static int nextId(Connection conn) throws SQLException {
        String sql = "SELECT COALESCE(MAX(feedback_id), 0) + 1 AS next_id FROM feedback";
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

    public static List<Feedback> getAllFeedbackByStoreId(int storeId, Connection conn) throws SQLException {
        String sql = "SELECT f.* FROM feedback f JOIN products p ON f.product_id = p.product_id WHERE p.store_id = ?";
        List<Feedback> feedbackList = new ArrayList<>();

        if (conn == null) {
            throw new SQLException("No connection");
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, storeId);
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
        return feedbackList;
    }
}
