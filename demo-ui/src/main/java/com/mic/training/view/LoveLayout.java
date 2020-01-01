package com.mic.training.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mic.training.R;

import java.util.Random;

/**
 * Created by hcDarren on 2017/8/6.
 * 点赞的效果
 */

public class LoveLayout extends RelativeLayout {
    // 随机数
    private Random mRandom;
    // 图片资源
    private int[] mImageRes;
    // 控件的宽高
    private int mWidth, mHeight;
    // 图片的宽高
    private int mDrawableWidth, mDrawableHeight;

    private Interpolator[] mInterpolator;

    public LoveLayout(Context context) {
        this(context, null);
    }

    public LoveLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoveLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRandom = new Random();
        mImageRes = new int[]{R.drawable.pl_blue, R.drawable.pl_red, R.drawable.pl_yellow};

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.pl_blue);
        mDrawableWidth = drawable.getIntrinsicWidth();
        mDrawableHeight = drawable.getIntrinsicHeight();

        mInterpolator = new Interpolator[]{new AccelerateDecelerateInterpolator(),new AccelerateInterpolator(),
        new DecelerateInterpolator(),new LinearInterpolator()};
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获取控件的宽高
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    /**
     * 添加一个点赞的View
     */
    public void addLove() {
        // 添加一个ImageView在底部
        final ImageView loveIv = new ImageView(getContext());
        // 给一个图片资源（随机）
        loveIv.setImageResource(mImageRes[mRandom.nextInt(mImageRes.length - 1)]);
        // 怎么添加到底部中心？ LayoutParams
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(ALIGN_PARENT_BOTTOM);
        params.addRule(CENTER_HORIZONTAL);
        loveIv.setLayoutParams(params);
        addView(loveIv);

        AnimatorSet animator = getAnimator(loveIv);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // 执行完毕之后移除
                removeView(loveIv);
            }
        });
        animator.start();
    }

    public AnimatorSet getAnimator(ImageView iv) {
        AnimatorSet allAnimatorSet = new AnimatorSet();// 待会再用

        // 添加的效果：有放大和透明度变化 （属性动画）
        AnimatorSet innerAnimator = new AnimatorSet();
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(iv, "alpha", 0.3f, 1.0f);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(iv, "scaleX", 0.3f, 1.0f);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(iv, "scaleY", 0.3f, 1.0f);
        // 一起执行
        innerAnimator.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
        innerAnimator.setDuration(350);

        // 运行的路径动画  playSequentially 按循序执行
        allAnimatorSet.playSequentially(innerAnimator, getBezierAnimator(iv));

        return allAnimatorSet;
    }

    public Animator getBezierAnimator(final ImageView iv) {
        // 怎么确定四个点
        PointF point0 = new PointF(mWidth / 2 - mDrawableWidth / 2, mHeight - mDrawableHeight);
        // 确保 p2 点的 y 值 一定要大于 p1 点的 y 值
        PointF point1 = getPoint(1);
        PointF point2 = getPoint(2);
        PointF point3 = new PointF(mRandom.nextInt(mWidth) - mDrawableWidth, 0);

        LoveTypeEvaluator typeEvaluator = new LoveTypeEvaluator(point1, point2);
        // ofFloat  第一个参数 LoveTypeEvaluator 第二个参数 p0, 第三个是 p3
        ValueAnimator bezierAnimator = ObjectAnimator.ofObject(typeEvaluator, point0, point3);
        // 加一些随机的差值器（效果更炫）
        bezierAnimator.setInterpolator(mInterpolator[mRandom.nextInt(mInterpolator.length-1)]);
        bezierAnimator.setDuration(3000);
        bezierAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                iv.setX(pointF.x);
                iv.setY(pointF.y);
                // 透明度
                float t = animation.getAnimatedFraction();
                iv.setAlpha(1 - t + 0.2f);
            }
        });
        return bezierAnimator;
    }

    private PointF getPoint(int index) { // 1
        return new PointF(mRandom.nextInt(mWidth) - mDrawableWidth, mRandom.nextInt(mHeight / 2) + (index - 1) * (mHeight / 2));
    }
}
