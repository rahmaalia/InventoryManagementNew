package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.inventorymanagement.adapter.BarangPetugasAdapter;
import com.rahma.inventorymanagement.adapter.BarangRusakAdapter;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_peminjaman;
import com.rahma.inventorymanagement.model_entitity.M_peminjaman;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarangRusak extends AppCompatActivity {
    BaseApiService mApiService;
    BarangRusakAdapter barangRusakAdapter;
    TextView tvNamaBarang,tvStok;
    RecyclerView rvPeminjaman;
    List<E_peminjaman> peminjamans;
    int jurusan_id,idjurusan;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_rusak);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        sharedPrefManager = new SharedPrefManager(this);
        jurusan_id = sharedPrefManager.getSpIdjurusan();

        rvPeminjaman = findViewById(R.id.rvBarangRusak);
        
        getBarang();
        mApiService.getPeminjaman(jurusan_id).enqueue(new Callback<M_peminjaman>() {
            @Override
            public void onResponse(Call<M_peminjaman> call, Response<M_peminjaman> response) {
                if (response.isSuccessful()){
                    Toast.makeText(BarangRusak.this,"sukses",Toast.LENGTH_SHORT).show();
                    peminjamans = response.body().getData();

                    barangRusakAdapter = new BarangRusakAdapter(BarangRusak.this,peminjamans);
                    Log.d("onResponse", peminjamans.toString());
                    rvPeminjaman.setAdapter(barangRusakAdapter);
                    barangRusakAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(BarangRusak.this,"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<M_peminjaman> call, Throwable t) {
                Toast.makeText(BarangRusak.this,"koneksi jelek",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBarang() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvPeminjaman.setLayoutManager(mLayoutManager);
        rvPeminjaman.setItemAnimator(new DefaultItemAnimator());
    }
}