package com.mic.home.binder;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mic.R;
import com.mic.home.bean.AndroidUI;
import com.mic.thirdparty.multitype.ItemViewBinder;
import com.mic.view.singleview.TrackTextView;


public class TrackTextViewBinder extends ItemViewBinder<AndroidUI, TrackTextViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.binder_track_text_view, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull AndroidUI item) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TrackTextView mColorTrackTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mColorTrackTextView = itemView.findViewById(R.id.color_track_tv);
            itemView.findViewById(R.id.left2right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    leftToRight();
                }
            });

            itemView.findViewById(R.id.right2left).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rightToLeft();
                }
            });
        }

        public void leftToRight(){
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

        public void rightToLeft(){
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
}
