package com.rahma.inventorymanagement.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.adapter.PermintaanPetugasAdapter;
import com.rahma.inventorymanagement.adapter.PermintaanPetugasAdapter2;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_permintaan;
import com.rahma.inventorymanagement.model_entitity.M_permintaan;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class PermintaanFragmentPetugas extends Fragment {
    BaseApiService mApiService;
    PermintaanPetugasAdapter permintaanPetugasAdapter;
    TextView namaSiswa,kelas,namaBarang,stok,tglPinjamm,tglKembalii,status,tvGone;
    RecyclerView rvPermintaan;
    List<E_permintaan> permintaans;
    E_permintaan ePermintaan;
    SharedPrefManager sharedPrefManager;
    Button btnACC,btnBatal;
    String statuss,statusss;
    int jurusan_id;

    public PermintaanFragmentPetugas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_permintaan_petugas,container,false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        sharedPrefManager = new SharedPrefManager(getContext());

        jurusan_id = sharedPrefManager.getSpIdjurusan();

        statusss = "dipinjam";

        namaSiswa = view.findViewById(R.id.tvNamaSiswa);
        kelas = view.findViewById(R.id.tvKelas);
        namaBarang = view.findViewById(R.id.tvNBPermintaan);
        stok = view.findViewById(R.id.tvStokPermintaan);
        tglPinjamm = view.findViewById(R.id.tvTanggalPinjam);
        tglKembalii = view.findViewById(R.id.tvTanggalKembali);
        status = view.findViewById(R.id.tvStatusPermintaanP);
        rvPermintaan = view.findViewById(R.id.rvPermintaan);
        btnACC = view.findViewById(R.id.btnAcc);
        btnBatal = view.findViewById(R.id.btnBatal);
        tvGone = view.findViewById(R.id.tv_gonePermintaan);
        
        getPermintaan();
//        statuss = ePermintaan.getStatusPermintaan();
        mApiService.getPermintaan(jurusan_id).enqueue(new Callback<M_permintaan>() {
            @Override
            public void onResponse(Call<M_permintaan> call, Response<M_permintaan> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(),"sukses",Toast.LENGTH_SHORT).show();
                    permintaans = response.body().getData();
                    if (response.body().getData().isEmpty()){
                        tvGone.setVisibility(View.VISIBLE);
                    }

                    permintaanPetugasAdapter = new PermintaanPetugasAdapter(getContext(),permintaans);
                    rvPermintaan.setAdapter(permintaanPetugasAdapter);
                    permintaanPetugasAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(),"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<M_permintaan> call, Throwable t) {
                Toast.makeText(getActivity(),"koneksi jelek",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void getPermintaan() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvPermintaan.setLayoutManager(mLayoutManager);
        rvPermintaan.setItemAnimator(new DefaultItemAnimator());
    }

}
