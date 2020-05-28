package com.mic.color;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.Router;

@Router(path = "/color_demo/ColorFilterViewActivity")
public class ColorFilterViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ColorFilterView(this));
    }
}
