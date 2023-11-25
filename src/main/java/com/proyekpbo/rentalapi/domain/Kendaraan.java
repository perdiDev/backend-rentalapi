package com.proyekpbo.rentalapi.domain;

public class Kendaraan {
    private Integer kendaraanId;
    private String namaKendaraan;
    private String tipeKendaraan;
    private Integer hargaSewa;
    private Integer jumlahKetersediaan;

    public Kendaraan(Integer kendaraanId, String namaKendaraan, String tipeKendaraan, Integer hargaSewa, Integer jumlahKetersediaan) {
        this.kendaraanId = kendaraanId;
        this.namaKendaraan = namaKendaraan;
        this.tipeKendaraan = tipeKendaraan;
        this.hargaSewa = hargaSewa;
        this.jumlahKetersediaan = jumlahKetersediaan;
    }

    public Integer getKendaraanId() {
        return kendaraanId;
    }

    public void setKendaraanId(Integer kendaraanId) {
        this.kendaraanId = kendaraanId;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public String getTipeKendaraan() {
        return tipeKendaraan;
    }

    public void setTipeKendaraan(String tipeKendaraan) {
        this.tipeKendaraan = tipeKendaraan;
    }

    public Integer getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(Integer hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public Integer getJumlahKetersediaan() {
        return jumlahKetersediaan;
    }

    public void setJumlahKetersediaan(Integer jumlahKetersediaan) {
        this.jumlahKetersediaan = jumlahKetersediaan;
    }
}
