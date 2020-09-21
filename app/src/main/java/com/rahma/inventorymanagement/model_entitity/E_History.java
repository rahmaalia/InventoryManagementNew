package com.rahma.inventorymanagement.model_entitity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class E_History {
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
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("nama_barang")
    @Expose
    private String namaBarang;
    @SerializedName("id_akun")
    @Expose
    private Integer idAkun;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("kata_sandi")
    @Expose
    private String kataSandi;
    @SerializedName("role_id")
    @Expose
    private Integer roleId;
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

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Integer getIdAkun() {
        return idAkun;
    }

    public void setIdAkun(Integer idAkun) {
        this.idAkun = idAkun;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKataSandi() {
        return kataSandi;
    }

    public void setKataSandi(String kataSandi) {
        this.kataSandi = kataSandi;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }
    @Override
    public String toString() {
        return "E_History{" +
                "barangId=" + barangId +
                ", namaBarang='" + namaBarang + '\'' +
                ", akunId=" + akunId +
                ", jumlahPinjam=" + jumlahPinjam +
                '}';
    }
}
