package com.mic.demo.binder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.demo.bean.AndroidUI;
import com.mic.core.thirdparty.multitype.ItemViewBinder;
import com.mic.demo.view.singleview.ProgressBar;

public class ProgressBarBinder extends ItemViewBinder<AndroidUI, ProgressBarBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_progress_bar, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private ProgressBar mProgressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.progress_bar);
            testProgressBar();
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
}
