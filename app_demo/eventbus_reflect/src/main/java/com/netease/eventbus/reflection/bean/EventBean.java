package com.netease.eventbus.reflection.bean;


import androidx.annotation.NonNull;

public class EventBean {

    private String name;

    public EventBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "name = " + name;
    }
}


