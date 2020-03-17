package com.mic.xx.singleview;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchViewGroup extends LinearLayout {

    private static final String TAG ="TouchView";
    public TouchViewGroup(Context context) {
        this(context,null);
    }

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d(TAG,"TouchViewGroup  --> onInterceptTouchEvent"+"event-->"+ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    //事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d(TAG,"TouchViewGroup  --> dispatchTouchEvent "+"event-->"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    //事件消费
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"TouchViewGroup  --> onTouchEvent"+"event-->"+event.getAction());
        return super.onTouchEvent(event);
    }
}
