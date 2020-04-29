package com.mic.sofa;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.R;
import com.mic.core.utils.StatusBarUtil;
import com.mic.demo.view.MyScrollView;

public class VideoDetailMyScrollViewActivity extends AppCompatActivity {
    private View mTitleBar;
    private MyScrollView mScrollView;
    private ImageView mImageView;
    private int mImageViewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scrollview);
        StatusBarUtil.setStatusBarTranslucent(this);
        // QQ 效果 1.不断监听ScrollView的滚动，判断当前滚动的位置跟头部的ImageView比较计算背景透明度
        // 2.自定义 Behavior  简书有文章

        //  1.刚进来背景完全透明
        mTitleBar = findViewById(R.id.title_bar);
        mTitleBar.getBackground().setAlpha(0);
        mImageView = findViewById(R.id.image_view);

        // 这里是拿不到高度，布局绘制流程源码分析
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mImageViewHeight = mImageView.getMeasuredHeight();
            }
        });

        // 不断的监听滚动 判断当前滚动的位置跟头部的ImageView比较计算背景透明度
        mScrollView = findViewById(R.id.scroll_view);
        mScrollView.setOnScrollChangeListener(new MyScrollView.ScrollChangeListener() {
            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                // 获取图片的高度，根据当前滚动的位置，计算alpha 值
                if (mImageViewHeight == 0) return;

                // mImageViewHeight - TitleBar的高度
                float alpha = (float) t / mImageViewHeight;

                if (alpha <= 0) {
                    alpha = 0;
                }

                if (alpha > 1) {
                    alpha = 1;
                }

                mTitleBar.getBackground().setAlpha((int) (alpha * 255));
            }
        });
    }
}
