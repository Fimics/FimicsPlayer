package com.mic.demo.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.mic.R;


public class AnimatorActivity extends AppCompatActivity {
    AppCompatButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        button = findViewById(R.id.btn);


    }

    public void scale(View view) {
//
        ObjectAnimator objectAnimator = ObjectAnimator.
                ofFloat(button, "scaleX", 2f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }
}
