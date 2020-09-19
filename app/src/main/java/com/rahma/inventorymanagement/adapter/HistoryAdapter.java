package com.rahma.inventorymanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_permintaan;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
   private List<E_permintaan> permintaanList;
    Context mContext;
    CardView cardView;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    String statuss;

    public HistoryAdapter(Context mContext,List<E_permintaan> ePermintaans){
        this.mContext = mContext;
        permintaanList = ePermintaans;
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
