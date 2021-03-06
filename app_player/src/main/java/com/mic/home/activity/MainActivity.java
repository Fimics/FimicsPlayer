package com.mic.home.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.mic.R;
import com.mic.core.BaseActivity;
import com.mic.core.utils.hotfix.FixDexManager;
import com.mic.training.UserAidl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import com.mic.service.MessageService;


public class MainActivity extends BaseActivity implements View.OnClickListener {


    UserAidl mUserAidl;
    Button btnBindService;
    Button btnShowname;
    Button btnHookActivty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_temp);
        //startService(new Intent(this, MessageService.class));

//        btnBindService = findViewById(R.idgs.btn_bindService);
//        btnShowname=findViewById(R.id.btn_showname);
//        btnHookActivty=findViewById(R.id.btn_hook_activty);

        //测试全局异常捕获
        //int a = 20 / 1;

        //fixDexBug();
        //getResources();

        // 注册，思考为什么要注册？
        EventBus.getDefault().register(this);
        EventBus.getDefault().post("hello eventbus");


    }


    /**
     * threadMode 执行的线程方式
     * priority 执行的优先级，值越大优先级越高
     * sticky 粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100,sticky = true)
    public void test2(String msg){
        // 如果有一个地方用 EventBus 发送一个 String 对象，那么这个方法就会被执行
        Log.e("TAG","msg2 = "+msg);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void setContentView() {

    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_bindService:
//                testAidl();
//                break;
//            case R.id.btn_showname:
//                try {
//                    Toast.makeText(this, mUserAidl.getUserName(), Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                break;
//        }
    }

    private class Conn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mUserAidl = UserAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onBindingDied(ComponentName name) {

        }
    }

    private void testAidl() {
//
        Intent intent = new Intent(this, MessageService.class);
        Conn conn = new Conn();
        bindService(intent, conn, Context.BIND_AUTO_CREATE);


    }


    private void testDb() {

    }


    private void fixDexBug() {
        File fixFile = new File(Environment.getExternalStorageDirectory(), "fix.dex");

        if (fixFile.exists()) {
            FixDexManager fixDexManager = new FixDexManager(this);

            try {
                fixDexManager.loadFixDex();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
