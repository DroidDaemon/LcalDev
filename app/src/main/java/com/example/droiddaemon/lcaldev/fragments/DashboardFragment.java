package com.example.droiddaemon.lcaldev.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.droiddaemon.lcaldev.Controller;
import com.example.droiddaemon.lcaldev.R;
import com.example.droiddaemon.lcaldev.SharedApplication;
import com.example.droiddaemon.lcaldev.activity.MainActivity;
import com.example.droiddaemon.lcaldev.adapter.FruitAdapter;
import com.example.droiddaemon.lcaldev.adapter.HomeAdapter;
import com.example.droiddaemon.lcaldev.listeners.NetworkDataListener;
import com.example.droiddaemon.lcaldev.model.H_Recycler_fruit;
import com.example.droiddaemon.lcaldev.model.Item;
import com.example.droiddaemon.lcaldev.model.RetroItem;
import com.example.droiddaemon.lcaldev.retrofit.APIInterface;
import com.example.droiddaemon.lcaldev.retrofit.RetrofitClientInstance;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends android.support.v4.app.Fragment implements HomeAdapter.ItemListener, NetworkDataListener {
    Activity activity;
    private RecyclerView gridRecyclerView;
    private RecyclerView h_recyclerView;
    private ArrayList<Item> arrayList;
    ProgressDialog progressDoalog;
    HomeAdapter adapter;
    double lat = 28.464926;
    double lng = 77.056155;
    private ArrayList<H_Recycler_fruit> imageModelArrayList;
    private FruitAdapter adapterFruit;
    private Controller controller;
    private Context context;

    private int[] myImageList = new int[]{R.drawable.beer, R.drawable.ferrari, R.drawable.battle, R.drawable.three_d, R.drawable.jetpack_joyride, R.drawable.terraria, R.drawable.beer};
    private String[] myImageNameList = new String[]{"Apple", "Mango", "Strawberry", "Pineapple", "Orange", "Blueberry", "Watermelon"};

    public DashboardFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        this.context = (Activity) context;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.content_main, container, false);
        controller = ((SharedApplication) getActivity().getApplication()).getAppController();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        gridRecyclerView = (RecyclerView) view.findViewById(R.id.gridRecyclerView);
        h_recyclerView = (RecyclerView) view.findViewById(R.id.h_recyclerView);
        arrayList = new ArrayList<>();
        imageModelArrayList = eatFruits();
        /////
        adapterFruit = new FruitAdapter(getActivity(), imageModelArrayList);
        h_recyclerView.setAdapter(adapterFruit);
        h_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        arrayList.add(new Item("Item 1", R.drawable.beer, "#000000"));
        arrayList.add(new Item("Item 2", R.drawable.ferrari, "#000000"));
        arrayList.add(new Item("Item 3", R.drawable.battle, "#000000"));
        arrayList.add(new Item("Item 4", R.drawable.jetpack_joyride, "#000000"));
        arrayList.add(new Item("Item 5", R.drawable.three_d, "#000000"));
        arrayList.add(new Item("Item 6", R.drawable.terraria, "#000000"));
        arrayList.add(new Item("Item 7", R.drawable.terraria, "#000000"));
        arrayList.add(new Item("More Items", R.drawable.beer, "#000000"));

        adapter = new HomeAdapter(getActivity(), arrayList, this);
        gridRecyclerView.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4, GridLayoutManager.VERTICAL, false);
        gridRecyclerView.setLayoutManager(manager);
    }

    private ArrayList<H_Recycler_fruit> eatFruits() {

        ArrayList<H_Recycler_fruit> list = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            H_Recycler_fruit fruitModel = new H_Recycler_fruit();
            fruitModel.setName(myImageNameList[i]);
            fruitModel.setImage_drawable(myImageList[i]);
            list.add(fruitModel);
        }
        return list;
    }

    @Override
    public void onItemClick(Item item) {
        Toast.makeText(getActivity(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
//        controller.fetchNetworkData(context, this);
       getAddress();
    }

    private void generateDataList(List<RetroItem> body) {
        Log.e("Response", body.toString());
        arrayList.add(new Item("Network Data", R.drawable.beer, "#000000"));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fetchNetworkDataSuccess(List<RetroItem> body) {
        generateDataList(body);
    }

    @Override
    public void fetchNetworkDataFailure(String msg) {
        Log.e("Response", msg);

    }


    public String getAddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getActivity(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            String add = city+" "+state+" - "+postalCode;
            return add;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
