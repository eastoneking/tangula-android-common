package com.tangula.android.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class AndroidSettingsUtils{
    companion object {
        @SuppressLint("NewApi")
        fun canOverlayOrNot(act: Activity, task: () -> Unit, onNot: (() -> Unit)?) {
            when {
                SdkVersionDecides.beforeSdk27A8d1() -> {
                    PermissionUtils.whenHasAllPremissions(act, listOf(
                            "android.permission.SYSTEM_ALERT_WINDOW"
                    ), task, onNot)
                }
                else -> {
                    val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                            Uri.parse("package:${act.packageName}"))
                    ActivityResultUtils.startActivityForResult(act, intent) { _, _ ->
                        SdkVersionDecides.afterSdk23A6d0 {
                            if (Settings.canDrawOverlays(act)) {
                                task()
                            } else {
                                onNot?.also { it() }
                            }
                        }
                    }
                }
            }
        }
    }
}

class ActivityResultUtils {

        companion object {

            /**
             * 本次运行Activity代码.
             */
            private var CUR_ACTIVITY_CODE = 0

            /**
             * 获得下一次运行Activity的Code.
             * @return 下次运行Activity的Code.
             */
            private fun nextActivityCode(): Int {
                synchronized(com.tangula.android.utils.PermissionUtils.Companion) {
                    return ++CUR_ACTIVITY_CODE
                }
            }

            private val WAITING_RESULT_ACTIVITIES = mutableMapOf<Int, (Int, Intent?) -> Unit>()


            @SuppressLint("NewApi")
            fun startActivityForResult(act: Activity, intent: Intent, callback: (Int, Intent?) -> Unit) {

                val code = nextActivityCode()

                WAITING_RESULT_ACTIVITIES.put(code) {res,data->
                    try {
                        callback(res, data)
                    }finally {
                        WAITING_RESULT_ACTIVITIES.remove(code)
                    }
                }


                act.startActivityForResult(intent,code)

            }

            fun onActivityResult(code: Int, resultCode:Int, data:Intent?) {
                WAITING_RESULT_ACTIVITIES[code]?.also {
                    it(resultCode, data)
                }
            }

        }
}