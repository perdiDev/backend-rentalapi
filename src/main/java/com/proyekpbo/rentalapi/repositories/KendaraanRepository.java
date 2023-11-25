package com.proyekpbo.rentalapi.repositories;

import com.proyekpbo.rentalapi.domain.Kendaraan;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;
import com.proyekpbo.rentalapi.exceptions.NotFoundException;

import java.util.List;

public interface KendaraanRepository {

    List<Kendaraan> findAllKendaraan();
    Kendaraan findKendaraanById(Integer kendaraanId) throws NotFoundException;
    Integer create(String namaKendaraan, String tipeKendaraan, Integer hargaSewa, Integer jumlahKetersediaan) throws BadRequestException;
    void updateById(Integer kendaraanId, Kendaraan kendaraan) throws BadRequestException;
    void deleteById(Integer kendaraanId) throws NotFoundException;
}
