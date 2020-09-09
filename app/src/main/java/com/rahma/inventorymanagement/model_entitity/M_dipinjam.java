package com.rahma.inventorymanagement.model_entitity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class M_dipinjam {
    @SerializedName("barang_id")
    @Expose
    private Integer barangId;
    @SerializedName("nama_barang")
    @Expose
    private String namaBarang;
    @SerializedName("akun_id")
    @Expose
    private Integer akunId;
    @SerializedName("tanggal_peminjaman")
    @Expose
    private String tanggalPeminjaman;
    @SerializedName("tanggal_pengembalian")
    @Expose
    private String tanggalPengembalian;
    @SerializedName("jumlah_pinjam")
    @Expose
    private Integer jumlahPinjam;

    public Integer getBarangId() {
        return barangId;
    }

    public void setBarangId(Integer barangId) {
        this.barangId = barangId;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Integer getAkunId() {
        return akunId;
    }

    public void setAkunId(Integer akunId) {
        this.akunId = akunId;
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

    public Integer getJumlahPinjam() {
        return jumlahPinjam;
    }

    public void setJumlahPinjam(Integer jumlahPinjam) {
        this.jumlahPinjam = jumlahPinjam;
    }

    @Override
    public String toString() {
        return "M_dipinjam{" +
                "barangId=" + barangId +
                ", namaBarang='" + namaBarang + '\'' +
                ", akunId=" + akunId +
                ", jumlahPinjam=" + jumlahPinjam +
                '}';
    }
}
