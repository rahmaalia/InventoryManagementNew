package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeminjamanActivity extends AppCompatActivity {
    BaseApiService mApiService;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    Button btnPinjam,btnTambah,btnKurang;
    TextView namaBarang,Stok,tvJumlahbrng;
    RadioButton tanggal1,tanggal2,tanggal3,rbSemua;
    RadioGroup rgTanggall;
    String status,tanggal_peminjaman,tanggal_pengembalian,jumlahBarang;
    View view;
    int akun_id;
    private int mCount = 1;
    private final String COUNT_KEY = "count";

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
        btnTambah = findViewById(R.id.btnTambah);
        btnKurang = findViewById(R.id.btnKurang);
        tvJumlahbrng =findViewById(R.id.tvJumlahbarangPost);
        rgTanggall = (RadioGroup) findViewById(R.id.rgTanggal);
        int selected = rgTanggall.getCheckedRadioButtonId();
        rbSemua = findViewById(selected);


        final Intent intent= getIntent();
        namaBarang.setText(getIntent().getExtras().getString("nama_barang"));
        final String stokk = String.valueOf(intent.getIntExtra("stok_barang",1));
        Stok.setText(stokk);
        final int stok = intent.getIntExtra("stok_barang",1);
        final int id_barang = intent.getIntExtra("id_barang",1);
        akun_id = sharedPrefManager.getSpIduser();

        //BUTTON TAMBAH JUMLAH PINJAM
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCount == stok){
                    Toast.makeText(PeminjamanActivity.this,"Maksimal",Toast.LENGTH_SHORT).show();
                }else {
                    mCount ++;
                    tvJumlahbrng.setText(String.format("%s",mCount));
                    jumlahBarang = tvJumlahbrng.getText().toString();
                }
            }
        });

        //BUTTON KURANG JUMLAH PINJAM
        btnKurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCount == 1){
                    Toast.makeText(PeminjamanActivity.this,"Minimal",Toast.LENGTH_SHORT).show();
                }else {
                    mCount --;
                    tvJumlahbrng.setText(String.format("%s",mCount));
                    jumlahBarang = tvJumlahbrng.getText().toString();
                }
            }
        });
        jumlahBarang = tvJumlahbrng.getText().toString();


        //TANGGAL
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        int thisYear = c.get(Calendar.YEAR);
        int date = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);

        int tgl = date + 1;
        int tgl2 = date + 2;

        tanggal_peminjaman = ("" +date +"/"+""+month+"/"+""+thisYear);

        tanggal1 = findViewById(R.id.rbTanggal1);
        tanggal2 = findViewById(R.id.rbTanggal2);
        tanggal3 = findViewById(R.id.rbTanggal3);

        tanggal1.setText("" +date +"/"+""+month+"/"+""+thisYear);
        tanggal2.setText("" +tgl +"/"+""+month+"/"+""+thisYear);
        tanggal3.setText("" +tgl2 +"/"+""+month+"/"+""+thisYear);




        tanggal_pengembalian = rbSemua.getText().toString();

        tanggalPinjam();

        status ="diminta";
        btnPinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    mApiService.peminjamanRequest(id_barang,akun_id,status,jumlahBarang,tanggal_peminjaman,tanggal_pengembalian).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()){
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    if (jsonObject.getString("status").equals("true")){
                                        Toast.makeText(PeminjamanActivity.this, "berjasil", Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(PeminjamanActivity.this, "gagal", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(PeminjamanActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });
    }

    private void tanggalPinjam() {
    }


    private void tanggal() {
//        Calendar c = Calendar.getInstance(TimeZone.getDefault());
//        int thisYear = c.get(Calendar.YEAR);
//        int date = c.get(Calendar.DAY_OF_MONTH);
//        int month = c.get(Calendar.MONTH);
//
//        int tgl = date + 1;
//        int tgl2 = date + 2;
//
//         tanggal_peminjaman = ("" +date +"/"+""+month+"/"+""+thisYear);
//
//        tanggal1 = findViewById(R.id.rbTanggal1);
//        tanggal2 = findViewById(R.id.rbTanggal2);
//        tanggal3 = findViewById(R.id.rbTanggal3);
//
//        tanggal1.setText("" +date +"/"+""+month+"/"+""+thisYear);
//        tanggal2.setText("" +tgl +"/"+""+month+"/"+""+thisYear);
//        tanggal3.setText("" +tgl2 +"/"+""+month+"/"+""+thisYear);
    }
}