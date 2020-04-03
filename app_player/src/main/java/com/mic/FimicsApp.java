package com.mic;


import android.app.Application;

import androidx.multidex.MultiDex;


public class FimicsApp extends Application {



    private static Application context;

    @Override
    public void onCreate() {
        super.onCreate();
        context= this;
        MultiDex.install(this);
        // 设置全局异常捕捉类
        // ExceptionCrashHandler.getInstance().init(this);
    }

    public static Application getAppContext(){
        return context;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
}
