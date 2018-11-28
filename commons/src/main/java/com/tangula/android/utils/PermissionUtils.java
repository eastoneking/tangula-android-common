package com.tangula.android.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;

/**
 * 授权验证工具.
 */
public class PermissionUtils {


    /**
     * 本次运行中动态申请授权次数.
     */
    private static int grantCount = 0;

    public static final Object LOCK = new Object();

    /**
     * 获得下一次运行动态授权的Code.
     *
     * @return 下次运行动态授权的Code.
     */
    private static int nextGrantCode() {
        synchronized (LOCK) {
            return ++grantCount;
        }
    }

    @SuppressLint("UseSparseArrays")
    private static Map<Integer, BiConsumer<String[], int[]>> WAITING_PERMISSION_REQUESTS = new HashMap<>();

    /**
     * 检查列表中权限是否都被授予.
     *
     * @param permissions 被检查的列表.
     * @return 检查结果.
     * 如果 [permissions]为空，返回结果中的allPass是true.
     */
    @SuppressWarnings("WeakerAccess")
    public static PermissionCheckResult checkPermissions(List<String> permissions) {
        boolean allPass = true;
        List<String> passed = new LinkedList<>();
        List<String> rejected = new LinkedList<>();

        for (String it : permissions) {
            switch (ActivityCompat.checkSelfPermission(ApplicationUtils.APP, it)) {
                case PackageManager.PERMISSION_GRANTED:
                    passed.add(it);
                    break;
                default:
                    rejected.add(it);
                    allPass = false;
            }
        }
        return new PermissionCheckResult(allPass, passed, rejected);
    }

