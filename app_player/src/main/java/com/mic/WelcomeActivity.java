package com.mic;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Debug;
import android.os.Environment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class WelcomeActivity extends AppCompatActivity {

    private static final int FUTURE =200;
    private static final int INTERVAL =100;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 运行时权限申请（6.0+）
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//            if (checkSelfPermission(perms[0]) == PackageManager.PERMISSION_DENIED) {
//                requestPermissions(perms, 200);
//            }
//        }
        //代码执行时间统计
//        File file = new File(Environment.getExternalStorageDirectory(),"app.trace");
//        Debug.startMethodTracing(file.getAbsolutePath());
        setContentView(R.layout.activity_welcome);
        findViewById(R.id.iv_welcome).setOnClickListener(view -> {
            goHome();
        });
        goHome();
       // Debug.stopMethodTracing();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelTimer();
    }

    private void goHome(){
         countDownTimer = new CountDownTimer(FUTURE,INTERVAL) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                MainActivity.start(WelcomeActivity.this);
//                MainJetpackActivity.start(WelcomeActivity.this);
                finish();
            }
        };
        countDownTimer.start();
    }

    private void cancelTimer(){
        if (countDownTimer==null)
            return;
        countDownTimer.cancel();
    }
}
