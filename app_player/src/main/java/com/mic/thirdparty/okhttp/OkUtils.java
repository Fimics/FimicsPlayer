package com.mic.thirdparty.okhttp;

import android.text.TextUtils;

import java.net.FileNameMap;
import java.net.URLConnection;


public class OkUtils {

    public static String guessMimeType(String filePath) {

        FileNameMap fileNameMap = URLConnection.getFileNameMap();

        String mimType = fileNameMap.getContentTypeFor(filePath);

        if(TextUtils.isEmpty(mimType)){
            return "application/octet-stream";
        }
        return mimType;
    }



}
