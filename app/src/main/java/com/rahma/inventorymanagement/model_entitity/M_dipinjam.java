package com.rahma.inventorymanagement.model_entitity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class M_dipinjam {
    @SerializedName("namabarang")
    @Expose
    private String namabarang;
    @SerializedName("stok")
    @Expose
    private String stok;
    @SerializedName("tanggal_pinjam")
    @Expose
    private String tanggal_pinjam;
    @SerializedName("tanggal_kembali")
    @Expose
    private String tanggal_kembali;

    public String getNamabarang(){
        return namabarang;

    }
    public void setNamabarang(String nama) {
        this.namabarang = nama;
    }

    public String getStok(){
        return stok;

    }
    public void setStok(String nama) {
        this.stok = nama;
    }

    public String getTanggal_pinjam(){
        return tanggal_pinjam;

    }
    public void setTanggal_pinjam(String nama) {
        this.tanggal_pinjam = nama;
    }

    public String getTanggal_kembali(){
        return tanggal_kembali;

    }
    public void setTanggal_kembali(String nama) {
        this.tanggal_kembali = nama;
    }

}
