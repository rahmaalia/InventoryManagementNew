package com.rahma.inventorymanagement.model_entitity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class E_permintaan {
    @SerializedName("id_permintaan")
    @Expose
    private Integer idPermintaan;
    @SerializedName("barang_id")
    @Expose
    private Integer barangId;
    @SerializedName("akun_id")
    @Expose
    private Integer akunId;
    @SerializedName("kelas_id")
    @Expose
    private Integer kelasId;
    @SerializedName("jurusan_id")
    @Expose
    private Integer jurusanId;
    @SerializedName("status_permintaan")
    @Expose
    private String statusPermintaan;
    @SerializedName("jumlah_pinjam")
    @Expose
    private Integer jumlahPinjam;
    @SerializedName("tanggal_peminjaman")
    @Expose
    private String tanggalPeminjaman;
    @SerializedName("tanggal_pengembalian")
    @Expose
    private String tanggalPengembalian;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("nama_barang")
    @Expose
    private String namaBarang;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("angkatan")
    @Expose
    private String angkatan;

    public Integer getIdPermintaan() {
        return idPermintaan;
    }

    public void setIdPermintaan(Integer idPermintaan) {
        this.idPermintaan = idPermintaan;
    }

    public Integer getBarangId() {
        return barangId;
    }

    public void setBarangId(Integer barangId) {
        this.barangId = barangId;
    }

    public Integer getAkunId() {
        return akunId;
    }

    public void setAkunId(Integer akunId) {
        this.akunId = akunId;
    }

    public Integer getKelasId() {
        return kelasId;
    }

    public void setKelasId(Integer kelasId) {
        this.kelasId = kelasId;
    }

    public Integer getJurusanId() {
        return jurusanId;
    }

    public void setJurusanId(Integer jurusanId) {
        this.jurusanId = jurusanId;
    }

    public String getStatusPermintaan() {
        return statusPermintaan;
    }

    public void setStatusPermintaan(String statusPermintaan) {
        this.statusPermintaan = statusPermintaan;
    }

    public Integer getJumlahPinjam() {
        return jumlahPinjam;
    }

    public void setJumlahPinjam(Integer jumlahPinjam) {
        this.jumlahPinjam = jumlahPinjam;
    }

    public String getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(String tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public String getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(String tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }
}
