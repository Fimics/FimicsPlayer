package com.mic.view;

public class Bottom {

    public String name;
    public int resId;

    public Bottom(int resId) {
        this.resId=resId;
    }

    public Bottom(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }
}
