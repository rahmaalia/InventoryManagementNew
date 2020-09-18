package com.rahma.inventorymanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.BerandaPetugas;
import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
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

public class PermintaanPetugasAdapter2 extends RecyclerView.Adapter<PermintaanPetugasAdapter2.GridViewHolder> {
    private List<E_permintaan> permintaanList;
    Context mContext;
    CardView cardView;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    String statuss,tglmasuk;
    int id_permintaan;
    Button btnAcc;

    public PermintaanPetugasAdapter2(Context mContext, List<E_permintaan> ePermintaans){
        this.mContext = mContext;
        permintaanList = ePermintaans;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_permintaan_petugas,parent,false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        return new PermintaanPetugasAdapter2.GridViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PermintaanPetugasAdapter2.GridViewHolder holder, int position) {
        final E_permintaan ePermintaan = permintaanList.get(position);
        holder.namaSiswa.setText(ePermintaan.getUsername());
        holder.kelas.setText(ePermintaan.getAngkatan());
        holder.namaBarang.setText(ePermintaan.getNamaBarang());
        holder.stok.setText(String.valueOf(ePermintaan.getJumlahPinjam()));

        holder.tglKembalii.setText(ePermintaan.getTanggalPengembalian());
        holder.status.setText(ePermintaan.getStatusPermintaan());
        holder.tglPinjamm.setText(ePermintaan.getTanggalPeminjaman());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,""+ePermintaan.getIdPermintaan(),Toast.LENGTH_SHORT).show();
            }
        });
        statuss = "dipinjam";
        holder.btnAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION){
//                        Toast.makeText(mContext,""+ePermintaan.getNamaBarang(),Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public int getItemCount() {
        return permintaanList.size();
    }
    public class GridViewHolder extends RecyclerView.ViewHolder {
     TextView namaSiswa,kelas,namaBarang,stok,tglPinjamm,tglKembalii,status;
        public final Button btnAcc,btnBatal;
        LinearLayout linearLayout;

        public GridViewHolder(@NonNull View itemView) {
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
            linearLayout=itemView.findViewById(R.id.ly);
            cardView = itemView.findViewById(R.id.cvPermintaan);

//            id_permintaan = ePermintaan.getIdPermintaan();

        }




    }
}
