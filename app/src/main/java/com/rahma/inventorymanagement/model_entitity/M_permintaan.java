package com.rahma.inventorymanagement.model_entitity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class M_permintaan {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<E_permintaan> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<E_permintaan> getData() {
        return data;
    }

    public void setData(List<E_permintaan> data) {
        this.data = data;
    }
}
