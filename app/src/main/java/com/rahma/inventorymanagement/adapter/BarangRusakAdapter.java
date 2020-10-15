package com.rahma.inventorymanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.model_entitity.E_peminjaman;

import java.util.List;

public class BarangRusakAdapter extends RecyclerView.Adapter<BarangRusakAdapter.BarangRusakViewHolder> {
    private List<E_peminjaman> peminjamanList;
    E_peminjaman ePeminjaman;
    Context mContext;

    public BarangRusakAdapter(Context mContext, List<E_peminjaman> ePeminjamans){
        this.mContext = mContext;
        peminjamanList = ePeminjamans;
    }

    @NonNull
    @Override
    public BarangRusakAdapter.BarangRusakViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barangrusak,parent,false);
        return new BarangRusakAdapter.BarangRusakViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangRusakAdapter.BarangRusakViewHolder holder, int position) {
        ePeminjaman = peminjamanList.get(position);
        holder.namaBarang.setText(ePeminjaman.getNamaBarang());
        holder.stok.setText(String.valueOf(ePeminjaman.getBarangRusak()));
    }

    @Override
    public int getItemCount() {
        return peminjamanList.size();
    }

    public class BarangRusakViewHolder extends RecyclerView.ViewHolder {
        public final TextView namaBarang,stok;
        public BarangRusakViewHolder(@NonNull View itemView) {
            super(itemView);
            namaBarang = itemView.findViewById(R.id.tvNamaBarangrusak);
            stok = itemView.findViewById(R.id.tvStokRusak);
        }
    }
}
