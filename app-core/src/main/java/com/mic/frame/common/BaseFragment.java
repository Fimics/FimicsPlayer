package com.mic.frame.common;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mic.libcore.ioc.ViewUtils;


public abstract class BaseFragment extends Fragment{

    protected View rootView;
    protected Context context;
    protected LayoutInflater layoutInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layoutInflater = inflater;
        this.context = getActivity();
        rootView = View.inflate(context,getLayoutId(),null);

        // 加入注解
        ViewUtils.inject(rootView,this);
        initView();
        initData();
        return rootView;
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutId();

}
