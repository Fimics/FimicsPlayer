package com.mic.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;


public class SignatureTools {

    public static Signature[] getSignature(Context context) {
        if (context == null) return null;
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getOpPackageName(), PackageManager.GET_SIGNATURES);
        }catch (Exception e){
            TrackingTools.handleException(e);
        }
        Signature[] signatures = packageInfo.signatures;
        return signatures;
    }
}
