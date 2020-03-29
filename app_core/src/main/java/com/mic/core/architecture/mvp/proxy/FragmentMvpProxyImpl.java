package com.mic.core.architecture.mvp.proxy;

import com.mic.core.architecture.mvp.base.BaseView;

public class FragmentMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements FragmentMvpProxy {
    public FragmentMvpProxyImpl(V view) {
        super(view);
    }
}
