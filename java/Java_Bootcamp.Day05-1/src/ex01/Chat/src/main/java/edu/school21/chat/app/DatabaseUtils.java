package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.BufferedReader;

public class DatabaseUtils {

    public static DataSource getConnection(Properties properties) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://" + properties.getProperty("db.url"));
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setValidationTimeout(300_000);
        return new HikariDataSource(hikariConfig);
    }

    public static DataSource getDataSource() {
        String propertiesFilePath = "/dbConnection.properties";
        InputStream propertiesStream = DatabaseUtils.class.getResourceAsStream(propertiesFilePath);

        if (propertiesStream == null) {
            System.err.println("Properties file not found: " + propertiesFilePath);
            return null;
        }

        Properties properties = new Properties();
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            System.err.println("Failed to load properties file: " + e.getMessage());
            return null;
        }

        DataSource dataSource = getConnection(properties);
        if (!initDatabase(dataSource)) {
            System.err.println("Failed to initialize database.");
            return null;
        }

        return dataSource;
    }

    public static boolean initDatabase(DataSource dataSource) {
        String schemaFilePath = "/schema.sql";
        String dataFilePath = "/data.sql";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String createTablesQuery = readQueryFromFile(schemaFilePath);
            String fillTablesQuery = readQueryFromFile(dataFilePath);

            if (createTablesQuery == null || fillTablesQuery == null) {
                System.err.println("Failed to read schema or data file.");
                return false;
            }

            statement.executeUpdate(createTablesQuery);
            statement.executeUpdate(fillTablesQuery);

            return true;
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
            return false;
        }
    }

    private static String readQueryFromFile(String filePath) {
        InputStream inputStream = DatabaseUtils.class.getResourceAsStream(filePath);

        if (inputStream == null) {
            System.err.println("Resource not found: " + filePath);
            return null;
        }

        StringBuilder query = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                query.append(line).append(System.lineSeparator());
            }

            String result = query.toString().trim();
            return result.isEmpty() ? null : result;

        } catch (IOException e) {
            System.err.println("Failed to read query file: " + e.getMessage());
            return null;
        }
    }
}