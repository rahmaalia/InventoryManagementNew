package com.rahma.inventorymanagement;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.adapter.HistoryAdapter;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_History;
import com.rahma.inventorymanagement.model_entitity.M_History;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    HistoryAdapter historyAdapter;
    RecyclerView rvHistory;
    int akun_id;
    TextView tvGone;
    List<E_History> histories;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);

        sharedPrefManager = new SharedPrefManager(this);
        akun_id = sharedPrefManager.getSpIduser();

        tvGone = findViewById(R.id.tv_goneHistory);
        rvHistory = findViewById(R.id.rvHistory);

        getHistory();
        mApiService.getHistory(akun_id).enqueue(new Callback<M_History>() {
            @Override
            public void onResponse(Call<M_History> call, Response<M_History> response) {
                if (response.isSuccessful()){
                    Toast.makeText(HistoryActivity.this,"sukses",Toast.LENGTH_SHORT).show();
                    histories = response.body().getData();
                    if (response.body().getData().isEmpty()){
                        tvGone.setVisibility(View.VISIBLE);
                    }
                    historyAdapter = new HistoryAdapter(HistoryActivity.this,histories);
                    Log.d( "onResponse: " , histories.toString());
                    rvHistory.setAdapter(historyAdapter);
                    historyAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(HistoryActivity.this,"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<M_History> call, Throwable t) {
                Toast.makeText(HistoryActivity.this,"koneksi jelek",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getHistory() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rvHistory.setLayoutManager(mLayoutManager);
        rvHistory.setItemAnimator(new DefaultItemAnimator());
    }
}