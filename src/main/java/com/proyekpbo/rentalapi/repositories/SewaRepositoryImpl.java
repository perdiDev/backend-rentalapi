package com.proyekpbo.rentalapi.repositories;

import com.proyekpbo.rentalapi.domain.Sewa;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;
import com.proyekpbo.rentalapi.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class SewaRepositoryImpl implements SewaRepository{

    private static final String SQL_FIND_BY_ID = "SELECT * FROM sewa WHERE id_sewa = ? AND id_user = ? AND id_kendaraan = ?";
    private static final String SQL_CREATE = "INSERT INTO sewa(id_sewa, id_user, id_kendaraan, tanggal_sewa, lama_sewa, total_harga_sewa) VALUES(NEXTVAL('sewa_seq'), ?, ?, ?, ?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Sewa findById(Integer sewaId, Integer kendaraanId, Integer userId) throws NotFoundException {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{sewaId, userId, kendaraanId}, sewaRowMapper);
    }

    @Override
    public Integer create(Integer userId, Integer kendaraanId, Long tanggalSewa, Integer lamaSewa, Integer totalHargaSewa) throws BadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, userId);
                ps.setInt(2, kendaraanId);
                ps.setLong(3, tanggalSewa);
                ps.setInt(4, lamaSewa);
                ps.setInt(5, totalHargaSewa);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID_SEWA");
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    private RowMapper<Sewa> sewaRowMapper = ((rs, rowNum) -> {
        return new Sewa(
                rs.getInt("ID_SEWA"),
                rs.getInt("ID_USER"),
                rs.getInt("ID_KENDARAAN"),
                rs.getLong("TANGGAL_SEWA"),
                rs.getInt("LAMA_SEWA"),
                rs.getInt("TOTAL_HARGA_SEWA")
        );
    });
}
