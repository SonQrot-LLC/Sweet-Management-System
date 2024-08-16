package sweet.management.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Notification {
    public static final String NOTIFICATION_ID = "notification_id";
    public static final String USER_EMAIL = "user_email";
    public static final String MESSAGE_COLUMN = "message";
    public static final String IS_READ = "is_read";
    public static final String CREATED_AT = "created_at";

    private final int notificationId;
    private final String userEmail;
    private final String message;
    private final boolean isRead;
    private final Timestamp createdAt;

    public Notification(int notificationId, String userEmail, String message, boolean isRead, Timestamp createdAt) {
        this.notificationId = notificationId;
        this.userEmail = userEmail;
        this.message = message;
        this.isRead = isRead;
        this.createdAt = createdAt;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }


    public static void insertNotification(Connection connection, String userEmail, String senderEmail, String message) throws SQLException {
        String sql = "INSERT INTO notifications (user_email, sender_email, message, is_read) VALUES (?, ?, ?, 0)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userEmail);
            statement.setString(2, senderEmail);
            statement.setString(3, message);
            statement.executeUpdate();
        }
    }


    // Get a notification by its ID
    public static Notification getNotificationById(Connection connection, int notificationId) throws SQLException {
        String sql = "SELECT * "+" FROM notifications WHERE notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Notification(
                        resultSet.getInt(NOTIFICATION_ID),
                        resultSet.getString(USER_EMAIL),
                        resultSet.getString(MESSAGE_COLUMN),
                        resultSet.getBoolean(IS_READ),
                        resultSet.getTimestamp(CREATED_AT)
                );
            }
        }
        return null;
    }

    // Get all notifications for a specific user by their email
    public static List<Notification> getNotificationsByUserEmail(Connection connection, String userEmail) throws SQLException {
        String sql = "SELECT * "+" FROM notifications WHERE user_email = ?";
        List<Notification> notifications = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                notifications.add(new Notification(
                        resultSet.getInt(NOTIFICATION_ID),
                        resultSet.getString(USER_EMAIL),
                        resultSet.getString(MESSAGE_COLUMN),
                        resultSet.getBoolean(IS_READ),
                        resultSet.getTimestamp(CREATED_AT)
                ));
            }
        }
        return notifications;
    }

    // Mark a notification as read by its ID
    public static void markNotificationAsRead(Connection connection, int notificationId) throws SQLException {
        String sql = "UPDATE notifications SET is_read = 1 WHERE notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            statement.executeUpdate();
        }
    }

    public static void deleteNotificationById(Connection connection, int notificationId) throws SQLException {
        String sql = "DELETE FROM notifications WHERE notification_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, notificationId);
            statement.executeUpdate();
        }
    }
}
