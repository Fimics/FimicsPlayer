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
public class LetterSideBarFragment extends Fragment {


    public LetterSideBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_letter_side_bar, container, false);
        return view;
    }

}
