package com.mic.training.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.mic.R;


/**
 * Created by hcDarren on 2017/8/4.
 * 监听当前View的触摸时间
 */

public class BubbleMessageTouchListener implements View.OnTouchListener, MessageBubbleView.MessageBubbleListener {
    // 原来需要拖动爆炸的View
    private View mStaticView;
    private WindowManager mWindowManager;
    private MessageBubbleView mMessageBubbleView;
    private WindowManager.LayoutParams mParams;
    private Context mContext;
    // 爆炸动画
    private FrameLayout mBombFrame;
    private ImageView mBombImage;
    private BubbleDisappearListener mDisappearListener;

    public BubbleMessageTouchListener(View view, Context context,BubbleDisappearListener disappearListener) {
        mStaticView = view;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mMessageBubbleView = new MessageBubbleView(context);
        mMessageBubbleView.setMessageBubbleListener(this);
        mParams = new WindowManager.LayoutParams();
        // 背景要透明
        mParams.format = PixelFormat.TRANSPARENT;
        this.mContext = context;

        mBombFrame = new FrameLayout(mContext);
        mBombImage = new ImageView(mContext);
        mBombImage.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mBombFrame.addView(mBombImage);
        this.mDisappearListener = disappearListener;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 要在WindowManager上面搞一个View ,上一节写好的贝塞尔的View
                mWindowManager.addView(mMessageBubbleView, mParams);
                // 初始化贝塞尔View的点
                // 保证固定圆的中心在View的中心
                int[] location = new int[2];
                mStaticView.getLocationOnScreen(location);
                Bitmap bitmap = getBitmapByView(mStaticView);
                mMessageBubbleView.initPoint(location[0] + mStaticView.getWidth() / 2, location[1] + mStaticView.getWidth() / 2 - BubbleUtils.getStatusBarHeight(mContext));
                // 给消息拖拽设置一个Bitmap
                mMessageBubbleView.setDragBitmap(bitmap);

                // 首先把自己隐藏
                mStaticView.setVisibility(View.INVISIBLE);
                break;
            case MotionEvent.ACTION_MOVE:
                mMessageBubbleView.updateDragPoint(event.getRawX(), event.getRawY() - BubbleUtils.getStatusBarHeight(mContext));
                break;
            case MotionEvent.ACTION_UP:
                mMessageBubbleView.handleActionUp();
                break;
        }
        return true;
    }

    /**
     * 从一个View中获取Bitmap
     *
     * @param view
     * @return
     */
    private Bitmap getBitmapByView(View view) {
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    @Override
    public void restore() {
        // 把消息的View移除
        mWindowManager.removeView(mMessageBubbleView);
        // 把原来的View显示
        mStaticView.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismiss(PointF pointF) {
        // 要去执行爆炸动画 （帧动画）
        // 原来的View的View肯定要移除
        mWindowManager.removeView(mMessageBubbleView);
        // 要在 mWindowManager 添加一个爆炸动画
        mWindowManager.addView(mBombFrame,mParams);
        mBombImage.setBackgroundResource(R.drawable.anim_bubble_pop);

        AnimationDrawable drawable = (AnimationDrawable) mBombImage.getBackground();
        mBombImage.setX(pointF.x-drawable.getIntrinsicWidth()/2);
        mBombImage.setY(pointF.y-drawable.getIntrinsicHeight()/2);

        drawable.start();
        // 等它执行完之后我要移除掉这个 爆炸动画也就是 mBombFrame
        mBombImage.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWindowManager.removeView(mBombFrame);
                // 通知一下外面该消失
                if(mDisappearListener != null){
                    mDisappearListener.dismiss(mStaticView);
                }
            }
        },getAnimationDrawableTime(drawable));
    }

    private long getAnimationDrawableTime(AnimationDrawable drawable) {
        int numberOfFrames = drawable.getNumberOfFrames();
        long time = 0;
        for (int i=0;i<numberOfFrames;i++){
            time += drawable.getDuration(i);
        }
        return time;
    }

    public interface BubbleDisappearListener {
        void dismiss(View view);
    }

}
