package com.mic.match;

import android.app.Activity;
import android.os.Bundle;

import com.mic.match.R;
import com.mic.router.annotation.Router;

@Router(path ="/match_demo/MatchPercentActivity")
public class MatchPercentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_match_percent);
    }
}
