package com.example.droiddaemon.lcaldev.fragments;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import android.support.v4.app.Fragment;

import com.example.droiddaemon.lcaldev.Controller;
import com.example.droiddaemon.lcaldev.R;
import com.example.droiddaemon.lcaldev.SharedApplication;
import com.example.droiddaemon.lcaldev.adapter.SubCategoryAdapter;
import com.example.droiddaemon.lcaldev.listeners.CategoryByProductIdListener;
import com.example.droiddaemon.lcaldev.model.CategoryByProductIdModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SubCategoryFragment extends Fragment implements CategoryByProductIdListener {

    private Controller controller;
    View view;
    private Context context;
    private Activity activity;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_subcategery, container, false);
        controller = ((SharedApplication) getActivity().getApplication()).getAppController();

        Bundle bundle = getArguments();

        if(bundle != null){
            String productId = bundle.getString("productId");
            productId = "7e564a0b-61c8-4e19-8c6e-bbaadafcf55b";
            controller.fetchCategoryByProductId(context,this,""+productId);
        }




        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        setAdapter();


        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        this.context = (Activity) context;
    }

    void setAdapter(List<CategoryByProductIdModel.Child> categoryByProductIdModels){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        SubCategoryAdapter subCategoryAdapter = new SubCategoryAdapter(getActivity(), categoryByProductIdModels);
        recyclerView.setAdapter(subCategoryAdapter);
    }


    @Override
    public void onFetchCategoryByProductIdeSuccess(List<CategoryByProductIdModel> categoryByProductIdModels) {
        Toast.makeText(getContext(), categoryByProductIdModels.size() + " is clicked", Toast.LENGTH_SHORT).show();
        List<CategoryByProductIdModel.Child> children;
        setAdapter(categoryByProductIdModels.get(0).getChildren());


    }

    @Override
    public void onFetchCategoryByProductIdFailure(String msg) {

    }
}