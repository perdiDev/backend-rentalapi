package com.proyekpbo.rentalapi.domain;

public class User {
    private Integer userId;
    private String status;
    private String nama;
    private String alamat;
    private String email;
    private String password;

    public User(
            Integer userId, String status, String nama, String alamat, String email, String password
    ) {
        this.userId = userId;
        this.status = status;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
