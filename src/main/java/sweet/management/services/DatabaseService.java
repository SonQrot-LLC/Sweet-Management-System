package sweet.management.services;


import java.sql.*;

public class DatabaseService {
    private static final String DATABASE_USER = System.getenv("DATABASE_USER");
    private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");
    private static Connection connection;
    private static boolean test = false;

    public static Connection getConnection(boolean connect) {
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
