package com.rahma.inventorymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Context mContext;
    BaseApiService mApiService;
    EditText etNamaPengguna;
    EditText etKataSandi;
    Button btnLogin;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mContext = this;
        mApiService = RetrofitClient.getAPIService();

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getSpLogin()) {
            startActivity(new Intent(LoginActivity.this, BerandaActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
        Button btnMoveActivity = findViewById(R.id.btnLogin);
        btnMoveActivity.setOnClickListener(this);

        mContext = LoginActivity.this;
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        initComponents();
    }

    private void initComponents() {
        etNamaPengguna = (EditText) findViewById(R.id.et_namapengguna);
        etKataSandi = (EditText) findViewById(R.id.et_katasandi);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.loginRequest(etNamaPengguna.getText().toString(), etKataSandi.getText().toString())
                        .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject JSONResult = new JSONObject(response.body().string());
                                if (JSONResult.getString("status").equals("true")){
                                    Toast.makeText(mContext, "Berhasil login", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
//                                          String nama = jsonRESULT.getJSONObject("data").getString("nama");
                                            String username = JSONResult.getJSONObject("data").getString("username");
                                            int idjurusan = JSONResult.getJSONObject("data").getInt("jurusan_id");
                                            int idakun = JSONResult.getJSONObject("data").getInt("id_akun");
                                            Log.d("username", "user" + username);
                                            Log.d("id_akun", "id_akun" + idakun);


                                            sharedPrefManager.saveSPString(SharedPrefManager.SP_USERNAME, username);
                                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_LOGIN, true);
                                            sharedPrefManager.saveSPint(String.valueOf(SharedPrefManager.SP_IDJURUSAN), idjurusan);
                                            sharedPrefManager.saveSPint(String.valueOf(SharedPrefManager.SP_IDUSER), idakun);
                                            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                                            finish();
                                }
                                else{
                                    String error = JSONResult.getString("error");
                                    Toast.makeText(mContext,error, Toast.LENGTH_SHORT).show();
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
                        Log.e("debug", "onFailure : ERROR > " + t.toString());
//                                loading.dismiss();
                                Toast.makeText(mContext, "Gagal login", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                Intent moveIntent = new Intent(LoginActivity.this, BerandaActivity.class);
                startActivity(moveIntent);
                break;

        }
    }
}
