package com.tangula.android.utils

import android.app.Application
import android.os.Build
import org.apache.commons.lang3.StringUtils
import java.util.*

/**
 * APP工具.
 */
class ApplicationUtils {

    companion object {
        /**
         * 当前APP.
         */
        @JvmStatic
        lateinit var APP: Application


        /**
         * 获得用户唯一ID.
         */
        @JvmStatic
        fun fetchUniqueId(): String {
            val id_string = "35" + //we make this look like a valid IMEI
                    Build.BOARD +
                    Build.BRAND +
                    StringUtils.join(Build.SUPPORTED_ABIS) +
                    Build.DEVICE +
                    Build.DISPLAY +
                    Build.HOST +
                    Build.ID +
                    Build.MANUFACTURER +
                    Build.MODEL +
                    Build.PRODUCT +
                    Build.TAGS +
                    Build.TYPE +
                    Build.USER

            return UUID.nameUUIDFromBytes(id_string.toByteArray(Charsets.UTF_8)).toString()
        }



    }

}