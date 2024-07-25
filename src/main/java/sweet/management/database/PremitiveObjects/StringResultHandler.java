package sweet.management.database.PremitiveObjects;
import sweet.management.database.QueryResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringResultHandler implements QueryResultHandler<String> {
    @Override
    public String handle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }
}