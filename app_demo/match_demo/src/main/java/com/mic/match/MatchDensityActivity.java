package com.mic.match;

import android.os.Bundle;

import com.mic.match.density.BaseActivity;
import com.mic.router.annotation.Router;


@Router(path="/match_demo/MatchDensityActivity")
public class MatchDensityActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_match_density);
    }
}
