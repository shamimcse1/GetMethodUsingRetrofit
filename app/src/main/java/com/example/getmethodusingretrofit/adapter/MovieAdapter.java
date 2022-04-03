package com.example.getmethodusingretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.getmethodusingretrofit.R;
import com.example.getmethodusingretrofit.databinding.MovieItemBinding;
import com.example.getmethodusingretrofit.helpers.Constant;
import com.example.getmethodusingretrofit.model.Model;
import com.example.getmethodusingretrofit.view.DetailsActivity;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    private Context context;
    private List<Model> models;

    public MovieAdapter(Context context, List<Model> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MovieItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = models.get(position);
        if (model != null) {
            try {
                holder.bind(position);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.KEY, model);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        MovieItemBinding itemBinding;

        public MyViewHolder(@NonNull MovieItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }

        public void bind(int position) {
            itemBinding.nameTxt.setText(models.get(position).getName());
            Glide.with(context).load(models.get(position).getImageUrl()).into(itemBinding.imageView);
        }
    }
}
