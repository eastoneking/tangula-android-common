package com.tangula.android.utils

import android.app.Activity
import kotlin.reflect.KClass

@Suppress("unused")
fun Activity.startActivity(clazz:KClass<out Activity>){
    ActivityUtils.startActivity(clazz.java)
}