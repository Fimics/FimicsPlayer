package com.mic.demo.bean;

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
    public static final String NAME_TOUCH_VIEW = "TouchView";
    public static final String NAME_TOUCH_VIEWGROUP = "TouchViewGroup";
    public static final String NAME_SLIDING_MENU = "SlidingMenu";
    public static final String NAME_QQSLIDING_MENU = "QQSlidingMenu";
    public static final String NAME_VERTICAL_DRAGLISTVIEW = "VerticalDragListView";
    public static final String NAME_NESTED_SCROLLVIEW = "NestedScrollView";
    public static final String NAME_MYSCROLL_VIEW = "MyScrollView";
    public static final String NAME_BEHAVIOR = "Behavior";
    public static final String NAME_LOADING_VIEW = "LoadingView";
    public static final String NAME_LIST_MENU = "ListMenu";
    public static final String NAME_CIRCLEL_OADINGVIEW = "CircleLoadingView";
    public static final String NAME_MESSAGE_BUBBLEVIEW = "MessageBubbleView";
    public static final String NAME_MESSAGE_BUBBLEVIEW1 = "MessageBubbleView1";
    public static final String NAME_LOVE_LAYOUT = "LoveLayout";
    public static final String NAME_LOGIN = "login";
    public static final String NAME_PAINT = "paint";
    public static final String NAME_XFERMODE = "xfermode";
    public static final String NAME_XFERMODES = "xfermodes";
    public static final String NAME_COLOR_FILTER = "colorFilter";
    public static final String NAME_COLOR_FILTER_VIEW = "colorFilterView";
    public static final String NAME_CANVAS_TRANSFORM = "canvasTransform";
    public static final String NAME_CANVAS_SPLIT = "canvasSplit";
    public static final String NAME_CANVAS_SPLASH = "canvasSplash";
    public static final String NAME_PATH_BEZIER = "pathBezier";
    public static final String NAME_PATH_MULTI_BEZIER = "pathMultiBezier(多阶贝赛尔曲线)";
    public static final String NAME_PATH_MEASURE = "pathMeasure";
    public static final String NAME_ANIMATOR = "animator";
    public static final String NAME_SPLASH = "splash";
    public static final String NAME_MATCH_PIXEL = "像素适配";
    public static final String NAME_MATCH_PERCENT = "百分比适配";
    public static final String NAME_MATCH_DENSITY = "density适配";
    public static final String NAME_MATCH_CUTOUT = "刘海屏适配";
    public static final String NAME_MATERIAL = "material";
    public static final String NAME_RECYCLER_VIEW = "recyclerView";
    public static final String NAME_COORDINATOR_LAYOUT = "coordinatorlayout";
    public static final String NAME_CARDVIEW = "cardview";
    public static final String NAME_SKIN_BUILT_IN = "内置换肤";
    public static final String NAME_SKIN_CUSTOM = "个性换肤";
    public static final String NAME_HOT_FIX = "hotfix";
    public static final String NAME_GRADLE_CONFIG = "gradleConfig";
    public static final String NAME_ROUTER = "router";
    public static final String NAME_PLUGIN_PROXY = "plugin_proxy";
    public static final String NAME_HOOK_BUTTON = "hook_button";
    public static final String NAME_PLUGIN_HOOK = "plugin_hook";
    public static final String NAME_PLUGIN_LOAD_APK = "plugin_load_apk";
    public static final String NAME_EVENT_USE = "event_use";
    //android ui type
    public static final int TYPE_TEXTVIEW = 0;
    public static final int TYPE_QQ_STEP = 1;
    public static final int TYPE_TRACKT_EXTVIEW = 2;
    public static final int TYPE_PROGRESS_BAR = 3;
    public static final int TYPE_VIEW_PAGER = 4;
    public static final int TYPE_SHAPE_VIEW = 5;
    public static final int TYPE_RATING_BAR = 6;
    public static final int TYPE_LETTER_SIDEBAR = 7;
    public static final int TYPE_TOUCH_VIEW = 8;
    public static final int TYPE_TOUCH_VIEWGROUP = 9;
    public static final int TYPE_SLIDING_MENU = 10;
    public static final int TYPE_QQSLIDING_MENU = 11;
    public static final int TYPE_VERTICAL_DRAGLISTVIEW = 12;
    public static final int TYPE_NESTED_SCROLLVIEW = 13;
    public static final int TYPE_MYSCROLL_VIEW = 14;
    public static final int TYPE_BEHAVIOR = 15;
    public static final int TYPE_LOADING_VIEW = 16;
    public static final int TYPE_LIST_MENU = 17;
    public static final int TYPE_CIRCLEL_OADINGVIEW = 18;
    public static final int TYPE_MESSAGE_BUBBLEVIEW = 19;
    public static final int TYPE_MESSAGE_BUBBLEVIEW1 = 20;
    public static final int TYPE_LOVE_LAYOUT = 21;
    public static final int TYPE_LOGIN = 22;
    public static final int TYPE_PAINT = 23;
    public static final int TYPE_XFERMODE = 24;
    public static final int TYPE_XFERMODES= 25;
    public static final int TYPE_COLOR_FILTER = 26;
    public static final int TYPE_COLOR_FILTER_VIEW= 27;
    public static final int TYPE_CANVAS_TRANSFORM = 28;
    public static final int TYPE_CANVAS_SPLIT= 29;
    public static final int TYPE_CANVAS_SPLASH= 30;
    public static final int TYPE_PATH_BEZIER= 31;
    public static final int TYPE_PATH_MULTI_BEZIER= 32;
    public static final int TYPE_PATH_MEASURE= 33;
    public static final int TYPE_ANIMATOR= 34;
    public static final int TYPE_SPLASH= 35;
    public static final int TYPE_MATCH_PIXEL= 36;
    public static final int TYPE_MATCH_PERCENT= 37;
    public static final int TYPE_MATCH_DENSITY= 38;
    public static final int TYPE_MATCH_CUTOUT= 39;
    public static final int TYPE_MATERIAL= 40;
    public static final int TYPE_RECYCLER_VIEW= 41;
    public static final int TYPE_COORDINATOR_LAYOUT= 42;
    public static final int TYPE_CARDVIEW= 43;
    public static final int TYPE_SKIN_BUILT_IN= 44;
    public static final int TYPE_SKIN_CUSTOM= 45;
    public static final int TYPE_HOT_FIX= 46;
    public static final int TYPE_GRADLE_CONFIG= 47;
    public static final int TYPE_ROUTER= 48;
    public static final int TYPE_PLUGIN_PROXY= 49;
    public static final int TYPE_HOOK_BUTTON=50;
    public static final int TYPE_PLUGIN_HOOK= 51;
    public static final int TYPE_PLUGIN_LOOD_APK= 52;
    public static final int TYPE_EVENT_USE= 53;

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
        uiArrayList.add(new AndroidUI( NAME_LOADING_VIEW ,TYPE_LOADING_VIEW));
        uiArrayList.add(new AndroidUI( NAME_CIRCLEL_OADINGVIEW ,TYPE_CIRCLEL_OADINGVIEW));
        uiArrayList.add(new AndroidUI( NAME_LIST_MENU ,TYPE_LIST_MENU));
        uiArrayList.add(new AndroidUI( NAME_LOVE_LAYOUT ,TYPE_LOVE_LAYOUT));
        uiArrayList.add(new AndroidUI( NAME_MESSAGE_BUBBLEVIEW ,TYPE_MESSAGE_BUBBLEVIEW));
        uiArrayList.add(new AndroidUI( NAME_MESSAGE_BUBBLEVIEW1 ,TYPE_MESSAGE_BUBBLEVIEW1));
        uiArrayList.add(new AndroidUI( NAME_VIEW_PAGER ,TYPE_VIEW_PAGER));
        uiArrayList.add(new AndroidUI( NAME_NESTED_SCROLLVIEW ,TYPE_NESTED_SCROLLVIEW));
        uiArrayList.add(new AndroidUI( NAME_MYSCROLL_VIEW ,TYPE_MYSCROLL_VIEW));
        uiArrayList.add(new AndroidUI( NAME_BEHAVIOR ,TYPE_BEHAVIOR));
        uiArrayList.add(new AndroidUI( NAME_LOGIN ,TYPE_LOGIN));
        uiArrayList.add(new AndroidUI( NAME_PAINT ,TYPE_PAINT));
        uiArrayList.add(new AndroidUI(NAME_XFERMODE,TYPE_XFERMODE));
        uiArrayList.add(new AndroidUI(NAME_XFERMODES,TYPE_XFERMODES));
        uiArrayList.add(new AndroidUI(NAME_COLOR_FILTER,TYPE_COLOR_FILTER));
        uiArrayList.add(new AndroidUI(NAME_COLOR_FILTER_VIEW,TYPE_COLOR_FILTER_VIEW));
        uiArrayList.add(new AndroidUI(NAME_CANVAS_TRANSFORM,TYPE_CANVAS_TRANSFORM));
        uiArrayList.add(new AndroidUI(NAME_CANVAS_SPLIT,TYPE_CANVAS_SPLIT));
        uiArrayList.add(new AndroidUI(NAME_CANVAS_SPLASH,TYPE_CANVAS_SPLASH));
        uiArrayList.add(new AndroidUI(NAME_PATH_BEZIER,TYPE_PATH_BEZIER));
        uiArrayList.add(new AndroidUI(NAME_PATH_MULTI_BEZIER,TYPE_PATH_MULTI_BEZIER));
        uiArrayList.add(new AndroidUI(NAME_PATH_MEASURE,TYPE_PATH_MEASURE));
        uiArrayList.add(new AndroidUI(NAME_ANIMATOR,TYPE_ANIMATOR));
        uiArrayList.add(new AndroidUI(NAME_SPLASH,TYPE_SPLASH));
        uiArrayList.add(new AndroidUI(NAME_MATCH_PIXEL,TYPE_MATCH_PIXEL));
        uiArrayList.add(new AndroidUI(NAME_MATCH_PERCENT,TYPE_MATCH_PERCENT));
        uiArrayList.add(new AndroidUI(NAME_MATCH_DENSITY,TYPE_MATCH_DENSITY));
        uiArrayList.add(new AndroidUI(NAME_MATCH_CUTOUT,TYPE_MATCH_CUTOUT));
        uiArrayList.add(new AndroidUI(NAME_MATERIAL,TYPE_MATERIAL));
        uiArrayList.add(new AndroidUI(NAME_RECYCLER_VIEW,TYPE_RECYCLER_VIEW));
        uiArrayList.add(new AndroidUI(NAME_COORDINATOR_LAYOUT,TYPE_COORDINATOR_LAYOUT));
        uiArrayList.add(new AndroidUI(NAME_CARDVIEW,TYPE_CARDVIEW));
        uiArrayList.add(new AndroidUI(NAME_SKIN_BUILT_IN,TYPE_SKIN_BUILT_IN));
        uiArrayList.add(new AndroidUI(NAME_SKIN_CUSTOM,TYPE_SKIN_CUSTOM));
        uiArrayList.add(new AndroidUI(NAME_HOT_FIX,TYPE_HOT_FIX));
        uiArrayList.add(new AndroidUI(NAME_GRADLE_CONFIG,TYPE_GRADLE_CONFIG));
        uiArrayList.add(new AndroidUI(NAME_ROUTER,TYPE_ROUTER));
        uiArrayList.add(new AndroidUI(NAME_PLUGIN_PROXY,TYPE_PLUGIN_PROXY));
        uiArrayList.add(new AndroidUI(NAME_HOOK_BUTTON,TYPE_HOOK_BUTTON));
        uiArrayList.add(new AndroidUI(NAME_PLUGIN_HOOK,TYPE_PLUGIN_HOOK));
        uiArrayList.add(new AndroidUI(NAME_PLUGIN_LOAD_APK,TYPE_PLUGIN_LOOD_APK));
        uiArrayList.add(new AndroidUI(NAME_EVENT_USE,TYPE_EVENT_USE));
        return uiArrayList;
    }
}
