package com.tangula.android.utils

import android.widget.Toast
import com.tangula.android.utils.TaskUtils.Companion.runInUiThread

@Suppress("UNUSED", "MemberVisibilityCanBePrivate")
class ToastUtils {

    companion object {

        /**
         * Show text as toast with special duration.
         * @param text The messasge would show in toast.
         * @param duration The display time length.
         */
        @JvmStatic
        fun showToast(text: String, duration: Int) {
            runInUiThread { Toast.makeText(ApplicationUtils.APP, text, duration).show() }
        }

        /**
         * Show text as toast with special duration.
         * @param [resId] The messasge resource id which would show in toast.
         * @param duration The display time length.
         */
        @JvmStatic
        fun showToastRes(resId: Int, duration: Int) {
            runInUiThread { Toast.makeText(ApplicationUtils.APP, resId, duration).show() }
        }

        /**
         * Show text as toast long time.
         * @param text The messasge would show in toast.
         */
        @JvmStatic
        fun showToastLong(text: String) {
            showToast(text, Toast.LENGTH_LONG)
        }


        /**
         * Show text as toast long time.
         * @param resId The messasge would show in toast.
         */
        @JvmStatic
        fun showToastResLong(resId: Int) {
            showToastRes(resId, Toast.LENGTH_LONG)
        }

        /**
         * Show text as toast short time.
         * @param text The messasge would show in toast.
         */
        @JvmStatic
        fun showToastShort(text: String) {
            showToast(text, Toast.LENGTH_SHORT)
        }

        /**
         * Show text as toast short time.
         * @param resId The messasge would show in toast.
         */
        @JvmStatic
        fun showToastResShort(resId: Int) {
            showToastRes(resId, Toast.LENGTH_SHORT)
        }

    }
}