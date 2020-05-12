package com.mic;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.multidex.MultiDex;

import com.mic.core.thirdparty.okhttp.ApiService;
import com.mic.demo.viewwy.screenmatch.density.Density;
import com.mic.hotfix.library.FixDexUtils;
import com.mic.skin.custom.SkinManager;
import com.tencent.bugly.crashreport.CrashReport;

/**s
 * 项目在线Api文档地址：http://123.56.232.18:8080/serverdemo/swagger-ui.html#/
 * 可以按照:https://git.imooc.com/Chubby/jetpack_ppjoke/src/master/%e6%9c%8d%e5%8a%a1%e5%99%a8%e7%8e%af%e5%a2%83%e6%90%ad%e5%bb%ba.md
 * 来搭建本地服务器
 */
public class FimicsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
         ApiService.init(BuildConfig.host,null);

        CrashReport.initCrashReport(getApplicationContext(), "eb455a94a3", true);
        // 设置全局异常捕捉类
        // ExceptionCrashHandler.getInstance().init(this);
       // matchWithDenisty(this);
        SkinManager.init(this);
        // 加载热修复Dex文件
        FixDexUtils.loadFixedDex(this);
    }

    private void matchWithDenisty(Application application){
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Density.setDensity(application, activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
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
