package com.mic.view;

import android.animation.ObjectAnimator;
import android.content.Context;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mic.libcore.utils.system.DisplayUtil;

import java.util.ArrayList;

import static android.widget.LinearLayout.VERTICAL;


public class BottomLayout extends RelativeLayout implements View.OnClickListener {

    private int defColor=Color.BLUE;
    private int defFontSize =11;


    private int childPading =30;
    private int perViewWidth;
    private int perViewHeight;

    private String defBgColor="#FFFFF0";
    private String pressBgColor="#FFFACD";

    private int mWidth;

    private OnTabChanged mOnTabChanged;

    private int indicatorLineWidth;
    private TextView indicatorView;
    private float mDensity;

    //当前指示器的位置
    private int mIndex;

    private boolean mIsFirstWindowChanged = true;


    public BottomLayout(Context context) {
        this(context,null);
    }

    public BottomLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BottomLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setBackgroundColor(Color.parseColor(defBgColor));
        this.mWidth= (int) DisplayUtil.getScreenWidth(context);
        this.mDensity =DisplayUtil.getScreenDensity(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setBottomList(ArrayList<Bottom> bottomList) {
        addSubView(bottomList);
    }

    public void setmOnTabChanged(OnTabChanged mOnTabChanged) {
        this.mOnTabChanged = mOnTabChanged;
    }

    private void addSubView(ArrayList<Bottom> bottomList){

        int count = bottomList.size();

        if(count==0) return;

        perViewWidth=mWidth/count;
        LinearLayout.LayoutParams subParams = new LinearLayout.LayoutParams(perViewWidth,ViewGroup.LayoutParams.WRAP_CONTENT);
        subParams.gravity=Gravity.CENTER;

        for(int i=0;i<count;i++){

            Bottom bottom= bottomList.get(i);

            LinearLayout subView = new LinearLayout(this.getContext());
            subView.setOrientation(VERTICAL);
            subView.setPadding(childPading,15,childPading,15);
            subView.setLayoutParams(subParams);

            ImageView imageView = new ImageView(this.getContext());
            imageView.setImageResource(bottom.resId);


            TextView textView = new TextView(this.getContext());
            textView.setTextColor(defColor);
            textView.setTextSize(defFontSize);
            textView.setText(bottom.name);
            textView.setGravity(Gravity.CENTER);

            subView.addView(imageView);
            subView.addView(textView);
            subView.setTag(i);
            subView.setOnClickListener(this);
            this.addView(subView);
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);

        if(mIsFirstWindowChanged&& hasWindowFocus){
            LinearLayout subView = (LinearLayout) getChildAt(0);
            perViewHeight =subView.getHeight();

            int count = subView.getChildCount();
            for(int i=0;i<count;i++){
                View view=subView.getChildAt(i);

                if(view instanceof ImageView){
                    indicatorLineWidth = (int) (view.getWidth()/mDensity);
                }
            }


            int h = this.getHeight();

            addIndicatorView();
            moveIndicator(mIndex);
        }

        mIsFirstWindowChanged =false;


    }

    private void addIndicatorView(){
        indicatorView =new TextView(this.getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(indicatorLineWidth,5);
        params.topMargin=perViewHeight-10;
        indicatorView.setLayoutParams(params);
        indicatorView.setBackgroundColor(Color.RED);
        this.addView(indicatorView);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int count = getChildCount();

        for (int i=0;i<count;i++){
            View childView =getChildAt(i);

            if(childView instanceof LinearLayout){
                int left =i*perViewWidth;
                int top =childView.getTop();
                int right=(i+1)*perViewWidth;
                int bottom=childView.getBottom();
                childView.layout(left,top,right,bottom);
            }
        }

    }

    @Override
    public void onClick(View v) {
        if(mOnTabChanged!=null){
            int index = (int) v.getTag();
            mOnTabChanged.onSelected(index);
            changeBackground(v);
            moveIndicator(index);
        }


    }

    private void changeBackground(View view){

        int count =getChildCount();

        for(int i=0;i<count;i++){

            View childView =getChildAt(i);

            if(childView instanceof LinearLayout){
                if(view==childView){
                    childView.setBackgroundColor(Color.parseColor(pressBgColor));
                }else{
                    childView.setBackgroundColor(Color.parseColor(defBgColor));
                }
            }

        }

    }



    private ObjectAnimator indicatorAnimator;
    private void moveIndicator(int index){

        int startX =(mIndex*perViewWidth)+(perViewWidth-indicatorLineWidth)/2;
        int endX=(index*perViewWidth)+(perViewWidth-indicatorLineWidth)/2;

        if(indicatorAnimator==null){
            indicatorAnimator= ObjectAnimator.ofFloat(indicatorView,"translationX",0,0);
        }

        indicatorAnimator.setFloatValues(startX,endX);
        indicatorAnimator.setDuration(100);

        if(!indicatorAnimator.isRunning()){
            indicatorAnimator.start();
        }
        this.mIndex=index;
    }

    public void updateIndicator(int index){
        moveIndicator(index);
        if(this.getChildAt(index) instanceof LinearLayout){
            LinearLayout childView = (LinearLayout) this.getChildAt(index);
            changeBackground(childView);
        }
    }



    public interface OnTabChanged{
       void onSelected(int index);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        indicatorAnimator=null;
    }
}
