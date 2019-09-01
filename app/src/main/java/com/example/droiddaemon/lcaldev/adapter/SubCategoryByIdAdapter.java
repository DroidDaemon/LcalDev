package com.example.droiddaemon.lcaldev.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.droiddaemon.lcaldev.R;
import com.example.droiddaemon.lcaldev.model.CategoryByIdModel;
import com.example.droiddaemon.lcaldev.model.CategoryByProductIdModel;

import java.util.List;

public class SubCategoryByIdAdapter extends RecyclerView.Adapter<SubCategoryByIdAdapter.MyViewHolder> {
    List<CategoryByIdModel.Child> categoryByIdModels;
    Context context;

    public SubCategoryByIdAdapter(Context context, List<CategoryByIdModel.Child> categoryByIdModels) {
        this.context = context;
        this.categoryByIdModels = categoryByIdModels;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.price_list_row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.titleName.setText("" + categoryByIdModels.get(position).getValue());
        holder.txtPrice.setText("\u20B9 " + categoryByIdModels.get(position).getBasePrice());
        holder.txtDescOne.setVisibility(View.GONE);
        holder.txtDescTwo.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Toast.makeText(context, categoryByIdModels.get(position).getParentId() + " is clicked   tttt", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryByIdModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleName;
        private TextView txtPrice;
        private TextView txtDescOne;
        private TextView txtDescTwo;

        public MyViewHolder(View itemView) {
            super(itemView);
            titleName = (TextView) itemView.findViewById(R.id.title_name);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price);
            txtDescOne = (TextView) itemView.findViewById(R.id.txt_desc_one);
            txtDescTwo = (TextView) itemView.findViewById(R.id.txt_desc_two);
        }
    }
}