package com.mic.home.activity;

import android.os.Bundle;
import android.os.Looper;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.R;


public class UpdateUIActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ui);
        mTextView=findViewById(R.id.textview);
        updateUi();
    }


    private void updateUi(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mTextView.setText("jjjjjj");
            }
        }).start();


        new Thread(){
            @Override
            public void run() {
                // 直接的情况下不会出错，因为 布局的绘制流程在 onResume 方法之后执行的
                // 暂停1s后这个时候 onResume() 一般会执行完毕，布局开始绘制，
                // 如果你再次去更新布局，那么就会调用 requestLayout() ,会去 checkThread 抛异常
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Looper.prepare();
                // 子线程更新 Toast toast是加载在 WindowManager 上面的
                Toast.makeText(UpdateUIActivity.this,"111",Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
    }
}
