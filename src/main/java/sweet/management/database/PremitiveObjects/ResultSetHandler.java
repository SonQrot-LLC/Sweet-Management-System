package sweet.management.database.PremitiveObjects;
import sweet.management.database.QueryResultHandler;

import java.sql.ResultSet;


public class ResultSetHandler implements QueryResultHandler<ResultSet> {
    @Override
    public ResultSet handle(ResultSet resultSet) {
        return resultSet;
    }
}