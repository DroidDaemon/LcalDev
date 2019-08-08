package com.example.droiddaemon.lcaldev.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.droiddaemon.lcaldev.R;
import com.example.droiddaemon.lcaldev.adapter.FruitAdapter;
import com.example.droiddaemon.lcaldev.adapter.HomeAdapter;
import com.example.droiddaemon.lcaldev.fragments.DashboardFragment;
import com.example.droiddaemon.lcaldev.model.H_Recycler_fruit;
import com.example.droiddaemon.lcaldev.model.Item;
import com.example.droiddaemon.lcaldev.model.RetroItem;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements HomeAdapter.ItemListener {

    private RecyclerView gridRecyclerView;
    private RecyclerView h_recyclerView;
    private ArrayList<Item> arrayList;
    ProgressDialog progressDoalog;
    HomeAdapter adapter;
    double lat = 28.4625;
    double lng = 77.0564;
    private ArrayList<H_Recycler_fruit> imageModelArrayList;
    private FruitAdapter adapterFruit;
    private int[] myImageList = new int[]{R.drawable.beer, R.drawable.ferrari, R.drawable.battle, R.drawable.three_d, R.drawable.jetpack_joyride, R.drawable.terraria, R.drawable.beer};
    private String[] myImageNameList = new String[]{"Apple", "Mango", "Strawberry", "Pineapple", "Orange", "Blueberry", "Watermelon"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorDark));


        Fragment DashboardFragment = new DashboardFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.home_fragment_container, DashboardFragment);
        fragmentTransaction.commit();


//        gridRecyclerView = (RecyclerView) findViewById(R.id.gridRecyclerView);
//        h_recyclerView = (RecyclerView) findViewById(R.id.h_recyclerView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
//        h_recyclerView.setLayoutManager(layoutManager);
//        arrayList = new ArrayList<>();
//        imageModelArrayList = eatFruits();
//        /////
//        adapterFruit = new FruitAdapter(this, imageModelArrayList);
//        h_recyclerView.setAdapter(adapterFruit);
//        h_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//
//        arrayList.add(new Item("Item 1", R.drawable.beer, "#000000"));
//        arrayList.add(new Item("Item 2", R.drawable.ferrari, "#000000"));
//        arrayList.add(new Item("Item 3", R.drawable.battle, "#000000"));
//        arrayList.add(new Item("Item 4", R.drawable.jetpack_joyride, "#000000"));
//        arrayList.add(new Item("Item 5", R.drawable.three_d, "#000000"));
//        arrayList.add(new Item("Item 6", R.drawable.terraria, "#000000"));
//        arrayList.add(new Item("Item 7", R.drawable.terraria, "#000000"));
//        arrayList.add(new Item("More Items", R.drawable.beer, "#000000"));
//
//
//        adapter = new HomeAdapter(this, arrayList, this);
//        gridRecyclerView.setAdapter(adapter);
//
//        GridLayoutManager manager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
//        gridRecyclerView.setLayoutManager(manager);

//        fetchNetworkData();
    }

//    private void fetchNetworkData() {
//        progressDoalog = new ProgressDialog(MainActivity.this);
//        progressDoalog.setMessage("Loading....");
//        progressDoalog.show();
//
//        /*Create handle for the RetrofitInstance interface*/
//        APIInterface service = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
//        Call<List<RetroItem>> call = service.getAllPhotos();
//        call.enqueue(new Callback<List<RetroItem>>() {
//
//            @Override
//            public void onResponse(Call<List<RetroItem>> call, Response<List<RetroItem>> response) {
//                progressDoalog.dismiss();
//                generateDataList(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<RetroItem>> call, Throwable t) {
//                progressDoalog.dismiss();
//                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    private void generateDataList(List<RetroItem> body) {
        Log.e("Response", body.toString());
        arrayList.add(new Item("Network Data", R.drawable.beer, "#000000"));
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(Item item) {
        Toast.makeText(getApplicationContext(), item.text + " is clicked", Toast.LENGTH_SHORT).show();
    }





//    private ArrayList<H_Recycler_fruit> eatFruits() {
//
//        ArrayList<H_Recycler_fruit> list = new ArrayList<>();
//
//        for (int i = 0; i < 7; i++) {
//            H_Recycler_fruit fruitModel = new H_Recycler_fruit();
//            fruitModel.setName(myImageNameList[i]);
//            fruitModel.setImage_drawable(myImageList[i]);
//            list.add(fruitModel);
//        }
//        return list;
//    }
}
