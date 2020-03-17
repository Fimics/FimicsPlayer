package com.mic.architecture.mvp.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mic.BaseFragment;
import com.mic.architecture.mvp.proxy.FragmentMvpProxy;
import com.mic.architecture.mvp.proxy.FragmentMvpProxyImpl;

@SuppressWarnings("")
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
