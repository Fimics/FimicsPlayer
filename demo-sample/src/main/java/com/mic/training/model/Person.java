package com.mic.training.model;

import com.mic.libsqlite.annotation.DbFiled;
import com.mic.libsqlite.annotation.DbTable;

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