package com.tangula.android.base;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.tangula.android.utils.ApplicationUtils;
import com.tangula.android.utils.LogUt;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import kotlin.Unit;

public abstract class TglService<S extends IServiceBinder> extends Service {

    private static final int BIND_FLAG_AUTO_CRATE_AND_ALWAY_START = Context.BIND_WAIVE_PRIORITY | Context.BIND_AUTO_CREATE;


    private static final int BIND_FLAG_AUTO_CRATE_AND_ALWAY_START_IN_BG = Context.BIND_WAIVE_PRIORITY | Context.BIND_AUTO_CREATE | Context.BIND_NOT_FOREGROUND;

    protected static final List<IServiceBinder> BINDERS = new ArrayList<>(5);


    public static <S extends IServiceBinder> void startService(Activity act, Class<? extends TglService<S>> serviceType) {
        if (act != null) {
            act.startService(new Intent(act, serviceType));
        } else {
            ApplicationUtils.APP.startService(new Intent(ApplicationUtils.APP, serviceType));
        }

    }


    public static <S extends IServiceBinder> ServiceConnection bind2Service(Context ctx,
                                                                            Class<? extends TglService<S>> serviceType,
                                                                            int flags,
                                                                            final Consumer<S> afterConnected,
                                                                            final Consumer<ComponentName> onDisConnect,
                                                                            final Runnable onBindFail) {
        Intent intent = new Intent(ctx, serviceType);
        ServiceConnection conn = new ServiceConnection() {
            private S binder = null;

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                if (binder != null) {
                    try {
                        afterConnected.accept(binder);
                    } catch (Exception e) {
                        LogUt.e(e);
                    }
                } else {
                    if (onBindFail != null) {
                        onBindFail.run();
                    }
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

                if (onDisConnect != null) {
                    try {
                        onDisConnect.accept(name);
                    } catch (Exception e) {
                        LogUt.e(e);
                    }
                }

                if (binder != null) {
                    binder.close();
                    BINDERS.remove(binder);
                }

            }

        };
        ctx.bindService(intent, conn, flags);
        return conn;
    }

    public static <I extends IServiceBinder> ServiceConnection bind2NormalService(Context ctx, Class<? extends TglService<I>> serviceType, final Consumer<I> afterConnected,
                                                                                  final Consumer<ComponentName> onDisConnect,
                                                                                  final Runnable onBindFail) {
        return bind2Service(ctx, serviceType, BIND_FLAG_AUTO_CRATE_AND_ALWAY_START, afterConnected, onDisConnect, onBindFail);
    }

    public static <I extends IServiceBinder> ServiceConnection bind2BackgroundService(Context ctx, Class<? extends TglService<I>> serviceType, final Consumer<I> afterConnected,
                                                                                      final Consumer<ComponentName> onDisConnect,
                                                                                      final Runnable onBindFail) {
        return bind2Service(ctx, serviceType, BIND_FLAG_AUTO_CRATE_AND_ALWAY_START_IN_BG, afterConnected, onDisConnect, onBindFail);
    }


    /**
     * 是否显示在前台.
     */
    private ForegroundServiceMetaInfo foreGroundInfo = null;

    private boolean isSingleton = true;

    /**
     * 服务实现对象.
     */
    private S instance = null;

    public IBinder onBind(Intent intent) {
        S res;
        if (isSingleton) {
            if (instance == null) {
                synchronized (this) {
                    if (instance == null) {
                        instance = createBinder(intent, -1, -1);
                    }
                }
            }
            res = instance;
        }else {
            res = createBinder(intent, -2, -2);
        }
        if (res != null) {
            res.onStart();
        }
        return res;
    }

    public boolean onUnbind(Intent intent){
        if (isSingleton) {
            synchronized (this) {
                if(instance!=null){
                    instance.close();
                }
            }
        }
        return super.onUnbind(intent);
    }

    public abstract S onCreateBinder(Intent intent, int flags, int startId);

    public S createBinder(Intent intent, int flags, int startId)

    {
        S res = onCreateBinder(intent, flags, startId);
        if (res != null) {
            res.setServiceInstance(this);
            BINDERS.add(res);
        }
        return res;
    }

    public void onCreate() {
        super.onCreate();
        if (isSingleton) {
            instance = createBinder(null, -1, -1);
        }
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if(foreGroundInfo!=null) {
            startForeground(foreGroundInfo.getServiceId(), new ForeServiceNotificationBuilder().buildNotification(this.getApplicationContext(), foreGroundInfo.getIconRes(), foreGroundInfo.getChannelId(), foreGroundInfo.getChannelName()));
        }

        if (isSingleton) {

            if(instance!=null){
                return instance.onStart();
            }else{
                return START_STICKY;
            }

        } else {
            return createBinder(null, flags, startId).onStart();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        for(IServiceBinder it:BINDERS){
            it.onLowMemory();
        }
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for(IServiceBinder it:BINDERS){
            it.onTrimMemory(level);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        for(IServiceBinder it:BINDERS){
            it.onDestroy();
        }
    }

}
