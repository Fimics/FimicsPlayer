package com.mic.ndk;

public class NDKInterface {

    public static native String sayHello();

    //签名MD5
    public static native String signature(String params);
}
