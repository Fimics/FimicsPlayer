package com.mic.demo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.demo.viewwy.bezier.PathView;

public class PathBezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathView(this));
    }

}
