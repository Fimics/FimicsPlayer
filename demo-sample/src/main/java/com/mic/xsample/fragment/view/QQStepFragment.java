package com.mic.xsample.fragment.view;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.mic.customview.view.QQStepView;
import com.mic.xsample.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QQStepFragment extends Fragment {

    QQStepView qqStepView;

    public QQStepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qqstep, container, false);
        qqStepView = view.findViewById(R.id.step_view);
        testQQStepView();
        return view;
    }

    private void testQQStepView(){
        qqStepView.setStepMax(4000);
        // 属性动画 后面讲的内容
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 2000);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();
                qqStepView.setCurrentStep((int)currentStep);
            }
        });
        valueAnimator.start();
    }

}
