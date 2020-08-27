package com.rahma.inventorymanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.model_entitity.M_dipinjam;

import java.util.List;

public class DipinjamAdapter extends RecyclerView.Adapter<DipinjamAdapter.DipinjamViewHolder> {
   private List<M_dipinjam> dipinjams;
   Context mContext;
   CardView cardView;

   public DipinjamAdapter(Context mContext, List<M_dipinjam> mDipinjams){
       this.mContext = mContext;
       dipinjams = mDipinjams;
   }


    @NonNull
    @Override
    public DipinjamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dipinjam,parent,false);
        return new DipinjamAdapter.DipinjamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DipinjamViewHolder holder, int position) {
        final M_dipinjam mDipinjam = dipinjams.get(position);
        holder.namabarang.setText(mDipinjam.getNamabarang());
        holder.stok.setText(mDipinjam.getStok());
        holder.tgl_pinjam.setText(mDipinjam.getTanggal_pinjam());
        holder.tgl_kembali.setText(mDipinjam.getTanggal_kembali());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DipinjamViewHolder extends RecyclerView.ViewHolder {
       public final TextView namabarang,stok,tgl_pinjam,tgl_kembali;
        public DipinjamViewHolder(@NonNull View itemView) {
            super(itemView);
            namabarang = itemView.findViewById(R.id.tvNamabarang);
            stok = itemView.findViewById(R.id.tvStok);
            tgl_pinjam = itemView.findViewById(R.id.tvTglPinjam);
            tgl_kembali = itemView.findViewById(R.id.tvTglKembali);
            cardView = itemView.findViewById(R.id.cvDipinjam);

        }
    }
}
