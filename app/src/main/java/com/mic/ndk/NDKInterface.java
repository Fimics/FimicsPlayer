package com.mic.ndk;

import android.content.Context;

public class NDKInterface {

    public static native String sayHello();
    //MD5
    public static native String signature(String params);
    //签名校验 ,只允许自己的app使用so
    public static native boolean signatureVerify(Context context);
}
