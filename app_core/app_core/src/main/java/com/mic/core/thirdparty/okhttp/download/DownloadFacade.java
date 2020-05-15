package com.mic.core.thirdparty.okhttp.download;

import android.content.Context;

/**
 * Created by hcDarren on 2017/11/26.
 */

@SuppressWarnings("unused")
public class DownloadFacade {
    private static final DownloadFacade sFacade = new DownloadFacade();

    private DownloadFacade(){}

    public static DownloadFacade getFacade() {
        return sFacade;
    }

    public void init(Context context){
        FileManager.manager().init(context);
        DaoManagerHelper.getManager().init(context);
    }

    public void startDownload(String url,DownloadCallback callback){
        DownloadDispatcher.getDispatcher().startDownload(url,callback);
    }

    public void startDownload(String url){
        // DownloadDispatcher.getDispatcher().startDownload(url);
    }
}
