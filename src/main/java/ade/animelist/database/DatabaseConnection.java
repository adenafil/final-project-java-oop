package ade.animelist.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Class untuk konek ke database
 * berisi konfigurasi hikaruConfig dan DbSQLite
 */
public class DatabaseConnection {
    // Membuat variabel HikariDataSource
    private static HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:sqlite:src/main/resources/_database/_db.db");

        // config of pool
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {return  dataSource;}
}
