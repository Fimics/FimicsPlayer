package com.mic.core.architecture.mvp.proxy;


import com.mic.core.architecture.mvp.base.BaseView;



public class ActivityMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements ActivityMvpProxy{
    public ActivityMvpProxyImpl(V view) {
        super(view);
    }
    // 不同对待，一般可能不会
}
