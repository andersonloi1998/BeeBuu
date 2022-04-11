package com.example.beebuu.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import com.example.beebuu.Model.User;

public class Common {
    public static User currentUser;
    public static String DELETE = "DELETE";
    public static String USER_KEY = "User";
    public static String PWD_KEY = "Password";


    public static String convertCodeToStatus(String status){
        if(status.equals("0"))
            return "Ordered!";
        else if(status.equals("1"))
            return "On the way!!!";
        else
            return "Shipped!";
    }

    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null){
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();

            if(info != null){
                for(int i=0;i<info.length;i++)
                {
                    if(info[i].getState() == NetworkInfo.State.CONNECTED)
                    return true;
                }
            }
        }
        return false;
    }
}