package com.mic.architecture.mvp.proxy;

import com.mic.architecture.mvp.base.BaseView;

public class FragmentMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements FragmentMvpProxy {
    public FragmentMvpProxyImpl(V view) {
        super(view);
    }
}
