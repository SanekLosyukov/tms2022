package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.Image;
import by.teachmeskills.eshop.repositories.ImageRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepositoryImpl implements ImageRepository {
    private static final String GET_ALL_SECONDARY_IMAGES = "SELECT * FROM products, images WHERE images.product_id = ? AND images.primary_flag = 0";
    private final JdbcTemplate jdbcTemplate;

    public ImageRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Image create(Image entity) {
        return null;
    }

    @Override
    public List read() {
        return null;
    }

    @Override
    public Image update(Image entity) {
        return null;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public List<Image> getAllSecondaryImages(int id) {
        return jdbcTemplate.query(GET_ALL_SECONDARY_IMAGES, (rs, rowNum) -> Image.builder()
                .id(rs.getInt("id"))
                .productId(rs.getInt("product_id"))
                .flag(rs.getBoolean("primary_flag"))
                .secondaryImageName(rs.getString("image"))
                .build(), id);
    }
}
