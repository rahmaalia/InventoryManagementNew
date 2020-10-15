package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.inventorymanagement.adapter.DipinjamAdapter;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.EDipinjam;
import com.rahma.inventorymanagement.model_entitity.E_Profil;
import com.rahma.inventorymanagement.model_entitity.M_Profil;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    Button btn_keluar;
    TextView TvResultNama,noTelp,alamat;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    ImageView back;
//    E_Profil mProfil;
    String namaa,nohp,alamatt;
    BaseApiService mApiService;
    int siswa_id;
    List<E_Profil> profil;
    E_Profil mProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);


//        mContext = getApplicationContext();
        TvResultNama = findViewById(R.id.tvNama);
        noTelp = findViewById(R.id.no_telp);
        alamat = findViewById(R.id.alamat);

        sharedPrefManager = new SharedPrefManager(ProfilActivity.this);
        siswa_id = sharedPrefManager.getSpIduser();




        mApiService.getProfil(siswa_id).enqueue(new Callback<M_Profil>() {
            @Override
            public void onResponse(Call<M_Profil> call, Response<M_Profil> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(ProfilActivity.this, "berhasilah", Toast.LENGTH_SHORT).show();
                    profil = response.body().getData();
                    namaa = profil.get(0).getNama();
                    nohp = profil.get(0).getNoHp();
                    alamatt = profil.get(0).getAlamat();
                    TvResultNama.setText(namaa);
                    noTelp.setText(nohp);
                    alamat.setText(alamatt);

                }else{
                    Toast.makeText(ProfilActivity.this,"gagal",Toast.LENGTH_SHORT).show();
                }
                response.body().getData();
            }

            @Override
            public void onFailure(Call<M_Profil> call, Throwable t) {
                Toast.makeText(ProfilActivity.this,"koneksi jelek",Toast.LENGTH_SHORT).show();
            }

        });


        back = findViewById(R.id.exitProfil);

        btn_keluar =findViewById(R.id.btnKeluar);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfilActivity.this);
                builder1.setMessage("Apakah anda yakin ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Iya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LOGIN, false);
                                startActivity(new Intent(ProfilActivity.this, LoginActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                finish();
//                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Tidak",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfilActivity.this,BerandaActivity.class);
                startActivity(i);
            }
        });

    }
}