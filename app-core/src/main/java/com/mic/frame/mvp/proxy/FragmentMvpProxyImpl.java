package com.mic.frame.mvp.proxy;

import com.mic.frame.mvp.base.BaseView;

public class FragmentMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements FragmentMvpProxy {
    public FragmentMvpProxyImpl(V view) {
        super(view);
    }
}
