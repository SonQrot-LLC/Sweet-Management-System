package sweet.management.services;

import sweet.management.database.QueryResultHandler;

import java.sql.*;

public class DatabaseService {
    private static final String DATABASE_USER = System.getenv("DATABASE_USER");
    private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");
    private static   Connection connection;


//    public <T> T executeQuery(String query, QueryResultHandler<T> resultHandler) throws SQLException {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
//             ResultSet resultSet = preparedStatement.executeQuery()) {
//            return resultHandler.handle(resultSet);
//        }
//    }

    public static Connection getConnection(boolean connect) {

            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SweetManagementSystem", DATABASE_USER, DATABASE_PASSWORD);
                if (!connect)
                    throw new SQLException("Failed to connect to database");
                return connection;
            } catch (SQLException e) {
            return null;
            }
    }
}
