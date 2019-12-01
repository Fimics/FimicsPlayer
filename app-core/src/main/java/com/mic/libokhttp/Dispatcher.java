package com.mic.libokhttp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Dispatcher {

    private ExecutorService executorService;

    public synchronized  ExecutorService executorService(){
        if(executorService==null){
            executorService=new ThreadPoolExecutor(
                    0,
                    Integer.MAX_VALUE,
                    600,
                    TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(),
                    new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = new Thread(r,"FOkhttp");
                            thread.setDaemon(false);
                            return thread;
                        }
                    }
            );
        }

        return executorService;
    }


    public void enqueue(RealCall.AsynCall call){
        executorService().execute(call);
    }


}
