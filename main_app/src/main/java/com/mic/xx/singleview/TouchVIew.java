package com.mic.xx.singleview;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchVIew extends View {

    private static final String TAG = "TouchView";
    public TouchVIew(Context context) {
        this(context,null);
    }

    public TouchVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //如果返回true 只会执行onTouchEvent ,onTouch 都不会走，
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG,"TouchVIew  --> dispatchTouchEvent "+"event-->"+event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Log.d(TAG,"TouchVIew  -->onTouchEvent  "+"event-->"+event.getAction());
        return super.onTouchEvent(event);
        //return true;
    }
}
