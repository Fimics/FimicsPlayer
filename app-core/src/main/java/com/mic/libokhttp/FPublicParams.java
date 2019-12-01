package com.mic.libokhttp;

import java.util.HashMap;
import java.util.Map;

public class FPublicParams {

    private static final  FPublicParams instance = new FPublicParams();

    private static HashMap<String,String> hashMap = new HashMap<String, String>();

    private FPublicParams(){}

    public static FPublicParams getInstance(){
        return  instance;
    }

    public Map<String,String> getParams(){

        hashMap.put("platform","android");
        hashMap.put("version","1.0.0");
        hashMap.put("androidVersion","8.0");
        hashMap.put("model","xiaomi");
        hashMap.put("w","1280");
        hashMap.put("h","2160");

        return hashMap;
    }

}
