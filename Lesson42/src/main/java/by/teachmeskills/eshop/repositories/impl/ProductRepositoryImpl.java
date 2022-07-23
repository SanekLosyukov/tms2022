package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.Product;
import by.teachmeskills.eshop.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private static String GET_ALL_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM products, images WHERE products.category_id = ? AND images.product_id = products.id AND images.primary_flag = 1";
    private static String GET_PRODUCT_BY_ID = "SELECT * FROM products, images WHERE products.id = ? AND images.product_id = products.id AND images.primary_flag = 1";
    private static final String SEARCH_BY_NAME_OR_DESCRIPTION = "SELECT * FROM products WHERE products.name LIKE ? OR products.description LIKE ?";

    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product create(Product entity) {
        return null;
    }

    @Override
    public List<Product> read() {
        return null;
    }

    @Override
    public Product update(Product entity) {
        return null;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public Product findById(int id) throws Exception {
        log.info(String.format("Method UserServiceImpl#findById(%s) is called", id));

        return jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID, (RowMapper<Product>) (rs, rowNum) -> Product.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getInt("price"))
                .imagePath(rs.getString("image"))
                .categoryId(rs.getInt("category_id"))
                .build(), id);
    }

    @Override
    public List<Product> findByAllCategoryId(int categoryId) throws Exception {
        return jdbcTemplate.query(GET_ALL_PRODUCTS_BY_CATEGORY_ID, (rs, rowNum) -> Product.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getInt("price"))
                .imagePath(rs.getString("image"))
                .build(), categoryId);
    }

    @Override
    public List<Product> getSearchFromDB(String search) {
        String setSearch = "%" + search + "%";
        return jdbcTemplate.query(SEARCH_BY_NAME_OR_DESCRIPTION, (RowMapper<Product>) (rs, rowNum) -> Product.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .price(rs.getInt("price"))
                .categoryId(rs.getInt("category_id"))
                .build(), setSearch, setSearch);
    }
}
