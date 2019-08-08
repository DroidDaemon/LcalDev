package com.example.droiddaemon.lcaldev.DataBase;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.droiddaemon.lcaldev.util.SharedPrefKeys;

public class SharedPrefsService implements IDatabaseService {

    private static final String TAG = SharedPrefsService.class.getSimpleName();
    private SharedPreferences sharedPreferences;
    Context context;


    public SharedPrefsService(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Override
    public String getUsername() {
        return getFromSharedPref(SharedPrefKeys.SHARED_PREFS_USERNAME, null);
    }

    @Override
    public void setUsername(String username) {
        saveToSharedPref(SharedPrefKeys.SHARED_PREFS_USERNAME, username);
    }

    @Override
    public void removeUsername() {
        removeFromSharedPref(SharedPrefKeys.SHARED_PREFS_USERNAME);
    }


    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public void setEmail(String email) {

    }

    @Override
    public void removeEmail() {

    }


    private String getFromSharedPref(String key, String defValue) {
        return sharedPreferences.getString(key, defValue);
    }


    private void saveToSharedPref(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
        Log.d(TAG, "successfully saved to sharedPref " + getFromSharedPref(key, null));
    }

    private void removeFromSharedPref(String key) {
        sharedPreferences.edit().remove(key).apply();
    }
}
