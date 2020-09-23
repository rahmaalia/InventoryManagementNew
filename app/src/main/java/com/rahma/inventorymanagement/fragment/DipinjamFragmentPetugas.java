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
import com.rahma.inventorymanagement.adapter.DipinjamAdapterPetugas;
import com.rahma.inventorymanagement.adapter.PermintaanPetugasAdapter;
import com.rahma.inventorymanagement.apihelper.BaseApiService;
import com.rahma.inventorymanagement.apihelper.RetrofitClient;
import com.rahma.inventorymanagement.model_entitity.E_DipinjamPetugas;
import com.rahma.inventorymanagement.model_entitity.E_permintaan;
import com.rahma.inventorymanagement.model_entitity.M_DipinjamPetugas;
import com.rahma.inventorymanagement.model_entitity.M_permintaan;
import com.rahma.inventorymanagement.sharedpref.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DipinjamFragmentPetugas extends Fragment {
    BaseApiService mApiService;
    DipinjamAdapterPetugas dipinjamAdapterPetugas;
    TextView namaSiswa,kelas,namaBarang,stok,tglPinjamm,tglKembalii,status,tvGone;
    RecyclerView rvDipinjam;
    List<E_DipinjamPetugas> dipinjamPetugas;
    SharedPrefManager sharedPrefManager;
    Button btnDiekembalian;
    int jurusan_id;

    public DipinjamFragmentPetugas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dipinjam_petugas,container,false);
        mApiService = RetrofitClient.getClient(RetrofitClient.BASE_URL_API).create(BaseApiService.class);
        sharedPrefManager = new SharedPrefManager(getContext());

        jurusan_id = sharedPrefManager.getSpIdjurusan();
        namaSiswa = view.findViewById(R.id.tvNamaSiswaDiminta);
        kelas = view.findViewById(R.id.tvKelasDiminta);
        namaBarang = view.findViewById(R.id.tvNBDiminta);
        stok = view.findViewById(R.id.tvStokPermintaanDiminta);
        tglPinjamm = view.findViewById(R.id.tvTanggalPinjamDiminta);
        tglKembalii = view.findViewById(R.id.tvTanggalKembaliDiminta);
        status = view.findViewById(R.id.tvStatusDiminta);
//        btnDikembalikan = view.findViewById(R.id.btnDikembalikan);
        rvDipinjam = view.findViewById(R.id.rvDipinjamPetugas);
        tvGone = view.findViewById(R.id.tv_goneDipinjamPetugas);

        getPermintaan();
        mApiService.getDipinjam(jurusan_id).enqueue(new Callback<M_DipinjamPetugas>() {
            @Override
            public void onResponse(Call<M_DipinjamPetugas> call, Response<M_DipinjamPetugas> response) {
                if (response.isSuccessful()){
//                    Toast.makeText(getActivity(),"sukses",Toast.LENGTH_SHORT).show();
                    dipinjamPetugas = response.body().getData();
                    if (response.body().getData().isEmpty()){
                        tvGone.setVisibility(View.VISIBLE);
                    }

                    dipinjamAdapterPetugas = new DipinjamAdapterPetugas(getActivity(),dipinjamPetugas);
                    rvDipinjam.setAdapter(dipinjamAdapterPetugas);
                    dipinjamAdapterPetugas.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(),"gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<M_DipinjamPetugas> call, Throwable t) {
                Toast.makeText(getActivity(),"koneksi jelek",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void getPermintaan() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        rvDipinjam.setLayoutManager(mLayoutManager);
        rvDipinjam.setItemAnimator(new DefaultItemAnimator());
    }

}
