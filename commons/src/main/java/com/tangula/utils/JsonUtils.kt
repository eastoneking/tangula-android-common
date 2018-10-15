package com.tangula.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * The json utils.
 *
 * **这个Json工具类并未最终完成!!**
 * 最初因为轻信了使用了alibaba的fastJSON库。这导致在Kotlin的环境下，很多JSON资源的解析出现问题，会有
 * 找不到Kotlin类型的构造函数的问题。最后无奈改为Gson实现。但是这个类的接口并非最终的形态，因为目前
 * 的实现仅仅是fastJSON转为GSON的赶工过渡产物。未来需要重新考虑如何设计JSON的接口。
 *
 *
 */
class JsonUtils {

    companion object {
        @JvmStatic
        fun <T> toJson(input:T):String{
            return Gson().toJson(input)
        }

        @JvmStatic
        fun <T> fromJson(str:String, type:Class<T>): T {
            return Gson().fromJson(str, type)
        }

        @JvmStatic
        fun <T> fromArrayJson(str:String, typeToken: TypeToken<T>): T{
            return Gson().fromJson(str, typeToken.type)
        }

    }



}


