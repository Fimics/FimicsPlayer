package com.mic.ndk;

import java.util.UUID;

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

    //静态获取uuid的方法，然后c调用这个方法获取uuid
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    //ndk call java
    public native void changeName();

    //update static attr by native
    public static native void changeId();

    //c call java-method
    public native int callAddMethod();

    //c 调用java 的static方法
    public native static String callStaticMethod();


}
