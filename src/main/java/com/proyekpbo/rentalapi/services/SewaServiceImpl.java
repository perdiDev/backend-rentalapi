package com.proyekpbo.rentalapi.services;

import com.proyekpbo.rentalapi.domain.Kendaraan;
import com.proyekpbo.rentalapi.domain.Sewa;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;
import com.proyekpbo.rentalapi.exceptions.NotFoundException;
import com.proyekpbo.rentalapi.repositories.KendaraanRepository;
import com.proyekpbo.rentalapi.repositories.SewaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SewaServiceImpl implements SewaService {

    @Autowired
    SewaRepository sewaRepository;

    @Override
    public Sewa fetchSewaById(Integer sewaId, Integer userId, Integer kendaraanId) throws NotFoundException {
        return sewaRepository.findById(sewaId, userId, kendaraanId);
    }

    @Override
    public Sewa addSewa(Integer userId, Integer kendaraanId, Long tanggalSewa, Integer lamaSewa) throws BadRequestException {
        Kendaraan kendaraan = sewaRepository.findKendaraanById(kendaraanId);
        Integer totalHargaSewa = lamaSewa * kendaraan.getHargaSewa();

        Integer sewaId = sewaRepository.create(userId, kendaraanId, tanggalSewa, lamaSewa, totalHargaSewa);
        return sewaRepository.findById(sewaId, userId, kendaraanId);
    }
}
