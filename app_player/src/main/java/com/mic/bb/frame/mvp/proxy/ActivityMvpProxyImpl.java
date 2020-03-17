package com.mic.bb.frame.mvp.proxy;


import com.mic.bb.frame.mvp.base.BaseView;



public class ActivityMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements ActivityMvpProxy{
    public ActivityMvpProxyImpl(V view) {
        super(view);
    }
    // 不同对待，一般可能不会
}
