package com.example.droiddaemon.lcaldev;

import android.support.multidex.MultiDexApplication;

import com.example.droiddaemon.lcaldev.Controller;
import com.example.droiddaemon.lcaldev.DataBase.CachingService;
import com.example.droiddaemon.lcaldev.DataBase.DatabaseHelper;
import com.example.droiddaemon.lcaldev.DataBase.IDatabaseService;
import com.example.droiddaemon.lcaldev.DataBase.SharedPrefsService;

public class SharedApplication extends MultiDexApplication{
    protected Controller controller;

    public Controller getAppController() {
        return (Controller  ) controller;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        CachingService cachingService = new CachingService(databaseHelper);
        IDatabaseService databaseService = new SharedPrefsService(this);
        controller = new Controller(databaseHelper, cachingService,databaseService);
    }
}
