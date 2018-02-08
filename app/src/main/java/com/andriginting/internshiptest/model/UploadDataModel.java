package com.andriginting.internshiptest.model;

/**
 * Created by Andri Ginting on 2/2/2018.
 */

public class UploadDataModel {
    public String judul;
    public String lokasi;
    public String kategori;
    public String deskripsi;
    public String userId;
    public String imageUrl;

    public UploadDataModel(String judul, String lokasi, String kategori, String deskripsi, String userId, String imageUrl) {
        this.judul = judul;
        this.lokasi = lokasi;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.userId = userId;
        this.imageUrl = imageUrl;
    }

    public String getJudul() {
        return judul;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getKategori() {
        return kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getUserId() {
        return userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
