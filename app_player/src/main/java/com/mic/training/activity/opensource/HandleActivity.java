package com.mic.training.activity.opensource;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.R;


public class HandleActivity extends AppCompatActivity {


    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText("222");
        }
    };

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);

        mTextView = findViewById(R.id.text);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Message message = new Message();
                handler.sendMessage(message);
            }
        });
    }
}
