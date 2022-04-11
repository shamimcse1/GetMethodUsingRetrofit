package com.example.getmethodusingretrofit.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.getmethodusingretrofit.helpers.DataResource;
import com.example.getmethodusingretrofit.model.Model;
import com.example.getmethodusingretrofit.network.ApiInterface;
import com.example.getmethodusingretrofit.network.RetrofitClient;
import com.orhanobut.logger.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    public static MovieRepository MovieRepository;
    public ApiInterface apiInterface;

    public static MovieRepository getInstance() {
        MovieRepository = new MovieRepository();

        return MovieRepository;
    }

    private MutableLiveData mutableLiveData = new MutableLiveData<DataResource<List<Model>>>();
    public LiveData<DataResource<List<Model>>> movieLiveData = mutableLiveData;

    public void getMovieList() {

        apiInterface = RetrofitClient.getRetrofit().create(ApiInterface.class);
        Call<List<Model>> call = apiInterface.getActualData();

        mutableLiveData.setValue(DataResource.loading());
        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if (response.code() == 200 && response.body() != null) {
                    mutableLiveData.postValue(DataResource.success(response.body()));
                } else {
                    mutableLiveData.postValue(DataResource.error(response.message()));
                }

            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {

            }
        });


    }
}
