package com.tangula.android.base

import android.support.multidex.MultiDexApplication
import com.tangula.android.utils.ApplicationUtils

abstract class TglBasicApplication :MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        ApplicationUtils.APP = this

    }
}