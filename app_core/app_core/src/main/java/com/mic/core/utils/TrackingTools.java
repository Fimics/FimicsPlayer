package com.mic.core.utils;

public class TrackingTools {

    public static void handleException(Throwable t){
        Logger.d(Logger.TAG.EXCEPTION,t.getStackTrace().toString());
    }
}
