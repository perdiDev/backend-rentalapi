package com.proyekpbo.rentalapi.services;

import com.proyekpbo.rentalapi.domain.Sewa;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;

public interface SewaService {

    Sewa fetchSewaById(Integer sewaId, Integer userId, Integer kendaraanId) throws NoClassDefFoundError;
    Sewa addSewa(Integer userId, Integer kendaraanId, Long tanggalSewa, Integer lamaSewa, Integer totalHargaSewa) throws BadRequestException;
}
