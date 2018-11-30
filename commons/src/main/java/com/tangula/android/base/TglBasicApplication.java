package com.tangula.android.base;

import android.support.multidex.MultiDexApplication;

import com.tangula.android.utils.ApplicationUtils;

public class TglBasicApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        ApplicationUtils.APP = this;
        super.onCreate();
    }
}
