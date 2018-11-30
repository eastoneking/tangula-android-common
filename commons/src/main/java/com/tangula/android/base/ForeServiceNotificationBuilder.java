package com.tangula.android.base;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.DrawableRes;

/**
 * 为前台服务通知构建器.
 */
@SuppressWarnings("WeakerAccess")
public class ForeServiceNotificationBuilder {

    @SuppressWarnings("WeakerAccess")
    public Notification buildNotification(Context ctx, @DrawableRes int iconResId,
                                          String channelId, String channelName) {

        NotificationManager manager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) {
            return null;
        }

        Notification.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            builder = new Notification.Builder(ctx, channelId);
        } else {
            builder = new Notification.Builder(ctx);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            builder.setLocalOnly(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            builder.setShowWhen(false);
        }


        builder.setContentTitle(channelId).setContentText(channelName)
                .setSmallIcon(iconResId).setAutoCancel(false);

        return builder.build();
    }
}
