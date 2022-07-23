package by.teachmeskills.eshop.repositories.impl;

import by.teachmeskills.eshop.entities.User;
import by.teachmeskills.eshop.repositories.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private static String GET_ALL_USERS = "SELECT * FROM users";
    private static String GET_USER_BY_EMAIL_AND_PASS = "SELECT * FROM users WHERE email = ? AND password = ?";

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User create(User entity) {
        return null;
    }

    @Override
    public List<User> read() {
        return jdbcTemplate.query(GET_ALL_USERS, (rs, rowNum) -> User.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .build()
        );
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return jdbcTemplate.queryForObject(GET_USER_BY_EMAIL_AND_PASS, (RowMapper<User>) (rs, rowNum) -> User.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .build(), email, password);
    }
}
