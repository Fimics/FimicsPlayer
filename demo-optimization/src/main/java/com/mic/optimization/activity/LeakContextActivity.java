package com.mic.optimization.activity;

import android.os.Debug;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.mic.optimization.common.ListenerCollector;
import com.mic.optimization.view.LeakView;

public class LeakContextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_leak_context);
        //CommUtil commUtil = CommUtil.getInstance(getApplicationContext());
        //CommUtil commUtil = CommUtil.getInstance(this);

        Debug.startMethodTracing();
        LeakView  leakView = new LeakView(this);
        setContentView(leakView);
        Debug.stopMethodTracing();

    }

    @Override
    protected void onStop() {
        super.onStop();
        ListenerCollector.clearListener();
    }
}
