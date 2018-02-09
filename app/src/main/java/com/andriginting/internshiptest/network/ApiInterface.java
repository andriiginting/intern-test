package com.andriginting.internshiptest.network;

import com.andriginting.internshiptest.model.ApiResponse;
import com.andriginting.internshiptest.model.LoginModel;
import com.andriginting.internshiptest.model.RegisterModel;
import com.andriginting.internshiptest.model.UploadDataModel;
import com.andriginting.internshiptest.model.WisataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Andri Ginting on 2/2/2018.
 */

public interface ApiInterface {
    //untuk login
    @POST("post/user/login")
    Call<ApiResponse> getLogin(@Body LoginModel loginModel);

    //untuk sign up
    @FormUrlEncoded
    @POST("post/user/account")
    Call<ApiResponse> getRegister(@Body RegisterModel registerModel);

    //untuk upload data ke API
    @Multipart
    @POST("post/data/upload")
    Call<ApiResponse> getUpload(@Body UploadDataModel uploadDataModel);

    //untuk filter data kategori 1,2,3
    @GET("get/filter/dataalam?")
    Call<WisataResponse> getKategoriWisata(@Query("kategori") String kategoriId);

    //untuk ambil data detail tempat wisata
    @GET("get/detil/dataalam?")
    Call<WisataResponse> getDetailData(@Query("itemid") String id);
}
