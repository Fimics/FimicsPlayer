package com.mic.user.model;

import com.mic.thirdparty.sqlite.annotation.DbFiled;
import com.mic.thirdparty.sqlite.annotation.DbTable;

@DbTable("person")
public class Person {

    @DbFiled("name")
    private String name;
    @DbFiled("age")
    private int age;

    // 默认的构造方法
    public Person(){

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}