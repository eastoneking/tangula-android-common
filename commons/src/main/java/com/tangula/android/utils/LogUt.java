package com.tangula.android.utils;

import android.util.Log;

import org.apache.commons.lang3.StringUtils;


public final class LogUt {

    private static final int LOG_LEVEL_MASK_VERB = 0x01;
    private static final int LOG_LEVEL_MASK_DEBUG = 0x02;
    private static final int LOG_LEVEL_MASK_INFO = 0x04;
    private static final int LOG_LEVEL_MASK_WARN = 0x08;
    private static final int LOG_LEVEL_MASK_ERROR = 0x10;


    private static final int LOG_LEVEL_ERROR = LOG_LEVEL_MASK_ERROR;
    private static final int LOG_LEVEL_WARN = LOG_LEVEL_ERROR|LOG_LEVEL_MASK_WARN;
    private static final int LOG_LEVEL_INFO = LOG_LEVEL_WARN|LOG_LEVEL_MASK_INFO;
    private static final int LOG_LEVEL_DEBUG = LOG_LEVEL_INFO|LOG_LEVEL_MASK_DEBUG;
    private static final int LOG_LEVEL_VERB = LOG_LEVEL_DEBUG|LOG_LEVEL_MASK_VERB;

    private static int LOG_LEVEL = LOG_LEVEL_VERB;

    @SuppressWarnings("WeakerAccess")
    public static String TAG = "console";

    @SuppressWarnings("WeakerAccess")
    public static boolean isVerbose(){
        return (LOG_LEVEL&LOG_LEVEL_MASK_VERB)>0;
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean isDebug(){
        return (LOG_LEVEL&LOG_LEVEL_MASK_DEBUG)>0;
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean isInfo(){
        return (LOG_LEVEL&LOG_LEVEL_MASK_INFO)>0;
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean isWarn(){
        return (LOG_LEVEL&LOG_LEVEL_MASK_WARN)>0;
    }

    @SuppressWarnings("WeakerAccess")
    public static boolean isError(){
        return (LOG_LEVEL&LOG_LEVEL_MASK_ERROR)>0;
    }

    @SuppressWarnings("WeakerAccess")
    public static void v(String msg){
        if(isVerbose()&&StringUtils.isNotEmpty(msg)){
            Log.v(TAG, msg);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static void v(String msg, Throwable t){
        if(isVerbose()&&StringUtils.isEmpty(msg)&&t!=null){
            msg = t.getLocalizedMessage();
        }
        Log.v(TAG, msg, t);
    }

    public static void v(Throwable t){
        if(isVerbose()&&t!=null){
            Log.v(TAG, t.getLocalizedMessage(), t);
        }
    }

    public static void v(LogMsgFactory msgFac){
        if(isVerbose()&&msgFac!=null){
            v(msgFac.buildMessage());
        }
    }


    public static void v(LogMsgFactory msgFac, Throwable t){
        if(isVerbose()&&msgFac!=null){
            v(msgFac.buildMessage(), t);
        }
    }


    @SuppressWarnings("WeakerAccess")
    public static void d(String msg){
        if(isDebug()&&StringUtils.isNotEmpty(msg)){
            Log.d(TAG, msg);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static void d(String msg, Throwable t){
        if(isDebug()&&StringUtils.isEmpty(msg)&&t!=null){
            msg = t.getLocalizedMessage();
        }
        Log.d(TAG, msg, t);
    }

    public static void d(Throwable t){
        if(isDebug()&&t!=null){
            Log.d(TAG, t.getLocalizedMessage(), t);
        }
    }

    public static void d(LogMsgFactory msgFac){
        if(isDebug()&&msgFac!=null){
            d(msgFac.buildMessage());
        }
    }


    public static void d(LogMsgFactory msgFac, Throwable t){
        if(isDebug()&&msgFac!=null){
            d(msgFac.buildMessage(), t);
        }
    }



    public static void i(String msg){
        if(isInfo()&&StringUtils.isNotEmpty(msg)){
            Log.i(TAG, msg);
        }
    }

    public static void i(String msg, Throwable t){
        if(isInfo()&&StringUtils.isEmpty(msg)&&t!=null){
            msg = t.getLocalizedMessage();
        }
        Log.i(TAG, msg, t);
    }

    public static void i(Throwable t){
        if(isInfo()&&t!=null){
            Log.i(TAG, t.getLocalizedMessage(), t);
        }
    }

    public static void i(LogMsgFactory msgFac){
        if(isInfo()&&msgFac!=null){
            i(msgFac.buildMessage());
        }
    }


    public static void i(LogMsgFactory msgFac, Throwable t){
        if(isInfo()&&msgFac!=null){
            i(msgFac.buildMessage(), t);
        }
    }



    @SuppressWarnings("WeakerAccess")
    public static void w(String msg){
        if(isWarn()&&StringUtils.isNotEmpty(msg)){
            Log.w(TAG, msg);
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static void w(String msg, Throwable t){
        if(isWarn()&&StringUtils.isEmpty(msg)&&t!=null){
            msg = t.getLocalizedMessage();
        }
        Log.w(TAG, msg, t);
    }

    public static void w(Throwable t){
        if(isWarn()&&t!=null){
            Log.w(TAG, t.getLocalizedMessage(), t);
        }
    }

    public static void w(LogMsgFactory msgFac){
        if(isWarn()&&msgFac!=null){
            w(msgFac.buildMessage());
        }
    }


    public static void w(LogMsgFactory msgFac, Throwable t){
        if(isWarn()&&msgFac!=null){
            w(msgFac.buildMessage(), t);
        }
    }




    public static void e(String msg){
        if(isError()&&StringUtils.isNotEmpty(msg)){
            Log.e(TAG, msg);
        }
    }

    public static void e(String msg, Throwable t){
        if(isError()&&StringUtils.isEmpty(msg)&&t!=null){
            msg = t.getLocalizedMessage();
        }
        Log.e(TAG, msg, t);
    }

    public static void e(Throwable t){
        if(isError()&&t!=null){
            Log.e(TAG, t.getLocalizedMessage(), t);
        }
    }

    public static void e(LogMsgFactory msgFac){
        if(isError()&&msgFac!=null){
            e(msgFac.buildMessage());
        }
    }


    public static void e(LogMsgFactory msgFac, Throwable t){
        if(isError()&&msgFac!=null){
            e(msgFac.buildMessage(), t);
        }
    }

    private LogUt(){}


}
