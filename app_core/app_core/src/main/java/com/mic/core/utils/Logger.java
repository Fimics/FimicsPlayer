package com.mic.core.utils;

import android.util.Log;

import com.mic.BuildConfig;

public class Logger {

    private static boolean isDebug = BuildConfig.DEBUG;

    public static void v(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }

    public static void e(String tag,String msg){
        if(isDebug){
            Log.e(tag,msg);
        }
    }


    public static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag,msg);
        }
    }

    public static void i(String tag,String msg){
        if(isDebug){
            Log.i(tag,msg);
        }
    }

    public static void w(String tag,String msg){
        if(isDebug){
            Log.w(tag,msg);
        }
    }

    public static final class TAG{
        public static final String HOME = "home";
        public static final String NEWS = "news";
        public static final String VIDEO = "video";
        public static final String MAG = "msg";
        public static final String USER = "user";
        public static final String NDK = "ndk";
        public static final String EXCEPTION ="exception";
    }
}
