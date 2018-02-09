package com.andriginting.internshiptest.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Andri Ginting on 2/2/2018.
 */

public class ApiClient {
    public static final String BASE_URL = "isi";
    public static final String IMAGE_URL = "isi";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
