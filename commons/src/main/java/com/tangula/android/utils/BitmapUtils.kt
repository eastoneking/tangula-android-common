package com.tangula.android.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * 操作Bitmap的工具类.
 */
@Suppress("UNUSED")
class BitmapUtils {

    companion object {

        /**
         * 生成位图的Base64格式字符串.
         *
         * @param[bitmap] 图片.
         * @return 字符串.
         * 如果参数[bitmap]为空，返回空字符串.
         */
        @JvmStatic
        fun bitmapToBase64(bitmap: Bitmap?): String {

            var result = ""
            var baos: ByteArrayOutputStream? = null
            try {
                if (bitmap != null) {
                    baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)

                    baos.flush()
                    baos.close()

                    val bitmapBytes = baos.toByteArray()
                    result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    if (baos != null) {
                        baos.flush()
                        baos.close()
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            return result
        }

        /**
         * 将保存为base64格式的位图转换回[Bitmap]类型的对象.
         * @param base64Data input.
         * @return 位图对象.
         */
        @JvmStatic
        fun base64ToBitmap(base64Data: String): Bitmap {
            val bytes = Base64.decode(base64Data, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }
    }

}