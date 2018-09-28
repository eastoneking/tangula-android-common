package com.tangula.android.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

/**
 * The utilities functions for SharedPreferences.
 *
 * @author Dose &middot; king &lt;dose.king@outlook.com&gt; 9.28 2018
 */
class SharedPreferencesUtils {

    companion object {

        /**
         * Obtain [key]'s value from shared preferences which named [spName].
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[dv] the default value, nullable.
         * @return the preference value.
         */
        @JvmStatic
        fun getStringPrivate(ctx:Context, spName:String, key:String, dv:String?):String?{
            return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getString(key, dv)
        }

        /**
         * Obtain [key]'s value from shared preferences which named [spName].
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[dv] the default value, nullable.
         * @return the preference value.
         */
        @JvmStatic
        fun getBooleanPrivate(ctx:Context, spName:String, key:String, dv:Boolean):Boolean{
            return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getBoolean(key,dv)
        }

        /**
         * Obtain [key]'s value from shared preferences which named [spName].
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[dv] the default value, nullable.
         * @return the preference value.
         */
        @JvmStatic
        fun getIntPrivate(ctx:Context, spName:String, key:String, dv:Int):Int{
            return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getInt(key,dv)
        }

        /**
         * Obtain [key]'s value from shared preferences which named [spName].
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[dv] the default value, nullable.
         * @return the preference value.
         */
        @JvmStatic
        fun getLongPrivate(ctx:Context, spName:String, key:String, dv:Long):Long{
            return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getLong(key,dv)
        }

        /**
         * Obtain [key]'s value from shared preferences which named [spName].
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[dv] the default value, nullable.
         * @return the preference value.
         */
        @JvmStatic
        fun getFloatPrivate(ctx:Context, spName:String, key:String, dv:Float):Float{
            return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getFloat(key,dv)
        }


        /**
         * Obtain [key]'s value from shared preferences which named [spName].
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @return if not have the value, return an empty set.
         */
        @JvmStatic
        fun getStringSetPrivate(ctx:Context, spName:String, key:String):Set<String>{
            return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getStringSet(key, mutableSetOf())
        }

        /**
         * upadte the private shared preference value.
         *
         */
        var updatePrivateSharedPreference={ctx:Context, spName:String, key:String, value:Any?, updator:(SharedPreferences.Editor)->Unit->
            val sp = ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE)
            val edit = sp.edit()
            if(value==null){
                edit.remove(key)
            }else {
                updator(edit)
            }
            edit.apply()
        }


        /**
         * Save [key]'s value to shared preferences which named [spName].
         *
         * if [value] was null, it would remove the [key] from shared preferences.
         * The update would be process in background, because it's name is ending by charactor 'A'.
         *
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[value] the default value, nullable.
         */
        @JvmStatic
        fun putStringPrivateA(ctx:Context, spName:String, key:String, value:String?){
            updatePrivateSharedPreference(ctx,spName,key,value){ed->ed.putString(key, value)}
        }

        /**
         * Save [key]'s value to shared preferences which named [spName].
         *
         * if [value] was null, it would remove the [key] from shared preferences.
         * The update would be process in background, because it's name is ending by charactor 'A'.
         *
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[value] the default value, nullable.
         */
        @JvmStatic
        fun putBooleanPrivateA(ctx:Context, spName:String, key:String, value:Boolean?){
            updatePrivateSharedPreference(ctx,spName,key,value){ed->ed.putBoolean(key, value!!)}
        }

        /**
         * Save [key]'s value to shared preferences which named [spName].
         *
         * if [value] was null, it would remove the [key] from shared preferences.
         * The update would be process in background, because it's name is ending by charactor 'A'.
         *
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[value] the default value, nullable.
         */
        @JvmStatic
        fun putIntPrivateA(ctx:Context, spName:String, key:String, value:Int?){
            updatePrivateSharedPreference(ctx,spName,key,value){ed->ed.putInt(key, value!!)}
        }

        /**
         * Save [key]'s value to shared preferences which named [spName].
         *
         * if [value] was null, it would remove the [key] from shared preferences.
         * The update would be process in background, because it's name is ending by charactor 'A'.
         *
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[value] the default value, nullable.
         */
        @JvmStatic
        fun putLongPrivateA(ctx:Context, spName:String, key:String, value:Long?){
            updatePrivateSharedPreference(ctx,spName,key,value){ed->ed.putLong(key, value!!)}
        }

        /**
         * Save [key]'s value to shared preferences which named [spName].
         *
         * if [value] was null, it would remove the [key] from shared preferences.
         * The update would be process in background, because it's name is ending by charactor 'A'.
         *
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[value] the default value, nullable.
         */
        @JvmStatic
        fun putFloatPrivateA(ctx:Context, spName:String, key:String, value:Float?){
            updatePrivateSharedPreference(ctx,spName,key,value){ed->ed.putFloat(key, value!!)}
        }


        /**
         * Save [key]'s value to shared preferences which named [spName].
         *
         * if [value] was null, it would remove the [key] from shared preferences.
         * The update would be process in background, because it's name is ending by charactor 'A'.
         *
         * @param[ctx] android context.
         * @param[spName] the shared preferences's name.
         * @param[key] the key in shared preferences.
         * @param[value] the default value, nullable.
         */
        @JvmStatic
        fun putStringSetPrivateA(ctx:Context, spName:String, key:String, value:Set<String>?){
            updatePrivateSharedPreference(ctx,spName,key,value){ed->ed.putStringSet(key, value!!)}
        }
    }

}
