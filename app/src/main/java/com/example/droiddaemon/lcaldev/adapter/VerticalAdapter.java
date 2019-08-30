package com.example.droiddaemon.lcaldev.adapter;

import android.content.Context;
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

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {

    private ArrayList<AllServiceModel> mValues;
    private Context mContext;
    protected VerticalAdapter.VItemListener mListener;

    public VerticalAdapter(Context context, ArrayList<AllServiceModel> values, VerticalAdapter.VItemListener itemListener) {
        mValues = values;
        mContext = context;
        mListener = itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView title_textView, info_textView;
        private ImageView imageView;
        private RelativeLayout relativeLayout;
        private AllServiceModel item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            title_textView = (TextView) v.findViewById(R.id.title);
            info_textView = (TextView) v.findViewById(R.id.info);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);
        }

        public void setData(AllServiceModel item) {
            this.item = item;
            title_textView.setText(item.getName());
            info_textView.setText(item.getCategory());

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
    public VerticalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.vertical_liat_row_items, parent, false);
        return new VerticalAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VerticalAdapter.ViewHolder viewHolder, int position) {
        viewHolder.setData(mValues.get(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface VItemListener {
        void onItemClick(AllServiceModel item);
    }
}