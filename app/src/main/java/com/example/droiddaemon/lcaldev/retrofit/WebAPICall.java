package com.example.droiddaemon.lcaldev.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.droiddaemon.lcaldev.Controller;
import retrofit2.Call;

/**
 * Created by kirti on 8/30/2017.
 */

public class WebAPICall {
    private static final String TAG = WebAPICall.class.getSimpleName();
    private ProgressDialog progressDialog;
    private Context context;
    private Controller controller;

    public WebAPICall(Context context) {
        this.context = context;
    }

    public WebAPICall(Context context, Controller controller) {
        this.context = context;
        this.controller = controller;
    }

    public void doGetResponse(String msg, final Call call, boolean isShown, final Response res) {
        if (isShown && (null != context) && !((Activity) context).isFinishing()) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            progressDialog.setCancelable(false);
            try {
                progressDialog.show();
            }catch(Exception e){
                // WindowManager$BadTokenException will be caught
            }

        }

            callAPI(call, res);

    }

    private void callAPI(Call call, final Response res) {
        Log.d(TAG, "(++) callAPI");
        call.enqueue(new retrofit2.Callback() {

            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                Log.i(TAG, "===== request url : " + response.raw().request().url().toString());
                dismissProgress(progressDialog);
                if (response.isSuccessful()) {
                    res.onSuccessResponse(response.body());
                    Log.i(TAG, "===== response body : " + response.body().toString());
                } else {
                    res.onFailureResponse("Response Unsuccessful");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                dismissProgress(progressDialog);
                res.onFailureResponse(t.getLocalizedMessage());
            }
        });
    }

    private void dismissProgress(ProgressDialog progressDialog) {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }


    public interface Response<T> {
        void onSuccessResponse(T result);

        void onFailureResponse(String message);
    }
}
