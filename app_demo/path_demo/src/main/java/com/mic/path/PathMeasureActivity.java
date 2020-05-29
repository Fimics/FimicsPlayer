package com.mic.path;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.path.pathmeasure.PathMeasureView;
import com.mic.router.annotation.Router;

@Router(path="/path_demo/PathMeasureActivity")
public class PathMeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureView(this));
    }
}
