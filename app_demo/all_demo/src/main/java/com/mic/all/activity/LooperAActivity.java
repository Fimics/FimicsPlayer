package com.mic.all.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import com.mic.all.R;
import com.mic.router.annotation.Router;

@Router(path = "/all_demo/LooperAActivity")
public class LooperAActivity extends AppCompatActivity {

    private static final String TAG = "looper";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_looper_a);
        Log.d(TAG,"onCreate--A");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LooperAActivity.this,LooperBActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart--A");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume--A");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"onRestart--A");
    }


    @Override
    protected void onPause() {
        super.onPause();
        //
        Log.d(TAG,"onPause--A");
        //此处如果执行耗时任务，就不会执行跳转界面的生命周期
//        for (int i=0;i<10000000;i++){
//            int a = i+i;
//        }
//        Log.d(TAG,"onPause--A  end");
//        Log.d(TAG,"onPause--A");
//        while (true){
//            int i = 1+1;
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop--A");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy--A");
    }


}