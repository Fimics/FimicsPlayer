package com.mic.path;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.path.bezier.PathView;
import com.mic.router.annotation.Router;

@Router(path="/path_demo/PathBezierActivity")
public class PathBezierActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathView(this));
    }

}
