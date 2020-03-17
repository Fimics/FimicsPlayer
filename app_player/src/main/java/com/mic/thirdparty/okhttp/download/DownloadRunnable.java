package com.mic.thirdparty.okhttp.download;

import android.util.Log;

import com.mic.thirdparty.okhttp.download.db.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * 负责apk的部分 2-3M
 */

public class DownloadRunnable implements Runnable{
    private static final int STATUS_DOWNLOADING = 1;
    private static final int STATUS_STOP = 2;
    private final long start;
    private final long end;
    private final int threadId;
    private final String url;
    private final DownloadCallback mCallback;
    private int mStatus = STATUS_DOWNLOADING;
    private long mProgress = 0;
    private DownloadEntity mDownloadEntity;

    public DownloadRunnable(String url, int threadId, long start, long end, long progress, DownloadEntity downloadEntity,DownloadCallback callback) {
        this.threadId = threadId;
        this.url = url;
        this.start = start + progress;// 1M-2M 0.5M  1.5M - 2M
        this.end = end;
        mCallback = callback;
        this.mProgress = progress;
        this.mDownloadEntity = downloadEntity;
    }

    @Override
    public void run() {
        // 只读写我自己的内容，Range
        RandomAccessFile accessFile = null;
        InputStream inputStream = null;
        try {
            Response response = OkHttpManager.getManager().syncResponse(url,start,end);
            Log.e("TAG",this.toString());

           inputStream = response.body().byteStream();
            // 写数据
            File file = FileManager.manager().getFile(url);
            accessFile = new RandomAccessFile(file,"rwd");
            // 从这里开始
            accessFile.seek(start);

            int len = 0;
            byte[] buffer = new byte[1024*10];

            while ((len = inputStream.read(buffer))!=-1){
                if(mStatus == STATUS_STOP)
                    break;
                // 保存进度，做断点 , 100kb
                mProgress += len;
                accessFile.write(buffer,0,len);
            }

            mCallback.onSucceed(file);
        } catch (IOException e) {
            mCallback.onFailure(e);
        }finally {
            Utils.close(inputStream);
            Utils.close(accessFile);

            // 存到数据库，数据库怎么存？
            mDownloadEntity.setProgress(mProgress);
            DaoManagerHelper.getManager().addEntity(mDownloadEntity);
        }
    }

    @Override
    public String toString() {
        return "DownloadRunnable{" +
                "start=" + start +
                ", end=" + end +
                ", threadId=" + threadId +
                ", url='" + url + '\'' +
                '}';
    }

    public void stop() {
        mStatus = STATUS_STOP;
    }

}
