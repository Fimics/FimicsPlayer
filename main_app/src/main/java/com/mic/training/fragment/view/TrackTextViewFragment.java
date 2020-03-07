package com.mic.training.fragment.view;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.mic.R;
import com.mic.customview.view.TrackTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackTextViewFragment extends Fragment {

    private TrackTextView mColorTrackTextView;
    public TrackTextViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track_text_view, container, false);
        mColorTrackTextView = view.findViewById(R.id.color_track_tv);
        return view;

    }

    public void leftToRight(View view){
        mColorTrackTextView.setDirection(TrackTextView.Direction.LEFT_TO_RIGHT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgress = (float) animation.getAnimatedValue();
                mColorTrackTextView.setCurrentProgress(currentProgress);
            }
        });
        valueAnimator.start();
    }

    public void rightToLeft(View view){
        mColorTrackTextView.setDirection(TrackTextView.Direction.RIGHT_TO_LEFT);
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentProgress = (float) animation.getAnimatedValue();
                mColorTrackTextView.setCurrentProgress(currentProgress);
                Log.e("TAG","currentProgress -> "+currentProgress);
            }
        });
        valueAnimator.start();
    }

}
