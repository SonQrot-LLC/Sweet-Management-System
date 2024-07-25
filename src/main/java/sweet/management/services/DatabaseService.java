package sweet.management.services;

import sweet.management.database.QueryResultHandler;

import java.sql.*;

public class DatabaseService {
    private static final String DATABASE_USER = System.getenv("DATABASE_USER");
    private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/SweetManagementSystem", DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.out.println("\nNot Connected to the database! Please try again.\n");
        }
    }
    public <T> T executeQuery(String query, QueryResultHandler<T> resultHandler) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            return resultHandler.handle(resultSet);
        }
    }





}
