package com.mic.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;

import com.mic.R;


@SuppressLint("AppCompatCustomView")
public class AutoRotateView extends ImageView {
    private Animation animation;

    public AutoRotateView(Context context) {
        super(context);
    }

    public AutoRotateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoRotateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AutoRotateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);

        if (visibility == VISIBLE && this.getVisibility() == VISIBLE) {
            start();
        } else {
            stop();
        }

    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (visibility != VISIBLE) {
            stop();
        } else {
            start();
        }
    }

    private void start() {
        if (getAnimation() != null)
            return;

        doStart();
    }

    private void doStart() {
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.loading_rotate);
        animation.setInterpolator(new LinearInterpolator());
        startAnimation(animation);
    }

    private void stop() {
        animation = null;
        clearAnimation();
    }

}
