package com.assignment.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

public class Common {
    private static final String PREFERENCE_NAME = "searchsample";
    private String TAG = "Common";
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;
    private static SharedPreferences settings;
    private static SharedPreferences.Editor prefEditor;

    public static boolean isConnectingToInternet(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Network[] networks = connectivityManager.getAllNetworks();
                NetworkInfo networkInfo;
                for (Network mNetwork : networks) {
                    networkInfo = connectivityManager.getNetworkInfo(mNetwork);
                    if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        return true;
                    }
                }
            } else {
                if (connectivityManager != null) {
                    //noinspection deprecation
                    NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
                    if (info != null) {
                        for (NetworkInfo anInfo : info) {
                            if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
                                Log.d("Network",
                                        "NETWORKNAME: " + anInfo.getTypeName());
                                return true;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //Toast.makeText(mContext, "Please Connect to Internet.", Toast.LENGTH_SHORT).show();
        return false;
    }

    public static String  getMovieActionName(Context context) {
        try {
            prefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
            String name = prefs.getString("MOVIE_ACTION_NAME", "");
            return name;
        }catch (Exception e){
            return "null";
        }
    }
}
