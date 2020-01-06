package com.mic;


import android.app.Application;

import androidx.multidex.MultiDex;


public class PlayerApp extends Application {


    //public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        // 设置全局异常捕捉类
        // ExceptionCrashHandler.getInstance().init(this);
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
