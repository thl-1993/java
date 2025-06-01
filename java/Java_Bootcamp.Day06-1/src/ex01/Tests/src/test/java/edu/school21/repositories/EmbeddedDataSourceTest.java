package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class EmbeddedDataSourceTest {
    private DataSource dataSource;

    @BeforeEach
    public void init(){
        dataSource = new EmbeddedDatabaseBuilder()
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
    }
    @Test
    public void testGetConnection() throws SQLException{
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
    }
}
