package com.tangula.android.utils

import android.app.Application
import android.content.Context
import android.os.Build
import android.provider.Settings
import org.apache.commons.lang3.StringUtils
import java.util.*


/**
 * Application Utilitities.
 * <p>Some functions which help you handle the application scope processors.</p>
 *
 * @author Dose &middot; King &lt;doss.king@outlook.com&gt;
 */
class ApplicationUtils {

    companion object {

        private const val KEY_UNIQUE_ID_SP_NAME = "KEY_UNIQUE_ID_SP_NAME"
        private const val KEY_UNIQUE_ID_IN_SP = "KEY_UNIQUE_ID_IN_SP"

        /**
         * The current application object.
         * <p>
         *     You can define a custom application class, and save the application
         *     with this property.
         * </p>
         */
        @JvmStatic
        lateinit var APP: Application


        /**
         * To fetch the android id.
         * @return The runtime device's android id.
         */
        fun getAndroidId(context: Context): String {
            return Settings.System.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID)
        }

        /**
         * To create an unique id of an user.
         * <p>This function's resuilt is not real unique id.</p>
         */
        @JvmStatic
        fun fetchUniqueId(ctx: Context): String {

            //use string builder to save the ABIS info.
            val buffer = StringBuilder()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Build.SUPPORTED_ABIS.forEach {
                    buffer.append(it).append(",")
                }
            }

            //create the unique id
            val strId = """${getAndroidId(ctx)}|BOARD:${Build.BOARD}|BRAND:${Build.BRAND}
                |DEVICE:${Build.DEVICE}|DISPLAY:${Build.DISPLAY}|HOST:${Build.HOST}|ID:${Build.ID}
                |MANUFACTURER:${Build.MANUFACTURER}|MODEL:${Build.MODEL}|PRODUCT:${Build.PRODUCT}
                |TAGS:${Build.TAGS}|TYPE:${Build.TYPE}|USER:${Build.USER}
                |SUPPORTED_ABIS:${buffer}"""

            return UUID.nameUUIDFromBytes(strId.toByteArray(Charsets.UTF_8)).toString()
        }

        /**
         * To clear the unique id which be stored in shared preferences.
         */
        @JvmStatic
        fun clearUniqueId(ctx: Context) {
            val sp = ctx.getSharedPreferences(KEY_UNIQUE_ID_SP_NAME, Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.remove(KEY_UNIQUE_ID_IN_SP)
            editor.apply()
        }

        /**
         * To update the unique id in shared preferences.
         */
        @JvmStatic
        fun storeUniqueId(ctx: Context) : String {
            val sp = ctx.getSharedPreferences(KEY_UNIQUE_ID_SP_NAME, Context.MODE_PRIVATE)
            val editor = sp.edit()
            val id = fetchUniqueId(ctx)
            editor.putString(KEY_UNIQUE_ID_IN_SP, id)
            editor.apply()
            return id
        }

        /**
         * To load the unique id which store in shared preferences,if not have it, create and save one unique id, and return it.
         */
        @JvmStatic
        fun loadUniqueId(ctx: Context): String {
            val res = ctx.getSharedPreferences(KEY_UNIQUE_ID_SP_NAME, Context.MODE_PRIVATE).getString(KEY_UNIQUE_ID_IN_SP, "")
            return if(StringUtils.isNotBlank(res) ) res else storeUniqueId(ctx)
        }

    }

}