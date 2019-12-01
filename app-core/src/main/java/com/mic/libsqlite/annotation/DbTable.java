package com.mic.libsqlite.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//type 用在类上
@Retention(RetentionPolicy.RUNTIME)//用在编译时，编译以后OVeride就没有了
public @interface DbTable {
    public String value();
}
