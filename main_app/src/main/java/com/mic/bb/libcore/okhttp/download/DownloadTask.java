package com.mic.bb.libcore.okhttp.download;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.mic.bb.libcore.okhttp.download.db.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 每一个 apk 的下载，这个类最终是要复用的，不复用也行，
 */

@SuppressWarnings("unused")
public class DownloadTask {
    private String mUrl;
    private long mContentLength;
    private List<DownloadRunnable> mRunnables;
    // OkHttp 为什么搞一个能被回收的线程池？
    OkHttpClient client = new OkHttpClient();
    /**
     * Executes calls. Created lazily.
     */
    private
    @Nullable
    ExecutorService executorService;
    private volatile int mSucceedNumber;

    private DownloadCallback mCallback;

    public synchronized ExecutorService executorService() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, THREAD_SIZE, 30, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    Thread thread = new Thread(r, "DownloadTask");
                    thread.setDaemon(false);
                    return thread;
                }
            });
        }
        return executorService;
    }

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int THREAD_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));

    public DownloadTask(String url, long contentLength, DownloadCallback callback) {
        this.mUrl = url;
        this.mContentLength = contentLength;
        mRunnables = new ArrayList<>();
        this.mCallback = callback;
    }

    /**
     * 初始化
     */
    public void init() {
        for (int i = 0; i < THREAD_SIZE; i++) {
            // 计算出每个线程要下载的内容
            long threadSize = mContentLength / THREAD_SIZE;

            // 初始化的时候 这里要去读取数据库


            long start = i * threadSize;
            long end = (i + threadSize) - 1;

            if (i == THREAD_SIZE - 1) {
                end = mContentLength - 1;
            }

            List<DownloadEntity> entities = DaoManagerHelper.getManager().queryAll(mUrl);
            DownloadEntity downloadEntity = getEntity(i, entities);
            if (downloadEntity == null) {
                downloadEntity = new DownloadEntity(start, end, mUrl, i, 0, mContentLength);
            }

            DownloadRunnable downloadRunnable = new DownloadRunnable(mUrl, i, start, end,
                    downloadEntity.getProgress(), downloadEntity, new DownloadCallback() {

                @Override
                public void onFailure(IOException e) {
                    // 一个apk 下载里面有一个线程异常了，处理异常,把其他线程停止掉
                    mCallback.onFailure(e);
                }

                @Override
                public void onSucceed(File file) {
                    // 线程同步一下，
                    synchronized (DownloadTask.this) {
                        mSucceedNumber += 1;
                        if (mSucceedNumber == THREAD_SIZE) {
                            mCallback.onSucceed(file);
                            DownloadDispatcher.getDispatcher().recyclerTask(DownloadTask.this);

                            // 清楚数据库的这个文件下载存储

                        }
                    }
                }
            });
            // 通过线程池去执行
            executorService().execute(downloadRunnable);
        }
    }

    private DownloadEntity getEntity(int threadId, List<DownloadEntity> entities) {
        for (DownloadEntity entity : entities) {
            if (threadId == entity.getThreadId()) {
                return entity;
            }
        }
        return null;
    }

    public void stop() {
        for (DownloadRunnable runnable : mRunnables) {
            runnable.stop();
        }
    }
}
