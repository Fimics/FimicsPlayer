package com.mic.training.fragment.view;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mic.R;
import com.mic.customview.view.ProgressBar;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProgressBarFragment extends Fragment {


    private ProgressBar mProgressBar;
    public ProgressBarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_progress_bar, container, false);

        mProgressBar = view.findViewById(R.id.progress_bar);
        testProgressBar();
        return view;
    }



    public void testProgressBar(){
        mProgressBar.setMax(4000);

        ValueAnimator animator = ObjectAnimator.ofFloat(0, 4000);
        animator.setDuration(10000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                mProgressBar.setProgress((int) progress);
            }
        });
    }

}
