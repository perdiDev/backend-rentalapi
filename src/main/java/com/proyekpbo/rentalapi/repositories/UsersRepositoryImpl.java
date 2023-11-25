package com.proyekpbo.rentalapi.repositories;

import com.proyekpbo.rentalapi.domain.User;
import com.proyekpbo.rentalapi.exceptions.AuthException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpStatusCodeException;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UsersRepositoryImpl implements UsersRepository{

    private static final String SQL_FIND_BY_EMAIL = "SELECT COUNT(*) FROM users WHERE email = ?";
    private static final String SQL_CREATE = "INSERT INTO users(id_user, nama, alamat, email, password) " +
            "VALUES(NEXTVAL('users_seq'), ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT id_user, status, nama, alamat, email, password " +
            "FROM users WHERE id_user = ?";
    private static final String SQL_FIND_BY_EMAIL_PASSWORD = "SELECT * FROM users WHERE email = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer findByEmail(String email) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, Integer.class);
    }

    @Override
    public User findById(Integer userId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

    @Override
    public Integer create(String nama, String alamat, String email, String password) throws AuthException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, nama);
                ps.setString(2, alamat);
                ps.setString(3, email);
                ps.setString(4, hashedPassword);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("id_user");
        } catch (Exception e) {
            throw new AuthException("Invalid detail, failed to create new user");
        }
    }

    @Override
    public User findBYEmailPassword(String email, String password) throws AuthException {
        try {
            User user = jdbcTemplate.queryForObject(
                    SQL_FIND_BY_EMAIL_PASSWORD,
                    new Object[]{email},
                    userRowMapper
            );
            if(!BCrypt.checkpw(password, user.getPassword()))
                throw new AuthException("Invalid email/password");
            return user;
        } catch (Exception e) {
            throw new AuthException("Invalid email/password");
        }
    }

    private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
        return new User(
                rs.getInt("id_user"),
                rs.getString("status"),
                rs.getString("nama"),
                rs.getString("alamat"),
                rs.getString("email"),
                rs.getString("password")
        );
    });
}
