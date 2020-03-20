package com.mic.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mic.BaseFragment;
import com.mic.R;
import com.mic.home.HomeFragment;
import com.mic.home.bean.AndroidUI;
import com.mic.home.bean.ResourceType;
import com.mic.home.binder.AndroidUIBinder;
import com.mic.news.multitype.bilibili.PostItemDecoration;
import com.mic.thirdparty.multitype.MultiTypeAdapter;
import com.mic.view.FimRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class AndroidUIFragment  extends BaseFragment {

    private static final String TAG = "android_ui";
    private List<AndroidUI> mViews = new ArrayList<AndroidUI>();
    private FimRecyclerView list;
    private static final int SPAN_COUNT=6;
    private MultiTypeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
           list = rootView.findViewById(R.id.list);
    }

    @Override
    protected void initData() {
        initAndroidUINames();
        adapter = new MultiTypeAdapter();
        AndroidUIBinder uiBinder = new AndroidUIBinder();
        uiBinder.setListener(new AndroidUIBinder.ItemClickListener() {
            @Override
            public void onItemOnClick(int position) {
                send(position);
            }
        });
        adapter.register(AndroidUI.class ,uiBinder);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),SPAN_COUNT);
        GridLayoutManager.SpanSizeLookup sizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 3;
            }
        };
        layoutManager.setSpanSizeLookup(sizeLookup);
        list.setLayoutManager(layoutManager);
        int space = 20;
        list.addItemDecoration(new PostItemDecoration(space,sizeLookup));
        list.setAdapter(adapter);
        adapter.setItems(mViews);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.list;
    }

    private void send(int position){
        HomeFragment homeFragment = (HomeFragment) this.getParentFragment();
        homeFragment.toNextPage();
        EventBus eventBus = EventBus.getDefault();
        eventBus.post(mViews.get(position));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showChild(AndroidUI uiEvent){
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    private void initAndroidUINames(){
        mViews= ResourceType.getAndroidUiTypeList();
    }
}