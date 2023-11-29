package com.proyekpbo.rentalapi.services;

import com.proyekpbo.rentalapi.domain.Sewa;
import com.proyekpbo.rentalapi.exceptions.BadRequestException;

import java.util.List;

public interface SewaService {

    public List<Sewa> fetchAllSewaByIdUser(Integer userId);
    Sewa fetchSewaById(Integer sewaId, Integer userId, Integer kendaraanId) throws NoClassDefFoundError;
    Sewa addSewa(Integer userId, Integer kendaraanId, Long tanggalSewa, Integer lamaSewa) throws BadRequestException;
    public void updateStatusSewa(Integer sewaId, String statusSewa) throws BadRequestException;
}
