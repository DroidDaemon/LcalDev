package com.example.droiddaemon.lcaldev.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.droiddaemon.lcaldev.R;
import com.example.droiddaemon.lcaldev.model.AllServiceModel;
import com.example.droiddaemon.lcaldev.model.AllServiceRequestModel;
import com.example.droiddaemon.lcaldev.model.Item;

import java.util.ArrayList;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private ArrayList<AllServiceModel> mValues;
    private Context mContext;
    protected ItemListener mListener;

    public HomeAdapter(Context context, ArrayList<AllServiceModel> values, ItemListener itemListener) {
        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private AllServiceModel item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
        }

        public void setData(AllServiceModel item) {
            this.item = item;
            textView.setText(item.getName());
//            imageView.setImageResource(item.drawable);
//            relativeLayout.setBackgroundColor();
            Glide.with(mContext)
                    .load(item.getImageUrl())
                    .fitCenter()
                    .override(100, 100)
                    .centerCrop()
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(AllServiceModel item);
    }
}