package com.mic.ndk;

/**
 * Native 修改java方法与属性
 */
public class NDKModel {
    public static String id ="123";
    private String name ="nkd-model";
    private int age = 20;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int add(int x,int y){
        return x+y;
    }

    //ndk call java
    public native void changeName();

    //update static attr by native
    public static native void changeId();

    //c call java-method
    public native int callAddMethod();
}
