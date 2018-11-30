package com.tangula.android.base;

import android.app.Service;
import android.os.IBinder;
import android.os.IInterface;

public interface IServiceBinder extends IInterface, IBinder {

    Service getServiceInstance();

    void setServiceInstance(Service serv);

    /**
     * 每次bind或者start的时候执行.
     *
     * @return 参见:[Service.onStartCommand]
     */
    int onStart();

    /**
     * 服务被unbind的时候被执行.
     */
    void close();

    void onLowMemory();

    void onTrimMemory(int level);

    void onDestroy();
}