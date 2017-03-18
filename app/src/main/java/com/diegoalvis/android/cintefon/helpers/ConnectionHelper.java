package com.diegoalvis.android.cintefon.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by diegoalvis on 3/16/17.
 */

public class ConnectionHelper {

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State stateMobile = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State stateWifi = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

        if (stateMobile == NetworkInfo.State.CONNECTED || stateWifi == NetworkInfo.State.CONNECTED)
            return true;
        else
            return false;
    }

}
