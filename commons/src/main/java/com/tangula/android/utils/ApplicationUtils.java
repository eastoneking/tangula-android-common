package com.tangula.android.utils;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.Settings;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Application Utilitities.
 * <p>Some functions which help you handle the application scope processors.</p>
 *
 * @author Dose &middot; King &lt;doss.king@outlook.com&gt;
 */
public class ApplicationUtils {

    private static final String KEY_UNIQUE_ID_SP_NAME = "KEY_UNIQUE_ID_SP_NAME";
    private static final String KEY_UNIQUE_ID_IN_SP = "KEY_UNIQUE_ID_IN_SP";

    /**
     * The current application object.
     * <p>
     *     You can define a custom application class, and save the application
     *     with this property.
     * </p>
     */

    public static Application APP;

    /**
     * To fetch the android id.
     * @return The runtime device's android id.
     */
    @SuppressWarnings("WeakerAccess")
    public static String getAndroidId(Context context) {
        return Settings.System.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
    }


    /**
     * To create an unique id of an user.
     * <p>This function's resuilt is not real unique id.</p>
     */
    public static String fetchUniqueId(Context ctx) {

        //use string builder to save the ABIS info.
        StringBuilder buffer = new StringBuilder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            for(String it : Build.SUPPORTED_ABIS){
                buffer.append(it).append(",");
            }
        }

        //create the unique id

        String str = buffer.append(getAndroidId(ctx)).append(Build.BOARD).append(Build.BRAND)
                .append(Build.DEVICE).append(Build.DISPLAY).append(Build.HOST)
                .append(Build.ID).append(Build.TAGS).append(Build.TYPE).append(Build.USER)
                .toString();

        return UUID.nameUUIDFromBytes(str.getBytes(Charset.forName("UTF8"))).toString();
    }

    /**
     * To clear the unique id which be stored in shared preferences.
     */
    public static void clearUniqueId(Context ctx) {
        SharedPreferences sp = ctx.getSharedPreferences(KEY_UNIQUE_ID_SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(KEY_UNIQUE_ID_IN_SP);
        editor.apply();
    }


    /**
     * To update the unique id in shared preferences.
     */
    @SuppressWarnings("WeakerAccess")
    public static String storeUniqueId(Context ctx)  {
        SharedPreferences sp = ctx.getSharedPreferences(KEY_UNIQUE_ID_SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String id = fetchUniqueId(ctx);
        editor.putString(KEY_UNIQUE_ID_IN_SP, id);
        editor.apply();
        return id;
    }

    /**
     * To load the unique id which store in shared preferences,if not have it, create and save one unique id, and return it.
     */
    public static String loadUniqueId(Context ctx) {
        String res = ctx.getSharedPreferences(KEY_UNIQUE_ID_SP_NAME, Context.MODE_PRIVATE)
                .getString(KEY_UNIQUE_ID_IN_SP, "");
         if(StringUtils.isNotBlank(res) ) {
             return res;
         }else {
             return storeUniqueId(ctx);
         }
    }


}