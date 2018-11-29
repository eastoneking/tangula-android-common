package com.tangula.android.utils;

import android.app.Activity;
import android.content.Intent;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.BiConsumer;

public class ActivityResultUtils {

    /**
     * 本次运行Activity代码.
     */
    private static int CUR_ACTIVITY_CODE = 0;

    /**
     * 获得下一次运行Activity的Code.
     * @return 下次运行Activity的Code.
     */
    private static int nextActivityCode(){
        synchronized(com.tangula.android.utils.PermissionUtils.LOCK) {
            return ++CUR_ACTIVITY_CODE;
        }
    }

    private static Map<Integer, BiConsumer<Integer, Intent>> WAITING_RESULT_ACTIVITIES = new HashMap<>();


    public static void startActivityForResult(Activity act, Intent intent, final BiConsumer<Integer, Intent> callback) {

        final int code = nextActivityCode();

        WAITING_RESULT_ACTIVITIES.put(code, new BiConsumer<Integer, Intent>() {
            @Override
            public void accept(Integer res, Intent data) throws Exception {
                try {
                    if(callback!=null){
                        callback.accept(res, data);
                    }
                }finally {
                    WAITING_RESULT_ACTIVITIES.remove(code);
                }
            }
        });


        act.startActivityForResult(intent,code);

    }

    public static void onActivityResult(int code, int resultCode, Intent data) {

        if(WAITING_RESULT_ACTIVITIES.containsKey(code)){
            BiConsumer<Integer, Intent> task = WAITING_RESULT_ACTIVITIES.get(code);
            if(task!=null){
                try {
                    task.accept(resultCode, data);
                } catch (Exception e) {
                    LogUt.e(e);
                }
            }
        }
    }


}
