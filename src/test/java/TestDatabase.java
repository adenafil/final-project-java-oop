import ade.myanimelist.anime.database.ConnectionDatabase;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabase {
    @Test
    void testConnection() throws SQLException {
        Connection connection = ConnectionDatabase.getDataSource().getConnection();
    }
}
