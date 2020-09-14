package com.rahma.inventorymanagement.apihelper;

import com.google.gson.Gson;
import com.rahma.inventorymanagement.apihelper.BaseApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//class ini berfungsi untuk mendeklarasikan retrofit2

public class RetrofitClient {

    private static Retrofit retrofit = null;
    public static final String BASE_URL_API = "http://192.168.1.3:80/api/";

    public static Retrofit getClient(String baseUrl){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
