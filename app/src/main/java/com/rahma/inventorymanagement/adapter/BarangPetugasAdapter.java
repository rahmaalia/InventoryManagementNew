package com.rahma.inventorymanagement.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.model_entitity.E_peminjaman;

import java.util.List;

public class BarangPetugasAdapter extends RecyclerView.Adapter<BarangPetugasAdapter.BarangViewHolder> {
    private List<E_peminjaman> peminjamanList;
    E_peminjaman ePeminjaman;
    Context mContext;

    public BarangPetugasAdapter(Context mContext, List<E_peminjaman> ePeminjamans){
        this.mContext = mContext;
        peminjamanList = ePeminjamans;
    }

    @NonNull
    @Override
    public BarangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang_petugas,parent,false);
        return new BarangPetugasAdapter.BarangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangPetugasAdapter.BarangViewHolder holder, int position) {
        ePeminjaman = peminjamanList.get(position);
        holder.namaBarang.setText(ePeminjaman.getNamaBarang());
        holder.stok.setText(String.valueOf(ePeminjaman.getBarang_benar()));

        Log.d("onBindViewHolder", ePeminjaman.toString());
    }

    @Override
    public int getItemCount() {
        return peminjamanList.size();
    }

    public class BarangViewHolder extends RecyclerView.ViewHolder {
        public final TextView namaBarang,stok;
        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);
            namaBarang = itemView.findViewById(R.id.tvNamaBarangPetugas);
            stok = itemView.findViewById(R.id.tvStokPetugas);

        }
    }
}
