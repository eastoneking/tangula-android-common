package com.tangula.android.utils;

import android.app.Activity;
import android.content.Intent;

public class ActivityUtils {

    public static void startActivity(Class<? extends Activity> clazz){
        ApplicationUtils.APP.startActivity(new Intent(ApplicationUtils.APP, clazz));
    }

}
