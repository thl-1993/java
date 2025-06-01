package edu.school21.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import edu.school21.models.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductsRepositoryJdbcImplTest {
    private DataSource dataSource;
    ProductsRepository productsRepository;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1, "prod1", Integer.valueOf(11)),
            new Product(2, "prod2", Integer.valueOf(12)),
            new Product(3, "prod3", Integer.valueOf(13)),
            new Product(4, "prod4", Integer.valueOf(14)),
            new Product(5, "prod5", Integer.valueOf(15)),
            new Product(6, "prod6", Integer.valueOf(16))
    );
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1, "prod1", Integer.valueOf(11));
    final Product EXPECTED_UPDATED_PRODUCT = new Product(4, "nothing", 10);
    final Product EXPECTED_SAVED_PRODUCT = new Product(7, "prod7", 99999);


    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    void testProductsRepositoryJdbcImplFindAll() throws SQLException {
        List<Product> productsList = productsRepository.findAll();
        assertEquals(productsList, EXPECTED_FIND_ALL_PRODUCTS);
    }

    @Test
    void findByIdTest() throws SQLException {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, productsRepository.findById(1L).get());
    }

    @Test
    void updateTest() throws SQLException {
        productsRepository.update(new Product(4, "nothing", 10));
        assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(4L).get());
    }

    @Test
    void saveTest() throws SQLException {
        productsRepository.save(new Product(7, "prod7", 99999));
        assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById(7L).get());
    }

    @Test
    void deleteTest() throws SQLException {
        productsRepository.delete(3L);
        assertEquals(Optional.empty(), productsRepository.findById(3L));
    }

}
