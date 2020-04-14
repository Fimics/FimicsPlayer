package com.mic.core.architecture.mmvm;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static androidx.arch.core.executor.ArchTaskExecutor.*;

/**
 * 具体原理见 {@link MutableItemKeyedDataSource}
 *
 * @param <Value>
 */
public class MutablePageKeyedDataSource<Value> extends PageKeyedDataSource<Integer, Value> {
    public List<Value> data = new ArrayList<>();

    public PagedList<Value> buildNewPagedList(PagedList.Config config) {
        PagedList<Value> pagedList = new PagedList.Builder<Integer, Value>(this, config)
                .setFetchExecutor(getIOThreadExecutor())
                .setNotifyExecutor(getMainThreadExecutor())
                .build();

        return pagedList;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Value> callback) {
        callback.onResult(data, null, null);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Value> callback) {
        callback.onResult(Collections.emptyList(), null);
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Value> callback) {
        callback.onResult(Collections.emptyList(), null);
    }
}
