package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

public class ProfilActivity extends AppCompatActivity {
    Button btn_keluar;
    TextView TvResultNama;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

//        mContext = getApplicationContext();
        sharedPrefManager = new SharedPrefManager(this);
        TvResultNama = findViewById(R.id.tvNama);
        TvResultNama.setText(sharedPrefManager.getSpUsername());
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