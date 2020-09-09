package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

public class ProfilActivity extends AppCompatActivity {
    Button btn_keluar;
    View view;
    SharedPrefManager sharedPrefManager;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

//        mContext = getApplicationContext();
        sharedPrefManager = new SharedPrefManager(this);

        btn_keluar =findViewById(R.id.btnKeluar);
        btn_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LOGIN, false);
                startActivity(new Intent(ProfilActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

    }
}