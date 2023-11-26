package com.proyekpbo.rentalapi.domain;

public class Sewa {
    private Integer sewaId;
    private Integer userId;
    private Integer kendaraanId;
    private Long tanggalSewa;
    private Integer lamaSewa;
    private Integer totalHargaSewa;

    public Sewa(Integer sewaId, Integer userId, Integer kendaraanId, Long tanggalSewa, Integer lamaSewa, Integer totalHargaSewa) {
        this.sewaId = sewaId;
        this.userId = userId;
        this.kendaraanId = kendaraanId;
        this.tanggalSewa = tanggalSewa;
        this.lamaSewa = lamaSewa;
        this.totalHargaSewa = totalHargaSewa;
    }

    public Integer getSewaId() {
        return sewaId;
    }

    public void setSewaId(Integer sewaId) {
        this.sewaId = sewaId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getKendaraanId() {
        return kendaraanId;
    }

    public void setKendaraanId(Integer kendaraanId) {
        this.kendaraanId = kendaraanId;
    }

    public Long getTanggalSewa() {
        return tanggalSewa;
    }

    public void setTanggalSewa(Long tanggalSewa) {
        this.tanggalSewa = tanggalSewa;
    }

    public Integer getLamaSewa() {
        return lamaSewa;
    }

    public void setLamaSewa(Integer lamaSewa) {
        this.lamaSewa = lamaSewa;
    }

    public Integer getTotalHargaSewa() {
        return totalHargaSewa;
    }

    public void setTotalHargaSewa(Integer totalHargaSewa) {
        this.totalHargaSewa = totalHargaSewa;
    }
}
