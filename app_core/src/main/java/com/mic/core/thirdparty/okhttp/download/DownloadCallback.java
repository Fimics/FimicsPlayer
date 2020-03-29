package com.mic.core.thirdparty.okhttp.download;

import java.io.File;
import java.io.IOException;



public interface DownloadCallback {
    void onFailure(IOException e);

    void onSucceed(File file);
}
