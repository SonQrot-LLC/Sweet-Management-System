package sweet.management.entities;

import sweet.management.services.DatabaseService;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private int messageId;
    private String senderEmail;
    private String receiverEmail;
    private String content;
    private Timestamp createdAt;

    // Constructor
    public Message(int messageId, String senderEmail, String receiverEmail, String content, Timestamp createdAt) {
        this.messageId = messageId;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.content = content;
        this.createdAt = createdAt;
    }
//
//    // Getters and setters
//    public int getMessageId() {
//        return messageId;
//    }
//
//    public void setMessageId(int messageId) {
//        this.messageId = messageId;
//    }
//
//    public String getSenderEmail() {
//        return senderEmail;
//    }
//
//    public void setSenderEmail(String senderEmail) {
//        this.senderEmail = senderEmail;
//    }
//
//    public String getReceiverEmail() {
//        return receiverEmail;
//    }
//
//    public void setReceiverEmail(String receiverEmail) {
//        this.receiverEmail = receiverEmail;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(Timestamp createdAt) {
//        this.createdAt = createdAt;
//    }

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

//    public static Message getMessageById(Connection connection, int messageId) throws SQLException {
//        String sql = "SELECT * FROM messages WHERE message_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, messageId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return new Message(
//                        resultSet.getInt("message_id"),
//                        resultSet.getString("sender_email"),
//                        resultSet.getString("receiver_email"),
//                        resultSet.getString("content"),
//                        resultSet.getTimestamp("created_at")
//                );
//            }
//        }
//        return null;
//    }

    public static List<Message> getMessagesBySenderEmail(Connection connection, String senderEmail) throws SQLException {
        String sql = "SELECT * FROM messages WHERE sender_email = ?";
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, senderEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(
                        resultSet.getInt("message_id"),
                        resultSet.getString("sender_email"),
                        resultSet.getString("receiver_email"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at")
                ));
            }
        }
//        for (Message message : messages) {
//            System.out.print(message.content);
//            System.out.print(" "+message.senderEmail);
//            System.out.print(" "+message.receiverEmail);
//            System.out.println(" "+message.createdAt);}
        return messages;
    }

    public static List<Message> getMessagesByReceiverEmail(Connection connection, String receiverEmail) throws SQLException {
        String sql = "SELECT * "+" FROM messages WHERE receiver_email = ?";
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, receiverEmail);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(
                        resultSet.getInt("message_id"),
                        resultSet.getString("sender_email"),
                        resultSet.getString("receiver_email"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at")
                ));
            }
        }
//            for (Message message : messages) {
//            System.out.print(message.content);
//            System.out.print(" "+message.senderEmail);
//            System.out.print(" "+message.receiverEmail);
//            System.out.println(" "+message.createdAt);}
        return messages;
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
                        resultSet.getInt("message_id"),
                        resultSet.getString("sender_email"),
                        resultSet.getString("receiver_email"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at")
                ));
            }
        }
//            for (Message message : messages) {
//            System.out.print(message.content);
//            System.out.print(" "+message.senderEmail);
//            System.out.print(" "+message.receiverEmail);
//            System.out.println(" "+message.createdAt);}
        return messages;
    }


//
//    public static void deleteMessageById(Connection connection, int messageId) throws SQLException {
//        String sql = "DELETE FROM messages WHERE message_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, messageId);
//            statement.executeUpdate();
//        }
//    }

//    public static void updateMessageContent(Connection connection, int messageId, String newContent) throws SQLException {
//        String sql = "UPDATE messages SET content = ? WHERE message_id = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, newContent);
//            statement.setInt(2, messageId);
//            statement.executeUpdate();
//        }
//    }

    // Example usage
//    public static void main(String[] args) {
//        String jdbcUrl = "jdbc:mysql://localhost:3306/yourdatabase";
//        String jdbcUser = "yourusername";
//        String jdbcPassword = "yourpassword";
//
//        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
//            // Insert a new message
//            insertMessage(connection, "sender@example.com", "receiver@example.com", "Hello!");
//
//            // Retrieve a message by ID
//            Message message = getMessageById(connection, 1);
//            System.out.println("Message content: " + message.getContent());
//
//            // Update a message
//            updateMessageContent(connection, 1, "Updated content");
//
//            // Retrieve messages by sender email
//            List<Message> sentMessages = getMessagesBySenderEmail(connection, "sender@example.com");
//            for (Message msg : sentMessages) {
//                System.out.println("Sent message content: " + msg.getContent());
//            }
//
//            // Delete a message
//            deleteMessageById(connection, 1);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
