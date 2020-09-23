package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahma.inventorymanagement.model_entitity.E_permintaan;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

public class ProfilPetugas extends AppCompatActivity {
    Button btn_keluar;
    TextView TvResultNama,kelasprofil;
    SharedPrefManager sharedPrefManager;
    E_permintaan ePermintaan;
    String kelas;
    int jurusan;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_petugas);

        sharedPrefManager = new SharedPrefManager(this);
//        kelas = ePermintaan.getAngkatan();
        TvResultNama = findViewById(R.id.tvNamaPetugas);
        kelasprofil = findViewById(R.id.jurusans);
        back= findViewById(R.id.exitProfilP);
        TvResultNama.setText(sharedPrefManager.getSpUsername());
        jurusan = sharedPrefManager.getSpIdjurusan();

        if (jurusan == 1){
            kelasprofil.setText("RPL");
        }else if (jurusan == 2){
            kelasprofil.setText("MEKATRONIKA");
        }else if (jurusan == 3) {
            kelasprofil.setText("ANIMASI");
        }else if (jurusan == 4) {
            kelasprofil.setText("Mesin");
        }else if (jurusan == 5) {
            kelasprofil.setText("KIMIA INDUSTRI");
        }else if (jurusan == 6) {
            kelasprofil.setText("mULTIMEDIA");
        }

        btn_keluar =findViewById(R.id.btnKeluarPetugas);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfilPetugas.this);
                builder1.setMessage("Apakah anda yakin ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Iya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LOGIN_ADMIN, false);
                                startActivity(new Intent(ProfilPetugas.this, LoginActivity.class)
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
                Intent i = new Intent(ProfilPetugas.this,BerandaPetugas.class);
                startActivity(i);
            }
        });
    }
}