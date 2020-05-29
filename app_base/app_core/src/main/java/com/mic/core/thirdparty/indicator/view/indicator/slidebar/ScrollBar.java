package com.mic.core.thirdparty.indicator.view.indicator.slidebar;

import android.view.View;

public interface ScrollBar {
    enum Gravity {
        TOP,
        TOP_FLOAT,
        BOTTOM,
        BOTTOM_FLOAT,
        CENTENT,
        CENTENT_BACKGROUND
    }

    int getHeight(int tabHeight);

    int getWidth(int tabWidth);

    View getSlideView();

    Gravity getGravity();

    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
}
