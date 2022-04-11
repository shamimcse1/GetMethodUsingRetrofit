package com.example.getmethodusingretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.getmethodusingretrofit.R;
import com.example.getmethodusingretrofit.adapter.MovieAdapter;
import com.example.getmethodusingretrofit.databinding.ActivityMainBinding;
import com.example.getmethodusingretrofit.helpers.DataResource;
import com.example.getmethodusingretrofit.model.Model;
import com.example.getmethodusingretrofit.view_model.MovieViewModel;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MovieViewModel viewModel;
    ActivityMainBinding binding;
    MovieAdapter adapter;
    private ProgressDialog progressDialog;
    List<Model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        Logger.addLogAdapter(new AndroidLogAdapter());
        viewModel.loadMovieList();

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");

        initView();
        getData();
        Logger.d("onCreate");
    }

    private void getData() {
        viewModel.movieLiveData.observe(this, new Observer<DataResource<List<Model>>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(DataResource<List<Model>> modelDataResource) {
                switch (modelDataResource.getStatus()) {
                    case SUCCESS:

                        adapter.setModels(modelDataResource.getActualData());
                        binding.RecyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                        break;
                    case LOADING:
                        Log.e("data", "");
                        progressDialog.show();
                    case ERROR:
                        Log.e("data", "");
                        progressDialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
        });
        Logger.d("getData");
    }

    private void initView() {
        binding.RecyclerView.setHasFixedSize(true);
        binding.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new MovieAdapter(this);
        Logger.d("initView");

    }
}