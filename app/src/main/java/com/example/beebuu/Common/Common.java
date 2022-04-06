package com.example.beebuu.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.beebuu.Model.User;

public class Common {
    public static User currentUser;
    public static String DELETE = "DELETE";
    public static String USER_KEY = "User";
    public static String PWD_KEY = "Password";

    public static boolean isConnectedToInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null){
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo();

            if(info != null){
                for(int i=0;i<info.length;){
                    return true;
                }
            }
        }
        return false;
    }
}