package com.tangula.android.utils;

import android.Manifest;
import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * 拨号授权工具.
 */
@SuppressWarnings("unused")
public class CallPermsUtils {

    private static final List<String> CALLPHONE_PERMISSIONS = new LinkedList<>();

    static {
        CALLPHONE_PERMISSIONS.add(Manifest.permission.CALL_PHONE);
    }

    /**
     * 拥有[Manifest.permission.CALL_PHONE]权限，就执行[task],否则执行[onRejectGrantCallback].
     *
     * @param act                   调用所在的Activity.
     * @param task                  要执行的任务.
     * @param onRejectGrantCallback 拒绝授予权限时候的处理方法.
     */
    public static void canMakeACall(Activity act, Runnable task, Runnable onRejectGrantCallback) {
        PermissionUtils.whenHasAnyPremissions(act, CALLPHONE_PERMISSIONS, task, onRejectGrantCallback);
    }
}

