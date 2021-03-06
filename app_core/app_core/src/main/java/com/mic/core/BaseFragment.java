package com.mic.core;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mic.core.utils.ioc.ViewUtils;


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
