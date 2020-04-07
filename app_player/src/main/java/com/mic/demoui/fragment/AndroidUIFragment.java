package com.mic.demoui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mic.core.BaseFragment;
import com.mic.R;
import com.mic.tabs.TabHomeFragment;
import com.mic.demoui.bean.AndroidUI;
import com.mic.demoui.bean.ResourceType;
import com.mic.demoui.binder.AndroidUIBinder;
import com.mic.find.multitype.bilibili.PostItemDecoration;
import com.mic.core.thirdparty.multitype.MultiTypeAdapter;
import com.mic.find.FindDetailBehaviorActivity;
import com.mic.user.login.LoginDialogFragment;
import com.mic.sofa.VideoDetailMyScrollViewActivity;
import com.mic.user.detail.UserDetailNestedActivity;
import com.mic.demoui.ViewPagerActivity;
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
        AndroidUI uiEvent = mViews.get(position);
        int type = uiEvent.type;
        if(type==ResourceType.TYPE_VIEW_PAGER){
            startActivity(ViewPagerActivity.class);
        }else if(type==ResourceType.TYPE_NESTED_SCROLLVIEW){
            startActivity(UserDetailNestedActivity.class);
        }else if(type==ResourceType.TYPE_MYSCROLL_VIEW){
            startActivity(VideoDetailMyScrollViewActivity.class);
        }else if(type==ResourceType.TYPE_BEHAVIOR){
            startActivity(FindDetailBehaviorActivity.class);
        }else if(type==ResourceType.TYPE_LOGIN){
            LoginDialogFragment.instance().show(getActivity());
        }else{
            TabHomeFragment homeFragment = (TabHomeFragment) this.getParentFragment();
            homeFragment.toNextPage();
            EventBus eventBus = EventBus.getDefault();
            eventBus.post(uiEvent);
        }
    }

    private void startActivity(Class clazz){
        Intent intent = new Intent(getContext(),clazz);
        startActivity(intent);
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
