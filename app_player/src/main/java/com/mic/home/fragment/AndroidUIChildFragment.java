package com.mic.home.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mic.BaseFragment;
import com.mic.R;
import com.mic.home.bean.AndroidUI;
import com.mic.home.bean.ResourceType;
import com.mic.home.binder.ProgressBarBinder;
import com.mic.home.binder.QQStepBinder;
import com.mic.home.binder.ShapeViewBinder;
import com.mic.home.binder.TextViewBinder;
import com.mic.home.binder.TrackTextViewBinder;
import com.mic.news.multitype.bilibili.PostItemDecoration;
import com.mic.thirdparty.multitype.MultiTypeAdapter;
import com.mic.view.FimRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class AndroidUIChildFragment extends BaseFragment {

    private static final int SPAN_COUNT = 2;
    private FimRecyclerView list;
    private MultiTypeAdapter adapter;
    private ArrayList<AndroidUI> uiArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView() {
        list = rootView.findViewById(R.id.list);
        adapter = new MultiTypeAdapter();
        uiArrayList = new ArrayList<>();
        adapter.setItems(uiArrayList);

        adapter.register(AndroidUI.class).to(
                new TextViewBinder(),
                new QQStepBinder(),
                new TrackTextViewBinder(),
                new ProgressBarBinder(),
                new ShapeViewBinder(getActivity())
        ).withClassLinker((position, androidUI) -> {
            int type = androidUI.type;
            if (type == ResourceType.TYPE_TEXTVIEW) {
                return TextViewBinder.class;
            } else if (type == ResourceType.TYPE_QQ_STEP) {
                return QQStepBinder.class;
            } else if (type == ResourceType.TYPE_TRACKT_EXTVIEW) {
                return TrackTextViewBinder.class;
            }else if(type==ResourceType.TYPE_PROGRESS_BAR){
                return ProgressBarBinder.class;
            }else if(type==ResourceType.TYPE_SHAPE_VIEW){
                return ShapeViewBinder.class;
            } else {
                return TextViewBinder.class;
            }
        });

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), SPAN_COUNT);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        };

        list.setLayoutManager(layoutManager);

        int space = 12;
        list.addItemDecoration(new PostItemDecoration(space, spanSizeLookup));
        list.setAdapter(adapter);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showChild(AndroidUI uiEvent) {
        uiArrayList.clear();
        uiArrayList.add(uiEvent);
        adapter.notifyDataSetChanged();
    }
}
