package com.mic.core.thirdparty.okhttp.upload;


public interface UploadProgressListener {
    void onProgress(long total, long current);
}
