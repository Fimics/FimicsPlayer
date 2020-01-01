package com.mic.training.xaspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记切点 注解
 */
@Target(ElementType.METHOD) // Target 放在哪个位置
@Retention(RetentionPolicy.RUNTIME)// RUNTIME 运行时 xUtils  CLASS 代表编译时期 ButterKnife   SOURCE 代表资源
public @interface CheckNet { // @interface 注解

}
