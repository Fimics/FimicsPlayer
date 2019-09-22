package com.mic.xsample.activity.view;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;



import com.mic.xsample.R;
import com.mic.xsample.utils.StatusBarUtil;

public class StatusBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar);
        StatusBarUtil.setStatusBarColor(this,Color.YELLOW);
        // QQ 效果 1.不断监听ScrollView的滚动，判断当前滚动的位置跟头部的ImageView比较计算背景透明度
        // 2.自定义 Behavior  简书有文章
    }
}
