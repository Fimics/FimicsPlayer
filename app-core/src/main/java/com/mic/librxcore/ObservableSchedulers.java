package com.mic.librxcore;


final class ObservableSchedulers<T> extends Observable<T> {
    final Observable<T> source;
    final Schedulers schedulers;

    public ObservableSchedulers(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        schedulers.scheduleDirect(new SchedulerTask(observer));
    }

    private class SchedulerTask implements Runnable{
        final Observer<T> observer;
        public SchedulerTask(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            // 线程池最终回来执行 Runnable -> 这行代码，会执行上游的 subscribe()
            // 而这个run方法在子线程中
            source.subscribe(observer);
        }
    }
}
