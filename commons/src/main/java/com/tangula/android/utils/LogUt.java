package com.tangula.android.utils;

import android.util.Log;

public class LogUt {
    public static String TAG = "console";

    public static void e(Throwable e){
        if(e!=null){
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
    }

}
