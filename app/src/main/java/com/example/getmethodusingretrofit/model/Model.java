package com.example.getmethodusingretrofit.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model implements Serializable {
    @SerializedName("name")
    public String name;
    @SerializedName("desc")
    public String desc;
    @SerializedName("category")
    public String category;
    @SerializedName("imageUrl")
    public String imageUrl;

    public Model(String name, String desc, String category, String imageUrl) {
        this.name = name;
        this.desc = desc;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
