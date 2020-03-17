package com.mic.view.singleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.mic.R;


public class ProgressBar extends View {

    private int mInnerBackground = Color.RED;
    private int mOuterBackground = Color.RED;
    private int mRoundWidth = 10;// 10px
    private float mProgressTextSize = 15;
    private int mProgressTextColor = Color.RED;

    private Paint mInnerPaint, mOuterPaint, mTextPaint;

    private int mMax = 100;
    private int mProgress = 0;
    public ProgressBar(Context context) {
        this(context,null);
    }

    public ProgressBar(Context context,AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressBar(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);



        // 获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressBar);
        mInnerBackground = array.getColor(R.styleable.ProgressBar_innerBackground, mInnerBackground);
        mOuterBackground = array.getColor(R.styleable.ProgressBar_outerBackground, mOuterBackground);
        mRoundWidth = (int) array.getDimension(R.styleable.ProgressBar_roundWidth, dip2px(10));
        mProgressTextSize = array.getDimensionPixelSize(R.styleable.ProgressBar_progressTextSize,
                sp2px(mProgressTextSize));
        mProgressTextColor = array.getColor(R.styleable.ProgressBar_progressTextColor, mProgressTextColor);



        mInnerPaint = new Paint();
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setColor(mInnerBackground);
        mInnerPaint.setStrokeWidth(mRoundWidth);
        mInnerPaint.setStyle(Paint.Style.STROKE);

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setColor(mOuterBackground);
        mOuterPaint.setStrokeWidth(mRoundWidth);
        mOuterPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mProgressTextColor);
        mTextPaint.setTextSize(mProgressTextSize);

        array.recycle();
    }

    private int sp2px(float sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    private float dip2px(int dip) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 只保证是正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 先画内圆
        int center = getWidth() / 2;
        canvas.drawCircle(center, center, center - mRoundWidth / 2, mInnerPaint);

        RectF rect = new RectF(0+mRoundWidth/2,0+mRoundWidth/2,getWidth()-mRoundWidth/2,getHeight()-mRoundWidth/2);
        if (mProgress == 0) {
            return;
        }
        float percent = (float) mProgress / mMax;
        canvas.drawArc(rect,0,percent*360,false,mOuterPaint);

        // 画进度文字
        String text = ((int) (percent * 100)) + "%";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), textBounds);
        int x = getWidth() / 2 - textBounds.width() / 2;

        Paint.FontMetricsInt fontMetricsInt = mTextPaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLineY = getHeight() / 2 + dy;

        canvas.drawText(text, x, baseLineY, mTextPaint);
    }

    // 给几个方法
    public synchronized void setMax(int max) {
        if (max < 0) {

        }
        this.mMax = max;
    }

    public synchronized void setProgress(int progress) {
        if (progress < 0) {
        }
        this.mProgress = progress;
        // 刷新 invalidate
        invalidate();
    }
}
