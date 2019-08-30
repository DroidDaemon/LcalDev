package com.example.droiddaemon.lcaldev;

import android.content.Context;
import android.location.Location;

import com.example.droiddaemon.lcaldev.DataBase.CachingService;
import com.example.droiddaemon.lcaldev.DataBase.DatabaseHelper;
import com.example.droiddaemon.lcaldev.DataBase.IDatabaseService;
import com.example.droiddaemon.lcaldev.listeners.AllServiceFetchListener;
import com.example.droiddaemon.lcaldev.listeners.NetworkDataListener;
import com.example.droiddaemon.lcaldev.model.AllServiceModel;
import com.example.droiddaemon.lcaldev.model.AllServiceRequestModel;
import com.example.droiddaemon.lcaldev.model.RetroItem;
import com.example.droiddaemon.lcaldev.retrofit.APIInterface;
import com.example.droiddaemon.lcaldev.retrofit.RetrofitClientInstance;
import com.example.droiddaemon.lcaldev.retrofit.WebAPICall;
import com.example.droiddaemon.lcaldev.util.RequestResponseFormatter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Controller {
    private Context context;
    DatabaseHelper databaseHelper;
    CachingService cachingService;
    IDatabaseService databaseService;
    Controller controller;

    public Controller(DatabaseHelper databaseHelper, CachingService cachingService, IDatabaseService databaseService) {
        this.databaseHelper = databaseHelper;
        this.cachingService = cachingService;
    }


    public void setContext(Context context) {
        this.context = context;
    }

    public final Context getContext() {
        return context;
    }

    public final void setUsername(String userName) {
        databaseService.setUsername(userName);
    }

    public final String getUsername() {
        return databaseService.getUsername();
    }

    public final void saveLatLng(Location location) {
        databaseService.saveLatLng(location);
    }

    public final LatLng getLatLng() {
        return databaseService.getLatLng();
    }


    public void fetchNetworkData(Context context, final NetworkDataListener listener) {
        APIInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
        Call call1 = apiInterface.getAllPhotos();
        new WebAPICall(context).doGetResponse("Loading Skip Reasons...", call1, true, new WebAPICall.Response() {

            @Override
            public void onSuccessResponse(Object object) {
                try {
                    if (object instanceof ArrayList) {
                        if (object != null && ((ArrayList) object).get(0) instanceof RetroItem) {
                            List<RetroItem> skipReasoneRetroPojo = (List<RetroItem>) object;
                            listener.fetchNetworkDataSuccess(skipReasoneRetroPojo);
                        }
                    }
                } catch (Exception e) {
//                    Log.e("", e);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                listener.fetchNetworkDataFailure(message);
                // ToastUtills.showLongToast(getActivity(),message);

            }
        });

    }

    public void fetchAllServices(Context context, final AllServiceFetchListener listener) {
        APIInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
        Call call1 = apiInterface.getAllServices();
        new WebAPICall(context).doGetResponse("Loading Data...", call1, true, new WebAPICall.Response() {

            @Override
            public void onSuccessResponse(Object object) {
                try {
                    if (object instanceof ArrayList) {
                        if (object != null && ((ArrayList) object).get(0) instanceof AllServiceRequestModel) {
                            ArrayList<AllServiceRequestModel> allServiceRequestModels = (ArrayList<AllServiceRequestModel>) object;
                            ArrayList<AllServiceModel> allserviceObject = RequestResponseFormatter.convertAllserviceObject(allServiceRequestModels);
                            listener.onFetchAllserviceSuccess(allserviceObject);
                        }
                    }
                } catch (Exception e) {
//                    Log.e("", e);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                listener.onFetchAllserviceFailure(message);
                // ToastUtills.showLongToast(getActivity(),message);

            }
        });


    }

    public void fetchBanners(Context context, final AllServiceFetchListener listener) {
        APIInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
        Call call1 = apiInterface.getAllServices();
        new WebAPICall(context).doGetResponse("Loading Data...", call1, true, new WebAPICall.Response() {

            @Override
            public void onSuccessResponse(Object object) {
                try {
                    if (object instanceof ArrayList) {
                        if (object != null && ((ArrayList) object).get(0) instanceof AllServiceRequestModel) {
                            ArrayList<AllServiceRequestModel> allServiceRequestModels = (ArrayList<AllServiceRequestModel>) object;
                            ArrayList<AllServiceModel> allserviceObject = RequestResponseFormatter.convertAllserviceObject(allServiceRequestModels);
                            listener.onFetchAllserviceSuccess(allserviceObject);
                        }
                    }
                } catch (Exception e) {
//                    Log.e("", e);
                }

            }

            @Override
            public void onFailureResponse(String message) {
                listener.onFetchAllserviceFailure(message);
                // ToastUtills.showLongToast(getActivity(),message);

            }
        });
    }
}
