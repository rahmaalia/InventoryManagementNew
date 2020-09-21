package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

public class ProfilPetugas extends AppCompatActivity {
    Button btn_keluar;
    TextView TvResultNama;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_petugas);

        sharedPrefManager = new SharedPrefManager(this);
        TvResultNama = findViewById(R.id.tvNamaPetugas);
        TvResultNama.setText(sharedPrefManager.getSpUsername());

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
    }
}