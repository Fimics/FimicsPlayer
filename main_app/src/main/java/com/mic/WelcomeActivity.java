package com.mic;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mic.home.HomeActivity;

public class WelcomeActivity extends AppCompatActivity {

    private static final int FUTURE =200;
    private static final int INTERVAL =100;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        findViewById(R.id.iv_welcome).setOnClickListener(view -> {
            goHome();
        });
        goHome();
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
                HomeActivity.start(WelcomeActivity.this);
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
