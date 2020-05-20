package com.mic.demo.view.qqslidingmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.mic.R;


// 3. 自定义一个View  extends HorizaontalScrollView
public class SlidingMenu extends HorizontalScrollView {

    private static final String TAG = "HorizontalScrollView";

    // 4.1.2 获取菜单布局 - 菜单布局
    private View mMenuView;

    // 4.3 默认关闭的状态 - 菜单的宽度
    private int mMenuWidth;

    // 7.手指快速滑动 - 手势处理类
    private GestureDetector mGestureDetector;

    // 7.手指快速滑动 - 菜单是否打开
    private boolean mMenuIsOpen = false;

    // 9.1.2.2 把阴影加入新的内容容器  - 阴影View
    private ImageView mShadowView;

    public SlidingMenu(Context context) {
        // 在代码中 new 的时候调用
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        // 写在布局文件中的时候调用
        this(context, attrs, 0);
    }

    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        // 也是写在布局文件中调用 但是会有style
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.SlidingMenu);
        // 获取自定义属性
        float rightPadding = array.getDimension(R.styleable.SlidingMenu_rightPadding,dip2px(50));

        // 4.2.1 指定菜单的宽度 = 屏幕的宽度 - 距右边宽度 用户能够自定义（自定义属性）
        mMenuWidth = (int) (getScreenWidth() - rightPadding);
        array.recycle();

        // 7.1 初始化手势处理类
        mGestureDetector = new GestureDetector(context,new GestureListener());
    }


    // 代表整个布局加载完毕会执行的方法
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 4.用代码动态的指定布局的宽度
        // 4.1 获取菜单和内容的view
        // 4.1.1 获取根布局LinearLayout
        ViewGroup container = (ViewGroup) getChildAt(0);
        // 4.1.2 获取菜单布局
        mMenuView =  container.getChildAt(0);

        // 4.1.2 获取内容布局

        // 9.处理内容的阴影效果
        // 9.1 思路在内容布局的外面加一层阴影  ImageView
        // 9.1.1 把原来的内容从根布局里面移除
        View oldContentView = container.getChildAt(1);
        container.removeView(oldContentView);
        // 9.1.2 新建一个布局容器  = 原来的内容 + 阴影
        FrameLayout newContentView = new FrameLayout(getContext());
        // 9.1.2.1 把原来的内容加入新的内容容器
        newContentView.addView(oldContentView);
        // 9.1.2.2 把阴影加入新的内容容器
        mShadowView = new ImageView(getContext());
        mShadowView.setBackgroundColor(Color.parseColor("#99000000"));
        newContentView.addView(mShadowView);
        // 9.1.3 把新的容器再放回原来的位置
        container.addView(newContentView);

        // 9.2 滑动到不同的位置改变其阴影透明度

        // 4.2 指定菜单和内容的view宽度
        mMenuView.getLayoutParams().width = mMenuWidth;
        // 4.2.2 指定内容的宽度 = 屏幕的宽度
        newContentView.getLayoutParams().width = getScreenWidth();

        // 4.3 默认关闭的状态  要让其自己滚动一段距离  菜单的宽度

    }

    // 6.处理onTouch事件 手指抬起 肯定要判断一下菜单是打开的还是关闭
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        // 7.2 处理手指快速滑动  手势处理类使用  拦截
        if(mGestureDetector.onTouchEvent(ev)){
            return false;
        }

        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                // 手指抬起
                int currentScrollX = getScrollX();
                if(currentScrollX > mMenuWidth/2){
                    // 当前滚动x > 菜单宽度的一半
                    closeMenu();
                }else{
                    openMenu();
                }
                return false;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 6.打开菜单
     */
    private void openMenu() {
        // 滚动到当前位置  并且带一个动画
        smoothScrollTo(0,0);
        mMenuIsOpen = true;
    }

    /**
     * 6.关闭菜单
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        mMenuIsOpen = false;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        // 4.3 默认关闭的状态 摆放子布局执行的方法
        if(changed){
            // 4.3 默认关闭的状态 要让其自己滚动一段距离  菜单的宽度
            scrollTo(mMenuWidth,0);
        }
    }

    /**
     * 4. dip 转 px
     */
    private int dip2px(int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
    }

    /**
     * 4. 获取屏幕的宽度
     */
    public int getScreenWidth() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    /**
     * 7.1 初始化手势处理类  - 手势处理类的监听回调
     */
    private class GestureListener extends GestureDetector.SimpleOnGestureListener{
        // 只需要复写快速滑动
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            // Log.e(TAG,velocityX+"");
            // Bug  判断左右还是上下   只有左右快速滑动才切换
            if(Math.abs(velocityY) > Math.abs(velocityX)){
                // 代表上下快速划  这个时候不做处理
                return super.onFling(e1, e2, velocityX, velocityX);
            }

            // 向右边快速滑动 是一个 大于 0 的数
            // 向左边快速滑动 是一个 小于 0 的数
            // 逻辑  如果是菜单打开  向左边快速滑动的时候 应该切换菜单状态
            if(mMenuIsOpen){
                if(velocityX < 0){
                    toggleMenu();
                    return true;
                }
            }
            //       如果是菜单关闭  向右边快速滑动的时候 应该切换菜单状态
            else{
                if(velocityX > 0){
                    toggleMenu();
                    return true;
                }
            }
            return super.onFling(e1, e2, velocityX, velocityX);
        }
    }

    /**
     * 7.切换菜单的状态
     */
    public void toggleMenu() {
        if(mMenuIsOpen){
            closeMenu();
        }else{
            openMenu();
        }
    }

    // 8.处理菜单的抽屉效果
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        // 滚动回调的方法  这个方法是不断的执行的
        // l 代表当前滚动的距离   scrollX   mMenuWidth --> 0
        // Log.e(TAG,l+"");
        // 8.处理菜单的抽屉效果  让菜单移动一段距离
        mMenuView.setTranslationX(l*0.8f);

        // 9.2 滑动到不同的位置改变其阴影透明度
        // 透明度肯定是一个 梯度值
        float scale = l*1f / mMenuWidth;// 600   600/600  1   300/600  0.5   0/600  0   1 --> 0
        float alphaScale = 1 - scale; // 0 -- > 1
        mShadowView.setAlpha(alphaScale);

        // 大家可以用了  真正能够完整用到代码中  尽量解决bug
    }

    // 10.事件传递和拦截
    /**
     * 事件的分发
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // 如果菜单打开   并且 手指按下的位置 > menuWidth   关闭切换菜单   停止分发事件
        if(mMenuIsOpen){
            int fingerX = (int) ev.getX();
            // 手指按下的位置 > menuWidth
            if(fingerX>mMenuWidth){
                // 关闭切换菜单
                toggleMenu();
                // 停止分发事件
                return false;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 事件的拦截
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }
}
