package com.tangula.android.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.graphics.drawable.DrawableCompat

class ResUtils {

    companion object {

        @JvmStatic
        fun fetchResString(@StringRes resId:Int):String{
            return ApplicationUtils.APP.getString(resId)
        }

        @JvmStatic
        fun fetchResString(@StringRes resId:Int, args:List<String>):String{
            return ApplicationUtils.APP.getString(resId, args)
        }

        @SuppressLint("NewApi")
        @JvmStatic
        fun fetchResInt(@ColorRes colorId:Int): Int {
            return  if(SdkVersionDecides.beforeSdk20A4d4W()) {
                ApplicationUtils.APP.resources.getColor(colorId)
            }else{
                ApplicationUtils.APP.getColor(colorId)
            }
        }


        @SuppressLint("NewApi")
        @JvmStatic
        fun fetchResInt(@ColorRes colorId:Int, theme:Resources.Theme): Int {
            return  if(SdkVersionDecides.beforeSdk20A4d4W()) {
                ApplicationUtils.APP.resources.getColor(colorId)
            }else{
                ApplicationUtils.APP.getColor(colorId)
            }
        }

        @JvmStatic
        fun fetchDrawable(resId:Int):Drawable? {
            return if(SdkVersionDecides.beforeSdk20A4d4W()){
                val btmp = BitmapFactory.decodeResource(ApplicationUtils.APP.resources, resId)
                BitmapDrawable(ApplicationUtils.APP.resources, btmp)
            }else{
                ApplicationUtils.APP.resources.getDrawable(resId, null)
            }
        }

        @JvmStatic
        fun fetchDrawable(resId:Int, theme:Resources.Theme?):Drawable? {
            return if(SdkVersionDecides.beforeSdk20A4d4W()){
                val btmp = BitmapFactory.decodeResource(ApplicationUtils.APP.resources, resId)
                val res = BitmapDrawable(ApplicationUtils.APP.resources, btmp)
                if(theme is Resources.Theme){
                    DrawableCompat.applyTheme(res, theme)
                }
                res
            }else{
                ApplicationUtils.APP.resources.getDrawable(resId, theme)
            }
        }


    }

}