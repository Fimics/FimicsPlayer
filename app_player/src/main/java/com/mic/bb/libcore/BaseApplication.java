package com.mic.bb.libcore;

import android.app.Application;

import com.mic.bb.libcore.utils.crash.ExceptionCrashHandler;

public class BaseApplication  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ExceptionCrashHandler.getInstance().init(this);

    }
}
