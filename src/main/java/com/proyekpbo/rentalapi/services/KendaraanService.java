package com.proyekpbo.rentalapi.services;

import com.proyekpbo.rentalapi.domain.Kendaraan;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;
import com.proyekpbo.rentalapi.exceptions.NotFoundException;

import java.util.List;

public interface KendaraanService {

    List<Kendaraan> fetchAllKendaraan();
    Kendaraan fetchKendaraanById(Integer kendaraanId) throws NotFoundException;
    Kendaraan addKendaraan(String namaKendaraan, String tipeKendaraan, Integer hargaSewa, Integer jumlahKetersediaan) throws BadRequestException;
    void updateKendaraan(Integer kendaraanId, Kendaraan kendaraan) throws BadRequestException;
    void removeKendaraan(Integer kendaraanId) throws NotFoundException;
}
