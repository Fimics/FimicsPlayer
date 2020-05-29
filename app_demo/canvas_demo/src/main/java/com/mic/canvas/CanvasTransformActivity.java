package com.mic.canvas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.Router;


@Router(path = "/canvas_demo/CanvasTransformActivity")
public class CanvasTransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasSaveRestoreView(this));
    }
}
