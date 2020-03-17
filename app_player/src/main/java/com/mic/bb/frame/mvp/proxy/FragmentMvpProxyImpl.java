package com.mic.bb.frame.mvp.proxy;

import com.mic.bb.frame.mvp.base.BaseView;

public class FragmentMvpProxyImpl<V extends BaseView> extends MvpProxyImpl<V> implements FragmentMvpProxy {
    public FragmentMvpProxyImpl(V view) {
        super(view);
    }
}
