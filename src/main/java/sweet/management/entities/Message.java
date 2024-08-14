package sweet.management.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Message {
    public static final String MESSAGE_ID = "message_id";
    public static final String SENDER_EMAIL = "sender_email";
    public static final String RECEIVER_EMAIL = "receiver_email";
    public static final String CONTENT_COLUMN = "content";
    public static final String CREATED_AT = "created_at";
    private final int messageId;
    private final String senderEmail;
    private final String receiverEmail;
    private final String content;
    private final Timestamp createdAt;

    // Constructor
    public Message(int messageId, String senderEmail, String receiverEmail, String content, Timestamp createdAt) {
        this.messageId = messageId;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.content = content;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getMessageId() {
        return messageId;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // Database operations
    public static void insertMessage(Connection connection, String senderEmail, String receiverEmail, String content) throws SQLException {
        String sql = "INSERT INTO messages (sender_email, receiver_email, content) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, senderEmail);
            statement.setString(2, receiverEmail);
            statement.setString(3, content);
            statement.executeUpdate();
        }
    }

    public static Message getMessageById(Connection connection, int messageId) throws SQLException {
        String sql = "SELECT" + " * FROM messages WHERE message_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, messageId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Message(
                        resultSet.getInt(MESSAGE_ID),
                        resultSet.getString(SENDER_EMAIL),
                        resultSet.getString(RECEIVER_EMAIL),
                        resultSet.getString(CONTENT_COLUMN),
                        resultSet.getTimestamp(CREATED_AT)
                );
            }
        }
        return null;
    }

    public static List<Message> getMessagesBySenderEmail(Connection connection, String senderEmail) throws SQLException {
        String sql = "SELECT" + " * FROM messages WHERE sender_email = ?";
        return getMessagesList(connection, senderEmail, sql);
    }

    private static List<Message> getMessagesList(Connection connection, String senderEmail, String sql) throws SQLException {
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, senderEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(
                        resultSet.getInt(MESSAGE_ID),
                        resultSet.getString(SENDER_EMAIL),
                        resultSet.getString(RECEIVER_EMAIL),
                        resultSet.getString(CONTENT_COLUMN),
                        resultSet.getTimestamp(CREATED_AT)
                ));
            }
        }
        return messages;
    }

    public static List<Message> getMessagesByReceiverEmail(Connection connection, String receiverEmail) throws SQLException {
        String sql = "SELECT * " + " FROM messages WHERE receiver_email = ?";
        return getMessagesList(connection, receiverEmail, sql);
    }

    public static List<Message> getMessagesBetweenUsers(Connection connection, String senderEmail, String receiverEmail) throws SQLException {
        String sql = "SELECT *" + " FROM messages WHERE sender_email = ? AND receiver_email = ?";
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, senderEmail);
            statement.setString(2, receiverEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(
                        resultSet.getInt(MESSAGE_ID),
                        resultSet.getString(SENDER_EMAIL),
                        resultSet.getString(RECEIVER_EMAIL),
                        resultSet.getString(CONTENT_COLUMN),
                        resultSet.getTimestamp(CREATED_AT)
                ));
            }
        }
        return messages;
    }

}
