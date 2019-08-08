package com.example.droiddaemon.lcaldev.listeners;

import com.example.droiddaemon.lcaldev.model.RetroItem;

import java.util.List;

public interface NetworkDataListener {

     void fetchNetworkDataSuccess(List<RetroItem> body);
     void fetchNetworkDataFailure(String msg);

}
