package com.tangula.android.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat





/**
 * 定位授权工具.
 */
@Suppress("UNUSED")
class LocationPermsUtils {
    companion object {

        val LOCATION_PERMISSIONS = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        /**
         * 只要有任何定位权限，就执行[task],否则执行[onRejectGrantCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectGrantCallback] 拒绝授予权限时候的处理方法.
         */
        fun canAccessLocation(act: Activity, task: () -> Unit, onRejectGrantCallback: (() -> Unit)?) {
            PermissionUtils.whenHasAnyPremissions(act, LOCATION_PERMISSIONS, task, onRejectGrantCallback)
        }

    }
}

/**
 * 拨号授权工具.
 */
@Suppress("UNUSED")
class CallPermsUtils {
    companion object {
        /**
         * 拥有[Manifest.permission.CALL_PHONE]权限，就执行[task],否则执行[onRejectGrantCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectGrantCallback] 拒绝授予权限时候的处理方法.
         */
        fun canMakeACall(act: Activity, task: () -> Unit, onRejectGrantCallback: (() -> Unit)?) {
            PermissionUtils.whenHasAnyPremissions(act, listOf(Manifest.permission.CALL_PHONE), task, onRejectGrantCallback)
        }
    }
}


/**
 * 文件读写授权工具.
 */
@Suppress("UNUSED")
class FilePermsUtils {
    companion object {
        /**
         * 同时拥有[Manifest.permission.READ_EXTERNAL_STORAGE]和[Manifest.permission.WRITE_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectCallback] 拒绝授予权限时候的处理方法.
         */
        fun bothReadWrite(act: Activity, task: () -> Unit, onRejectCallback: () -> Unit) {
            PermissionUtils.whenHasAllPremissions(act, listOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), task, onRejectCallback)
        }
        /**
         * 拥有[Manifest.permission.READ_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectCallback] 拒绝授予权限时候的处理方法.
         */
        fun onlyRead(act: Activity, task: () -> Unit, onRejectCallback: () -> Unit) {
            PermissionUtils.whenHasAllPremissions(act, listOf(Manifest.permission.READ_EXTERNAL_STORAGE), task, onRejectCallback)
        }
        /**
         * 拥有[Manifest.permission.WRITE_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectCallback] 拒绝授予权限时候的处理方法.
         */
        fun onlyWrite(act: Activity, task: () -> Unit, onRejectCallback: () -> Unit) {
            PermissionUtils.whenHasAllPremissions(act, listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), task, onRejectCallback)
        }
    }
}

/**
 * 网络授权工具.
 */
@Suppress("UNUSED")
class NetwordPermsUtils {
    companion object {
        /**
         * 拥有[Manifest.permission.INTERNET]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectCallback] 拒绝授予权限时候的处理方法.
         */
        fun whenCanAccessInternet(act: Activity, task: () -> Unit, onRejectCallback: () -> Unit) {
            PermissionUtils.whenHasAllPremissions(act, listOf(Manifest.permission.INTERNET), task, onRejectCallback)
        }
    }
}

