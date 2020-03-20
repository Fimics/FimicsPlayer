package com.mic.home.bean;

import java.util.ArrayList;

public class ResourceType {

    //android ui names
    public static final String NAME_TEXTVIEW = "TextView";
    public static final String NAME_QQ_STEP = "QQStep";
    public static final String NAME_TRACKT_EXTVIEW = "TrackTextView";
    public static final String NAME_PROGRESS_BAR = "ProgressBar";
    public static final String NAME_VIEW_PAGER = "ViewPager";
    public static final String NAME_SHAPE_VIEW = "ShapeView";
    public static final String NAME_RATING_BAR = "RatingBar";
    public static final String NAME_LETTER_SIDEBAR = "LetterSideBar";
    public static final String NAME_VIEW_DRAWFLOW = "ViewDrawFlow";
    public static final String NAME_TAG_LAYOUT = "TagLayout";
    public static final String NAME_TOUCH_VIEW = "TouchView";
    public static final String NAME_TOUCH_VIEWGROUP = "TouchViewGroup";
    public static final String NAME_SLIDING_MENU = "SlidingMenu";
    public static final String NAME_QQSLIDING_MENU = "QQSlidingMenu";
    public static final String NAME_VERTICAL_DRAGLISTVIEW = "VerticalDragListView";
    public static final String NAME_LOCK_PATTERNVIEW = "LockPatternView";
    public static final String NAME_SWIPE_REFRESH_LAYOUT = "SwipeRefreshLayout";
    public static final String NAME_NESTED_SCROLLVIEW = "NestedScrollView";
    public static final String NAME_STATUS_BAR = "StatusBar";
    public static final String NAME_MYSCROLL_VIEW = "MyScrollView";
    public static final String NAME_BEHAVIOR = "Behavior";
    public static final String NAME_LOADING_VIEW = "LoadingView";
    public static final String NAME_LIST_MENU = "ListMenu";
    public static final String NAME_CIRCLEL_OADINGVIEW = "CircleLoadingView";
    public static final String NAME_MESSAGE_BUBBLEVIEW = "MessageBubbleView";
    public static final String NAME_MESSAGE_BUBBLEVIEW1 = "MessageBubbleView1";
    public static final String NAME_LOVE_LAYOUT = "LoveLayout";


    //android ui type
    public static final int TYPE_TEXTVIEW = 0;
    public static final int TYPE_QQ_STEP = 1;
    public static final int TYPE_TRACKT_EXTVIEW = 2;
    public static final int TYPE_PROGRESS_BAR = 3;
    public static final int TYPE_VIEW_PAGER = 4;
    public static final int TYPE_SHAPE_VIEW = 5;
    public static final int TYPE_RATING_BAR = 6;
    public static final int TYPE_LETTER_SIDEBAR = 7;
    public static final int TYPE_VIEW_DRAWFLOW = 8;
    public static final int TYPE_TAG_LAYOUT = 9;
    public static final int TYPE_TOUCH_VIEW = 10;
    public static final int TYPE_TOUCH_VIEWGROUP = 11;
    public static final int TYPE_SLIDING_MENU = 12;
    public static final int TYPE_QQSLIDING_MENU = 13;
    public static final int TYPE_VERTICAL_DRAGLISTVIEW = 14;
    public static final int TYPE_LOCK_PATTERNVIEW = 15;
    public static final int TYPE_SWIPE_REFRESH_LAYOUT = 16;
    public static final int TYPE_NESTED_SCROLLVIEW = 17;
    public static final int TYPE_STATUS_BAR = 18;
    public static final int TYPE_MYSCROLL_VIEW = 19;
    public static final int TYPE_BEHAVIOR = 20;
    public static final int TYPE_LOADING_VIEW = 21;
    public static final int TYPE_LIST_MENU = 22;
    public static final int TYPE_CIRCLEL_OADINGVIEW = 22;
    public static final int TYPE_MESSAGE_BUBBLEVIEW = 24;
    public static final int TYPE_MESSAGE_BUBBLEVIEW1 = 25;
    public static final int TYPE_LOVE_LAYOUT = 26;

    public static ArrayList<AndroidUI> getAndroidUiTypeList(){
        ArrayList<AndroidUI> uiArrayList = new ArrayList<>();
        //android ui names
        uiArrayList.add(new AndroidUI( NAME_TEXTVIEW ,TYPE_TEXTVIEW));
        uiArrayList.add(new AndroidUI( NAME_QQ_STEP ,TYPE_QQ_STEP));
        uiArrayList.add(new AndroidUI( NAME_TRACKT_EXTVIEW ,TYPE_TRACKT_EXTVIEW));
        uiArrayList.add(new AndroidUI( NAME_PROGRESS_BAR ,TYPE_PROGRESS_BAR));
        uiArrayList.add(new AndroidUI( NAME_SHAPE_VIEW ,TYPE_SHAPE_VIEW));
        uiArrayList.add(new AndroidUI( NAME_RATING_BAR ,TYPE_RATING_BAR));
        uiArrayList.add(new AndroidUI( NAME_LETTER_SIDEBAR ,TYPE_LETTER_SIDEBAR));
        uiArrayList.add(new AndroidUI( NAME_TOUCH_VIEW ,TYPE_TOUCH_VIEW));
        uiArrayList.add(new AndroidUI( NAME_TOUCH_VIEWGROUP ,TYPE_TOUCH_VIEWGROUP));
        uiArrayList.add(new AndroidUI( NAME_SLIDING_MENU ,TYPE_SLIDING_MENU));
        uiArrayList.add(new AndroidUI( NAME_QQSLIDING_MENU ,TYPE_QQSLIDING_MENU));
        uiArrayList.add(new AndroidUI( NAME_VERTICAL_DRAGLISTVIEW ,TYPE_VERTICAL_DRAGLISTVIEW));
        uiArrayList.add(new AndroidUI( NAME_LOCK_PATTERNVIEW ,TYPE_LOCK_PATTERNVIEW));
        uiArrayList.add(new AndroidUI( NAME_SWIPE_REFRESH_LAYOUT ,TYPE_SWIPE_REFRESH_LAYOUT));
        uiArrayList.add(new AndroidUI( NAME_NESTED_SCROLLVIEW ,TYPE_NESTED_SCROLLVIEW));
        uiArrayList.add(new AndroidUI( NAME_STATUS_BAR ,TYPE_STATUS_BAR));
        uiArrayList.add(new AndroidUI( NAME_MYSCROLL_VIEW ,TYPE_MYSCROLL_VIEW));
        uiArrayList.add(new AndroidUI( NAME_BEHAVIOR ,TYPE_BEHAVIOR));
        uiArrayList.add(new AndroidUI( NAME_LOADING_VIEW ,TYPE_LOADING_VIEW));
        uiArrayList.add(new AndroidUI( NAME_LIST_MENU ,TYPE_LIST_MENU));
        uiArrayList.add(new AndroidUI( NAME_CIRCLEL_OADINGVIEW ,TYPE_CIRCLEL_OADINGVIEW));
        uiArrayList.add(new AndroidUI( NAME_MESSAGE_BUBBLEVIEW ,TYPE_MESSAGE_BUBBLEVIEW));
        uiArrayList.add(new AndroidUI( NAME_MESSAGE_BUBBLEVIEW1 ,TYPE_MESSAGE_BUBBLEVIEW1));
        uiArrayList.add(new AndroidUI( NAME_LOVE_LAYOUT ,TYPE_LOVE_LAYOUT));
        uiArrayList.add(new AndroidUI( NAME_VIEW_PAGER ,TYPE_VIEW_PAGER));
        return uiArrayList;
    }
}
