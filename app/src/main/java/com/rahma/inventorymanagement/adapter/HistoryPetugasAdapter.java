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
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_History;
import com.rahma.inventorymanagement.model_entitity.E_HistoryPetugas;
import com.rahma.inventorymanagement.model_entitity.E_permintaan;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

public class HistoryPetugasAdapter extends RecyclerView.Adapter<HistoryPetugasAdapter.HistoryPetugasViewHolder> {
    private List<E_HistoryPetugas> historyList;
    Context mContext;
    CardView cardView;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;

    public HistoryPetugasAdapter(Context mContext, List<E_HistoryPetugas> eHistories){
        this.mContext = mContext;
        historyList = eHistories;
    }

    @NonNull
    @Override
    public HistoryPetugasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_petugas,parent,false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        return new HistoryPetugasAdapter.HistoryPetugasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryPetugasAdapter.HistoryPetugasViewHolder holder, int position) {
        final E_HistoryPetugas e_history = historyList.get(position);
        holder.namaSiswa.setText(e_history.getUsername());
        holder.kelas.setText(e_history.getAngkatan());
        holder.namaBarang.setText(e_history.getNamaBarang());
        holder.stok.setText(String.valueOf(e_history.getJumlahPinjam()));

        holder.tglKembalii.setText(e_history.getTanggalPengembalian());
        holder.status.setText(e_history.getStatusPermintaan());
        holder.tglPinjamm.setText(e_history.getTanggalPeminjaman());
    }

    @Override
    public int getItemCount() {
        return  historyList.size();
    }

    public class HistoryPetugasViewHolder extends RecyclerView.ViewHolder {
        public final TextView namaSiswa,kelas,namaBarang,stok,tglPinjamm,tglKembalii,status;

        public HistoryPetugasViewHolder(@NonNull View itemView) {
            super(itemView);
            namaSiswa = itemView.findViewById(R.id.tvNamaSiswaHistory);
            kelas = itemView.findViewById(R.id.tvKelasHistory);
            namaBarang = itemView.findViewById(R.id.tvNBPermintaanHistory);
            stok = itemView.findViewById(R.id.tvStokPermintaanHistory);
            tglPinjamm = itemView.findViewById(R.id.tvTanggalPinjamHistory);
            tglKembalii = itemView.findViewById(R.id.tvTanggalKembaliHistory);
            status = itemView.findViewById(R.id.tvStatusHistory);
        }
    }
}
