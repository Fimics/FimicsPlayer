package com.mic.thirdparty.okhttp.upload;


public interface UploadProgressListener {
    void onProgress(long total, long current);
}
