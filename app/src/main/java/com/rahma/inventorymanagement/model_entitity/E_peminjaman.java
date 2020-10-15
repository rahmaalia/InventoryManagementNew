package com.rahma.inventorymanagement.model_entitity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class E_peminjaman {
    @SerializedName("id_barang")
    @Expose
    private Integer idBarang;
    @SerializedName("nama_barang")
    @Expose
    private String namaBarang;
    @SerializedName("stok_barang")
    @Expose
    private Integer stokBarang;
    @SerializedName("jurusan_id")
    @Expose
    private Integer jurusanId;
    @SerializedName("barang_rusak")
    @Expose
    private Integer barang_rusak;
    @SerializedName("barang_benar")
    @Expose
    private Integer barang_benar;
    @SerializedName("created_at")
    @Expose
    private Object createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;

    public Integer getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Integer idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Integer getStokBarang() {
        return stokBarang;
    }

    public void setStokBarang(Integer stokBarang) {
        this.stokBarang = stokBarang;
    }

    public Integer getJurusanId() {
        return jurusanId;
    }

    public void setJurusanId(Integer jurusanId) {
        this.jurusanId = jurusanId;
    }

    public Integer getBarangRusak() {
        return barang_rusak;
    }

    public void setBarang_rusak(Integer barang_rusak) {
        this.barang_rusak = barang_rusak;
    }

    public Integer getBarang_benar() {
        return barang_benar;
    }

    public void setBarang_benar(Integer barang_benar) {
        this.barang_benar = barang_benar;
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
}
