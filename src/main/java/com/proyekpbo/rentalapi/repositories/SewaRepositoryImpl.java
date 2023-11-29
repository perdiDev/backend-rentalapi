package com.proyekpbo.rentalapi.repositories;

import com.proyekpbo.rentalapi.domain.Kendaraan;
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
import java.util.List;

@Repository
public class SewaRepositoryImpl implements SewaRepository{

    private static final String SQL_FIND_ALL_SEWA_BY_USER_ID = "SELECT * FROM sewa WHERE id_user = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM sewa WHERE id_sewa = ? AND id_user = ? AND id_kendaraan = ?";
    private static final String SQL_CREATE = "INSERT INTO sewa(id_sewa, id_user, id_kendaraan, tanggal_sewa, lama_sewa, total_harga_sewa) VALUES(NEXTVAL('sewa_seq'), ?, ?, ?, ?, ?)";
    private static final String SQL_GET_KENDARAAN_BY_ID = "SELECT * FROM kendaraan WHERE id_kendaraan = ?";
    private static final String SQL_UPDATE_STATUS = "UPDATE sewa SET status_sewa = ?::status_sewa WHERE id_sewa = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Sewa> findAllSewaByIdUser(Integer userId) {
        return jdbcTemplate.query(SQL_FIND_ALL_SEWA_BY_USER_ID, new Object[]{userId}, sewaRowMapper);
    }

    @Override
    public Sewa findById(Integer sewaId, Integer userId, Integer kendaraanId) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{sewaId, userId, kendaraanId}, sewaRowMapper);
        } catch (Exception e) {
            throw new NotFoundException("Sewa not found");
        }
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
            System.out.println(e);
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public void updateStatusSewaById(Integer sewaId, String statusSewa) throws BadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE_STATUS, new Object[]{statusSewa, sewaId});
        } catch (Exception e) {
            System.out.println(e);
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public Kendaraan findKendaraanById(Integer kendaraanId) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_GET_KENDARAAN_BY_ID, new Object[]{kendaraanId}, kendaraanRowMapper);
        } catch (Exception e) {
            throw new NotFoundException("Kendaraan not found");
        }
    }

    private RowMapper<Sewa> sewaRowMapper = ((rs, rowNum) -> {
        return new Sewa(
                rs.getInt("ID_SEWA"),
                rs.getInt("ID_USER"),
                rs.getInt("ID_KENDARAAN"),
                rs.getLong("TANGGAL_SEWA"),
                rs.getInt("LAMA_SEWA"),
                rs.getInt("TOTAL_HARGA_SEWA"),
                rs.getString("STATUS_SEWA"),
                rs.getInt("DENDA_SEWA")
        );
    });

    private RowMapper<Kendaraan> kendaraanRowMapper = ((rs, rowNum) -> {
        return new Kendaraan(
                rs.getInt("id_kendaraan"),
                rs.getString("nama_kendaraan"),
                rs.getString("tipe_kendaraan"),
                rs.getInt("harga_sewa"),
                rs.getInt("jumlah_ketersediaan")
        );
    });
}
