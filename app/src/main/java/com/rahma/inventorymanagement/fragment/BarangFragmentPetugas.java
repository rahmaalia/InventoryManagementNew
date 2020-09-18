package com.rahma.inventorymanagement.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rahma.inventorymanagement.R;
import com.rahma.inventorymanagement.adapter.BarangPetugasAdapter;
import com.rahma.inventorymanagement.adapter.PeminjamanAdapter;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_peminjaman;
import com.rahma.inventorymanagement.model_entitity.M_peminjaman;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class BarangFragmentPetugas extends Fragment {
    BaseApiService mApiService;
    BarangPetugasAdapter barangPetugasAdapter;
    TextView tvNamaBarang,tvStok;
    RecyclerView rvPeminjaman;
    List<E_peminjaman> peminjamans;
    int jurusan_id,idjurusan;
    SharedPrefManager sharedPrefManager;
    Context mContext;

    public BarangFragmentPetugas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_barang_fragment_petugas,container,false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        sharedPrefManager = new SharedPrefManager(getContext());
        jurusan_id = sharedPrefManager.getSpIdjurusan();

        tvNamaBarang = view.findViewById(R.id.tvNamaBarangPetugas);
        tvStok = view.findViewById(R.id.tvStokPetugas);
        rvPeminjaman = view.findViewById(R.id.rvBarangPetugas);

        getPeminjaman();
        mApiService.getPeminjaman(jurusan_id).enqueue(new Callback<M_peminjaman>() {
            @Override
            public void onResponse(Call<M_peminjaman> call, Response<M_peminjaman> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(),"sukses",Toast.LENGTH_SHORT).show();
                    peminjamans = response.body().getData();

                    barangPetugasAdapter = new BarangPetugasAdapter(getActivity(),peminjamans);
                    Log.d("onResponse", peminjamans.toString());
                    rvPeminjaman.setAdapter(barangPetugasAdapter);
                    barangPetugasAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(),"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<M_peminjaman> call, Throwable t) {
                Toast.makeText(getActivity(),"koneksi jelek",Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    private void getPeminjaman() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvPeminjaman.setLayoutManager(mLayoutManager);
        rvPeminjaman.setItemAnimator(new DefaultItemAnimator());
    }

}
