package sweet.management.services;


import java.sql.*;

public class DatabaseService {
    private static final String DATABASE_USER = System.getenv("DATABASE_USER");
    private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");
    private static Connection connection;


//    public <T> T executeQuery(String query, QueryResultHandler<T> resultHandler) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            return resultHandler.handle(resultSet);
//        }
//    }

//    public static Connection getConnection(boolean connect) {
//
//        try {
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SweetManagementSystem", DATABASE_USER, DATABASE_PASSWORD);
//            if (!connect)
//                throw new SQLException("Failed to connect to database");
//            return connection;
//        } catch (SQLException e) {
//            return null;
//        }
//    }

    public static Connection getConnection(boolean connect) {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://sweet-managment-system-db-momanani2017-feec.l.aivencloud.com:16046/SweetManagementSystem?useSSL=true&requireSSL=true";
            String user = "avnadmin";
            String password = "AVNS_V48PsTcnluVA-KIvExk";

            // Attempt to establish a connection
            connection = DriverManager.getConnection(url, user, password);

            // Check if connection should be verified (if connect is true)
            if (!connect) {
                // Simulate a failure condition for demonstration purposes
                throw new SQLException("Connection verification failed.");
            }

        } catch (SQLException e) {
            // Print the stack trace for debugging
            e.printStackTrace();
        }

        // Return the connection (or null if there was an error)
        return connection;
    }


    @FunctionalInterface
    public interface SQLConsumer<T> {
        void accept(T t) throws SQLException;
    }

    public static boolean executeUpdate(String sql, Connection conn, SQLConsumer<PreparedStatement> consumer) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            consumer.accept(stmt);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }
}
