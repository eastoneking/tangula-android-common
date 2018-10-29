package com.tangula.android.utils

import android.app.Activity
import android.content.Intent
import kotlin.reflect.KClass


@Suppress("unused")
class ActivityUtils {

    companion object {

        @Suppress("unused")
        fun startActivity(clazz:KClass<out Activity>){
            ApplicationUtils.APP.startActivity(Intent(ApplicationUtils.APP, clazz.java))
        }

    }

}

fun Activity.startActivity(clazz:KClass<out Activity>){
    ActivityUtils.startActivity(clazz)
}