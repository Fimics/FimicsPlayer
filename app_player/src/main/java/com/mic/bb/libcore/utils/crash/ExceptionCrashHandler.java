package com.mic.bb.libcore.utils.crash;


import android.content.Context;

/**
 * 全局异常捕获类
 */

@SuppressWarnings("unused")
public class ExceptionCrashHandler  implements Thread.UncaughtExceptionHandler{

    private static final String TAG ="ExceptionCrashHandler";
    private Context context;
    private Thread.UncaughtExceptionHandler mDefexceptionHandler;


    private static  class Holder{
       private static final ExceptionCrashHandler INSTANCE = new ExceptionCrashHandler();
    }

    public static ExceptionCrashHandler getInstance(){
        return Holder.INSTANCE;
    }

    public void init(Context context){
        this.context = context;
        Thread.currentThread().setUncaughtExceptionHandler(this);
        mDefexceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        /**
         * 系统默认处理
         */
        mDefexceptionHandler.uncaughtException(t,e);
        //
    }
}
