package com.rahma.inventorymanagement.model_entitity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class E_Profil {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("angkatan")
    @Expose
    private String angkatan;
    @SerializedName("kelas_id")
    @Expose
    private Integer kelasId;



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

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public Integer getKelasId() {
        return kelasId;
    }

    public void setKelasId(Integer kelasId) {
        this.kelasId = kelasId;
    }

    @Override
    public String toString() {
        return "E_Profil{" +
                "nama='" + nama + '\'' +
                ", alamat='" + alamat + '\'' +
                ", noHp='" + noHp + '\'' +
                ", angkatan='" + angkatan + '\'' +
                ", kelasId=" + kelasId +
                '}';
    }
}
