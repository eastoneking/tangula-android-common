package com.tangula.android.utils;

import android.Manifest;
import android.app.Activity;

import java.util.LinkedList;
import java.util.List;


/**
 * 网络授权工具.
 */
@SuppressWarnings("unused")
public class NetwordPermsUtils {

    private static final List<String> INTERNET_PERMISSIONS = new LinkedList<>();

    static {
        INTERNET_PERMISSIONS.add(Manifest.permission.INTERNET);
    }

    /**
     * 拥有[Manifest.permission.INTERNET]权限时，就执行[task],否则执行[onRejectCallback].
     *
     * @param act 调用所在的Activity.
     * @param task 要执行的任务.
     * @param onRejectGrantCallback 拒绝授予权限时候的处理方法.
     */
    public static void whenCanAccessInternet(Activity act, Runnable task, Runnable onRejectGrantCallback) {
        PermissionUtils.whenHasAllPremissions(act, INTERNET_PERMISSIONS, task, onRejectGrantCallback);
    }
}
