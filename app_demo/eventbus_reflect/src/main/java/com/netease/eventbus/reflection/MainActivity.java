package com.netease.eventbus.reflection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.Router;
import com.netease.eventbus.library.EventBus;
import com.netease.eventbus.library.annotation.Subscribe;
import com.netease.eventbus.library.mode.ThreadMode;
import com.netease.eventbus.reflection.bean.EventBean;

/**
 * 纯反射架构，也是EventBus3.0之前原理
 */

@Router(path="/eventbus_reflect/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bus);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND) // 默认不填线程
    public void getMessage(EventBean bean) {
        Log.e("EventBus >>1>> ", "thread = " + Thread.currentThread().getName());
        Log.e("EventBus >>1>> ", "" + bean.getName());
    }

    public void click(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}
