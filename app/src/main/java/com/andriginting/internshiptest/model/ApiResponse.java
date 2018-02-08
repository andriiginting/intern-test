package com.andriginting.internshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andri Ginting on 2/2/2018.
 */

public class ApiResponse {
    @SerializedName("status")
    public String status;
    @SerializedName("status_code")
    public String statusCode;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public detail[] details;
}
