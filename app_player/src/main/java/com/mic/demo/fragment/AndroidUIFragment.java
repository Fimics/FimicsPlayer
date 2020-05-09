package com.mic.demo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mic.R;
import com.mic.core.BaseFragment;
import com.mic.core.thirdparty.multitype.MultiTypeAdapter;
import com.mic.demo.activity.AnimatorActivity;
import com.mic.demo.activity.CanvasSplitActivity;
import com.mic.demo.activity.CanvasTransformActivity;
import com.mic.demo.activity.CardViewActivity;
import com.mic.demo.activity.ColorFilterActivity;
import com.mic.demo.activity.ColorFilterViewActivity;
import com.mic.demo.activity.CoordinatorLayoutActivity;
import com.mic.demo.activity.MatchDensityActivity;
import com.mic.demo.activity.MatchDisplayCutoutActivity;
import com.mic.demo.activity.MatchPercentActivity;
import com.mic.demo.activity.MatchPixelActivity;
import com.mic.demo.activity.PathBezierActivity;
import com.mic.demo.activity.PathMeasureActivity;
import com.mic.demo.activity.PathMultiBezierActivity;
import com.mic.demo.activity.RecyclerViewActivity;
import com.mic.demo.activity.SkinBuiltInActivity;
import com.mic.demo.activity.SplashActivity;
import com.mic.demo.activity.XfermodeActivity;
import com.mic.demo.activity.XfermodesActivity;
import com.mic.demo.viewwy.material.MaterialMainActivity;
import com.mic.find.FindDetailBehaviorActivity;
import com.mic.find.multitype.bilibili.PostItemDecoration;
import com.mic.home.ViewPagerActivity;
import com.mic.demo.bean.AndroidUI;
import com.mic.demo.bean.ResourceType;
import com.mic.demo.binder.AndroidUIBinder;
import com.mic.sofa.VideoDetailMyScrollViewActivity;
import com.mic.tabs.TabHomeFragment;
import com.mic.user.detail.UserDetailNestedActivity;
import com.mic.user.login.LoginActivity;
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
//            LoginDialogFragment.instance().show(getActivity());
            startActivity(LoginActivity.class);
        }else if(type==ResourceType.TYPE_XFERMODE){
            startActivity(XfermodeActivity.class);
        }else if(type==ResourceType.TYPE_XFERMODES){
            startActivity(XfermodesActivity.class);
        }else if(type==ResourceType.TYPE_COLOR_FILTER){
            startActivity(ColorFilterActivity.class);
        }else if(type==ResourceType.TYPE_COLOR_FILTER_VIEW){
            startActivity(ColorFilterViewActivity.class);
        }else if(type==ResourceType.TYPE_CANVAS_TRANSFORM){
            startActivity(CanvasTransformActivity.class);
        }else if(type==ResourceType.TYPE_CANVAS_SPLIT){
            startActivity(CanvasSplitActivity.class);
        }else if(type==ResourceType.TYPE_PATH_BEZIER){
            startActivity(PathBezierActivity.class);
        }else if(type==ResourceType.TYPE_PATH_MULTI_BEZIER){
            startActivity(PathMultiBezierActivity.class);
        }else if(type==ResourceType.TYPE_PATH_MEASURE){
            startActivity(PathMeasureActivity.class);
        }else if(type==ResourceType.TYPE_ANIMATOR){
            startActivity(AnimatorActivity.class);
        }else if(type==ResourceType.TYPE_SPLASH){
            startActivity(SplashActivity.class);
        }else if(type==ResourceType.TYPE_MATCH_PIXEL){
            startActivity(MatchPixelActivity.class);
        }else if(type==ResourceType.TYPE_MATCH_PERCENT){
            startActivity(MatchPercentActivity.class);
        }else if(type==ResourceType.TYPE_MATCH_DENSITY){
            startActivity(MatchDensityActivity.class);
        }else if(type==ResourceType.TYPE_MATCH_CUTOUT){
            startActivity(MatchDisplayCutoutActivity.class);
        }else if(type==ResourceType.TYPE_MATERIAL){
            startActivity(MaterialMainActivity.class);
        }else if(type==ResourceType.TYPE_RECYCLER_VIEW){
            startActivity(RecyclerViewActivity.class);
        }else if(type==ResourceType.TYPE_COORDINATOR_LAYOUT){
            startActivity(CoordinatorLayoutActivity.class);
        }else if(type==ResourceType.TYPE_CARDVIEW){
            startActivity(CardViewActivity.class);
        }else if(type==ResourceType.TYPE_SKIN_BUILT_IN){
            startActivity(SkinBuiltInActivity.class);
        }
        else{
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
