package com.mic.all.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.mic.core.BaseFragment;
import com.mic.all.R;
import com.mic.all.bean.AndroidUI;
import com.mic.all.bean.ResourceType;
import com.mic.all.binder.CanvasSplashBinder;
import com.mic.all.binder.LetterSideBarBinder;
import com.mic.all.binder.ListMenuBinder;
import com.mic.all.binder.LoadingViewBinder;
import com.mic.all.binder.LoadingViewCircleBinder;
import com.mic.all.binder.LoveLayoutBinder;
import com.mic.all.binder.MessageBubbleViewBinder;
import com.mic.all.binder.MessageBubbleViewBinder1;
import com.mic.all.binder.PaintBinder;
import com.mic.all.binder.ProgressBarBinder;
import com.mic.all.binder.QQStepBinder;
import com.mic.all.binder.RatingBarBinder;
import com.mic.all.binder.ShapeViewBinder;
import com.mic.all.binder.SlidingMenuBinder;
import com.mic.all.binder.SlidingMenuQQBinder;
import com.mic.all.binder.TextViewBinder;
import com.mic.all.binder.TouchViewBinder;
import com.mic.all.binder.TouchViewGroupBinder;
import com.mic.all.binder.TrackTextViewBinder;
import com.mic.all.binder.VerticalDragListViewBinder;
import com.mic.core.thirdparty.multitype.MultiTypeAdapter;
import com.mic.core.view.FimRecyclerView;
import com.mic.multitype.bilibili.PostItemDecoration;
import com.mic.router.annotation.Router;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

@Router(path = "/all_demo/AndroidUIChildFragment")
public class AndroidUIChildFragment extends BaseFragment {

    private static final int SPAN_COUNT = 1;
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
                new ShapeViewBinder(getActivity()),
                new RatingBarBinder(),
                new LetterSideBarBinder(),
                new TouchViewBinder(),
                new TouchViewGroupBinder(),
                new SlidingMenuBinder(),
                new SlidingMenuQQBinder(),
                new VerticalDragListViewBinder(),
                new LoadingViewBinder(),
                new LoadingViewCircleBinder(),
                new ListMenuBinder(),
                new LoveLayoutBinder(),
                new MessageBubbleViewBinder(),
                new MessageBubbleViewBinder1(),
                new PaintBinder(),
                new CanvasSplashBinder()
        ).withClassLinker((position, androidUI) -> {
            int type = androidUI.type;
            if (type == ResourceType.TYPE_TEXTVIEW) {
                return TextViewBinder.class;
            } else if (type == ResourceType.TYPE_QQ_STEP) {
                return QQStepBinder.class;
            } else if (type == ResourceType.TYPE_TRACKT_EXTVIEW) {
                return TrackTextViewBinder.class;
            } else if (type == ResourceType.TYPE_PROGRESS_BAR) {
                return ProgressBarBinder.class;
            } else if (type == ResourceType.TYPE_SHAPE_VIEW) {
                return ShapeViewBinder.class;
            } else if (type == ResourceType.TYPE_RATING_BAR) {
                return RatingBarBinder.class;
            } else if (type == ResourceType.TYPE_LETTER_SIDEBAR) {
                return LetterSideBarBinder.class;
            } else if (type == ResourceType.TYPE_TOUCH_VIEW) {
                return TouchViewBinder.class;
            } else if (type == ResourceType.TYPE_TOUCH_VIEWGROUP) {
                return TouchViewGroupBinder.class;
            } else if (type == ResourceType.TYPE_SLIDING_MENU) {
                return SlidingMenuBinder.class;
            } else if (type == ResourceType.TYPE_QQSLIDING_MENU) {
                return SlidingMenuQQBinder.class;
            } else if (type == ResourceType.TYPE_VERTICAL_DRAGLISTVIEW) {
                return VerticalDragListViewBinder.class;
            } else if (type == ResourceType.TYPE_LOADING_VIEW) {
                return LoadingViewBinder.class;
            } else if (type == ResourceType.TYPE_CIRCLEL_OADINGVIEW) {
                return LoadingViewCircleBinder.class;
            } else if (type == ResourceType.TYPE_LIST_MENU) {
                return ListMenuBinder.class;
            } else if (type == ResourceType.TYPE_LOVE_LAYOUT) {
                return LoveLayoutBinder.class;
            } else if (type == ResourceType.TYPE_MESSAGE_BUBBLEVIEW) {
                return MessageBubbleViewBinder.class;
            } else if (type == ResourceType.TYPE_MESSAGE_BUBBLEVIEW1) {
                return MessageBubbleViewBinder1.class;
            }else if(type==ResourceType.TYPE_PAINT){
                return PaintBinder.class;
            }else if(type==ResourceType.TYPE_CANVAS_SPLASH){
                return CanvasSplashBinder.class;
            }
             else {
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
