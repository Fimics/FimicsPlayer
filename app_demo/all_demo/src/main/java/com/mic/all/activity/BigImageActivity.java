package com.mic.all.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mic.all.R;
import com.mic.all.view.NeBigView;
import com.mic.router.annotation.Router;

import java.io.IOException;
import java.io.InputStream;

@Router(path = "/all_demo/BigImageActivity")
public class BigImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);

        NeBigView bigView = findViewById(R.id.bigview);
        InputStream is = null;

        try {
            is = getAssets().open("aaa.jpg");
            bigView.setImage(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}