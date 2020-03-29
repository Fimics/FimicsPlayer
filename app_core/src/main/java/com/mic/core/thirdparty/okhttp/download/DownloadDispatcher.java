package com.mic.core.thirdparty.okhttp.download;



import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


@SuppressWarnings("unused")
final class DownloadDispatcher {

    private static final DownloadDispatcher sDispatcher = new DownloadDispatcher();

    private DownloadDispatcher(){

    }

    public static DownloadDispatcher getDispatcher() {
        return sDispatcher;
    }

    /** Ready async calls in the order they'll be run. */
    private final Deque<DownloadTask> readyTasks = new ArrayDeque<>();

    /** Running asynchronous calls. Includes canceled calls that haven't finished yet. */
    private final Deque<DownloadTask> runningTasks = new ArrayDeque<>();

    /** Running synchronous calls. Includes canceled calls that haven't finished yet. */
    private final Deque<DownloadTask> stopTasks = new ArrayDeque<>();

    // 最大只能下载多少个 3 5

    public void startDownload(final String url, final DownloadCallback callback){
        // 获取文件的大小
        Call call = OkHttpManager.getManager().asyncCall(url);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                  callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 获取文件的大小
                long contentLength = response.body().contentLength();

                if(contentLength <= -1){
                    // 没有获取到文件的大小，
                    // 1. 跟后台商量
                    // 2. 只能采用单线程去下载
                    return;
                }
                // 计算每个线程负责哪一块？
                DownloadTask downloadTask = new DownloadTask(url,contentLength,callback);
                downloadTask.init();

                runningTasks.add(downloadTask);
            }
        });
    }

    public void recyclerTask(DownloadTask downloadTask) {
        runningTasks.remove(downloadTask);
        // 参考 OkHttp 的 Dispatcher 的源码,如果还有需要下载的开始下一个的下载
    }

    public void stopDownload(String url){
        // 这个停止的是不是正在下载的
    }

    // 开个单独的线程去执行 所有下载的回调

}
