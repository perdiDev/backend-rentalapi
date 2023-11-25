package com.proyekpbo.rentalapi.resources;

import com.proyekpbo.rentalapi.domain.Kendaraan;
import com.proyekpbo.rentalapi.exceptions.AuthException;
import com.proyekpbo.rentalapi.services.KendaraanService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/kendaraan")
public class KendaraanResource {

    @Autowired
    KendaraanService kendaraanService;

    @GetMapping("")
    ResponseEntity<List<Kendaraan>> getAllKendaraan() {
        List<Kendaraan> kendaraans = kendaraanService.fetchAllKendaraan();
        return new ResponseEntity<>(kendaraans, HttpStatus.OK);
    }

    @GetMapping("/{kendaraanId}")
    ResponseEntity<Kendaraan> getKendaraanById(
            @PathVariable("kendaraanId") Integer kendaraanId
    ) {
        Kendaraan kendaraan = kendaraanService.fetchKendaraanById(kendaraanId);
        return new ResponseEntity<>(kendaraan, HttpStatus.OK);
    }

    @PostMapping("")
    ResponseEntity<Kendaraan> addKendaraan(
            HttpServletRequest request,
            @RequestBody Map<String, Object> kendaraanMap
    ) {
        Integer userId = (Integer) request.getAttribute("userId");

        String namaKendaraan = (String) kendaraanMap.get("namaKendaraan");
        String tipeKendaraan = (String) kendaraanMap.get("tipeKendaraan");
        Integer hargaSewa = (Integer) kendaraanMap.get("hargaSewa");
        Integer jumlahKetersediaan = (Integer) kendaraanMap.get("jumlahKetersediaan");

        Kendaraan kendaraan = kendaraanService.addKendaraan(namaKendaraan, tipeKendaraan, hargaSewa, jumlahKetersediaan);
        return new ResponseEntity<>(kendaraan, HttpStatus.CREATED);
    }

    @PutMapping("/{kendaraanId}")
    ResponseEntity<Map<String, Boolean>> updateKendaraan(
            @PathVariable("kendaraanId") Integer kendaraanId,
            @RequestBody Kendaraan kendaraan
    ) {
        kendaraanService.updateKendaraan(kendaraanId, kendaraan);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{kendaraanId}")
    ResponseEntity<Map<String, Boolean>> deleteKendaraan(
            @PathVariable("kendaraanId") Integer kendaraanId
    ) {
        kendaraanService.removeKendaraan(kendaraanId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("success", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
