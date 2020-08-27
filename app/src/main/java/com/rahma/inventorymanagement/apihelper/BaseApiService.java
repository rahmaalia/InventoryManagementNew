package com.rahma.inventorymanagement.apihelper;

import com.rahma.inventorymanagement.model_entitity.EDipinjam;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("kata_sandi") String kata_sandi);

    @GET("pinjam/{akun_id}")
    Call<EDipinjam> getPinjam (@Path("akun_id") int akun_id);
}
