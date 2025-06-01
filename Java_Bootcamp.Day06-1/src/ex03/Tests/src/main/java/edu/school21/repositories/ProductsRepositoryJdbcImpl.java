package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private final DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Statement getStatement() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection.createStatement();
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Product";
        ResultSet resultSet = getStatement().executeQuery(query);
        while (resultSet.next()) {
            products.add(Product.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .price(resultSet.getInt(3))
                    .build());
        }
        return products;
    }


    @Override
    public Optional<Product> findById(Long id) throws SQLException {
        String query = "SELECT * FROM Product WHERE id=" + id;
        ResultSet resultSet = getStatement().executeQuery(query);
        if (!resultSet.next()) {
            return Optional.empty();
        }
        return Optional.of(Product.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .price(resultSet.getInt(3))
                .build());
    }

    @Override
    public void update(Product product) throws SQLException {
        String query = "UPDATE Product SET name='" + product.getName() + "',"
                + "price=" + product.getPrice() + " WHERE id=" + product.getId();
        getStatement().executeQuery(query);
    }

    @Override
    public void save(Product product) throws SQLException {
        String query = "INSERT INTO Product VALUES (" +
                product.getId() + ", '" +
                product.getName() + "', " +
                product.getPrice() + ");";

                getStatement().executeQuery(query);
    }

    @Override
    public void delete(Long id) throws SQLException {
        String query ="DELETE FROM Product WHERE id = " + id;
        getStatement().executeUpdate(query);
    }
}
