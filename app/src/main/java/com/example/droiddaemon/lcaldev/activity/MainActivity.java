package com.example.droiddaemon.lcaldev.activity;

import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.droiddaemon.lcaldev.R;
import com.example.droiddaemon.lcaldev.adapter.FruitAdapter;
import com.example.droiddaemon.lcaldev.adapter.HomeAdapter;
import com.example.droiddaemon.lcaldev.fragments.BookingsFragment;
import com.example.droiddaemon.lcaldev.fragments.DashboardFragment;
import com.example.droiddaemon.lcaldev.fragments.ProfileFragment;
import com.example.droiddaemon.lcaldev.model.AllServiceModel;
import com.example.droiddaemon.lcaldev.model.H_Recycler_fruit;
import com.example.droiddaemon.lcaldev.model.Item;
import com.example.droiddaemon.lcaldev.model.RetroItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements HomeAdapter.ItemListener, BookingsFragment.OnBookingFragmentInteractionListener {

    double lat = 28.4625;
    double lng = 77.0564;
    TextView toolbarTextView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTextView = (TextView) findViewById(R.id.toolbarTextView);


        setSupportActionBar(toolbar);
        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorDark));

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setAddress(getAddress());
        loadFragment(new DashboardFragment());
    }

    private void setAddress(String address) {
        toolbarTextView.setText(address);
    }

    public String getAddress() {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();
            String knownName = addresses.get(0).getFeatureName();
            String add = city + " " + state + " - " + postalCode;
            return add;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    @Override
    public void onItemClick(AllServiceModel item) {
        Toast.makeText(getApplicationContext(), item.getName() + " is clicked", Toast.LENGTH_SHORT).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.home_fragment_container, fragment);
        transaction.commit();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("");
                    fragment = new DashboardFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_bookings:
                    toolbar.setTitle("My Bookings");
                    fragment = new BookingsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:
                    toolbar.setTitle("Profile");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//// if user is On CheckInScanFragment pop two fragment immediately
//        FragmentManager fm = getSupportFragmentManager();
//
//        Fragment fragment = fm.findFragmentByTag(DashboardFragment.TAG);
//        if (fragment instanceof DashboardFragment && fragment.isVisible()) {
//
//            for (Fragment frag : fm.getFragments()) {
//                if (frag != null && frag.isVisible()) {
//                    FragmentManager childFm = frag.getFragmentManager();
//                    if (childFm.getBackStackEntryCount() > 0) {
//                        childFm.popBackStack();
//                        return;
//                    }
//                }
//            }
//        }
    }
}
