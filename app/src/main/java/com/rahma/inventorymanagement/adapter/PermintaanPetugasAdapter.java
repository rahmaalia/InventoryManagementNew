package com.rahma.inventorymanagement.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.BerandaActivity;
import com.rahma.inventorymanagement.BerandaPetugas;
import com.rahma.inventorymanagement.LoginActivity;
import com.rahma.inventorymanagement.PeminjamanActivity;
import com.rahma.inventorymanagement.ProfilActivity;
import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.fragment.DipinjamFragmentPetugas;
import com.rahma.inventorymanagement.model_entitity.E_permintaan;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PermintaanPetugasAdapter extends RecyclerView.Adapter<PermintaanPetugasAdapter.PermintaanViewHolder> {
    private List<E_permintaan> permintaanList;
    Context mContext;
    CardView cardView;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    String statuss,statusbatal;
    int id_permintaan;
    Button btnAcc;

    public PermintaanPetugasAdapter(Context mContext, List<E_permintaan> ePermintaans){
        this.mContext = mContext;
        permintaanList = ePermintaans;
    }

    @NonNull
    @Override
    public PermintaanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permintaan_petugas,parent,false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        return new PermintaanPetugasAdapter.PermintaanViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PermintaanViewHolder holder, int position) {
       final E_permintaan ePermintaan = permintaanList.get(position);
        holder.namaSiswa.setText(ePermintaan.getUsername());
        holder.kelas.setText(ePermintaan.getAngkatan());
        holder.namaBarang.setText(ePermintaan.getNamaBarang());
        holder.stok.setText(String.valueOf(ePermintaan.getJumlahPinjam()));

        holder.tglKembalii.setText(ePermintaan.getTanggalPengembalian());
        holder.status.setText(ePermintaan.getStatusPermintaan());
        holder.tglPinjamm.setText(ePermintaan.getTanggalPeminjaman());

        statuss = "dipinjam";
        holder.btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.updateStatus(ePermintaan.getIdPermintaan(),statuss).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                if (jsonObject.getString("status").equals("true")){
                                    Toast.makeText(mContext, "berhasil", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(mContext, BerandaPetugas.class);
                                    mContext.startActivity(intent);
                                    ((BerandaPetugas)mContext).finish();
                                }else {
                                    Toast.makeText(mContext, "gagal", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Log.d("onBindViewHolder: ", ePermintaan.toString());


//        Toast.makeText(mContext,""+id_permintaan,Toast.LENGTH_SHORT).show();
        statusbatal = "ditolak";
        holder.btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(mContext);
                builder1.setMessage("Apakah anda yakin ?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Iya",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mApiService.updateStatus(ePermintaan.getIdPermintaan(),statusbatal).enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()){
                                            try {
                                                JSONObject jsonObject = new JSONObject(response.body().string());
                                                if (jsonObject.getString("status").equals("true")){
                                                    Toast.makeText(mContext, "berhasil", Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(mContext, BerandaPetugas.class);
                                                    mContext.startActivity(intent);
                                                    ((BerandaPetugas)mContext).finish();
                                                }else {
                                                    Toast.makeText(mContext, "gagal", Toast.LENGTH_SHORT).show();
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
                                        Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                                    }
                                });
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

    @Override
    public int getItemCount() {
        return permintaanList.size();
    }

    public class PermintaanViewHolder extends RecyclerView.ViewHolder {
        public final TextView namaSiswa,kelas,namaBarang,stok,tglPinjamm,tglKembalii,status;
        public final Button btnAcc,btnBatal;

        public PermintaanViewHolder(@NonNull View itemView) {
            super(itemView);
            namaSiswa = itemView.findViewById(R.id.tvNamaSiswa);
            kelas = itemView.findViewById(R.id.tvKelas);
            namaBarang = itemView.findViewById(R.id.tvNBPermintaan);
            stok = itemView.findViewById(R.id.tvStokPermintaan);
            tglPinjamm = itemView.findViewById(R.id.tvTanggalPinjam);
            tglKembalii = itemView.findViewById(R.id.tvTanggalKembali);
            status = itemView.findViewById(R.id.tvStatusPermintaanP);
            btnAcc = itemView.findViewById(R.id.btnAcc);
            btnBatal = itemView.findViewById(R.id.btnBatal);
            cardView = itemView.findViewById(R.id.cvPermintaan);

//            id_permintaan = ePermintaan.getIdPermintaan();

                    }




        }
    }
