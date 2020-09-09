package com.rahma.inventorymanagement.model_entitity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class M_peminjaman {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<E_peminjaman> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<E_peminjaman> getData() {
        return data;
    }

    public void setData(List<E_peminjaman> data) {
        this.data = data;
    }
}
