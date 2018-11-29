package com.tangula.android.utils

import android.app.Activity
import android.content.Intent
import kotlin.reflect.KClass


fun Activity.startActivity(clazz:KClass<out Activity>){
    ActivityUtils.startActivity(clazz.java)
}