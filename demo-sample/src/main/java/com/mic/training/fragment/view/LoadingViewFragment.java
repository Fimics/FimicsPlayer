package com.mic.training.fragment.view;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mic.training.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoadingViewFragment extends Fragment {


    public LoadingViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading_view, container, false);
    }


    public void click(View view){
        view.setVisibility(View.GONE);
    }

}
