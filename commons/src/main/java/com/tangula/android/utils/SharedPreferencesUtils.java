package com.tangula.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.functions.Consumer;

/**
 * The utilities functions for SharedPreferences.
 *
 * @author Dose &middot; king &lt;dose.king@outlook.com&gt; 9.28 2018
 */
public class SharedPreferencesUtils {

    /**
     * Obtain [key]'s value from shared preferences which named [spName].
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param dv the default value, nullable.
     * @return the preference value.
     */
    public static String getStringPrivate(Context ctx,String spName,String key, String dv){
        return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getString(key, dv);
    }

    /**
     * Obtain [key]'s value from shared preferences which named [spName].
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param dv the default value, nullable.
     * @return the preference value.
     */
    public static boolean getBooleanPrivate(Context ctx,String spName,String key,boolean dv){
        return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getBoolean(key,dv);
    }

    /**
     * Obtain [key]'s value from shared preferences which named [spName].
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param dv the default value, nullable.
     * @return the preference value.
     */
    public static int getIntPrivate(Context ctx,String spName,String key, int dv){
        return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getInt(key,dv);
    }

    /**
     * Obtain [key]'s value from shared preferences which named [spName].
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param dv the default value, nullable.
     * @return the preference value.
     */
    public static long getLongPrivate(Context ctx,String spName,String key,long dv){
        return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getLong(key,dv);
    }

    /**
     * Obtain [key]'s value from shared preferences which named [spName].
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param dv the default value, nullable.
     * @return the preference value.
     */
    public static float getFloatPrivate(Context ctx,String spName,String key,float dv){
        return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getFloat(key,dv);
    }


    /**
     * Obtain [key]'s value from shared preferences which named [spName].
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @return if not have the value, return an empty set.
     */
    public static Set<String> getStringSetPrivate(Context ctx, String spName, String key){
        return ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE).getStringSet(key, new HashSet<String>());
    }
    
    
    interface SpUpdateHandler{
        void update(Context ctx, String spName, String key, Object value, Consumer<SharedPreferences.Editor> callback);
    }

    /**
     * upadte the private shared preference value.
     *
     */
    public static SpUpdateHandler updatePrivateSharedPreference= new SpUpdateHandler(){
        @Override
        public void update(Context ctx, String spName, String key, Object value, Consumer<SharedPreferences.Editor> callback) {
            SharedPreferences sp = ctx.getSharedPreferences(spName, Activity.MODE_PRIVATE);
            SharedPreferences.Editor edit = sp.edit();
            if(value==null){
                edit.remove(key);
            }else {
                if(callback!=null){
                    try {
                        callback.accept(edit);
                    } catch (Exception e) {
                        LogUt.e(e);
                    }
                }
            }
            edit.apply();
        }
    };
            
    


    /**
     * Save [key]'s value to shared preferences which named [spName].
     *
     * if [value] was null, it would remove the [key] from shared preferences.
     * The update would be process in background, because it's name is ending by charactor 'A'.
     *
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param value the default value, nullable.
     */
    public static void putStringPrivateA(Context ctx,String spName,final String key,final String value){
        updatePrivateSharedPreference.update(ctx,spName,key,value, new Consumer<SharedPreferences.Editor>(){
            @Override
            public void accept(SharedPreferences.Editor ed) {
                ed.putString(key, value);
            }
        });
    }

    /**
     * Save [key]'s value to shared preferences which named [spName].
     *
     * if [value] was null, it would remove the [key] from shared preferences.
     * The update would be process in background, because it's name is ending by charactor 'A'.
     *
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param value the default value, nullable.
     */
    public static void putBooleanPrivateA(Context ctx,String spName,final String key,final boolean value){
        updatePrivateSharedPreference.update(ctx,spName,key,value, new Consumer<SharedPreferences.Editor>(){
            @Override
            public void accept(SharedPreferences.Editor ed) {
                ed.putBoolean(key, value);
            }
        });
    }

    /**
     * Save [key]'s value to shared preferences which named [spName].
     *
     * if [value] was null, it would remove the [key] from shared preferences.
     * The update would be process in background, because it's name is ending by charactor 'A'.
     *
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param value the default value, nullable.
     */
    public static void putIntPrivateA(Context ctx,String spName,final String key,final int value){

        updatePrivateSharedPreference.update(ctx,spName,key,value, new Consumer<SharedPreferences.Editor>(){
            @Override
            public void accept(SharedPreferences.Editor ed) {
                ed.putInt(key, value);
            }
        });

    }

    /**
     * Save [key]'s value to shared preferences which named [spName].
     *
     * if [value] was null, it would remove the [key] from shared preferences.
     * The update would be process in background, because it's name is ending by charactor 'A'.
     *
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param value the default value, nullable.
     */
    public static void putLongPrivateA(Context ctx,String spName,final String key,final long value){
        updatePrivateSharedPreference.update(ctx,spName,key,value, new Consumer<SharedPreferences.Editor>(){
            @Override
            public void accept(SharedPreferences.Editor ed) {
                ed.putLong(key, value);
            }
        });
    }

    /**
     * Save [key]'s value to shared preferences which named [spName].
     *
     * if [value] was null, it would remove the [key] from shared preferences.
     * The update would be process in background, because it's name is ending by charactor 'A'.
     *
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param value the default value, nullable.
     */
    public static void putFloatPrivateA(Context ctx,String spName,final String key,final float value){
        updatePrivateSharedPreference.update(ctx,spName,key,value, new Consumer<SharedPreferences.Editor>(){
            @Override
            public void accept(SharedPreferences.Editor ed) {
                ed.putFloat(key, value);
            }
        });
    }


    /**
     * Save [key]'s value to shared preferences which named [spName].
     *
     * if [value] was null, it would remove the [key] from shared preferences.
     * The update would be process in background, because it's name is ending by charactor 'A'.
     *
     * @param ctx android context.
     * @param spName the shared preferences's name.
     * @param key the key in shared preferences.
     * @param value the default value, nullable.
     */
    public static void  putStringSetPrivateA(Context ctx,String spName,final String key,final Set<String> value){
        updatePrivateSharedPreference.update(ctx,spName,key,value, new Consumer<SharedPreferences.Editor>(){
            @Override
            public void accept(SharedPreferences.Editor ed) {
                ed.putStringSet(key, value);
            }
        });
    }
}
