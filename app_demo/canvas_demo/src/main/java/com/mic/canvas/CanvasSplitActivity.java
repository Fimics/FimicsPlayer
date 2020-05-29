package com.mic.canvas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.Router;


@Router(path = "/canvas_demo/CanvasSplitActivity")
public class CanvasSplitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CanvasSplitView(this));

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.pic);
//        bitmap.getWidth();
//        bitmap.getHeight();
//        int pixel = bitmap.getPixel(0, 0);
    }
}
