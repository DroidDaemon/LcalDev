package com.example.droiddaemon.lcaldev.retrofit;

import com.example.droiddaemon.lcaldev.model.AllServiceRequestModel;
import com.example.droiddaemon.lcaldev.model.CategoryByIdModel;
import com.example.droiddaemon.lcaldev.model.CategoryByProductIdModel;
import com.example.droiddaemon.lcaldev.model.RetroItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/posts")
    Call<List<RetroItem>> getAllPhotos();

    @GET("services/get")
    Call<List<AllServiceRequestModel>> getAllServices();

    @GET("Pcategory/getCategoryByProducId/{productId}")
    Call<List<CategoryByProductIdModel>> getCategoryByProductId(@Path("productId") String productId);


    @GET("Pcategory/getCategoryById/{productId}")
    Call<List<CategoryByIdModel>> getCategoryById(@Path("productId") String productId);
}
