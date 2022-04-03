package com.example.getmethodusingretrofit.network;

import com.example.getmethodusingretrofit.helpers.DataResource;
import com.example.getmethodusingretrofit.model.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/movielist.json")
    Call<List<Model>> getActualData();
}
