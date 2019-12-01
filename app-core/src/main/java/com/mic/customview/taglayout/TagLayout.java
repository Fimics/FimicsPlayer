package com.mic.customview.taglayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TagLayout extends ViewGroup {


    private TagAdapter tagAdapter;

    public TagLayout(Context context) {
        this(context,null);
    }

    public TagLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TagAdapter getTagAdapter() {
        return tagAdapter;
    }

    public void setTagAdapter(TagAdapter tagAdapter) {
        this.tagAdapter = tagAdapter;
        //this.requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if(tagAdapter==null|| tagAdapter.getCount()==0){
            return;
        }

        int childCount = tagAdapter.getCount();
        int lines=1;

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height =getPaddingBottom()+getPaddingTop();

        //一行的宽度
        int lineWidth = getPaddingLeft();

        for (int i=0;i<childCount;i++){
            View childView = tagAdapter.getView(null,i);
            addOnItemClickListener(childView,i);
            this.addView(childView);

            //执行完这句，就可以获取子View的高 ，宽了，因为调用了子View的 onMeasure()
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);


            Location location = new Location();
            //什么时候去换行？

            if(lineWidth+childView.getMeasuredWidth()+childView.getPaddingLeft()+childView.getPaddingRight()>width){
                //换行 累加高度
                lines+=1;
                lineWidth=childView.getMeasuredWidth()+childView.getPaddingLeft()+childView.getPaddingRight();;

            }else{
                lineWidth+=childView.getMeasuredWidth()+childView.getPaddingLeft()+childView.getPaddingRight();

            }

            height=(childView.getMeasuredHeight()+childView.getPaddingRight()+childView.getPaddingLeft())*lines;
            //String text = (String) ((TextView)childView).getText();
            location.left=lineWidth-childView.getMeasuredWidth();
            location.right=lineWidth;
            location.top=height-childView.getMeasuredHeight();
            location.bottom=height;

            setViewLocation(childView,location);
        }

        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    private void setViewLocation(View view, Location location){
        view.setTag(location);
    }


    private class Location{
        public int left;
        public int top;
        public int right;
        public int bottom;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        int count = getChildCount();
        for (int i=0;i<count;i++){
            View childView = getChildAt(i);
            Location loc = (Location) childView.getTag();
            childView.layout(loc.left,loc.top,loc.right,loc.bottom);

        }

    }


    private  OnTagItemClickListener onTagItemClickListener;

    public void setOnTagItemClickListener(final OnTagItemClickListener onTagItemClickListener) {
        this.onTagItemClickListener = onTagItemClickListener;
    }

    public interface OnTagItemClickListener{
       public void onItemClick(int position,View view);
    }

    private void addOnItemClickListener(final View childView,final int i){
        childView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTagItemClickListener.onItemClick(i,childView);
                childView.setBackgroundColor(Color.YELLOW);
            }
        });
    }
}
