package com.proyekpbo.rentalapi.repositories;

import com.proyekpbo.rentalapi.domain.Kendaraan;
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
public class KendaraanRepositoryImpl implements KendaraanRepository{

    private static final String SQL_FIND_ALL = "SELECT * FROM kendaraan";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM kendaraan WHERE id_kendaraan = ?";
    private static final String SQL_CREATE = "INSERT INTO kendaraan(id_kendaraan, nama_kendaraan, tipe_kendaraan, harga_sewa, jumlah_ketersediaan) " +
            "VALUES(NEXTVAL('kendaraan_seq'), ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE kendaraan SET nama_kendaraan = ?, tipe_kendaraan = ?, harga_sewa = ?, jumlah_ketersediaan = ? " +
            "WHERE id_kendaraan = ?";
    private static final String SQL_DELETE = "DELETE FROM kendaraan WHERE id_kendaraan = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Kendaraan> findAllKendaraan() {
        return jdbcTemplate.query(SQL_FIND_ALL, kendaraanRowMapper);
    }

    @Override
    public Kendaraan findKendaraanById(Integer kendaraanId) throws NotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{kendaraanId}, kendaraanRowMapper);
        } catch (Exception e) {
            throw new NotFoundException("Kendaraan not found");
        }
    }

    @Override
    public Integer create(String namaKendaraan, String tipeKendaraan, Integer hargaSewa, Integer jumlahKetersediaan) throws BadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps =connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, namaKendaraan);
                ps.setString(2, tipeKendaraan);
                ps.setInt(3, hargaSewa);
                ps.setInt(4, jumlahKetersediaan);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID_KENDARAAN");
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public void updateById(Integer kendaraanId, Kendaraan kendaraan) throws BadRequestException {
        try {
            jdbcTemplate.update(
                    SQL_UPDATE,
                    new Object[]{kendaraan.getNamaKendaraan(), kendaraan.getTipeKendaraan(), kendaraan.getHargaSewa(), kendaraan.getJumlahKetersediaan(), kendaraanId}
            );
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }
    }

    @Override
    public void deleteById(Integer kendaraanId) throws NotFoundException {
        try {
            jdbcTemplate.update(SQL_DELETE, new Object[]{kendaraanId});
        } catch (Exception e) {
            throw new NotFoundException("Kendaraan not found");
        }
    }

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
