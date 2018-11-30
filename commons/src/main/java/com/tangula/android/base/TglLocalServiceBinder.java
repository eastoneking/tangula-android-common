package com.tangula.android.base;

import android.app.Service;
import android.os.Binder;
import android.os.IBinder;

public class TglLocalServiceBinder extends Binder implements IServiceBinder {


    private Service serviceInstance;


    @Override
    public Service getServiceInstance() {
        return this.serviceInstance;
    }

    @Override
    public void setServiceInstance(Service serv) {
        this.serviceInstance = serv;
    }

    public int onStart()  {
        return Service.START_STICKY;
    }

    public void close() {
    }

    public void onLowMemory() {
    }

    public void onTrimMemory(int level) {
    }

    public void onDestroy() {
    }

    public IBinder asBinder() {
        return this;
    }

}


