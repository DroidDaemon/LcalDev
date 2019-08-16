package com.example.droiddaemon.lcaldev.listeners;

import com.example.droiddaemon.lcaldev.model.AllServiceModel;
import com.example.droiddaemon.lcaldev.model.AllServiceRequestModel;

import java.util.ArrayList;
import java.util.List;

public interface AllServiceFetchListener {

    void onFetchAllserviceSuccess(ArrayList<AllServiceModel> allServiceRequestModels);
    void onFetchAllserviceFailure(String msg);



}
