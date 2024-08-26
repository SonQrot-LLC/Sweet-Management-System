package sweet.management.entities;

import javax.mail.*;
import javax.mail.internet.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class Notification {
    public static final String NOTIFICATION_ID = "notification_id";
    public static final String USER_EMAIL = "user_email";
    public static final String MESSAGE_COLUMN = "message";
    public static final String IS_READ = "is_read";
    public static final String CREATED_AT = "created_at";
    private static final String GMAIL_PASSWORD = System.getenv("GMAIL_PASSWORD");
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


    public static void insertNotification(Connection connection, String userEmail, String senderEmail, String message) throws SQLException, MessagingException {
        String sql = "INSERT INTO notifications (user_email, sender_email, message, is_read) VALUES (?, ?, ?, 0)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userEmail);
            statement.setString(2, senderEmail);
            statement.setString(3, message);
            statement.executeUpdate();

            sendEmail(userEmail, senderEmail, message);
        }
    }

    private static void sendEmail(String recipientEmail, String senderEmail, String messageContent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String username = "toostronkm@gmail.com";



        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, GMAIL_PASSWORD);
            }
        });

        javax.mail.Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Special Request Notification from " + senderEmail);
        message.setText("You have a new special request from " + senderEmail + ":\n\n" + messageContent);

        Transport.send(message);
    }


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

    public static int getUnreadNotificationCount(Connection connection, String userEmail) throws SQLException {
        String sql = "SELECT COUNT(*) FROM notifications WHERE user_email = ? AND is_read = 0";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userEmail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

}
