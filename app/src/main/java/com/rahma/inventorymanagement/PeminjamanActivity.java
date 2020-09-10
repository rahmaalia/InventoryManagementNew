package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.Calendar;
import java.util.TimeZone;

public class PeminjamanActivity extends AppCompatActivity {
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    Button btnPinjam;
    TextView namaBarang,Stok;
    String stokk;
    RadioButton tanggal1,tanggal2,tanggal3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminjaman);

        mContext = this;
        sharedPrefManager = new SharedPrefManager(this);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);

        tanggal();

        namaBarang =  findViewById(R.id.namabarangPost);
        Stok =  findViewById(R.id.tvStokPost);
        btnPinjam =  findViewById(R.id.btnPinjam);



        final Intent intent= getIntent();
        namaBarang.setText(getIntent().getExtras().getString("nama_barang"));
        String stokk = String.valueOf(intent.getIntExtra("stok_barang",1));
        Stok.setText(stokk);




    }

    private void tanggal() {
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        int thisYear = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        int tgl = date + 1;
        int tgl2 = date + 2;

        tanggal1 = findViewById(R.id.rbTanggal1);
        tanggal2 = findViewById(R.id.rbTanggal2);
        tanggal3 = findViewById(R.id.rbTanggal3);

        tanggal1.setText("" +date +"/"+""+month+"/"+""+thisYear);
        tanggal2.setText("" +tgl +"/"+""+month+"/"+""+thisYear);
        tanggal3.setText("" +tgl2 +"/"+""+month+"/"+""+thisYear);
    }
}