package com.example.droiddaemon.lcaldev.DataBase;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public interface IDatabaseService {

    String getUsername();

    void setUsername(String username);

    void removeUsername();

    String getEmail();

    void setEmail(String email);

    void removeEmail();

    void saveLatLng(Location location);

    LatLng getLatLng();


}
