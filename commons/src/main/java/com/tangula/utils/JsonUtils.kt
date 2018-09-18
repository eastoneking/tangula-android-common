package com.tangula.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


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


