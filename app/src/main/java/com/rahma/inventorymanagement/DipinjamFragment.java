package com.rahma.inventorymanagement;


import android.content.Context;
import android.content.Intent;
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

import com.rahma.inventorymanagement.adapter.DipinjamAdapter;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.EDipinjam;
import com.rahma.inventorymanagement.model_entitity.M_dipinjam;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DipinjamFragment extends Fragment {
    Context mContext;
    BaseApiService mApiService;
    private ArrayList<M_dipinjam> listDipinjam = new ArrayList<>();
    SharedPrefManager sharedPrefManager;
    DipinjamAdapter dipinjamAdapter;
    TextView tvNamaBarang,tvStok,tvTglPinjam,tvTanggalKembali,tvGone;
    RecyclerView rvDipinjam;
    String status;
    int akun_id,id_akun;
    List<M_dipinjam> dipinjams;

    public DipinjamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dipinjam, container, false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);

        final Intent intent = getActivity().getIntent();
        akun_id = intent.getIntExtra("id_akun",1);

        tvNamaBarang = view.findViewById(R.id.tvNamabarang);
        tvStok = view.findViewById(R.id.tvStok);
        tvTglPinjam= view.findViewById(R.id.tvTglPinjam);
        tvTanggalKembali = view.findViewById(R.id.tvTglKembali);
        tvGone = view.findViewById(R.id.tv_gone);
        rvDipinjam = view.findViewById(R.id.rvDipinjam);

        getPinjam();
        mApiService.getPinjam(akun_id).enqueue(new Callback<EDipinjam>() {
            @Override
            public void onResponse(Call<EDipinjam> call, Response<EDipinjam> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(),"sukses",Toast.LENGTH_SHORT).show();
                    dipinjams = response.body().getData();
                   if (response.body().getData().isEmpty()){
                       tvGone.setVisibility(View.VISIBLE);
                   }
                   dipinjamAdapter = new DipinjamAdapter(getActivity(),dipinjams);
                    Log.d( "onResponse: " , dipinjams.toString());
                     rvDipinjam.setAdapter(dipinjamAdapter);
                     dipinjamAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(),"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EDipinjam> call, Throwable t) {
                Toast.makeText(getActivity(),"koneksi jelek",Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    private void getPinjam() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvDipinjam.setLayoutManager(mLayoutManager);
        rvDipinjam.setItemAnimator(new DefaultItemAnimator());
    }
}

