package com.mic.frame.mvp.proxy;


import com.mic.frame.mvp.base.BaseView;



public class ActivityMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements ActivityMvpProxy{
    public ActivityMvpProxyImpl(V view) {
        super(view);
    }
    // 不同对待，一般可能不会
}
