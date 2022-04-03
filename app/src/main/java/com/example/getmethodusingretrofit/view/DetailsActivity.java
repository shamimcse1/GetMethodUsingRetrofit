package com.example.getmethodusingretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.getmethodusingretrofit.R;
import com.example.getmethodusingretrofit.databinding.ActivityDetailsBinding;
import com.example.getmethodusingretrofit.helpers.Constant;
import com.example.getmethodusingretrofit.model.Model;
import com.orhanobut.logger.Logger;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Model model = (Model) bundle.getSerializable(Constant.KEY);

        Logger.d(model);
        if (model != null) {

            binding.nameTxt.setText("Name : " + model.getName());
            binding.categoryTxt.setText("Category : "+model.getCategory());
            binding.descTxt.setText("Description : "+model.getDesc());
            Glide.with(DetailsActivity.this).load(model.getImageUrl()).into(binding.imageView);
        }
    }
}