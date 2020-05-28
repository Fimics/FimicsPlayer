package com.mic.match;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mic.router.annotation.Router;

@Router(path = "/match_demo/MatchPercentActivity")
public class MatchPercentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_match_percent);
    }
}
