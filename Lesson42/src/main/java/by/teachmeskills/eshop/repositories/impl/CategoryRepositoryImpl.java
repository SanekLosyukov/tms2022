package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.Category;
import by.teachmeskills.eshop.repositories.CategoryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    private static String GET_ALL_CATEGORIES = "SELECT * FROM categories, images WHERE images.category_id = categories.id";
    private static String GET_CATEGORY_BY_ID = "SELECT * FROM categories, images WHERE categories.id = ? AND images.category_id = categories.id";

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Category create(Category entity) {
        return null;
    }

    @Override
    public List<Category> read() throws Exception {
        return jdbcTemplate.query(GET_ALL_CATEGORIES, (rs, rowNum) -> Category.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .imagePath(rs.getString("image"))
                .build()
        );
    }

    @Override
    public Category update(Category entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Category findById(int id) {
        return jdbcTemplate.queryForObject(GET_CATEGORY_BY_ID, (RowMapper<Category>) (rs, rowNum) -> Category.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .imagePath(rs.getString("image"))
                .build(), id);
    }
}
