package com.mic.frame.mvp.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 通过注解注入 prewenter
 */
@Target(ElementType.FIELD)// 属性
@Retention(RetentionPolicy.RUNTIME)// 运行时
public @interface InjectPresenter {
}
