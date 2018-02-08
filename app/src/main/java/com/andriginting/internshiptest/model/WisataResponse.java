package com.andriginting.internshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andri Ginting on 2/7/2018.
 */

public class WisataResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("status_code")
    public String status_code;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public WisataModel[] listDataWisata;

    public WisataModel[] getListData(){ return listDataWisata;}
}
