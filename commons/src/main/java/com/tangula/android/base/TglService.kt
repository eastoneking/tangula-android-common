package com.tangula.android.base

import android.annotation.SuppressLint
import android.app.*
import android.content.*
import android.os.Binder
import android.os.IBinder
import android.os.IInterface
import android.support.annotation.DrawableRes
import android.util.Log
import com.tangula.android.utils.ApplicationUtils
import com.tangula.android.utils.SdkVersionDecides

/**
 * 前台服务元数据.
 */
data class ForegroundServiceMetaInfo(val serviceId: Int, val iconRes: Int, val channelId: String, val channelName: String?)


/**
 * 为前台服务通知构建器.
 */
class ForeServiceNotificationBuilder {
    @SuppressLint("NewApi")
    fun buildNotification(ctx: Context, @DrawableRes iconResId: Int, channelId: String, channelName: String?): Notification {

        val manager: NotificationManager = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: Notification.Builder

        builder = if (SdkVersionDecides.afterSdk26A8d0()) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
            Notification.Builder(ctx, channelId)
        } else {
            Notification.Builder(ctx)
        }

        SdkVersionDecides.afterSdk20A4d4W {
            builder.setLocalOnly(true)
        }

        SdkVersionDecides.afterSdk17A4d2 {
            builder.setShowWhen(false)
        }

        builder.setContentTitle(channelId).setContentText(channelName)
                .setSmallIcon(iconResId).setAutoCancel(false)

        return builder.build()
    }
}

interface IServiceBinder:IInterface,IBinder{

    var serviceInstance : Service?

    /**
     * 每次bind或者start的时候执行.
     * @return 参见:[Service.onStartCommand]
     */
    fun onStart():Int

    /**
     *  服务被unbind的时候被执行.
     */
    fun close()

    fun onLowMemory()

    fun onTrimMemory(level: Int)

    fun onDestroy()
}

open class TglLocalServiceBinder:Binder(), IServiceBinder{

    override var serviceInstance : Service? = null

    override fun onStart(): Int {
        return Service.START_STICKY
    }

    override fun close() {
    }

    override fun onLowMemory() {
    }

    override fun onTrimMemory(level: Int) {
    }

    override fun onDestroy() {
    }

    override fun asBinder(): IBinder {
        return this
    }
}



abstract class TglService<S: IServiceBinder> : Service() {

    companion object {

        val BIND_FLAG_AUTO_CRATE_AND_ALWAY_START = Context.BIND_WAIVE_PRIORITY or Context.BIND_AUTO_CREATE

        val BIND_FLAG_AUTO_CRATE_AND_ALWAY_START_IN_BG = Context.BIND_WAIVE_PRIORITY or Context.BIND_AUTO_CREATE or Context.BIND_NOT_FOREGROUND

        protected val BINDERS:MutableList<IServiceBinder> = mutableListOf()

        fun <S: IServiceBinder> startService(act: Activity?, serviceType:Class<out TglService<S>>) {
            if (act is Activity) {
                    act.startService(Intent(act, serviceType))
            }else{
                ApplicationUtils.APP.startService(Intent(ApplicationUtils.APP, serviceType))
            }

        }

        fun <S:IServiceBinder> bind2Service(ctx:Context, serviceType: Class<out TglService<S>>, flags:Int, afterConnected:((S)->Unit)?, onDisConnect:((ComponentName?)->Unit)?, onBindFail:(()->Unit)?): ServiceConnection{
            val intent = Intent(ctx, serviceType)
            val conn = object: ServiceConnection {
                var binder: S? = null
                override fun onServiceDisconnected(name: ComponentName?) {
                    onDisConnect?.also { it(name) }
                    binder?.also {
                        it.close()
                        BINDERS.remove(it)
                    }
                }
                override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                    if(binder is IServiceBinder){
                        afterConnected?.also { it(binder!! as S) }
                    }else{
                        onBindFail?.also { it() }
                    }
                }
            }
            ctx.bindService(intent, conn,flags)
            return conn
        }

        fun <I:IServiceBinder>  bind2NormalService(ctx:Context, serviceType: Class<out TglService<I>>,afterConnected:((I)->Unit)?, onDisConnect:((ComponentName?)->Unit)?, onBindFail:(()->Unit)?):ServiceConnection{
            return bind2Service(ctx, serviceType, BIND_FLAG_AUTO_CRATE_AND_ALWAY_START,afterConnected , onDisConnect, onBindFail)
        }

        fun <I:IServiceBinder>  bind2BackgroundService(ctx:Context, serviceType: Class<out TglService<I>>,afterConnected:((I)->Unit)?, onDisConnect:((ComponentName?)->Unit)?, onBindFail:(()->Unit)?):ServiceConnection{
            return bind2Service(ctx, serviceType, BIND_FLAG_AUTO_CRATE_AND_ALWAY_START_IN_BG,afterConnected , onDisConnect, onBindFail)
        }

    }


    /**
     * 是否显示在前台.
     */
    var foreGroundInfo: ForegroundServiceMetaInfo? = null

    var isSingleton = true

    /**
     * 服务实现对象.
     */
    var instance: S? = null

    override fun onBind(intent: Intent?): IBinder {
        if(isSingleton){
            if(instance == null){
                synchronized(this) {
                     if(instance==null) {
                         instance = createBinder(intent, -1, -1)
                     }
                }
            }

            return instance!!.also { it.onStart() }
        }
        return createBinder(intent, -2, -2).also { it.onStart() }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        if(isSingleton){
            synchronized(this){
                instance?.also {
                    it.close()
                }
            }
        }
        return super.onUnbind(intent)
    }

    abstract fun onCreateBinder(intent:Intent?,flags:Int, startId:Int):S

    fun createBinder(intent: Intent?, flags: Int, startId: Int): S{
        Log.v("console","TglService::createBinder")
        return onCreateBinder(intent, flags, startId).also {
            it.serviceInstance =  this
            BINDERS.add(it)
        }
    }

    override fun onCreate() {
        Log.v("console","TglService::onCreate")
        super.onCreate()
        if(isSingleton) {
            instance = createBinder(null, -1, -1)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v("console","TglService::onStartCommand")
        foreGroundInfo?.also {
            startForeground(it.serviceId, ForeServiceNotificationBuilder().buildNotification(applicationContext, it.iconRes, it.channelId, it.channelName))
        }

        if(isSingleton){
            return instance?.onStart()?: START_STICKY
        }else{
            return createBinder(null, flags, startId).onStart()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        BINDERS.forEach{
            it.onLowMemory()
        }
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        BINDERS.forEach{
            it.onTrimMemory(level)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        BINDERS.forEach{
            it.onDestroy()
        }
    }

}
