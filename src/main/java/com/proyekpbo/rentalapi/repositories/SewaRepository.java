package com.proyekpbo.rentalapi.repositories;

import com.proyekpbo.rentalapi.domain.Kendaraan;
import com.proyekpbo.rentalapi.domain.Sewa;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;
import com.proyekpbo.rentalapi.exceptions.NotFoundException;

import java.util.List;

public interface SewaRepository {

    List<Sewa> findAllSewaByIdUser(Integer userId);
    Sewa findById(Integer sewaId, Integer kendaraanId, Integer userId) throws NotFoundException;
    Integer create(Integer userId, Integer kendaraanId, Long tanggalSewa, Integer lamaSewa, Integer totalHargaSewa) throws BadRequestException;
    Kendaraan findKendaraanById(Integer kendaraanId) throws NotFoundException;
    void updateStatusSewaById(Integer sewaId, String statusSewa, Integer dendaSewa) throws BadRequestException;
    void updateKetersediaanKendaraan(Integer kendaraanId, Integer jumlahKendaraan) throws BadRequestException;
}
