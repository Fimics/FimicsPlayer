package com.mic.training.fragment.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mic.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SlidingMenuFragment extends Fragment {


    public SlidingMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.binder_sliding_menu, container, false);
        return view;
    }

}
