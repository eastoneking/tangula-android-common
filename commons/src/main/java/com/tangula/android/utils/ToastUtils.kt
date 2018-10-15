package com.tangula.android.utils

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.widget.Toast
import com.tangula.android.utils.UiThreadUtils.Companion.runInUiThread

class ToastUtils {


    companion object {

        /**
         * Show text as toast with special duration.
         * @param text The messasge would show in toast.
         * @param duration The display time length.
         */
        @JvmStatic
        fun showToast(text:String, duration:Int){
            runInUiThread{ Toast.makeText(ApplicationUtils.APP, text, duration).show()}
        }

        /**
         * Show text as toast long time.
         * @param text The messasge would show in toast.
         */
        @JvmStatic
        fun showToastLong(text:String){
            showToast(text, Toast.LENGTH_LONG)
        }

        /**
         * Show text as toast short time.
         * @param text The messasge would show in toast.
         */
        @JvmStatic
        fun showToastShort(text:String){
            showToast(text, Toast.LENGTH_SHORT)
        }

    }
}