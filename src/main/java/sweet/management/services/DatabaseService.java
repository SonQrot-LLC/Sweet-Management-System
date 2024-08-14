package sweet.management.services;


import java.sql.*;

public class DatabaseService {
    private static final String DATABASE_USER = System.getenv("DATABASE_USER");
    private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");
    private static Connection connection;
    public static boolean test;

    static {

    }


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
//        try {


            // Attempt to establish a connection
            try {
                if (!connect) {
                    return null;
                }
                else if (!test || connection.isClosed()) {
                    String url = "jdbc:mysql://sweet-managment-system-db-momanani2017-feec.l.aivencloud.com:16046/SweetManagementSystem?useSSL=true&requireSSL=true";
                    connection = DriverManager.getConnection(url, DATABASE_USER, DATABASE_PASSWORD);
                    test = true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



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
