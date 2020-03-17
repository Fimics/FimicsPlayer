package com.mic.bb.libcore;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.mic.bb.libcore.utils.crash.CrashUtils;
import com.mic.bb.libcore.utils.system.SPUtils;
import com.mic.bb.libcore.utils.app.Utils;


public class BaseApp extends Application {

    private static BaseApp app;

    public static Context getAppContext() {
        return app;
    }

    public static Resources getAppResources() {
        return app.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SPUtils.init(this);
        Utils.init(this);
        CrashUtils.getInstance().init();
//        LogUtils2.getBuilder().setTag("MyTag").setLog2FileSwitch(true).create();
    }
}
