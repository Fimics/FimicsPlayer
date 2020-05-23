package com.netease.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.Router;
import com.netease.eventbus.annotation.Subscribe;
import com.netease.eventbus.annotation.mode.ThreadMode;
import com.netease.eventbus.apt.EventBusIndex;
import com.netease.eventbus.library.XEventBus;
import com.netease.eventbus.model.UserInfo;

import static java.lang.Thread.sleep;

@Router(path="/eventbus_demo/MainActivity")
public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 163) {
                UserInfo user = (UserInfo) msg.obj;
                tv.setText(user.toString());
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_eventbus);

        tv = findViewById(R.id.tv);
        XEventBus.getDefault().addIndex(new EventBusIndex());
        XEventBus.getDefault().register(this);
    }

    // 跳转按钮
    public void jump(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    // 粘性按钮
    public void sticky(View view) {
        XEventBus.getDefault().postSticky(new UserInfo("simon", 35));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void abc(UserInfo user) {
//        tv.setText(user.toString());
//        Log.e("abc", user.toString());
        Message msg = new Message();
        msg.obj = user;
        msg.what = 163;
        handler.sendMessage(msg);
        Log.e("abc", user.toString());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC, priority = 1)
    public void abc2(UserInfo user) {
        //tv.setText(user.toString());
        Log.e("abc2", user.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (XEventBus.getDefault().isRegistered(this)) {
            XEventBus.getDefault().unregister(this);
        }
        XEventBus.clearCaches();
    }
}
