package com.mic.tabs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mic.annotation.FragmentDestination;
import com.mic.core.BaseFragment;
import com.mic.R;

@FragmentDestination(pageUrl ="main/tabs/sofa" ,asStarter = false)
public class SofaFragment extends BaseFragment {


    public SofaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        return rootView;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sofa;
    }

}
