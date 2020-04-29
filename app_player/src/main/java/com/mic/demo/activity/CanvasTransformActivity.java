package com.mic.demo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.demo.viewwy.canvas.CanvasSaveRestoreView;

public class CanvasTransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasSaveRestoreView(this));
    }
}
