package com.mic.xsample.activity.opensource;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mic.xsample.R;

public class GlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ImageView imageView = this.findViewById(R.id.iv_glide);
        String url ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535905675854&di=bb25fb430e70bd2e384eaaab24245d38&imgtype=0&src=http%3A%2F%2Fd.paper.i4.cn%2Fmax%2F2016%2F12%2F22%2F11%2F1482375748699_742796.jpg";
        Glide.with(this).load(url).into(imageView);

    }
}
