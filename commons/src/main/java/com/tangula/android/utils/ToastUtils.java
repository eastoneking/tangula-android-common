package com.tangula.android.utils;

import android.widget.Toast;

public class ToastUtils {


    /**
     * Show text as toast with special duration.
     * @param text The messasge would show in toast.
     * @param duration The display time length.
     */
    @SuppressWarnings({"WeakerAccess"})
    public  static void showToast(final String text,final int duration) {
        TaskUtils.runInUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ApplicationUtils.APP, text, duration).show();
            }
        });
    }

    /**
     * Show text as toast with special duration.
     * @param resId The messasge resource id which would show in toast.
     * @param duration The display time length.
     */
    @SuppressWarnings({"WeakerAccess"})
    public  static void  showToastRes(final int resId,final int duration) {
        TaskUtils.runInUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ApplicationUtils.APP, resId, duration).show();
            }
        });
    }

    /**
     * Show text as toast long time.
     * @param text The messasge would show in toast.
     */
    @SuppressWarnings({"unused","WeakerAccess"})
    public static void showToastLong(String text) {
        showToast(text, Toast.LENGTH_LONG);
    }


    /**
     * Show text as toast long time.
     * @param resId The messasge would show in toast.
     */
    @SuppressWarnings({"unused","WeakerAccess"})
    public static void showToastResLong(int resId) {
        showToastRes(resId, Toast.LENGTH_LONG);
    }

    /**
     * Show text as toast short time.
     * @param text The messasge would show in toast.
     */
    @SuppressWarnings({"unused","WeakerAccess"})
    public static void showToastShort(String text) {
        showToast(text, Toast.LENGTH_SHORT);
    }

    /**
     * Show text as toast short time.
     * @param resId The messasge would show in toast.
     */
    @SuppressWarnings({"unused","WeakerAccess"})
    public static void showToastResShort(int resId) {
        showToastRes(resId, Toast.LENGTH_SHORT);
    }


}
