package com.proyekpbo.rentalapi.resources;

import com.proyekpbo.rentalapi.domain.Kendaraan;
import com.proyekpbo.rentalapi.domain.Sewa;
import com.proyekpbo.rentalapi.services.SewaService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kendaraan/{kendaraanId}/sewa")
public class SewaResource {

    @Autowired
    SewaService sewaService;

    @GetMapping("")
    ResponseEntity<List<Sewa>> getAllSewa(HttpServletRequest request) {
        int userId = (Integer) request.getAttribute("userId");
        List<Sewa> sewas = sewaService.fetchAllSewaByIdUser(userId);
        return new ResponseEntity<>(sewas, HttpStatus.OK);
    }

    @GetMapping("/{sewaId}")
    ResponseEntity<Sewa> getSewaById(HttpServletRequest request,
                                          @PathVariable("kendaraanId") Integer kendaraanId,
                                          @PathVariable("sewaId") Integer sewaId) {
        int userId = (Integer) request.getAttribute("userId");
        Sewa sewa = sewaService.fetchSewaById(sewaId, userId, kendaraanId);
        return new ResponseEntity<>(sewa, HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Sewa> addSewa(HttpServletRequest request,
                                 @PathVariable("kendaraanId") Integer kendaraanId,
                                 @RequestBody Map<String, Object> sewaMap) {
        int userId = (Integer) request.getAttribute("userId");
        Long tanggalSewa = (Long) sewaMap.get("tanggalSewa");
        Integer lamaSewa = (Integer) sewaMap.get("lamaSewa");

        Sewa sewa = sewaService.addSewa(userId, kendaraanId, tanggalSewa, lamaSewa);
        return new ResponseEntity<>(sewa, HttpStatus.CREATED);
    }

}
