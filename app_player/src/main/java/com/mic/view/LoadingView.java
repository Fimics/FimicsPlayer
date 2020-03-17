package com.mic.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by hcDarren on 2017/7/29.
 */

public class LoadingView extends RelativeLayout{
    private CircleView mLeftView,mMiddleView,mRightView;
    private int mTranslationDistance = 30;
    private final long ANIMATION_TIME = 350;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {// LayoutInflater里面
        super(context, attrs, defStyleAttr);
        mTranslationDistance = dip2px(mTranslationDistance);

        setBackgroundColor(Color.WHITE);
        // 添加三个View 但是要圆形
        mLeftView = getCircleView(context);
        mLeftView.exchangeColor(Color.BLUE);
        mMiddleView = getCircleView(context);
        mMiddleView.exchangeColor(Color.RED);
        mRightView = getCircleView(context);
        mRightView.exchangeColor(Color.GREEN);
        addView(mLeftView);
        addView(mRightView);
        addView(mMiddleView);

        post(new Runnable() {
            @Override
            public void run() {
                // 让布局实例化好之后再去开启动画
                expendAnimation();
            }
        });
    }

    /**
     * 展开动画
     */
    private void expendAnimation() {
        // 左边跑
        ObjectAnimator leftTranslationAnimator = ObjectAnimator.ofFloat(mLeftView,"translationX",0,-mTranslationDistance);
        // 右边跑
        ObjectAnimator rightTranslationAnimator = ObjectAnimator.ofFloat(mRightView,"translationX",0,mTranslationDistance);

        // 弹性效果  荡秋千一样 差值器   刚开始快越来越慢

        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIMATION_TIME);
        set.playTogether(leftTranslationAnimator,rightTranslationAnimator);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 往里面跑
                innerAnimation();
            }
        });
        set.start();
    }

    private void innerAnimation() {
        // 左边跑
        ObjectAnimator leftTranslationAnimator = ObjectAnimator.ofFloat(mLeftView,"translationX",-mTranslationDistance,0);
        // 右边跑
        ObjectAnimator rightTranslationAnimator = ObjectAnimator.ofFloat(mRightView,"translationX",mTranslationDistance,0);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new AccelerateInterpolator());
        set.setDuration(ANIMATION_TIME);
        set.playTogether(leftTranslationAnimator,rightTranslationAnimator);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 往里外面跑
                // 切换颜色顺序  左边的给中间 中间的给右边  右边的给左边
                int leftColor = mLeftView.getColor();
                int rightColor = mRightView.getColor();
                int middleColor = mMiddleView.getColor();
                mMiddleView.exchangeColor(leftColor);
                mRightView.exchangeColor(middleColor);
                mLeftView.exchangeColor(rightColor);
                expendAnimation();
            }
        });
        set.start();
    }

    public CircleView getCircleView(Context context) {
        CircleView circleView = new CircleView(context);
        LayoutParams params = new LayoutParams(dip2px(10),dip2px(10));
        params.addRule(CENTER_IN_PARENT);
        circleView.setLayoutParams(params);
        return circleView;
    }

    // 有没有意识到问题？ 会不会感觉有什么不对劲？

    private int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }
}
