package com.mic.training.activity.opensource;

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
import android.widget.Toast;


import com.mic.frame.common.BaseActivity;
import com.mic.libcore.hotfix.FixDexManager;
import com.mic.training.R;
import com.mic.training.UserAidl;
import com.mic.training.service.MessageService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings("unused")
public class MainActivity extends BaseActivity {


    UserAidl mUserAidl;
    @BindView(R.id.btn_bindService)
    Button btnBindService;
    @BindView(R.id.btn_showname)
    Button btnShowname;
    @BindView(R.id.btn_hook_activty)
    Button btnHookActivty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //startService(new Intent(this, MessageService.class));

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

    @OnClick({R.id.btn_bindService, R.id.btn_showname})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bindService:
                testAidl();
                break;
            case R.id.btn_showname:
                try {
                    Toast.makeText(this, mUserAidl.getUserName(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
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
