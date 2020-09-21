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
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_History;
import com.rahma.inventorymanagement.model_entitity.E_permintaan;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
   private List<E_History> historyList;
   E_History eHistory;
    Context mContext;
    CardView cardView;
    BaseApiService mApiService;
    String statuss;

    public HistoryAdapter(Context mContext,List<E_History> eHistories){
        this.mContext = mContext;
        historyList = eHistories;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history,parent,false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        return new HistoryAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        eHistory = historyList.get(position);
        holder.namabarang.setText(eHistory.getNamaBarang());
        holder.stok.setText(String.valueOf(eHistory.getJumlahPinjam()));
        holder.tgl_pinjam.setText(eHistory.getTanggalPeminjaman());
        holder.tgl_kembali.setText(eHistory.getTanggalPengembalian());
        holder.status.setText(eHistory.getStatusPermintaan());

        Log.d("onBindViewHolder: ", eHistory.toString());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        public final TextView namabarang,stok,tgl_pinjam,tgl_kembali,status;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            namabarang = itemView.findViewById(R.id.tvNamabarangHistory);
            stok = itemView.findViewById(R.id.tvStokHistory);
            tgl_pinjam = itemView.findViewById(R.id.tvTglPinjamHistory);
            tgl_kembali = itemView.findViewById(R.id.tvTglKembaliHistory);
            cardView = itemView.findViewById(R.id.cvHistory);
            status = itemView.findViewById(R.id.tvStatusHistory);
        }
    }
}
