package com.tangula.android.utils;


import android.Manifest;
import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * 定位授权工具.
 */
public class LocationPermsUtils {

    private static final List<String> LOCATION_PERMISSIONS = new LinkedList<>();

    static {
        LOCATION_PERMISSIONS.add(Manifest.permission.ACCESS_FINE_LOCATION);
        LOCATION_PERMISSIONS.add(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    /**
     * 只要有任何定位权限，就执行[task],否则执行[onRejectGrantCallback].
     *
     * @param act 调用所在的Activity.
     * @param task 要执行的任务.
     * @param onRejectGrantCallback 拒绝授予权限时候的处理方法.
     */
    public static void canAccessLocation(Activity act, Runnable task, Runnable onRejectGrantCallback)

    {
        PermissionUtils.whenHasAnyPremissions(act, LOCATION_PERMISSIONS, task, onRejectGrantCallback);
    }


}
