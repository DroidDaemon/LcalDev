package com.example.droiddaemon.lcaldev.retrofit;

import com.example.droiddaemon.lcaldev.model.AllServiceRequestModel;
import com.example.droiddaemon.lcaldev.model.RetroItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/posts")
    Call<List<RetroItem>> getAllPhotos();

    @GET("services/get")
    Call<List<AllServiceRequestModel>> getAllServices();
}
