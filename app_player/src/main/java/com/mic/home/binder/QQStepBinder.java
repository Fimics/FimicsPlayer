package com.mic.home.binder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.home.bean.AndroidUI;
import com.mic.thirdparty.multitype.ItemViewBinder;
import com.mic.view.singleview.QQStepView;

public class QQStepBinder extends ItemViewBinder<AndroidUI, QQStepBinder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_qqstep,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        QQStepView qqStepView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            qqStepView = itemView.findViewById(R.id.step_view);
            testQQStepView();
        }

        public void testQQStepView(){
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
}
