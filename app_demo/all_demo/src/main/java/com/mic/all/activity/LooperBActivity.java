package com.mic.all.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.mic.all.R;

public class LooperBActivity extends AppCompatActivity {

    private static final String TAG = "looper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper_b);
        Log.d(TAG,"onCreate--B");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart--B");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume--B");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart--B");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause--B");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop--B");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy--B");
    }



}