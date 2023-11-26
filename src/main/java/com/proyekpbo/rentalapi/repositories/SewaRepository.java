package com.proyekpbo.rentalapi.repositories;

import com.proyekpbo.rentalapi.domain.Sewa;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;
import com.proyekpbo.rentalapi.exceptions.NotFoundException;

public interface SewaRepository {

    Sewa findById(Integer sewaId, Integer kendaraanId, Integer userId) throws NotFoundException;
    Integer create(Integer userId, Integer kendaraanId, Long tanggalSewa, Integer lamaSewa, Integer totalHargaSewa) throws BadRequestException;
}
