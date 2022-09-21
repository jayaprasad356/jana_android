package com.greymatter.earnjoy.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.greymatter.earnjoy.JoblistActivity;
import com.greymatter.earnjoy.R;
import com.greymatter.earnjoy.helper.Session;
import com.greymatter.earnjoy.model.Category;

import java.util.ArrayList;


public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Category> categories;
    Session session;

    public CategoryAdapter(Activity activity, ArrayList<Category> categories) {
        this.activity = activity;
        this.categories = categories;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.category_item, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Category category = categories.get(position);


        holder.tvName.setText(category.getName());
        Glide.with(activity).load(category.getImage()).placeholder(R.drawable.applogo).into(holder.imgCategory);







        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, JoblistActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final TextView tvName;
        final ImageView imgCategory;


        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);

            imgCategory = itemView.findViewById(R.id.ivImg);
            tvName = itemView.findViewById(R.id.tvName);




        }
    }
}

