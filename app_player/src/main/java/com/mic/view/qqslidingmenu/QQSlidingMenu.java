package com.mic.view.qqslidingmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;

import com.mic.R;
import com.mic.view.slidingmenu.ScreenUtils;


public class QQSlidingMenu extends HorizontalScrollView {
    // 菜单的宽度
    private int mMenuWidth;

    private View mContentView, mMenuView, mShadowView;

    // GestureDetector 处理快速滑动,手势处理类
    private GestureDetector mGestureDetector;

    // 菜单是否打开
    private boolean mMenuIsOpen = false;

    // 是否拦截
    private boolean mIsIntercept = false;

    public QQSlidingMenu(Context context) {
        this(context, null);
    }

    public QQSlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQSlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 初始化自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingMenu);

        float rightMargin = array.getDimension(
                R.styleable.SlidingMenu_menuRightMargin, ScreenUtils.dip2px(context, 50));
        // 菜单页的宽度是 = 屏幕的宽度 - 右边的一小部分距离（自定义属性）
        mMenuWidth = (int) (getScreenWidth(context) - rightMargin);
        array.recycle();

        mGestureDetector = new GestureDetector(context, mGestureListener);
    }

    private GestureDetector.OnGestureListener mGestureListener = new
            GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    // 只关注快速滑动,只要快速就会回掉
                    // 条件 打开的时候往右边快速滑动切换（关闭），关闭的时候往左边快速滑动切换（打开）
                    Log.e("TAG", "velocityX -> " + velocityX);
                    // 快速往左边滑动的时候是一个负数，往右边滑动的时候是一个正数
                    if (mMenuIsOpen) {
                        // 打开的时候往右边快速滑动切换（关闭）
                        if (velocityX < 0) {
                            closeMenu();
                            return true;
                        }
                    } else {
                        // 关闭的时候往左边快速滑动切换（打开）
                        if (velocityX > 0) {
                            openMenu();
                            return true;
                        }
                    }
                    return super.onFling(e1, e2, velocityX, velocityY);
                }
            };

    // 1.宽度不对（乱套了），指定宽高
    @Override
    protected void onFinishInflate() {
        // 这个方法是布局解析完毕也就是 XML 布局文件解析完毕
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

        // 把内容布局单独提取出来，
        mContentView = container.getChildAt(1);
        ViewGroup.LayoutParams contentParams = mContentView.getLayoutParams();
        container.removeView(mContentView);
        // 然后在外面套一层阴影，
        RelativeLayout contentContainer = new RelativeLayout(getContext());
        contentContainer.addView(mContentView);
        mShadowView = new View(getContext());
        mShadowView.setBackgroundColor(Color.parseColor("#55000000"));
        contentContainer.addView(mShadowView);
        // 最后在把容器放回原来的位置
        contentParams.width = getScreenWidth(getContext());
        contentContainer.setLayoutParams(contentParams);
        container.addView(contentContainer);
        mShadowView.setAlpha(0.0f);

        // 2. 初始化进来是关闭 发现没用
        // scrollTo(mMenuWidth,0);
    }

    // 4. 处理右边的缩放，左边的缩放和透明度，需要不断的获取当前滚动的位置
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // Log.e("TAG", "l -> " + l);// 变化是 mMenuWidth - 0
        // 算一个梯度值
        float scale = 1f * l / mMenuWidth;// scale 变化是 1 - 0

        // 控制阴影 0 - 1
        float alphaScale = 1 - scale;
        mShadowView.setAlpha(alphaScale);

        ViewCompat.setTranslationX(mMenuView, 0.6f * l);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // 2. 初始化进来是关闭
        scrollTo(mMenuWidth, 0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        mIsIntercept = false;

        // 2. 处理事件拦截 + ViewGroup 事件分发的源码实践
        //    当菜单打开的时候，手指触 摸右边内容部分需要关闭菜单，还需要拦截事件（打开情况下点击内容页不会响应点击事件）
        if (mMenuIsOpen) {
            float currentX = ev.getX();
            if (currentX > mMenuWidth) {
                // 1.关闭菜单
                closeMenu();
                // 2.子 View 不需要响应任何事件（点击和触摸），拦截子 View 的事件
                // 如果返回 true 代表我会拦截子View的事件，但是我会响应自己的 onTouch 事件
                mIsIntercept = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    // 3.手指抬起是二选一，要么关闭要么打开
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 如果有拦截不要执行自己的 onTouch
        if (mIsIntercept) {
            return true;
        }

        if (mGestureDetector.onTouchEvent(ev)) {
            // 快速滑动触发了下面的就不要执行了
            return true;
        }
        // 1. 获取手指滑动的速率，当期大于一定值就认为是快速滑动 ， GestureDetector（系统提供好的类）
        // 2. 处理事件拦截 + ViewGroup 事件分发的源码实践
        //    当菜单打开的时候，手指触 摸右边内容部分需要关闭菜单，还需要拦截事件（打开情况下点击内容页不会响应点击事件）

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
        smoothScrollTo(0, 0);
        mMenuIsOpen = true;
    }

    /**
     * 关闭菜单 滚动到 mMenuWidth 的位置
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        mMenuIsOpen = false;
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
