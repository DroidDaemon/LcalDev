package com.example.droiddaemon.lcaldev.adapter;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.droiddaemon.lcaldev.R;
import com.example.droiddaemon.lcaldev.fragments.SubCategoryFragment;
import com.example.droiddaemon.lcaldev.fragments.SubCategoryPriceFragment;
import com.example.droiddaemon.lcaldev.model.CategoryByProductIdModel;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.MyViewHolder> {
    List<CategoryByProductIdModel.Child> categoryByProductIdModels;
    Context context;
    public SubCategoryAdapter(Context context, List<CategoryByProductIdModel.Child> categoryByProductIdModels) {
        this.context = context;
        this.categoryByProductIdModels = categoryByProductIdModels;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcatageryitem, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name.setText(""+categoryByProductIdModels.get(position).getValue());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, categoryByProductIdModels.get(position).getParentId()+ " is clicked   hhhh", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryByProductIdModels.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
