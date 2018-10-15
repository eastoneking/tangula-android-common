package com.tangula.android.base

import android.support.v7.app.AppCompatActivity
import com.tangula.android.utils.PermissionUtils

abstract class TglBasicActivity : AppCompatActivity() {

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionUtils.onRequestPermisionsFinish(requestCode, permissions ,grantResults)
    }

}