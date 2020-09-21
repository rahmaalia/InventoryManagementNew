package com.rahma.inventorymanagement.apihelper;

import com.rahma.inventorymanagement.model_entitity.EDipinjam;
import com.rahma.inventorymanagement.model_entitity.M_DipinjamPetugas;
import com.rahma.inventorymanagement.model_entitity.M_History;
import com.rahma.inventorymanagement.model_entitity.M_HistoryPetugas;
import com.rahma.inventorymanagement.model_entitity.M_peminjaman;
import com.rahma.inventorymanagement.model_entitity.M_permintaan;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("kata_sandi") String kata_sandi);

    @FormUrlEncoded
    @POST("inputPeminjaman")
    Call<ResponseBody> peminjamanRequest (@Field("barang_id") int barang_id,
                                          @Field("akun_id") int akun_id,
                                          @Field("kelas_id") int kelas_id,
                                          @Field("jurusan_id") int jurusan_id,
                                          @Field("status_permintaan") String status_permintaan,
                                          @Field("jumlah_pinjam") String jumlah_pinjam ,
                                          @Field("tanggal_peminjaman") String tanggal_peminjaman,
                                          @Field("tanggal_pengembalian") String tanggal_pengembalian);


    @GET("getPeminjaman/{akun_id}")
    Call<EDipinjam> getPinjam (@Path("akun_id") int akun_id);

    @GET("barang/{jurusan_id}")
    Call<M_peminjaman> getPeminjaman(@Path("jurusan_id")int jurusan_id);

    @GET("getPermintaan/{jurusan_id}")
    Call<M_permintaan> getPermintaan(@Path("jurusan_id")int jurusan_id);

    @GET("getDipinjam/{jurusan_id}")
    Call<M_DipinjamPetugas> getDipinjam(@Path("jurusan_id")int jurusan_id);

    @FormUrlEncoded
    @PUT("Permintaan/{id}")
    Call<ResponseBody> updateStatus(@Path("id")int id,
                                    @Field("status_permintaan") String status_permintaan);

    @GET("history_siswa/{akun_id}")
    Call<M_History> getHistory (@Path("akun_id") int akun_id);

    @GET("history_petugas/{jurusan_id}")
    Call<M_HistoryPetugas> getHistoryPetugas(@Path("jurusan_id")int jurusan_id);

    @FormUrlEncoded
    @PUT("Pengembalian/{id}")
    Call<ResponseBody> updateStatusDikembalikan(@Path("id")int id,
                                                @Field("status_permintaan") String status_permintaan);

    @FormUrlEncoded
    @PUT("Ditolak/{id}")
    Call<ResponseBody> updateStatusDitolak (@Path("id")int id,
                                           @Field("status_permintaan") String status_permintaan);

}
