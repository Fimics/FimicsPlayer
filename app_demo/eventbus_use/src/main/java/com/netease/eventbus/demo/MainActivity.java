package com.netease.eventbus.demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.Router;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@Router(path="/eventbus_use/MainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_use);
//        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        EventBus.getDefault().register(this);
    }

    // 订阅方法
    @Subscribe
    public void event(String string) {
        Log.e("event >>> ", string);
    }

    // 测试优先级
    @Subscribe(priority = 10, sticky = true)
    public void event2(String string) {
        Log.e("event2 >>> ", string);
    }

    // 点击事件
    public void jump(View view) {
        EventBus.getDefault().postSticky("sticky");
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
