package com.mic.optimization.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mic.optimization.R;
import com.mic.optimization.common.CommUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeakSingleFragment extends Fragment {


    public LeakSingleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =inflater.inflate(R.layout.fragment_leak_single, container, false);

        return view;
    }

}
