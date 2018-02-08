package com.andriginting.internshiptest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andri Ginting on 2/7/2018.
 */

public class WisataModel{
    @SerializedName("id_data")
    public String dataId;
    @SerializedName("title")
    public String title;
    @SerializedName("description")
    public String description;
    @SerializedName("url_image")
    public String imageUrl;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("category")
    public String category;
    @SerializedName("Location")
    public String Location;

    public WisataModel(String dataId, String title, String description, String imageUrl, String createdAt, String category, String location) {
        this.dataId = dataId;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.category = category;
        Location = location;
    }

    public String getDataId() {
        return dataId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return Location;
    }
}
