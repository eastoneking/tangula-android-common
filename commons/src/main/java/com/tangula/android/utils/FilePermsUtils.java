package com.tangula.android.utils;


import android.Manifest;
import android.app.Activity;

import java.util.LinkedList;
import java.util.List;


/**
 * 文件读写授权工具.
 */
@SuppressWarnings("unused")
public class FilePermsUtils {

    private static final List<String> SD_RW_PERMISSIONS = new LinkedList<>();
    private static final List<String> SD_R_PERMISSIONS = new LinkedList<>();
    private static final List<String> SD_W_PERMISSIONS = new LinkedList<>();

    static {
        SD_RW_PERMISSIONS.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        SD_RW_PERMISSIONS.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        SD_R_PERMISSIONS.add(Manifest.permission.READ_EXTERNAL_STORAGE);

        SD_W_PERMISSIONS.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

        /**
         * 同时拥有[Manifest.permission.READ_EXTERNAL_STORAGE]和[Manifest.permission.WRITE_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param act 调用所在的Activity.
         * @param task 要执行的任务.
         * @param onRejectCallback 拒绝授予权限时候的处理方法.
         */
        public static void bothReadWrite(Activity act, Runnable task, Runnable onRejectCallback) {
            PermissionUtils.whenHasAllPremissions(act,SD_RW_PERMISSIONS, task, onRejectCallback);
        }
        /**
         * 拥有[Manifest.permission.READ_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param act 调用所在的Activity.
         * @param task 要执行的任务.
         * @param onRejectCallback 拒绝授予权限时候的处理方法.
         */
        public static void  onlyRead(Activity act, Runnable task, Runnable onRejectCallback) {
            PermissionUtils.whenHasAllPremissions(act, SD_R_PERMISSIONS, task, onRejectCallback);
        }
        /**
         * 拥有[Manifest.permission.WRITE_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param act 调用所在的Activity.
         * @param task 要执行的任务.
         * @param onRejectCallback 拒绝授予权限时候的处理方法.
         */
        public static void  onlyWrite(Activity act, Runnable task, Runnable onRejectCallback) {
            PermissionUtils.whenHasAllPremissions(act,SD_W_PERMISSIONS, task, onRejectCallback);
        }

}