package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

public class PeminjamanActivity extends AppCompatActivity {
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    Button btnPinjam;
    TextView namaBarang,Stok;
    String stokk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);

        mContext = this;
        sharedPrefManager = new SharedPrefManager(this);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);

        namaBarang =  findViewById(R.id.namabarangPost);
        Stok =  findViewById(R.id.tvStokPost);
        btnPinjam =  findViewById(R.id.btnPinjam);

        final Intent intent= getIntent();
        namaBarang.setText(getIntent().getExtras().getString("nama_barang"));
        String stokk = String.valueOf(intent.getIntExtra("stok_barang",1));
        Stok.setText(stokk);




    }
}