    /**
     * 动态申请授权.
     * <p>
     * 这个方法检查了SDK API是否大于等于23（Android 6.0），因为动态授权是Android6.0之后提供的.
     *
     * @param act            申请授权的Activity.
     * @param permissions    申请的授权项目.
     * @param callback       用户授权之后的回调函数.
     *                       <p>
     *                       这个参数是个方法，类型为：``(Array<out String>, IntArray)->Unit``，第一个参数是请求的授权
     *                       列表，第二个参数是用户授权结果。开发人员应该在这个回调函数里对授权结果进行检查，当授权不
     *                       能满足要求的时候，应该调用[rejectCallback].
     * @param rejectCallback 用户拒绝授予相关条件时的处理函数.
     */
    @SuppressWarnings("WeakerAccess")
    public static void requestPermissions(Activity act, List<String> permissions, final BiConsumer<String[], int[]> callback, Runnable rejectCallback) {
        final int code = nextGrantCode();

        WAITING_PERMISSION_REQUESTS.put(code, new BiConsumer<String[], int[]>() {
            @Override
            public void accept(String[] ps, int[] res) throws Exception {
                if (callback != null) {
                    callback.accept(ps, res);
                }
                WAITING_PERMISSION_REQUESTS.remove(code);
            }
        });
        String[] arr = new String[permissions.size()];
        permissions.toArray(arr);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            act.requestPermissions(arr, code);
        } else {
            if (rejectCallback != null) {
                rejectCallback.run();
            }
        }
    }


    /**
     * 提供给Acvitiy在[Activity.onRequestPermissionsResult]方法中调用,处理用户授权结果的方法.
     * <p>
     * 请直接在[Activity.onRequestPermissionsResult]方法中调用。
     *
     * @param code         请求调用时指定的Code.
     * @param permisssions 请求被授予权限的列表.
     * @param res          用户授权的结果.
     */
    public static void onRequestPermisionsFinish(int code, String[] permisssions, int[] res) {
        BiConsumer<String[], int[]> func = WAITING_PERMISSION_REQUESTS.get(code);
        if (func != null) {
            try {
                func.accept(permisssions, res);
            } catch (Exception e) {
                LogUt.e(e);
            }
        }
    }

    /**
     * 仅在拥有[permissions]所有授权时执行[task]，否则，不请求缺失的授权改为执行[onNoPerms].
     *
     * @param permissions 需要的授权列表.
     * @param task        拥有完整授权时要执行的任务.
     * @param onNoPerms   没有获得完整授权是执行的任务.
     */
    public static PermissionCheckResult whenHasAllPremissionsNotWithRequestPermissions(List<String> permissions, Runnable task, Consumer<PermissionCheckResult> onNoPerms) {
        PermissionCheckResult res = checkPermissions(permissions);
        if (res.isAllPass()) {
            if (task != null) {
                task.run();
            }
        } else {
            if (onNoPerms != null) {
                try {
                    onNoPerms.accept(res);
                } catch (Exception e) {
                    LogUt.e(e);
                }
            }
        }
        return res;
    }

    /**
     * 当APP拥有所有[permissions]列表中的授权时运行[task]，否则运行[rejectCallback].
     * <p>
     * 函数首先检查是否拥有所有授权，如果拥有全部授权，就执行[task]；否则，则请求授予缺失的授权，
     * 并且在用户授予全部确实的授权后，执行[task],如果用户仍然不授予全部权限，则执行
     * [rejectCallback].
     *
     * @param act            调用的Acvitity.
     * @param permissions    需要的授权列表.
     * @param task           拥有完整授权时要执行的任务.
     * @param rejectCallback 无法获得完整授权是执行的任务.
     */
    public static void whenHasAllPremissions(final Activity act, List<String> permissions, final Runnable task, final Runnable rejectCallback) {
        whenHasAllPremissionsNotWithRequestPermissions(permissions, task, new Consumer<PermissionCheckResult>() {
            @Override
            public void accept(PermissionCheckResult it) {
                //只有在Android 6.0下才会有动态申请权限的效果,requestPermissions(...)内有检查,
                //低版本直接调用rejectCallback.
                requestPermissions(act, it.getRejectPermission(),
                        //授权之后的回调函数
                        new BiConsumer<String[], int[]>() {
                            @Override
                            public void accept(String[] strings, int[] res) {
                                boolean result = true;
                                for (int c : res) {
                                    result = c == PackageManager.PERMISSION_GRANTED;
                                    if (!result) {
                                        break;
                                    }
                                }
                                if (result) {
                                    if (task != null) {
                                        task.run();
                                    }
                                } else {
                                    if (rejectCallback != null) {
                                        rejectCallback.run();
                                    }
                                }
                            }
                        }, rejectCallback);
            }
        });
    }

    /**
     * 当用户第一次授权所有[permissions]列表中的授权时运行[task]，否则运行[rejectCallback].
     * <p>
     * 函数首先检查是否拥有所有授权，如果拥有全部授权，则什么都不执行；否则，则请求授予缺失的授权，
     * 并且在用户授予全部确实的授权后，执行[task],如果用户仍然不授予全部权限，则执行
     * [rejectCallback].
     * <p>
     * 这是说系统在启动过程中，或者说本Activity出现之前，已经调用了某些涉及授权的代码，但是不能
     * 保证运行时是否已经启动，而这里的授权就是为了检查权限完整性，亡羊补牢.
     *
     * @param act            调用的Acvitity.
     * @param permissions    需要的授权列表.
     * @param task           拥有完整授权时要执行的任务.
     * @param rejectCallback 无法获得完整授权是执行的任务.
     */

    @SuppressWarnings("unused")
    public static void whenFirstGrantPremissions(final Activity act, List<String> permissions, final Runnable task, final Runnable rejectCallback) {
        whenHasAllPremissionsNotWithRequestPermissions(permissions, null, new Consumer<PermissionCheckResult>() {
            @Override
            public void accept(PermissionCheckResult it) {
                //只有在Android 6.0下才会有动态申请权限的效果,requestPermissions(...)内有检查,
                //低版本直接调用rejectCallback.
                requestPermissions(act, it.getRejectPermission(),
                        new BiConsumer<String[], int[]>() {
                            @Override
                            public void accept(String[] strings, int[] res) {
                                boolean result = true;
                                for (int c : res) {
                                    result = c == PackageManager.PERMISSION_GRANTED;
                                    if (!result) {
                                        break;
                                    }
                                }
                                if (result) {
                                    if (task != null) {
                                        task.run();
                                    }
                                } else {
                                    if (rejectCallback != null) {
                                        rejectCallback.run();
                                    }
                                }
                            }
                        }, rejectCallback);
            }
        });

    }

    /**
     * 在拥有[permissions]任意一项授权时执行[task]，否则，不请求缺失的授权改为执行[onNoPerms].
     *
     * @param permissions 需要检查的授权列表.
     * @param task        拥有任意一项授权时要执行的任务.
     * @param onNoPerms   没有获得任意一项授权是执行的任务.
     */
    @SuppressWarnings("WeakerAccess")
    public static void whenHasAnyPremissionsNotWithRequestPermissions(List<String> permissions, Runnable task, Runnable onNoPerms) {
        PermissionCheckResult res = checkPermissions(permissions);

        if (!res.getPassedPermissions().isEmpty()) {
            if (task != null) {
                task.run();
            }
        } else {
            if (onNoPerms != null) {
                onNoPerms.run();
            }
        }
    }

    /**
     * 当APP拥有[permissions]列表中任意一项授权时运行[task]，否则运行[rejectCallback].
     * <p>
     * 函数首先检查[permissions]列表中权限被授予的情况，如果被授予列表中不为空，不执行任何代码；
     * 否则，则请求授予[permissions]列表中所有权限，并且在用户授予其中任何一项时，执行[task],如
     * 果用户仍然不授予任何权限，则执行[rejectCallback].
     * <p>
     * 这是说系统在启动过程中，或者说本Activity出现之前，已经调用了某些涉及授权的代码，但是不能
     * 保证运行时是否已经启动，而这里的授权就是为了检查权限完整性，亡羊补牢.
     *
     * @param act            调用的Acvitity.
     * @param permissions    需要检查的授权列表.
     * @param task           拥有任意一项授权时要执行的任务.
     * @param rejectCallback 无法获得任意一项授权是执行的任务.
     */
    @SuppressWarnings("unused")
    public static void whenFirstGrantedAnyPremissions(final Activity act, final List<String> permissions, final Runnable task, final @Nullable Runnable rejectCallback) {
        whenHasAnyPremissionsNotWithRequestPermissions(permissions, new Runnable() {
            @Override
            public void run() {
            }
        }, new Runnable() {
            @Override
            public void run() {

                //只有在Android 6.0下才会有动态申请权限的效果,requestPermissions(...)内有检查,
                //低版本直接调用rejectCallback.
                requestPermissions(act, permissions,
                        //授权之后的回调函数
                        new BiConsumer<String[], int[]>() {
                            @Override
                            public void accept(String[] strings, int[] res) {
                                boolean result = false;
                                for (int c : res) {
                                    if (c == PackageManager.PERMISSION_GRANTED) {
                                        result = true;
                                        break;
                                    }
                                }
                                if (result) {
                                    if (task != null) {
                                        task.run();
                                    }
                                } else {
                                    if (rejectCallback != null) {
                                        rejectCallback.run();
                                    }
                                }
                            }
                        }, rejectCallback);
            }
        });
    }

    /**
     * 当APP拥有[permissions]列表中任意一项授权时运行[task]，否则运行[rejectCallback].
     * <p>
     * 函数首先检查[permissions]列表中权限被授予的情况，如果被授予列表中不为空，就执行[task]；
     * 否则，则请求授予[permissions]列表中所有权限，并且在用户授予其中任何一项时，执行[task],如
     * 果用户仍然不授予任何权限，则执行[rejectCallback].
     *
     * @param act            调用的Acvitity.
     * @param permissions    需要检查的授权列表.
     * @param task           拥有任意一项授权时要执行的任务.
     * @param rejectCallback 无法获得任意一项授权是执行的任务.
     */
    public static void whenHasAnyPremissions(final Activity act, final List<String> permissions, final Runnable task, final Runnable rejectCallback) {

        whenHasAnyPremissionsNotWithRequestPermissions(permissions, task, new Runnable() {
            @Override
            public void run() {
                //只有在Android 6.0下才会有动态申请权限的效果,requestPermissions(...)内有检查,
                //低版本直接调用rejectCallback.
                requestPermissions(act, permissions,
                        //授权之后的回调函数
                        new BiConsumer<String[], int[]>() {
                            @Override
                            public void accept(String[] strings, int[] res) {
                                boolean result = false;
                                for (int c : res) {
                                    if (c == PackageManager.PERMISSION_GRANTED) {
                                        result = true;
                                        break;
                                    }
                                }
                                if (result) {
                                    if (task != null) {
                                        task.run();
                                    }
                                } else {
                                    if (rejectCallback != null) {
                                        rejectCallback.run();
                                    }
                                }
                            }
                        }, rejectCallback);
            }
        });
    }


}
