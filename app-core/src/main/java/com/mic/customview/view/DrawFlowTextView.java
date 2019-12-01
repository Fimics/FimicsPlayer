package com.mic.customview.view;

import android.content.Context;
import android.view.View;


public class DrawFlowTextView extends View{
    public DrawFlowTextView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 指定宽高
        // widthMeasureSpec = childWidthMeasureSpec
        // heightMeasureSpec = childHeightMeasureSpec

        // wrap_content = AT_MOST
        // match_parent fill_parent 100dp = EXACTLY
        // 模式和大小是由父布局和自己决定的
        // 比方 父布局是包裹内容 就算子布局是match_parent，这个时候计算的测量模式还是 AT_MOST
        // 比方 父布局是match_parent  子布局是match_parent，这个时候计算的测量模式还是 EXACTLY

        // setMeasuredDimension();
    }
}
