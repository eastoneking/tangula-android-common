package com.tangula.android.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import java.util.LinkedList;
import java.util.List;

import io.reactivex.functions.BiConsumer;

@SuppressWarnings("unused")
public class AndroidSettingsUtils {

    public static void canOverlayOrNot(final Activity act , final Runnable task, final Runnable onNot) {

        List<String> overlay_perms = new LinkedList<>();
        overlay_perms.add("android.permission.SYSTEM_ALERT_WINDOW");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(!Settings.canDrawOverlays(act)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:${act.packageName}"));



                ActivityResultUtils.startActivityForResult(act, intent,new BiConsumer<Integer, Intent>(){
                    @Override
                    public void accept(Integer integer, Intent intent) {
                        if (Settings.canDrawOverlays(act)) {
                            if(task!=null){
                                task.run();
                            }
                        } else {
                            if(onNot!=null){
                                onNot.run();
                            }
                        }
                    }
                });
            }else{
                if(task!=null){
                    task.run();
                }
            }
        }else {
            PermissionUtils.whenHasAllPremissions(act, overlay_perms, task, onNot);
        }

    }
}
