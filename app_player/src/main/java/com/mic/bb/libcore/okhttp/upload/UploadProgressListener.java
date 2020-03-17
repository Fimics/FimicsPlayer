package com.mic.bb.libcore.okhttp.upload;


public interface UploadProgressListener {
    void onProgress(long total, long current);
}
