package com.example.getmethodusingretrofit.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.getmethodusingretrofit.helpers.DataResource;
import com.example.getmethodusingretrofit.model.Model;
import com.example.getmethodusingretrofit.repository.MovieRepository;
import com.orhanobut.logger.Logger;

import java.util.List;


public class MovieViewModel extends ViewModel {

    MovieRepository repository = new MovieRepository();
    public LiveData<DataResource<List<Model>>> movieLiveData = repository.movieLiveData;

    public void loadMovieList() {
        repository.getMovieList();
        Logger.d("loadMovieList");
    }


}


