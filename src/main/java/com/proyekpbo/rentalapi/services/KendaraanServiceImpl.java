package com.proyekpbo.rentalapi.services;

import com.proyekpbo.rentalapi.domain.Kendaraan;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;
import com.proyekpbo.rentalapi.exceptions.NotFoundException;
import com.proyekpbo.rentalapi.repositories.KendaraanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KendaraanServiceImpl implements KendaraanService{

    @Autowired
    KendaraanRepository kendaraanRepository;

    @Override
    public List<Kendaraan> fetchAllKendaraan() {
        return kendaraanRepository.findAllKendaraan();
    }

    @Override
    public Kendaraan fetchKendaraanById(Integer kendaraanId) throws NotFoundException {
        return kendaraanRepository.findKendaraanById(kendaraanId);
    }

    @Override
    public Kendaraan addKendaraan(String namaKendaraan, String tipeKendaraan, Integer hargaSewa, Integer jumlahKetersediaan) throws BadRequestException {
        Integer kendaraanId = kendaraanRepository.create(namaKendaraan, tipeKendaraan, hargaSewa, jumlahKetersediaan);

        return kendaraanRepository.findKendaraanById(kendaraanId);
    }

    @Override
    public void updateKendaraan(Integer kendaraanId, Kendaraan kendaraan) throws BadRequestException {
        kendaraanRepository.updateById(kendaraanId, kendaraan);
    }

    @Override
    public void removeKendaraan(Integer kendaraanId) throws NotFoundException {
        kendaraanRepository.deleteById(kendaraanId);
    }
}
