package com.tangula.android.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.tangula.android.utils.ApplicationUtils
import com.tangula.android.utils.PermissionUtils

abstract class TglBasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        if(ApplicationUtils.APP==null){
            ApplicationUtils.APP = application
        }
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtils.onRequestPermisionsFinish(requestCode, permissions ,grantResults)
    }

}