package com.mic.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class XListView extends ListView {

    public XListView(Context context) {
        super(context);
    }

    public XListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        /**
         * listView 显示不全的问题 ，
         */
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
        //this.setOnTouchListener();
    }
}
