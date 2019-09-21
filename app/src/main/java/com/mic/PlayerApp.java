package com.mic;


import android.app.Application;

public class PlayerApp extends Application {

    //public static PatchManager mPatchManager;

    @Override
    public void onCreate() {
        super.onCreate();

        // 设置全局异常捕捉类
        // ExceptionCrashHandler.getInstance().init(this);


    }
}
