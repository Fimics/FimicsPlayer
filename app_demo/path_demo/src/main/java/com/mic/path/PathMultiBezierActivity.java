package com.mic.path;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.Router;

@Router(path="/path_demo/PathMultiBezierActivity")
public class PathMultiBezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_multi_bezier);
    }
}
