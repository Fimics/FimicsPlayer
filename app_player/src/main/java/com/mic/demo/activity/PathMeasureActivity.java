package com.mic.demo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.demo.viewwy.pathmeasure.PathMeasureView;

public class PathMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureView(this));
    }
}
