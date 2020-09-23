package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.inventorymanagement.adapter.DipinjamAdapterPetugas;
import com.rahma.inventorymanagement.adapter.HistoryPetugasAdapter;
import com.rahma.inventorymanagement.adapter.PermintaanPetugasAdapter;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_DipinjamPetugas;
import com.rahma.inventorymanagement.model_entitity.E_History;
import com.rahma.inventorymanagement.model_entitity.E_HistoryPetugas;
import com.rahma.inventorymanagement.model_entitity.M_HistoryPetugas;
import com.rahma.inventorymanagement.model_entitity.M_permintaan;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPetugas extends AppCompatActivity {
    BaseApiService mApiService;
    HistoryPetugasAdapter historyPetugasAdapter;
    TextView namaSiswa,kelas,namaBarang,stok,tglPinjamm,tglKembalii,status,tvGone;
    RecyclerView rvHistory;
    List<E_HistoryPetugas> historyPetugas;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    int jurusan_id;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_petugas);

        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        sharedPrefManager = new SharedPrefManager(this);

        jurusan_id = sharedPrefManager.getSpIdjurusan();

        rvHistory = findViewById(R.id.rvHistoryPetugas);
        tvGone = findViewById(R.id.tv_goneHistoryPetugas);
        back = findViewById(R.id.exitP);

        getHistory();
        mApiService.getHistoryPetugas(jurusan_id).enqueue(new Callback<M_HistoryPetugas>() {
            @Override
            public void onResponse(Call<M_HistoryPetugas> call, Response<M_HistoryPetugas> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(HistoryPetugas.this,"sukses",Toast.LENGTH_SHORT).show();
                    historyPetugas = response.body().getData();
                    if (response.body().getData().isEmpty()){
                        tvGone.setVisibility(View.VISIBLE);
                    }

                    historyPetugasAdapter = new HistoryPetugasAdapter(mContext,historyPetugas);
                    rvHistory.setAdapter(historyPetugasAdapter);
                    historyPetugasAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(mContext,"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<M_HistoryPetugas> call, Throwable t) {
                Toast.makeText(mContext,"koneksi jelek",Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HistoryPetugas.this,BerandaPetugas.class);
                startActivity(i);
            }
        });
    }

    private void getHistory() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rvHistory.setLayoutManager(mLayoutManager);
        rvHistory.setItemAnimator(new DefaultItemAnimator());
    }
}