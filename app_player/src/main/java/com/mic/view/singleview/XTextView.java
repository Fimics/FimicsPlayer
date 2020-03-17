package com.mic.view.singleview;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.mic.R;


@SuppressLint("AppCompatCustomView")
@SuppressWarnings("unused")
public class XTextView extends TextView {

    private String mText;
    private int mSize = 20;
    private int mColor = Color.RED;
    private Paint mPaint;

    private Context mContext;


    /**
     * 通过代码调用
     * @param context
     */
    public XTextView(Context context) {
        this(context,null);
    }

    /**
     * 在xml中调用
     * @param context
     * @param attrs
     */
    public XTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    /**
     * 在xml 中但会有 style才会调用
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public XTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

        //获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.DrawFlowTextView);

        mText = array.getString(R.styleable.DrawFlowTextView_text);
        mSize = array.getDimensionPixelSize(R.styleable.DrawFlowTextView_size,mSize);
        mColor =array.getColor(R.styleable.DrawFlowTextView_color,mColor);


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mSize);
        mPaint.setColor(mColor);

        array.recycle();

    }

    /**
     * 测量布局的宽高
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int hightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        //1.给的是固定的值 这个时候不需要计算，
        if(widthMode==MeasureSpec.EXACTLY){

            Rect bounds = new Rect();
            mPaint.getTextBounds(mText,0,mText.length(),bounds);
            width=bounds.width()+getPaddingLeft()+getPaddingRight();
        }

        //2.给的是warp_content  需要计算

        if(widthMode==MeasureSpec.AT_MOST){
            //计算的宽度与字休的多少，字休的大小有关

            Rect bounds = new Rect();
            mPaint.getTextBounds(mText,0,mText.length(),bounds);
            width =bounds.width()+getPaddingLeft()+getPaddingRight();
            height= bounds.height()+getPaddingTop()+getPaddingBottom();
        }

        setMeasuredDimension(width,height);

    }


    /**
     * 用于绘制 ViewGroup 不能进入onDraw方法，因为
     * view -->draw()--->onDraw() 使用模板模式
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

       //drawText
       Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        /**
         * top 是一个负值，bottom是正值 bottom是baseLine到文子地步的距离(正值)
         */
       int dy = (fontMetrics.bottom-fontMetrics.top)/2 -fontMetrics.bottom;
       int baseLine = getHeight()/2 +dy;
       int x = getPaddingLeft();
       canvas.drawText(mText,x,baseLine,mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                    break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onTouchEvent(event);

    }


}
