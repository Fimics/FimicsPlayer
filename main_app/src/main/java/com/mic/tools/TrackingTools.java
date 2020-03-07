package com.mic.tools;

public class TrackingTools {

    public static void handleException(Throwable t){
        Logger.d(Logger.TAG.EXCEPTION,t.getStackTrace().toString());
    }
}
