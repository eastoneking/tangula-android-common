package com.tangula.android.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Environment
import android.support.annotation.*
import android.support.v4.graphics.drawable.DrawableCompat
import android.util.Log
import java.io.*
import java.lang.Exception

class ResUtils {

    companion object {
        @JvmStatic
        fun readFromInputStream(fac: () -> InputStream?, callback: (InputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            var instm: InputStream? = null
            try {
                instm = fac.invoke()
                instm?.apply(callback)
            } catch (ex: java.lang.Exception) {
                onThrow?.apply { this(ex) }
            } finally {
                try {
                    instm?.close()
                } catch (ex: Exception) {
                    Log.w("console", ex.localizedMessage, ex)
                }
            }
        }
        @JvmStatic
        fun writeToOutputStream(fac:()->OutputStream?, callback: (OutputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            var out: OutputStream? = null
            try {
                out = fac.invoke()
                out?.apply(callback)
            } catch (ex: java.lang.Exception) {
                onThrow?.apply { this(ex) }
            } finally {
                try {
                    out?.close()
                } catch (ex: Exception) {
                    Log.w("console", ex.localizedMessage, ex)
                }
            }
        }

        @JvmStatic
        fun readAssertFile(filename: String, callback: (InputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            readFromInputStream({ ApplicationUtils.APP.resources.assets.open(filename) }, callback, onThrow)
        }
        @JvmStatic
        fun readRawFile(@RawRes resId: Int, callback: (InputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            readFromInputStream({ ApplicationUtils.APP.resources.openRawResource(resId) }, callback, onThrow)
        }
        @JvmStatic
        fun readInteralFile(@RawRes filepath: String, callback: (InputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            readFromInputStream({ ApplicationUtils.APP.openFileInput(filepath) }, callback, onThrow)
        }
        @JvmStatic
        fun writeInteralFile(@RawRes filepath: String, isAppend: Boolean, callback: (OutputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            writeToOutputStream({ApplicationUtils.APP.openFileOutput(filepath, if (isAppend) Context.MODE_APPEND else Context.MODE_PRIVATE)}, callback, onThrow)
        }

        @JvmStatic
        private fun checkSdCardFileParentPathAndCreateIt(filepath:String):File{
            val sdcard = Environment.getExternalStorageDirectory()
            val file =  File(sdcard,filepath)
            val parent = file.parentFile
            if(!parent.exists()){
                if(!parent.mkdirs()){
                    throw FileNotFoundException("can not create the file "+parent.path)
                }
            }
            return file
        }

        @JvmStatic
        private val openSdCardInputStream = {filepath:String->
            FileInputStream(checkSdCardFileParentPathAndCreateIt(filepath))
        }

        @JvmStatic
        private val openSdCardOutput = {filepath:String, isAppend:Boolean->
            FileOutputStream(checkSdCardFileParentPathAndCreateIt(filepath), isAppend)
        }

        @JvmStatic
        fun readSdCardFile( filepath: String, callback: (InputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            PermissionUtils.whenHasAllPremissionsNotWithRequestPermissions(listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    {readFromInputStream({openSdCardInputStream(filepath)}, callback, onThrow)}){}

        }

        @JvmStatic
        fun readSdCardFile(activity: Activity, filepath: String, callback: (InputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            PermissionUtils.whenHasAllPremissions(activity, listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    {readFromInputStream({ openSdCardInputStream(filepath) }, callback, onThrow)}){}
        }

        @JvmStatic
        fun writeSdCardFile(filepath: String, isAppend: Boolean, callback: (OutputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            PermissionUtils.whenHasAllPremissionsNotWithRequestPermissions(listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    {writeToOutputStream({ openSdCardOutput(filepath, isAppend) }, callback, onThrow)}){}

        }

        @JvmStatic
        fun writeSdCardFile(activity: Activity, filepath: String, isAppend: Boolean, callback: (OutputStream) -> Unit, onThrow: ((ex: java.lang.Exception) -> Unit)?) {
            PermissionUtils.whenHasAllPremissions(activity, listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    {writeToOutputStream({ openSdCardOutput(filepath, isAppend) }, callback, onThrow)}){}

        }


        @JvmStatic
        fun fetchResString(@StringRes resId: Int): String {
            return ApplicationUtils.APP.getString(resId)
        }

        @JvmStatic
        fun fetchResString(@StringRes resId: Int, args: List<String>): String {
            return ApplicationUtils.APP.getString(resId, args)
        }

        @JvmStatic
        fun fetchResStringArray(@ArrayRes resId: Int): Array<String> {
            return ApplicationUtils.APP.resources.getStringArray(resId)
        }

        @SuppressLint("NewApi")
        @JvmStatic
        fun fetchResColor(@ColorRes colorId: Int): Int {
            return if (SdkVersionDecides.beforeSdk20A4d4W()) {
                ApplicationUtils.APP.resources.getColor(colorId)
            } else {
                ApplicationUtils.APP.getColor(colorId)
            }
        }


        @SuppressLint("NewApi")
        @JvmStatic
        fun fetchResColor(@ColorRes colorId: Int, theme: Resources.Theme): Int {
            return if (SdkVersionDecides.beforeSdk20A4d4W()) {
                ApplicationUtils.APP.resources.getColor(colorId)
            } else {
                ApplicationUtils.APP.getColor(colorId)
            }
        }


        @SuppressLint("NewApi")
        @JvmStatic
        fun fetchResInt(@IntegerRes resId: Int): Int {
            return ApplicationUtils.APP.resources.getInteger(resId)
        }


        @SuppressLint("NewApi")
        @JvmStatic
        fun fetchResIntArray(@ArrayRes resId: Int): IntArray {
            return ApplicationUtils.APP.resources.getIntArray(resId)
        }


        @JvmStatic
        fun fetchDrawable(resId: Int): Drawable? {
            return if (SdkVersionDecides.beforeSdk20A4d4W()) {
                val btmp = BitmapFactory.decodeResource(ApplicationUtils.APP.resources, resId)
                BitmapDrawable(ApplicationUtils.APP.resources, btmp)
            } else {
                ApplicationUtils.APP.resources.getDrawable(resId, null)
            }
        }

        @JvmStatic
        fun fetchDrawable(resId: Int, theme: Resources.Theme?): Drawable? {
            return if (SdkVersionDecides.beforeSdk20A4d4W()) {
                val btmp = BitmapFactory.decodeResource(ApplicationUtils.APP.resources, resId)
                val res = BitmapDrawable(ApplicationUtils.APP.resources, btmp)
                if (theme is Resources.Theme) {
                    DrawableCompat.applyTheme(res, theme)
                }
                res
            } else {
                ApplicationUtils.APP.resources.getDrawable(resId, theme)
            }
        }


    }

}