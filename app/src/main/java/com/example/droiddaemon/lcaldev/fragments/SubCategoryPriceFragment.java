package com.example.droiddaemon.lcaldev.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.droiddaemon.lcaldev.Controller;
import com.example.droiddaemon.lcaldev.R;
import com.example.droiddaemon.lcaldev.SharedApplication;
import com.example.droiddaemon.lcaldev.adapter.SubCategoryAdapter;
import com.example.droiddaemon.lcaldev.adapter.SubCategoryByIdAdapter;
import com.example.droiddaemon.lcaldev.listeners.CategoryByIdListener;
import com.example.droiddaemon.lcaldev.model.CategoryByIdModel;
import com.example.droiddaemon.lcaldev.model.CategoryByProductIdModel;

import java.util.List;


public class SubCategoryPriceFragment extends Fragment implements CategoryByIdListener {

    View view;
    private Controller controller;
    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_subcategeryprice, container, false);
        controller = ((SharedApplication) getActivity().getApplication()).getAppController();

        Bundle bundle = getArguments();

        if(bundle != null){
            String productId = bundle.getString("productId");
            productId = "20";
            controller.fetchCategoryById(context,this,""+productId);
        }




        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        this.context = (Activity) context;
    }

    void setAdapter(List<CategoryByIdModel.Child> categoryByIdModels){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        SubCategoryByIdAdapter customAdapter = new SubCategoryByIdAdapter(getActivity(), categoryByIdModels);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void onFetchCategoryByIdeSuccess(List<CategoryByIdModel> categoryByIdModels) {
        Toast.makeText(context, categoryByIdModels.size() + " is clicked", Toast.LENGTH_SHORT).show();
        List<CategoryByProductIdModel.Child> children;
        setAdapter(categoryByIdModels.get(0).getChildren());
    }

    @Override
    public void onFetchCategoryByIdFailure(String msg) {

    }
}