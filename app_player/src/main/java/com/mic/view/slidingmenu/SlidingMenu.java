package com.mic.view.slidingmenu;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

import com.mic.R;


public class SlidingMenu extends HorizontalScrollView {

    // 菜单的宽度
    private int mMenuWidth;

    private View mContentView,mMenuView;

    public SlidingMenu(Context context) {
        this(context,null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 初始化自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);

        float rightMargin = array.getDimension(R.styleable.SlidingMenu_menuRightMargin, ScreenUtils.dip2px(context, 50));
        // 菜单页的宽度是 = 屏幕的宽度 - 右边的一小部分距离（自定义属性）
        mMenuWidth = (int) (getScreenWidth(context) - rightMargin);
        array.recycle();
    }

    // 1.宽度不对（乱套了），指定宽高
    @Override
    protected void onFinishInflate() {
        // 这个方法是布局解析完毕也就是 XML 布局文件解析完毕  在onCreate中执行，onLayout在onResume
        super.onFinishInflate();
        // 指定宽高 1.内容页的宽度是屏幕的宽度
        // 获取LinearLayout
        ViewGroup container = (ViewGroup) getChildAt(0);

        int childCount = container.getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("只能放置两个子View!");
        }

        mMenuView = container.getChildAt(0);
        // 设置只能通过 LayoutParams ，
        ViewGroup.LayoutParams menuParams = mMenuView.getLayoutParams();
        menuParams.width = mMenuWidth;
        // 7.0 以下的手机必须采用下面的方式
        mMenuView.setLayoutParams(menuParams);

        // 2.菜单页的宽度是 屏幕的宽度 - 右边的一小部分距离（自定义属性）
        mContentView = container.getChildAt(1);
        ViewGroup.LayoutParams contentParams = mContentView.getLayoutParams();
        contentParams.width = getScreenWidth(getContext());
        mContentView.setLayoutParams(contentParams);

        // 2. 初始化进来是关闭 发现没用
        // scrollTo(mMenuWidth,0);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //在onCreate中执行，onLayout在onResume
        scrollTo(mMenuWidth,0);
    }



    // 3.手指抬起是二选一，要么关闭要么打开
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        // 1. 获取手指滑动的速率，当期大于一定值就认为是快速滑动 ， GestureDetector（系统提供好的类）
        // 2. 处理事件拦截 + ViewGroup 事件分发的源码实践
        //    当菜单打开的时候，手指触摸右边内容部分需要关闭菜单，还需要拦截事件（打开情况下点击内容页不会响应点击事件）

        if (ev.getAction() == MotionEvent.ACTION_UP) {
            // 只需要管手指抬起 ，根据我们当前滚动的距离来判断
            int currentScrollX = getScrollX();

            if (currentScrollX > mMenuWidth / 2) {
                // 关闭
                closeMenu();
            } else {
                // 打开
                openMenu();
            }
            // 确保 super.onTouchEvent() 不会执行
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单 滚动到 0 的位置
     */
    private void openMenu() {
        // smoothScrollTo 有动画
        smoothScrollTo(0, mMenuWidth);
    }

    /**
     * 关闭菜单 滚动到 mMenuWidth 的位置
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
    }

    // 4. 处理右边的缩放，左边的缩放和透明度，需要不断的获取当前滚动的位置


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.e("TAG", "l -> " + l);// 变化是 mMenuWidth - 0

        float scale =1f*l/mMenuWidth;
        //右边的缩放最小0.7 最大1

        float rightScale = 0.7f+0.3f*scale;
        // 设置右边的缩放,默认是以中心点缩放
        // 设置缩放的中心点位置
        ViewCompat.setPivotX(mContentView,0);
        ViewCompat.setPivotY(mContentView, mContentView.getMeasuredHeight() / 2);
        ViewCompat.setScaleX(mContentView,rightScale);
        ViewCompat.setScaleY(mContentView, rightScale);

        // 菜单的缩放和透明度
        // 透明度是 半透明到完全透明  0.5f - 1.0f
        float leftAlpha = 0.5f + (1-scale)*0.5f;
        ViewCompat.setAlpha(mMenuView,leftAlpha);
        // 缩放 0.7f - 1.0f
        float leftScale = 0.7f + (1-scale)*0.3f;
        ViewCompat.setScaleX(mMenuView,leftScale);
        ViewCompat.setScaleY(mMenuView, leftScale);


        // 最后一个效果 退出这个按钮刚开始是在右边，安装我们目前的方式永远都是在左边
        // 设置平移，先看一个抽屉效果
        // ViewCompat.setTranslationX(mMenuView,l);
        // 平移 l*0.7f
        ViewCompat.setTranslationX(mMenuView, 0.25f*l);
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    private int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * Dip into pixels
     */
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
