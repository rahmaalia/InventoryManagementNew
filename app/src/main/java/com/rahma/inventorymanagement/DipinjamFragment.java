package com.rahma.inventorymanagement;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    TextView tvNamaBarang,tvStok,tvTglPinjam,tvTanggalKembali;
    RecyclerView rvDipinjam;
    String status;
    int akun_id;

    public DipinjamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dipinjam, container, false);
        getPinjam();
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        status="dipinjam";
        rvDipinjam = view.findViewById(R.id.rvDipinjam);
        akun_id= 1;
        mApiService.getPinjam(akun_id).enqueue(new Callback<EDipinjam>() {
            @Override
            public void onResponse(Call<EDipinjam> call, Response<EDipinjam> response) {
                if (response.isSuccessful()){
                     List<M_dipinjam> dipinjams = response.body().getData();

                     rvDipinjam.setAdapter(new DipinjamAdapter(mContext,dipinjams));
                     dipinjamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<EDipinjam> call, Throwable t) {

            }
        });

        return view;

    }

    private void getPinjam() {
        dipinjamAdapter
    }

}
