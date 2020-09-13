package com.rahma.inventorymanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.PeminjamanActivity;
import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.model_entitity.E_peminjaman;

import java.util.List;

public class PeminjamanAdapter extends RecyclerView.Adapter<PeminjamanAdapter.PeminjamanViewHolder> {
    private List<E_peminjaman> peminjamanList;
    E_peminjaman ePeminjaman;
    Context mContext;
    CardView cardView;

    public PeminjamanAdapter(Context mContext, List<E_peminjaman> ePeminjamans){
        this.mContext = mContext;
        peminjamanList = ePeminjamans;
    }

    @NonNull
    @Override
    public PeminjamanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_peminjaman,parent,false);
        return new PeminjamanAdapter.PeminjamanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeminjamanViewHolder holder, int position) {
        ePeminjaman = peminjamanList.get(position);
        holder.namaBarang.setText(ePeminjaman.getNamaBarang());
        holder.stok.setText(String.valueOf(ePeminjaman.getStokBarang()));

        Log.d("onBindViewHolder", ePeminjaman.toString());
    }

    @Override
    public int getItemCount() {
        return peminjamanList.size();
    }

    public class PeminjamanViewHolder extends RecyclerView.ViewHolder {
        public final TextView namaBarang,stok;
        public PeminjamanViewHolder(@NonNull View itemView) {
            super(itemView);
            namaBarang = itemView.findViewById(R.id.tvNamaBarangPeminjaman);
            stok = itemView.findViewById(R.id.tvStokPeminjaman);
            cardView = itemView.findViewById(R.id.cvPeminjaman);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Intent i = new Intent(mContext, PeminjamanActivity.class);
                        i.putExtra("nama_barang",peminjamanList.get(position).getNamaBarang());
                        i.putExtra("stok_barang",peminjamanList.get(position).getStokBarang());
                        i.putExtra("id_barang",peminjamanList.get(position).getIdBarang());
//                        i.putExtra("stok_barang",peminjamanList);
                        mContext.startActivity(i);
                    }

                }
            });
        }
    }
}
