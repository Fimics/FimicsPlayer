package com.mic.bb.frame.mvp.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mic.bb.frame.common.BaseFragment;
import com.mic.bb.frame.mvp.proxy.FragmentMvpProxy;
import com.mic.bb.frame.mvp.proxy.FragmentMvpProxyImpl;

@SuppressWarnings("all")
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements BaseView {

    protected FragmentMvpProxy mvpProxy;

    public BaseMvpFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mvpProxy=createMvpProxy();
        View view =super.onCreateView(inflater,container,savedInstanceState);;
        return view;
    }


    private FragmentMvpProxy createMvpProxy(){
        if(mvpProxy==null){
            mvpProxy = new FragmentMvpProxyImpl(this);
        }
        return mvpProxy;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mvpProxy!=null){
            mvpProxy.unbindPresenter();
        }
    }


}
