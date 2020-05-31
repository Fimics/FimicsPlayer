package com.mic;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mic.all.R;
import com.mic.all.activity.FindDetailBehaviorActivity;
import com.mic.all.activity.UserDetailNestedActivity;
import com.mic.all.activity.VideoDetailMyScrollViewActivity;
import com.mic.all.activity.ViewPagerActivity;
import com.mic.all.bean.AndroidUI;
import com.mic.all.bean.ResourceType;
import com.mic.all.binder.AndroidUIBinder;
import com.mic.core.BaseFragment;
import com.mic.core.thirdparty.multitype.MultiTypeAdapter;
import com.mic.core.view.FimRecyclerView;
import com.mic.multitype.bilibili.PostItemDecoration;
import com.mic.router.api.RouterManager;
import com.mic.tabs.TabHomeFragment;
import com.mic.user.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class AndroidUIFragment extends BaseFragment {

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
            navigation("/all_demo/ViewPagerActivity");
        }else if(type==ResourceType.TYPE_NESTED_SCROLLVIEW){
            navigation("/all_demo/UserDetailNestedActivity");
        }else if(type==ResourceType.TYPE_MYSCROLL_VIEW){
            navigation("/all_demo/VideoDetailMyScrollViewActivity");
        }else if(type==ResourceType.TYPE_BEHAVIOR){
            navigation("/all_demo/FindDetailBehaviorActivity");
        }else if(type==ResourceType.TYPE_LOGIN){
//            LoginDialogFragment.instance().show(getActivity());
            navigation("/app_player/LoginActivity");
        }else if(type==ResourceType.TYPE_XFERMODE){
            navigation("/xfermode_demo/XfermodeActivity");
        }else if(type==ResourceType.TYPE_XFERMODES){
            navigation("/xfermode_demo/XfermodesActivity");
        }else if(type==ResourceType.TYPE_COLOR_FILTER){
            navigation("/color_demo/ColorFilterActivity");
        }else if(type==ResourceType.TYPE_COLOR_FILTER_VIEW){
            navigation("/color_demo/ColorFilterViewActivity");
        }else if(type==ResourceType.TYPE_CANVAS_TRANSFORM){
            navigation("/canvas_demo/CanvasTransformActivity");
        }else if(type==ResourceType.TYPE_CANVAS_SPLIT){
            navigation("/canvas_demo/CanvasSplitActivity");
        }else if(type==ResourceType.TYPE_PATH_BEZIER){
            navigation("/path_demo/PathBezierActivity");
        }else if(type==ResourceType.TYPE_PATH_MULTI_BEZIER){
            navigation("/path_demo/PathMultiBezierActivity");
        }else if(type==ResourceType.TYPE_PATH_MEASURE){
            navigation("/path_demo/PathMeasureActivity");
        }else if(type==ResourceType.TYPE_ANIMATOR){
            navigation("/animator_demo/AnimatorActivity");
        }else if(type==ResourceType.TYPE_SPLASH){
            navigation("/splash_demo/SplashActivity");
        }else if(type==ResourceType.TYPE_MATCH_PIXEL){
            navigation("/match_demo/MatchPixelActivity");
        }else if(type==ResourceType.TYPE_MATCH_PERCENT){
            navigation("/match_demo/MatchPercentActivity");
        }else if(type==ResourceType.TYPE_MATCH_DENSITY){
            navigation("/match_demo/MatchDensityActivity");
        }else if(type==ResourceType.TYPE_MATCH_CUTOUT){
            navigation("/match_demo/MatchDisplayCutoutActivity");
        }else if(type==ResourceType.TYPE_MATERIAL){
            navigation("/material_demo/MaterialMainActivity");
        }else if(type==ResourceType.TYPE_RECYCLER_VIEW){
            navigation("/material_demo/RecyclerViewActivity");
        }else if(type==ResourceType.TYPE_COORDINATOR_LAYOUT){
            navigation("/material_demo/CoordinatorLayoutActivity");
        }else if(type==ResourceType.TYPE_CARDVIEW){
            navigation("/material_demo/CardViewActivity");
        }else if(type==ResourceType.TYPE_SKIN_BUILT_IN){
            navigation("/skin_demo/SkinBuiltInActivity");
        }else if(type==ResourceType.TYPE_SKIN_CUSTOM){
            navigation("/skin_demo/SkinCustomActivity");
        }else if(type==ResourceType.TYPE_HOT_FIX){
            navigation("/hotfix_demo/HotfixActivity");
        }else if(type==ResourceType.TYPE_ROUTER){
            navigation("/demo/DemoMainActivity");
        }else if(type==ResourceType.TYPE_PLUGIN_PROXY){
            navigation("/plugin_proxy/MainActivity");
        }else if(type==ResourceType.TYPE_HOOK_BUTTON){
            navigation("/demo/HookButtonActivity");
        }else if(type==ResourceType.TYPE_PLUGIN_HOOK){
            navigation("/plugin_hook/MainActivity");
        }else if(type==ResourceType.TYPE_PLUGIN_LOOD_APK){
            navigation("/plugin_loadapk/MainActivity");
        }else if(type==ResourceType.TYPE_EVENT_USE){
            navigation("/eventbus_use/MainActivity");
        }else if(type==ResourceType.TYPE_EVENT_REFLECT){
            navigation("/eventbus_reflect/MainActivity");
        }else if(type==ResourceType.TYPE_EVENT_BUS){
            navigation("/eventbus_demo/MainActivity");
        }else if(type==ResourceType.TYPE_FAST_JSON){
            navigation("/fastjson_demo/MainActivity");
        }else if(type==ResourceType.TYPE_SQLITE){
            navigation("/sqlite_demo/MainActivity");
        }
        else{
            TabHomeFragment homeFragment = (TabHomeFragment) this.getParentFragment();
            homeFragment.toNextPage();
            EventBus eventBus = EventBus.getDefault();
            eventBus.post(uiEvent);
        }
    }

    private void navigation(String path){
        RouterManager.getInstance()
                .build(path)
                .navigation(getActivity());
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
