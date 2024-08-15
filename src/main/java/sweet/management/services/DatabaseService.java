package sweet.management.services;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseService {
    private static final String DATABASE_USER = System.getenv("DATABASE_USER");
    private static final String DATABASE_PASSWORD = System.getenv("DATABASE_PASSWORD");
    private static Connection connection;
    private static boolean test = false;
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static Connection getConnection(boolean connect) throws SQLException{
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
                throw new SQLException(e);
            }
        return connection;

    }
    public static int getIdValueFromDataBase(Connection conn, String sql) throws SQLException {
        if (conn == null) {
            throw new SQLException("NO_CONNECTION");
        }
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "sweetmanagementsystem");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
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
