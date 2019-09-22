package com.mic.xsample.fragment.view;



import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mic.xsample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class XTextViewFragment extends Fragment {


    public XTextViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_xtext_view, container, false);
    }

}
