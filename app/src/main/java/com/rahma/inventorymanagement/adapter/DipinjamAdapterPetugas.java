package com.rahma.inventorymanagement.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.rahma.inventorymanagement.BerandaPetugas;
import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_DipinjamPetugas;
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

public class DipinjamAdapterPetugas extends RecyclerView.Adapter<DipinjamAdapterPetugas.DipinjamPetugasViewHolder> {
    private List<E_DipinjamPetugas> dipinjamPetugasList;
    E_DipinjamPetugas eDipinjamPetugas;
    Context mContext;
    CardView cardView;
    SharedPrefManager sharedPrefManager;
    BaseApiService mApiService;
    String status;

    public DipinjamAdapterPetugas(Context mContext, List<E_DipinjamPetugas> eDipinjamPetugases){
        this.mContext = mContext;
        dipinjamPetugasList = eDipinjamPetugases;
    }

    @NonNull
    @Override
    public DipinjamPetugasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dipinjam_petugas,parent,false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        return new DipinjamAdapterPetugas.DipinjamPetugasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DipinjamAdapterPetugas.DipinjamPetugasViewHolder holder, int position) {
        eDipinjamPetugas = dipinjamPetugasList.get(position);
        holder.namaSiswa.setText(eDipinjamPetugas.getUsername());
        holder.kelas.setText(eDipinjamPetugas.getAngkatan());
        holder.namaBarang.setText(eDipinjamPetugas.getNamaBarang());
        holder.stok.setText(String.valueOf(eDipinjamPetugas.getJumlahPinjam()));
        holder.tglPinjamm.setText(String.valueOf(eDipinjamPetugas.getTanggalPeminjaman()));
//        holder.tglPinjamm.setText(ePermintaan.getTanggalPeminjaman());
        holder.tglKembalii.setText(eDipinjamPetugas.getTanggalPengembalian());
        holder.status.setText(eDipinjamPetugas.getStatusPermintaan());

        status = "dikembalikan";
        holder.btnDikembalikan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiService.updateStatusDikembalikan(eDipinjamPetugas.getIdPermintaan(),status).enqueue(new Callback<ResponseBody>() {
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
    }

    @Override
    public int getItemCount() {
        return dipinjamPetugasList.size();
    }

    public class DipinjamPetugasViewHolder extends RecyclerView.ViewHolder {
        public final TextView namaSiswa,kelas,namaBarang,stok,tglPinjamm,tglKembalii,status;
        public final Button btnDikembalikan;
        public DipinjamPetugasViewHolder(@NonNull View itemView) {
            super(itemView);
            namaSiswa = itemView.findViewById(R.id.tvNamaSiswaDiminta);
            kelas = itemView.findViewById(R.id.tvKelasDiminta);
            namaBarang = itemView.findViewById(R.id.tvNBDiminta);
            stok = itemView.findViewById(R.id.tvStokPermintaanDiminta);
            tglPinjamm = itemView.findViewById(R.id.tvTanggalPinjamDiminta);
            tglKembalii = itemView.findViewById(R.id.tvTanggalKembaliDiminta);
            status = itemView.findViewById(R.id.tvStatusDiminta);
            btnDikembalikan = itemView.findViewById(R.id.btnDikembalikan);
        }
    }
}
